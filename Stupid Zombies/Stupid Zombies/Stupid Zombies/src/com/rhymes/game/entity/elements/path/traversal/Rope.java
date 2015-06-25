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
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.physical.PhysicsHelper;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Rope  extends ElementBase{

    private  int COUNT ;
    private boolean STRONG = false;
	private World world;
	private Vector2 anchor= new Vector2(); 
	private Body sBody;
	private boolean first = true;
	private TextureRegion imageRope;
	private Body link;
	private float ropeNodeWidth= 20f;
	private float ropeNodeHeight= 05f;
    
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
		this.x = startPoint.x;
		this.y = startPoint.y;
		this.ropeNodeWidth = ropeNodeWidth;
		this.ropeNodeHeight = ropeNodeHeight;
		this.width = ropeNodeWidth;
		this.height = ropeNodeHeight;
		this.COUNT = count;
		this.startPoint = startPoint;
		this.startPoint.x = 440;
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
	
	
    @Override
	public void render() {
		// TODO Auto-generated method stub
//		super.render();
    	this.update(Gdx.graphics.getDeltaTime());
    	for(int i=0;i<= COUNT-1;i++)
    	{
    	link = links.get(i);
    	if(i!=COUNT-1)
    	nLink = links.get(i+1);
    	calculatedBodyangle = Helper.getAngle(link.getPosition().x, link.getPosition().x, nLink.getPosition().x, nLink.getPosition().y);
		GlobalVars.ge.getRenderer().render(imageRope,
				GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(link.getPosition().x)/*-GameMenuInfo.ratio_w*width/2*/,
				GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(link.getPosition().y)/*-GameMenuInfo.ratio_h*height/2*/,
				GameMenuInfo.ratio_h*this.width , GameMenuInfo.ratio_w*this.height, 
				0,0,
				(MathUtils.radiansToDegrees*link.getAngle()), 1,1);
//		Helper.println("Iam here and renderinr points are::"+link.getPosition()+"angle::"+MathUtils.radiansToDegrees*link.getAngle());
    	}
		if(first  ){
//			Helper.println("Car X from phy to game world: " + player.getPosition().x);
//		Helper.println("delta time :::"+1/Gdx.graphics.getDeltaTime());
			
	
			first = false;
		} 
	}
    
    
    public void update(float dt){
		
		   PhysicsHelper.accumulator+=dt;
//		   Helper.println("i'm here in update"+PhysicsHelper.accumulator+"and dt:::"+dt);
		    while(PhysicsHelper.accumulator>PhysicsHelper.BOX_STEP){
		      world.step(PhysicsHelper.BOX_STEP,PhysicsHelper.BOX_VELOCITY_ITERATIONS,PhysicsHelper.BOX_POSITION_ITERATIONS);
//		      this.setLocation(PhysicsHelper.ConvertToWorld(player.getPosition().x)-width/2, PhysicsHelper.ConvertToWorld(player.getPosition().y)-height/2);
		      PhysicsHelper.accumulator -= PhysicsHelper.BOX_STEP;
		    }
    }

	public void createRope()
    {
    // Create links. Last link will be a very heavy box.
  
    
    for (int i =0; i <= COUNT-1; i++) {
      BodyDef bodyDef = new BodyDef();
//      bodyDef.position = anchor.add(new Vector2(0, i * 0.25f)); 
//      anchor.add(new Vector2(i*.25f, 0));
//      anchor.set(startPoint);
      anchor.set(new Vector2(PhysicsHelper.ConvertToBox(startPoint.x)/*+2*i*(PhysicsHelper.ConvertToBox(ropeNodeWidth))*/, PhysicsHelper.ConvertToBox(startPoint.y)));
//      anchor.add(i*(PhysicsHelper.ConvertToBox(ropeNodeWidth)),0 );
      bodyDef.position.set(anchor);
//      bodyDef.angle = i*MathUtils.degreesToRadians* .125f;
//      bodyDef.angle = calculatedBodyangle;
      
      link = world.createBody(bodyDef);
      link.setUserData("link");
/*  	// link creation
		for (var i:Number=0; i<=links; i++) {
			bodyDef.position.Set(320/worldScale,(chainLength+2*chainLength*i)/worldScale);
			if (i==0) {
				var link:b2Body=world.CreateBody(bodyDef);
				link.CreateFixture(fixtureDef);
				revoluteJoint(wall,link,new b2Vec2(0,0),new b2Vec2(0,-chainLength/worldScale));
			}
			else {
				var newLink:b2Body=world.CreateBody(bodyDef);
				newLink.CreateFixture(fixtureDef);
				revoluteJoint(link,newLink,new b2Vec2(0,chainLength/worldScale),new b2Vec2(0,-chainLength/worldScale));
				link=newLink;
			}
		}*/

      if (i != COUNT) {
        // A link

          PolygonShape polygonShape = new PolygonShape();
//          polygonShape.setAsBox(PhysicsHelper.ConvertToBox(ropeNodeWidth), PhysicsHelper.ConvertToBox(ropeNodeHeight));
          polygonShape.setAsBox(PhysicsHelper.ConvertToBox(ropeNodeWidth/2), PhysicsHelper.ConvertToBox(ropeNodeHeight/2), new Vector2(PhysicsHelper.ConvertToBox(ropeNodeWidth/2),PhysicsHelper.ConvertToBox(ropeNodeHeight/2)), 0);
          FixtureDef polygonDef = new FixtureDef();
//          polygonDef.density = .0001f;
//          polygonDef.friction = 0f;
//          polygonDef.restitution = 0f;
          polygonDef.filter.categoryBits = 9;
          polygonDef.filter.maskBits =1|2|3|4|5|6|7;
          polygonDef.shape = polygonShape;
          link.createFixture(polygonDef);
//        
      } else {
////         A box attached at the end of the rope
//        PolygonShape polygonShape = new PolygonShape();
//        polygonShape.setAsBox(PhysicsHelper.ConvertToBox(ropeNodeWidth/2), PhysicsHelper.ConvertToBox(ropeNodeHeight/2), new Vector2(PhysicsHelper.ConvertToBox(ropeNodeWidth),0), 0);
//        FixtureDef polygonDef = new FixtureDef();
//        polygonDef.density = 0f;
//        polygonDef.friction = 05f;
//        polygonDef.restitution = 0f;
//        polygonDef.filter.categoryBits = 10;
//        polygonDef.filter.maskBits =1|2|3|4|5|6|7;
//        polygonDef.shape = polygonShape;
//        link.createFixture(polygonDef);
      }
      

      link.setType(BodyType.DynamicBody);
      link.setActive(true);
      
//      MassData massData = new MassData();
//      massData.mass = link.getMass();
//      massData.I = link.getInertia();
//      Helper.println("mass data for weak rope:::"+massData.mass);
//      link.setMassData(massData);
//      link.resetMassData();
      links.add(link);
    }
//    links.get(0).setType(BodyType.StaticBody); 
    links.get(COUNT-1).setType(BodyType.StaticBody);
    // Connects links together. First link is connected directly to the "ceiling".
    Body lastLink = null;
    
    for (Body link : links) {
      DistanceJointDef distanceJointDef = new DistanceJointDef();
      if (lastLink == null) {
//    	  enhancedRevelouteJoint(groudBody ,link,groudBody.getPosition(), link.getPosition());
    	  enhancedRevelouteJoint(sBody ,link,new Vector2(sBody.getLocalCenter()),new Vector2(-PhysicsHelper.ConvertToBox(ropeNodeWidth/2),0));
      } else {
    	  enhancedRevelouteJoint(link,lastLink , new Vector2(PhysicsHelper.ConvertToBox(ropeNodeWidth/2), 0),new Vector2(-PhysicsHelper.ConvertToBox(ropeNodeWidth/2), 0));
      }
     
      lastLink = link;
      lastLink.setUserData("lastLink");
    }
//    lastLink.setType(BodyType.StaticBody);
    if (STRONG) {
      // To make rope stronger, connect each link directly to the ceiling.
      for (Body link : links) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(anchor);
        Body attachement = world.createBody(bodyDef);
          
        MassData massData = new MassData();
        massData.mass = link.getMass();
        massData.I = link.getInertia();
        attachement.setMassData(massData);
                
        RevoluteJointDef revoluteJointDef = new RevoluteJointDef();
        revoluteJointDef.initialize(attachement,sBody , attachement.getPosition());
        world.createJoint(revoluteJointDef);
          
        PrismaticJointDef prismaticJointDef = new PrismaticJointDef();
        prismaticJointDef.initialize(attachement, link, link.getPosition(), new Vector2(0, -1));
        prismaticJointDef.enableLimit = true;
        prismaticJointDef.lowerTranslation = 0;
        prismaticJointDef.upperTranslation = (link.getPosition().y - anchor.y);
        world.createJoint(prismaticJointDef);
      }
    }
}

	int c =0;

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
//			Helper.println("joint info:::"+"call No"+c+++"body A::"+revoluteJointDef.bodyA.getUserData()+"    body B::"+revoluteJointDef.bodyB.getUserData()+"    anchorA:::"+revoluteJointDef.bodyA.getPosition()+"    anchor B::"+revoluteJointDef.bodyB.getPosition());
//		}
	}

	public Body getLastLink() {
		// TODO Auto-generated method stub
		return links.get(links.size()-1);
	}
}
