package com.rhymes.game.entity.elements.physical;


//import flash.display.Sprite;
//import flash.events.Event;

import java.awt.Event;
import java.awt.Menu;
import java.awt.event.KeyEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
//import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Shape;
//import com.badlogic.gdx.physics.box2d.;
//import Box2D.Dynamics.Joints.*;
import com.rhymes.game.data.App;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class PhysicsGame extends StageBase implements InteractionTouchCallbacks, InputProcessor{

   public int ITERATIONS       = 30; 
   public float TIME_STEP     = (float) (1.0/60.0f);
   public float SCREEN_WIDTH    = 800;
   public float SCREEN_HEIGHT   = 500;
   public float DRAW_SCALE      = 50;

//   private World world;
   private Body cart;
   private Body wheel1;
   private Body wheel2;
   private Body axle1;
   private Body axle2; 
   private RevoluteJoint motor1;
   private RevoluteJoint motor2;
   private PrismaticJoint spring1;
   private PrismaticJoint spring2 ;

   private Sprite screen;
   private Input input;
//   App physApp;
   
   //for loadcar model
   
   private  float degreesToRadians =0.0174532925f;
   //
   private World world =new World(new Vector2(0,10),true);
   private float worldScaleX = 30;
   private float worldScaleY = 30;
   private Body car;
   private Body floor;
   private Body leftWheel;
   private Body rightWheel;
   
   private RevoluteJoint leftWheelRevoluteJoint;
   private RevoluteJoint rightWheelRevoluteJoint;
   private Boolean left=false;
   private Boolean right=false;
   private float motorSpeed=0;
   private PrismaticJoint leftAxlePrismaticJoint;
   private PrismaticJoint rightAxlePrismaticJoint;
   //
   private float carPosX=220;
   private float carPosY=150;
   private float carWidth=45;
   private float carHeight=10;
   private float axleContainerDistance=30;
   private float axleContainerWidth=5;
   private float axleContainerHeight=20;
   private float axleContainerDepth=10;
   private float axleAngle=20;
   private float wheelRadius=25;
private PrismaticJointDef leftAxlePrismaticJointDef;
private PrismaticJointDef rightAxlePrismaticJointDef;
   


   public void PhysicsGame()  {

      // create the drawing screen //
//      screen = new Sprite();
//      screen.scale(-1);
//      screen.setY(SCREEN_HEIGHT);
//      this.addChild(screen);
      
//      this.addChild(screen);

      // initialize input object //
//      input = new Input(this);

      // add main loop event listener //
//      addEventListener(Event.ENTER_FRAME, update, false, 0, true);

   }

   private void load(){
Helper.println("physics world loaded");
      // create world //
//      var worldAABB:b2AABB = new b2AABB();
//	   b2AABB aabb;
	   
//      Vector2 worldAABB;
//	worldAABB.lowerBound.Set(-200, -100);
//      worldAABB.upperBound.Set(200, 200);

//      world = new World(worldAABB, true);
	   world = new World(new Vector2(0, -10), false);


      // allow debug drawing //
//      var debugDraw : b2DebugDraw = new b2DebugDraw();
//      debugDraw.m_sprite          = screen;
//      debugDraw.m_drawScale       = DRAW_SCALE;
//      debugDraw.m_fillAlpha       = 0.1;
//      debugDraw.m_lineThickness    = 2.0;
//      debugDraw.m_drawFlags       = 1;
//
//      world.SetDebugDraw(debugDraw);


      // temporary variables for adding new bodies //
      int i;
      Body body;
      BodyDef bodyDef ;
      PolygonShape box= new PolygonShape();
      box.setAsBox(10, 50);
      
      CircleShape circleDef= new CircleShape();
      RevoluteJointDef revoluteJointDef;
      PrismaticJointDef prismaticJointDef;
      

      // Define the shape.
//      PolygonShape Box1Box = new PolygonShape();


      
//      var boxDef             : b2PolygonDef;
//      var circleDef          : b2CircleDef;
//      var revoluteJointDef   : b2RevoluteJointDef;
//      var prismaticJointDef   : b2PrismaticJointDef;


      // add the ground //
      //body def
      bodyDef = new BodyDef();
      bodyDef.position.set(0, 0.5f);
//      bodyDef.type = BodyType.StaticBody;
      //shape
      box.setAsBox(50, 0.5f);
      /// cerate body
      body = world.createBody(bodyDef);
      
    //fixture def
      FixtureDef fixtureDef = new FixtureDef();
      fixtureDef.friction = 1;
      fixtureDef.density = 0;
      fixtureDef.shape = box;

     PolygonShape s = new PolygonShape();
      
      body.createFixture(fixtureDef);

      box.setAsBox(1, 2, new Vector2(-50, 0.5f), 0);
      body.createFixture(fixtureDef);

      box.setAsBox(1, 2, new Vector2(50, 0.5f), 0);
      body.createFixture(fixtureDef);

      box.setAsBox(3, 0.5f, new Vector2(5, 1.5f), (float)(Math.PI/4));
      body.createFixture(fixtureDef);

      box.setAsBox(3, 0.5f, new Vector2(3.5f, 1), (float)Math.PI/8);
      body.createFixture(fixtureDef);

      box.setAsBox(3, 0.5f, new Vector2(9, 1.5f), (float)-Math.PI/4);
      body.createFixture(fixtureDef);

      box.setAsBox(3, 0.5f, new Vector2(10.5f, 1), (float)-Math.PI/8);
      body.createFixture(fixtureDef);

      body.resetMassData();


      // add random shit //
      FixtureDef circleShape = new FixtureDef();
      circleShape.density = 0.01f;
      circleShape.friction = 0.1f;
      circleShape.restitution = 0.5f;
      circleShape.shape = circleDef;
      

      for (i = 0; i < 30; i++) {
         circleDef.setRadius((float) (Math.random()/20+0.02));

         bodyDef = new BodyDef();
         
         bodyDef.position.set((float) (Math.random()*35+15), 1);
         bodyDef.allowSleep = true;
         bodyDef.linearDamping = 0.1f;
         bodyDef.angularDamping = 0.1f;

         body = world.createBody(bodyDef);

         body.createFixture(circleShape);
         body.resetMassData();
      }


      // add cart //
      bodyDef = new BodyDef();
      bodyDef.position.set(0, 3.5f);

      cart = world.createBody(bodyDef);

      FixtureDef boxDef = new FixtureDef();
      boxDef.density = 2;
      boxDef.friction = 0.5f;
      boxDef.restitution = 0.2f;
      boxDef.filter.groupIndex = -1;
      boxDef.shape = box;

      box.setAsBox(1.5f, 0.3f);
      cart.createFixture(boxDef);

      box.setAsBox(0.4f, 0.15f, new Vector2(-1, -0.3f), (float)(Math.PI/3));
      cart.createFixture(boxDef);

      box.setAsBox(0.4f, 0.15f, new Vector2(1, -0.3f), (float)(-Math.PI/3));
      cart.createFixture(boxDef);

      cart.resetMassData();

      boxDef.density = 1;


      // add the axles //
      axle1 = world.createBody(bodyDef);

      box.setAsBox(0.4f, 0.1f, new Vector2(-1 - (float)(0.6*Math.cos(Math.PI/3)), -0.3f - (float)(0.6*Math.sin(Math.PI/3))), (float)(Math.PI/3));
      axle1.createFixture(boxDef);
      axle1.resetMassData();

      prismaticJointDef = new PrismaticJointDef();
      prismaticJointDef.initialize(cart, axle1, axle1.getWorldCenter(), new Vector2((float)(Math.cos(Math.PI/3)), (float)(Math.sin(Math.PI/3))));
      prismaticJointDef.lowerTranslation = -0.3f;
      prismaticJointDef.upperTranslation = 0.5f;
      prismaticJointDef.enableLimit = true;
      prismaticJointDef.enableMotor = true;

      spring1 = (PrismaticJoint) world.createJoint(prismaticJointDef);


      axle2 = world.createBody(bodyDef);

      box.setAsBox(0.4f, 0.1f, new Vector2(1 + (float)(0.6*Math.cos(-Math.PI/3)), -0.3f + (float)(0.6*Math.sin(-Math.PI/3))), (float) (-Math.PI/3));
      axle2.createFixture(boxDef);
//      axle2.SetMassFromShapes();
      axle2.resetMassData();

      prismaticJointDef.initialize(cart, axle2, axle2.getWorldCenter(), new Vector2(-(float)(Math.cos(Math.PI/3)), (float)(Math.sin(Math.PI/3))));

      spring2 = (PrismaticJoint) world.createJoint(prismaticJointDef);


      // add wheels //
      circleDef.setRadius(0.7f);
      circleShape.density = 0.1f;
      circleShape.friction = 5f;
      circleShape.restitution = 0.2f;
      circleShape.filter.groupIndex = -1;

      for (i = 0; i < 2; i++) {

         bodyDef = new BodyDef();
         if (i == 0) bodyDef.position.set
         (axle1.getWorldCenter().x - (float)(0.3*Math.cos(Math.PI/3)), axle1.getWorldCenter().y - (float)(0.3*Math.sin(Math.PI/3)));         
         else bodyDef.position.set(axle2.getWorldCenter().x + (float)(0.3*Math.cos(-Math.PI/3)), axle2.getWorldCenter().y + (float)(0.3*Math.sin(-Math.PI/3)));
         bodyDef.allowSleep = false;


 		
         if (i == 0) wheel1 = world.createBody(bodyDef);
         else wheel2 = world.createBody(bodyDef);

         (i == 0 ? wheel1 : wheel2).createFixture(circleShape);
         (i == 0 ? wheel1 : wheel2).resetMassData();
         
         if (i == 0){ 
        	 this.elements.add(new PhysicsBody(30, 30, wheel1, AssetConstants.IMG_BALL_RUBBER));
             Helper.println("1: px: " +  wheel1.getPosition().x);
      		Helper.println("1: py: " +  wheel1.getPosition().y);
         }
         else{
        	 this.elements.add(new PhysicsBody(30, 30, wheel2, AssetConstants.IMG_BALL_RUBBER));
             Helper.println("2: px: " +  wheel2.getPosition().x);
      		Helper.println("2: py: " +  wheel2.getPosition().y);
         }

      }


      // add joints //
      revoluteJointDef = new RevoluteJointDef();
      revoluteJointDef.enableMotor = true;

      revoluteJointDef.initialize(axle1, wheel1, wheel1.getWorldCenter());
      motor1 = (RevoluteJoint) world.createJoint(revoluteJointDef);

      revoluteJointDef.initialize(axle2, wheel2, wheel2.getWorldCenter());
      motor2 = (RevoluteJoint) world.createJoint(revoluteJointDef);

   }


   public  void update(Event e){
	   input = Gdx.input;
//      if (input.isKeyPressed(32)) {
//         world = null;
//         load();
//       
//         return;
//      }

      
//      motor2.setMotorSpeed((float) (15*Math.PI));
//      cart.applyTorque(30);

      motor1.setMotorSpeed((float) 
    		  (15*Math.PI ));
      motor1.setMaxMotorTorque((float)  17 );

      motor2.setMotorSpeed((float) 
    		  (15*Math.PI ));
      motor2.setMaxMotorTorque((float)  12 );

      spring1.setMaxMotorForce((float) (30+Math.abs(800*Math.pow(spring1.getJointTranslation(), 2))));
      //spring1.SetMotorSpeed(-4*Math.pow(spring1.GetJointTranslation(), 1));
      spring1.setMotorSpeed((float) ((spring1.getMotorSpeed() - 10*spring1.getJointTranslation())*0.4));         

      spring2.setMaxMotorForce((float) (20+Math.abs(800*Math.pow(spring2.getJointTranslation(), 2))));
      spring2.setMotorSpeed((float) (-4*Math.pow(spring2.getJointTranslation(), 1)));

      cart.applyTorque(30);

//      motor1.setMotorSpeed((float) 
//    		  (15*Math.PI * (input.isKeyPressed('a') ? 1 : input.isKeyPressed(38) ? -1 : 1)));
//      motor1.setMaxMotorTorque((float) (input.isKeyPressed('a') || input.isKeyPressed('d') ? 17 : 0.5));
//
//      motor2.setMotorSpeed((float) (15*Math.PI * (input.isKeyPressed(40) ? 1 : input.isKeyPressed(38) ? -1 : 0)));
//      motor2.setMaxMotorTorque((float) (input.isKeyPressed(40) || input.isKeyPressed(38) ? 12 : 0.5));
//
//      spring1.setMaxMotorForce((float) (30+Math.abs(800*Math.pow(spring1.getJointTranslation(), 2))));
//      //spring1.SetMotorSpeed(-4*Math.pow(spring1.GetJointTranslation(), 1));
//      spring1.setMotorSpeed((float) ((spring1.getMotorSpeed() - 10*spring1.getJointTranslation())*0.4));         
//
//      spring2.setMaxMotorForce((float) (20+Math.abs(800*Math.pow(spring2.getJointTranslation(), 2))));
//      spring2.setMotorSpeed((float) (-4*Math.pow(spring2.getJointTranslation(), 1)));
//
//      cart.applyTorque(30*(input.isKeyPressed(37) ? 1: input.isKeyPressed(39) ? -1 : 0));

//      screen.setX(screen.getX() - ((screen.getX() - (-DRAW_SCALE*cart.getWorldCenter().x + SCREEN_WIDTH/2 - cart.getLinearVelocity().x*10))/3));
//      screen.setY(screen.getY() - ((screen.getY() - (DRAW_SCALE*cart.getWorldCenter().y + 2*SCREEN_HEIGHT/3 + cart.getLinearVelocity().y*6))/3));


   }



@Override
public void onEvent(Point hitPoint) {
	// TODO Auto-generated method stub
	
}

@Override
public void loadElements() {
	Gdx.input.setInputProcessor(this);
//	this.load();
	this.loadCar();
}

private void loadCar() {
	// TODO Auto-generated method stub

//public void Main(){w
//	debugDraw();
	
	world = new World(new Vector2(0, -10), false);
	// ************************ THE FLOOR ************************ //
	PolygonShape floorShape = new PolygonShape();
	floorShape.setAsBox((float)(640/worldScaleX),(float)(10/worldScaleY));
	FixtureDef floorFixture = new FixtureDef();
	floorFixture.density=0;
	floorFixture.friction=10;
	floorFixture.restitution=0;
	floorFixture.shape=floorShape;
	BodyDef floorBodyDef = new BodyDef();
	floorBodyDef.position.set(0,0);
//	Body
	floor =world.createBody(floorBodyDef);
	floor.createFixture(floorFixture);
	floor.setType(BodyType.StaticBody);
	
//     Helper.println("leftWheel: px: " +  floor.getPosition().x);
//		Helper.println("leftWheel: py: " +  floor.getPosition().y);
	
	// ************************ THE CAR ************************ //
	
	PolygonShape carShape = new PolygonShape();
	carShape.setAsBox(carWidth/worldScaleX,carHeight/worldScaleY);
	FixtureDef carFixture = new FixtureDef();
	carFixture.density=5f;
	carFixture.friction=3f;
	carFixture.restitution=0.3f;
	carFixture.filter.groupIndex=-1;
	carFixture.shape=carShape;
	BodyDef carBodyDef = new BodyDef();
	carBodyDef.position.set(carPosX/worldScaleX,carPosY/worldScaleY);
	carBodyDef.type=BodyType.DynamicBody;
	Helper.println("now my state is======================= "+carBodyDef.type);
	
//	// ************************ LEFT AXLE CONTAINER ************************ //
//	
//	PolygonShape leftAxleContainerShape = new PolygonShape();
//	leftAxleContainerShape.setAsBox(axleContainerWidth/worldScaleX,axleContainerHeight/worldScaleX,new Vector2(-axleContainerDistance/worldScaleX,axleContainerDepth/worldScaleX),axleAngle*degreesToRadians);
//	FixtureDef leftAxleContainerFixture = new FixtureDef();
//	leftAxleContainerFixture.density=3f;
//	leftAxleContainerFixture.friction=3f;
//	leftAxleContainerFixture.restitution=0.3f;
//	leftAxleContainerFixture.filter.groupIndex=-1;
//	leftAxleContainerFixture.shape=leftAxleContainerShape;
//	
//	
//	// ************************ RIGHT AXLE CONTAINER ************************ //
//	PolygonShape rightAxleContainerShape = new PolygonShape();
//	rightAxleContainerShape.setAsBox(axleContainerWidth/worldScaleX,axleContainerHeight/worldScaleX,new Vector2(axleContainerDistance/worldScaleX,axleContainerDepth/worldScaleX),-axleAngle*degreesToRadians);
//	FixtureDef rightAxleContainerFixture = new FixtureDef();
//	rightAxleContainerFixture.density=3;
//	rightAxleContainerFixture.friction=3;
//	rightAxleContainerFixture.restitution=0.3f;
//	rightAxleContainerFixture.filter.groupIndex=-1;
//	rightAxleContainerFixture.shape=rightAxleContainerShape;
//	
	
	// ************************ MERGING ALL TOGETHER ************************ //
	car=world.createBody(carBodyDef);
	car.createFixture(carFixture);
//	car.createFixture(leftAxleContainerFixture);
//	car.createFixture(rightAxleContainerFixture);
//	car.setType(BodyType.DynamicBody);
//	car.setUserData(this);
//	car.resetMassData();
	
//	
//	// ************************ THE AXLES ************************ //
//	PolygonShape leftAxleShape = new PolygonShape();
//	leftAxleShape.setAsBox(axleContainerWidth/worldScaleX/2,axleContainerHeight/worldScaleX,new Vector2(0,0),axleAngle*degreesToRadians);
//	FixtureDef leftAxleFixture = new FixtureDef();
//	leftAxleFixture.density=0.5f;
//	leftAxleFixture.friction=3;
//	leftAxleFixture.restitution=0;
//	leftAxleFixture.shape=leftAxleShape;
//	leftAxleFixture.filter.groupIndex=-1;
//	BodyDef leftAxleBodyDef = new BodyDef();
////	leftAxleBodyDef.type=BodyType.DynamicBody;
//	leftAxleBodyDef.position.set(new Vector2((float)(carPosX/worldScaleX-axleContainerDistance/worldScaleX-axleContainerHeight/worldScaleX*Math.cos((90-axleAngle)*degreesToRadians)),(float)(carPosY/worldScaleX+axleContainerDepth/worldScaleX+axleContainerHeight/worldScaleX*Math.sin((90-axleAngle)*degreesToRadians))));
//	Body leftAxle=world.createBody(leftAxleBodyDef);
//	leftAxle.createFixture(leftAxleFixture);
//	leftAxle.setType(BodyType.DynamicBody);
////	leftAxle.
////	leftAxle.setPosition(new Vector2((float)(carPosX/worldScale-axleContainerDistance/worldScale-axleContainerHeight/worldScale*Math.cos((90-axleAngle)*degreesToRadians)),(float)(carPosY/worldScale+axleContainerDepth/worldScale+axleContainerHeight/worldScale*Math.sin((90-axleAngle)*degreesToRadians))));
//	PolygonShape rightAxleShape = new PolygonShape();
//	rightAxleShape.setAsBox(axleContainerWidth/worldScaleX/2,axleContainerHeight/worldScaleX,new Vector2(0,0),-axleAngle*degreesToRadians);
//	FixtureDef rightAxleFixture = new FixtureDef();
//	rightAxleFixture.density=0.5f;
//	rightAxleFixture.friction=3;
//	rightAxleFixture.restitution=0;
//	rightAxleFixture.shape=rightAxleShape;
//	rightAxleFixture.filter.groupIndex=-1;
//	BodyDef rightAxleBodyDef = new BodyDef();
////	rightAxleBodyDef.type=BodyType.DynamicBody;
//	rightAxleBodyDef.position.set(new Vector2((float)(carPosX/worldScaleX+axleContainerDistance/worldScaleX+axleContainerHeight/worldScaleX*Math.cos((90-axleAngle)*degreesToRadians)),(float)(carPosY/worldScaleX+axleContainerDepth/worldScaleX+axleContainerHeight/worldScaleX*Math.sin((90-axleAngle)*degreesToRadians))));
//	Body rightAxle = world.createBody(rightAxleBodyDef);
//	rightAxle.createFixture(rightAxleFixture);
//	rightAxle.setType(BodyType.DynamicBody);
////	rightAxle.SetPosition(new Vector2(carPosX/worldScale+axleContainerDistance/worldScale+axleContainerHeight/worldScale*Math.cos((90-axleAngle)*degreesToRadians),carPosY/worldScale+axleContainerDepth/worldScale+axleContainerHeight/worldScale*Math.sin((90-axleAngle)*degreesToRadians)));
//
//	
//	// ************************ THE WHEELS ************************ //;
//	CircleShape wheelShape =new CircleShape();//((float) (wheelRadius/worldScale))
//	wheelShape.setRadius(wheelRadius/worldScaleX);
//	FixtureDef wheelFixture = new FixtureDef();
//	wheelFixture.density=1;
//	wheelFixture.friction=15;
//	wheelFixture.restitution=0.2f;
//	wheelFixture.filter.groupIndex=-1;
//	wheelFixture.shape=wheelShape;
//	BodyDef wheelBodyDef = new BodyDef();
////	wheelBodyDef.type=BodyType.DynamicBody;
//	wheelBodyDef.position.set((float)(carPosX/worldScaleX-axleContainerDistance/worldScaleX-2*axleContainerHeight/worldScaleX*Math.cos((90-axleAngle)*degreesToRadians)),(float)(carPosY/worldScaleX+axleContainerDepth/worldScaleX-2*axleContainerHeight/worldScaleX*(float)Math.sin((90-axleAngle)*degreesToRadians)));
////	wheelBodyDef.position.set(carPosX, carPosY);
////	Body
//	leftWheel=world.createBody(wheelBodyDef);
//	leftWheel.createFixture(wheelFixture);
//	leftWheel.setType(BodyType.DynamicBody);
//	//passing to the game world
//	
//	wheelBodyDef.position.set((float)(carPosX/worldScaleX+axleContainerDistance/worldScaleX+2*axleContainerHeight/worldScaleX*Math.cos((90-axleAngle)*degreesToRadians)),(float)(carPosY/worldScaleX+axleContainerDepth/worldScaleX-2*axleContainerHeight/worldScaleX*(float)Math.sin((90-axleAngle)*degreesToRadians)));
////	Body
//	rightWheel=world.createBody(wheelBodyDef);
//	rightWheel.createFixture(wheelFixture);
//	rightWheel.setType(BodyType.DynamicBody);
	
//	// ************************ REVOLUTE JOINTS ************************ //
//	RevoluteJointDef leftWheelRevoluteJointDef=new RevoluteJointDef();
//	leftWheelRevoluteJointDef.initialize(leftWheel,leftAxle,leftWheel.getWorldCenter());
//	leftWheelRevoluteJointDef.enableMotor=true;
//	leftWheelRevoluteJoint=(RevoluteJoint) world.createJoint(leftWheelRevoluteJointDef);// as b2RevoluteJoint;
//	leftWheelRevoluteJoint.setMaxMotorTorque(10);
//	RevoluteJointDef rightWheelRevoluteJointDef=new RevoluteJointDef();
//	rightWheelRevoluteJointDef.initialize(rightWheel,rightAxle,rightWheel.getWorldCenter());
//	rightWheelRevoluteJointDef.enableMotor=true;
//	rightWheelRevoluteJoint=(RevoluteJoint) world.createJoint(rightWheelRevoluteJointDef);// as b2RevoluteJoint;
//	rightWheelRevoluteJoint.setMaxMotorTorque(10);
//	
//	// ************************ PRISMATIC JOINTS ************************ //
////	PrismaticJointDef 
//	leftAxlePrismaticJointDef=new PrismaticJointDef();
//	leftAxlePrismaticJointDef.lowerTranslation=0;
//	leftAxlePrismaticJointDef.upperTranslation=axleContainerDepth/worldScaleX;
//	leftAxlePrismaticJointDef.enableLimit=true;
//	leftAxlePrismaticJointDef.enableMotor=true;
//	leftAxlePrismaticJointDef.initialize(car,leftAxle,leftAxle.getWorldCenter(), new Vector2((float)(-Math.cos((90-axleAngle)*degreesToRadians)),(float)(Math.sin((90-axleAngle)*degreesToRadians))));
//	leftAxlePrismaticJoint=(PrismaticJoint) world.createJoint(leftAxlePrismaticJointDef);// as b2PrismaticJoint;
//	leftAxlePrismaticJoint.setMaxMotorForce(10);                         
//	leftAxlePrismaticJoint.setMotorSpeed(10);                         			
////	PrismaticJointDef
//	rightAxlePrismaticJointDef=new PrismaticJointDef();
//	rightAxlePrismaticJointDef.lowerTranslation=0;
//	rightAxlePrismaticJointDef.upperTranslation=axleContainerDepth/worldScaleX;
//	rightAxlePrismaticJointDef.enableLimit=true;
//	rightAxlePrismaticJointDef.enableMotor=true;
//	rightAxlePrismaticJointDef.initialize(car,rightAxle,rightAxle.getWorldCenter(), new Vector2((float)(Math.cos((90-axleAngle)*degreesToRadians)),(float)(Math.sin((90-axleAngle)*degreesToRadians))));
//	rightAxlePrismaticJoint=(PrismaticJoint) world.createJoint(rightAxlePrismaticJointDef);// as b2PrismaticJoint;
//	rightAxlePrismaticJoint.setMaxMotorForce(10);                         
//	rightAxlePrismaticJoint.setMotorSpeed(10);      
	////////drawing elements into game world
	this.elements.add(new PhysicsBody(480, 22, floor, AssetConstants.IMG_GROUND));
	this.elements.add(new PhysicsBody(carWidth, carHeight, car, AssetConstants.IMG_GROUND));
	Ball b = new Ball1(100, 300,
			7f * GameMenuInfo.ratio_w, world, false);
	addElement(b);
//	this.elements.add(new PhysicsBody(50,50, leftWheel, AssetConstants.IMG_BALL_BASKET));
//	this.elements.add(new PhysicsBody(50,50, rightWheel, AssetConstants.IMG_BALL_BOWL));
//	this.elements.add(new PhysicsBody(10,50, leftAxle, AssetConstants.IMG_GROUND));
//	this.elements.add(new PhysicsBody(10,50, rightAxle, AssetConstants.IMG_GROUND));
//	addEventListener(Event.ENTER_FRAME,updateWorld);
//	stage.addEventListener(KeyboardEvent.KEY_DOWN,keyPressed);
//	stage.addEventListener(KeyboardEvent.KEY_UP,keyReleased);
//}
//private void debugDraw(){
//	var worldDebugDraw:b2DebugDraw=new DebugDraw();
//	var debugSprite:Sprite = new Sprite();
//	addChild(debugSprite);
//	worldDebugDraw.SetSprite(debugSprite);
//	worldDebugDraw.SetDrawScale(worldScale);
//	worldDebugDraw.SetFlags(b2DebugDraw.e_shapeBit|b2DebugDraw.e_jointBit);
//	worldDebugDraw.SetFillAlpha(0.5);
//	world.SetDebugDraw(worldDebugDraw);
//}



}

public void keyPressed(int key) {
	switch (key) {
		case 21 :
			left=true;
			break;
		case 22 :
			right=true;
			break;
	}
	
	Helper.println("Key Pressed: " + key);
}
public void keyReleased(int key){
	switch (key) {
		case 21 :
			left=false;
			break;
		case 22 :
			right=false;
			break;
	}
	
	Helper.println("Key Released: " + key);
}
public  void updateWorld(Event e){
	if (left) {
		motorSpeed-=0.5;
		Helper.println("Speeding up: LEFT" );
	}
	if (right) {
		motorSpeed+=0.5;
		Helper.println("Speeding up: Right" );
	}
	motorSpeed*=0.99;
	if (motorSpeed>100) {
//		motorSpeed=10;
	}
//	Helper.println("Motor Speed: " + motorSpeed );
//	leftWheelRevoluteJoint.setMotorSpeed(motorSpeed);
//	rightWheelRevoluteJoint.setMotorSpeed(motorSpeed);
//	leftAxlePrismaticJoint.setMaxMotorForce(Math.abs(600*leftAxlePrismaticJoint.getJointTranslation()));
//	leftAxlePrismaticJoint.setMotorSpeed((leftAxlePrismaticJoint.getMotorSpeed()-2*leftAxlePrismaticJoint.getJointTranslation()));
//	rightAxlePrismaticJoint.setMaxMotorForce(Math.abs(600*rightAxlePrismaticJoint.getJointTranslation()));
//	rightAxlePrismaticJoint.setMotorSpeed((rightAxlePrismaticJoint.getMotorSpeed()-2*rightAxlePrismaticJoint.getJointTranslation()));
	
	
//	Helper.println("LRXA  left axle: " + leftAxlePrismaticJoint.getJointTranslation());
//	Helper.println("LRYB: " + leftWheelRevoluteJoint.getBodyB().getPosition().x);
	world.step(1/30,10,10);
//	world.clearForces();

//	this.elements.add(new PhysicsBody(480,22, floor, AssetConstants.IMG_GROUND));
//	this.elements.add(new PhysicsBody(carWidth,carHeight, car, AssetConstants.IMG_GROUND));
//	this.elements.add(new PhysicsBody(50,50, leftWheel, AssetConstants.IMG_BALL_BASKET));
//	this.elements.add(new PhysicsBody(50,50, rightWheel, AssetConstants.IMG_BALL_BOWL));
	 
	  Helper.println("in update method ");
//	world.DrawDebugData();
}

@Override
public AssetPack getAssets(AssetPack assetPack) {
	assetPack.addTexture(AssetConstants.IMG_GROUND);
	assetPack.addTexture(AssetConstants.IMG_BALL_BOWL);
	assetPack.addTexture(AssetConstants.IMG_BALL_BASKET);
	return assetPack;
}

@Override
public void tick(long stepTime) {
//	update(null);
//	world.step(TIME_STEP, ITERATIONS, ITERATIONS);
	this.updateWorld(null);
	Helper.println(".........................................." + this.car.getPosition());
	
}

@Override
public boolean keyDown(int arg0) {
	keyPressed(arg0);
	return false;
}

@Override
public boolean keyTyped(char arg0) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean keyUp(int arg0) {
	keyReleased(arg0);
	return false;
}

@Override
public boolean scrolled(int arg0) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean touchDragged(int arg0, int arg1, int arg2) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean touchMoved(int arg0, int arg1) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
	// TODO Auto-generated method stub
	return false;
}


}
