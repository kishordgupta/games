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

public class Ground extends ElementBase implements InteractionTouchCallbacks{

	final static float MAX_VELOCITY = 7f;	
	String imagePath;
	private World world;
//	private Body ground;
	public static final short categoryBits = 1;
	private boolean first = true;
	private int stillTime;
	private Vector2 pos = new Vector2();
	
	private float angle;
	private float density=0;
	private float restitution=0;
	private float friction = 05f;

	static final float BOX_STEP=1/120f;
//	static final int  BOX_VELOCITY_ITERATIONS=10;
//	static final int BOX_POSITION_ITERATIONS=10;
	float accumulator;
	private Body floor;
	private boolean isStatic;

	
	

	public void Update(float dt){
		   accumulator+=dt;
//		   Helper.println("i'm here in update"+accumulator+"and dt:::"+dt);
		    while(accumulator>BOX_STEP){
//		      world.step(BOX_STEP,BOX_VELOCITY_ITERATIONS,BOX_POSITION_ITERATIONS);
//		      world.step(PhysicsHelper.BOX_STEP,PhysicsHelper.BOX_VELOCITY_ITERATIONS,PhysicsHelper.BOX_POSITION_ITERATIONS);
		      accumulator -= BOX_STEP;
//		      Helper.println("i'm here in update");
		    }
		   }
	

	public Ground() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Ground(float x, float y,float width, float height,float angle, String imagePath, World world, boolean b) {
//		super(x , y, width,height, 1);
		// TODO Auto-generated constructor stub
//		this.radius = radius / GameMenuInfo.ratio_w;
		Helper.println("X: " + x);
		Helper.println("After X: " +  this.x + imagePath);		
		this.imagePath = imagePath;
		this.world = world;
		this.x = x;
		this.y = y;
		this.pos.set(this.x, this.y);
		this.width =width;
		this.height = height;
		this.angle = angle;
		this.isStatic = b;
//		createBallModel();
		createground();
		GlobalVars.ge.getCurrentStage().subscribeToInteraction(this,
				InteractionTouch.class);
	}


private void createground() {
		// TODO Auto-generated method stub
	
//		 ground = PhysicsHelper.CreateBody(world, ground, BodyType.StaticBody, pos, angle);
//		 PhysicsHelper.MakeFixture(ground, Shape.Type.Polygon, width, height, density, restitution,friction);
//		Helper.println("ground position::"+ground.getPosition()+"and to world"+this.getRenderLocation());	
		 
		 
			PolygonShape floorShape = new PolygonShape();
			floorShape.setAsBox(PhysicsHelper.ConvertToBox(width/2*GameMenuInfo.ratio_w),PhysicsHelper.ConvertToBox(height/2*GameMenuInfo.ratio_h));
//			floorShape.setAsBox(PhysicsHelper.ConvertToBox(width),PhysicsHelper.ConvertToBox(height),new Vector2(width/2,height/2),this.angle*MathUtils.degreesToRadians);
			FixtureDef floorFixture = new FixtureDef();
			floorFixture.density=density;
			floorFixture.friction=friction;
			floorFixture.restitution=restitution;
			floorFixture.filter.categoryBits = 8;
			floorFixture.filter.maskBits = 1|2|3|6|7;
			floorFixture.shape=floorShape;
			BodyDef floorBodyDef = new BodyDef();
			floorBodyDef.position.set(PhysicsHelper.ConvertToBox(this.x),PhysicsHelper.ConvertToBox(this.y));
			floorBodyDef.angle = this.angle*MathUtils.degreesToRadians;
//			Body
			floor =world.createBody(floorBodyDef);
			floor.createFixture(floorFixture);
			floor.setType(BodyType.StaticBody);
	}

	@Override
	public void render() {
//		Vector2 vel = ground.getLinearVelocity();
//		Vector2 pos = player.getPosition().sub(Helper.w2p(w/2), Helper.w2p(h/2));
		this.Update(Gdx.graphics.getDeltaTime());
//		 world.step(BOX_STEP,BOX_VELOCITY_ITERATIONS,BOX_POSITION_ITERATIONS);
//		Vector2 pos1=ground.getPosition().sub(ConvertToBox(width/2), ConvertToBox(height/2));
//		Vector2 pos1=ground.getPosition();
//		Helper.println("Physics position"+pos1);
//		pos1.set(ConvertToWorld(ground.getPosition().x), ConvertToWorld(ground.getPosition().y));
//			float angleDeg = ground.getAngle() * MathUtils.radiansToDegrees;
//		GlobalVars.ge.getRenderer().render(image, ConvertToWorld(pos1.x), ConvertToWorld(pos1.y),width , height, width/2, height/2,angleDeg, 1,1);
			
			GlobalVars.ge.getRenderer().render(image,
					GameMenuInfo.ratio_w*PhysicsHelper.ConvertToWorld(floor.getPosition().x)-GameMenuInfo.ratio_h*(width/2),
					GameMenuInfo.ratio_h*PhysicsHelper.ConvertToWorld(floor.getPosition().y)-GameMenuInfo.ratio_w*(height/2),
					GameMenuInfo.ratio_h*(width), 
					GameMenuInfo.ratio_w*(height), 
					GameMenuInfo.ratio_h*(width/2), 
					GameMenuInfo.ratio_w*(height/2),
					MathUtils.radiansToDegrees*floor.getAngle(), 1,1);
			if(first ){
//			Helper.println("Car X from phy to game world: " + player.getPosition().x);
//			Helper.println("Car Render X: " + (player.getPosition().x* GameMenuInfo.ratio_w));
//		Helper.println("delta time :::"+1/Gdx.graphics.getDeltaTime());
			Helper.println("Floor rendering at:::"+this.getLocation()+"angle ::"+floor.getAngle()*MathUtils.radiansToDegrees+"physics world position::"+floor.getPosition());
	
			first = false;
		} 

	}
	
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
//		assetPack.addTexture(AssetConstants.IMG_PLAYER1_THROW1);
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
		this.image =  Helper.getImageFromAssets(imagePath);
		
	}
	
	
	@Override
	public void step(long stepTime) {


	}

	public Body getBody() {
		return floor;
	}


	@Override
	public void onEvent(Point hitPoint) {


	}

	Point renderPoint = new Point();
	public Point getRenderLocation() {
		this.renderPoint.setLocation(x * GameMenuInfo.ratio_w,
				y * GameMenuInfo.ratio_h );
		return renderPoint;
	}
}
