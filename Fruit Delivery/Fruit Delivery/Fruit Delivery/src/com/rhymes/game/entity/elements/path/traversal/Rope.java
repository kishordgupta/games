package com.rhymes.game.entity.elements.path.traversal;

import java.util.ArrayList;
import java.util.logging.Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.entity.elements.physical.PhysicsHelper;
import com.rhymes.game.entity.elements.ui.PhysicsBody2;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;
import com.rhymes.helpers.XMLReader;
import com.rhymes.helpers.XMLReaderChain;

public class Rope  extends ElementBase{

    private  int COUNT ;
    private boolean STRONG = false;
	private World world;
	private Vector2 anchor= new Vector2(); 
	private Body sBody;
	private boolean first = true;
	private TextureRegion imageRope;
	private Body link;
	private float ropeNodeWidth= 05f;
	private float ropeNodeHeight= 0f;
    
	 ArrayList<Body> links = new ArrayList<Body>();
	private Vector2 startPoint= new Vector2();
	private Body nLink;
	private float calculatedBodyangle;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.imageRope = Helper.getImageFromAssets(AssetConstants.IMG_AXLE);
	}

	public Rope(Vector2 startPoint,Body sBody, int count, World world,boolean strong) {
		// TODO Auto-generated constructor stub
		this.x = startPoint.x;
		this.y = startPoint.y;
		this.width = ropeNodeWidth;
		this.height = ropeNodeHeight;
		this.COUNT = count;
		this.startPoint = startPoint;
		anchor.set(PhysicsHelper.ConvertToBox(startPoint.x),PhysicsHelper.ConvertToBox(startPoint.y));
		this.world = world;
		this.sBody = sBody;
		this.STRONG = strong;
		
		createRope();
	}

	public Rope(Vector2 startPoint,Body sBody, int count, World world,boolean strong, float ropeNodeWidth, float ropeNodeHeight) {
		// TODO Auto-generated constructor stub
//		this.x = startPoint.x;
//		this.y = startPoint.y;
		this.ropeNodeWidth = PhysicsHelper.ConvertToBox(ropeNodeWidth);
		this.ropeNodeHeight =PhysicsHelper.ConvertToBox( ropeNodeHeight);
		this.width = ropeNodeWidth;
		this.height = ropeNodeHeight;
		this.COUNT = count;
//		this.startPoint = startPoint;
//		this.startPoint.x = 440;
		anchor.set(PhysicsHelper.ConvertToBox(startPoint.x),PhysicsHelper.ConvertToBox(startPoint.y));
		this.world = world;
		this.sBody = sBody;
		this.STRONG = strong;
		createRope();
	}
	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub
		
		assetPack.addTexture(AssetConstants.IMG_AXLE);
	}
	
	
	Vector2 v = new Vector2();
	Point p = new Point();
	Point q = new Point();
	Point r = new Point();
	
	private float startBodyWidth=PhysicsHelper
			.ConvertToBox(05 * GameMenuInfo.ratio_w)
			* GameMenuInfo.carSizeCoeff;;
	private float wheelWidth=PhysicsHelper
			.ConvertToBox(55 * GameMenuInfo.ratio_w)
			* GameMenuInfo.carSizeCoeff;;
	
	private Body startBody;
	private Body wheel;
	private Body wheel2;
	private RevoluteJoint revoluteJoint;
//	private Vector2[] vs;
	@Override
	public void render() {
//		this.update(Gdx.graphics.getDeltaTime());
			for (int i = 1; i < links.size(); i++) {
				link = links.get(i-1);
				
//				c.getVertex(i-1, v);
				p.setLocation(PhysicsHelper.ConvertToWorld(link.getPosition().x), 
						PhysicsHelper.ConvertToWorld(link.getPosition().y));
				nLink = links.get(i);
//				c.getVertex(i, v);
				q.setLocation(PhysicsHelper.ConvertToWorld(nLink.getPosition().x), PhysicsHelper.ConvertToWorld(nLink.getPosition().y));
				
				r.setLocation(PhysicsHelper.ConvertToWorld(links.get(0).getPosition().x), PhysicsHelper.ConvertToWorld(links.get(0).getPosition().y));
				
//				ropeNodeWidth = (float) Helper.pointToPointDistance(p, q);
				GlobalVars.ge.getRenderer().render(imageRope,
						p.x,
						p.y,
//						p.distancePoint2Point(q),
						PhysicsHelper.ConvertToWorld(ropeNodeWidth),
						PhysicsHelper.ConvertToWorld(ropeNodeHeight),
						0, 0, Helper.getAngle(p, q)+90f, 1,1);
				if(i==links.size()-1)
					GlobalVars.ge.getRenderer().render(imageRope,
							q.x,
							q.y,
//							q.distancePoint2Point(p),
							PhysicsHelper.ConvertToWorld(ropeNodeWidth),
							PhysicsHelper.ConvertToWorld(ropeNodeHeight),
							0, 0, Helper.getAngle(q, r)+90f, 1,1);
//				Helper.println("path render position is:::"+p+"chain no....."+i);
				calculatedBodyangle = Helper.getAngle(p, q);
			}
	}
//	public void render() {
//		// TODO Auto-generated method stub
////		super.render();
//    	this.update(Gdx.graphics.getDeltaTime());
//    	for(int i=0;i<= COUNT-1;i++)
//    	{
//    	link = links.get(i);
//    	if(i!=COUNT-1)
//    	nLink = links.get(i+1);
//    	calculatedBodyangle = Helper.getAngle(link.getPosition().x, link.getPosition().x, nLink.getPosition().x, nLink.getPosition().y);
//		GlobalVars.ge.getRenderer().render(imageRope,
//				GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(link.getPosition().x)/*-GameMenuInfo.ratio_w*width/2*/,
//				GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(link.getPosition().y)/*-GameMenuInfo.ratio_h*height/2*/,
//				GameMenuInfo.ratio_h*this.width , GameMenuInfo.ratio_w*this.height, 
//				0,0,
//				(MathUtils.radiansToDegrees*link.getAngle()), 1,1);
////		Helper.println("Iam here and renderinr points are::"+link.getPosition()+"angle::"+MathUtils.radiansToDegrees*link.getAngle());
//    	}
//		if(first  ){
////			Helper.println("Car X from phy to game world: " + player.getPosition().x);
////		Helper.println("delta time :::"+1/Gdx.graphics.getDeltaTime());
//			
//	
//			first = false;
//		} 
//	}
    
    
    public void update(float dt){
		
		   PhysicsHelper.accumulator+=dt;
//		   Helper.println("i'm here in update"+PhysicsHelper.accumulator+"and dt:::"+dt);
		    while(PhysicsHelper.accumulator>PhysicsHelper.BOX_STEP){
//		      world.step(PhysicsHelper.BOX_STEP,PhysicsHelper.BOX_VELOCITY_ITERATIONS,PhysicsHelper.BOX_POSITION_ITERATIONS);
//		      this.setLocation(PhysicsHelper.ConvertToWorld(player.getPosition().x)-width/2, PhysicsHelper.ConvertToWorld(player.getPosition().y)-height/2);
		      PhysicsHelper.accumulator -= PhysicsHelper.BOX_STEP;
		      if(Constants.setJoint.compareTo(Constants.jointState2)== 0)
		      {
//		    	  links.get(0).setType(BodyType.DynamicBody);
//		    	  links.get(links.size()-1).setTransform(links.get(0).getPosition().x - ropeNodeWidth,links.get(0).getPosition().y+ropeNodeHeight, links.get(0).getAngle());
//		    		enhancedRevelouteJoint(links.get(links.size()-1),links.get(0),new Vector2(ropeNodeWidth,0),new Vector2(-ropeNodeWidth,0));
//		    		Constants.setJoint = Constants.jointState3;
		    		Helper.println("Joint sate]]]"+Constants.setJoint);
		      }
		    }
    }

	public void createRope()
    {
    // Create links. Last link will be a very heavy box.
		for (int i =0; i <XMLReaderChain.ropes.size; i++) 
		{
			  if(XMLReaderChain.ropes.get(i).pathType.compareToIgnoreCase("chain")== 0 )
	        	{
				 Vector2  vs[] = new Vector2[XMLReaderChain.ropes.get(i).getNodes().size()];        
	
			for (int vertexCount =0;vertexCount<XMLReaderChain.ropes.get(i).getNodes().size();vertexCount++)
        {
//      	  Helper.println("vertex count::"+ vertexCount);
				 vs[vertexCount] =new Vector2(PhysicsHelper.ConvertToBox(XMLReaderChain.ropes.get(i).getNodes().get(vertexCount).getX()),
						 					 PhysicsHelper.ConvertToBox(XMLReaderChain.ropes.get(i).getNodes().get(vertexCount).getY()));
        }
	        	
		
    
    for (int j =0; j < XMLReaderChain.ropes.get(i).getNodes().size(); j++) {
      
//      bodyDef.position = anchor.add(new Vector2(0, i * 0.25f)); 
//      anchor.add(new Vector2(i*ropeNodeWidth, 0));
      BodyDef bodyDef= new BodyDef();
      anchor.set(vs[j]);
//      anchor.set(new Vector2(PhysicsHelper.ConvertToBox(startPoint.x)/*+2*i*(PhysicsHelper.ConvertToBox(ropeNodeWidth))*/, PhysicsHelper.ConvertToBox(startPoint.y)));
//      anchor.add(i*(PhysicsHelper.ConvertToBox(ropeNodeWidth)),0 );
    	
//      bodyDef.angle = i*MathUtils.degreesToRadians* .125f;
      bodyDef.angle = calculatedBodyangle;

  	// link creation
//      		XMLReader.
      
			bodyDef.position.set(anchor.x,anchor.y);
			PolygonShape boxDef = new PolygonShape();
			boxDef.setAsBox(ropeNodeWidth,ropeNodeHeight/2);
			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.density=1f;
			fixtureDef.friction=0.5f;
			fixtureDef.restitution=0.2f;
			fixtureDef.shape = boxDef;
			fixtureDef.filter.categoryBits = 15;
			fixtureDef.filter.maskBits = 1|2|20;
			if (j==0) {
				 link=world.createBody(bodyDef);
				link.createFixture(fixtureDef);
//				enhancedRevelouteJoint(sBody,link,new Vector2(0,0),new Vector2(-ropeNodeWidth,0));
			}
			else {
				nLink=world.createBody(bodyDef);
				nLink.createFixture(fixtureDef);
				enhancedRevelouteJoint(link,nLink,new Vector2(ropeNodeWidth,0),new Vector2(-ropeNodeWidth,0));
				link=nLink;
			}
			link.setType(BodyType.DynamicBody);
			link.setUserData("ropechain");
			links.add(link);
    }
    }
			  
	            else if(XMLReaderChain.ropes.get(i).pathType.compareToIgnoreCase("chainwheel")== 0 )
	        	{
	        	Vector2 vs2[] = new Vector2[XMLReaderChain.ropes.get(i).getNodes().size()];
	          for (int vertexCount =0;vertexCount<XMLReaderChain.ropes.get(i).getNodes().size();vertexCount++)
	          {
	        	  vs2[vertexCount] = new Vector2();
	          }
	          for (int vertexCount =0;vertexCount<XMLReaderChain.ropes.get(i).getNodes().size();vertexCount++)
	          {
//	        	  Helper.println("vertex count::"+ vertexCount);
//	        	  vs[vertexCount] = new Vector2(vs[vertexCount-1].x + elevetorWidth, vs[vertexCount-1].y * 1.10f);
//	        	  Helper.println("vertex position in pixel:::"+PhysicsHelper.ConvertToWorld((vs[vertexCount-1].x) ));
//	        	  Helper.println("value of i:::"+i+"value of certexCpunt::"+vertexCount);
	        	  try {
//					Helper.println("xml reader given rope no::::;"+XMLReader.ropes.get(i)+"xml reader given rope nodes ate::::;"+XMLReader.ropes.get(i).getNodes().get(vertexCount).getX());
					 vs2[vertexCount] =new Vector2(PhysicsHelper.ConvertToBox(XMLReaderChain.ropes.get(i).getNodes().get(vertexCount).getX()),
							 					 PhysicsHelper.ConvertToBox(XMLReaderChain.ropes.get(i).getNodes().get(vertexCount).getY()));
					 
//					 vs[vertexCount] =new Vector2((XMLReader.ropes.get(i).getNodes().get(vertexCount).getX()),
//		 					 (XMLReader.ropes.get(i).getNodes().get(vertexCount).getY()));
//					 Helper.println("rope  no:"+i+"rope node no:"+vertexCount+"rope node position"+PhysicsHelper.ConvertToWorld(vs[vertexCount].x)+"rope node position"+PhysicsHelper.ConvertToWorld(vs[vertexCount].y));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	 
	          }
	          createFruitChain(vs2[0].x-.3f,vs2[0].y,1);
	          createFruitChain(vs2[1].x-.6f,vs2[1].y,2);
	        
//	        elevetor = world.createBody(bodyDef);
//	        elevetor.setUserData("elevator");
//	        elevetor.createFixture(chainShepDef);
//	        elevetor.setType(BodyType.DynamicBody);
//	        elevators.add(elevetor);
//	        Helper.println("body angle is :::"+ elevetor.getAngle());
	        
	        }
			
    }
//    links.get(links.size()-1).setTransform(links.get(0).getPosition().x - ropeNodeWidth,links.get(0).getPosition().y+200*ropeNodeHeight, links.get(0).getAngle());
	enhancedRevelouteJoint(links.get(links.size()-1),links.get(0),new Vector2(ropeNodeWidth,0),new Vector2(-ropeNodeWidth,0));
//	links.get(0).setType(BodyType.StaticBody);
//	links.get(links.size()-1).setType(BodyType.StaticBody);
}

	
	public void createFruitChain(float x,float y, int wheelno)
    {
//		// ceiling polygon shape
		
		
		PhysicsBody2 pb2 = new PhysicsBody2(PhysicsHelper.ConvertToWorld(x),
				PhysicsHelper.ConvertToWorld(y),
				PhysicsHelper.ConvertToWorld(startBodyWidth),
				0f,
				AssetConstants.PHY_WHEEL ,
				AssetConstants.PHY_IMG_WHEEL,
				"wheel", 
				world, 
				((short) -1),
				((short) 3),
				((short) ( 9|10|11)),
				"gearbody",
				2,
				BodyType.StaticBody);
		
		GlobalVars.ge.getCurrentStage().addElement(pb2);
		Helper.println("chain  wheel genarating at position:::"+pb2.getLocation());
//		pb2.getBody().getFixtureList().get(0).setDensity(1);
		
		
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
	
	public void revjoint(Body bodyA, Body bodyB)
	{
		RevoluteJointDef revoluteJointDef=new RevoluteJointDef();
		revoluteJointDef.initialize(bodyA, bodyB, bodyB.getWorldCenter());
		revoluteJointDef.enableMotor=true;
		revoluteJointDef.maxMotorTorque = 20f;
		revoluteJointDef.motorSpeed = -5f;
//		revoluteJointDef.upperAngle = (float) (Math.PI/2);
//		revoluteJointDef.lowerAngle = (float) (-Math.PI/2);
		revoluteJoint =(RevoluteJoint) world.createJoint(revoluteJointDef);
	}
	
	

	private void enhancedRevelouteJoint(Body bodyA, Body bodyB,
			Vector2 anchorA,Vector2 anchorB) {
		// TODO Auto-generated method stub
//		private function revoluteJoint(bodyA:b2Body,bodyB:b2Body,anchorA:b2Vec2,anchorB:b2Vec2):void {
			RevoluteJointDef revoluteJointDef=new RevoluteJointDef();
			revoluteJointDef.localAnchorA.set(anchorA.x,anchorA.y);
			revoluteJointDef.localAnchorB.set(anchorB.x,anchorB.y);
			revoluteJointDef.bodyA=bodyA;
			revoluteJointDef.bodyB=bodyB;
			world.createJoint(revoluteJointDef);
			Helper.println("joint info:::"+"call No"+"body A::"+revoluteJointDef.bodyA.getUserData()+"    body B::"+revoluteJointDef.bodyB.getUserData()+"    anchorA:::"+revoluteJointDef.bodyA.getPosition()+"    anchor B::"+revoluteJointDef.bodyB.getPosition());
//		}
	}

	public Body getLastLink() {
		// TODO Auto-generated method stub
		return links.get(links.size()-1);
	}
	
	public void setropeJoint()
	{
		links.get(0).setType(BodyType.DynamicBody);
//  	  links.get(links.size()-1).setTransform(links.get(0).getPosition().x - ropeNodeWidth,links.get(0).getPosition().y+ropeNodeHeight, links.get(links.size()-1).getAngle());
  		enhancedRevelouteJoint(links.get(links.size()-1),links.get(0),new Vector2(-ropeNodeWidth,0),new Vector2(ropeNodeWidth,0));
  		Constants.setJoint = Constants.jointState3;
  		Helper.println("Joint sate]]]"+Constants.setJoint);
	}
}
