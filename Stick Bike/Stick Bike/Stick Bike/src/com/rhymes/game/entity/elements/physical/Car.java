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

public class Car extends ElementBase implements InteractionTouchCallbacks {
	
//	private double accForce = -0.09f;
	private double accForce = -0.2f;
	private double maxMotorSpeed = 30;
	Vector2 backForce = new Vector2(-50, 0);
	
	
	@Override
	public void render() {
	}

	OrthographicCamera cam;
	// float aspectRatio = ((float)Gdx.graphics.getWidth()*GameMenuInfo.ratio_w
	// / (float)Gdx.graphics.getHeight()*GameMenuInfo.ratio_h);
	// OrthographicCamera cam= new OrthographicCamera(aspectRatio, .1f);

	final static float MAX_VELOCITY = 7f;
	String imagePath;
	private World world;
	private Body player;
	Fixture playerPhysicsFixture;
	Fixture playerSensorFixture;
	SpriteBatch batch;
	BitmapFont font;
	float w = 200;
	float h = 200;

	public float radius;

	private boolean isStatic;

	private boolean collided = false;

	private boolean active = true;

	public static final short categoryBits = 1;

	private float prevY = 1;

	private boolean first = true;
	private int stillTime;
	private Vector2 pos = new Vector2();
	private Body floor;
	private Body leftWheel;
	private Body rightWheel;

	private RevoluteJoint leftWheelRevoluteJoint;
	private RevoluteJoint rightWheelRevoluteJoint;

	private Boolean left = false;
	private Boolean right = false;
	private float motorSpeed = 0;

	public float getMotorSpeed() {
		return motorSpeed;
	}

	public void setMotorSpeed(float motorSpeed) {
		this.motorSpeed = motorSpeed;
	}

	private PrismaticJoint leftAxlePrismaticJoint;
	private PrismaticJoint rightAxlePrismaticJoint;
	//
	private float carPosX = PhysicsHelper.ConvertToBox(20)
			* GameMenuInfo.carSizeCoeff;
	private float carPosY = PhysicsHelper.ConvertToBox(20)
			* GameMenuInfo.carSizeCoeff;
	// carbody width and height

	private float carWidth = PhysicsHelper
			.ConvertToBox(55 /** GameMenuInfo.ratio_w*/)
			* GameMenuInfo.carSizeCoeff;
	private float carHeight = PhysicsHelper
			.ConvertToBox(10 /** GameMenuInfo.ratio_h*/)
			* GameMenuInfo.carSizeCoeff;

	// axleContainer Distance and depth fromt the worldcenter of car

	private float axleContainerDistance = PhysicsHelper
			.ConvertToBox(40 /** GameMenuInfo.ratio_w*/)
			* GameMenuInfo.carSizeCoeff;
	private float axleContainerDepth = PhysicsHelper
			.ConvertToBox(23 /** GameMenuInfo.ratio_h*/)
			* GameMenuInfo.carSizeCoeff;

	// /axle container width and height
	private float axleContainerWidth = PhysicsHelper
			.ConvertToBox(05f /** GameMenuInfo.ratio_w*/)
			* GameMenuInfo.carSizeCoeff;
	private float axleContainerHeight = PhysicsHelper
			.ConvertToBox(05f /** GameMenuInfo.ratio_h*/)
			* GameMenuInfo.carSizeCoeff;

	private float leftAxleContainerAngle = (MathUtils.degreesToRadians * 35)/**
	 * 
	 * 
	 * 
	 * GameMenuInfo.carSizeCoeff
	 */
	;
	private float rightAxleContainerAngle = (MathUtils.degreesToRadians * 35)/**
	 * 
	 * 
	 * 
	 * GameMenuInfo.carSizeCoeff
	 */
	;

	// /axle width and height
	private float axleWidth = PhysicsHelper
			.ConvertToBox(2.5f /** GameMenuInfo.ratio_w*/)
			* GameMenuInfo.carSizeCoeff;
	private float axleHeight = PhysicsHelper
			.ConvertToBox(2.5f /** GameMenuInfo.ratio_h*/)
			* GameMenuInfo.carSizeCoeff;
	private float leftAxleAngle = (MathUtils.degreesToRadians * 35)/**
	 * 
	 * 
	 * 
	 * GameMenuInfo.carSizeCoeff
	 */
	;
	private float rightAxleAngle = (MathUtils.degreesToRadians * 35)/**
	 * 
	 * 
	 * 
	 * GameMenuInfo.carSizeCoeff
	 */
	;
	// /wheel radious
	private float wheelRadius = PhysicsHelper
			.ConvertToBox(25 /** GameMenuInfo.ratio_w*/)
			* GameMenuInfo.carSizeCoeff;

	// rider width height and angle
	private float manWidth = PhysicsHelper.ConvertToBox(45)
			/** GameMenuInfo.ratio_w*/ * GameMenuInfo.carSizeCoeff;
	private float manHeight = PhysicsHelper.ConvertToBox(10)
			/** GameMenuInfo.ratio_h*/ * GameMenuInfo.carSizeCoeff;
	private float riderAngle = (MathUtils.degreesToRadians * 0)
			* GameMenuInfo.carSizeCoeff;

	private PrismaticJointDef leftAxlePrismaticJointDef;
	private PrismaticJointDef rightAxlePrismaticJointDef;
	private float angle;
	private TextureRegion imageWheelRight;
	private TextureRegion imageWheelLeft;
	private Body leftAxle;
	private Body rightAxle;
	private TextureRegion imageLeftaxle;
	private TextureRegion imageRightaxle;

	private Renderer renderer;
	private TextureRegion imageRider;

	private TextureRegion imageBikeBody;

	private Background rider;
	private BikeLevel currentLevel;
	private float agerY;
	private float agerX;
	private Body leftAxleContainer;
	private Body rightAxleContainer;
	private DistanceJoint leftAxleContainerDistanceJoint;
	private DistanceJoint rightAxleContainerDistanceJoint;
	private Joint leftWeldJoint;
	private Joint rightWeldJoint;

	private RevoluteJoint leftAxleContainerRevoluteJoint;
	private Body riderBody;
	private Body fumeBody;
	private Joint riderWeldJoint;
	private PrismaticJoint riderPrismaticJoint;


	public void update(float dt) {

		if (GlobalVars.ge.getCurrentStage().getGameState() == GameState.OVER)
			return;
		
		if(Helper.getScreenWidth() >1100)
			((BikeLevel)GlobalVars.ge.getCurrentStage()).setFumePosition(PhysicsHelper.ConvertToWorld(fumeBody.getPosition().x) * LevelInfo.ratioX, 
				PhysicsHelper.ConvertToWorld(fumeBody.getPosition().y) * LevelInfo.ratioX - 6f*LevelInfo.ratioX);
		else if(Helper.getScreenWidth() < 860)
			((BikeLevel)GlobalVars.ge.getCurrentStage()).setFumePosition(PhysicsHelper.ConvertToWorld(fumeBody.getPosition().x) * LevelInfo.ratioX, 
				PhysicsHelper.ConvertToWorld(fumeBody.getPosition().y) * LevelInfo.ratioX + 3.5f/LevelInfo.ratioX);
		else
			((BikeLevel)GlobalVars.ge.getCurrentStage()).setFumePosition(PhysicsHelper.ConvertToWorld(fumeBody.getPosition().x) * LevelInfo.ratioX, 
					PhysicsHelper.ConvertToWorld(fumeBody.getPosition().y) * LevelInfo.ratioX);
		
//		((BikeLevel)GlobalVars.ge.getCurrentStage()).setFumePosition(PhysicsHelper.ConvertToWorld(fumeJoint.getAnchorA().x), 
//				PhysicsHelper.ConvertToWorld(fumeJoint.getAnchorA().y));
		
		// Helper.println("Car: Upate" );
/*		if (leftAxlePrismaticJoint != null) {
			if ((PhysicsHelper.BOX_TO_WORLD
					* (leftAxlePrismaticJoint.getReactionForce(1 / dt).x) > 10)
					&& (PhysicsHelper.BOX_TO_WORLD
							* (leftAxlePrismaticJoint.getReactionForce(1 / dt).y) > 10)) {
				// float x =
				// PhysicsHelper.BOX_TO_WORLD*(leftAxlePrismaticJoint.getReactionForce(1/dt).x);
				// float y =
				// PhysicsHelper.BOX_TO_WORLD*(leftAxlePrismaticJoint.getReactionForce(1/dt).y);

				// float reactionForce = (float) Math.sqrt(Math.pow((double)x,
				// 2)+Math.pow((double)y, 2));
				// Helper.println("raction force::::"+reactionForce);
				// if(reactionForce > 1500)
				// this.destroyJoints();
			}
		}*/

		PhysicsHelper.accumulator += dt;
//		 Helper.println( "dt:::"+PhysicsHelper.accumulator*1000f);
		while (PhysicsHelper.accumulator > PhysicsHelper.BOX_STEP) {

			world.step( PhysicsHelper.BOX_STEP ,
					PhysicsHelper.BOX_VELOCITY_ITERATIONS,
					PhysicsHelper.BOX_POSITION_ITERATIONS);

			this.setLocation(
					PhysicsHelper.ConvertToWorld(player.getPosition().x) ,
					PhysicsHelper.ConvertToWorld(player.getPosition().y));
			PhysicsHelper.accumulator -= PhysicsHelper.BOX_STEP;
			// Helper.println("i'm here in update");

			// motorSpeed*=.99f;
			// if (motorSpeed>20) {
			// motorSpeed=20;
			// }

			if (leftWheelRevoluteJoint != null
					&& rightWheelRevoluteJoint != null) {

				rightWheelRevoluteJoint.setMaxMotorTorque(100);
				leftWheelRevoluteJoint.setMaxMotorTorque(100);

				if(!(motorSpeed < 0 && motorSpeed > -2.5f)){
					leftWheelRevoluteJoint.setMotorSpeed(motorSpeed);
					rightWheelRevoluteJoint.setMotorSpeed(motorSpeed);
				}
				// rightWheelRevoluteJoint.setMotorSpeed(motorSpeed);

				// leftWheel.applyForce(new Vector2((float) (motorSpeed * 5 *
				// Math.cos(leftWheel.getAngle() * MathUtils.degreesToRadians)),
				// (float) (motorSpeed * 5 * Math.sin(leftWheel.getAngle() *
				// MathUtils.degreesToRadians))), leftWheel.getWorldCenter());

				// Helper.println("traversed distance : " +
				// player.getPosition().x+"leftmotor speed"+
				// leftWheelRevoluteJoint.getMotorSpeed());
				// Helper.println("traversed distance : " +
				// player.getPosition().x+"rightmotor speed"+
				// rightWheelRevoluteJoint.getMotorSpeed());
			}

			// Gdx.gl10.glClearColor(0.4f, 0.6f, 0.9f, 1);

			// float aspectRatio = (float)Gdx.graphics.getWidth() /
			// (float)Gdx.graphics.getHeight();
			// cam= new OrthographicCamera(aspectRatio, 1.0f);
			// cam= new OrthographicCamera();
			// GL10 gl = Gdx.graphics.getGL10();

			// cam.position.set(PhysicsHelper.ConvertToWorld(player.getPosition().x)-PhysicsHelper.ConvertToWorld(2*carWidth),
			// PhysicsHelper.ConvertToWorld(player.getPosition().y)-PhysicsHelper.ConvertToWorld(2*carHeight),
			// 0);
			// cam.position.set(PhysicsHelper.ConvertToWorld(player.getPosition().x)-carWidth,200f,
			// 0);

			// cam.update();
			// cam.apply(gl);
			// batch.setProjectionMatrix(cam.combined);

//			 cam.zoom = 1.8f;

//			cam.position.x = PhysicsHelper
//					.ConvertToWorld(player.getPosition().x) * LevelInfo.ratioX+
//														 Gdx.graphics.getWidth()/3f;
//			cam.position.y = PhysicsHelper
//					.ConvertToWorld(player.getPosition().y)* LevelInfo.ratioY;
			
			
			cam.position.x = (PhysicsHelper
					.ConvertToWorld(player.getPosition().x) * LevelInfo.ratioX+
														 Gdx.graphics.getWidth()/3f);
			cam.position.y = PhysicsHelper
					.ConvertToWorld(player.getPosition().y) * LevelInfo.ratioX;
			
			// cam.update();

			// cam.zoom = 0.8f;
			GlobalVars.ge.getRenderer().getBatch()
					.setProjectionMatrix(cam.combined);
			// cam.position.set(GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(player.getPosition().x)-GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(carWidth),
			// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(player.getPosition().y)+GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(carHeight*2),
			// 0);
			// cam.position.set(GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(rightWheel.getPosition().x)-GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(wheelRadius),
			// 310, 0);
			// Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

			// Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			// cam.position.set(PhysicsHelper.ConvertToWorld(player.getPosition().x),
			// PhysicsHelper.ConvertToWorld(player.getPosition().y), 0);
			//
			// cam.update();
			// batch.setProjectionMatrix(cam.combined);
			// renderer.render(world, cam.combined);

			// leftAxlePrismaticJoint.setMaxMotorForce(Math.abs(500*leftAxlePrismaticJoint.getJointTranslation()));
			// leftAxlePrismaticJoint.setMotorSpeed((leftAxlePrismaticJoint.getMotorSpeed()-2*leftAxlePrismaticJoint.getJointTranslation()));
			// rightAxlePrismaticJoint.setMaxMotorForce(Math.abs(500*rightAxlePrismaticJoint.getJointTranslation()));
			// rightAxlePrismaticJoint.setMotorSpeed((rightAxlePrismaticJoint.getMotorSpeed()-2*rightAxlePrismaticJoint.getJointTranslation()));

			// //

			// int baseSpringForce= (int) player.getMass()*-5;
			// leftAxlePrismaticJoint.setMaxMotorForce( baseSpringForce + (40 *
			// baseSpringForce *
			// leftAxlePrismaticJoint.getJointTranslation()));

			// spring1->SetMotorSpeed( -20 * spring1->GetJointTranslation() );
			// spring2->SetMaxMotorForce( baseSpringForce + (40 *
			// baseSpringForce *
			// powf(spring2->GetJointTranslation(), 2) ) );
			// spring2->SetMotorSpeed( -20 * spring2->GetJointTranslation() );
			//

			// Helper.println("LRXA  left axle: " +
			// leftAxlePrismaticJoint.getJointTranslation());
			// Helper.println("LRYB: " +
			// leftWheelRevoluteJoint.getBodyB().getPosition().x);
		}
		world.clearForces();
	}

	public Car(float x, float y, float width, float height, float angle,
			String imagePath, World world, boolean b) {
		// super(x , y, width,height, 1);
		// TODO Auto-generated constructor stub
		// this.radius = radius / GameMenuInfo.ratio_w;
//		Helper.println("X: " + x);
//		Helper.println("After X: " + this.x + imagePath);
		this.imagePath = imagePath;
		this.world = world;
		this.x = x;
		this.y = y;
		this.pos.set(this.x, this.y);
		this.width = width;
		this.height = height;
		this.angle = angle;
		this.isStatic = b;
		// createBallModel();
		createCarModel();
		GlobalVars.ge.getCurrentStage().subscribeToInteraction(this,
				InteractionTouch.class);
	}

	// float rad;

	// boolean first = true;

	// @Override
	public void updatePhysicsWorld() {
		// Helper.println("Updating physics world");
		// world.step(Gdx.graphics.getDeltaTime(), 4, 4);
		this.update(Gdx.graphics.getDeltaTime());
		// player.setAwake(true);
		// player.get

		// cam.position.set(PhysicsHelper.ConvertToWorld(player.getPosition().x)
		// - GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(carWidth/2),
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(player.getPosition().y)-GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(carHeight/2),
		// 0);
		// cam.update();

		// cam.position.set(player.getWorldCenter().x,player.getWorldCenter().y,0);

		// cam.position.set(PhysicsHelper.ConvertToWorld(player.getPosition().x),
		// PhysicsHelper.ConvertToWorld(player.getPosition().y), 0);

		// rider.setX(GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(player.getPosition().x));
		// // rider.setY(
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(player.getPosition().y)-GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(carHeight/4));
		// rider.setY( PhysicsHelper.ConvertToWorld(riderBody.getPosition().y));
		// GlobalVars.ge.getRenderer().render(imageLeftaxle,rider.getX()
		// ,rider.getY(),
		// manWidth, manHeight,
		// manWidth/2, manHeight/2,
		// MathUtils.radiansToDegrees*player.getAngle()+130, 1, 1);

		// rider = new Background(x, y, manWidth, manHeight, categoryBits);
		// rider.setX(GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(player.getPosition().x));
		// rider.setY(
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(player.getPosition().y)-GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(carHeight/4));

		// GlobalVars.ge.getRenderer().render
		// (imageLeftaxle,
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(player.getPosition().x),
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(player.getPosition().y),
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(carWidth * 2f) ,
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(carHeight * 2f),
		// // GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(carWidth),
		// // GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(carHeight),
		// 0,0,
		// MathUtils.radiansToDegrees*player.getAngle(), 1,1);

		// GlobalVars.ge.getRenderer().render
		// (imageRider,
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(riderBody.getPosition().x)
		// /*- GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(manWidth/2)*/,
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(riderBody.getPosition().y)/*-GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(manHeight*1.5f)*/,
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(manWidth*2) ,
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(manHeight*2),
		// /*GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(manWidth/2),
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(manHeight/2),*/
		// 0,0,
		// MathUtils.radiansToDegrees*riderBody.getAngle()+90, 1,1);

		// GlobalVars.ge.getRenderer().render(imageWheelLeft,
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(leftWheel.getPosition().x)
		// - GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(wheelRadius),
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(leftWheel.getPosition().y)
		// - GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(wheelRadius),
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(wheelRadius*2) ,
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(wheelRadius*2),
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(wheelRadius),
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(wheelRadius),
		// MathUtils.radiansToDegrees*leftWheel.getAngle(), 1,1);
		//
		// GlobalVars.ge.getRenderer().render(imageWheelRight,
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(rightWheel.getPosition().x)
		// - GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(wheelRadius),
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(rightWheel.getPosition().y)
		// - GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(wheelRadius),
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(wheelRadius*2) ,
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(wheelRadius*2),
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(wheelRadius),
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(wheelRadius),
		// MathUtils.radiansToDegrees*rightWheel.getAngle(), 1,1);

		// GlobalVars.ge.getRenderer().render(image,GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(player.getPosition().x),GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(player.getPosition().y),GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(carWidth)
		// , GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(carHeight),
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(carWidth/2),
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(carHeight/2),MathUtils.radiansToDegrees*player.getAngle(),
		// 1,1);
		// GlobalVars.ge.getRenderer().render(imageLeftaxle,
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(leftAxleContainer.getPosition().x)/*-GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(axleContainerWidth)*/,
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(leftAxleContainer.getPosition().y)/*-GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(axleContainerHeight)*/,
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(axleContainerWidth*2)
		// ,
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(axleContainerHeight*2),
		// //
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(axleContainerWidth),
		// //
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(axleContainerHeight),
		// 0,0,
		// leftAxleContainer.getAngle()*MathUtils.radiansToDegrees, 1,1);
		// GlobalVars.ge.getRenderer().render(imageRightaxle,
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(rightAxleContainer.getPosition().x)/*-GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(axleContainerWidth)*/,
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(rightAxleContainer.getPosition().y)/*-GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(axleContainerHeight)*/,
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(axleContainerWidth*2)
		// ,
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(axleContainerHeight*2),
		// //
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(axleContainerWidth),
		// //
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(axleContainerHeight),
		// 0,0,
		// rightAxleContainer.getAngle()*MathUtils.radiansToDegrees, 1,1);
		// GlobalVars.ge.getRenderer().render(imageLeftaxle,
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(leftAxle.getPosition().x)/*-GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(axleContainerWidth/2)*/,
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(leftAxle.getPosition().y)/*-GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(axleContainerHeight/2)*/,
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(axleContainerWidth)
		// ,
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(axleContainerHeight*2),
		// //
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(axleContainerWidth/2),
		// //
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(axleContainerHeight/2),
		// 0,0,
		// leftAxle.getAngle()*MathUtils.radiansToDegrees, 1,1);
		// GlobalVars.ge.getRenderer().render(imageRightaxle,
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(rightAxle.getPosition().x)/*-GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(axleContainerWidth/2)*/,
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(rightAxle.getPosition().y)/*-GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(axleContainerHeight/2)*/,
		// GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(axleContainerWidth)
		// ,
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(axleContainerHeight*2),
		// /*GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(axleContainerWidth/2),
		// GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(axleContainerHeight/2),*/
		// 0,0,
		// rightAxle.getAngle()*MathUtils.radiansToDegrees, 1,1);

		/*
		 * if (Math.abs((cam.position.x - agerX)) < Gdx.graphics.getWidth()-100)
		 * { // Helper.println("difference =="+Math.abs((rider.getY()-agerY)));
		 * cam.position.x = agerX; } agerX = cam.position.x;
		 * 
		 * if (Math.abs((cam.position.y - agerY)) < 200) { //
		 * Helper.println("difference =="+Math.abs((rider.getY()-agerY)));
		 * cam.position.y = agerY; } agerY = cam.position.y;
		 */

		// Helper.println("Car Render position: " +
		// (this.getLocation())+"and camera location"+cam.position+"and something even more"+player.getFixtureList().get(player.getFixtureList().size()-1).getBody().getPosition());

		// apply left impulse, but only if max velocity is not reached yet
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			leftAxleContainer.setAngularVelocity(0);
			leftAxleContainer.applyLinearImpulse(
					(float) (accForce  * Math.cos(player.getAngle() + 90
							* MathUtils.degreesToRadians)),
					(float) (accForce  * Math.sin(player.getAngle() + 90
							* MathUtils.degreesToRadians)),
					leftAxleContainer.getWorldCenter().x,
					leftAxleContainer.getWorldCenter().y);
			// leftAxleContainer.applyLinearImpulse(0f, -0.05f,
			// leftAxleContainer.getPosition().x,
			// leftAxleContainer.getPosition().y);
			// leftWheel.applyAngularImpulse(10f);
			// rightWheel.applyAngularImpulse(10f);
//			player.setTransform(player.getPosition(), player.getAngle() - 0.1f);
//			leftAxleContainer.setTransform(leftAxleContainer.getPosition(), leftAxleContainer.getAngle() - 0.1f);
//			rightAxleContainer.setTransform(rightAxleContainer.getPosition(), rightAxleContainer.getAngle() - 0.1f);
//			leftAxle.setTransform(leftAxle.getPosition(), leftAxle.getAngle() - 0.1f);
//			rightAxle.setTransform(rightAxle.getPosition(), rightAxle.getAngle() - 0.1f);
//			leftWheel.setTransform(leftWheel.getPosition(), leftWheel.getAngle() - 0.1f);
//			rightWheel.setTransform(rightWheel.getPosition(), rightWheel.getAngle() - 0.1f);
//			Helper.println("player angle in degree::"
//					+ (MathUtils.radiansToDegrees * player.getAngle()));
			 
			// Helper.println("key pressed: "+Keys.A+"player position physics world"+player.getPosition()+"and player position in game world::"+this.getLocation());
		}

		// apply right impulse, but only if max velocity is not reached yet
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			rightAxleContainer.setAngularVelocity(0);
			rightAxleContainer.applyLinearImpulse(
					(float) (accForce  * Math.cos(player.getAngle() + 90
							* MathUtils.degreesToRadians)),
					(float) (accForce  * Math.sin(player.getAngle() + 90
							* MathUtils.degreesToRadians)),
					rightAxleContainer.getWorldCenter().x,
					rightAxleContainer.getWorldCenter().y);
			
			
			// leftWheel.applyAngularImpulse(-10f);
			// rightWheel.applyAngularImpulse(-0.5f);
			// player.setTransform(player.getPosition().x,
			// player.getPosition().y, player.getAngle()-.1f);
			
//			player.setTransform(player.getPosition(), player.getAngle() + 0.1f);
//			leftAxleContainer.setTransform(leftAxleContainer.getPosition(), leftAxleContainer.getAngle() + 0.1f);
//			rightAxleContainer.setTransform(rightAxleContainer.getPosition(), rightAxleContainer.getAngle() + 0.1f);
//			leftAxle.setTransform(leftAxle.getPosition(), leftAxle.getAngle() + 0.1f);
//			rightAxle.setTransform(rightAxle.getPosition(), rightAxle.getAngle() + 0.1f);
//			leftWheel.setTransform(leftWheel.getPosition(), leftWheel.getAngle() + 0.1f);
//			rightWheel.setTransform(rightWheel.getPosition(), rightWheel.getAngle() + 0.1f);
			
			Helper.println("player angle in degree::"
					+ (MathUtils.radiansToDegrees * player.getAngle()));
			// player.setTransform(player.getPosition(), 0f);
			// player.setTransform(player.getPosition().x,
			// player.getPosition().y,
			// player.getAngle()+(MathUtils.degreesToRadians*10));
			// Helper.println("key pressed: "+Keys.D+"player position physics world"+player.getPosition()+"and player position in game world::"+this.getLocation());
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
			Helper.println("Speeding down: left : " + motorSpeed);
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
			Helper.println("Speeding up: Right : " + motorSpeed);
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

	public void increaseMotorSpeed(boolean directionRight) {
		if (directionRight) {
			if (motorSpeed < 0){
				motorSpeed = 0;
			}

			if(motorSpeed == 0)
			{
				motorSpeed += 0.1f;
				StartupInfo.sound_handler.playSound(SoundHandler.soundType.SOUND_BIKE_START);
				StartupInfo.sound_handler.playMusic(musicType.SOUND_BIKE_RUNNING,0.2f);
			}		
			if (motorSpeed < 8 && motorSpeed > 0) {
				motorSpeed += 0.1f;
			}
//			if (motorSpeed < 10 && motorSpeed >= 8) {
//				motorSpeed += 0.2f;
//			}
			if (motorSpeed < maxMotorSpeed && motorSpeed >= 8) {
				motorSpeed += 0.2f;
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
			if (motorSpeed >= 0)
				motorSpeed -= 0.3f;
			if (motorSpeed <= 0 && motorSpeed > -5) {
//				StartupInfo.sound_handler.stopSound(SoundHandler.soundType.SOUND_BIKE_RUNNING);
				((BikeLevel)GlobalVars.ge.getCurrentStage()).stopSounds();				
			
				motorSpeed-= 0.05;
				
//				rightWheel.applyForce(backForce, rightWheel.getWorldCenter());
				Helper.println("applying force");
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

	/*
	 * PhysicsBody2 carPhysicsBody; private void createCarModel() { // TODO
	 * Auto-generated method stub
	 * 
	 * // ************************ THE CAR ************************ //
	 * 
	 * PolygonShape carShape = new PolygonShape();
	 * carShape.setAsBox(carWidth,carHeight,new
	 * Vector2(carWidth,carHeight),this.angle*MathUtils.degreesToRadians); //
	 * carShape.setAsBox(carWidth,carHeight); FixtureDef carFixture = new
	 * FixtureDef(); carFixture.density=5f; carFixture.friction=3f;
	 * carFixture.restitution=0.3f; // carFixture.filter.groupIndex=-1;
	 * carFixture.filter.categoryBits = 1; carFixture.filter.maskBits = 11;
	 * carFixture.shape=carShape;
	 * 
	 * BodyDef carBodyDef = new BodyDef();
	 * carBodyDef.position.set(PhysicsHelper.
	 * ConvertToBox(this.x),PhysicsHelper.ConvertToBox(this.y));
	 * carBodyDef.type=BodyType.DynamicBody; // player =
	 * PhysicsHelper.CreateBody(world, player, BodyType.StaticBody, new
	 * Vector2(carPosX, carPosY), angle); player=world.createBody(carBodyDef);
	 * player.createFixture(carFixture); player.setUserData("carbody");
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * carPhysicsBody = new PhysicsBody2(this.x,this.y,
	 * PhysicsHelper.ConvertToWorld(carWidth*2), 100, AssetConstants.PHY_CARBODY
	 * , AssetConstants.PHY_IMG_CARBODY, "carbody", world, ((short) 1),((short)
	 * 11),"carbody",2, BodyType.DynamicBody);
	 * GlobalVars.ge.getCurrentStage().addElement(carPhysicsBody); player =
	 * carPhysicsBody.getBody(); player.setUserData("carbody");
	 * 
	 * 
	 * 
	 * // ************************ THE Rider ************************ //
	 * 
	 * PolygonShape riderShape = new PolygonShape();
	 * riderShape.setAsBox(PhysicsHelper
	 * .ConvertToBox(manWidth),PhysicsHelper.ConvertToBox(manHeight)); //
	 * carShape.setAsBox(carWidth,carHeight); FixtureDef riderFixture = new
	 * FixtureDef(); riderFixture.density=5f; riderFixture.friction=3f;
	 * riderFixture.restitution=0.3f; // riderFixture.filter.groupIndex=-1;
	 * riderFixture.filter.categoryBits = 13; riderFixture.filter.maskBits =
	 * 1|11; riderFixture.shape=riderShape;
	 * 
	 * BodyDef riderBodyDef = new BodyDef(); //
	 * riderBodyDef.position.set((player
	 * .getPosition().x),(player.getPosition().y+carHeight));
	 * riderBodyDef.position
	 * .set(player.getWorldCenter().x+carWidth/2f,player.getWorldCenter
	 * ().y+2*carHeight); //
	 * riderBodyDef.position.set(PhysicsHelper.ConvertToBox
	 * (this.x),PhysicsHelper.ConvertToBox(this.y+10f)); //
	 * riderBodyDef.type=BodyType.DynamicBody; // riderBodyDef.angle = (float)
	 * (-Math.PI); riderBody=world.createBody(riderBodyDef);
	 * riderBody.createFixture(riderFixture); riderBody.setFixedRotation(false);
	 * riderBody.setType(BodyType.DynamicBody);
	 * riderBody.setUserData("riderBody");
	 * 
	 * 
	 * 
	 * 
	 * 
	 * carPhysicsBody = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld(player.getPosition
	 * ().x+carWidth),
	 * PhysicsHelper.ConvertToWorld(player.getPosition().y+carHeight),
	 * PhysicsHelper.ConvertToWorld(manWidth),
	 * PhysicsHelper.ConvertToWorld(manHeight), AssetConstants.PHY_BIKEMAN ,
	 * AssetConstants.PHY_IMG_BIKEMAN, "bikeman", world, ((short) 13),((short)
	 * (1|11)),"bikeman",2, BodyType.DynamicBody);
	 * GlobalVars.ge.getCurrentStage().addElement(carPhysicsBody); riderBody =
	 * carPhysicsBody.getBody(); riderBody.setUserData("riderBody");
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // // ************************ THE WHEELS ************************ //;
	 * CircleShape wheelShape =new CircleShape();//((float)
	 * (wheelRadius/worldScale)) wheelShape.setRadius(wheelRadius); FixtureDef
	 * wheelFixture = new FixtureDef(); wheelFixture.density=05f;
	 * wheelFixture.friction=15; wheelFixture.restitution=0.2f; //
	 * wheelFixture.filter.groupIndex=-1; wheelFixture.filter.categoryBits=7;
	 * wheelFixture.filter.maskBits =11; wheelFixture.shape=wheelShape; BodyDef
	 * leftwheelBodyDef = new BodyDef(); //
	 * wheelBodyDef.type=BodyType.DynamicBody; //
	 * wheelBodyDef.position.set((float
	 * )(carPosX-axleContainerDistance-2*axleContainerHeight
	 * *Math.cos((90-axleAngle
	 * ))),(float)(carPosY+axleContainerDepth-2*axleContainerHeight
	 * *(float)Math.sin((90-axleAngle))));
	 * leftwheelBodyDef.position.set((float)(
	 * player.getPosition().x-axleContainerDistance-2*axleContainerHeight
	 * Math.cos
	 * (MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees
	 * ))),(float
	 * )(player.getPosition().y-axleContainerDepth-2*axleContainerHeight
	 * *(float)Math
	 * .sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees
	 * )))); // wheelBodyDef.position.set(carPosX, carPosY); // Body
	 * leftWheel=world.createBody(leftwheelBodyDef);
	 * leftWheel.createFixture(wheelFixture);
	 * leftWheel.setType(BodyType.DynamicBody);
	 * leftWheel.setUserData("leftWheel");
	 * 
	 * 
	 * 
	 * //passing to the game world BodyDef rightwheelBodyDef = new BodyDef(); //
	 * wheelBodyDef
	 * .position.set((float)(carPosX+axleContainerDistance+2*axleContainerHeight
	 * *Math.cos((90-axleAngle))),(float)(carPosY+axleContainerDepth-2*
	 * axleContainerHeight*(float)Math.sin((90-axleAngle))));
	 * rightwheelBodyDef.position
	 * .set((float)(player.getPosition().x+axleContainerDistance
	 * +2*axleContainerHeight
	 * *Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils
	 * .radiansToDegrees
	 * ))),(float)(player.getPosition().y-axleContainerDepth-2*
	 * axleContainerHeight
	 * *(float)Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.
	 * radiansToDegrees)))); // Body
	 * rightWheel=world.createBody(rightwheelBodyDef);
	 * rightWheel.createFixture(wheelFixture);
	 * rightWheel.setType(BodyType.DynamicBody);
	 * rightWheel.setUserData("rightWheel");
	 * 
	 * 
	 * 
	 * // ************************ LEFT AXLE CONTAINER ************************
	 * //
	 * 
	 * carPhysicsBody = new
	 * PhysicsBody2(PhysicsHelper.ConvertToWorld(player.getPosition
	 * ().x+axleContainerWidth),
	 * PhysicsHelper.ConvertToWorld(player.getPosition().y-axleContainerHeight),
	 * PhysicsHelper.ConvertToWorld(axleContainerWidth),
	 * PhysicsHelper.ConvertToWorld(axleContainerHeight),
	 * AssetConstants.PHY_AXLE_CONTAINER ,
	 * AssetConstants.PHY_IMG_LEFTAXLECONTAINER, "leftaxlecontainer", world,
	 * ((short) 2),((short) (1|11)),"leftaxlecontainer",2,
	 * BodyType.DynamicBody);
	 * GlobalVars.ge.getCurrentStage().addElement(carPhysicsBody);
	 * leftAxleContainer = carPhysicsBody.getBody();
	 * leftAxleContainer.setUserData("leftAxleContainer");
	 * 
	 * // PolygonShape leftAxleContainerShape = new PolygonShape(); // //
	 * leftAxleContainerShape
	 * .setAsBox(axleContainerWidth,axleContainerHeight,new
	 * Vector2(axleContainerWidth,axleContainerHeight),axleAngle); ////
	 * leftAxleContainerShape.setAsBox(axleContainerWidth,axleContainerHeight);
	 * // FixtureDef leftAxleContainerFixture = new FixtureDef(); //
	 * leftAxleContainerFixture.density=6f; //
	 * leftAxleContainerFixture.friction=3f; //
	 * leftAxleContainerFixture.restitution=0.3f; ////
	 * leftAxleContainerFixture.filter.groupIndex=-1; //
	 * leftAxleContainerFixture.filter.categoryBits=2; ////
	 * leftAxleContainerFixture.filter.maskBits = 11; // //
	 * leftAxleContainerFixture.shape=leftAxleContainerShape; // BodyDef
	 * leftAxleContainerDef = new BodyDef(); ////
	 * leftAxleContainerDef.position.set(new
	 * Vector2((float)(player.getPosition()
	 * .x-2*axleContainerH*Math.cos(MathUtils
	 * .degreesToRadians*(90-axleAngle*MathUtils
	 * .radiansToDegrees))-axleContainerDistance
	 * -axleContainerDepth*Math.cos(MathUtils
	 * .degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))), ////
	 * (float
	 * )(player.getPosition().y-axleContainerDepth+0*axleContainerHeight*Math
	 * .sin
	 * (MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))
	 * ))); // leftAxleContainerDef.position.set((float)(player.getPosition().x-
	 * axleContainerDistance
	 * -axleContainerHeight/2*Math.cos(MathUtils.degreesToRadians
	 * *(90-axleAngle*MathUtils
	 * .radiansToDegrees))),(float)(player.getPosition().
	 * y-axleContainerDepth-axleContainerHeight
	 * /2*(float)Math.sin(MathUtils.degreesToRadians
	 * *(90-axleAngle*MathUtils.radiansToDegrees)))); //
	 * leftAxleContainerDef.type=BodyType.DynamicBody; //
	 * leftAxleContainerDef.angle = -axleAngle; // leftAxleContainer =
	 * world.createBody(leftAxleContainerDef); //
	 * leftAxleContainer.createFixture(leftAxleContainerFixture); ////
	 * leftAxleContainer.setFixedRotation(false); //
	 * leftAxleContainer.setUserData("leftAxleContainer");
	 * 
	 * // ************************ RIGHT AXLE CONTAINER ************************
	 * // PolygonShape rightAxleContainerShape = new PolygonShape();
	 * rightAxleContainerShape
	 * .setAsBox(axleContainerWidth,axleContainerHeight,new
	 * Vector2(axleContainerWidth,axleContainerHeight),-axleAngle); FixtureDef
	 * rightAxleContainerFixture = new FixtureDef();
	 * rightAxleContainerFixture.density=6f;
	 * rightAxleContainerFixture.friction=3;
	 * rightAxleContainerFixture.restitution=0.3f; //
	 * rightAxleContainerFixture.filter.groupIndex=-1;
	 * rightAxleContainerFixture.filter.categoryBits=3; //
	 * rightAxleContainerFixture.filter.maskBits = 11;
	 * 
	 * rightAxleContainerFixture.shape=rightAxleContainerShape; BodyDef
	 * rightAxleContainerDef = new BodyDef();
	 * 
	 * // rightAxleContainerDef.position.set(new
	 * Vector2((float)(player.getPosition
	 * ().x+2*axleContainerWidth*Math.cos(90-axleAngle
	 * )+axleContainerDistance+axleContainerDepth
	 * *Math.cos(MathUtils.degreesToRadians
	 * *(90-axleAngle*MathUtils.radiansToDegrees))), //
	 * (float)(player.getPosition
	 * ().y-axleContainerDepth+0*axleContainerHeight*Math
	 * .sin(MathUtils.degreesToRadians
	 * *(90-axleAngle*MathUtils.radiansToDegrees)))));
	 * rightAxleContainerDef.position
	 * .set((float)(player.getPosition().x+axleContainerDistance
	 * +axleContainerHeight
	 * /2*Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils
	 * .radiansToDegrees
	 * ))),(float)(player.getPosition().y-axleContainerDepth-axleContainerHeight
	 * /2*(float)Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.
	 * radiansToDegrees)))); rightAxleContainerDef.type=BodyType.DynamicBody;
	 * rightAxleContainerDef.angle = axleAngle; rightAxleContainer =
	 * world.createBody(rightAxleContainerDef);
	 * rightAxleContainer.createFixture(rightAxleContainerFixture); //
	 * rightAxleContainer.setFixedRotation(false);
	 * rightAxleContainer.setUserData("rightAxleContainer"); //
	 * ************************ MERGING ALL TOGETHER ************************ //
	 * // PhysicsHelper.MakeFixture(player, Shape.Type.Polygon, width, height,
	 * 5f, 0.3f,3f); // player=world.createBody(carBodyDef); //
	 * player.createFixture(carFixture); //
	 * player.createFixture(leftAxleContainerFixture); //
	 * player.createFixture(rightAxleContainerFixture); //
	 * player.setFixedRotation(false); // player.getf //
	 * player.setType(BodyType.StaticBody); // player.setUserData("car"); //
	 * player.resetMassData();
	 * 
	 * // // // ************************ THE AXLES ************************ //
	 * PolygonShape leftAxleShape = new PolygonShape(); // float axleHight =
	 * leftWheel.getWorldCenter().dst(leftAxleContainer.getWorldCenter());
	 * leftAxleShape.setAsBox(axleContainerWidth/2,axleContainerHeight,new
	 * Vector2(axleContainerWidth/2,axleContainerHeight),axleAngle); //
	 * leftAxleShape.setAsBox(axleContainerWidth,axleHight/2,new
	 * Vector2(axleContainerWidth,axleHight),axleAngle); FixtureDef
	 * leftAxleFixture = new FixtureDef(); leftAxleFixture.density=0.5f;
	 * leftAxleFixture.friction=3; leftAxleFixture.restitution=0;
	 * leftAxleFixture.shape=leftAxleShape; //
	 * leftAxleFixture.filter.groupIndex=-1;
	 * leftAxleFixture.filter.categoryBits=4; leftAxleFixture.filter.maskBits =
	 * 11; BodyDef leftAxleBodyDef = new BodyDef(); //
	 * leftAxleBodyDef.type=BodyType.DynamicBody; //
	 * leftAxleBodyDef.position.set(new
	 * Vector2((float)(carPosX-axleContainerDistance
	 * -axleContainerHeight*Math.cos
	 * ((90-axleAngle))),(float)(carPosY+axleContainerDepth
	 * +axleContainerHeight*Math.sin((90-axleAngle)))));
	 * leftAxleBodyDef.position.set(new
	 * Vector2((float)(leftAxleContainer.getPosition
	 * ().x-axleContainerWidth*Math.
	 * cos(axleAngle)-axleContainerDepth*Math.cos(MathUtils
	 * .degreesToRadians*(90-
	 * axleAngle*MathUtils.radiansToDegrees))),(float)(leftAxleContainer
	 * .getPosition
	 * ().y-axleContainerDepth*Math.sin(MathUtils.degreesToRadians*(90
	 * -axleAngle*
	 * MathUtils.radiansToDegrees))-axleContainerHeight/2*Math.sin(MathUtils
	 * .degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))))); //
	 * leftAxleBodyDef.position.set(new
	 * Vector2((float)(player.getPosition().x-axleContainerDistance
	 * -axleContainerHeight
	 * *Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils
	 * .radiansToDegrees
	 * ))),(float)(player.getPosition().y-axleContainerDepth-axleContainerHeight
	 * *
	 * Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees
	 * ))))); leftAxleBodyDef.angle = -axleAngle;
	 * leftAxle=world.createBody(leftAxleBodyDef);
	 * leftAxle.createFixture(leftAxleFixture);
	 * leftAxle.setType(BodyType.DynamicBody); //
	 * leftAxle.setFixedRotation(false); leftAxle.setUserData("leftAxle"); //
	 * leftAxle.setPosition(new
	 * Vector2((float)(carPosX/worldScale-axleContainerDistance
	 * /worldScale-axleContainerHeight
	 * /worldScale*Math.cos((90-axleAngle)*degreesToRadians
	 * )),(float)(carPosY/worldScale
	 * +axleContainerDepth/worldScale+axleContainerHeight
	 * /worldScale*Math.sin((90-axleAngle)*degreesToRadians)))); PolygonShape
	 * rightAxleShape = new PolygonShape();
	 * rightAxleShape.setAsBox(axleContainerWidth/2,axleContainerHeight,new
	 * Vector2(axleContainerWidth/2,axleContainerHeight),-axleAngle); FixtureDef
	 * rightAxleFixture = new FixtureDef(); rightAxleFixture.density=0.5f;
	 * rightAxleFixture.friction=3; rightAxleFixture.restitution=0;
	 * rightAxleFixture.shape=rightAxleShape; //
	 * rightAxleFixture.filter.groupIndex=-1;
	 * rightAxleFixture.filter.categoryBits=5; rightAxleFixture.filter.maskBits
	 * =11; BodyDef rightAxleBodyDef = new BodyDef(); //
	 * rightAxleBodyDef.type=BodyType.DynamicBody; //
	 * rightAxleBodyDef.position.set(new
	 * Vector2((float)(carPosX+axleContainerDistance
	 * +axleContainerHeight*Math.cos
	 * ((90-axleAngle))),(float)(carPosY+axleContainerDepth
	 * +axleContainerHeight*Math.sin((90-axleAngle)))));
	 * rightAxleBodyDef.position.set(new
	 * Vector2((float)(rightAxleContainer.getPosition
	 * ().x+axleContainerWidth*Math
	 * .cos(axleAngle)+axleContainerDepth*Math.cos(MathUtils
	 * .degreesToRadians*(90
	 * -axleAngle*MathUtils.radiansToDegrees))),(float)(rightAxleContainer
	 * .getPosition
	 * ().y-axleContainerDepth*Math.sin(MathUtils.degreesToRadians*(90
	 * -axleAngle*
	 * MathUtils.radiansToDegrees))-axleContainerHeight/2*Math.sin(MathUtils
	 * .degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))))); //
	 * rightAxleBodyDef.position.set(new
	 * Vector2((float)(player.getPosition().x+axleContainerDistance
	 * +axleContainerHeight
	 * *Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils
	 * .radiansToDegrees
	 * ))),(float)(player.getPosition().y-axleContainerDepth-axleContainerHeight
	 * *
	 * Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees
	 * ))))); rightAxleBodyDef.angle = axleAngle; rightAxle =
	 * world.createBody(rightAxleBodyDef);
	 * rightAxle.createFixture(rightAxleFixture);
	 * rightAxle.setType(BodyType.DynamicBody); //
	 * leftAxle.setFixedRotation(false); rightAxle.setUserData("rightAxle"); //
	 * rightAxle.SetPosition(new
	 * Vector2(carPosX/worldScale+axleContainerDistance
	 * /worldScale+axleContainerHeight
	 * /worldScale*Math.cos((90-axleAngle)*degreesToRadians
	 * ),carPosY/worldScale+axleContainerDepth
	 * /worldScale+axleContainerHeight/worldScale
	 * *Math.sin((90-axleAngle)*degreesToRadians))); //
	 * 
	 * 
	 * 
	 * //Fixed foints // DistanceJointDef leftAxleContainerDistanceJointDef=new
	 * DistanceJointDef(); WeldJointDef weldJointDef = new WeldJointDef();
	 * weldJointDef.initialize(player, leftAxleContainer,
	 * leftAxleContainer.getWorldCenter()); leftWeldJoint =
	 * world.createJoint(weldJointDef);
	 * 
	 * // RevoluteJointDef leftAxleContainerRevoluteJointDef=new
	 * RevoluteJointDef(); //
	 * leftAxleContainerRevoluteJointDef.initialize(player
	 * ,leftAxleContainer,leftAxleContainer.getPosition()); //
	 * leftAxleContainerRevoluteJointDef.enableMotor=true; //
	 * leftAxleContainerRevoluteJointDef.enableLimit = true; //
	 * leftAxleContainerRevoluteJoint=(RevoluteJoint)
	 * world.createJoint(leftAxleContainerRevoluteJointDef);// as
	 * b2RevoluteJoint; //
	 * leftAxleContainerRevoluteJoint.setMaxMotorTorque(100f); //
	 * leftAxleContainerRevoluteJoint.setLimits(-1f,1f);
	 * 
	 * // leftAxleContainerDistanceJointDef.initialize(player,
	 * leftAxleContainer, new Vector2
	 * (player.getPosition().x-carWidth,player.getPosition().y-carHeight),
	 * leftAxleContainer.getPosition()); //
	 * leftAxleContainerDistanceJointDef.collideConnected = true; //
	 * leftAxleContainerDistanceJointDef.frequencyHz = 4.0f; //
	 * leftAxleContainerDistanceJointDef.dampingRatio = 0.5f; ////
	 * leftAxleContainerDistanceJoint.setLength(.01f); //
	 * leftAxleContainerDistanceJoint=(DistanceJoint)
	 * world.createJoint(leftAxleContainerDistanceJointDef);// as
	 * b2RevoluteJoint;
	 * 
	 * 
	 * WeldJointDef weldJointDefRight = new WeldJointDef();
	 * weldJointDefRight.initialize(player, rightAxleContainer,
	 * rightAxleContainer.getWorldCenter()); rightWeldJoint =
	 * world.createJoint(weldJointDefRight); // DistanceJointDef
	 * rightAxleContainerDistanceJointDef=new DistanceJointDef(); //
	 * rightAxleContainerDistanceJointDef.initialize(player, rightAxleContainer,
	 * player.getPosition(), rightAxleContainer.getPosition()); //
	 * rightAxleContainerDistanceJointDef.collideConnected = true; //
	 * leftAxleContainerDistanceJointDef.dampingRatio = .1f; //
	 * rightAxleContainerDistanceJoint.setLength(.1f); //
	 * rightAxleContainerDistanceJoint=(DistanceJoint)
	 * world.createJoint(rightAxleContainerDistanceJointDef);// as
	 * b2RevoluteJoint;
	 * 
	 * WeldJointDef weldJointDefRider = new WeldJointDef();
	 * weldJointDefRider.initialize(player, riderBody,
	 * riderBody.getWorldCenter()); riderWeldJoint =
	 * world.createJoint(weldJointDefRider);
	 * 
	 * RopeJointDef ropeJointDef = new RopeJointDef(); ropeJointDef.bodyA =
	 * player; ropeJointDef.bodyB = riderBody; ropeJointDef.localAnchorA.set(new
	 * Vector2(0,0)); ropeJointDef.localAnchorB.set(new Vector2(0,0));
	 * ropeJointDef.maxLength = .10f; ropeJointDef.collideConnected = true;
	 * world.createJoint(ropeJointDef);
	 * 
	 * // RopeJointDef ropeJointDef = new RopeJointDef(); // ropeJointDef.bodyA
	 * = riderBody; // ropeJointDef.bodyB = player; //
	 * ropeJointDef.localAnchorB.set(new Vector2(0,0)); //
	 * ropeJointDef.localAnchorA.set(new
	 * Vector2(PhysicsHelper.ConvertToBox(manWidth
	 * /2),PhysicsHelper.ConvertToBox(manHeight/2))); // ropeJointDef.maxLength
	 * = 0.50f; // ropeJointDef.collideConnected = true; //
	 * world.createJoint(ropeJointDef);
	 * 
	 * // FrictionJointDef frictionJointDef = new FrictionJointDef(); //
	 * frictionJointDef.bodyA = riderBody; // frictionJointDef.bodyB = player;
	 * // frictionJointDef.localAnchorB.set(new Vector2(0,0)); //
	 * frictionJointDef.localAnchorA.set(new
	 * Vector2(PhysicsHelper.ConvertToBox(manWidth
	 * /2),PhysicsHelper.ConvertToBox(manHeight/2))); //
	 * frictionJointDef.initialize(player, riderBody, riderBody.getPosition());
	 * // frictionJointDef.maxForce = 0.5f; // frictionJointDef.maxTorque =
	 * 0.5f; // frictionJointDef.collideConnected =true; //
	 * world.createJoint(frictionJointDef);
	 * 
	 * // FrictionJointDef frictionJointDef = new FrictionJointDef(); //
	 * frictionJointDef.bodyA = riderBody; // frictionJointDef.bodyB = player;
	 * // frictionJointDef.localAnchorB.set(new Vector2(0,0)); //
	 * frictionJointDef.localAnchorA.set(new
	 * Vector2(PhysicsHelper.ConvertToBox(manWidth
	 * /2),PhysicsHelper.ConvertToBox(manHeight/2))); //
	 * frictionJointDef.initialize(player, riderBody, riderBody.getPosition());
	 * // frictionJointDef.maxForce = 0.5f; // frictionJointDef.maxTorque =
	 * 0.5f; // frictionJointDef.collideConnected =true; //
	 * world.createJoint(frictionJointDef);
	 * 
	 * 
	 * // PrismaticJointDef // PrismaticJointDef riderPrismaticJointDef=new
	 * PrismaticJointDef(); // riderPrismaticJointDef.lowerTranslation=0; //
	 * riderPrismaticJointDef.upperTranslation=0; //
	 * riderPrismaticJointDef.enableLimit=true; //
	 * riderPrismaticJointDef.enableMotor=true; //
	 * riderPrismaticJointDef.initialize
	 * (player,riderBody,riderBody.getWorldCenter(), new
	 * Vector2((float)(Math.cos
	 * (MathUtils.degreesToRadians*(90-riderAngle*MathUtils
	 * .radiansToDegrees))),(
	 * float)(-Math.sin(MathUtils.degreesToRadians*(90-riderAngle
	 * *MathUtils.radiansToDegrees))))); // riderPrismaticJoint=(PrismaticJoint)
	 * world.createJoint(riderPrismaticJointDef);// as b2PrismaticJoint; //
	 * riderPrismaticJoint.setMaxMotorForce(10f); //
	 * riderPrismaticJoint.setMotorSpeed(10f); //
	 * riderPrismaticJoint.setLimits(-.25f, .25f);
	 * 
	 * 
	 * // ************************ REVOLUTE JOINTS ************************ //
	 * RevoluteJointDef leftWheelRevoluteJointDef=new RevoluteJointDef();
	 * leftWheelRevoluteJointDef
	 * .initialize(leftWheel,leftAxle,leftWheel.getWorldCenter());
	 * leftWheelRevoluteJointDef.enableMotor=true; //
	 * leftWheelRevoluteJointDef.enableLimit = true;
	 * leftWheelRevoluteJoint=(RevoluteJoint)
	 * world.createJoint(leftWheelRevoluteJointDef);// as b2RevoluteJoint;
	 * leftWheelRevoluteJoint.setMaxMotorTorque(10f); //
	 * leftWheelRevoluteJoint.setLimits(-180f,180f); //
	 * leftWheelRevoluteJoint.setMotorSpeed(1f); //
	 * leftWheelRevoluteJoint.setLimits(MathUtils.degreesToRadians*-.01f,
	 * MathUtils.degreesToRadians*.01f); RevoluteJointDef
	 * rightWheelRevoluteJointDef=new RevoluteJointDef();
	 * rightWheelRevoluteJointDef
	 * .initialize(rightWheel,rightAxle,rightWheel.getWorldCenter());
	 * rightWheelRevoluteJointDef.enableMotor=true; //
	 * rightWheelRevoluteJointDef.enableLimit = true;
	 * rightWheelRevoluteJoint=(RevoluteJoint)
	 * world.createJoint(rightWheelRevoluteJointDef);// as b2RevoluteJoint;
	 * rightWheelRevoluteJoint.setMaxMotorTorque(10f); //
	 * rightWheelRevoluteJoint.setMotorSpeed(1f); //
	 * rightWheelRevoluteJoint.setLimits(-180f,180f);
	 * 
	 * 
	 * // // ************************ PRISMATIC JOINTS ************************
	 * // // PrismaticJointDef leftAxlePrismaticJointDef=new
	 * PrismaticJointDef(); leftAxlePrismaticJointDef.lowerTranslation=0;
	 * leftAxlePrismaticJointDef.upperTranslation=axleContainerDepth;
	 * leftAxlePrismaticJointDef.enableLimit=true;
	 * leftAxlePrismaticJointDef.enableMotor=true; //
	 * leftAxlePrismaticJointDef.initialize
	 * (player,leftAxle,leftAxle.getWorldCenter(), new
	 * Vector2((float)(Math.cos(MathUtils
	 * .degreesToRadians*(90-axleAngle*MathUtils
	 * .radiansToDegrees))),(float)(Math
	 * .sin(MathUtils.degreesToRadians*(90-axleAngle
	 * *MathUtils.radiansToDegrees)))));
	 * leftAxlePrismaticJointDef.initialize(leftAxleContainer
	 * ,leftAxle,leftAxle.getWorldCenter(), new
	 * Vector2((float)(Math.cos(MathUtils
	 * .degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))),
	 * (float)(Math
	 * .sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees
	 * ))))); leftAxlePrismaticJoint=(PrismaticJoint)
	 * world.createJoint(leftAxlePrismaticJointDef);// as b2PrismaticJoint;
	 * leftAxlePrismaticJoint.setMaxMotorForce(10f);
	 * leftAxlePrismaticJoint.setMotorSpeed(10f);
	 * leftAxlePrismaticJoint.setLimits(0.02f, 0.025f);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // PrismaticJointDef rightAxlePrismaticJointDef=new PrismaticJointDef();
	 * rightAxlePrismaticJointDef.lowerTranslation=0;
	 * rightAxlePrismaticJointDef.upperTranslation=axleContainerDepth;
	 * rightAxlePrismaticJointDef.enableLimit=true;
	 * rightAxlePrismaticJointDef.enableMotor=true;
	 * rightAxlePrismaticJointDef.initialize
	 * (rightAxleContainer,rightAxle,rightAxle.getWorldCenter(), new
	 * Vector2((float
	 * )(-Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils
	 * .radiansToDegrees
	 * ))),(float)(Math.sin(MathUtils.degreesToRadians*(90-axleAngle
	 * *MathUtils.radiansToDegrees)))));
	 * rightAxlePrismaticJoint=(PrismaticJoint)
	 * world.createJoint(rightAxlePrismaticJointDef);// as b2PrismaticJoint;
	 * rightAxlePrismaticJoint.setMaxMotorForce(10f);
	 * rightAxlePrismaticJoint.setMotorSpeed(10f);
	 * rightAxlePrismaticJoint.setLimits(0.02f, 0.025f); }
	 */

	/*
	 * // private Body createPlayer() { // BodyDef def = new BodyDef(); //
	 * def.type = BodyType.DynamicBody; // def.position.set(Helper.w2p(x),
	 * Helper.w2p(y)); // // Body box = world.createBody(def); // //
	 * PolygonShape poly = new PolygonShape(); //// poly.setAsBox(0.45f, 1.4f);
	 * // poly.setAsBox(Helper.w2p(width)/2, Helper.w2p(height)/2); //
	 * Helper.println("box width and height::::"+Helper.w2p(width)/2+
	 * "and "+Helper.w2p(height)/2); // playerPhysicsFixture =
	 * box.createFixture(poly, 1); // playerPhysicsFixture.setUserData(this); //
	 * poly.dispose(); // CircleShape circle = new CircleShape(); //
	 * circle.setRadius(1f); // circle.setPosition(new Vector2(Helper.w2p(x),
	 * (Helper.w2p(y))*.5f)); // playerSensorFixture = box.createFixture(circle,
	 * 0); // circle.dispose(); // box.setUserData(this); //
	 * box.setBullet(true); // return box; // }
	 */

	/**
	 * Add all the texture needed by this element in this function
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(imagePath);
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

		// if(Constants.ballSelected == 1)
		// this.image =
		// Helper.getImageFromAssets(AssetConstants.IMG_BASKET_BALL);
		// if(Constants.ballSelected == 2)
		// this.image =
		// Helper.getImageFromAssets(AssetConstants.IMG_RUBBER_BALL);
		// if(Constants.ballSelected == 3)
		// this.image = Helper.getImageFromAssets(AssetConstants.IMG_BOWL_BALL);
		// else this.image = Helper.getImageFromAssets(imagePath);
//		this.image = Helper.getImageFromAssets(imagePath);
		this.imageWheelLeft = Helper
				.getImageFromAssets(AssetConstants.IMG_BIKE_WHEEL);
		this.imageWheelRight = Helper
				.getImageFromAssets(AssetConstants.IMG_BIKE_WHEEL);
		this.imageLeftaxle = Helper.getImageFromAssets(AssetConstants.IMG_AXLE);
		this.imageRightaxle = Helper
				.getImageFromAssets(AssetConstants.IMG_AXLE);
		this.imageRider = Helper.getImageFromAssets(AssetConstants.IMG_RIDER);
		this.imageBikeBody = Helper
				.getImageFromAssets(AssetConstants.IMG_BIKE_BODY);

		cam = GlobalVars.ge.getScreen().cam;
//		cam = new OrthographicCamera(Helper.getScreenWidth(), Helper.getScreenHeight());
//        cam.position.set(Helper.getScreenWidth()/2f, Helper.getScreenHeight()/2f, 0);

//		cam.position.set(0, 0, 0);
//        batch = GlobalVars.ge.getScreen().getBatch();
        
		agerX = cam.position.x;
		agerY = cam.position.y;
		// carPhysicsBody.init();

		leftWheelRevoluteJoint.setMotorSpeed(0);
		rightWheelRevoluteJoint.setMotorSpeed(0);

	}

	public boolean shallAccelarate = false;
	public float accelarateVal = 0.1f;

	@Override
	public void step(long stepTime) {
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
//		Helper.println("bike wheels are  in ground............");
		updatePhysicsWorld();
		checkInput();
//		cam.update();
//        batch.setProjectionMatrix(cam.combined);
		// cam.zoom = 2.5f;
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

				leftAxleContainer.setAngularVelocity(0);
				leftAxleContainer.applyLinearImpulse(
						(float) (accForce * (Math.abs(acc)-threshold + 1) * Math.cos(player.getAngle() + 90
								* MathUtils.degreesToRadians)),
						(float) (accForce * Math.sin(player.getAngle() + 90
								* MathUtils.degreesToRadians)),
						leftAxleContainer.getWorldCenter().x,
						leftAxleContainer.getWorldCenter().y);

			} else if (acc > 0) // positive
			{
				// rightAxleContainer.applyLinearImpulse(0f, -0.05f,
				// rightAxleContainer.getPosition().x,
				// rightAxleContainer.getPosition().y);

				rightAxleContainer.setAngularVelocity(0);
				rightAxleContainer.applyLinearImpulse(
						(float) (accForce * (Math.abs(acc)-threshold)* Math.cos(player.getAngle() + 90
								* MathUtils.degreesToRadians)),
						(float) (accForce * Math.sin(player.getAngle() + 90
								* MathUtils.degreesToRadians)),
						rightAxleContainer.getWorldCenter().x,
						rightAxleContainer.getWorldCenter().y);
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

		// ************************ THE CAR ************************ //
		this.createCarBody();
		this.createFumeBody();
		// this.carBodyFromJson();
		// ************************ THE Rider ************************ //
		this.createRiderBody();

		// ************************ LEFT AXLE CONTAINER ************************
		this.createAxleContainer();

		// ************************ THE WHEELS ************************ //;
		this.createWheel();

		// ************************ THE AXLES ************************ //
		this.createAxle();
		 fumeJoint = this.createWeldJoint( player, fumeBody,
					fumeBody.getPosition());
		
		riderWeldJoint = this.createWeldJoint(player, riderBody,
				riderBody.getWorldCenter());
		leftWeldJoint = this.createWeldJoint(player, leftAxleContainer,
				leftAxleContainer.getWorldCenter());
		rightWeldJoint = this.createWeldJoint(player, rightAxleContainer,
				rightAxleContainer.getWorldCenter());
		
		Vector2 axis = new Vector2(0, 1);
		leftAxlePrismaticJoint = this.createPrismaticJoint(
				leftAxleContainer,
				leftAxle,
				leftAxle.getWorldCenter(),axis);
		rightAxlePrismaticJoint = this.createPrismaticJoint(
				rightAxleContainer,
				rightAxle,
				rightAxle.getWorldCenter(),axis);
		/*leftAxlePrismaticJoint = this.createPrismaticJoint(
				leftAxleContainer,
				leftAxle,
				leftAxle.getWorldCenter(),
				new Vector2((float) (Math.cos(MathUtils.degreesToRadians
						* (90 - leftAxleAngle * MathUtils.radiansToDegrees))),
						(float) (Math.sin(MathUtils.degreesToRadians
								* (90 - leftAxleAngle
										* MathUtils.radiansToDegrees)))));
		rightAxlePrismaticJoint = this.createPrismaticJoint(
				rightAxleContainer,
				rightAxle,
				rightAxle.getWorldCenter(),
				new Vector2((float) (-Math.cos(MathUtils.degreesToRadians
						* (90 - rightAxleAngle * MathUtils.radiansToDegrees))),
						(float) (Math.sin(MathUtils.degreesToRadians
								* (90 - rightAxleAngle
										* MathUtils.radiansToDegrees)))));*/
		/*
		leftAxlePrismaticJoint = this.createPrismaticJoint(
				leftAxleContainer,
				leftAxle,
				leftAxle.getWorldCenter(),
				new Vector2((float) (Math.cos(MathUtils.degreesToRadians
						* (180 + leftAxleAngle * MathUtils.radiansToDegrees))),
						(float) (Math.sin(MathUtils.degreesToRadians
								* (180 + leftAxleAngle
										* MathUtils.radiansToDegrees)))));
		rightAxlePrismaticJoint = this.createPrismaticJoint(
				rightAxleContainer,
				rightAxle,
				rightAxle.getWorldCenter(),
				new Vector2((float) (-Math.cos(MathUtils.degreesToRadians
						* (90 + rightAxleAngle * MathUtils.radiansToDegrees))),
						(float) (Math.sin(MathUtils.degreesToRadians
								* (90 + rightAxleAngle
										* MathUtils.radiansToDegrees)))));*/
		
		leftWheelRevoluteJoint = this.createRevoluteJoint(leftWheel, leftAxle,
				leftWheel.getWorldCenter());
		// Helper.println("joint null or not:::"+ leftWheelRevoluteJoint);
		rightWheelRevoluteJoint = this.createRevoluteJoint(rightWheel,
				rightAxle, rightWheel.getWorldCenter());
		// Helper.println("joint null or not:::"+ rightWheelRevoluteJoint);

		setDensities();
	}

	private void setDensity(Body body, float density) {
		body.getFixtureList().get(0).setDensity(density);
	}

	private void setDensities() {
//		 setDensity(rightAxle, 1000);
//		 setDensity(rightWheel, 1);
		// setDensity(rightAxleContainer, 100);
	}

	private PrismaticJoint createPrismaticJoint(Body bodyA, Body bodyB,
			Vector2 anchor, Vector2 axis) {
		// TODO Auto-generated method stub
		// PrismaticJointDef
		PrismaticJointDef prismaticJointDef = new PrismaticJointDef();
		prismaticJointDef.lowerTranslation = 0f;
		prismaticJointDef.upperTranslation = 0f;
		prismaticJointDef.enableLimit = true;
		prismaticJointDef.enableMotor = true;
		// leftAxlePrismaticJointDef.initialize(player,leftAxle,leftAxle.getWorldCenter(),
		// new
		// Vector2((float)(Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))),(float)(Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees)))));
		prismaticJointDef.initialize(bodyA, bodyB, anchor, axis);

		PrismaticJoint prismaticJoint = (PrismaticJoint) world
				.createJoint(prismaticJointDef);// as PrismaticJoint;
		prismaticJoint.setMaxMotorForce(10f);
		prismaticJoint.setMotorSpeed(10f);
//		 prismaticJoint.setLimits(-.25f, 0.25f);
		prismaticJoint.setLimits(-0.0f, 0.0f);
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
		revoluteJointDef.enableMotor = true;
		// leftWheelRevoluteJointDef.enableLimit = true;
		// RevoluteJoint revoluteJoint=(RevoluteJoint)
		// world.createJoint(revoluteJointDef);// as b2RevoluteJoint;
		// revoluteJoint.setMaxMotorTorque(10f);
		// jointName = revoluteJoint;
		RevoluteJoint jointName = (RevoluteJoint) world
				.createJoint(revoluteJointDef);// as b2RevoluteJoint;
		jointName.setMaxMotorTorque(10f);
		jointName.setMotorSpeed(10f);
		return jointName;

	}

	private WeldJoint createWeldJoint(Body bodyA, Body bodyB, Vector2 anchor) {
		// TODO Auto-generated method stub
		WeldJointDef weldJointDef = new WeldJointDef();
		weldJointDef.initialize(bodyA, bodyB, anchor);
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

		PhysicsBody2 pb = new PhysicsBody2(
				PhysicsHelper.ConvertToWorld((float) (leftWheel
						.getWorldCenter().x - axleWidth / 2
						* Math.cos(/* Math.PI/2- */leftAxleAngle))),
				PhysicsHelper.ConvertToWorld((float) (leftWheel
						.getWorldCenter().y + axleWidth / 2
						* Math.sin(/* Math.PI/2- */leftAxleAngle))),
				PhysicsHelper.ConvertToWorld(axleWidth), 0f,
				AssetConstants.PHY_AXLE, AssetConstants.PHY_IMG_LEFTAXLE,
				"axle", world, ((short) -1), ((short) 4), ((short) (11)),
				"leftaxle", 5, BodyType.DynamicBody);

		pb.getBody().setTransform(pb.getBody().getPosition(),
				pb.getBody().getAngle() - leftAxleAngle);

		GlobalVars.ge.getCurrentStage().addElement(pb);
		leftAxle = pb.getBody();
		leftAxle.setUserData("leftAxle");

		PhysicsBody2 pb2 = new PhysicsBody2(
				PhysicsHelper.ConvertToWorld((float) (rightWheel
						.getWorldCenter().x - axleWidth / 2
						* Math.cos(Math.PI / 2 - rightAxleAngle))),
				PhysicsHelper.ConvertToWorld((float) (rightWheel
						.getWorldCenter().y - axleWidth / 2
						* Math.cos(Math.PI / 2 - rightAxleAngle))),
				PhysicsHelper.ConvertToWorld(axleWidth), 0f,
				AssetConstants.PHY_AXLE, AssetConstants.PHY_IMG_LEFTAXLE,
				"axle", world, ((short) -1), ((short) 5), ((short) (11)),
				"rightaxle", 6, BodyType.DynamicBody);

		pb2.getBody().setTransform(pb2.getBody().getPosition(),
				pb2.getBody().getAngle() + rightAxleAngle);

		GlobalVars.ge.getCurrentStage().addElement(pb2);
		rightAxle = pb2.getBody();
		rightAxle.setUserData("rightAxle");		
	}

	private void createAxleContainer() {
		// TODO Auto-generated method stub
		// PolygonShape leftAxleContainerShape = new PolygonShape();
		// leftAxleContainerShape.setAsBox(axleContainerWidth,axleContainerHeight,new
		// Vector2(axleContainerWidth,axleContainerHeight),axleAngle);
		// //
		// leftAxleContainerShape.setAsBox(axleContainerWidth,axleContainerHeight);
		// FixtureDef leftAxleContainerFixture = new FixtureDef();
		// leftAxleContainerFixture.density=1f;
		// leftAxleContainerFixture.friction=3f;
		// leftAxleContainerFixture.restitution=0.3f;
		// // leftAxleContainerFixture.filter.groupIndex=-1;
		// leftAxleContainerFixture.filter.categoryBits=2;
		// // leftAxleContainerFixture.filter.maskBits = 11;
		//
		// leftAxleContainerFixture.shape=leftAxleContainerShape;
		// BodyDef leftAxleContainerDef = new BodyDef();
		// // leftAxleContainerDef.position.set(new
		// Vector2((float)(player.getPosition().x-2*axleContainerH*Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))-axleContainerDistance-axleContainerDepth*Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))),
		// //
		// (float)(player.getPosition().y-axleContainerDepth+0*axleContainerHeight*Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees)))));
		// leftAxleContainerDef.position.set(carWidth +
		// (float)(player.getPosition().x-axleContainerDistance-axleContainerHeight/2*Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))),(float)(player.getPosition().y-axleContainerDepth-axleContainerHeight/2*(float)Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))));
		// leftAxleContainerDef.type=BodyType.DynamicBody;
		// leftAxleContainerDef.angle = -axleAngle;
		// leftAxleContainer = world.createBody(leftAxleContainerDef);
		// leftAxleContainer.createFixture(leftAxleContainerFixture);
		// leftAxleContainer.setFixedRotation(false);
		PhysicsBody2 pb = new PhysicsBody2(
				PhysicsHelper
						.ConvertToWorld((float) (player.getWorldCenter().x
								- axleContainerDistance/*-axleContainerHeight*Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))*/- axleContainerWidth
								/ 2
								* Math.cos(/* Math.PI/2- */leftAxleContainerAngle))),
				PhysicsHelper.ConvertToWorld((float) (player.getWorldCenter().y
						- axleContainerDepth/*-axleContainerHeight/2*(float)Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))*/+ axleContainerWidth
						/ 2 * Math.sin(/* Math.PI/2- */leftAxleContainerAngle))),
				PhysicsHelper.ConvertToWorld(axleContainerWidth), 0f,
				AssetConstants.PHY_AXLE_CONTAINER,
				AssetConstants.PHY_IMG_LEFTAXLECONTAINER, "leftaxlecontainer",
				world, ((short) -1), ((short) 2), ((short) (11)),
				"leftaxlecontainer", 3, BodyType.DynamicBody);

		// pb.getBody().getFixtureList().get(0).setDensity(50);
		pb.getBody().setTransform(pb.getBody().getPosition(),
				pb.getBody().getAngle() - leftAxleContainerAngle);
		// pb.getBody().setTransform((float)
		// (pb.getBody().getPosition().x-Math.tanh(axleAngle)*axleContainerHeight),pb.getBody().getPosition().y-axleContainerHeight,
		// pb.getBody().getAngle());

		GlobalVars.ge.getCurrentStage().addElement(pb);
		leftAxleContainer = pb.getBody();
		leftAxleContainer.setUserData("leftAxleContainer");

		// ************************ RIGHT AXLE CONTAINER
		// ************************ //
		// PolygonShape rightAxleContainerShape = new PolygonShape();
		// rightAxleContainerShape.setAsBox(axleContainerWidth,axleContainerHeight,new
		// Vector2(axleContainerWidth,axleContainerHeight),-axleAngle);
		// FixtureDef rightAxleContainerFixture = new FixtureDef();
		// rightAxleContainerFixture.density=1f;
		// rightAxleContainerFixture.friction=3;
		// rightAxleContainerFixture.restitution=0.3f;
		// // rightAxleContainerFixture.filter.groupIndex=-1;
		// rightAxleContainerFixture.filter.categoryBits=3;
		// // rightAxleContainerFixture.filter.maskBits = 11;
		//
		// rightAxleContainerFixture.shape=rightAxleContainerShape;
		// BodyDef rightAxleContainerDef = new BodyDef();
		//
		// // rightAxleContainerDef.position.set(new
		// Vector2((float)(player.getPosition().x+2*axleContainerWidth*Math.cos(90-axleAngle)+axleContainerDistance+axleContainerDepth*Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))),
		// //
		// (float)(player.getPosition().y-axleContainerDepth+0*axleContainerHeight*Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees)))));
		// rightAxleContainerDef.position.set(carWidth+(float)(player.getPosition().x+axleContainerDistance+axleContainerHeight/2*Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))),(float)(player.getPosition().y-axleContainerDepth-axleContainerHeight/2*(float)Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))));
		// rightAxleContainerDef.type=BodyType.DynamicBody;
		// rightAxleContainerDef.angle = axleAngle;
		// rightAxleContainer = world.createBody(rightAxleContainerDef);
		// rightAxleContainer.createFixture(rightAxleContainerFixture);
		// rightAxleContainer.setFixedRotation(false);

		PhysicsBody2 pb2 = new PhysicsBody2(
				PhysicsHelper.ConvertToWorld((float) (player.getWorldCenter().x
						+ axleContainerDistance/*
												 * +axleContainerHeight*Math.cos(
												 * MathUtils
												 * .degreesToRadians*(90
												 * -axleAngle
												 * *MathUtils.radiansToDegrees))
												 */- axleContainerWidth / 2
						* Math.cos(/* Math.PI/2- */rightAxleContainerAngle))),
				PhysicsHelper.ConvertToWorld((float) (player.getWorldCenter().y
						- axleContainerDepth - /*
												 * axleContainerHeight/2*(float)Math
												 * .
												 * sin(MathUtils.degreesToRadians
												 * *(90-axleAngle*MathUtils.
												 * radiansToDegrees))-
												 */axleContainerWidth / 2
						* Math.cos(/* Math.PI/2- */rightAxleContainerAngle))),
				PhysicsHelper.ConvertToWorld(axleContainerWidth), 0f,
				AssetConstants.PHY_AXLE_CONTAINER,
				AssetConstants.PHY_IMG_LEFTAXLECONTAINER, "leftaxlecontainer",
				world, ((short) -1), ((short) 3), ((short) (11)),
				"rightaxlecontainer", 4, BodyType.DynamicBody);

		// pb2.getBody().getFixtureList().get(0).setDensity(50);
		pb2.getBody().setTransform(pb2.getBody().getPosition(),
				pb2.getBody().getAngle() + rightAxleContainerAngle);
		// pb2.getBody().setTransform((float)
		// (pb2.getBody().getPosition().x+Math.tanh(axleAngle)*axleContainerHeight),pb2.getBody().getPosition().y-axleContainerHeight,
		// pb2.getBody().getAngle());

		GlobalVars.ge.getCurrentStage().addElement(pb2);
		rightAxleContainer = pb2.getBody();
		rightAxleContainer.setUserData("rightAxleContainer");
	}

	private void createWheel() {
		// TODO Auto-generated method stub
		// CircleShape wheelShape =new CircleShape();//((float)
		// (wheelRadius/worldScale))
		// wheelShape.setRadius(wheelRadius);
		// FixtureDef wheelFixture = new FixtureDef();
		// wheelFixture.density=10f;
		// wheelFixture.friction=15;
		// wheelFixture.restitution=0.2f;
		// // wheelFixture.filter.groupIndex=-1;
		// wheelFixture.filter.categoryBits=7;
		// wheelFixture.filter.maskBits =11;
		// wheelFixture.shape=wheelShape;
		// BodyDef leftwheelBodyDef = new BodyDef();
		// // wheelBodyDef.type=BodyType.DynamicBody;
		// //
		// wheelBodyDef.position.set((float)(carPosX-axleContainerDistance-2*axleContainerHeight*Math.cos((90-axleAngle))),(float)(carPosY+axleContainerDepth-2*axleContainerHeight*(float)Math.sin((90-axleAngle))));
		// leftwheelBodyDef.position.set((float)(player.getPosition().x+carWidth-axleContainerDistance-2*axleContainerHeight*Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))),
		// (float)(player.getPosition().y-axleContainerDepth-2*axleContainerHeight*(float)Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))));
		// // wheelBodyDef.position.set(carPosX, carPosY);
		// // Body
		// leftWheel=world.createBody(leftwheelBodyDef);
		// leftWheel.createFixture(wheelFixture);
		// leftWheel.setType(BodyType.DynamicBody);

		PhysicsBody2 pb = new PhysicsBody2(
				PhysicsHelper.ConvertToWorld((float) (player.getWorldCenter().x
						- axleContainerDistance
						- 2
						* axleContainerHeight
						* Math.cos(MathUtils.degreesToRadians
								* (90 - leftAxleAngle
										* MathUtils.radiansToDegrees)) - wheelRadius/**
				 * 
				 * 
				 * 
				 * Math.cos(Math.PI/2-axleAngle)
				 */
				)),
				PhysicsHelper.ConvertToWorld((float) (player.getWorldCenter().y
						- axleContainerDepth
						- 2
						* axleContainerHeight
						* (float) Math.sin(MathUtils.degreesToRadians
								* (90 - leftAxleAngle
										* MathUtils.radiansToDegrees)) - wheelRadius/**
				 * 
				 * 
				 * 
				 * Math.sin(Math.PI/2-axleAngle)
				 */
				)), PhysicsHelper.ConvertToWorld(wheelRadius * 2), 0f,
				AssetConstants.PHY_WHEEL, AssetConstants.PHY_IMG_WHEEL,
				"wheel", world, ((short) -1), ((short) 6), ((short) (11)),
				"leftWheel", 7, BodyType.DynamicBody);
		// new Point().getPointAtDistance(srcPoint, destinationPoint, retPoint,
		// distance)
		// pb.getBody().setTransform(pb.getBody().getPosition(),
		// pb.getBody().getAngle()-axleAngle);

		GlobalVars.ge.getCurrentStage().addElement(pb);
		leftWheel = pb.getBody();
		leftWheel.setUserData("leftWheel");
		// passing to the game world
		// BodyDef rightwheelBodyDef = new BodyDef();
		// //
		// wheelBodyDef.position.set((float)(carPosX+axleContainerDistance+2*axleContainerHeight*Math.cos((90-axleAngle))),(float)(carPosY+axleContainerDepth-2*axleContainerHeight*(float)Math.sin((90-axleAngle))));
		// rightwheelBodyDef.position.set(carWidth +
		// (float)(player.getPosition().x+axleContainerDistance+2*axleContainerHeight*Math.cos(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))),(float)(player.getPosition().y-axleContainerDepth-2*axleContainerHeight*(float)Math.sin(MathUtils.degreesToRadians*(90-axleAngle*MathUtils.radiansToDegrees))));
		// // Body
		// rightWheel=world.createBody(rightwheelBodyDef);
		// rightWheel.createFixture(wheelFixture);
		// rightWheel.setType(BodyType.DynamicBody);
		// rightWheel.setUserData("rightWheel");

		PhysicsBody2 pb2 = new PhysicsBody2(
				PhysicsHelper.ConvertToWorld((float) (player.getWorldCenter().x
						+ axleContainerDistance + 2
						* axleContainerHeight
						* Math.cos(MathUtils.degreesToRadians
								* (90 - rightAxleAngle
										* MathUtils.radiansToDegrees)))
						- wheelRadius),
				PhysicsHelper.ConvertToWorld((float) (player.getWorldCenter().y
						- axleContainerDepth - 2
						* axleContainerHeight
						* (float) Math.sin(MathUtils.degreesToRadians
								* (90 - rightAxleAngle
										* MathUtils.radiansToDegrees)))
						- wheelRadius),
				PhysicsHelper.ConvertToWorld(wheelRadius * 2), 0f,
				AssetConstants.PHY_WHEEL, AssetConstants.PHY_IMG_WHEEL,
				"wheel", world, ((short) -1), ((short) 7), ((short) (11)),
				"rightWheel", 8, BodyType.DynamicBody);
		// Point p = new Point(0,0);
		// p.x = PhysicsHelper.ConvertToWorld(player.getWorldCenter().x);
		// p.y = PhysicsHelper.ConvertToWorld(player.getWorldCenter().y);
		//
		// Helper.println("\n\n\nSource: " + p.toString());
		// Point d = new Point(0,0);
		// p.getPointAtDistance(p, -45, 200, d);
		//
		// Helper.println("Destination: " + d.toString());
		// Helper.println("Distance: " + p.distancePoint2Point(d));
		//
		// PhysicsBody2 pb2 = new PhysicsBody2(d.x, d.y,
		// PhysicsHelper.ConvertToWorld(wheelRadius*2),
		// 0f,
		// AssetConstants.PHY_WHEEL ,
		// AssetConstants.PHY_IMG_WHEEL,
		// "wheel",
		// world,
		// ((short) 7),
		// ((short) (1|11)),
		// "rightWheel",8,
		// BodyType.StaticBody);

		// pb.getBody().setTransform(pb.getBody().getPosition(),
		// pb.getBody().getAngle()-axleAngle);

		GlobalVars.ge.getCurrentStage().addElement(pb2);
		rightWheel = pb2.getBody();
		rightWheel.setUserData("rightWheel");

	}
	
	public Body getFumeBody()
	{
		return fumeBody;
	}
	
	private void createFumeBody() {

		PhysicsBody2 pb2 = new PhysicsBody2(Helper.p2w(player.getPosition().x) + 5 * LevelInfo.ratioX,
				Helper.p2w(player.getPosition().y )+27* LevelInfo.ratioX,				
				0.01f, 0f,
				AssetConstants.PHY_AXLE, AssetConstants.PHY_IMG_LEFTAXLE,
				"axle", world, ((short) -1), ((short) 5), ((short) (11)),
				"fume", 6, BodyType.DynamicBody);

//		pb2.getBody().setTransform(pb2.getBody().getPosition(),
//				pb2.getBody().getAngle() + rightAxleAngle);

		GlobalVars.ge.getCurrentStage().addElement(pb2);

		pb2.getBody().setUserData("fume");
		fumeBody = pb2.getBody();
		((BikeLevel)(GlobalVars.ge.getCurrentStage())).setFumeBody(pb2.getBody());
	}


	private void createRiderBody() {

		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld(player
				.getWorldCenter().x)
				- PhysicsHelper.ConvertToWorld(manWidth / 2) - LevelInfo.ratioX * 5,
				PhysicsHelper.ConvertToWorld(player.getWorldCenter().y)
						- PhysicsHelper.ConvertToWorld(manHeight / 2)- LevelInfo.ratioY * 5,
				PhysicsHelper.ConvertToWorld(manWidth)+ 5 * LevelInfo.ratioX, 0f,
				AssetConstants.PHY_BIKEMAN, AssetConstants.PHY_IMG_BIKEMAN,
				"bikeman", world, ((short) -1), ((short) 13),
				((short) (1 | 11)), "bikeman", 2, BodyType.DynamicBody);

		GlobalVars.ge.getCurrentStage().addElement(pb);
		riderBody = pb.getBody();
		riderBody.setUserData("riderBody");
	}

	private void createCarBody() {

		PhysicsBody2 pb = new PhysicsBody2(this.x, this.y,
				PhysicsHelper.ConvertToWorld(carWidth * 2f), carHeight,
				AssetConstants.PHY_CARBODY, AssetConstants.PHY_IMG_CARBODY,
				"carbody", world, ((short) -1), ((short) 1), ((short) 11),
				"carbody", 2, BodyType.DynamicBody);

		GlobalVars.ge.getCurrentStage().addElement(pb);
		player = pb.getBody();
		player.setUserData("carbody");

	}

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

}
