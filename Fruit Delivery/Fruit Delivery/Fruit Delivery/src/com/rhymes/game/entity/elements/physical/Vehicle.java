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

public class Vehicle extends ElementBase implements InteractionTouchCallbacks{

	OrthographicCamera cam = new OrthographicCamera(480*GameMenuInfo.ratio_w,320*GameMenuInfo.ratio_h);
//    float aspectRatio = ((float)Gdx.graphics.getWidth()*GameMenuInfo.ratio_w / (float)Gdx.graphics.getHeight()*GameMenuInfo.ratio_h);
//    OrthographicCamera cam= new OrthographicCamera(aspectRatio, .1f);

	final static float MAX_VELOCITY = 7f;	
	String imagePath;
	private World world;
	private Body player;
	Fixture playerPhysicsFixture;
	Fixture playerSensorFixture;
	SpriteBatch batch;
	BitmapFont font;
	float w=200;
	float h=200;

	public float radius;

	private boolean active = true;

	public static final short categoryBits = 1;
	
	private boolean first = true;
	private Vector2 pos = new Vector2();
	   private Body leftWheel;
	   private Body rightWheel;
	   
	   private RevoluteJoint leftWheelRevoluteJoint;
	   private RevoluteJoint rightWheelRevoluteJoint;

	   private float motorSpeed=0;
	   public float getMotorSpeed() {
		return motorSpeed;
	}

	public void setMotorSpeed(float motorSpeed) {
		this.motorSpeed = motorSpeed;
	}

	private PrismaticJoint leftAxlePrismaticJoint;
	   private PrismaticJoint rightAxlePrismaticJoint;
	   //
	   private float carPosX=PhysicsHelper.ConvertToBox(20)*GameMenuInfo.carSizeCoeff;
	   private float carPosY=PhysicsHelper.ConvertToBox(20)*GameMenuInfo.carSizeCoeff;
	   //carbody width and height
	   
	   private float carWidth=PhysicsHelper.ConvertToBox(95*GameMenuInfo.ratio_w)*GameMenuInfo.carSizeCoeff;
	   private float carHeight=PhysicsHelper.ConvertToBox(1*GameMenuInfo.ratio_h)*GameMenuInfo.carSizeCoeff;
	   
	   private float carheadWidth = PhysicsHelper.ConvertToBox(125*GameMenuInfo.ratio_w)*GameMenuInfo.carSizeCoeff;
	   private float carHeadHeight = PhysicsHelper.ConvertToBox(10*GameMenuInfo.ratio_h)*GameMenuInfo.carSizeCoeff;
	   
	   
	 //axleContainer Distance and depth fromt the worldcenter of car
	   
	   private float axleContainerDistance=PhysicsHelper.ConvertToBox(100f*GameMenuInfo.ratio_w)*GameMenuInfo.carSizeCoeff;
	   private float axleContainerDepth=PhysicsHelper.ConvertToBox(0f*GameMenuInfo.ratio_h)*GameMenuInfo.carSizeCoeff;
	   
	   ///axle container width and height
	   private float axleContainerWidth=PhysicsHelper.ConvertToBox(10f*GameMenuInfo.ratio_w)*GameMenuInfo.carSizeCoeff;
	   private float axleContainerHeight=PhysicsHelper.ConvertToBox(05f*GameMenuInfo.ratio_h)*GameMenuInfo.carSizeCoeff;
	   
	   private float leftAxleContainerAngle=(MathUtils.degreesToRadians*0)/**GameMenuInfo.carSizeCoeff*/;
	   private float rightAxleContainerAngle=(MathUtils.degreesToRadians*0)/**GameMenuInfo.carSizeCoeff*/;
	   
	   
	   ///axle  width and height	   
	   private float axleWidth= PhysicsHelper.ConvertToBox(2.5f*GameMenuInfo.ratio_w)*GameMenuInfo.carSizeCoeff;
	   private float axleHeight=PhysicsHelper.ConvertToBox(0f*GameMenuInfo.ratio_h)*GameMenuInfo.carSizeCoeff;
	   private float leftAxleAngle=(MathUtils.degreesToRadians*0)/**GameMenuInfo.carSizeCoeff*/;
	   private float rightAxleAngle=(MathUtils.degreesToRadians*0)/**GameMenuInfo.carSizeCoeff*/;
	   ///wheel radious
	   private float wheelRadius=PhysicsHelper.ConvertToBox(29f*GameMenuInfo.ratio_w)*GameMenuInfo.carSizeCoeff;
	   
	   // rider width height and angle
	   private float manWidth=PhysicsHelper.ConvertToBox(20)*GameMenuInfo.ratio_w*GameMenuInfo.carSizeCoeff;
	   private float manHeight=PhysicsHelper.ConvertToBox(10)*GameMenuInfo.ratio_h*GameMenuInfo.carSizeCoeff;
	   private Body leftAxle;
	private Body rightAxle;
	private Background rider;
	private float agerY;
	private Body leftAxleContainer;
	private Body rightAxleContainer;
	private Joint leftWeldJoint;
	private Joint rightWeldJoint;

	private Body riderBody;
	private Joint riderWeldJoint;
	
	
	
	Point renderPoint = new Point();
	private Body carHead;
	private String imageTruckHead;
	private String imageTruckBody;
	
	public void update(float dt){
		
		if(leftAxlePrismaticJoint!=null)
		{
			if((PhysicsHelper.BOX_TO_WORLD*(leftAxlePrismaticJoint.getReactionForce(1/dt).x)>10)&&(PhysicsHelper.BOX_TO_WORLD*(leftAxlePrismaticJoint.getReactionForce(1/dt).y)>10))
			{
			}
		}

		
		   PhysicsHelper.accumulator+=dt;
//		   Helper.println( "dt:::"+PhysicsHelper.accumulator*1000f);
		    while(PhysicsHelper.accumulator> PhysicsHelper.BOX_STEP){
		      world.step(PhysicsHelper.BOX_STEP,PhysicsHelper.BOX_VELOCITY_ITERATIONS,PhysicsHelper.BOX_POSITION_ITERATIONS);
		      this.setLocation(PhysicsHelper.ConvertToWorld(player.getPosition().x), PhysicsHelper.ConvertToWorld(player.getPosition().y));
		      PhysicsHelper.accumulator -= PhysicsHelper.BOX_STEP;
//		      Helper.println("i'm here in update");
		
//				motorSpeed*=.99f;
//				if (motorSpeed>20) {
//					motorSpeed=20;
//				}
			
		     
			if (leftWheelRevoluteJoint!= null && rightWheelRevoluteJoint != null) {
				
//				rightWheelRevoluteJoint.setMaxMotorTorque(15);
//				leftWheelRevoluteJoint.setMaxMotorTorque(15);
				
				leftWheelRevoluteJoint.setMotorSpeed(motorSpeed);
				rightWheelRevoluteJoint.setMotorSpeed(motorSpeed);
				
				
//				Helper.println("traversed distance : " + player.getPosition().x+"leftmotor speed"+ leftWheelRevoluteJoint.getMotorSpeed());
//				Helper.println("traversed distance : " + player.getPosition().x+"rightmotor speed"+ rightWheelRevoluteJoint.getMotorSpeed());
			}
			
			
		    
		    GlobalVars.ge.getRenderer().getBatch().setProjectionMatrix(cam.combined);
			
		    }
		    world.clearForces();
		   }

	
	public Vehicle(float x, float y,float width, float height, String imageTruckHead, String imageTruckBody,float angle, World world, boolean b) {
//		super(x , y, width,height, 1);
		// TODO Auto-generated constructor stub
//		this.radius = radius / GameMenuInfo.ratio_w;
		Helper.println("X: " + x);
		Helper.println("After X: " +  this.x + imagePath);		
//		this.imagePath = imagePath;
		this.imageTruckHead = imageTruckHead;
		this.imageTruckBody = imageTruckBody;
		this.world = world;
		this.x = x;
		this.y = y;
		this.pos.set(this.x, this.y);
		this.width =width;
		this.height = height;
		//		createBallModel();
		createCarModel();
		GlobalVars.ge.getCurrentStage().subscribeToInteraction(this,
				InteractionTouch.class);
		rider = new Background(x, y, manWidth, manHeight, categoryBits);
	}
	
	public Vehicle(float x, float y,float width, float height,float angle, World world, boolean b) {
//		super(x , y, width,height, 1);
		// TODO Auto-generated constructor stub
//		this.radius = radius / GameMenuInfo.ratio_w;
		Helper.println("X: " + x);
		Helper.println("After X: " +  this.x + imagePath);		
//		this.imagePath = imagePath;
		this.imageTruckHead = AssetConstants.PHY_IMG_TRUCK_HEAD;
		this.imageTruckBody = AssetConstants.PHY_IMG_TRUCK_BODY;
		this.world = world;
		this.x = x;
		this.y = y;
		this.pos.set(this.x, this.y);
		this.width =width;
		this.height = height;
		//		createBallModel();
		createCarModel();
		GlobalVars.ge.getCurrentStage().subscribeToInteraction(this,
				InteractionTouch.class);
		rider = new Background(x, y, manWidth, manHeight, categoryBits);
	}

	
	@Override
	public void render() {
//		world.step(Gdx.graphics.getDeltaTime(), 4, 4);
		this.update(Gdx.graphics.getDeltaTime());
rightWheel.getLinearVelocity();
		
			
			cam.position.x = PhysicsHelper.ConvertToWorld(player.getPosition().x)+200;
			cam.position.y = PhysicsHelper.ConvertToWorld(player.getPosition().y) ;
			
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
			leftAxle.applyForce(0 , -1, leftAxle.getWorldCenter().x, leftAxle.getWorldCenter().y);
//			leftAxleContainer.applyLinearImpulse(0f,  -0.05f, leftAxleContainer.getPosition().x, leftAxleContainer.getPosition().y);
//			leftWheel.applyAngularImpulse(10f);
//			rightWheel.applyAngularImpulse(10f);
//			Helper.println("player angle in degree::"+(MathUtils.radiansToDegrees*player.getAngle()));
//			player.setTransform(player.getPosition(), 0f);
//			player.setTransform(player.getPosition().x, player.getPosition().y, player.getAngle()-(MathUtils.degreesToRadians*10));
//			Helper.println("key pressed: "+Keys.A+"player position physics world"+player.getPosition()+"and player position in game world::"+this.getLocation());
		}
 
		// apply right impulse, but only if max velocity is not reached yet
		if(Gdx.input.isKeyPressed(Keys.D)) {
			rightAxle.applyLinearImpulse(0,  -1, rightAxle.getWorldCenter().x, rightAxle.getWorldCenter().y);
//			leftWheel.applyAngularImpulse(-10f);
//			rightWheel.applyAngularImpulse(-10f);
//			player.setTransform(player.getPosition().x, player.getPosition().y, player.getAngle()-.1f);
//			Helper.println("player angle in degree::"+(MathUtils.radiansToDegrees*player.getAngle()));
//			player.setTransform(player.getPosition(), 0f);
//			player.setTransform(player.getPosition().x, player.getPosition().y, player.getAngle()+(MathUtils.degreesToRadians*10));
//			Helper.println("key pressed: "+Keys.D+"player position physics world"+player.getPosition()+"and player position in game world::"+this.getLocation());
		}
		
		if(Gdx.input.isKeyPressed(Keys.S)) {
			player.applyLinearImpulse(0, -2f, player.getPosition().x, player.getPosition().y);
//			rightWheel.applyLinearImpulse(0f, -05f, rightWheel.getPosition().x, rightWheel.getPosition().x);
//			Helper.println("key pressed: "+Keys.S+"player position physics world"+player.getPosition()+"and player position in game world::"+this.getLocation());
		}
 
		// apply right impulse, but only if max velocity is not reached yet
		if(Gdx.input.isKeyPressed(Keys.W)) {
//			player.applyLinearImpulse(0,  10f, player.getPosition().x, player.getPosition().y);
			
			player.setTransform(player.getPosition().x, player.getPosition().y+01f, player.getAngle());
//			rightWheel.applyLinearImpulse(0f, 05f, rightWheel.getPosition().x, rightWheel.getPosition().x);
//			leftWheel.applyLinearImpulse(0f, -05f, leftWheel.getPosition().x, leftWheel.getPosition().x);
//			Helper.println("key pressed: "+Keys.W+"player position physics world"+player.getPosition()+"and player position in game world::"+this.getLocation()+"region btm:"+this.getBottom()+"region top:"+this.getTop()+"region width:"+this.getWidth());
		}
		
//	      leftWheelRevoluteJoint.setLimits(-10, 100);
	  	if (Gdx.input.isKeyPressed(Keys.Z)) {
//			if(carHead.getLinearVelocity().x<-3f)
//			{
//				carHead.applyLinearImpulse(-05, 0, carHead.getWorldCenter().x, carHead.getPosition().x);
//			}
	  		
//	  		if(motorSpeed > -15)
	  			motorSpeed-=.1f;
	  			this.setMotorSpeed(motorSpeed);
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
//			Helper.println("carhead linear velocirty ::"+carHead.getLinearVelocity());
			if(carHead.getLinearVelocity().x<1.5f)
			{
//				carHead.applyLinearImpulse(01, 0, carHead.getWorldCenter().x, carHead.getPosition().x);
			}
//			leftWheelRevoluteJoint.setMaxMotorTorque(40);
			motorSpeed+=.11f;
			this.setMotorSpeed(motorSpeed);
			
			
			
//			Helper.println("Speeding up: Right : " + motorSpeed );
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
//		assetPack.addTexture(AssetConstants.IMG_BIKE_WHEEL);
//		assetPack.addTexture(AssetConstants.IMG_AXLE);
//		assetPack.addTexture(AssetConstants.IMG_RIDER);
//		assetPack.addTexture(AssetConstants.IMG_BIKE_BODY);
//		assetPack.addTexture(AssetConstants.PHY_IMG_TRUCK_BODY);
		
		
		
		
	}

	@Override
	public void init() {
//		if(Constants.ballSelected == 1)
//		this.image = Helper.getImageFromAssets(AssetConstants.IMG_BASKET_BALL);
//		if(Constants.ballSelected == 2)
//			this.image = Helper.getImageFromAssets(AssetConstants.IMG_RUBBER_BALL);
//		if(Constants.ballSelected == 3)
//			this.image = Helper.getImageFromAssets(AssetConstants.IMG_BOWL_BALL);
//		else this.image = Helper.getImageFromAssets(imagePath);
//		this.image =  Helper.getImageFromAssets(imagePath);
//		this.imageWheelLeft = Helper.getImageFromAssets(AssetConstants.IMG_BIKE_WHEEL);
//		this.imageWheelRight = Helper.getImageFromAssets(AssetConstants.IMG_BIKE_WHEEL);
//		this.imageLeftaxle = Helper.getImageFromAssets(AssetConstants.IMG_AXLE);
//		this.imageRightaxle = Helper.getImageFromAssets(AssetConstants.IMG_AXLE);
//		this.imageRider = Helper.getImageFromAssets(AssetConstants.IMG_RIDER);
//		this.imageBikeBody = Helper.getImageFromAssets(AssetConstants.IMG_BIKE_BODY);
//		this.imageBikeBody = Helper.getImageFromAssets(AssetConstants.IMG_BIKE_BODY);
		
//		this.imageTruckBody = Helper.getImageFromAssets(imageTruckBody);
		
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
				leftAxle.applyLinearImpulse(0f,  -0.01f, leftAxleContainer.getPosition().x, leftAxleContainer.getPosition().y);
			}
			else if(Gdx.input.getAccelerometerX() > 0) // positive
			{
				rightAxle.applyLinearImpulse(0f,  -0.01f, rightAxleContainer.getPosition().x, rightAxleContainer.getPosition().y);
			}
		}
	}

	public Body getBody() {
		return player;
	}




	public boolean isActive() {
		return active;
	}


	
	
	
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
		
		// ************************ THE CAR ************************ //
		this.createCarHead();
		
	// ************************ THE Rider ************************ //
		this.createRiderBody();
		// ************************ THE WHEELS ************************ //;
		// ************************ LEFT AXLE CONTAINER ************************ //
//		this.createAxleContainer();
//		// ************************ THE AXLES ************************ //
		this.createWheel();
		
		this.createAxle();
		
		riderWeldJoint = this.createWeldJoint(player, carHead, player.getWorldCenter());

		riderWeldJoint = this.createWeldJoint(player, riderBody, riderBody.getWorldCenter());
//		leftWeldJoint = this.createWeldJoint(player, leftAxleContainer, leftAxleContainer.getWorldCenter());
//		rightWeldJoint= this.createWeldJoint(player, rightAxleContainer, rightAxleContainer.getWorldCenter());
		leftAxlePrismaticJoint = this.createPrismaticJoint(player,leftAxle,leftAxle.getWorldCenter(), new Vector2((float)(Math.cos(MathUtils.degreesToRadians*(90-leftAxleAngle*MathUtils.radiansToDegrees))),(float)(Math.sin(MathUtils.degreesToRadians*(90-leftAxleAngle*MathUtils.radiansToDegrees)))));
		rightAxlePrismaticJoint = this.createPrismaticJoint(carHead,rightAxle,rightAxle.getWorldCenter(), new Vector2((float)(-Math.cos(MathUtils.degreesToRadians*(90-rightAxleAngle*MathUtils.radiansToDegrees))),(float)(Math.sin(MathUtils.degreesToRadians*(90-rightAxleAngle*MathUtils.radiansToDegrees)))));
		leftWheelRevoluteJoint = this.createRevoluteJoint(leftWheel,leftAxle,leftWheel.getWorldCenter());
//		Helper.println("joint null or not:::"+ leftWheelRevoluteJoint);
		rightWheelRevoluteJoint = this.createRevoluteJoint(rightWheel,rightAxle,rightWheel.getWorldCenter());
//		Helper.println("joint null or not:::"+ rightWheelRevoluteJoint);
		setPrisJointProperty(rightAxlePrismaticJoint, 0f, .05f);
		
		setDensities();
		
	}





	private void setDensity(Body body, float density)
	{
		body.getFixtureList().get(0).setDensity(density);
	}
	
	private void setPrisJointProperty(PrismaticJoint joint, float lowerLimit, float uperLimit)
	{
		joint.setLimits(lowerLimit, uperLimit);
	}
	private void setDensities() {
		setDensity(leftAxle, 1);
		setDensity(rightAxle, 1);
		setDensity(leftWheel, 5);
		setDensity(rightWheel, 5);
		setDensity(riderBody, 00);
		setDensity(player, 5);
		setDensity(carHead, 1);
//		setDensity(rightAxleContainer, 200);
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
		prismaticJoint.setMotorSpeed(0f);
		prismaticJoint.setLimits(0f, 0);
		return prismaticJoint;
		
//		jointName = prismaticJoint;
//		jointName.setLimits(-0.025f, 0.025f);
		
	}
		
	private RevoluteJoint createRevoluteJoint(Body bodyA, Body bodyB, Vector2 anchorA) {
		// TODO Auto-generated method stub
		RevoluteJointDef revoluteJointDef=new RevoluteJointDef();
		revoluteJointDef.initialize(bodyA, bodyB, anchorA);
//		revoluteJointDef.localAnchorB.set(anchorB);
		revoluteJointDef.enableMotor=true;
//		leftWheelRevoluteJointDef.enableLimit = true;
//		RevoluteJoint revoluteJoint=(RevoluteJoint) world.createJoint(revoluteJointDef);// as b2RevoluteJoint;
//		revoluteJoint.setMaxMotorTorque(10f);
//		jointName = revoluteJoint;
		RevoluteJoint jointName=(RevoluteJoint) world.createJoint(revoluteJointDef);// as b2RevoluteJoint;
		jointName.setMaxMotorTorque(30f);
		jointName.setMotorSpeed(01);
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
		
		PhysicsBody2 pb =new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(leftWheel.getWorldCenter().x)-axleWidth/2),//*Math.cos(/*Math.PI/2-*/leftAxleAngle))),
				PhysicsHelper.ConvertToWorld((float)(leftWheel.getWorldCenter().y+axleWidth/2)),//*Math.sin(/*Math.PI/2-*/leftAxleAngle))),
				PhysicsHelper.ConvertToWorld(axleWidth),
				0f,
				AssetConstants.PHY_AXLE ,
				AssetConstants.PHY_IMG_LEFTAXLE,
				"axle", 
				world,
				((short) -1),
				((short) 4),
				((short) (11)),
				"leftaxle",5,
				BodyType.DynamicBody);
		
		pb.getBody().setTransform(pb.getBody().getPosition(), pb.getBody().getAngle()-leftAxleAngle);
		
//		GlobalVars.ge.getCurrentStage().addElement(pb);
		leftAxle = pb.getBody();
		leftAxle.setUserData("leftAxle");
		PhysicsBody2 pb2 =new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(rightWheel.getWorldCenter().x-axleWidth/2)),//*Math.cos(Math.PI/2-rightAxleAngle))),
				PhysicsHelper.ConvertToWorld((float)(rightWheel.getWorldCenter().y+axleWidth/2)),//*Math.sin(Math.PI/2-rightAxleAngle))),
				PhysicsHelper.ConvertToWorld(axleWidth),
				0f,
				AssetConstants.PHY_AXLE ,
				AssetConstants.PHY_IMG_LEFTAXLE,
				"axle", 
				world,
				((short) -1),
				((short) 5),
				((short) (11)),
				"rightaxle",6,
				BodyType.DynamicBody);
		
		pb2.getBody().setTransform(pb2.getBody().getPosition(), pb2.getBody().getAngle()+rightAxleAngle);
		
//		GlobalVars.ge.getCurrentStage().addElement(pb2);
		rightAxle = pb2.getBody();
		rightAxle.setUserData("rightAxle");
//		rightAxle.setUserData("rightAxle");
//		rightAxle.SetPosition(new Vector2(carPosX/worldScale+axleContainerDistance/worldScale+axleContainerHeight/worldScale*Math.cos((90-axleAngle)*degreesToRadians),carPosY/worldScale+axleContainerDepth/worldScale+axleContainerHeight/worldScale*Math.sin((90-axleAngle)*degreesToRadians)));
	//

	}

	private void createAxleContainer() {
		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(player.getWorldCenter().x)),
				PhysicsHelper.ConvertToWorld((float)(player.getWorldCenter().y-axleContainerDepth)),///*-axleContainerHeight/2*(float)Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))*/+axleContainerWidth/2*Math.sin(/*Math.PI/2-*/leftAxleContainerAngle))),
				PhysicsHelper.ConvertToWorld(axleContainerWidth),
				0f,
				AssetConstants.PHY_AXLE_CONTAINER ,
				AssetConstants.PHY_IMG_LEFTAXLECONTAINER,
				"leftaxlecontainer", 
				world,
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
		PhysicsBody2 pb2 = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(leftAxleContainer.getPosition().x+axleContainerDistance)),
				PhysicsHelper.ConvertToWorld((float)(player.getWorldCenter().y-axleContainerDepth)),//-/*axleContainerHeight/2*(float)Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))-*/axleContainerWidth/2*Math.cos(/*Math.PI/2-*/rightAxleContainerAngle))),
				PhysicsHelper.ConvertToWorld(axleContainerWidth),
				0f,
				AssetConstants.PHY_AXLE_CONTAINER ,
				AssetConstants.PHY_IMG_LEFTAXLECONTAINER,
				"leftaxlecontainer", 
				world,
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
	}

	private void createWheel() {
		
		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(player.getWorldCenter().x-wheelRadius*.750f)),
				PhysicsHelper.ConvertToWorld((float)(player.getPosition().y-axleContainerDepth-axleContainerHeight-wheelRadius/**Math.sin(Math.PI/2-axleAngle)*/)),
				PhysicsHelper.ConvertToWorld(wheelRadius*2),
				0f,
				AssetConstants.PHY_TRUCK_WHEEL ,
				AssetConstants.PHY_IMG_TRUCK_WHEEL,
				"truckwheel", 
				world,
				((short) -1),
				((short) 6),
				((short) (11)),
				"lefttruckWheel",7,
				BodyType.DynamicBody);
//		new Point().getPointAtDistance(srcPoint, destinationPoint, retPoint, distance)
//		pb.getBody().setTransform(pb.getBody().getPosition(), pb.getBody().getAngle()-axleAngle);
		pb.getBody().getFixtureList().get(0).setFriction(10f);
		GlobalVars.ge.getCurrentStage().addElement(pb);
		leftWheel = pb.getBody();
		leftWheel.setUserData("lefttruckWheel");
		
		PhysicsBody2 pb2 = new PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(leftWheel.getPosition().x+axleContainerDistance)),//+2*axleContainerHeight*Math.cos(MathUtils.degreesToRadians*(90-rightAxleAngle*MathUtils.radiansToDegrees)))-wheelRadius),
				PhysicsHelper.ConvertToWorld((float)(player.getPosition().y-axleContainerDepth-axleContainerHeight-wheelRadius)),//*(float)Math.sin(MathUtils.degreesToRadians*(90-rightAxleAngle*MathUtils.radiansToDegrees)))-wheelRadius),
				PhysicsHelper.ConvertToWorld(wheelRadius*2f),
				0f,
				AssetConstants.PHY_TRUCK_WHEEL ,
				AssetConstants.PHY_IMG_TRUCK_WHEEL,
				"truckwheel", 
				world,
				((short) -1),
				((short) 7),
				((short) (11)),
				"righttruckWheel",8,
				BodyType.DynamicBody);
//		Point p = new Point(0,0);
//		p.x = PhysicsHelper.ConvertToWorld(player.getWorldCenter().x);
//		p.y = PhysicsHelper.ConvertToWorld(player.getWorldCenter().y);
//		
//		Helper.println("\n\n\nSource: " + p.toString());
//		Point d = new Point(0,0);
//		p.getPointAtDistance(p, -45, 200, d);
//		
//		Helper.println("Destination: " + d.toString());
//		Helper.println("Distance: " + p.distancePoint2Point(d));
//		
//		PhysicsBody2 pb2 = new PhysicsBody2(d.x, d.y,								
//				PhysicsHelper.ConvertToWorld(wheelRadius*2),
//				0f,
//				AssetConstants.PHY_WHEEL ,
//				AssetConstants.PHY_IMG_WHEEL,
//				"wheel", 
//				world,
//				((short) 7),
//				((short) (1|11)),
//				"rightWheel",8,
//				BodyType.StaticBody);
		
		
//		pb.getBody().setTransform(pb.getBody().getPosition(), pb.getBody().getAngle()-axleAngle);
		
		GlobalVars.ge.getCurrentStage().addElement(pb2);
		rightWheel = pb2.getBody();
		rightWheel.setUserData("righttruckWheel");
		
	}

	private void createRiderBody() {
		
		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld(player.getWorldCenter().x)-PhysicsHelper.ConvertToWorld(manWidth*2.5f),
				PhysicsHelper.ConvertToWorld(player.getWorldCenter().y)-PhysicsHelper.ConvertToWorld(manWidth),
				PhysicsHelper.ConvertToWorld(manWidth),
				0f,
				AssetConstants.PHY_TRUCK_FIRE ,
				AssetConstants.PHY_IMG_TRUCK_FIRE,
				"truckfire", 
				world,
				((short) -1),
				((short) 13),
				((short) (1|11)),
				"truckfire",2,
				BodyType.DynamicBody);
		
		GlobalVars.ge.getCurrentStage().addElement(pb);
		riderBody = pb.getBody();
		riderBody.setUserData("truckfire");
	}
	
	
	private void createCarHead() {
		// TODO Auto-generated method stub
		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld(player.getWorldCenter().x)+PhysicsHelper.ConvertToWorld(carWidth/2),
				PhysicsHelper.ConvertToWorld(player.getPosition().y),
				PhysicsHelper.ConvertToWorld(carheadWidth ),
				0f,
				AssetConstants.PHY_TRUCK_HEAD ,
				imageTruckHead,
				"truckhead", 
				world,
				((short) -1),
				((short) 13),
				((short) (1|11)),
				"truckhead",2,
				BodyType.DynamicBody);
		
		GlobalVars.ge.getCurrentStage().addElement(pb);
		carHead = pb.getBody();
		carHead.setUserData("truckhead");
	}
	

	private void createCarBody() {
		PhysicsBody2 pb = new PhysicsBody2(this.x,
				this.y,
				PhysicsHelper.ConvertToWorld(carWidth*2),
				carHeight,
				AssetConstants.PHY_TRUCK_BODY ,
				imageTruckBody,
				"truckbody", 
				world, 
				((short) -1),
				((short) 1),
				((short) (11|20)),
				"truckbody",
				2,
				BodyType.DynamicBody);
		
		GlobalVars.ge.getCurrentStage().addElement(pb);
		player = pb.getBody();
		player.setUserData("truckbody");
		
	}

	@Override
	public void onEvent(Point hitPoint) {
		// TODO Auto-generated method stub
		
	}

	
	
}

