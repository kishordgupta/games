package com.rhymes.game.entity.elements.physical;

import java.util.Vector;

import aurelienribon.box2deditor.FixtureAtlas;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.*;
import com.badlogic.gdx.scenes.scene2d.ui.utils.AndroidClipboard;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.elements.nonphysical.Arrow;
import com.rhymes.game.entity.elements.nonphysical.Player;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.renderer.Renderer;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.helpers.Helper;
import com.rhymes.game.entity.elements.nonphysical.Arrow;
import com.rhymes.game.entity.elements.physical.PhysicsTest.MovingPlatform;
import com.rhymes.game.entity.elements.ui.PhysicsBody2;
import com.rhymes.game.entity.elements.unsorted.Background;

public class CarSpeedPro extends ElementBase {

	OrthographicCamera cam = new OrthographicCamera(480 * GameMenuInfo.ratio_w,
			320 * GameMenuInfo.ratio_h);

	final static float MAX_VELOCITY = 7f;
	String imagePath;
	private World world;
	
	Fixture playerPhysicsFixture;
	Fixture playerSensorFixture;
	SpriteBatch batch;
	BitmapFont font;
	float w = 200;
	float h = 200;

	public float radius;

	private boolean active = true;

	public static final short categoryBits = 1;

	private boolean first = true;
	private Vector2 pos = new Vector2();
	
	private Body riderBody;
	private Body body;
	private Body leftWheel;
	private Body rightWheel;
	private Body frontWheel;
	private Body leftAxle;
	private Body rightAxle;
	
	
	private RevoluteJoint leftWheelRevoluteJoint;
	private RevoluteJoint rightWheelRevoluteJoint;
	private RevoluteJoint frontWheelRevoluteJoint;
	
	private PrismaticJoint leftAxlePrismaticJoint;
	private PrismaticJoint rightAxlePrismaticJoint;
	private PrismaticJoint frontAxlePrismaticJoint;
	
	private Joint leftWeldJoint;
	private Joint rightWeldJoint;

	private float motorSpeed = 0;


	
	
	private float carWidth = PhysicsHelper
			.ConvertToBox(256 * GameMenuInfo.ratio_w)
			* GameMenuInfo.carSizeCoeff;
//	private float carHeight = PhysicsHelper
//			.ConvertToBox(10 * GameMenuInfo.ratio_h)
//			* GameMenuInfo.carSizeCoeff;

	// axleContainer Distance and depth fromt the worldcenter of car

//	private float axleContainerDistance = PhysicsHelper
//			.ConvertToBox(90 * GameMenuInfo.ratio_w)
//			* GameMenuInfo.carSizeCoeff;
	private float axleContainerDepth = PhysicsHelper
			.ConvertToBox(10 * GameMenuInfo.ratio_h)
			* GameMenuInfo.carSizeCoeff;

	// /axle container width and height
//	private float axleContainerWidth = PhysicsHelper
//			.ConvertToBox(05f * GameMenuInfo.ratio_w)
//			* GameMenuInfo.carSizeCoeff;
//	private float axleContainerHeight = PhysicsHelper
//			.ConvertToBox(0.5f * GameMenuInfo.ratio_h)
//			* GameMenuInfo.carSizeCoeff;

//	private float leftAxleContainerAngle = (MathUtils.degreesToRadians * 0)/**
//	 * 
//	 * GameMenuInfo.carSizeCoeff
//	 */
//	;
//	private float rightAxleContainerAngle = (MathUtils.degreesToRadians * 0)/**
//	 * 
//	 * GameMenuInfo.carSizeCoeff
//	 */
//	;
	// /axle width and height
	private float axleWidth = PhysicsHelper
			.ConvertToBox(1f * GameMenuInfo.ratio_w)
			* GameMenuInfo.carSizeCoeff;
	private float axleAngle = (MathUtils.degreesToRadians * 0)/**
			 * 
			 * GameMenuInfo.carSizeCoeff
			 */
			; 
	private float leftAxleAngle = axleAngle;
	private float rightAxleAngle = axleAngle;
	private float frontAxleAngle =axleAngle;
	// /wheel radious
	private float wheelRadius = PhysicsHelper
			.ConvertToBox(45 * GameMenuInfo.ratio_w)
			* GameMenuInfo.carSizeCoeff;
	
	private float frontWheelRadius = PhysicsHelper
			.ConvertToBox(45 * GameMenuInfo.ratio_w)
			* GameMenuInfo.carSizeCoeff;
	
	// rider width height and angle
	private float manWidth = PhysicsHelper.ConvertToBox(45)
			* GameMenuInfo.ratio_w * GameMenuInfo.carSizeCoeff;
	private float manHeight = PhysicsHelper.ConvertToBox(10)
			* GameMenuInfo.ratio_h * GameMenuInfo.carSizeCoeff;


	private String carType;// = Constants.carTypeHuwwer;
	
	private String physicsBody;// = AssetConstants.PHY_BENGA_BODY;
	private String bodyfixturepath;// = "bengabody";

	private String imageCarBody;//= AssetConstants.PHY_IMG_HUWWER_BODY ;
	private String imageCarWheel;//=AssetConstants.PHY_IMG_HUWWER_WHEEL;
	private String imageaxle;//=AssetConstants.PHY_IMG_LEFTAXLE;
	
	

	

//	private TextureRegion imageRider;

	private TextureRegion imageBikeBody;

	private Background rider;
	private float agerY;
//	private Body leftAxleContainer;
//	private Body rightAxleContainer;

	public float getMotorSpeed() {
		return motorSpeed;
	}

	public void setMotorSpeed(float motorSpeed) {
		this.motorSpeed = motorSpeed;
	}

	  
	public void update(float dt){
		
		if(leftAxlePrismaticJoint!=null)
		{
			if((PhysicsHelper.BOX_TO_WORLD*(leftAxlePrismaticJoint.getReactionForce(1/dt).x)>10)&&(PhysicsHelper.BOX_TO_WORLD*(leftAxlePrismaticJoint.getReactionForce(1/dt).y)>10))
			{
//				float x = PhysicsHelper.BOX_TO_WORLD*(leftAxlePrismaticJoint.getReactionForce(1/dt).x);
//				float y = PhysicsHelper.BOX_TO_WORLD*(leftAxlePrismaticJoint.getReactionForce(1/dt).y);
				
//				float reactionForce = (float) Math.sqrt(Math.pow((double)x, 2)+Math.pow((double)y, 2));				
//				Helper.println("raction force::::"+reactionForce);
//				if(reactionForce > 1500)
//				this.destroyJoints();
			}
		}

		
		   PhysicsHelper.accumulator+=dt;
//		   Helper.println( "dt:::"+PhysicsHelper.accumulator*1000f);
//		    while(PhysicsHelper.accumulator>PhysicsHelper.BOX_STEP){
		    	while(PhysicsHelper.accumulator>PhysicsHelper.BOX_STEP){
		      world.step(PhysicsHelper.BOX_STEP,PhysicsHelper.BOX_VELOCITY_ITERATIONS,PhysicsHelper.BOX_POSITION_ITERATIONS);
		      this.setLocation(PhysicsHelper.ConvertToWorld(body.getPosition().x), PhysicsHelper.ConvertToWorld(body.getPosition().y));
		      PhysicsHelper.accumulator -= PhysicsHelper.BOX_STEP;
//		      Helper.println("i'm here in update");
		
//				motorSpeed*=.99f;
//				if (motorSpeed>20) {
//					motorSpeed=20;
//				}
			
		     
			if (leftWheelRevoluteJoint!= null && rightWheelRevoluteJoint != null) {
				
//				rightWheelRevoluteJoint.setMaxMotorTorque(5);
//				leftWheelRevoluteJoint.setMaxMotorTorque(5);
				
				leftWheelRevoluteJoint.setMotorSpeed(motorSpeed);
				rightWheelRevoluteJoint.setMotorSpeed(motorSpeed);
				if(this.carType.compareTo(Constants.carTypeKrac)==0)
				frontWheelRevoluteJoint.setMotorSpeed(motorSpeed);
				
//				Helper.println("traversed distance : " + player.getPosition().x+"leftmotor speed"+ leftWheelRevoluteJoint.getMotorSpeed());
//				Helper.println("traversed distance : " + player.getPosition().x+"rightmotor speed"+ rightWheelRevoluteJoint.getMotorSpeed());
			}
		    
		    GlobalVars.ge.getRenderer().getBatch().setProjectionMatrix(cam.combined);
		    }
		    world.clearForces();
		   }
	
	public CarSpeedPro(float x, float y,float width, float height,float angle, String carType, World world, boolean b) {
//		super(x , y, width,height, 1);

		this.carType = carType;
		this.world = world;
		this.x = x;
		this.y = y;
		this.pos.set(this.x, this.y);
		this.width =width;
		this.height = height;

		
		if (this.carType.compareTo(Constants.carTypeKrac)==0) 
		{
		physicsBody = AssetConstants.PHY_KRAC_BODY;
		 bodyfixturepath = "kracbody";

		 imageCarBody= AssetConstants.PHY_IMG_KRAC_BODY ;
		 imageCarWheel=AssetConstants.PHY_IMG_KRAC_WHEEL;
		 imageaxle=AssetConstants.PHY_IMG_LEFTAXLE;
		}
		
		if (this.carType.compareTo(Constants.carTypeHuwwer)==0) 
		{
			physicsBody = AssetConstants.PHY_HUWWER_BODY;
		 bodyfixturepath = "huwwerbody";

		 imageCarBody= AssetConstants.PHY_IMG_HUWWER_BODY ;
		 imageCarWheel=AssetConstants.PHY_IMG_HUWWER_WHEEL;
		 imageaxle=AssetConstants.PHY_IMG_LEFTAXLE;
		}
		
		if (this.carType.compareTo(Constants.carTypeBenga)==0) 
		{
			physicsBody = AssetConstants.PHY_BENGA_BODY;
		 bodyfixturepath = "bengabody";

		 imageCarBody= AssetConstants.PHY_IMG_BENGA_BODY ;
		 imageCarWheel=AssetConstants.PHY_IMG_BENGA_WHEEL;
		 imageaxle=AssetConstants.PHY_IMG_LEFTAXLE;
		}
		
		if (this.carType.compareTo(Constants.carTypeMilitary)==0) 
		{
		 
		 physicsBody = AssetConstants.PHY_MILITARY_BODY;
		 bodyfixturepath = "militarybody";

		 imageCarBody= AssetConstants.PHY_IMG_MILITARY_BODY ;
		 imageCarWheel=AssetConstants.PHY_IMG_MILITARY_WHEEL;
		 imageaxle=AssetConstants.PHY_IMG_LEFTAXLE;
		}
		
		if (this.carType.compareTo(Constants.carTypePolice)==0) 
		{
			physicsBody = AssetConstants.PHY_POLICE_BODY;
		 bodyfixturepath = "policebody";

		 imageCarBody= AssetConstants.PHY_IMG_POLICE_BODY ;
		 imageCarWheel=AssetConstants.PHY_IMG_POLICE_WHEEL;
		 imageaxle=AssetConstants.PHY_IMG_LEFTAXLE;
		}
		
		if (this.carType.compareTo(Constants.carTypeSchoolbus)==0) 
		{
			physicsBody = AssetConstants.PHY_SCHOOLBUS_BODY;
		 bodyfixturepath = "schoolbusbody";

		 imageCarBody= AssetConstants.PHY_IMG_SCHOOLBUS_BODY ;
		 imageCarWheel=AssetConstants.PHY_IMG_SCHOOLBUS_WHEEL;
		 imageaxle=AssetConstants.PHY_IMG_LEFTAXLE;
		}
		
		if (this.carType.compareTo(Constants.carTypeSuper)==0) 
		{
			physicsBody = AssetConstants.PHY_SUPER_BODY;
		 bodyfixturepath = "superbody";

		 imageCarBody= AssetConstants.PHY_IMG_SUPER_BODY ;
		 imageCarWheel=AssetConstants.PHY_IMG_SUPER_WHEEL;
		 imageaxle=AssetConstants.PHY_IMG_LEFTAXLE;
		}



		//		createBallModel();
		createCarModel();
		rider = new Background(x, y, manWidth, manHeight, categoryBits);
	}

	public CarSpeedPro(float x, float y,float width, float height,float angle, World world, boolean b) {
//		super(x , y, width,height, 1);
		// TODO Auto-generated constructor stub
//		this.radius = radius / GameMenuInfo.ratio_w;
//		Helper.println("X: " + x);
//		Helper.println("After X: " +  this.x + imagePath);		
		this.world = world;
		this.x = x;
		this.y = y;
		this.pos.set(this.x, this.y);
		this.width =width;
		this.height = height;
		//		createBallModel();
		createCarModel();
		rider = new Background(x, y, manWidth, manHeight, categoryBits);
	}

//	float rad;
	
//	boolean first = true;

	
	@Override
	public void render() {
//		world.step(Gdx.graphics.getDeltaTime(), 4, 4);
		this.update(Gdx.graphics.getDeltaTime());
rightWheel.getLinearVelocity();
			
			cam.position.x = PhysicsHelper.ConvertToWorld(body.getPosition().x)+200;
			cam.position.y = PhysicsHelper.ConvertToWorld(body.getPosition().y) ;
			
//			if (Math.abs((cam.position.x - agerX)) < Gdx.graphics.getWidth()-100) {
//				// Helper.println("difference =="+Math.abs((rider.getY()-agerY)));
//				cam.position.x = agerX;
//			}
//			agerX = cam.position.x;
			
			if (Math.abs((cam.position.y - agerY)) < 100) {
				// Helper.println("difference =="+Math.abs((rider.getY()-agerY)));
				cam.position.y = agerY;
			}
			agerY = cam.position.y;
//			Helper.println("Cam y: " + agerY);
			cam.update();
//			Helper.println("Car Render position: " + (this.getLocation())+"and camera location"+cam.position+"and something even more"+player.getFixtureList().get(player.getFixtureList().size()-1).getBody().getPosition());
		if(first ){
//			Helper.println("Car X from phy to game world: " + player.getPosition().x);
//			Helper.println("Car Render position: " + (this.getLocation())+"and camera location"+cam.position+"and something even more"+player.getFixtureList().get(player.getFixtureList().size()-1).getBody().getPosition());
//		Helper.println("delta time :::"+1/Gdx.graphics.getDeltaTime());
			
	
			first = false;
		} 
//			Helper.println("image rendering at"+ image);
		
//		if(Math.abs(vel.x) > MAX_VELOCITY) {			
//			vel.x = Math.signum(vel.x) * MAX_VELOCITY;
//			player.setLinearVelocity(vel.x, vel.y);
//		}
 
//		 calculate stilltime & damp
//		if(!Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D)) {			
//			stillTime += Gdx.graphics.getDeltaTime();
//			player.setLinearVelocity(vel.x * 0.9f, vel.y);
//		}
//		else { 
//			stillTime = 0;
//		}			
// 
//			if(!Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D) && stillTime > 0.2) {
//				playerPhysicsFixture.setFriction(100f);
//				player.getFixtureList().get(0).setFriction(100f);
//				playerSensorFixture.setFriction(100f);
//			}
//			else {
//				playerPhysicsFixture.setFriction(0.2f);
//				playerSensorFixture.setFriction(0.2f);
//			}
// 
// 
		// apply left impulse, but only if max velocity is not reached yet
		if(Gdx.input.isKeyPressed(Keys.A) ) {
			leftAxle.applyForce(0 , -0.5f, leftAxle.getWorldCenter().x, leftAxle.getWorldCenter().y);
//			leftAxleContainer.applyLinearImpulse(0f,  -0.05f, leftAxleContainer.getPosition().x, leftAxleContainer.getPosition().y);
//			leftWheel.applyAngularImpulse(10f);
//			rightWheel.applyAngularImpulse(10f);
			Helper.println("player angle in degree::"+(MathUtils.radiansToDegrees*body.getAngle()));
//			player.setTransform(player.getPosition(), 0f);
//			player.setTransform(player.getPosition().x, player.getPosition().y, player.getAngle()-(MathUtils.degreesToRadians*10));
//			Helper.println("key pressed: "+Keys.A+"player position physics world"+player.getPosition()+"and player position in game world::"+this.getLocation());
		}
 
		// apply right impulse, but only if max velocity is not reached yet
		if(Gdx.input.isKeyPressed(Keys.D)) {
			rightAxle.applyLinearImpulse(0,  -0.5f, rightAxle.getWorldCenter().x, rightAxle.getWorldCenter().y);
//			leftWheel.applyAngularImpulse(-10f);
//			rightWheel.applyAngularImpulse(-10f);
//			player.setTransform(player.getPosition().x, player.getPosition().y, player.getAngle()-.1f);
			Helper.println("player angle in degree::"+(MathUtils.radiansToDegrees*body.getAngle()));
//			player.setTransform(player.getPosition(), 0f);
//			player.setTransform(player.getPosition().x, player.getPosition().y, player.getAngle()+(MathUtils.degreesToRadians*10));
//			Helper.println("key pressed: "+Keys.D+"player position physics world"+player.getPosition()+"and player position in game world::"+this.getLocation());
		}
		
		if(Gdx.input.isKeyPressed(Keys.S)) {
			body.applyLinearImpulse(0, -.2f, body.getPosition().x, body.getPosition().y);
//			rightWheel.applyLinearImpulse(0f, -05f, rightWheel.getPosition().x, rightWheel.getPosition().x);
//			Helper.println("key pressed: "+Keys.S+"player position physics world"+player.getPosition()+"and player position in game world::"+this.getLocation());
		}
 
		// apply right impulse, but only if max velocity is not reached yet
		if(Gdx.input.isKeyPressed(Keys.W)) {
//			player.applyLinearImpulse(0,  10f, player.getPosition().x, player.getPosition().y);
			
			body.setTransform(body.getPosition().x, body.getPosition().y+0.5f, body.getAngle());
//			rightWheel.applyLinearImpulse(0f, 05f, rightWheel.getPosition().x, rightWheel.getPosition().x);
//			leftWheel.applyLinearImpulse(0f, -05f, leftWheel.getPosition().x, leftWheel.getPosition().x);
//			Helper.println("key pressed: "+Keys.W+"player position physics world"+player.getPosition()+"and player position in game world::"+this.getLocation()+"region btm:"+this.getBottom()+"region top:"+this.getTop()+"region width:"+this.getWidth());
		}
		
//	      leftWheelRevoluteJoint.setLimits(-10, 100);
	  	if (Gdx.input.isKeyPressed(Keys.Z)) {
//	  		frontWheelRevoluteJoint.setMaxMotorTorque(00);
	  		if(motorSpeed > -1)
	  			motorSpeed-=.1f;
//			Helper.println("Speeding up: LEFT" );
		}
		if (Gdx.input.isKeyPressed(Keys.X)) {
			
//			if(motorSpeed > 30 && motorSpeed < 30)
//				motorSpeed+=0.1f;
//			else
//			player.applyForce(new Vector2((float)(motorSpeed * Math.cos(player.getAngle() * MathUtils.degreesToRadians)), 
//					(float)(motorSpeed * Math.sin(player.getAngle() * MathUtils.degreesToRadians))), player.getWorldCenter());
//			motorSpeed = leftWheelRevoluteJoint.getMotorSpeed();
//			Helper.println("MotorSpeed "  + motorSpeed);
//			frontWheelRevoluteJoint.setMaxMotorTorque(10);
			motorSpeed+=.1f;
			
			
			
			Helper.println("Speeding up: Right : " + motorSpeed );
//			player.applyForce(new Vector2(1000, 0), player.getWorldCenter());
		}
		if (Gdx.input.isKeyPressed(Keys.C)) {
//			motorSpeed+=0.1;
			Helper.println("Destroying" );
			
//			((BikeLevel) GlobalVars.ge.getCurrentStage()).getWorld().destroyJoint(leftAxlePrismaticJoint);
//			
//			((BikeLevel) GlobalVars.ge.getCurrentStage()).getWorld().destroyJoint(leftAxlePrismaticJoint);
//
			this.destroyJoints();

			
			
		}
		if (Gdx.input.isKeyPressed(Keys.R)) {
//			motorSpeed+=0.5;
//			Helper.println("Speeding up: Right" );
			((BikeLevel) GlobalVars.ge.getCurrentStage()).reload();
			
		}

	}








	public void destroyJoints() {
	// TODO Auto-generated method stub
		if(!(leftWeldJoint == null))
		{
//			((BikeLevel) GlobalVars.ge.getCurrentStage()).getWorld().destroyJoint(leftWeldJoint);
		
		leftWeldJoint = null;
		}
		
		if(!(rightWeldJoint == null))
		{
//			((BikeLevel) GlobalVars.ge.getCurrentStage()).getWorld().destroyJoint(rightWeldJoint);
		
		rightWeldJoint = null;
		}
		
		if(!(leftAxlePrismaticJoint == null))
		{
//			((BikeLevel) GlobalVars.ge.getCurrentStage()).getWorld().destroyJoint(leftAxlePrismaticJoint);
		
		leftAxlePrismaticJoint = null;
		}
		
		if(!(rightAxlePrismaticJoint == null))
		{
//		((BikeLevel) GlobalVars.ge.getCurrentStage()).getWorld().destroyJoint(rightAxlePrismaticJoint);
		rightAxlePrismaticJoint = null;
		}
		
		if(!(leftWheelRevoluteJoint == null))
		{
			((BikeLevel) GlobalVars.ge.getCurrentStage()).getWorld().destroyJoint(leftWheelRevoluteJoint);
			leftWheelRevoluteJoint = null;
		}
		
		if(!(rightWheelRevoluteJoint == null))
		{
			((BikeLevel) GlobalVars.ge.getCurrentStage()).getWorld().destroyJoint(rightWheelRevoluteJoint);
			rightWheelRevoluteJoint = null;
		}
}

	
	
	/**
	 * Add all the texture needed by this element in this function
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
//		assetPack.addTexture(imagePath);
//		assetPack.addTexture(AssetConstants.IMG_BALL_BASKET);
//		assetPack.addTexture(AssetConstants.IMG_BASKET_BALL);
//		assetPack.addTexture(AssetConstants.IMG_RUBBER_BALL);
//		assetPack.addTexture(AssetConstants.IMG_BOWL_BALL);
//		assetPack.addTexture(AssetConstants.IMG_PLAYER1_NORMAL2);
//		assetPack.addTexture(AssetConstants.IMG_CL_BKG);
		assetPack.addTexture(AssetConstants.IMG_BIKE_WHEEL);
		assetPack.addTexture(AssetConstants.IMG_AXLE);
		assetPack.addTexture(AssetConstants.IMG_RIDER);
		assetPack.addTexture(AssetConstants.IMG_BIKE_BODY);
	}

	@Override
	public void init() {

		agerY = cam.position.y;
//		carPhysicsBody.init();
		

	}
	
	
	
	
	public boolean shallAccelarate = false;
	public float accelarateVal = 0.1f;
	@Override
	public void step(long stepTime) {
//		if((PhysicsHelper.ConvertToWorld(leftWheel.getPosition().y)<25)||(PhysicsHelper.ConvertToWorld(rightWheel.getPosition().y)<25))
//		{
//			((BikeLevel) GlobalVars.ge.getCurrentStage()).setInGround(true);
////			Helper.println("bike wheels are  in ground............"+PhysicsHelper.ConvertToWorld(rightWheel.getPosition().y));
//		}
//		else
//			{
//			((BikeLevel) GlobalVars.ge.getCurrentStage()).setInGround(false);
////			Helper.println("bike wheels are not  in ground............"+leftWheel.getPosition().sub(((BikeLevel) GlobalVars.ge.getCurrentStage()).h0.getBody().getPosition()).len());
//			}
		
		if(rider.getY()<70)
		{
//			Helper.println("rider y :: "+rider.getY());
		}
		
		
		if(shallAccelarate)
			motorSpeed += accelarateVal;
		
		checkInput();
	}

	private void checkInput() {
		float threshold = 1;
		if(Gdx.app.getType() == ApplicationType.Android)
		{
			if(Math.abs(Gdx.input.getAccelerometerX() ) <= threshold)
				return;
			if(Gdx.input.getAccelerometerX() < 0) // negative
			{
				leftAxle.applyLinearImpulse(0f,  -0.05f, leftAxle.getPosition().x, leftAxle.getPosition().y);
			}
			else if(Gdx.input.getAccelerometerX() > 0) // positive
			{
				rightAxle.applyLinearImpulse(0f,  -0.05f, rightAxle.getPosition().x, rightAxle.getPosition().y);
			}
		}
	}

	public Body getBody() {
		return body;
	}



	public boolean isActive() {
		return active;
	}
	
	Point renderPoint = new Point();

	private Body frontAxle;

	

	
	
	public Point getRenderLocation() {
		this.renderPoint.setLocation(x * GameMenuInfo.ratio_w,
				y * GameMenuInfo.ratio_h );
		return renderPoint;
	}
	
	public void changeMotorSpeed(float speed)
	{
		motorSpeed+=speed;
	}
	
	private void createCarModel() {
		// TODO Auto-generated method stub
		
	

		// ************************ THE CAR ************************ //
		
		this.createCarBody();
//		this.carBodyFromJson();
	// ************************ THE Rider ************************ //
//		this.createRiderBody();
		
		this.createWheel();
		// ************************ THE WHEELS ************************ //;
		// ************************ LEFT AXLE CONTAINER ************************ //
//		this.createAxleContainer();
//		// ************************ THE AXLES ************************ //

		
		this.createAxle();
		
		if(this.carType.compareTo(Constants.carTypeKrac)==0)
		{
		this.setCarWidth(PhysicsHelper
				.ConvertToBox(256 * GameMenuInfo.ratio_w)
				* GameMenuInfo.carSizeCoeff);	
			
		}
		else if(this.carType.compareTo(Constants.carTypeBenga)==0)
		{
		this.setCarWidth(PhysicsHelper
				.ConvertToBox(256 * GameMenuInfo.ratio_w)
				* GameMenuInfo.carSizeCoeff);
		}

//		riderWeldJoint = this.createWeldJoint(player, riderBody, riderBody.getWorldCenter());
//		leftWeldJoint = this.createWeldJoint(player, leftAxleContainer, leftAxleContainer.getWorldCenter());
//		rightWeldJoint= this.createWeldJoint(player, rightAxleContainer, rightAxleContainer.getWorldCenter());
		leftAxlePrismaticJoint = this.createPrismaticJoint(body,leftAxle,leftAxle.getWorldCenter(), new Vector2((float)(Math.cos(MathUtils.degreesToRadians*(90-leftAxleAngle*MathUtils.radiansToDegrees))),(float)(Math.sin(MathUtils.degreesToRadians*(90-leftAxleAngle*MathUtils.radiansToDegrees)))));
		rightAxlePrismaticJoint = this.createPrismaticJoint(body,rightAxle,rightAxle.getWorldCenter(), new Vector2((float)(-Math.cos(MathUtils.degreesToRadians*(90-rightAxleAngle*MathUtils.radiansToDegrees))),(float)(Math.sin(MathUtils.degreesToRadians*(90-rightAxleAngle*MathUtils.radiansToDegrees)))));
		if(this.carType.compareTo(Constants.carTypeKrac)==0)
		{
		frontAxlePrismaticJoint = this.createPrismaticJoint(body,frontAxle,frontAxle.getWorldCenter(), new Vector2((float)(-Math.cos(MathUtils.degreesToRadians*(90-frontAxleAngle*MathUtils.radiansToDegrees))),(float)(Math.sin(MathUtils.degreesToRadians*(90-frontAxleAngle*MathUtils.radiansToDegrees)))));
		frontWheelRevoluteJoint = this.createRevoluteJoint(frontWheel,frontAxle,frontWheel.getWorldCenter());
		}
		leftWheelRevoluteJoint = this.createRevoluteJoint(leftWheel,leftAxle,leftWheel.getWorldCenter());
		rightWheelRevoluteJoint = this.createRevoluteJoint(rightWheel,rightAxle,rightWheel.getWorldCenter());
		
//		Helper.println("joint null or not:::"+ rightWheelRevoluteJoint);
		
//		setPrisJointProperty(frontAxlePrismaticJoint, 0f, .05f);
		setDensities();
		

		
		
	}
	
	private void setPrisJointProperty(PrismaticJoint joint, float lowerLimit, float uperLimit)
	{
		joint.setLimits(lowerLimit, uperLimit);
	}


	private void setDensity(Body body, float density)
	{
		body.getFixtureList().get(0).setDensity(density);
	}
	private void setDensities() {
		setDensity(body, 05);
		setDensity(leftAxle, 3);
		setDensity(leftWheel, 3);
		setDensity(rightAxle, 3);
		setDensity(rightWheel, 3);
//		setDensity(frontAxle, 1);
//		setDensity(frontWheel, 1);
	}

	private PrismaticJoint createPrismaticJoint(Body bodyA, Body bodyB, Vector2 anchor, Vector2 axis) {
		// TODO Auto-generated method stub
//		PrismaticJointDef 
		PrismaticJointDef prismaticJointDef=new PrismaticJointDef();
		prismaticJointDef.lowerTranslation=0;
		prismaticJointDef.upperTranslation=axleContainerDepth;
		prismaticJointDef.enableLimit=true;
		prismaticJointDef.enableMotor=true;
//		leftAxlePrismaticJointDef.initialize(player,leftAxle,leftAxle.getWorldCenter(), new Vector2((float)(Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))),(float)(Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees)))));
		prismaticJointDef.initialize(bodyA,bodyB,anchor,axis);
	
		PrismaticJoint prismaticJoint =(PrismaticJoint) world.createJoint(prismaticJointDef);// as PrismaticJoint;
		prismaticJoint.setMaxMotorForce(10f);                         
//		prismaticJoint.setMotorSpeed(100f);
//		prismaticJoint.setLimits(-.1f, .1f);
		return prismaticJoint;
		
		
//		jointName = prismaticJoint;
//		jointName.setLimits(-0.025f, 0.025f);
		
	}
		
	private RevoluteJoint createRevoluteJoint(Body bodyA, Body bodyB, Vector2 anchorA) {
		// TODO Auto-generated method stub
		RevoluteJointDef revoluteJointDef=new RevoluteJointDef();
		revoluteJointDef.initialize(bodyA, bodyB, anchorA);
//		revoluteJointDef.localAnchorB.set(anchorB);
//		revoluteJointDef.upperAngle = (float) (Math.PI/2);
//		revoluteJointDef.upperAngle = (float) (-Math.PI/2);
		revoluteJointDef.enableMotor=true;
//		revoluteJointDef.enableLimit = true;
//		RevoluteJoint revoluteJoint=(RevoluteJoint) world.createJoint(revoluteJointDef);// as b2RevoluteJoint;
//		revoluteJoint.setMaxMotorTorque(10f);
//		jointName = revoluteJoint;
		RevoluteJoint jointName=(RevoluteJoint) world.createJoint(revoluteJointDef);// as b2RevoluteJoint;
		jointName.setMaxMotorTorque(10f);
//		jointName.setMotorSpeed(0);
		return jointName;
		
	}

	private WeldJoint createWeldJoint(Body bodyA, Body bodyB, Vector2 anchor) {
		// TODO Auto-generated method stub
		WeldJointDef weldJointDef = new WeldJointDef();
		weldJointDef.initialize(bodyA, bodyB, anchor);
		WeldJoint weldJoint = (WeldJoint) world.createJoint(weldJointDef);
		return weldJoint;
	}
		//////////////////////////
//		WeldJointDef weldJointDefRight = new WeldJointDef();
//		weldJointDefRight.initialize(player, rightAxleContainer, rightAxleContainer.getWorldCenter());
//		rightWeldJoint = world.createJoint(weldJointDefRight);
//		
//		////
//		WeldJointDef weldJointDefRider = new WeldJointDef();
//		weldJointDefRider.initialize(player, riderBody, riderBody.getWorldCenter());
//		riderWeldJoint = world.createJoint(weldJointDefRider);
//		
//	}

	private void createAxle() {
		
		PhysicsBody2 pb =new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(leftWheel.getWorldCenter().x-axleWidth/2*Math.cos(/*Math.PI/2-*/leftAxleAngle))),
				PhysicsHelper.ConvertToWorld((float)(leftWheel.getWorldCenter().y+axleWidth/2*Math.sin(/*Math.PI/2-*/leftAxleAngle))),
				PhysicsHelper.ConvertToWorld(axleWidth),
				0f,
				AssetConstants.PHY_AXLE ,
				imageaxle,
				"axle", 
				world,
				5f,
				3f,
				0f,
				((short) -1),
				((short) 4),
				((short) (11)),
				"leftaxle",5,
				BodyType.DynamicBody);
		
		pb.getBody().setTransform(pb.getBody().getPosition(), pb.getBody().getAngle()-leftAxleAngle);
		
		GlobalVars.ge.getCurrentStage().addElement(pb);
		leftAxle = pb.getBody();
		leftAxle.setUserData("leftAxle");
		
//		leftAxle.setFixedRotation(false);
		PhysicsBody2 pb2 =new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(rightWheel.getWorldCenter().x-axleWidth/2*Math.cos(rightAxleAngle))),
				PhysicsHelper.ConvertToWorld((float)(rightWheel.getWorldCenter().y+axleWidth/2*Math.sin(rightAxleAngle))),
				PhysicsHelper.ConvertToWorld(axleWidth),
				0f,
				AssetConstants.PHY_AXLE ,
				imageaxle,
				"axle", 
				world,
				5f,
				3f,
				0f,
				((short) -1),
				((short) 5),
				((short) (11)),
				"rightaxle",6,
				BodyType.DynamicBody);
		
		pb2.getBody().setTransform(pb2.getBody().getPosition(), pb2.getBody().getAngle()+rightAxleAngle);
		
		GlobalVars.ge.getCurrentStage().addElement(pb2);
		rightAxle = pb2.getBody();
		rightAxle.setUserData("rightAxle");
		
		 if (this.carType.compareTo(Constants.carTypeKrac)==0)  {
			PhysicsBody2 pb3 = new PhysicsBody2(
					PhysicsHelper.ConvertToWorld((float) (frontWheel
							.getWorldCenter().x - axleWidth / 2
							* Math.cos(frontAxleAngle))),
					PhysicsHelper.ConvertToWorld((float) (frontWheel
							.getWorldCenter().y + axleWidth / 2
							* Math.sin(frontAxleAngle))),
					PhysicsHelper.ConvertToWorld(axleWidth), 0f,
					AssetConstants.PHY_AXLE, imageaxle, "axle", world, 5f, 3f,
					0f, ((short) -1), ((short) 15), ((short) (11)),
					"frontaxle", 15, BodyType.DynamicBody);
			pb3.getBody().setTransform(pb3.getBody().getPosition(),
					pb3.getBody().getAngle() + frontAxleAngle);
			GlobalVars.ge.getCurrentStage().addElement(pb3);
			frontAxle = pb3.getBody();
			frontAxle.setUserData("frontAxle");
			//		rightAxle.setUserData("rightAxle");
			//		rightAxle.SetPosition(new Vector2(carPosX/worldScale+axleContainerDistance/worldScale+axleContainerHeight/worldScale*Math.cos((90-axleAngle)*degreesToRadians),carPosY/worldScale+axleContainerDepth/worldScale+axleContainerHeight/worldScale*Math.sin((90-axleAngle)*degreesToRadians)));
			//
		}

	}

/*	private void createAxleContainer() {
		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(player.getPosition().x))+84f,
				PhysicsHelper.ConvertToWorld((float)(player.getPosition().y)),
				PhysicsHelper.ConvertToWorld(axleContainerWidth),
				0f,
				AssetConstants.PHY_AXLE_CONTAINER ,
				AssetConstants.PHY_IMG_LEFTAXLECONTAINER,
				"leftaxlecontainer", 
				world,
				3f,
				3f,
				0.3f,
				((short) -1),
				((short) 2),
				((short) (11)),
				"leftaxlecontainer",3,
				BodyType.DynamicBody);
		
//		pb.getBody().getFixtureList().get(0).setDensity(50);
		pb.getBody().setTransform(pb.getBody().getPosition(), pb.getBody().getAngle()-leftAxleContainerAngle);
//		pb.getBody().setTransform((float) (pb.getBody().getPosition().x-Math.tanh(axleAngle)*axleContainerHeight),pb.getBody().getPosition().y-axleContainerHeight, pb.getBody().getAngle());
		
		
		GlobalVars.ge.getCurrentStage().addElement(pb);
		leftAxleContainer = pb.getBody();
		leftAxleContainer.setUserData("leftAxleContainer");
		
		// ************************ RIGHT AXLE CONTAINER ************************ //
		
		PhysicsBody2 pb2 = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(player.getWorldCenter().x+axleContainerDistance))+84,
				PhysicsHelper.ConvertToWorld((float)(player.getWorldCenter().y)),
				PhysicsHelper.ConvertToWorld(axleContainerWidth),
				0f,
				AssetConstants.PHY_AXLE_CONTAINER ,
				AssetConstants.PHY_IMG_LEFTAXLECONTAINER,
				"leftaxlecontainer", 
				world,
				3f,
				3f,
				0.3f,
				((short) -1),
				((short) 3),
				((short) (11)),
				"rightaxlecontainer",4,
				BodyType.DynamicBody);

//		pb2.getBody().getFixtureList().get(0).setDensity(50);
		pb2.getBody().setTransform(pb2.getBody().getPosition(), pb2.getBody().getAngle()+rightAxleContainerAngle);
//		pb2.getBody().setTransform((float) (pb2.getBody().getPosition().x+Math.tanh(axleAngle)*axleContainerHeight),pb2.getBody().getPosition().y-axleContainerHeight, pb2.getBody().getAngle());
		
		GlobalVars.ge.getCurrentStage().addElement(pb2);
		rightAxleContainer = pb2.getBody();
		rightAxleContainer.setUserData("rightAxleContainer");
	}*/

	private void createWheel() {
		if (this.carType.compareTo(Constants.carTypeKrac)==0) 
		{
		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().x- carWidth/2-wheelRadius*2)),
				PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().y-carWidth/2)),
				PhysicsHelper.ConvertToWorld(wheelRadius*2),
				0f,
				AssetConstants.PHY_KRAC_WHEEL ,
				imageCarWheel,
				"kracwheel", 
				world,
				1f,
				15f,
				0.2f,
				((short) -1),
				((short) 6),
				((short) (11)),
				"leftWheel",7,
				BodyType.DynamicBody);
//		new Point().getPointAtDistance(srcPoint, destinationPoint, retPoint, distance)
//		pb.getBody().setTransform(pb.getBody().getPosition(), pb.getBody().getAngle()-axleAngle);
		
		GlobalVars.ge.getCurrentStage().addElement(pb);
		leftWheel = pb.getBody();
		leftWheel.setUserData("leftWheel");
		
		PhysicsBody2 pb2 = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().x-carWidth/2+wheelRadius)),
				PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().y-carWidth/2)),
				PhysicsHelper.ConvertToWorld(wheelRadius*2),
				0f,
				AssetConstants.PHY_KRAC_WHEEL ,
				imageCarWheel,
				"kracwheel", 
				world,
				1f,
				15f,
				0.2f,
				((short) -1),
				((short) 7),
				((short) (11)),
				"rightWheel",8,
				BodyType.DynamicBody);
		
		GlobalVars.ge.getCurrentStage().addElement(pb2);
		rightWheel = pb2.getBody();
		rightWheel.setUserData("rightWheel");
		

			PhysicsBody2 pb3 = new PhysicsBody2(
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().x + carWidth / 2)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y - carWidth / 2)),
					PhysicsHelper.ConvertToWorld(frontWheelRadius * 2), 
					0f,
					AssetConstants.PHY_KRAC_WHEEL,
					imageCarWheel, 
					"kracwheel",
					world,
					1f,
					15f,
					0.2f,
					((short) -1),
					((short) 14),
					((short) (11)), "frontWheel", 9, BodyType.DynamicBody);
			GlobalVars.ge.getCurrentStage().addElement(pb3);
			frontWheel = pb3.getBody();
			frontWheel.setUserData("frontWheel");
		}
		
		if (this.carType.compareTo(Constants.carTypeBenga)==0) 
		{
		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().x- carWidth/2-wheelRadius)),
				PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().y-carWidth/2)),
				PhysicsHelper.ConvertToWorld(wheelRadius*2),
				0f,
				AssetConstants.PHY_KRAC_WHEEL ,
				imageCarWheel,
				"kracwheel", 
				world,
				1f,
				15f,
				0.2f,
				((short) -1),
				((short) 6),
				((short) (11)),
				"leftWheel",7,
				BodyType.DynamicBody);
//		new Point().getPointAtDistance(srcPoint, destinationPoint, retPoint, distance)
//		pb.getBody().setTransform(pb.getBody().getPosition(), pb.getBody().getAngle()-axleAngle);
		
		GlobalVars.ge.getCurrentStage().addElement(pb);
		leftWheel = pb.getBody();
		leftWheel.setUserData("leftWheel");
		
		PhysicsBody2 pb2 = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().x+wheelRadius)),
				PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().y-carWidth/2)),
				PhysicsHelper.ConvertToWorld(wheelRadius*2),
				0f,
				AssetConstants.PHY_KRAC_WHEEL ,
				imageCarWheel,
				"kracwheel", 
				world,
				1f,
				15f,
				0.2f,
				((short) -1),
				((short) 7),
				((short) (11)),
				"rightWheel",8,
				BodyType.DynamicBody);
		
		GlobalVars.ge.getCurrentStage().addElement(pb2);
		rightWheel = pb2.getBody();
		rightWheel.setUserData("rightWheel");
		}
		
		if (this.carType.compareTo(Constants.carTypeHuwwer)==0) 
		{
		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().x- carWidth+wheelRadius*1.5f)),
				PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().y-carWidth/2+wheelRadius/2)),
				PhysicsHelper.ConvertToWorld(wheelRadius*2),
				0f,
				AssetConstants.PHY_HUWWER_WHEEL ,
				imageCarWheel,
				"huwwerwheel", 
				world,
				1f,
				15f,
				0.2f,
				((short) -1),
				((short) 6),
				((short) (11)),
				"leftWheel",7,
				BodyType.DynamicBody);
//		new Point().getPointAtDistance(srcPoint, destinationPoint, retPoint, distance)
//		pb.getBody().setTransform(pb.getBody().getPosition(), pb.getBody().getAngle()-axleAngle);
		
		GlobalVars.ge.getCurrentStage().addElement(pb);
		leftWheel = pb.getBody();
		leftWheel.setUserData("leftWheel");
		
		PhysicsBody2 pb2 = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().x+carWidth/2-wheelRadius*.25f)),
				PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().y-carWidth/2+wheelRadius/2)),
				PhysicsHelper.ConvertToWorld(wheelRadius*2),
				0f,
				AssetConstants.PHY_HUWWER_WHEEL  ,
				imageCarWheel,
				"huwwerwheel", 
				world,
				1f,
				15f,
				0.2f,
				((short) -1),
				((short) 7),
				((short) (11)),
				"rightWheel",8,
				BodyType.DynamicBody);
		
		GlobalVars.ge.getCurrentStage().addElement(pb2);
		rightWheel = pb2.getBody();
		rightWheel.setUserData("rightWheel");
		}
		
		if (this.carType.compareTo(Constants.carTypeMilitary)==0) 
		{
		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().x- carWidth/2-wheelRadius)),
				PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().y-carWidth/2)),
				PhysicsHelper.ConvertToWorld(wheelRadius*2),
				0f,
				AssetConstants.PHY_MILITARY_WHEEL ,
				imageCarWheel,
				"militarywheel", 
				world,
				1f,
				15f,
				0.2f,
				((short) -1),
				((short) 6),
				((short) (11)),
				"leftWheel",7,
				BodyType.DynamicBody);
//		new Point().getPointAtDistance(srcPoint, destinationPoint, retPoint, distance)
//		pb.getBody().setTransform(pb.getBody().getPosition(), pb.getBody().getAngle()-axleAngle);
		
		GlobalVars.ge.getCurrentStage().addElement(pb);
		leftWheel = pb.getBody();
		leftWheel.setUserData("leftWheel");
		
		PhysicsBody2 pb2 = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().x+carWidth/2)),
				PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().y-carWidth/2)),
				PhysicsHelper.ConvertToWorld(wheelRadius*2),
				0f,
				AssetConstants.PHY_MILITARY_WHEEL  ,
				imageCarWheel,
				"militarywheel", 
				world,
				1f,
				15f,
				0.2f,
				((short) -1),
				((short) 7),
				((short) (11)),
				"rightWheel",8,
				BodyType.DynamicBody);
		
		GlobalVars.ge.getCurrentStage().addElement(pb2);
		rightWheel = pb2.getBody();
		rightWheel.setUserData("rightWheel");
		}

		if (this.carType.compareTo(Constants.carTypePolice)==0) 
		{
		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().x- carWidth/2-wheelRadius/2)),
				PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().y-carWidth/2+wheelRadius)),
				PhysicsHelper.ConvertToWorld(wheelRadius*2),
				0f,
				AssetConstants.PHY_POLICE_WHEEL ,
				imageCarWheel,
				"policewheel", 
				world,
				1f,
				15f,
				0.2f,
				((short) -1),
				((short) 6),
				((short) (11)),
				"leftWheel",7,
				BodyType.DynamicBody);
//		new Point().getPointAtDistance(srcPoint, destinationPoint, retPoint, distance)
//		pb.getBody().setTransform(pb.getBody().getPosition(), pb.getBody().getAngle()-axleAngle);
		
		GlobalVars.ge.getCurrentStage().addElement(pb);
		leftWheel = pb.getBody();
		leftWheel.setUserData("leftWheel");
		
		PhysicsBody2 pb2 = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().x+carWidth/2)),
				PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().y-carWidth/2+wheelRadius)),
				PhysicsHelper.ConvertToWorld(wheelRadius*2),
				0f,
				AssetConstants.PHY_POLICE_WHEEL  ,
				imageCarWheel,
				"policewheel", 
				world,
				1f,
				15f,
				0.2f,
				((short) -1),
				((short) 7),
				((short) (11)),
				"rightWheel",8,
				BodyType.DynamicBody);
		
		GlobalVars.ge.getCurrentStage().addElement(pb2);
		rightWheel = pb2.getBody();
		rightWheel.setUserData("rightWheel");
		}
		
		if (this.carType.compareTo(Constants.carTypeSchoolbus)==0) 
		{
		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().x- carWidth+wheelRadius/1.5f)),
				PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().y-carWidth/2)),
				PhysicsHelper.ConvertToWorld(wheelRadius*2),
				0f,
				AssetConstants.PHY_SHOOLBUS_WHEEL ,
				imageCarWheel,
				"schoolbuswheel", 
				world,
				1f,
				15f,
				0.2f,
				((short) -1),
				((short) 6),
				((short) (11)),
				"leftWheel",7,
				BodyType.DynamicBody);
//		new Point().getPointAtDistance(srcPoint, destinationPoint, retPoint, distance)
//		pb.getBody().setTransform(pb.getBody().getPosition(), pb.getBody().getAngle()-axleAngle);
		
		GlobalVars.ge.getCurrentStage().addElement(pb);
		leftWheel = pb.getBody();
		leftWheel.setUserData("leftWheel");
		
		PhysicsBody2 pb2 = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().x+carWidth/2+wheelRadius/10)),
				PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().y-carWidth/2)),
				PhysicsHelper.ConvertToWorld(wheelRadius*2),
				0f,
				AssetConstants.PHY_SHOOLBUS_WHEEL  ,
				imageCarWheel,
				"schoolbuswheel", 
				world,
				1f,
				15f,
				0.2f,
				((short) -1),
				((short) 7),
				((short) (11)),
				"rightWheel",8,
				BodyType.DynamicBody);
		
		GlobalVars.ge.getCurrentStage().addElement(pb2);
		rightWheel = pb2.getBody();
		rightWheel.setUserData("rightWheel");
		}
		
		if (this.carType.compareTo(Constants.carTypeSuper)==0) 
		{
		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().x- carWidth/2-wheelRadius)),
				PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().y-carWidth/2+wheelRadius/2)),
				PhysicsHelper.ConvertToWorld(wheelRadius*2),
				0f,
				AssetConstants.PHY_SUPER_WHEEL ,
				imageCarWheel,
				"superwheel", 
				world,
				1f,
				15f,
				0.2f,
				((short) -1),
				((short) 6),
				((short) (11)),
				"leftWheel",7,
				BodyType.DynamicBody);
//		new Point().getPointAtDistance(srcPoint, destinationPoint, retPoint, distance)
//		pb.getBody().setTransform(pb.getBody().getPosition(), pb.getBody().getAngle()-axleAngle);
		
		GlobalVars.ge.getCurrentStage().addElement(pb);
		leftWheel = pb.getBody();
		leftWheel.setUserData("leftWheel");
		
		PhysicsBody2 pb2 = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().x+carWidth/2)),
				PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter().y-carWidth/2+wheelRadius/2)),
				PhysicsHelper.ConvertToWorld(wheelRadius*2),
				0f,
				AssetConstants.PHY_SUPER_WHEEL  ,
				imageCarWheel,
				"superwheel", 
				world,
				1f,
				15f,
				0.2f,
				((short) -1),
				((short) 7),
				((short) (11)),
				"rightWheel",8,
				BodyType.DynamicBody);
		
		GlobalVars.ge.getCurrentStage().addElement(pb2);
		rightWheel = pb2.getBody();
		rightWheel.setUserData("rightWheel");
		}
		
		
	}

	private void createRiderBody() {
		
		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld(body.getWorldCenter().x),
				PhysicsHelper.ConvertToWorld(body.getWorldCenter().y),
				PhysicsHelper.ConvertToWorld(manWidth),
				0f,
				AssetConstants.PHY_BIKEMAN ,
				AssetConstants.PHY_IMG_BIKEMAN,
				"bikeman", 
				world,
				((short) -1),
				((short) 13),
				((short) (1|11)),
				"bikeman",3,
				BodyType.DynamicBody);
		
		GlobalVars.ge.getCurrentStage().addElement(pb);
		riderBody = pb.getBody();
		riderBody.setUserData("riderBody");
	}

	private void createCarBody() {
		PhysicsBody2 pb = new PhysicsBody2(this.x,
				this.y,
				PhysicsHelper.ConvertToWorld(carWidth*2),
				01f,
				physicsBody ,
				imageCarBody,
				bodyfixturepath, 
				world, 
				1f,
				3f,
				0.3f,
				((short) -1),
				((short) 1),
				((short) 11),
				"kracbody",
				2,
				BodyType.DynamicBody);
//		pb.getBody().getFixtureList().get(pb.getBody().getFixtureList().size()).setDensity(10);		
		GlobalVars.ge.getCurrentStage().addElement(pb);
		body = pb.getBody();
		body.setUserData("kracbody");
		
	}

	public float getCarWidth() {
		return carWidth;
	}

	public void setCarWidth(float carWidth) {
		this.carWidth = carWidth;
	}

	
	

	
	
}

