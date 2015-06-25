package com.rhymes.game.entity.elements.physical;

import java.util.Vector;

import aurelienribon.box2deditor.FixtureAtlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
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

public class Ball extends ElementBase implements InteractionTouchCallbacks{
	
	float rad;
	
	boolean first = true;
	@Override
	public void render() {
		// Helper.println("Pos: x: " + Helper.p2w(ballModel.getPosition().x) +
		// " y: " + Helper.p2w(ballModel.getPosition().y));
		// Helper.println("Ball Pos: x: " + ballModel.getPosition().x + " y: " +
		// ballModel.getPosition().y);
		
		
		this.setLocation(Helper.p2w(ballModel.getPosition().x), Helper
				.p2w(ballModel.getPosition().y)
				);
		
		if(first){
			Helper.println("Ball X from phy to game world: " + x);
			Helper.println("Ball Render X: " + (x * GameMenuInfo.ratio_w - rad));
			
			first = false;
		}
		
//		GlobalVars.ge.getRenderer().render(image,
//				x,
//				y , radius * 2,
//				radius * 2, radius, radius,
//				(float) (ballModel.getAngle() * MathUtils.radiansToDegrees), 1,
//				1);
		
		rad = radius * GameMenuInfo.ratio_w ;

		GlobalVars.ge.getRenderer().render(image,
				x * GameMenuInfo.ratio_w - rad,
				y * GameMenuInfo.ratio_h - rad, rad * 2f,
				rad * 2f, rad, rad,
				(float) (ballModel.getAngle() * MathUtils.radiansToDegrees), 1,
				1);
	}

	String imagePath;

	private Body ballModel;

	private World world;

	public float radius;

	private boolean isStatic;

	private boolean collided = false;

	private boolean active = true;

	public static final short categoryBits = 1;
	
	private float prevY = 1;

	public Ball() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ball(float x, float y, float radius, World world,
			boolean isStatic) {
		super(x / GameMenuInfo.ratio_w, y / GameMenuInfo.ratio_h, 0, 0, 1);
		Helper.println("X: " + x);
		Helper.println("After X: " +  this.x);
		y = y / GameMenuInfo.ratio_h;
		this.radius = radius / GameMenuInfo.ratio_w;
		this.world = world;
		this.isStatic = isStatic;
		createBallModel();
		GlobalVars.ge.getCurrentStage().subscribeToInteraction(this,
				InteractionTouch.class);
	}
	public Ball(float x, float y, float radius, String imagePath, World world,
			boolean isStatic) {
		super(x / GameMenuInfo.ratio_w, y / GameMenuInfo.ratio_h, 0, 0, 1);
		this.radius = radius / GameMenuInfo.ratio_w;
		
		this.imagePath = imagePath;
		this.world = world;
		this.isStatic = isStatic;
		createBallModel();
		GlobalVars.ge.getCurrentStage().subscribeToInteraction(this,
				InteractionTouch.class);
	}

	private void createBallModel() {
		BodyDef ballBodyDef = new BodyDef();
		ballBodyDef.type = BodyType.DynamicBody;
		if (isStatic) {
			// ballModel.setType(BodyDef.BodyType.StaticBody);
			ballBodyDef.type = BodyType.StaticBody;
		} else {
			// ballModel.setType(BodyDef.BodyType.DynamicBody);
			ballBodyDef.type = BodyType.DynamicBody;
		}
//		ballBodyDef.position.set(Helper.w2p(x/GameMenuInfo.ratio_w), Helper.w2p(y/GameMenuInfo.ratio_h));
		ballBodyDef.position.set(Helper.w2p(x), Helper.w2p(y));

		CircleShape ballShape = new CircleShape();
		ballShape.setRadius(Helper.w2p(radius));
		
		// ballShape.setRadius(radius);
		ballModel = world.createBody(ballBodyDef);
		ballModel.setUserData(this);
		
		// ballModel.setLinearVelocity(new Vector2(0, -15.4f));
		FixtureDef fixtureDef = new FixtureDef();

		fixtureDef.filter.categoryBits = categoryBits;
		fixtureDef.filter.maskBits = 2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32;
		fixtureDef.shape = ballShape;
		fixtureDef.friction = 0.3f;
//		fixtureDef.density = (float) (0.2f / radius * radius * Math.PI);
		
//		if(Gdx.graphics.getWidth() <= 450)			
//			fixtureDef.density = 0.6f;
//		else if(Gdx.graphics.getWidth() <= 600) 
			fixtureDef.density = 0.6f;
//		else
//			fixtureDef.density = 0.6f;
		
		
//		fixtureDef.density *= 1.2f;
		
		Helper.println("\n\n\n\nDensity: " + fixtureDef.density);
		fixtureDef.restitution = 0.7f;
		

		// Helper.println("linearVelocity at" +
		// ballModel.getLinearVelocityFromWorldPoint(new Vector2(x,y)));

		ballModel.createFixture(fixtureDef);
	}

	/**
	 * Add all the texture needed by this element in this function
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
//		assetPack.addTexture(imagePath);
		assetPack.addTexture(AssetConstants.IMG_BASKET_BALL);
		assetPack.addTexture(AssetConstants.IMG_RUBBER_BALL);
		assetPack.addTexture(AssetConstants.IMG_BOWL_BALL);
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
		
	}
	
	
	public boolean startThrow = false;
	
	int count = 0;
	float prevX = 1;
	@Override
	public void step(long stepTime) {
		if(startThrow){
			ballModel.setType(BodyDef.BodyType.DynamicBody);
		}

//		Helper.printKeyVal("PrevY: ", prevY);
//		Helper.printKeyVal("y: ", y);
		
		if(ballModel.getType() == BodyType.DynamicBody){			
			if(Math.abs(prevY - this.y ) < 0.01 &&
					Math.abs(prevX - this.x ) < 0.01 ){
				count++;
//				Helper.printKeyVal("Count", count);
				if(count > 10){
//					((BounceTest)GlobalVars.ge.getCurrentStage()).result.setState(GameState.OVER);
					count = 0;
				}
			}
			else
				count = 0;

			prevY = this.y;
			prevX = this.x;
		}
	}

	public Body getBody() {
		return ballModel;
	}

	public boolean virgin = true;

	public boolean isVirgin() {
		return virgin;
	}

	public void setVirgin(boolean virgin) {
		this.virgin = virgin;
	}

	@Override
	public void onEvent(Point hitPoint) {
//		if (this.getX()*GameMenuInfo.ratio_w <= hitPoint.x
//				&& this.getX()+ 2 * this.radius >= hitPoint.x
//				&& this.getY() + 2 * this.radius >= hitPoint.y
//				&& this.getY() <= hitPoint.y) {
////			applyForce(((BounceTestLevel2)GlobalVars.ge.getCurrentStage()).arrow.getHeight(),
////				((BounceTestLevel2)GlobalVars.ge.getCurrentStage()).arrow.getRotateAngle());
////		this.startThrow = true;
///*			if (virgin) {
//				Player.setCtlFlag(1);
//				virgin = false;
//			}*/
//		}
	}

	public void applyForce(float force, float angle) {
		if(GlobalVars.ge.getCurrentStage() instanceof BounceTest){
			if(!((BounceTest)GlobalVars.ge.getCurrentStage()).startNow)
				return;
		}
//		angle = 58f;
//		force = 1000f * 0.1f;
		Helper.println("Force: " + force  + " Angle: " + angle);
		if (virgin) {
			Player.setCtlFlag(1);
			virgin = false;
		}
//		try {
//			if(((BounceTest)GlobalVars.ge.getCurrentStage()).shotOngoing )
//				{
//				StartupInfo.sound_handler.stopSound();
//				return;
//				}
//		} catch (Exception e) {
//			return;
//		}
		if (this.startThrow) {
			((BounceTest)GlobalVars.ge.getCurrentStage()).arrow.showArrow = false;
			((BounceTest)GlobalVars.ge.getCurrentStage()).shotOngoing = true;
//			StartupInfo.sound_handler.playSound(SoundHandler.soundType.SOUND_FLICK);
			ballModel.setType(BodyDef.BodyType.DynamicBody);
			
			ballModel.applyForceToCenter((float) (force * Math.cos(angle
					* MathUtils.degreesToRadians)), (float) (force * Math
					.sin(angle * MathUtils.degreesToRadians)));
			if(StartupInfo.sound_handler.is_sound_active())
//			StartupInfo.sound_handler.playSound(SoundHandler.soundType.SOUND_CLICK);
			this.startThrow = false;
		}
	}

	public boolean isActive() {
		return active;
	}
	
	Point renderPoint = new Point();
	public Point getRenderLocation() {
		this.renderPoint.setLocation(x * GameMenuInfo.ratio_w,
				y * GameMenuInfo.ratio_h );
		return renderPoint;
	}
}
