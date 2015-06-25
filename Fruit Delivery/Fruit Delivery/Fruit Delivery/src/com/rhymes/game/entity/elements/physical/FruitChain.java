package com.rhymes.game.entity.elements.physical;

import java.util.ArrayList;
import java.util.logging.Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.GearJoint;
import com.badlogic.gdx.physics.box2d.joints.GearJointDef;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.physical.PhysicsHelper;
import com.rhymes.game.entity.elements.ui.PhysicsBody2;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class FruitChain  extends ElementBase{

    private  int COUNT ;
    private boolean movable = false;
	private World world;
	private Vector2 anchor= new Vector2(); 
	private Body startBody;
	private Body wheel;
	private Body wheel2;
	private boolean first = true;
	private TextureRegion imageRope;
	private Body link;
//	private float elevetorWidth= 20f;
//	private float elevetorHeight= 05f;
    
	 ArrayList<Body> wheels = new ArrayList<Body>();
	 
	 ArrayList<Body> links = new ArrayList<Body>();
	private Vector2 startPoint= new Vector2();
	private Body nLink;
	private float calculatedBodyangle;
	private Body steelBall;
	private Body chain;
	private Body chainHolder;
	
	private float startBodyWidth=PhysicsHelper.ConvertToBox(10*GameMenuInfo.ratio_w)*GameMenuInfo.carSizeCoeff;
	private float wheelWidth=PhysicsHelper.ConvertToBox(100*GameMenuInfo.ratio_w)*GameMenuInfo.carSizeCoeff;
	private float chainHolderWidth= PhysicsHelper.ConvertToBox(50*GameMenuInfo.ratio_w)*GameMenuInfo.carSizeCoeff;
	private float chainWidth= PhysicsHelper.ConvertToBox(100*GameMenuInfo.ratio_w)*GameMenuInfo.carSizeCoeff;
	
	
	
	float muly = 0.007125f;
	float ty;
	int direction=1;
	float targettime=200;
	private float gearlength=PhysicsHelper.ConvertToBox(15*GameMenuInfo.ratio_w)*GameMenuInfo.carSizeCoeff;
	GearJoint gearjoint;
	private RevoluteJoint revoluteJoint;
	PrismaticJoint prismaticJoint;
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.imageRope = Helper.getImageFromAssets(AssetConstants.IMG_AXLE);
	}

	public FruitChain(Vector2 startPoint,Body sBody,Vector2 endPoint,Body eBody, int count, World world,boolean movable, float width,float height) {
		// TODO Auto-generated constructor stub
		this.x = startPoint.x;
		this.y = startPoint.y;
		this.width = width;
		this.height = height;
		this.COUNT = count;
		this.startPoint = startPoint;
//		anchor.set(PhysicsHelper.ConvertToBox(startPoint.x),PhysicsHelper.ConvertToBox(startPoint.y));
		this.world = world;
		this.startBody = sBody;
		this.wheel = eBody;
		this.movable = movable;
		
		createFruitChain(this.x,this.y,1);
		createFruitChain(this.x+130,this.y,2);
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub
		
		assetPack.addTexture(AssetConstants.IMG_AXLE);
	}
	
	
    @Override
	public void render() {
		// TODO Auto-generated method stub
//		super.render();
    	this.update(Gdx.graphics.getDeltaTime());
//		GlobalVars.ge.getRenderer().render(imageRope,
//				this.x-GameMenuInfo.ratio_w*this.width/2,
//				this.y-GameMenuInfo.ratio_w*this.height/2,
//				GameMenuInfo.ratio_w*this.width ,
//				GameMenuInfo.ratio_h*this.height,
//				GameMenuInfo.ratio_w*this.width/2 , 
//				GameMenuInfo.ratio_w*this.height/2,
//				(MathUtils.radiansToDegrees*elevetor.getAngle()), 1,1);
//		Helper.println("Iam here and renderinr points are::"+link.getPosition()+"angle::"+MathUtils.radiansToDegrees*link.getAngle());
		if(first  ){
//			Helper.println("Car X from phy to game world: " + player.getPosition().x);
//		Helper.println("delta time :::"+1/Gdx.graphics.getDeltaTime());
			
	
//			first = false;
		}
		
		if(Gdx.input.isKeyPressed(Keys.L)) {
//	    if(PhysicsHelper.ConvertToWorld(elevetor.getPosition().y)<30)
    	{
    		if(first)
    		{
//    			chain.setTransform(chain.getPosition().x, chain.getPosition().y+.5f, chain.getAngle());
    			first= false;
    		}
    		else
    		{
//    			elevetor.setTransform(elevetor.getPosition().x, elevetor.getPosition().y-1f, elevetor.getAngle());
    			first=true;
    		}
//    	Helper.println("lift position:::"+PhysicsHelper.ConvertToWorld(elevetor.getPosition().y));
//    	elevetor.applyLinearImpulse(0, 50f,elevetor.getPosition().x+PhysicsHelper.ConvertToBox(width/2) , elevetor.getPosition().y);
//    	elevetor.setType(BodyType.KinematicBody);
//    	elevetor.applyForceToCenter(0, -10f);
//    	elevetor.	applyLinearImpulse(1,02, elevetor.getPosition().x, elevetor.getPosition().y);
    	}
		
		}
	}
    
    
    public void update(float dt){
		
		   PhysicsHelper.accumulator+=dt;
//		   Helper.println("i'm here in update"+PhysicsHelper.accumulator+"and dt:::"+dt);
		    while(PhysicsHelper.accumulator>PhysicsHelper.BOX_STEP){
		      world.step(PhysicsHelper.BOX_STEP,PhysicsHelper.BOX_VELOCITY_ITERATIONS,PhysicsHelper.BOX_POSITION_ITERATIONS);
//		      this.setLocation(PhysicsHelper.ConvertToWorld(chain.getPosition().x), PhysicsHelper.ConvertToWorld(chain.getPosition().y));
		      PhysicsHelper.accumulator -= PhysicsHelper.BOX_STEP;
		      
//		      elevetor.setGravityScale(-1);
//		      revoluteJoint.setMotorSpeed(-10);
//		      prismaticJoint.setMotorSpeed(100);
//		      revoluteJoint.setMaxMotorTorque(100);
//		      Helper.println("joint translation is ::"+prismaticJoint.getJointTranslation());
//		      if(prismaticJoint.getJointTranslation()>=05f)
//		      {
//		    	  chain.setTransform(chain.getPosition().x-1f, chain.getPosition().y, chain.getAngle());
		    	  
//		    	 revoluteJoint.setMotorSpeed(revoluteJoint.getMotorSpeed()+1f);
//		    	 prismaticJoint.setMotorSpeed(prismaticJoint.getMotorSpeed()-10f);
//		    	  chain.applyLinearImpulse(450, 0, chain.getPosition().x,chain.getWorldCenter().x);
//		      }
		      
		    }
//			    if(PhysicsHelper.ConvertToWorld(elevetor.getPosition().y)<30)
	
//		    	elevetor.applyLinearImpulse(0, 50f,elevetor.getPosition().x+PhysicsHelper.ConvertToBox(width/2) , elevetor.getPosition().y);
//		    	elevetor.setType(BodyType.KinematicBody);
//		    	elevetor.applyForceToCenter(0, -10f);
//		    	elevetor.applyLinearImpulse(1,02, elevetor.getPosition().x, elevetor.getPosition().y);
		    	
				
		    
	
		    
    }
    
	
	

	

	@Override
	public void step(long stepTime) {
		// TODO Auto-generated method stub
		super.step(stepTime);
	
//
//		if(ty>targettime)
//			{
//			direction= -direction;
//			ty=0;
//			}
//		
//		if(direction<0)
//		{
//		chain.setTransform(chain.getPosition().x, chain.getPosition().y-muly, chain.getAngle());
//		ty++;
//		}
//		if(direction>0)
//		{
//		chain.setTransform(chain.getPosition().x, chain.getPosition().y+muly, chain.getAngle());
//		ty++;
//		}
		
		
//	Helper.println("mulyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy::"+elevetor.getTransform().getPosition());
//	Helper.println("lift position:::"+PhysicsHelper.ConvertToWorld(elevetor.getPosition().y));
	}

	
	public void createFruitChain(float x,float y, int wheelno)
    {
//		// ceiling polygon shape
		
		
		PhysicsBody2 pb2 = new PhysicsBody2(x,
				y,
				PhysicsHelper.ConvertToWorld(startBodyWidth),
				0f,
				AssetConstants.PHY_WHEEL ,
				AssetConstants.PHY_IMG_WHEEL,
				"wheel", 
				world, 
				((short) -1),
				((short) 1),
				((short) ( 9|10|11)),
				"gearbody",
				2,
				BodyType.StaticBody);
		
		GlobalVars.ge.getCurrentStage().addElement(pb2);
		pb2.getBody().getFixtureList().get(0).setDensity(1);
		
		
		startBody = pb2.getBody();
		startBody.setUserData("gearbody");
		
		PhysicsBody2 pb = new PhysicsBody2(PhysicsHelper.ConvertToWorld(startBody.getPosition().x-startBodyWidth/2),
				PhysicsHelper.ConvertToWorld(startBody.getPosition().y+startBodyWidth/2-wheelWidth/2),
				PhysicsHelper.ConvertToWorld(wheelWidth),
				0f,
				AssetConstants.PHY_WHEEL ,
				AssetConstants.PHY_IMG_WHEEL,
				"wheel", 
				world, 
				((short) -1),
				((short) 2),
				((short)( 9|10|11|15)),
				"gearwheel",
				2,
				BodyType.DynamicBody);
		
		GlobalVars.ge.getCurrentStage().addElement(pb);
		pb.getBody().getFixtureList().get(0).setDensity(1);
//		pb.getBody().applyTorque(100);
		
		if(wheelno ==1)
		{
			wheel = pb.getBody();
		wheel.setUserData("gearwheel");
		revjoint(startBody, wheel);
		}
		else if(wheelno ==2)
		{
			wheel2 = pb.getBody();
		wheel2.setUserData("gearwheel");
		revjoint(startBody, wheel2);
		}
		
    }
// as b2RevoluteJoint;

	
public void transferSecondWheel()
{
	wheel2.setTransform(wheel2.getPosition().x+.5f, wheel2.getPosition().y, wheel2.getAngle());
	revoluteJoint.setMotorSpeed(2f);
}

public void revjoint(Body bodyA, Body bodyB)
{
	RevoluteJointDef revoluteJointDef=new RevoluteJointDef();
	revoluteJointDef.initialize(bodyA, bodyB, bodyB.getWorldCenter());
	revoluteJointDef.enableMotor=true;
	revoluteJointDef.maxMotorTorque = 10f;
	revoluteJointDef.motorSpeed = -1f;
//	revoluteJointDef.upperAngle = (float) (Math.PI/2);
//	revoluteJointDef.lowerAngle = (float) (-Math.PI/2);
	revoluteJoint =(RevoluteJoint) world.createJoint(revoluteJointDef);
}


}
