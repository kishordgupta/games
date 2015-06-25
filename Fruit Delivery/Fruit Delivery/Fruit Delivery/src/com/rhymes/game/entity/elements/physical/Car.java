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
import com.rhymes.game.entity.elements.fruitdelivery.TruckBody;
import com.rhymes.game.entity.elements.nonphysical.Arrow;
import com.rhymes.game.entity.elements.nonphysical.Player;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.game.stage.menus.stick.LevelInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.renderer.Renderer;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;
import com.rhymes.helpers.Helper;
import com.rhymes.game.entity.elements.nonphysical.Arrow;
import com.rhymes.game.entity.elements.physical.PhysicsTest.MovingPlatform;
import com.rhymes.game.entity.elements.ui.PhysicsBody2;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.fruitdelivery.menu.selectvehicle.SelectVehicleMenuFruit;
import com.rhymes.game.fruitdelivery.menu.selectvehicle.res.SelectButtonFruit.SpecificButtonTagFruit;
import com.rhymes.game.fruitdelivery.menu.settings.res.SpeedImageButton.speedTypeFruit;

public class Car extends ElementBase implements InteractionTouchCallbacks {

	public boolean started = false;
	public float MAX_DAMAGE = 100f;
	public float damage = 0;

	public boolean carDirectionForward = true;
	
	// private double accForce = -0.09f;
	private double accForce = -4f;
	private double maxMotorSpeed = 100;
	Vector2 backForce = new Vector2(-50, 0);

	private Body leftAxleContainer;
	private Body rightAxleContainer;

	private Body frontAxle;
	private Body player;

	private Body carHead;

	OrthographicCamera cam;
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

	private Joint leftAxlePrismaticJoint;
	private Joint rightAxlePrismaticJoint;
	private PrismaticJoint frontAxlePrismaticJoint;

	private Joint leftWeldJoint;
	private Joint rightWeldJoint;
	private WeldJoint carWeldJoint;
	private WeldJoint fireWeldJoint;

	private float motorSpeed = 0;

	// private float
	// truckBodyWidth=PhysicsHelper.ConvertToBox(95*GameMenuInfo.ratio_w)*GameMenuInfo.carSizeCoeff;

	public static float carWidth = PhysicsHelper
			.ConvertToBox(256 * GameMenuInfo.ratio_w)
			* GameMenuInfo.carSizeCoeff;

	private float carheadWidth = PhysicsHelper
			.ConvertToBox(130)
			* GameMenuInfo.carSizeCoeff;

	// private float carHeight = PhysicsHelper
	// .ConvertToBox(10 * GameMenuInfo.ratio_h)
	// * GameMenuInfo.carSizeCoeff;

	// axleContainer Distance and depth fromt the worldcenter of car

	public float getCarheadWidth() {
		return carheadWidth;
	}

	public void setCarheadWidth(float carheadWidth) {
		this.carheadWidth = carheadWidth;
	}

	// private float axleContainerDistance = PhysicsHelper
	// .ConvertToBox(90 * GameMenuInfo.ratio_w)
	// * GameMenuInfo.carSizeCoeff;
	private float axleContainerDepth = PhysicsHelper
			.ConvertToBox(10 * GameMenuInfo.ratio_h)
			* GameMenuInfo.carSizeCoeff;

	private float axleContainerHeight = PhysicsHelper
			.ConvertToBox(05f * GameMenuInfo.ratio_h)
			* GameMenuInfo.carSizeCoeff;

	private float axleContainerDistance = PhysicsHelper
			.ConvertToBox(100f * GameMenuInfo.ratio_w)
			* GameMenuInfo.carSizeCoeff;

	// /axle container width and height
	// private float axleContainerWidth = PhysicsHelper
	// .ConvertToBox(05f * GameMenuInfo.ratio_w)
	// * GameMenuInfo.carSizeCoeff;
	// private float axleContainerHeight = PhysicsHelper
	// .ConvertToBox(0.5f * GameMenuInfo.ratio_h)
	// * GameMenuInfo.carSizeCoeff;

	// private float leftAxleContainerAngle = (MathUtils.degreesToRadians *
	// 0)/**
	// *
	// * GameMenuInfo.carSizeCoeff
	// */
	// ;
	// private float rightAxleContainerAngle = (MathUtils.degreesToRadians *
	// 0)/**
	// *
	// * GameMenuInfo.carSizeCoeff
	// */
	// ;
	// /axle width and height
	private float axleWidth = PhysicsHelper
			.ConvertToBox(1f * GameMenuInfo.ratio_w)
			* GameMenuInfo.carSizeCoeff;

	public float getAxleWidth() {
		return axleWidth;
	}

	public void setAxleWidth(float axleWidth) {
		this.axleWidth = axleWidth;
	}

	private float axleAngle = (MathUtils.degreesToRadians * 0)/**
	 * 
	 * 
	 * GameMenuInfo.carSizeCoeff
	 */
	;
	private float leftAxleAngle = axleAngle;
	private float rightAxleAngle = axleAngle;
	private float frontAxleAngle = axleAngle;
	// /wheel radious
	private float wheelRadius = PhysicsHelper
			.ConvertToBox(45 * GameMenuInfo.ratio_w)
			* GameMenuInfo.carSizeCoeff;

	private float frontWheelRadius = PhysicsHelper
			.ConvertToBox(45 * GameMenuInfo.ratio_w)
			* GameMenuInfo.carSizeCoeff;

	// rider width height and angle
	private float manWidth = PhysicsHelper.ConvertToBox(20)
			* GameMenuInfo.ratio_w * GameMenuInfo.carSizeCoeff;
	private float manHeight = PhysicsHelper.ConvertToBox(10)
			* GameMenuInfo.ratio_h * GameMenuInfo.carSizeCoeff;

	private String carType;// = Constants.carTypeHuwwer;

	private String physicsBody;// = AssetConstants.PHY_BENGA_BODY;
	private String bodyfixturepath;// = "bengabody";

	private String physicsBodyHead;// = AssetConstants.PHY_BENGA_BODY;
	private String headBodyfixturepath;// = "bengabody";

	private String imageCarBody;// = AssetConstants.PHY_IMG_HUWWER_BODY ;
	private String imageTruckHead;
	private String imageTruckBody;
	private String imageTruckWheel;
	private String imageCarWheel;// =AssetConstants.PHY_IMG_HUWWER_WHEEL;
	private String imageaxle;// =AssetConstants.PHY_IMG_LEFTAXLE;

	// private TextureRegion imageRider;

	private TextureRegion imageBikeBody;

	private Background rider;
	private float agerY;

	// private Body leftAxleContainer;
	// private Body rightAxleContainer;

	/*
	 * // float aspectRatio =
	 * ((float)Gdx.graphics.getWidth()*GameMenuInfo.ratio_w // /
	 * (float)Gdx.graphics.getHeight()*GameMenuInfo.ratio_h); //
	 * OrthographicCamera cam= new OrthographicCamera(aspectRatio, .1f);
	 * 
	 * final static float MAX_VELOCITY = 7f; String imagePath; private World
	 * world; private Body player; private Body body; Fixture
	 * playerPhysicsFixture; Fixture playerSensorFixture; SpriteBatch batch;
	 * BitmapFont font; float w = 200; float h = 200;
	 * 
	 * public float radius;
	 * 
	 * private boolean isStatic;
	 * 
	 * private boolean collided = false;
	 * 
	 * private boolean active = true;
	 * 
	 * public static final short categoryBits = 1;
	 * 
	 * private float prevY = 1;
	 * 
	 * private boolean first = true; private int stillTime; private Vector2 pos
	 * = new Vector2(); private Body floor; private Body leftWheel; private Body
	 * rightWheel; private Body frontWheel; private RevoluteJoint
	 * leftWheelRevoluteJoint; private RevoluteJoint rightWheelRevoluteJoint;
	 * 
	 * // /wheel radious
	 * 
	 * 
	 * private float frontWheelRadius = PhysicsHelper .ConvertToBox(45 *
	 * GameMenuInfo.ratio_w) GameMenuInfo.carSizeCoeff;
	 * 
	 * 
	 * 
	 * private Boolean left = false; private Boolean right = false; private
	 * float motorSpeed = 0;
	 * 
	 * 
	 * private PrismaticJoint leftAxlePrismaticJoint; private PrismaticJoint
	 * rightAxlePrismaticJoint; // private float carPosX =
	 * PhysicsHelper.ConvertToBox(20) GameMenuInfo.carSizeCoeff; private float
	 * carPosY = PhysicsHelper.ConvertToBox(20) GameMenuInfo.carSizeCoeff; //
	 * carbody width and height
	 * 
	 * private float carWidth = PhysicsHelper .ConvertToBox(55
	 *//** GameMenuInfo.ratio_w */
	/*
	 * ) GameMenuInfo.carSizeCoeff; private float carHeight = PhysicsHelper
	 * .ConvertToBox(10
	 *//** GameMenuInfo.ratio_h */
	/*
	 * ) GameMenuInfo.carSizeCoeff;
	 * 
	 * // axleContainer Distance and depth fromt the worldcenter of car
	 * 
	 * private float axleContainerDistance = PhysicsHelper .ConvertToBox(40
	 *//** GameMenuInfo.ratio_w */
	/*
	 * ) GameMenuInfo.carSizeCoeff; private float axleContainerDepth =
	 * PhysicsHelper .ConvertToBox(23
	 *//** GameMenuInfo.ratio_h */
	/*
	 * ) GameMenuInfo.carSizeCoeff;
	 * 
	 * // /axle container width and height private float axleContainerWidth =
	 * PhysicsHelper .ConvertToBox(05f
	 *//** GameMenuInfo.ratio_w */
	/*
	 * ) GameMenuInfo.carSizeCoeff; private float axleContainerHeight =
	 * PhysicsHelper .ConvertToBox(05f
	 *//** GameMenuInfo.ratio_h */
	/*
	 * ) GameMenuInfo.carSizeCoeff;
	 * 
	 * private float leftAxleContainerAngle = (MathUtils.degreesToRadians * 35)
	 *//**
	 * 
	 * 
	 * 
	 * GameMenuInfo.carSizeCoeff
	 */
	/*
	 * ; private float rightAxleContainerAngle = (MathUtils.degreesToRadians *
	 * 35)
	 *//**
	 * 
	 * 
	 * 
	 * GameMenuInfo.carSizeCoeff
	 */
	/*
	 * ;
	 * 
	 * // /axle width and height private float axleWidth = PhysicsHelper
	 * .ConvertToBox(2.5f
	 *//** GameMenuInfo.ratio_w */
	/*
	 * ) GameMenuInfo.carSizeCoeff; private float axleHeight = PhysicsHelper
	 * .ConvertToBox(2.5f
	 *//** GameMenuInfo.ratio_h */
	/*
	 * ) GameMenuInfo.carSizeCoeff; private float axleAngle =
	 * (MathUtils.degreesToRadians * 0); private float leftAxleAngle =
	 * axleAngle; private float rightAxleAngle = axleAngle; private float
	 * frontAxleAngle =axleAngle; // /wheel radious private float wheelRadius =
	 * PhysicsHelper .ConvertToBox(25
	 *//** GameMenuInfo.ratio_w */
	/*
	 * ) GameMenuInfo.carSizeCoeff;
	 * 
	 * // rider width height and angle private float manWidth =
	 * PhysicsHelper.ConvertToBox(45)
	 *//** GameMenuInfo.ratio_w */
	/*
	 *  * GameMenuInfo.carSizeCoeff; private float manHeight =
	 * PhysicsHelper.ConvertToBox(10)
	 *//** GameMenuInfo.ratio_h */
	/*
	 *  * GameMenuInfo.carSizeCoeff; private float riderAngle =
	 * (MathUtils.degreesToRadians * 0) GameMenuInfo.carSizeCoeff;
	 * 
	 * private PrismaticJointDef leftAxlePrismaticJointDef; private
	 * PrismaticJointDef rightAxlePrismaticJointDef; private float angle;
	 * private TextureRegion imageWheelRight; private TextureRegion
	 * imageWheelLeft; private Body leftAxle; private Body rightAxle; private
	 * TextureRegion imageLeftaxle; private TextureRegion imageRightaxle;
	 * 
	 * private Renderer renderer; private TextureRegion imageRider;
	 * 
	 * private TextureRegion imageBikeBody;
	 * 
	 * private Background rider; private BikeLevel currentLevel; private float
	 * agerY; private float agerX; private Body leftAxleContainer; private Body
	 * rightAxleContainer; private DistanceJoint leftAxleContainerDistanceJoint;
	 * private DistanceJoint rightAxleContainerDistanceJoint; private Joint
	 * leftWeldJoint; private Joint rightWeldJoint;
	 * 
	 * private RevoluteJoint leftAxleContainerRevoluteJoint; private Body
	 * riderBody; private Body fumeBody; private Joint riderWeldJoint; private
	 * PrismaticJoint riderPrismaticJoint;
	 */

	public void update(float dt) {

		if (GlobalVars.ge.getCurrentStage().getGameState() == GameState.OVER)
			return;

		PhysicsHelper.accumulator += dt / 1000f;
		Helper.printTest("Accumulator: " + PhysicsHelper.accumulator
				+ " --  Box Step: " + PhysicsHelper.BOX_STEP);
		if (PhysicsHelper.accumulator > PhysicsHelper.BOX_STEP) {

			long time = System.currentTimeMillis();
			if(Gdx.app.getType() == ApplicationType.Android){
				world.step( /* PhysicsHelper.BOX_STEP */0.04f,
						PhysicsHelper.BOX_VELOCITY_ITERATIONS,
						PhysicsHelper.BOX_POSITION_ITERATIONS);
				Helper.printTest("============== World Step: "
						+ (System.currentTimeMillis() - time));
			}
			else{
				world.step( /* PhysicsHelper.BOX_STEP */0.016f,
						PhysicsHelper.BOX_VELOCITY_ITERATIONS,
						PhysicsHelper.BOX_POSITION_ITERATIONS);
				Helper.printTest("============== World Step: "
						+ (System.currentTimeMillis() - time));
			}
			this.setLocation(
					PhysicsHelper.ConvertToWorld(player.getPosition().x),
					PhysicsHelper.ConvertToWorld(player.getPosition().y));
			PhysicsHelper.accumulator = 0;



//			world.clearForces();
		}
		if (leftWheelRevoluteJoint != null
				&& rightWheelRevoluteJoint != null) {

//			rightWheelRevoluteJoint.setMaxMotorTorque(1000);
//			leftWheelRevoluteJoint.setMaxMotorTorque(1000);

			if (!(motorSpeed < 0 && motorSpeed > -2f)) {
				leftWheelRevoluteJoint.setMotorSpeed(motorSpeed);
				rightWheelRevoluteJoint.setMotorSpeed(motorSpeed);
			}

		}
		
		
	}
	
	private void updateCamera()
	{
		cam.position.x = (PhysicsHelper.ConvertToWorld(player
				.getPosition().x) * LevelInfo.ratioX + Gdx.graphics
				.getWidth() / 3f);
		cam.position.y = PhysicsHelper.ConvertToWorld(player
				.getPosition().y)
				* LevelInfo.ratioX
				+ 140f
				* LevelInfo.ratioY;

//		GlobalVars.ge.getRenderer().getBatch()
//				.setProjectionMatrix(cam.combined);

	}

	public Car(float x, float y, float width, float height, float angle,
			String imagePath, World world, boolean b) {
		// super(x , y, width,height, 1);
		// TODO Auto-generated constructor stub
		// this.radius = radius / GameMenuInfo.ratio_w;
		// Helper.println("X: " + x);
		// Helper.println("After X: " + this.x + imagePath);
		this.imagePath = imagePath;
		this.world = world;
		this.x = x;
		this.y = y;
		this.pos.set(this.x, this.y);
		this.width = width;
		this.height = height;
		// this.angle = angle;
		// this.isStatic = b;
		// createBallModel();
		createCarModel();
		GlobalVars.ge.getCurrentStage().subscribeToInteraction(this,
				InteractionTouch.class);
	}

	// float rad;

	// boolean first = true;

	// @Override
	public void updatePhysicsWorld(long dt) {

		long time = System.currentTimeMillis();

		this.update(/* Gdx.graphics.getDeltaTime() */dt);
		Helper.printTest("Car updatePhysicalWorld: "
				+ (System.currentTimeMillis() - time));

		// apply left impulse, but only if max velocity is not reached yet
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			leftAxle.setAngularVelocity(0);
			leftAxle.applyLinearImpulse(
					(float) (accForce * Math.cos(player.getAngle() + 90
							* MathUtils.degreesToRadians)),
					(float) (accForce * Math.sin(player.getAngle() + 90
							* MathUtils.degreesToRadians)),
					leftAxle.getWorldCenter().x, leftAxle.getWorldCenter().y);
		}

		// apply right impulse, but only if max velocity is not reached yet
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			rightAxle.setAngularVelocity(0);
			rightAxle.applyLinearImpulse(
					(float) (accForce * Math.cos(player.getAngle() + 90
							* MathUtils.degreesToRadians)),
					(float) (accForce * Math.sin(player.getAngle() + 90
							* MathUtils.degreesToRadians)),
					rightAxle.getWorldCenter().x, rightAxle.getWorldCenter().y);

		}

		if (Gdx.input.isKeyPressed(Keys.S)) {
			player.applyLinearImpulse(0, -2f, player.getPosition().x,
					player.getPosition().y);
			// rightWheel.applyLinearImpulse(0f, -05f,
			// rightWheel.getPosition().x, rightWheel.getPosition().x);
			// Helper.println("key pressed: "+Keys.S+"player position physics world"+player.getPosition()+"and player position in game world::"+this.getLocation());
		}

		// apply right impulse, but only if max velocity is not reached yet
		if (Gdx.input.isKeyPressed(Keys.W)) {
			// player.applyLinearImpulse(0, 10f, player.getPosition().x,
			// player.getPosition().y);

			player.setTransform(player.getPosition().x,
					player.getPosition().y + 04f, player.getAngle());
			// rightWheel.applyLinearImpulse(0f, 05f,
			// rightWheel.getPosition().x, rightWheel.getPosition().x);
			// leftWheel.applyLinearImpulse(0f, -05f, leftWheel.getPosition().x,
			// leftWheel.getPosition().x);
			// Helper.println("key pressed: "+Keys.W+"player position physics world"+player.getPosition()+"and player position in game world::"+this.getLocation()+"region btm:"+this.getBottom()+"region top:"+this.getTop()+"region width:"+this.getWidth());
		}

		// leftWheelRevoluteJoint.setLimits(-10, 100);
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			// if (motorSpeed > -20)
			// motorSpeed -= 10f;
			increaseMotorSpeed(false);
			// Helper.println("Speeding down: left : " + motorSpeed);
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {

			// if(motorSpeed > 30 && motorSpeed < 30)
			// motorSpeed+=0.1f;
			// else
			// player.applyForce(new Vector2((float)(motorSpeed *
			// Math.cos(player.getAngle() * MathUtils.degreesToRadians)),
			// (float)(motorSpeed * Math.sin(player.getAngle() *
			// MathUtils.degreesToRadians))), player.getWorldCenter());
			// motorSpeed = leftWheelRevoluteJoint.getMotorSpeed();
			// Helper.println("MotorSpeed " + motorSpeed);
			// motorSpeed =
			// leftWheelRevoluteJoint.getMotorTorque(1/Gdx.graphics.getDeltaTime());
			// if(leftWheelRevoluteJoint != null)
			// leftWheelRevoluteJoint.setMaxMotorTorque(35);

			// if(motorSpeed < 0)
			// motorSpeed = 0;
			// if(motorSpeed < 35)
			// motorSpeed += 0.3f;

			increaseMotorSpeed(true);
			// Helper.println("Speeding up: Right : " + motorSpeed);
			// player.applyForce(new Vector2(1000, 0), player.getWorldCenter());
		}
		if (Gdx.input.isKeyPressed(Keys.C)) {
			// motorSpeed+=0.1;
			Helper.println("Destroying");

			// ((BikeLevel)
			// GlobalVars.ge.getCurrentStage()).getWorld().destroyJoint(leftAxlePrismaticJoint);
			//
			// ((BikeLevel)
			// GlobalVars.ge.getCurrentStage()).getWorld().destroyJoint(leftAxlePrismaticJoint);
			//
			this.destroyJoints();

		}
		if (Gdx.input.isKeyPressed(Keys.R)) {
			// motorSpeed+=0.5;
			// Helper.println("Speeding up: Right" );
			((BikeLevel) GlobalVars.ge.getCurrentStage()).reload();

		}

	}

	/*public void increaseMotorSpeed(boolean directionRight) {
		Helper.printTest2("Motorspeed : " + motorSpeed);
		if (directionRight) {
			if (motorSpeed < 0) {
				motorSpeed = 0;
			}

			if (motorSpeed == 0) {
				motorSpeed += 0.1f;
				StartupInfo.sound_handler
						.playSound(SoundHandler.soundType.SOUND_BIKE_START);
				StartupInfo.sound_handler.playMusic(
						musicType.SOUND_BIKE_RUNNING, 0.2f);
			}
			if (motorSpeed < 8 && motorSpeed > 0) {
				motorSpeed += 0.2f;
			}
			// if (motorSpeed < 10 && motorSpeed >= 8) {
			// motorSpeed += 0.2f;
			// }
			if (motorSpeed < 30&& motorSpeed >= 8) {
				motorSpeed += 0.4f;
			}
			if (motorSpeed < maxMotorSpeed && motorSpeed >= 30) {
				motorSpeed += 1f;
			}
			// else if(motorSpeed < 20 && motorSpeed >= 15)
			// {
			// motorSpeed += 0.15f;
			// }
			// else if(motorSpeed < 30 && motorSpeed >= 20)
			// {
			// motorSpeed += 0.3f;
			// }
			// else if(motorSpeed < 50 && motorSpeed >= 30)
			// {
			// motorSpeed += 0.5f;
			// }

			if (motorSpeed > maxMotorSpeed)
				motorSpeed = (float) maxMotorSpeed;
		} else {
			if (motorSpeed >= 15)
				motorSpeed -= 1f;
			else if (motorSpeed >= 10)
				motorSpeed -= 0.6f;
			else if (motorSpeed >= 0)
				motorSpeed -= 0.3f;
			if (motorSpeed <= 0 && motorSpeed > -maxMotorSpeed) {
				// StartupInfo.sound_handler.stopSound(SoundHandler.soundType.SOUND_BIKE_RUNNING);
				((BikeLevel) GlobalVars.ge.getCurrentStage()).stopSounds();

				motorSpeed -= 0.05;

				// rightWheel.applyForce(backForce,
				// rightWheel.getWorldCenter());
				Helper.println("applying force");
			}
			// if(motorSpeed == 0)
			// player.applyForce(v, player.getWorldCenter());
		}

	}*/

	public void increaseMotorSpeed(boolean directionRight) {
//		Helper.printTest("Motorspeed: " + motorSpeed);
		if (directionRight) {//accelarate
			
			if (motorSpeed <= 0){

				if(!carDirectionForward)
					motorSpeed-=0.1;
				else
					motorSpeed+=0.1;
			}
			
			if(!carDirectionForward){//reverse
				if (motorSpeed >= 15)
					motorSpeed -= 1f;
				else if (motorSpeed >= 10)
					motorSpeed -= 0.6f;
				else if (motorSpeed > 0)
					motorSpeed -= 0.1f;
				if(motorSpeed < 1 && motorSpeed >= 0){
					((BikeLevel)GlobalVars.ge.getCurrentStage()).stopSounds();
					motorSpeed = 0;
				}
				return;
			}

			if(motorSpeed == 0)
			{
				motorSpeed += 0.1f;
				StartupInfo.sound_handler.playSound(SoundHandler.soundType.SOUND_BIKE_START);
				StartupInfo.sound_handler.playMusic(musicType.SOUND_BIKE_RUNNING,0.2f);
			}		
			if (motorSpeed < 20 && motorSpeed > 0) {
				motorSpeed += 0.1f;
			}
//			if (motorSpeed < 10 && motorSpeed >= 8) {
//				motorSpeed += 0.2f;
//			}
			if (motorSpeed < maxMotorSpeed && motorSpeed >= 8) {
				motorSpeed += 0.3f;
			}
			// else if(motorSpeed < 20 && motorSpeed >= 15)
			// {
			// motorSpeed += 0.15f;
			// }
			// else if(motorSpeed < 30 && motorSpeed >= 20)
			// {
			// motorSpeed += 0.3f;
			// }
			// else if(motorSpeed < 50 && motorSpeed >= 30)
			// {
			// motorSpeed += 0.5f;
			// }

			if (motorSpeed > maxMotorSpeed)
				motorSpeed = (float) maxMotorSpeed;
		} else {
			//on brake
			
			if (motorSpeed >= 15)
				motorSpeed -= 1f;
			else if (motorSpeed >= 10)
				motorSpeed -= 0.6f;
			else if (motorSpeed > 0)
				motorSpeed -= 0.1f;
			
			if(motorSpeed < 1 && motorSpeed >= 0){
				((BikeLevel)GlobalVars.ge.getCurrentStage()).stopSounds();
				motorSpeed = 0;
			}

			if (motorSpeed < 0 ) {
//				StartupInfo.sound_handler.stopSound(SoundHandler.soundType.SOUND_BIKE_RUNNING);
//					motorSpeed+= 0.1;
				if (motorSpeed <= -15)
					motorSpeed += 1f;
				else if (motorSpeed <= -10)
					motorSpeed += 0.6f;
				else if (motorSpeed < 0)
					motorSpeed += 0.1f;
				
				if(motorSpeed > -1 && motorSpeed <= 0){
					((BikeLevel)GlobalVars.ge.getCurrentStage()).stopSounds();
					motorSpeed = 0;
				}

			}
			// if(motorSpeed == 0)
			// player.applyForce(v, player.getWorldCenter());
		}

	}

	public void destroyJoints() {
		// TODO Auto-generated method stub
		if (!(leftWeldJoint == null)) {
			// ((BikeLevel)
			// GlobalVars.ge.getCurrentStage()).getWorld().destroyJoint(leftWeldJoint);

			leftWeldJoint = null;
		}

		if (!(rightWeldJoint == null)) {
			// ((BikeLevel)
			// GlobalVars.ge.getCurrentStage()).getWorld().destroyJoint(rightWeldJoint);

			rightWeldJoint = null;
		}

		if (!(leftAxlePrismaticJoint == null)) {
			// ((BikeLevel)
			// GlobalVars.ge.getCurrentStage()).getWorld().destroyJoint(leftAxlePrismaticJoint);

			leftAxlePrismaticJoint = null;
		}

		if (!(rightAxlePrismaticJoint == null)) {
			// ((BikeLevel)
			// GlobalVars.ge.getCurrentStage()).getWorld().destroyJoint(rightAxlePrismaticJoint);
			rightAxlePrismaticJoint = null;
		}

		if (!(leftWheelRevoluteJoint == null)) {
			((BikeLevel) GlobalVars.ge.getCurrentStage()).getWorld()
					.destroyJoint(leftWheelRevoluteJoint);
			leftWheelRevoluteJoint = null;
		}

		if (!(rightWheelRevoluteJoint == null)) {
			((BikeLevel) GlobalVars.ge.getCurrentStage()).getWorld()
					.destroyJoint(rightWheelRevoluteJoint);
			rightWheelRevoluteJoint = null;
		}
	}

	/**
	 * Add all the texture needed by this element in this function
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
		// assetPack.addTexture(imagePath);
		// assetPack.addTexture(AssetConstants.IMG_BALL_BASKET);
		// assetPack.addTexture(AssetConstants.IMG_BASKET_BALL);
		// assetPack.addTexture(AssetConstants.IMG_RUBBER_BALL);
		// assetPack.addTexture(AssetConstants.IMG_BOWL_BALL);
		// assetPack.addTexture(AssetConstants.IMG_PLAYER1_NORMAL2);
		// assetPack.addTexture(AssetConstants.IMG_CL_BKG);
		assetPack.addTexture(AssetConstants.IMG_BIKE_WHEEL);
		assetPack.addTexture(AssetConstants.IMG_AXLE);
		assetPack.addTexture(AssetConstants.IMG_RIDER);
		assetPack.addTexture(AssetConstants.IMG_BIKE_BODY);
	}

	@Override
	public void init() {

		// agerY = cam.position.y;
		// carPhysicsBody.init();
		cam = GlobalVars.ge.getScreen().cam;
		cam.position.x = (PhysicsHelper.ConvertToWorld(player.getPosition().x)
				* LevelInfo.ratioX + Gdx.graphics.getWidth() / 3f);
		cam.position.y = PhysicsHelper.ConvertToWorld(player.getPosition().y)
				* LevelInfo.ratioX + 140f * LevelInfo.ratioY;

	}

	/*
	 * @Override public void init() {
	 * 
	 * // if(Constants.ballSelected == 1) // this.image = //
	 * Helper.getImageFromAssets(AssetConstants.IMG_BASKET_BALL); //
	 * if(Constants.ballSelected == 2) // this.image = //
	 * Helper.getImageFromAssets(AssetConstants.IMG_RUBBER_BALL); //
	 * if(Constants.ballSelected == 3) // this.image =
	 * Helper.getImageFromAssets(AssetConstants.IMG_BOWL_BALL); // else
	 * this.image = Helper.getImageFromAssets(imagePath); // this.image =
	 * Helper.getImageFromAssets(imagePath); this.imageWheelLeft = Helper
	 * .getImageFromAssets(AssetConstants.IMG_BIKE_WHEEL); this.imageWheelRight
	 * = Helper .getImageFromAssets(AssetConstants.IMG_BIKE_WHEEL);
	 * this.imageLeftaxle = Helper.getImageFromAssets(AssetConstants.IMG_AXLE);
	 * this.imageRightaxle = Helper
	 * .getImageFromAssets(AssetConstants.IMG_AXLE); this.imageRider =
	 * Helper.getImageFromAssets(AssetConstants.IMG_RIDER); this.imageBikeBody =
	 * Helper .getImageFromAssets(AssetConstants.IMG_BIKE_BODY);
	 * 
	 * cam = GlobalVars.ge.getScreen().cam; // cam = new
	 * OrthographicCamera(Helper.getScreenWidth(), Helper.getScreenHeight()); //
	 * cam.position.set(Helper.getScreenWidth()/2f, Helper.getScreenHeight()/2f,
	 * 0);
	 * 
	 * // cam.position.set(0, 0, 0); // batch =
	 * GlobalVars.ge.getScreen().getBatch();
	 * 
	 * agerX = cam.position.x; agerY = cam.position.y; // carPhysicsBody.init();
	 * 
	 * leftWheelRevoluteJoint.setMotorSpeed(0);
	 * rightWheelRevoluteJoint.setMotorSpeed(0);
	 * 
	 * }
	 */

	public boolean shallAccelarate = false;
	public float accelarateVal = 0.1f;

	@Override
	public void step(long stepTime) {
		
		if (!started){
			updateCamera();
			return;
		}
		

		// if((PhysicsHelper.ConvertToWorld(leftWheel.getPosition().y)<25)||(PhysicsHelper.ConvertToWorld(rightWheel.getPosition().y)<25))
		// {
		// ((BikeLevel) GlobalVars.ge.getCurrentStage()).setInGround(true);
		// //
		// Helper.println("bike wheels are  in ground............"+PhysicsHelper.ConvertToWorld(rightWheel.getPosition().y));
		// }
		// else
		// {
		// ((BikeLevel) GlobalVars.ge.getCurrentStage()).setInGround(false);
		// //
		// Helper.println("bike wheels are not  in ground............"+leftWheel.getPosition().sub(((BikeLevel)
		// GlobalVars.ge.getCurrentStage()).h0.getBody().getPosition()).len());
		// }

		// if (shallAccelarate)
		// motorSpeed += accelarateVal;
		// Helper.println("bike wheels are  in ground............");

		long time = System.currentTimeMillis();
		updatePhysicsWorld(stepTime);
		Helper.printTest("Car update: " + (System.currentTimeMillis() - time));

		time = System.currentTimeMillis();
		checkInput();
		Helper.printTest("Check Input: " + (System.currentTimeMillis() - time));
		// cam.update();
		// batch.setProjectionMatrix(cam.combined);
		// cam.zoom = 2.5f;
		updateCamera();
	}

	float acc;
	float f;

	private void checkInput() {
		float threshold = 2;
		float tx;
		if (Gdx.input.isTouched()) {
			tx = Gdx.input.getX();
			if (tx < Gdx.graphics.getWidth() / 3f) {
				// car.accelarateVal = -0.1f;
				// car.shallAccelarate = true;
				increaseMotorSpeed(false);
			} else if (tx > Gdx.graphics.getWidth() * 2 / 3f) {
				// car.accelarateVal = 0.1f;
				// car.shallAccelarate = true;
				increaseMotorSpeed(true);
			}
		}

		// if(true)
		// return;

		// Helper.println("AccX: " + Gdx.input.getAccelerometerX());
		// Helper.println("AccY: " + Gdx.input.getAccelerometerY());
		// Helper.println("AccZ: " + Gdx.input.getAccelerometerZ());

		if (Gdx.app.getType() == ApplicationType.Android) {
			acc = Gdx.input.getAccelerometerY();
			if (Math.abs(acc) <= threshold)
				return;

			f = Math.abs(acc) - threshold;

			Helper.println("Acc: " + acc);

			if (acc < 0) // negative
			{
				// leftAxleContainer.applyLinearImpulse(0f, -0.05f,
				// leftAxleContainer.getPosition().x,
				// leftAxleContainer.getPosition().y);

				leftWheel.setAngularVelocity(0);
				leftWheel
						.applyLinearImpulse((float) (accForce
								* (Math.abs(acc) - threshold + 1) * Math
								.cos(player.getAngle() + 90
										* MathUtils.degreesToRadians)),
								(float) (accForce * Math.sin(player.getAngle()
										+ 90 * MathUtils.degreesToRadians)),
								leftWheel.getWorldCenter().x, leftWheel
										.getWorldCenter().y);

			} else if (acc > 0) // positive
			{
				// rightAxleContainer.applyLinearImpulse(0f, -0.05f,
				// rightAxleContainer.getPosition().x,
				// rightAxleContainer.getPosition().y);

				rightWheel.setAngularVelocity(0);
				rightWheel.applyLinearImpulse(
						(float) (accForce * (Math.abs(acc) - threshold) * Math
								.cos(player.getAngle() + 90
										* MathUtils.degreesToRadians)),
						(float) (accForce * Math.sin(player.getAngle() + 90
								* MathUtils.degreesToRadians)), rightWheel
								.getWorldCenter().x, rightWheel
								.getWorldCenter().y);
			}
		}
	}

	public Body getBody() {
		return player;
	}

	@Override
	public void onEvent(Point hitPoint) {

	}

	public boolean isActive() {
		return active;
	}

	Point renderPoint = new Point();

	public Point getRenderLocation() {
		this.renderPoint.setLocation(x * GameMenuInfo.ratio_w, y
				* GameMenuInfo.ratio_h);
		return renderPoint;
	}

	Joint fumeJoint;

	private void createCarModel() {
		// TODO Auto-generated method stub

		if (this.carType.compareTo(Constants.carTypeFruitTruck) == 0) {

			if (this.carType.compareTo(Constants.carTypeFruitTruck) == 0) {
				this.setCarWidth(PhysicsHelper
						.ConvertToBox(200 * GameMenuInfo.ratio_w)
						* GameMenuInfo.carSizeCoeff);
				this.setWheelRadius(PhysicsHelper
						.ConvertToBox(29 * GameMenuInfo.ratio_w)
						* GameMenuInfo.carSizeCoeff);
				this.setCarheadWidth(PhysicsHelper
						.ConvertToBox(115 * GameMenuInfo.ratio_w)
						* GameMenuInfo.carSizeCoeff);

				this.setAxleWidth(PhysicsHelper
						.ConvertToBox(2.5f * GameMenuInfo.ratio_w)
						* GameMenuInfo.carSizeCoeff);

			}
			this.createCarBody();
			this.createCarHead();
			this.createRiderBody();
			// this.createAxleContainer();
			this.createWheel();
			this.createAxle();

			carWeldJoint = this.createWeldJoint(player, carHead,
					/*player.getWorldCenter()*/carHead.getPosition());
			fireWeldJoint = this.createWeldJoint(player, riderBody,
					riderBody.getWorldCenter());
			/*
			 * leftAxlePrismaticJoint = this.createPrismaticJoint( player,
			 * leftAxle, leftAxle.getWorldCenter(), new Vector2((float) (Math
			 * .cos(MathUtils.degreesToRadians (90 - leftAxleAngle
			 * MathUtils.radiansToDegrees))), (float)
			 * (Math.sin(MathUtils.degreesToRadians (90 - leftAxleAngle
			 * MathUtils.radiansToDegrees)))));
			 */

//			leftAxlePrismaticJoint = this.createWeldJoint(player, leftAxle,
//					leftAxle.getWorldCenter());
//			rightAxlePrismaticJoint = this.createWeldJoint(player, rightAxle,
//					rightAxle.getWorldCenter());
			rightAxlePrismaticJoint = this.createPrismaticJoint(
					player,
					rightAxle,
					rightAxle.getWorldCenter(),
					new Vector2((float) (-Math
							.cos(MathUtils.degreesToRadians
									* (90 - rightAxleAngle
											* MathUtils.radiansToDegrees))),
							(float) (Math.sin(MathUtils.degreesToRadians
									* (90 - rightAxleAngle
											* MathUtils.radiansToDegrees)))));
			
			leftAxlePrismaticJoint = this.createPrismaticJoint(
					player,
					leftAxle,
					leftAxle.getWorldCenter(),
					new Vector2((float) (Math
							.cos(MathUtils.degreesToRadians
									* (90 - leftAxleAngle
											* MathUtils.radiansToDegrees))),
							(float) (Math.sin(MathUtils.degreesToRadians
									* (90 - leftAxleAngle
											* MathUtils.radiansToDegrees)))));

			/*
			 * rightAxlePrismaticJoint = this.createPrismaticJoint( carHead,
			 * rightAxle, rightAxle.getWorldCenter(), new Vector2((float) (-Math
			 * .cos(MathUtils.degreesToRadians (90 - rightAxleAngle
			 * MathUtils.radiansToDegrees))), (float)
			 * (Math.sin(MathUtils.degreesToRadians (90 - rightAxleAngle
			 * MathUtils.radiansToDegrees)))));
			 */
			leftWheelRevoluteJoint = this.createRevoluteJoint(leftWheel,
					leftAxle, leftWheel.getWorldCenter());
			rightWheelRevoluteJoint = this.createRevoluteJoint(rightWheel,
					rightAxle, rightWheel.getWorldCenter());
			// setPrisJointProperty(rightAxlePrismaticJoint, -0.1f, .05f);
			// setPrisJointProperty(leftAxlePrismaticJoint, -0.1f, .05f);
			setDensities();
		}

		else {
			this.createCarBody();
			// this.carBodyFromJson();
			// ************************ THE Rider ************************ //
			// this.createRiderBody();
			this.createWheel();
			// ************************ THE WHEELS ************************ //;
			// ************************ LEFT AXLE CONTAINER
			// ************************ //
			// this.createAxleContainer();
			// // ************************ THE AXLES ************************ //
			this.createAxle();
			if (this.carType.compareTo(Constants.carTypeKrac) == 0) {
				this.setCarWidth(PhysicsHelper
						.ConvertToBox(256 * GameMenuInfo.ratio_w)
						* GameMenuInfo.carSizeCoeff);

			} else if (this.carType.compareTo(Constants.carTypeBenga) == 0) {
				this.setCarWidth(PhysicsHelper
						.ConvertToBox(256 * GameMenuInfo.ratio_w)
						* GameMenuInfo.carSizeCoeff);
			}
			// riderWeldJoint = this.createWeldJoint(player, riderBody,
			// riderBody.getWorldCenter());
			// leftWeldJoint = this.createWeldJoint(player, leftAxleContainer,
			// leftAxleContainer.getWorldCenter());
			// rightWeldJoint= this.createWeldJoint(player, rightAxleContainer,
			// rightAxleContainer.getWorldCenter());
			leftAxlePrismaticJoint = this.createPrismaticJoint(
					body,
					leftAxle,
					leftAxle.getWorldCenter(),
					new Vector2((float) (Math
							.cos(MathUtils.degreesToRadians
									* (90 - leftAxleAngle
											* MathUtils.radiansToDegrees))),
							(float) (Math.sin(MathUtils.degreesToRadians
									* (90 - leftAxleAngle
											* MathUtils.radiansToDegrees)))));
			rightAxlePrismaticJoint = this.createPrismaticJoint(
					body,
					rightAxle,
					rightAxle.getWorldCenter(),
					new Vector2((float) (-Math
							.cos(MathUtils.degreesToRadians
									* (90 - rightAxleAngle
											* MathUtils.radiansToDegrees))),
							(float) (Math.sin(MathUtils.degreesToRadians
									* (90 - rightAxleAngle
											* MathUtils.radiansToDegrees)))));
			if (this.carType.compareTo(Constants.carTypeKrac) == 0) {
				frontAxlePrismaticJoint = this
						.createPrismaticJoint(
								body,
								frontAxle,
								frontAxle.getWorldCenter(),
								new Vector2(
										(float) (-Math
												.cos(MathUtils.degreesToRadians
														* (90 - frontAxleAngle
																* MathUtils.radiansToDegrees))),
										(float) (Math
												.sin(MathUtils.degreesToRadians
														* (90 - frontAxleAngle
																* MathUtils.radiansToDegrees)))));
				frontWheelRevoluteJoint = this.createRevoluteJoint(frontWheel,
						frontAxle, frontWheel.getWorldCenter());
			}
			leftWheelRevoluteJoint = this.createRevoluteJoint(leftWheel,
					leftAxle, leftWheel.getWorldCenter());
			rightWheelRevoluteJoint = this.createRevoluteJoint(rightWheel,
					rightAxle, rightWheel.getWorldCenter());
			// setPrisJointProperty(frontAxlePrismaticJoint, 0f, .05f);
			setDensities();
		}

	}

	public float getWheelRadius() {
		return wheelRadius;
	}

	public void setWheelRadius(float wheelRadius) {
		this.wheelRadius = wheelRadius;
	}

	private void setPrisJointProperty(PrismaticJoint joint, float lowerLimit,
			float uperLimit) {
		joint.setLimits(lowerLimit, uperLimit);
	}

	private void setDensity(Body body, float density) {
		body.getFixtureList().get(0).setDensity(density);
	}

	private void setDensities() {
		if (this.carType.compareTo(Constants.carTypeFruitTruck) == 0) {
			setDensity(leftAxle, 3);
			setDensity(rightAxle, 3);
			setDensity(leftWheel, 3);
			setDensity(rightWheel, 3);
			setDensity(riderBody, 00);
			setDensity(player, 5);
			setDensity(carHead, 05);
		} else {
			setDensity(body, 05);
			setDensity(leftAxle, 3);
			setDensity(leftWheel, 3);
			setDensity(rightAxle, 3);
			setDensity(rightWheel, 3);
			// setDensity(frontAxle, 1);
			// setDensity(frontWheel, 1);
		}
	}

	private PrismaticJoint createPrismaticJoint(Body bodyA, Body bodyB,
			Vector2 anchor, Vector2 axis) {
		// TODO Auto-generated method stub
		// PrismaticJointDef
		PrismaticJointDef prismaticJointDef = new PrismaticJointDef();
//		prismaticJointDef.lowerTranslation = 100;
//		prismaticJointDef.upperTranslation = 0;
		prismaticJointDef.enableLimit = true;
		prismaticJointDef.enableMotor = false;
		// leftAxlePrismaticJointDef.initialize(player,leftAxle,leftAxle.getWorldCenter(),
		// new
		// Vector2((float)(Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))),(float)(Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees)))));
		prismaticJointDef.initialize(bodyA, bodyB, anchor, axis);

		PrismaticJoint prismaticJoint = (PrismaticJoint) world
				.createJoint(prismaticJointDef);// as PrismaticJoint;
		prismaticJoint.setMaxMotorForce(10f);
		prismaticJoint.setMotorSpeed(10f);
		 prismaticJoint.setLimits(-0.004f, 0f);
		return prismaticJoint;

		// jointName = prismaticJoint;
		// jointName.setLimits(-0.025f, 0.025f);

	}

	private RevoluteJoint createRevoluteJoint(Body bodyA, Body bodyB,
			Vector2 anchorA) {
		// TODO Auto-generated method stub
		RevoluteJointDef revoluteJointDef = new RevoluteJointDef();
		revoluteJointDef.initialize(bodyA, bodyB, anchorA);
		// revoluteJointDef.localAnchorB.set(anchorB);
		// revoluteJointDef.upperAngle = (float) (Math.PI/2);
		// revoluteJointDef.upperAngle = (float) (-Math.PI/2);
		revoluteJointDef.enableMotor = true;
		revoluteJointDef.collideConnected = false;
		// revoluteJointDef.enableLimit = true;
		// RevoluteJoint revoluteJoint=(RevoluteJoint)
		// world.createJoint(revoluteJointDef);// as b2RevoluteJoint;
		// revoluteJoint.setMaxMotorTorque(10f);
		// jointName = revoluteJoint;
		RevoluteJoint jointName = (RevoluteJoint) world
				.createJoint(revoluteJointDef);// as b2RevoluteJoint;
		 jointName.setMaxMotorTorque(1000f);
		// jointName.setMotorSpeed(0);
		return jointName;

	}

	private WeldJoint createWeldJoint(Body bodyA, Body bodyB, Vector2 anchor) {
		// TODO Auto-generated method stub
		WeldJointDef weldJointDef = new WeldJointDef();
		weldJointDef.initialize(bodyA, bodyB, anchor);
		weldJointDef.collideConnected = true;
		WeldJoint weldJoint = (WeldJoint) world.createJoint(weldJointDef);
		return weldJoint;
	}

	// ////////////////////////
	// WeldJointDef weldJointDefRight = new WeldJointDef();
	// weldJointDefRight.initialize(player, rightAxleContainer,
	// rightAxleContainer.getWorldCenter());
	// rightWeldJoint = world.createJoint(weldJointDefRight);
	//
	// ////
	// WeldJointDef weldJointDefRider = new WeldJointDef();
	// weldJointDefRider.initialize(player, riderBody,
	// riderBody.getWorldCenter());
	// riderWeldJoint = world.createJoint(weldJointDefRider);
	//
	// }

	private void createAxle() {

		if (this.carType.compareTo(Constants.carTypeFruitTruck) == 0) {
			PhysicsBody2 pb = new PhysicsBody2(
					PhysicsHelper.ConvertToWorld((float) (leftWheel
							.getWorldCenter().x) - axleWidth / 2),// *Math.cos(/*Math.PI/2-*/leftAxleAngle))),
					PhysicsHelper.ConvertToWorld((float) (leftWheel
							.getWorldCenter().y + axleWidth / 2)),// *Math.sin(/*Math.PI/2-*/leftAxleAngle))),
					PhysicsHelper.ConvertToWorld(axleWidth), 0f,
					AssetConstants.PHY_AXLE, AssetConstants.PHY_IMG_LEFTAXLE,
					"axle", world, ((short) -1), ((short) 4), ((short) (11)),
					"leftaxle", 5, BodyType.DynamicBody);

			pb.getBody().setTransform(pb.getBody().getPosition(),
					pb.getBody().getAngle() - leftAxleAngle);
			// GlobalVars.ge.getCurrentStage().addElement(pb);
			leftAxle = pb.getBody();
			leftAxle.setUserData("leftAxle");
			PhysicsBody2 pb2 = new PhysicsBody2(
					PhysicsHelper.ConvertToWorld((float) (rightWheel
							.getWorldCenter().x - axleWidth / 2)),// *Math.cos(Math.PI/2-rightAxleAngle))),
					PhysicsHelper.ConvertToWorld((float) (rightWheel
							.getWorldCenter().y + axleWidth / 2)),// *Math.sin(Math.PI/2-rightAxleAngle))),
					PhysicsHelper.ConvertToWorld(axleWidth), 0f,
					AssetConstants.PHY_AXLE, AssetConstants.PHY_IMG_LEFTAXLE,
					"axle", world, ((short) -1), ((short) 5), ((short) (11)),
					"rightaxle", 6, BodyType.DynamicBody);
			pb2.getBody().setTransform(pb2.getBody().getPosition(),
					pb2.getBody().getAngle() + rightAxleAngle);
			// GlobalVars.ge.getCurrentStage().addElement(pb2);
			rightAxle = pb2.getBody();
			rightAxle.setUserData("rightAxle");
		} else {
			PhysicsBody2 pb = new PhysicsBody2(
					PhysicsHelper.ConvertToWorld((float) (leftWheel
							.getWorldCenter().x - axleWidth / 2
							* Math.cos(/* Math.PI/2- */leftAxleAngle))),
					PhysicsHelper.ConvertToWorld((float) (leftWheel
							.getWorldCenter().y + axleWidth / 2
							* Math.sin(/* Math.PI/2- */leftAxleAngle))),
					PhysicsHelper.ConvertToWorld(axleWidth), 0f,
					AssetConstants.PHY_AXLE, imageaxle, "axle", world, 5f, 3f,
					0f, ((short) -1), ((short) 4), ((short) (11)), "leftaxle",
					zIndex, BodyType.DynamicBody);
			pb.getBody().setTransform(pb.getBody().getPosition(),
					pb.getBody().getAngle() - leftAxleAngle);
			GlobalVars.ge.getCurrentStage().addElementZSorted(pb);
			leftAxle = pb.getBody();
			leftAxle.setUserData("leftAxle");
			// leftAxle.setFixedRotation(false);
			PhysicsBody2 pb2 = new PhysicsBody2(
					PhysicsHelper.ConvertToWorld((float) (rightWheel
							.getWorldCenter().x - axleWidth / 2
							* Math.cos(rightAxleAngle))),
					PhysicsHelper.ConvertToWorld((float) (rightWheel
							.getWorldCenter().y + axleWidth / 2
							* Math.sin(rightAxleAngle))),
					PhysicsHelper.ConvertToWorld(axleWidth), 0f,
					AssetConstants.PHY_AXLE, imageaxle, "axle", world, 5f, 3f,
					0f, ((short) -1), ((short) 5), ((short) (11)), "rightaxle",
					zIndex, BodyType.DynamicBody);
			pb2.getBody().setTransform(pb2.getBody().getPosition(),
					pb2.getBody().getAngle() + rightAxleAngle);
			GlobalVars.ge.getCurrentStage().addElementZSorted(pb2);
			rightAxle = pb2.getBody();
			rightAxle.setUserData("rightAxle");
			if (this.carType.compareTo(Constants.carTypeKrac) == 0) {
				PhysicsBody2 pb3 = new PhysicsBody2(
						PhysicsHelper.ConvertToWorld((float) (frontWheel
								.getWorldCenter().x - axleWidth / 2
								* Math.cos(frontAxleAngle))),
						PhysicsHelper.ConvertToWorld((float) (frontWheel
								.getWorldCenter().y + axleWidth / 2
								* Math.sin(frontAxleAngle))),
						PhysicsHelper.ConvertToWorld(axleWidth), 0f,
						AssetConstants.PHY_AXLE, imageaxle, "axle", world, 5f,
						3f, 0f, ((short) -1), ((short) 15), ((short) (11)),
						"frontaxle", zIndex, BodyType.DynamicBody);
				pb3.getBody().setTransform(pb3.getBody().getPosition(),
						pb3.getBody().getAngle() + frontAxleAngle);
				GlobalVars.ge.getCurrentStage().addElementZSorted(pb3);
				frontAxle = pb3.getBody();
				frontAxle.setUserData("frontAxle");
				// rightAxle.setUserData("rightAxle");
				// rightAxle.SetPosition(new
				// Vector2(carPosX/worldScale+axleContainerDistance/worldScale+axleContainerHeight/worldScale*Math.cos((90-axleAngle)*degreesToRadians),carPosY/worldScale+axleContainerDepth/worldScale+axleContainerHeight/worldScale*Math.sin((90-axleAngle)*degreesToRadians)));
				//
			}
		}

	}

	/*
	 * private void createAxleContainer() { PhysicsBody2 pb = new
	 * PhysicsBody2(PhysicsHelper
	 * .ConvertToWorld((float)(player.getPosition().x))+84f,
	 * PhysicsHelper.ConvertToWorld((float)(player.getPosition().y)),
	 * PhysicsHelper.ConvertToWorld(axleContainerWidth), 0f,
	 * AssetConstants.PHY_AXLE_CONTAINER ,
	 * AssetConstants.PHY_IMG_LEFTAXLECONTAINER, "leftaxlecontainer", world, 3f,
	 * 3f, 0.3f, ((short) -1), ((short) 2), ((short) (11)),
	 * "leftaxlecontainer",3, BodyType.DynamicBody);
	 * 
	 * // pb.getBody().getFixtureList().get(0).setDensity(50);
	 * pb.getBody().setTransform(pb.getBody().getPosition(),
	 * pb.getBody().getAngle()-leftAxleContainerAngle); //
	 * pb.getBody().setTransform((float)
	 * (pb.getBody().getPosition().x-Math.tanh(
	 * axleAngle)*axleContainerHeight),pb
	 * .getBody().getPosition().y-axleContainerHeight, pb.getBody().getAngle());
	 * 
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb); leftAxleContainer =
	 * pb.getBody(); leftAxleContainer.setUserData("leftAxleContainer");
	 * 
	 * // ************************ RIGHT AXLE CONTAINER ************************
	 * //
	 * 
	 * PhysicsBody2 pb2 = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(player
	 * .getWorldCenter().x+axleContainerDistance))+84,
	 * PhysicsHelper.ConvertToWorld((float)(player.getWorldCenter().y)),
	 * PhysicsHelper.ConvertToWorld(axleContainerWidth), 0f,
	 * AssetConstants.PHY_AXLE_CONTAINER ,
	 * AssetConstants.PHY_IMG_LEFTAXLECONTAINER, "leftaxlecontainer", world, 3f,
	 * 3f, 0.3f, ((short) -1), ((short) 3), ((short) (11)),
	 * "rightaxlecontainer",4, BodyType.DynamicBody);
	 * 
	 * // pb2.getBody().getFixtureList().get(0).setDensity(50);
	 * pb2.getBody().setTransform(pb2.getBody().getPosition(),
	 * pb2.getBody().getAngle()+rightAxleContainerAngle); //
	 * pb2.getBody().setTransform((float)
	 * (pb2.getBody().getPosition().x+Math.tanh
	 * (axleAngle)*axleContainerHeight),pb2
	 * .getBody().getPosition().y-axleContainerHeight,
	 * pb2.getBody().getAngle());
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb2); rightAxleContainer =
	 * pb2.getBody(); rightAxleContainer.setUserData("rightAxleContainer"); }
	 */

	private void createWheel() {
		

		if (this.carType.compareTo(Constants.carTypeFruitTruck) == 0) {
			PhysicsBody2 pb = new PhysicsBody2(
					PhysicsHelper.ConvertToWorld((float) (player
							.getWorldCenter().x - wheelRadius * .750f)),
					PhysicsHelper.ConvertToWorld((float) (player.getPosition().y
							- axleContainerDepth * 0 - axleContainerHeight - wheelRadius/**
					 * 
					 * Math.sin(Math.PI/2-axleAngle)
					 */
					)), PhysicsHelper.ConvertToWorld(wheelRadius * 2), 0f,
					AssetConstants.PHY_TRUCK_WHEEL, imageTruckWheel,
					"truckwheel", world, ((short) -1), ((short) 6),
					((short) (11)), "lefttruckWheel", 7, BodyType.DynamicBody);
			// new Point().getPointAtDistance(srcPoint, destinationPoint,
			// retPoint, distance)
			// pb.getBody().setTransform(pb.getBody().getPosition(),
			// pb.getBody().getAngle()-axleAngle);
			pb.getBody().getFixtureList().get(0).setFriction(10f);
			GlobalVars.ge.getCurrentStage().addElementZSorted(pb);
			leftWheel = pb.getBody();
			leftWheel.setUserData("lefttruckWheel");

			PhysicsBody2 pb2 = new PhysicsBody2(
					PhysicsHelper.ConvertToWorld((float) (leftWheel
							.getPosition().x + axleContainerDistance ))+ 12 /** LevelInfo.ratioX*/,// +2*axleContainerHeight*Math.cos(MathUtils.degreesToRadians*(90-rightAxleAngle*MathUtils.radiansToDegrees)))-wheelRadius),
					PhysicsHelper.ConvertToWorld((float) (player.getPosition().y
							- axleContainerDepth * 0 - axleContainerHeight - wheelRadius)) + 8 ,// *(float)Math.sin(MathUtils.degreesToRadians*(90-rightAxleAngle*MathUtils.radiansToDegrees)))-wheelRadius),
					PhysicsHelper.ConvertToWorld(wheelRadius * 2f), 0f,
					AssetConstants.PHY_TRUCK_WHEEL, imageTruckWheel,
					"truckwheel", world, ((short) -1), ((short) 7),
					((short) (11)), "righttruckWheel", 8, BodyType.DynamicBody);

			GlobalVars.ge.getCurrentStage().addElementZSorted(pb2);
			rightWheel = pb2.getBody();
			rightWheel.setUserData("righttruckWheel");
		}


		if(true)
			return;
		if (this.carType.compareTo(Constants.carTypeKrac) == 0) {
			PhysicsBody2 pb = new PhysicsBody2(
					PhysicsHelper
							.ConvertToWorld((float) (body.getWorldCenter().x
									- carWidth / 2 - wheelRadius * 2)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y - carWidth / 2)),
					PhysicsHelper.ConvertToWorld(wheelRadius * 2), 0f,
					AssetConstants.PHY_KRAC_WHEEL, imageCarWheel, "kracwheel",
					world, 1f, 15f, 0.2f, ((short) -1), ((short) 6),
					((short) (11)), "leftWheel", 7, BodyType.DynamicBody);
			// new Point().getPointAtDistance(srcPoint, destinationPoint,
			// retPoint, distance)
			// pb.getBody().setTransform(pb.getBody().getPosition(),
			// pb.getBody().getAngle()-axleAngle);

			GlobalVars.ge.getCurrentStage().addElementZSorted(pb);
			leftWheel = pb.getBody();
			leftWheel.setUserData("leftWheel");

			PhysicsBody2 pb2 = new PhysicsBody2(
					PhysicsHelper
							.ConvertToWorld((float) (body.getWorldCenter().x
									- carWidth / 2 + wheelRadius)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y - carWidth / 2)),
					PhysicsHelper.ConvertToWorld(wheelRadius * 2), 0f,
					AssetConstants.PHY_KRAC_WHEEL, imageCarWheel, "kracwheel",
					world, 1f, 15f, 0.2f, ((short) -1), ((short) 7),
					((short) (11)), "rightWheel", 8, BodyType.DynamicBody);

			GlobalVars.ge.getCurrentStage().addElement(pb2);
			rightWheel = pb2.getBody();
			rightWheel.setUserData("rightWheel");

			PhysicsBody2 pb3 = new PhysicsBody2(
					PhysicsHelper
							.ConvertToWorld((float) (body.getWorldCenter().x + carWidth / 2)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y - carWidth / 2)),
					PhysicsHelper.ConvertToWorld(frontWheelRadius * 2), 0f,
					AssetConstants.PHY_KRAC_WHEEL, imageCarWheel, "kracwheel",
					world, 1f, 15f, 0.2f, ((short) -1), ((short) 14),
					((short) (11)), "frontWheel", 9, BodyType.DynamicBody);
			GlobalVars.ge.getCurrentStage().addElement(pb3);
			frontWheel = pb3.getBody();
			frontWheel.setUserData("frontWheel");
		}

		if (this.carType.compareTo(Constants.carTypeBenga) == 0) {
			PhysicsBody2 pb = new PhysicsBody2(
					PhysicsHelper
							.ConvertToWorld((float) (body.getWorldCenter().x
									- carWidth / 2 - wheelRadius)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y - carWidth / 2)),
					PhysicsHelper.ConvertToWorld(wheelRadius * 2), 0f,
					AssetConstants.PHY_KRAC_WHEEL, imageCarWheel, "kracwheel",
					world, 1f, 15f, 0.2f, ((short) -1), ((short) 6),
					((short) (11)), "leftWheel", 7, BodyType.DynamicBody);
			// new Point().getPointAtDistance(srcPoint, destinationPoint,
			// retPoint, distance)
			// pb.getBody().setTransform(pb.getBody().getPosition(),
			// pb.getBody().getAngle()-axleAngle);

			GlobalVars.ge.getCurrentStage().addElement(pb);
			leftWheel = pb.getBody();
			leftWheel.setUserData("leftWheel");

			PhysicsBody2 pb2 = new PhysicsBody2(
					PhysicsHelper
							.ConvertToWorld((float) (body.getWorldCenter().x + wheelRadius)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y - carWidth / 2)),
					PhysicsHelper.ConvertToWorld(wheelRadius * 2), 0f,
					AssetConstants.PHY_KRAC_WHEEL, imageCarWheel, "kracwheel",
					world, 1f, 15f, 0.2f, ((short) -1), ((short) 7),
					((short) (11)), "rightWheel", 8, BodyType.DynamicBody);

			GlobalVars.ge.getCurrentStage().addElement(pb2);
			rightWheel = pb2.getBody();
			rightWheel.setUserData("rightWheel");
		}

		if (this.carType.compareTo(Constants.carTypeHuwwer) == 0) {
			PhysicsBody2 pb = new PhysicsBody2(
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().x
							- carWidth + wheelRadius * 1.5f)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y
							- carWidth / 2 + wheelRadius / 2)),
					PhysicsHelper.ConvertToWorld(wheelRadius * 2), 0f,
					AssetConstants.PHY_HUWWER_WHEEL, imageCarWheel,
					"huwwerwheel", world, 1f, 15f, 0.2f, ((short) -1),
					((short) 6), ((short) (11)), "leftWheel", 7,
					BodyType.DynamicBody);
			// new Point().getPointAtDistance(srcPoint, destinationPoint,
			// retPoint, distance)
			// pb.getBody().setTransform(pb.getBody().getPosition(),
			// pb.getBody().getAngle()-axleAngle);

			GlobalVars.ge.getCurrentStage().addElement(pb);
			leftWheel = pb.getBody();
			leftWheel.setUserData("leftWheel");

			PhysicsBody2 pb2 = new PhysicsBody2(
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().x
							+ carWidth / 2 - wheelRadius * .25f)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y
							- carWidth / 2 + wheelRadius / 2)),
					PhysicsHelper.ConvertToWorld(wheelRadius * 2), 0f,
					AssetConstants.PHY_HUWWER_WHEEL, imageCarWheel,
					"huwwerwheel", world, 1f, 15f, 0.2f, ((short) -1),
					((short) 7), ((short) (11)), "rightWheel", 8,
					BodyType.DynamicBody);

			GlobalVars.ge.getCurrentStage().addElement(pb2);
			rightWheel = pb2.getBody();
			rightWheel.setUserData("rightWheel");
		}

		if (this.carType.compareTo(Constants.carTypeMilitary) == 0) {
			PhysicsBody2 pb = new PhysicsBody2(
					PhysicsHelper
							.ConvertToWorld((float) (body.getWorldCenter().x
									- carWidth / 2 - wheelRadius)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y - carWidth / 2)),
					PhysicsHelper.ConvertToWorld(wheelRadius * 2), 0f,
					AssetConstants.PHY_MILITARY_WHEEL, imageCarWheel,
					"militarywheel", world, 1f, 15f, 0.2f, ((short) -1),
					((short) 6), ((short) (11)), "leftWheel", 7,
					BodyType.DynamicBody);
			// new Point().getPointAtDistance(srcPoint, destinationPoint,
			// retPoint, distance)
			// pb.getBody().setTransform(pb.getBody().getPosition(),
			// pb.getBody().getAngle()-axleAngle);

			GlobalVars.ge.getCurrentStage().addElement(pb);
			leftWheel = pb.getBody();
			leftWheel.setUserData("leftWheel");

			PhysicsBody2 pb2 = new PhysicsBody2(
					PhysicsHelper
							.ConvertToWorld((float) (body.getWorldCenter().x + carWidth / 2)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y - carWidth / 2)),
					PhysicsHelper.ConvertToWorld(wheelRadius * 2), 0f,
					AssetConstants.PHY_MILITARY_WHEEL, imageCarWheel,
					"militarywheel", world, 1f, 15f, 0.2f, ((short) -1),
					((short) 7), ((short) (11)), "rightWheel", 8,
					BodyType.DynamicBody);

			GlobalVars.ge.getCurrentStage().addElement(pb2);
			rightWheel = pb2.getBody();
			rightWheel.setUserData("rightWheel");
		}

		if (this.carType.compareTo(Constants.carTypePolice) == 0) {
			PhysicsBody2 pb = new PhysicsBody2(
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().x
							- carWidth / 2 - wheelRadius / 2)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y
							- carWidth / 2 + wheelRadius)),
					PhysicsHelper.ConvertToWorld(wheelRadius * 2), 0f,
					AssetConstants.PHY_POLICE_WHEEL, imageCarWheel,
					"policewheel", world, 1f, 15f, 0.2f, ((short) -1),
					((short) 6), ((short) (11)), "leftWheel", 7,
					BodyType.DynamicBody);
			// new Point().getPointAtDistance(srcPoint, destinationPoint,
			// retPoint, distance)
			// pb.getBody().setTransform(pb.getBody().getPosition(),
			// pb.getBody().getAngle()-axleAngle);

			GlobalVars.ge.getCurrentStage().addElement(pb);
			leftWheel = pb.getBody();
			leftWheel.setUserData("leftWheel");

			PhysicsBody2 pb2 = new PhysicsBody2(
					PhysicsHelper
							.ConvertToWorld((float) (body.getWorldCenter().x + carWidth / 2)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y
							- carWidth / 2 + wheelRadius)), PhysicsHelper
							.ConvertToWorld(wheelRadius * 2), 0f,
					AssetConstants.PHY_POLICE_WHEEL, imageCarWheel,
					"policewheel", world, 1f, 15f, 0.2f, ((short) -1),
					((short) 7), ((short) (11)), "rightWheel", 8,
					BodyType.DynamicBody);

			GlobalVars.ge.getCurrentStage().addElement(pb2);
			rightWheel = pb2.getBody();
			rightWheel.setUserData("rightWheel");
		}

		if (this.carType.compareTo(Constants.carTypeSchoolbus) == 0) {
			PhysicsBody2 pb = new PhysicsBody2(
					PhysicsHelper
							.ConvertToWorld((float) (body.getWorldCenter().x
									- carWidth + wheelRadius / 1.5f)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y - carWidth / 2)),
					PhysicsHelper.ConvertToWorld(wheelRadius * 2), 0f,
					AssetConstants.PHY_SHOOLBUS_WHEEL, imageCarWheel,
					"schoolbuswheel", world, 1f, 15f, 0.2f, ((short) -1),
					((short) 6), ((short) (11)), "leftWheel", 7,
					BodyType.DynamicBody);
			// new Point().getPointAtDistance(srcPoint, destinationPoint,
			// retPoint, distance)
			// pb.getBody().setTransform(pb.getBody().getPosition(),
			// pb.getBody().getAngle()-axleAngle);

			GlobalVars.ge.getCurrentStage().addElement(pb);
			leftWheel = pb.getBody();
			leftWheel.setUserData("leftWheel");

			PhysicsBody2 pb2 = new PhysicsBody2(
					PhysicsHelper
							.ConvertToWorld((float) (body.getWorldCenter().x
									+ carWidth / 2 + wheelRadius / 10)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y - carWidth / 2)),
					PhysicsHelper.ConvertToWorld(wheelRadius * 2), 0f,
					AssetConstants.PHY_SHOOLBUS_WHEEL, imageCarWheel,
					"schoolbuswheel", world, 1f, 15f, 0.2f, ((short) -1),
					((short) 7), ((short) (11)), "rightWheel", 8,
					BodyType.DynamicBody);

			GlobalVars.ge.getCurrentStage().addElement(pb2);
			rightWheel = pb2.getBody();
			rightWheel.setUserData("rightWheel");
		}

		if (this.carType.compareTo(Constants.carTypeSuper) == 0) {
			PhysicsBody2 pb = new PhysicsBody2(
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().x
							- carWidth / 2 - wheelRadius)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y
							- carWidth / 2 + wheelRadius / 2)),
					PhysicsHelper.ConvertToWorld(wheelRadius * 2), 0f,
					AssetConstants.PHY_SUPER_WHEEL, imageCarWheel,
					"superwheel", world, 1f, 15f, 0.2f, ((short) -1),
					((short) 6), ((short) (11)), "leftWheel", 7,
					BodyType.DynamicBody);
			// new Point().getPointAtDistance(srcPoint, destinationPoint,
			// retPoint, distance)
			// pb.getBody().setTransform(pb.getBody().getPosition(),
			// pb.getBody().getAngle()-axleAngle);

			GlobalVars.ge.getCurrentStage().addElement(pb);
			leftWheel = pb.getBody();
			leftWheel.setUserData("leftWheel");

			PhysicsBody2 pb2 = new PhysicsBody2(
					PhysicsHelper
							.ConvertToWorld((float) (body.getWorldCenter().x + carWidth / 2)),
					PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y
							- carWidth / 2 + wheelRadius / 2)), PhysicsHelper
							.ConvertToWorld(wheelRadius * 2), 0f,
					AssetConstants.PHY_SUPER_WHEEL, imageCarWheel,
					"superwheel", world, 1f, 15f, 0.2f, ((short) -1),
					((short) 7), ((short) (11)), "rightWheel", 8,
					BodyType.DynamicBody);

			GlobalVars.ge.getCurrentStage().addElement(pb2);
			rightWheel = pb2.getBody();
			rightWheel.setUserData("rightWheel");
		}
	}

	private void createRiderBody() {

		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld(player
				.getWorldCenter().x)
				- PhysicsHelper.ConvertToWorld(manWidth * 2.5f),
				PhysicsHelper.ConvertToWorld(player.getWorldCenter().y)
						- PhysicsHelper.ConvertToWorld(manWidth),
				PhysicsHelper.ConvertToWorld(manWidth), 0f,
				AssetConstants.PHY_TRUCK_FIRE,
				AssetConstants.PHY_IMG_TRUCK_FIRE, "truckfire", world,
				((short) -1), ((short) 13), ((short) (1 | 11)), "truckfire",
				zIndex, BodyType.DynamicBody);

		GlobalVars.ge.getCurrentStage().addElementZSorted(pb);
		riderBody = pb.getBody();
		riderBody.setUserData("truckfire");
	}

	private void createCarBody() {

		if (this.carType.compareTo(Constants.carTypeFruitTruck) == 0) {
			PhysicsBody2 pb = new TruckBody(this.x ,
					this.y, PhysicsHelper.ConvertToWorld(carWidth), 01f,
					AssetConstants.PHY_TRUCK_BODY, imageTruckBody,
					bodyfixturepath, world, ((short) -1), ((short) 1),
					((short) (11 | 20)), "truckbody", zIndex,
					BodyType.DynamicBody);
			
			tb = ((TruckBody)pb);
			GlobalVars.ge.getCurrentStage().addElementZSorted(pb);
			player = pb.getBody();
			player.setUserData("truckbody");
		} else {
			PhysicsBody2 pb = new PhysicsBody2(this.x, this.y,
					PhysicsHelper.ConvertToWorld(carWidth * 2), 01f,
					physicsBody, imageCarBody, bodyfixturepath, world, 1f, 3f,
					0.3f, ((short) -1), ((short) 1), ((short) 11),
					"bodyfixturepath", zIndex, BodyType.DynamicBody);
			// pb.getBody().getFixtureList().get(pb.getBody().getFixtureList().size()).setDensity(10);
			GlobalVars.ge.getCurrentStage().addElementZSorted(pb);
			body = pb.getBody();
			body.setUserData("carbody");
			player = body;
		}

	}

	TruckBody tb;
	private void createCarHead() {
		// TODO Auto-generated method stub
		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld(player
				.getWorldCenter().x)
				+ PhysicsHelper.ConvertToWorld(carWidth / 3.8f),
				PhysicsHelper.ConvertToWorld(player.getPosition().y)+10/* * LevelInfo.ratioY*/,
				130/*PhysicsHelper.ConvertToWorld(carheadWidth)*/, 01f,
				AssetConstants.PHY_TRUCK_HEAD, imageTruckHead, "truckhead",
				world, ((short) -1), ((short) 13), ((short) (1 | 11)),
				"truckhead", zIndex, BodyType.DynamicBody);

		GlobalVars.ge.getCurrentStage().addElement(pb);
		carHead = pb.getBody();
		carHead.setUserData("truckhead");
		
		if(tb != null)
			tb.setTruckHead(carHead);
	}

	/*
	 * private void createCarModel() {
	 * 
	 * // ************************ THE CAR ************************ //
	 * this.createCarBody(); this.createFumeBody(); // this.carBodyFromJson();
	 * // ************************ THE Rider ************************ //
	 * this.createRiderBody();
	 * 
	 * // ************************ LEFT AXLE CONTAINER ************************
	 * this.createAxleContainer();
	 * 
	 * // ************************ THE WHEELS ************************ //;
	 * this.createWheel();
	 * 
	 * // ************************ THE AXLES ************************ //
	 * this.createAxle(); fumeJoint = this.createWeldJoint( player, fumeBody,
	 * fumeBody.getPosition());
	 * 
	 * riderWeldJoint = this.createWeldJoint(player, riderBody,
	 * riderBody.getWorldCenter()); leftWeldJoint = this.createWeldJoint(player,
	 * leftAxleContainer, leftAxleContainer.getWorldCenter()); rightWeldJoint =
	 * this.createWeldJoint(player, rightAxleContainer,
	 * rightAxleContainer.getWorldCenter());
	 * 
	 * Vector2 axis = new Vector2(0, 1); leftAxlePrismaticJoint =
	 * this.createPrismaticJoint( leftAxleContainer, leftAxle,
	 * leftAxle.getWorldCenter(),axis); rightAxlePrismaticJoint =
	 * this.createPrismaticJoint( rightAxleContainer, rightAxle,
	 * rightAxle.getWorldCenter(),axis); leftAxlePrismaticJoint =
	 * this.createPrismaticJoint( leftAxleContainer, leftAxle,
	 * leftAxle.getWorldCenter(), new Vector2((float)
	 * (Math.cos(MathUtils.degreesToRadians (90 - leftAxleAngle *
	 * MathUtils.radiansToDegrees))), (float)
	 * (Math.sin(MathUtils.degreesToRadians (90 - leftAxleAngle
	 * MathUtils.radiansToDegrees))))); rightAxlePrismaticJoint =
	 * this.createPrismaticJoint( rightAxleContainer, rightAxle,
	 * rightAxle.getWorldCenter(), new Vector2((float)
	 * (-Math.cos(MathUtils.degreesToRadians (90 - rightAxleAngle *
	 * MathUtils.radiansToDegrees))), (float)
	 * (Math.sin(MathUtils.degreesToRadians (90 - rightAxleAngle
	 * MathUtils.radiansToDegrees)))));
	 * 
	 * leftAxlePrismaticJoint = this.createPrismaticJoint( leftAxleContainer,
	 * leftAxle, leftAxle.getWorldCenter(), new Vector2((float)
	 * (Math.cos(MathUtils.degreesToRadians (180 + leftAxleAngle *
	 * MathUtils.radiansToDegrees))), (float)
	 * (Math.sin(MathUtils.degreesToRadians (180 + leftAxleAngle
	 * MathUtils.radiansToDegrees))))); rightAxlePrismaticJoint =
	 * this.createPrismaticJoint( rightAxleContainer, rightAxle,
	 * rightAxle.getWorldCenter(), new Vector2((float)
	 * (-Math.cos(MathUtils.degreesToRadians (90 + rightAxleAngle *
	 * MathUtils.radiansToDegrees))), (float)
	 * (Math.sin(MathUtils.degreesToRadians (90 + rightAxleAngle
	 * MathUtils.radiansToDegrees)))));
	 * 
	 * leftWheelRevoluteJoint = this.createRevoluteJoint(leftWheel, leftAxle,
	 * leftWheel.getWorldCenter()); // Helper.println("joint null or not:::"+
	 * leftWheelRevoluteJoint); rightWheelRevoluteJoint =
	 * this.createRevoluteJoint(rightWheel, rightAxle,
	 * rightWheel.getWorldCenter()); // Helper.println("joint null or not:::"+
	 * rightWheelRevoluteJoint);
	 * 
	 * setDensities(); }
	 * 
	 * private void setDensity(Body body, float density) {
	 * body.getFixtureList().get(0).setDensity(density); }
	 * 
	 * private void setDensities() { // setDensity(rightAxle, 1000); //
	 * setDensity(rightWheel, 1); // setDensity(rightAxleContainer, 100); }
	 * 
	 * private PrismaticJoint createPrismaticJoint(Body bodyA, Body bodyB,
	 * Vector2 anchor, Vector2 axis) { // TODO Auto-generated method stub //
	 * PrismaticJointDef PrismaticJointDef prismaticJointDef = new
	 * PrismaticJointDef(); prismaticJointDef.lowerTranslation = 0f;
	 * prismaticJointDef.upperTranslation = 0f; prismaticJointDef.enableLimit =
	 * true; prismaticJointDef.enableMotor = true; //
	 * leftAxlePrismaticJointDef.initialize
	 * (player,leftAxle,leftAxle.getWorldCenter(), // new //
	 * Vector2((float)(Math
	 * .cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils
	 * .radiansToDegrees))
	 * ),(float)(Math.sin(MathUtils.degreesToRadians*(90-axleAngle
	 * *MathUtils.radiansToDegrees))))); prismaticJointDef.initialize(bodyA,
	 * bodyB, anchor, axis);
	 * 
	 * PrismaticJoint prismaticJoint = (PrismaticJoint) world
	 * .createJoint(prismaticJointDef);// as PrismaticJoint;
	 * prismaticJoint.setMaxMotorForce(10f); prismaticJoint.setMotorSpeed(10f);
	 * // prismaticJoint.setLimits(-.25f, 0.25f);
	 * prismaticJoint.setLimits(-0.0f, 0.0f); return prismaticJoint;
	 * 
	 * // jointName = prismaticJoint; // jointName.setLimits(-0.025f, 0.025f);
	 * 
	 * }
	 * 
	 * private RevoluteJoint createRevoluteJoint(Body bodyA, Body bodyB, Vector2
	 * anchorA) { // TODO Auto-generated method stub RevoluteJointDef
	 * revoluteJointDef = new RevoluteJointDef();
	 * revoluteJointDef.initialize(bodyA, bodyB, anchorA); //
	 * revoluteJointDef.localAnchorB.set(anchorB); revoluteJointDef.enableMotor
	 * = true; // leftWheelRevoluteJointDef.enableLimit = true; // RevoluteJoint
	 * revoluteJoint=(RevoluteJoint) // world.createJoint(revoluteJointDef);//
	 * as b2RevoluteJoint; // revoluteJoint.setMaxMotorTorque(10f); // jointName
	 * = revoluteJoint; RevoluteJoint jointName = (RevoluteJoint) world
	 * .createJoint(revoluteJointDef);// as b2RevoluteJoint;
	 * jointName.setMaxMotorTorque(10f); jointName.setMotorSpeed(10f); return
	 * jointName;
	 * 
	 * }
	 * 
	 * private WeldJoint createWeldJoint(Body bodyA, Body bodyB, Vector2 anchor)
	 * { // TODO Auto-generated method stub WeldJointDef weldJointDef = new
	 * WeldJointDef(); weldJointDef.initialize(bodyA, bodyB, anchor); WeldJoint
	 * weldJoint = (WeldJoint) world.createJoint(weldJointDef); return
	 * weldJoint; }
	 * 
	 * // //////////////////////// // WeldJointDef weldJointDefRight = new
	 * WeldJointDef(); // weldJointDefRight.initialize(player,
	 * rightAxleContainer, // rightAxleContainer.getWorldCenter()); //
	 * rightWeldJoint = world.createJoint(weldJointDefRight); // // //// //
	 * WeldJointDef weldJointDefRider = new WeldJointDef(); //
	 * weldJointDefRider.initialize(player, riderBody, //
	 * riderBody.getWorldCenter()); // riderWeldJoint =
	 * world.createJoint(weldJointDefRider); // // }
	 * 
	 * private void createAxle() {
	 * 
	 * PhysicsBody2 pb = new PhysicsBody2( PhysicsHelper.ConvertToWorld((float)
	 * (leftWheel .getWorldCenter().x - axleWidth / 2 Math.cos( Math.PI/2-
	 * leftAxleAngle))), PhysicsHelper.ConvertToWorld((float) (leftWheel
	 * .getWorldCenter().y + axleWidth / 2 Math.sin( Math.PI/2-
	 * leftAxleAngle))), PhysicsHelper.ConvertToWorld(axleWidth), 0f,
	 * AssetConstants.PHY_AXLE, AssetConstants.PHY_IMG_LEFTAXLE, "axle", world,
	 * ((short) -1), ((short) 4), ((short) (11)), "leftaxle", 5,
	 * BodyType.DynamicBody);
	 * 
	 * pb.getBody().setTransform(pb.getBody().getPosition(),
	 * pb.getBody().getAngle() - leftAxleAngle);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb); leftAxle = pb.getBody();
	 * leftAxle.setUserData("leftAxle");
	 * 
	 * PhysicsBody2 pb2 = new PhysicsBody2( PhysicsHelper.ConvertToWorld((float)
	 * (rightWheel .getWorldCenter().x - axleWidth / 2 Math.cos(Math.PI / 2 -
	 * rightAxleAngle))), PhysicsHelper.ConvertToWorld((float) (rightWheel
	 * .getWorldCenter().y - axleWidth / 2 Math.cos(Math.PI / 2 -
	 * rightAxleAngle))), PhysicsHelper.ConvertToWorld(axleWidth), 0f,
	 * AssetConstants.PHY_AXLE, AssetConstants.PHY_IMG_LEFTAXLE, "axle", world,
	 * ((short) -1), ((short) 5), ((short) (11)), "rightaxle", 6,
	 * BodyType.DynamicBody);
	 * 
	 * pb2.getBody().setTransform(pb2.getBody().getPosition(),
	 * pb2.getBody().getAngle() + rightAxleAngle);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb2); rightAxle =
	 * pb2.getBody(); rightAxle.setUserData("rightAxle"); }
	 * 
	 * private void createAxleContainer() { // TODO Auto-generated method stub
	 * // PolygonShape leftAxleContainerShape = new PolygonShape(); //
	 * leftAxleContainerShape
	 * .setAsBox(axleContainerWidth,axleContainerHeight,new //
	 * Vector2(axleContainerWidth,axleContainerHeight),axleAngle); // // //
	 * leftAxleContainerShape.setAsBox(axleContainerWidth,axleContainerHeight);
	 * // FixtureDef leftAxleContainerFixture = new FixtureDef(); //
	 * leftAxleContainerFixture.density=1f; //
	 * leftAxleContainerFixture.friction=3f; //
	 * leftAxleContainerFixture.restitution=0.3f; // //
	 * leftAxleContainerFixture.filter.groupIndex=-1; //
	 * leftAxleContainerFixture.filter.categoryBits=2; // //
	 * leftAxleContainerFixture.filter.maskBits = 11; // //
	 * leftAxleContainerFixture.shape=leftAxleContainerShape; // BodyDef
	 * leftAxleContainerDef = new BodyDef(); // //
	 * leftAxleContainerDef.position.set(new //
	 * Vector2((float)(player.getPosition
	 * ().x-2*axleContainerH*Math.cos(MathUtils
	 * .degreesToRadians*(90-axleAngle*MathUtils
	 * .radiansToDegrees))-axleContainerDistance
	 * -axleContainerDepth*Math.cos(MathUtils
	 * .degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))), // // //
	 * (float
	 * )(player.getPosition().y-axleContainerDepth+0*axleContainerHeight*Math
	 * .sin
	 * (MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))
	 * ))); // leftAxleContainerDef.position.set(carWidth + //
	 * (float)(player.getPosition
	 * ().x-axleContainerDistance-axleContainerHeight/2
	 * *Math.cos(MathUtils.degreesToRadians
	 * *(90-axleAngle*MathUtils.radiansToDegrees
	 * ))),(float)(player.getPosition().
	 * y-axleContainerDepth-axleContainerHeight/
	 * 2*(float)Math.sin(MathUtils.degreesToRadians
	 * *(90-axleAngle*MathUtils.radiansToDegrees)))); //
	 * leftAxleContainerDef.type=BodyType.DynamicBody; //
	 * leftAxleContainerDef.angle = -axleAngle; // leftAxleContainer =
	 * world.createBody(leftAxleContainerDef); //
	 * leftAxleContainer.createFixture(leftAxleContainerFixture); //
	 * leftAxleContainer.setFixedRotation(false); PhysicsBody2 pb = new
	 * PhysicsBody2( PhysicsHelper .ConvertToWorld((float)
	 * (player.getWorldCenter().x -
	 * axleContainerDistance-axleContainerHeight*Math
	 * .cos(MathUtils.degreesToRadians
	 * *(90-axleAngle*MathUtils.radiansToDegrees))- axleContainerWidth / 2
	 * Math.cos( Math.PI/2- leftAxleContainerAngle))),
	 * PhysicsHelper.ConvertToWorld((float) (player.getWorldCenter().y -
	 * axleContainerDepth
	 * -axleContainerHeight/2*(float)Math.sin(MathUtils.degreesToRadians
	 * *(90-axleAngle*MathUtils.radiansToDegrees))+ axleContainerWidth / 2 *
	 * Math.sin( Math.PI/2- leftAxleContainerAngle))),
	 * PhysicsHelper.ConvertToWorld(axleContainerWidth), 0f,
	 * AssetConstants.PHY_AXLE_CONTAINER,
	 * AssetConstants.PHY_IMG_LEFTAXLECONTAINER, "leftaxlecontainer", world,
	 * ((short) -1), ((short) 2), ((short) (11)), "leftaxlecontainer", 3,
	 * BodyType.DynamicBody);
	 * 
	 * // pb.getBody().getFixtureList().get(0).setDensity(50);
	 * pb.getBody().setTransform(pb.getBody().getPosition(),
	 * pb.getBody().getAngle() - leftAxleContainerAngle); //
	 * pb.getBody().setTransform((float) //
	 * (pb.getBody().getPosition().x-Math.tanh
	 * (axleAngle)*axleContainerHeight),pb
	 * .getBody().getPosition().y-axleContainerHeight, //
	 * pb.getBody().getAngle());
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb); leftAxleContainer =
	 * pb.getBody(); leftAxleContainer.setUserData("leftAxleContainer");
	 * 
	 * // ************************ RIGHT AXLE CONTAINER //
	 * ************************ // // PolygonShape rightAxleContainerShape = new
	 * PolygonShape(); //
	 * rightAxleContainerShape.setAsBox(axleContainerWidth,axleContainerHeight
	 * ,new // Vector2(axleContainerWidth,axleContainerHeight),-axleAngle); //
	 * FixtureDef rightAxleContainerFixture = new FixtureDef(); //
	 * rightAxleContainerFixture.density=1f; //
	 * rightAxleContainerFixture.friction=3; //
	 * rightAxleContainerFixture.restitution=0.3f; // //
	 * rightAxleContainerFixture.filter.groupIndex=-1; //
	 * rightAxleContainerFixture.filter.categoryBits=3; // //
	 * rightAxleContainerFixture.filter.maskBits = 11; // //
	 * rightAxleContainerFixture.shape=rightAxleContainerShape; // BodyDef
	 * rightAxleContainerDef = new BodyDef(); // // //
	 * rightAxleContainerDef.position.set(new //
	 * Vector2((float)(player.getPosition
	 * ().x+2*axleContainerWidth*Math.cos(90-axleAngle
	 * )+axleContainerDistance+axleContainerDepth
	 * *Math.cos(MathUtils.degreesToRadians
	 * *(90-axleAngle*MathUtils.radiansToDegrees))), // // //
	 * (float)(player.getPosition
	 * ().y-axleContainerDepth+0*axleContainerHeight*Math
	 * .sin(MathUtils.degreesToRadians
	 * *(90-axleAngle*MathUtils.radiansToDegrees))))); //
	 * rightAxleContainerDef.position
	 * .set(carWidth+(float)(player.getPosition().x
	 * +axleContainerDistance+axleContainerHeight
	 * /2*Math.cos(MathUtils.degreesToRadians
	 * *(90-axleAngle*MathUtils.radiansToDegrees
	 * ))),(float)(player.getPosition().
	 * y-axleContainerDepth-axleContainerHeight/
	 * 2*(float)Math.sin(MathUtils.degreesToRadians
	 * *(90-axleAngle*MathUtils.radiansToDegrees)))); //
	 * rightAxleContainerDef.type=BodyType.DynamicBody; //
	 * rightAxleContainerDef.angle = axleAngle; // rightAxleContainer =
	 * world.createBody(rightAxleContainerDef); //
	 * rightAxleContainer.createFixture(rightAxleContainerFixture); //
	 * rightAxleContainer.setFixedRotation(false);
	 * 
	 * PhysicsBody2 pb2 = new PhysicsBody2( PhysicsHelper.ConvertToWorld((float)
	 * (player.getWorldCenter().x + axleContainerDistance
	 * +axleContainerHeight*Math.cos( MathUtils .degreesToRadians*(90 -axleAngle
	 * *MathUtils.radiansToDegrees)) - axleContainerWidth / 2 Math.cos(
	 * Math.PI/2- rightAxleContainerAngle))),
	 * PhysicsHelper.ConvertToWorld((float) (player.getWorldCenter().y -
	 * axleContainerDepth - axleContainerHeight/2*(float)Math .
	 * sin(MathUtils.degreesToRadians *(90-axleAngle*MathUtils.
	 * radiansToDegrees))- axleContainerWidth / 2 Math.cos( Math.PI/2-
	 * rightAxleContainerAngle))),
	 * PhysicsHelper.ConvertToWorld(axleContainerWidth), 0f,
	 * AssetConstants.PHY_AXLE_CONTAINER,
	 * AssetConstants.PHY_IMG_LEFTAXLECONTAINER, "leftaxlecontainer", world,
	 * ((short) -1), ((short) 3), ((short) (11)), "rightaxlecontainer", 4,
	 * BodyType.DynamicBody);
	 * 
	 * // pb2.getBody().getFixtureList().get(0).setDensity(50);
	 * pb2.getBody().setTransform(pb2.getBody().getPosition(),
	 * pb2.getBody().getAngle() + rightAxleContainerAngle); //
	 * pb2.getBody().setTransform((float) //
	 * (pb2.getBody().getPosition().x+Math.
	 * tanh(axleAngle)*axleContainerHeight),pb2
	 * .getBody().getPosition().y-axleContainerHeight, //
	 * pb2.getBody().getAngle());
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb2); rightAxleContainer =
	 * pb2.getBody(); rightAxleContainer.setUserData("rightAxleContainer"); }
	 * 
	 * private void createWheel() { if
	 * (this.carType.compareTo(Constants.carTypeKrac)==0) { PhysicsBody2 pb =
	 * new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter(
	 * ).x- carWidth/2-wheelRadius*2)),
	 * PhysicsHelper.ConvertToWorld((float)(body
	 * .getWorldCenter().y-carWidth/2)),
	 * PhysicsHelper.ConvertToWorld(wheelRadius*2), 0f,
	 * AssetConstants.PHY_KRAC_WHEEL , imageCarWheel, "kracwheel", world, 1f,
	 * 15f, 0.2f, ((short) -1), ((short) 6), ((short) (11)), "leftWheel",7,
	 * BodyType.DynamicBody); // new Point().getPointAtDistance(srcPoint,
	 * destinationPoint, retPoint, distance) //
	 * pb.getBody().setTransform(pb.getBody().getPosition(),
	 * pb.getBody().getAngle()-axleAngle);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb); leftWheel = pb.getBody();
	 * leftWheel.setUserData("leftWheel");
	 * 
	 * PhysicsBody2 pb2 = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body
	 * .getWorldCenter().x-carWidth/2+wheelRadius)),
	 * PhysicsHelper.ConvertToWorld
	 * ((float)(body.getWorldCenter().y-carWidth/2)),
	 * PhysicsHelper.ConvertToWorld(wheelRadius*2), 0f,
	 * AssetConstants.PHY_KRAC_WHEEL , imageCarWheel, "kracwheel", world, 1f,
	 * 15f, 0.2f, ((short) -1), ((short) 7), ((short) (11)), "rightWheel",8,
	 * BodyType.DynamicBody);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb2); rightWheel =
	 * pb2.getBody(); rightWheel.setUserData("rightWheel");
	 * 
	 * 
	 * PhysicsBody2 pb3 = new PhysicsBody2( PhysicsHelper.ConvertToWorld((float)
	 * (body.getWorldCenter().x + carWidth / 2)),
	 * PhysicsHelper.ConvertToWorld((float) (body.getWorldCenter().y - carWidth
	 * / 2)), PhysicsHelper.ConvertToWorld(frontWheelRadius * 2), 0f,
	 * AssetConstants.PHY_KRAC_WHEEL, imageCarWheel, "kracwheel", world, 1f,
	 * 15f, 0.2f, ((short) -1), ((short) 14), ((short) (11)), "frontWheel", 9,
	 * BodyType.DynamicBody); GlobalVars.ge.getCurrentStage().addElement(pb3);
	 * frontWheel = pb3.getBody(); frontWheel.setUserData("frontWheel"); }
	 * 
	 * if (this.carType.compareTo(Constants.carTypeBenga)==0) { PhysicsBody2 pb
	 * = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter
	 * ().x- carWidth/2-wheelRadius)),
	 * PhysicsHelper.ConvertToWorld((float)(body.
	 * getWorldCenter().y-carWidth/2)),
	 * PhysicsHelper.ConvertToWorld(wheelRadius*2), 0f,
	 * AssetConstants.PHY_KRAC_WHEEL , imageCarWheel, "kracwheel", world, 1f,
	 * 15f, 0.2f, ((short) -1), ((short) 6), ((short) (11)), "leftWheel",7,
	 * BodyType.DynamicBody); // new Point().getPointAtDistance(srcPoint,
	 * destinationPoint, retPoint, distance) //
	 * pb.getBody().setTransform(pb.getBody().getPosition(),
	 * pb.getBody().getAngle()-axleAngle);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb); leftWheel = pb.getBody();
	 * leftWheel.setUserData("leftWheel");
	 * 
	 * PhysicsBody2 pb2 = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body
	 * .getWorldCenter().x+wheelRadius)),
	 * PhysicsHelper.ConvertToWorld((float)(body
	 * .getWorldCenter().y-carWidth/2)),
	 * PhysicsHelper.ConvertToWorld(wheelRadius*2), 0f,
	 * AssetConstants.PHY_KRAC_WHEEL , imageCarWheel, "kracwheel", world, 1f,
	 * 15f, 0.2f, ((short) -1), ((short) 7), ((short) (11)), "rightWheel",8,
	 * BodyType.DynamicBody);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb2); rightWheel =
	 * pb2.getBody(); rightWheel.setUserData("rightWheel"); }
	 * 
	 * if (this.carType.compareTo(Constants.carTypeHuwwer)==0) { PhysicsBody2 pb
	 * = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter
	 * ().x- carWidth+wheelRadius*1.5f)),
	 * PhysicsHelper.ConvertToWorld((float)(body
	 * .getWorldCenter().y-carWidth/2+wheelRadius/2)),
	 * PhysicsHelper.ConvertToWorld(wheelRadius*2), 0f,
	 * AssetConstants.PHY_HUWWER_WHEEL , imageCarWheel, "huwwerwheel", world,
	 * 1f, 15f, 0.2f, ((short) -1), ((short) 6), ((short) (11)), "leftWheel",7,
	 * BodyType.DynamicBody); // new Point().getPointAtDistance(srcPoint,
	 * destinationPoint, retPoint, distance) //
	 * pb.getBody().setTransform(pb.getBody().getPosition(),
	 * pb.getBody().getAngle()-axleAngle);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb); leftWheel = pb.getBody();
	 * leftWheel.setUserData("leftWheel");
	 * 
	 * PhysicsBody2 pb2 = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body
	 * .getWorldCenter().x+carWidth/2-wheelRadius*.25f)),
	 * PhysicsHelper.ConvertToWorld
	 * ((float)(body.getWorldCenter().y-carWidth/2+wheelRadius/2)),
	 * PhysicsHelper.ConvertToWorld(wheelRadius*2), 0f,
	 * AssetConstants.PHY_HUWWER_WHEEL , imageCarWheel, "huwwerwheel", world,
	 * 1f, 15f, 0.2f, ((short) -1), ((short) 7), ((short) (11)), "rightWheel",8,
	 * BodyType.DynamicBody);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb2); rightWheel =
	 * pb2.getBody(); rightWheel.setUserData("rightWheel"); }
	 * 
	 * if (this.carType.compareTo(Constants.carTypeMilitary)==0) { PhysicsBody2
	 * pb = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter
	 * ().x- carWidth/2-wheelRadius)),
	 * PhysicsHelper.ConvertToWorld((float)(body.
	 * getWorldCenter().y-carWidth/2)),
	 * PhysicsHelper.ConvertToWorld(wheelRadius*2), 0f,
	 * AssetConstants.PHY_MILITARY_WHEEL , imageCarWheel, "militarywheel",
	 * world, 1f, 15f, 0.2f, ((short) -1), ((short) 6), ((short) (11)),
	 * "leftWheel",7, BodyType.DynamicBody); // new
	 * Point().getPointAtDistance(srcPoint, destinationPoint, retPoint,
	 * distance) // pb.getBody().setTransform(pb.getBody().getPosition(),
	 * pb.getBody().getAngle()-axleAngle);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb); leftWheel = pb.getBody();
	 * leftWheel.setUserData("leftWheel");
	 * 
	 * PhysicsBody2 pb2 = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body
	 * .getWorldCenter().x+carWidth/2)),
	 * PhysicsHelper.ConvertToWorld((float)(body
	 * .getWorldCenter().y-carWidth/2)),
	 * PhysicsHelper.ConvertToWorld(wheelRadius*2), 0f,
	 * AssetConstants.PHY_MILITARY_WHEEL , imageCarWheel, "militarywheel",
	 * world, 1f, 15f, 0.2f, ((short) -1), ((short) 7), ((short) (11)),
	 * "rightWheel",8, BodyType.DynamicBody);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb2); rightWheel =
	 * pb2.getBody(); rightWheel.setUserData("rightWheel"); }
	 * 
	 * if (this.carType.compareTo(Constants.carTypePolice)==0) { PhysicsBody2 pb
	 * = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter
	 * ().x- carWidth/2-wheelRadius/2)),
	 * PhysicsHelper.ConvertToWorld((float)(body
	 * .getWorldCenter().y-carWidth/2+wheelRadius)),
	 * PhysicsHelper.ConvertToWorld(wheelRadius*2), 0f,
	 * AssetConstants.PHY_POLICE_WHEEL , imageCarWheel, "policewheel", world,
	 * 1f, 15f, 0.2f, ((short) -1), ((short) 6), ((short) (11)), "leftWheel",7,
	 * BodyType.DynamicBody); // new Point().getPointAtDistance(srcPoint,
	 * destinationPoint, retPoint, distance) //
	 * pb.getBody().setTransform(pb.getBody().getPosition(),
	 * pb.getBody().getAngle()-axleAngle);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb); leftWheel = pb.getBody();
	 * leftWheel.setUserData("leftWheel");
	 * 
	 * PhysicsBody2 pb2 = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body
	 * .getWorldCenter().x+carWidth/2)),
	 * PhysicsHelper.ConvertToWorld((float)(body
	 * .getWorldCenter().y-carWidth/2+wheelRadius)),
	 * PhysicsHelper.ConvertToWorld(wheelRadius*2), 0f,
	 * AssetConstants.PHY_POLICE_WHEEL , imageCarWheel, "policewheel", world,
	 * 1f, 15f, 0.2f, ((short) -1), ((short) 7), ((short) (11)), "rightWheel",8,
	 * BodyType.DynamicBody);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb2); rightWheel =
	 * pb2.getBody(); rightWheel.setUserData("rightWheel"); }
	 * 
	 * if (this.carType.compareTo(Constants.carTypeSchoolbus)==0) { PhysicsBody2
	 * pb = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter
	 * ().x- carWidth+wheelRadius/1.5f)),
	 * PhysicsHelper.ConvertToWorld((float)(body
	 * .getWorldCenter().y-carWidth/2)),
	 * PhysicsHelper.ConvertToWorld(wheelRadius*2), 0f,
	 * AssetConstants.PHY_SHOOLBUS_WHEEL , imageCarWheel, "schoolbuswheel",
	 * world, 1f, 15f, 0.2f, ((short) -1), ((short) 6), ((short) (11)),
	 * "leftWheel",7, BodyType.DynamicBody); // new
	 * Point().getPointAtDistance(srcPoint, destinationPoint, retPoint,
	 * distance) // pb.getBody().setTransform(pb.getBody().getPosition(),
	 * pb.getBody().getAngle()-axleAngle);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb); leftWheel = pb.getBody();
	 * leftWheel.setUserData("leftWheel");
	 * 
	 * PhysicsBody2 pb2 = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body
	 * .getWorldCenter().x+carWidth/2+wheelRadius/10)),
	 * PhysicsHelper.ConvertToWorld
	 * ((float)(body.getWorldCenter().y-carWidth/2)),
	 * PhysicsHelper.ConvertToWorld(wheelRadius*2), 0f,
	 * AssetConstants.PHY_SHOOLBUS_WHEEL , imageCarWheel, "schoolbuswheel",
	 * world, 1f, 15f, 0.2f, ((short) -1), ((short) 7), ((short) (11)),
	 * "rightWheel",8, BodyType.DynamicBody);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb2); rightWheel =
	 * pb2.getBody(); rightWheel.setUserData("rightWheel"); }
	 * 
	 * if (this.carType.compareTo(Constants.carTypeSuper)==0) { PhysicsBody2 pb
	 * = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body.getWorldCenter
	 * ().x- carWidth/2-wheelRadius)),
	 * PhysicsHelper.ConvertToWorld((float)(body.
	 * getWorldCenter().y-carWidth/2+wheelRadius/2)),
	 * PhysicsHelper.ConvertToWorld(wheelRadius*2), 0f,
	 * AssetConstants.PHY_SUPER_WHEEL , imageCarWheel, "superwheel", world, 1f,
	 * 15f, 0.2f, ((short) -1), ((short) 6), ((short) (11)), "leftWheel",7,
	 * BodyType.DynamicBody); // new Point().getPointAtDistance(srcPoint,
	 * destinationPoint, retPoint, distance) //
	 * pb.getBody().setTransform(pb.getBody().getPosition(),
	 * pb.getBody().getAngle()-axleAngle);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb); leftWheel = pb.getBody();
	 * leftWheel.setUserData("leftWheel");
	 * 
	 * PhysicsBody2 pb2 = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld((float)(body
	 * .getWorldCenter().x+carWidth/2)),
	 * PhysicsHelper.ConvertToWorld((float)(body
	 * .getWorldCenter().y-carWidth/2+wheelRadius/2)),
	 * PhysicsHelper.ConvertToWorld(wheelRadius*2), 0f,
	 * AssetConstants.PHY_SUPER_WHEEL , imageCarWheel, "superwheel", world, 1f,
	 * 15f, 0.2f, ((short) -1), ((short) 7), ((short) (11)), "rightWheel",8,
	 * BodyType.DynamicBody);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb2); rightWheel =
	 * pb2.getBody(); rightWheel.setUserData("rightWheel"); }
	 * 
	 * 
	 * }
	 * 
	 * public Body getFumeBody() { return fumeBody; }
	 * 
	 * private void createFumeBody() {
	 * 
	 * PhysicsBody2 pb2 = new PhysicsBody2(Helper.p2w(player.getPosition().x) +
	 * 5 * LevelInfo.ratioX, Helper.p2w(player.getPosition().y )+27*
	 * LevelInfo.ratioX, 0.01f, 0f, AssetConstants.PHY_AXLE,
	 * AssetConstants.PHY_IMG_LEFTAXLE, "axle", world, ((short) -1), ((short)
	 * 5), ((short) (11)), "fume", 6, BodyType.DynamicBody);
	 * 
	 * // pb2.getBody().setTransform(pb2.getBody().getPosition(), //
	 * pb2.getBody().getAngle() + rightAxleAngle);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb2);
	 * 
	 * pb2.getBody().setUserData("fume"); fumeBody = pb2.getBody();
	 * ((BikeLevel)(
	 * GlobalVars.ge.getCurrentStage())).setFumeBody(pb2.getBody()); }
	 * 
	 * 
	 * private void createRiderBody() {
	 * 
	 * PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld(player
	 * .getWorldCenter().x) - PhysicsHelper.ConvertToWorld(manWidth / 2),
	 * PhysicsHelper.ConvertToWorld(player.getWorldCenter().y) -
	 * PhysicsHelper.ConvertToWorld(manHeight / 2),
	 * PhysicsHelper.ConvertToWorld(manWidth), 0f, AssetConstants.PHY_BIKEMAN,
	 * AssetConstants.PHY_IMG_BIKEMAN, "bikeman", world, ((short) -1), ((short)
	 * 13), ((short) (1 | 11)), "bikeman", 2, BodyType.DynamicBody);
	 * 
	 * GlobalVars.ge.getCurrentStage().addElement(pb); riderBody = pb.getBody();
	 * riderBody.setUserData("riderBody"); }
	 * 
	 * private void createCarBody() { PhysicsBody2 pb = new PhysicsBody2(this.x,
	 * this.y, PhysicsHelper.ConvertToWorld(carWidth*2), 01f, physicsBody ,
	 * imageCarBody, bodyfixturepath, world, 1f, 3f, 0.3f, ((short) -1),
	 * ((short) 1), ((short) 11), "kracbody", 2, BodyType.DynamicBody); //
	 * pb.getBody
	 * ().getFixtureList().get(pb.getBody().getFixtureList().size()).setDensity
	 * (10); GlobalVars.ge.getCurrentStage().addElement(pb); player =
	 * pb.getBody(); player.setUserData("carbody"); body = player;
	 * 
	 * }
	 */
	// Fixed foints
	// DistanceJointDef leftAxleContainerDistanceJointDef=new
	// DistanceJointDef();

	// RevoluteJointDef leftAxleContainerRevoluteJointDef=new
	// RevoluteJointDef();
	// leftAxleContainerRevoluteJointDef.initialize(player,leftAxleContainer,leftAxleContainer.getPosition());
	// leftAxleContainerRevoluteJointDef.enableMotor=true;
	// leftAxleContainerRevoluteJointDef.enableLimit = true;
	// leftAxleContainerRevoluteJoint=(RevoluteJoint)
	// world.createJoint(leftAxleContainerRevoluteJointDef);// as
	// b2RevoluteJoint;
	// leftAxleContainerRevoluteJoint.setMaxMotorTorque(100f);
	// leftAxleContainerRevoluteJoint.setLimits(-1f,1f);

	// leftAxleContainerDistanceJointDef.initialize(player, leftAxleContainer,
	// new Vector2
	// (player.getPosition().x-carWidth,player.getPosition().y-carHeight),
	// leftAxleContainer.getPosition());
	// leftAxleContainerDistanceJointDef.collideConnected = true;
	// leftAxleContainerDistanceJointDef.frequencyHz = 4.0f;
	// leftAxleContainerDistanceJointDef.dampingRatio = 0.5f;
	// // leftAxleContainerDistanceJoint.setLength(.01f);
	// leftAxleContainerDistanceJoint=(DistanceJoint)
	// world.createJoint(leftAxleContainerDistanceJointDef);// as
	// b2RevoluteJoint;

	// DistanceJointDef rightAxleContainerDistanceJointDef=new
	// DistanceJointDef();
	// rightAxleContainerDistanceJointDef.initialize(player, rightAxleContainer,
	// player.getPosition(), rightAxleContainer.getPosition());
	// rightAxleContainerDistanceJointDef.collideConnected = true;
	// leftAxleContainerDistanceJointDef.dampingRatio = .1f;
	// rightAxleContainerDistanceJoint.setLength(.1f);
	// rightAxleContainerDistanceJoint=(DistanceJoint)
	// world.createJoint(rightAxleContainerDistanceJointDef);// as
	// b2RevoluteJoint;

	/*
	 * RopeJointDef ropeJointDef = new RopeJointDef(); ropeJointDef.bodyA =
	 * player; ropeJointDef.bodyB = riderBody; ropeJointDef.localAnchorA.set(new
	 * Vector2(0,0)); ropeJointDef.localAnchorB.set(new Vector2(0,0));
	 * ropeJointDef.maxLength = .10f; ropeJointDef.collideConnected = true;
	 * world.createJoint(ropeJointDef);
	 */

	// RopeJointDef ropeJointDef = new RopeJointDef();
	// ropeJointDef.bodyA = riderBody;
	// ropeJointDef.bodyB = player;
	// ropeJointDef.localAnchorB.set(new Vector2(0,0));
	// ropeJointDef.localAnchorA.set(new
	// Vector2(PhysicsHelper.ConvertToBox(manWidth/2),PhysicsHelper.ConvertToBox(manHeight/2)));
	// ropeJointDef.maxLength = 0.50f;
	// ropeJointDef.collideConnected = true;
	// world.createJoint(ropeJointDef);

	// FrictionJointDef frictionJointDef = new FrictionJointDef();
	// frictionJointDef.bodyA = riderBody;
	// frictionJointDef.bodyB = player;
	// frictionJointDef.localAnchorB.set(new Vector2(0,0));
	// frictionJointDef.localAnchorA.set(new
	// Vector2(PhysicsHelper.ConvertToBox(manWidth/2),PhysicsHelper.ConvertToBox(manHeight/2)));
	// frictionJointDef.initialize(player, riderBody, riderBody.getPosition());
	// frictionJointDef.maxForce = 0.5f;
	// frictionJointDef.maxTorque = 0.5f;
	// frictionJointDef.collideConnected =true;
	// world.createJoint(frictionJointDef);

	// FrictionJointDef frictionJointDef = new FrictionJointDef();
	// frictionJointDef.bodyA = riderBody;
	// frictionJointDef.bodyB = player;
	// frictionJointDef.localAnchorB.set(new Vector2(0,0));
	// frictionJointDef.localAnchorA.set(new
	// Vector2(PhysicsHelper.ConvertToBox(manWidth/2),PhysicsHelper.ConvertToBox(manHeight/2)));
	// frictionJointDef.initialize(player, riderBody, riderBody.getPosition());
	// frictionJointDef.maxForce = 0.5f;
	// frictionJointDef.maxTorque = 0.5f;
	// frictionJointDef.collideConnected =true;
	// world.createJoint(frictionJointDef);

	// PrismaticJointDef
	// PrismaticJointDef riderPrismaticJointDef=new PrismaticJointDef();
	// riderPrismaticJointDef.lowerTranslation=0;
	// riderPrismaticJointDef.upperTranslation=0;
	// riderPrismaticJointDef.enableLimit=true;
	// riderPrismaticJointDef.enableMotor=true;
	// riderPrismaticJointDef.initialize(player,riderBody,riderBody.getWorldCenter(),
	// new
	// Vector2((float)(Math.cos(MathUtils.degreesToRadians*(90-riderAngle*MathUtils.radiansToDegrees))),(float)(-Math.sin(MathUtils.degreesToRadians*(90-riderAngle*MathUtils.radiansToDegrees)))));
	// riderPrismaticJoint=(PrismaticJoint)
	// world.createJoint(riderPrismaticJointDef);// as b2PrismaticJoint;
	// riderPrismaticJoint.setMaxMotorForce(10f);
	// riderPrismaticJoint.setMotorSpeed(10f);
	// riderPrismaticJoint.setLimits(-.25f, .25f);

	// ************************ MERGING ALL TOGETHER ************************ //
	// PhysicsHelper.MakeFixture(player, Shape.Type.Polygon, width, height, 5f,
	// 0.3f,3f);
	// player=world.createBody(carBodyDef);
	// player.createFixture(carFixture);
	// player.createFixture(leftAxleContainerFixture);
	// player.createFixture(rightAxleContainerFixture);
	// player.setFixedRotation(false);
	// player.getf
	// player.setType(BodyType.StaticBody);
	// player.setUserData("car");
	// player.resetMassData();

	public Car(float x, float y, float width, float height, float angle,
			String carType, World world, boolean b, boolean dummy, int zIndex) {
		// super(x , y, width,height, 1);

		this.carType = carType;
		this.world = world;
		this.x = x;
		this.y = y;
		this.pos.set(this.x, this.y);
		this.width = width;
		this.height = height;
		this.zIndex = zIndex;

		if (this.carType.compareTo(Constants.carTypeKrac) == 0) {
			physicsBody = AssetConstants.PHY_KRAC_BODY;
			bodyfixturepath = "kracbody";

			imageCarBody = AssetConstants.PHY_IMG_KRAC_BODY;
			imageCarWheel = AssetConstants.PHY_IMG_KRAC_WHEEL;
			imageaxle = AssetConstants.PHY_IMG_LEFTAXLE;
		}

		if (this.carType.compareTo(Constants.carTypeHuwwer) == 0) {
			physicsBody = AssetConstants.PHY_HUWWER_BODY;
			bodyfixturepath = "huwwerbody";

			imageCarBody = AssetConstants.PHY_IMG_HUWWER_BODY;
			imageCarWheel = AssetConstants.PHY_IMG_HUWWER_WHEEL;
			imageaxle = AssetConstants.PHY_IMG_LEFTAXLE;
		}

		if (this.carType.compareTo(Constants.carTypeBenga) == 0) {
			physicsBody = AssetConstants.PHY_BENGA_BODY;
			bodyfixturepath = "bengabody";

			imageCarBody = AssetConstants.PHY_IMG_BENGA_BODY;
			imageCarWheel = AssetConstants.PHY_IMG_BENGA_WHEEL;
			imageaxle = AssetConstants.PHY_IMG_LEFTAXLE;
		}

		if (this.carType.compareTo(Constants.carTypeMilitary) == 0) {

			physicsBody = AssetConstants.PHY_MILITARY_BODY;
			bodyfixturepath = "militarybody";

			imageCarBody = AssetConstants.PHY_IMG_MILITARY_BODY;
			imageCarWheel = AssetConstants.PHY_IMG_MILITARY_WHEEL;
			imageaxle = AssetConstants.PHY_IMG_LEFTAXLE;
		}

		if (this.carType.compareTo(Constants.carTypePolice) == 0) {
			physicsBody = AssetConstants.PHY_POLICE_BODY;
			bodyfixturepath = "policebody";

			imageCarBody = AssetConstants.PHY_IMG_POLICE_BODY;
			imageCarWheel = AssetConstants.PHY_IMG_POLICE_WHEEL;
			imageaxle = AssetConstants.PHY_IMG_LEFTAXLE;
		}

		if (this.carType.compareTo(Constants.carTypeSchoolbus) == 0) {
			physicsBody = AssetConstants.PHY_SCHOOLBUS_BODY;
			bodyfixturepath = "schoolbusbody";

			imageCarBody = AssetConstants.PHY_IMG_SCHOOLBUS_BODY;
			imageCarWheel = AssetConstants.PHY_IMG_SCHOOLBUS_WHEEL;
			imageaxle = AssetConstants.PHY_IMG_LEFTAXLE;
		}

		if (this.carType.compareTo(Constants.carTypeSuper) == 0) {
			physicsBody = AssetConstants.PHY_SUPER_BODY;
			bodyfixturepath = "superbody";

			imageCarBody = AssetConstants.PHY_IMG_SUPER_BODY;
			imageCarWheel = AssetConstants.PHY_IMG_SUPER_WHEEL;
			imageaxle = AssetConstants.PHY_IMG_LEFTAXLE;
		}

		if (this.carType.compareTo(Constants.carTypeFruitTruck) == 0) {
			physicsBody = AssetConstants.PHY_TRUCK_BODY;
			bodyfixturepath = "truckbody";

			physicsBodyHead = AssetConstants.PHY_TRUCK_HEAD;
			headBodyfixturepath = "truckhead";

			if (SelectVehicleMenuFruit.selected_vehicle_button.specificTagFruit != null) {
				if (SelectVehicleMenuFruit.selected_vehicle_button.specificTagFruit == SpecificButtonTagFruit.vehicle_1) {
					imageTruckBody = AssetConstants.PHY_IMG_TRUCK_BODY;
					imageTruckHead = AssetConstants.PHY_IMG_TRUCK_HEAD;
					imageTruckWheel = AssetConstants.PHY_IMG_TRUCK_WHEEL;
					imageaxle = AssetConstants.PHY_IMG_LEFTAXLE;

				} else if (SelectVehicleMenuFruit.selected_vehicle_button.specificTagFruit == SpecificButtonTagFruit.vehicle_2) {
					imageTruckBody = AssetConstants.PHY_IMG_TRUCK_BODY2;
					imageTruckHead = AssetConstants.PHY_IMG_TRUCK_HEAD2;
					imageTruckWheel = AssetConstants.PHY_IMG_TRUCK_WHEEL;
					imageaxle = AssetConstants.PHY_IMG_LEFTAXLE;

				} else if (SelectVehicleMenuFruit.selected_vehicle_button.specificTagFruit == SpecificButtonTagFruit.vehicle_3) {
					imageTruckBody = AssetConstants.PHY_IMG_TRUCK_BODY3;
					imageTruckHead = AssetConstants.PHY_IMG_TRUCK_HEAD3;
					imageTruckWheel = AssetConstants.PHY_IMG_TRUCK_WHEEL;
					imageaxle = AssetConstants.PHY_IMG_LEFTAXLE;

				}
			}

		}

		Helper.println("car type: " + carType + " physics body: " + physicsBody);

		// createBallModel();
		createCarModel();
		// rider = new Background(x, y, manWidth, manHeight, categoryBits);
	}

	public float getCarWidth() {
		return carWidth;
	}

	public void setCarWidth(float carWidth) {
		this.carWidth = carWidth;
	}

	@Override
	public void render() {
	}

	public float getMotorSpeed() {
		return motorSpeed;
	}

	public void setMotorSpeed(float motorSpeed) {
		this.motorSpeed = motorSpeed;
	}

}
