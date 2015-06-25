package com.rhymes.game.entity.elements.physical;

import java.util.Vector;

import aurelienribon.box2deditor.FixtureAtlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.elements.nonphysical.Arrow;
import com.rhymes.game.entity.elements.nonphysical.Player;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.helpers.Helper;
import com.rhymes.game.entity.elements.nonphysical.Arrow;
import com.rhymes.game.entity.elements.physical.PhysicsTest.MovingPlatform;

public class PhysicsHelper {

	final static float MAX_VELOCITY = 7f;	
	String imagePath;
	private World world= new World(new Vector2(0,0),true);
	private Body ground;
	public static final short categoryBits = 1;
	private boolean first = true;
	private int stillTime;
	private Vector2 pos = new Vector2();
	private float angle;
	private float density=0;
	private float restitution=1;
	public static final float BOX_STEP=0.001f;
	public static final int  BOX_VELOCITY_ITERATIONS=8;
	public static final int BOX_POSITION_ITERATIONS=8;
	public static float accumulator;
	static final float WORLD_TO_BOX=1/32f;
	static final float BOX_TO_WORLD=32f;
	
	
	public static float ConvertToBox(float x){
	    return x*WORLD_TO_BOX;
	}
	
	public static float ConvertToWorld(float x){
	    return x*BOX_TO_WORLD;
	}
	
	public static Vector2 ConvertToWorld(Vector2 vector2) {
		// TODO Auto-generated method stub
		return vector2.mul(BOX_TO_WORLD);
		
	}
	
	public static Body CreateBody(World world,Body body,BodyDef.BodyType bodyType,Vector2 pos,float angle){
	    BodyDef bodyDef = new BodyDef(); 
	    bodyDef.type = bodyType;
	    bodyDef.position.set(ConvertToBox(pos.x),ConvertToBox(pos.y));
	    bodyDef.angle=MathUtils.degreesToRadians*angle;
	    body = world.createBody(bodyDef);
	    return body;
	}
	
	static void MakeFixture(Body body,Shape.Type shape,float width,float height,
		     float density,float restitution,float friction){
	     float w=ConvertToBox(width/2);
	     float h=ConvertToBox(height/2);
	     float r = ConvertToBox((width+height)/2f);
	     FixtureDef fixtureDef=new FixtureDef();
	     fixtureDef.filter.categoryBits =1;
	     fixtureDef.density=density;
	     fixtureDef.restitution=restitution;
	     fixtureDef.friction = friction;
	     
	     if(Shape.Type.Circle == shape)
	     {
	    	 fixtureDef.shape=new CircleShape();
		     fixtureDef.shape.setRadius(r);
		     Helper.println("i am here in circle shape");
	     }
	     else if(Shape.Type.Polygon== shape)
	     {
	    	 PolygonShape bodyShape = new PolygonShape();
		     bodyShape.setAsBox(w,h);
//	    	 Helper.println("w and h:::"+ConvertToWorld(w)+"and"+ConvertToWorld(h)+"and"+body.getAngle()*MathUtils.radiansToDegrees+"and"+body.getWorldCenter());
//		     bodyShape.setAsBox(w, h, new Vector2(-50, 0.5f), body.getAngle());
//		     bodyShape.setAsBox(w, h,body.getWorldCenter(), 0);
		     fixtureDef.shape=bodyShape;
		     
	     }
	     body.createFixture(fixtureDef);
	     body.resetMassData();
	     body.setUserData(body);
	     fixtureDef.shape.dispose();
//	     Helper.println("i am here in polygone shape"+body.getPosition());
		 }
	public PhysicsHelper() {
		super();
		// TODO Auto-generated constructor stub
	}


}
