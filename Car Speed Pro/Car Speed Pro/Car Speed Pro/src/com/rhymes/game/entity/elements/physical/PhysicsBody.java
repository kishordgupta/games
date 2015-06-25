package com.rhymes.game.entity.elements.physical;

import aurelienribon.box2deditor.FixtureAtlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.elements.physical.Ball;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class PhysicsBody extends ElementBase{
	
	
	@Override
	public void render() {
//		super.render();
		
		Helper.println("px: " +  vialModel.getPosition().x);
		Helper.println("pangle: " +  vialModel.getAngle());
		Helper.println("Avelocity: " +  vialModel.getAngularVelocity());
		Helper.println("Lvelocity: " +  vialModel.getLinearVelocity());
		Helper.println("py: " +  vialModel.getPosition().y);
		Helper.println("pwx: " +  Helper.p2w(vialModel.getPosition().x) * GameMenuInfo.ratio_w);
		Helper.println("pwy: " +  Helper.p2w(vialModel.getPosition().x) * GameMenuInfo.ratio_h);
		
		
		
		GlobalVars.ge.getRenderer().render
		(image, Helper.p2w(vialModel.getPosition().x) * GameMenuInfo.ratio_w, Helper.p2w(vialModel.getPosition().y)
				* GameMenuInfo.ratio_h,
				width* GameMenuInfo.ratio_w, height* GameMenuInfo.ratio_h);
//		GlobalVars.ge.getRenderer().render
//		(image, vialModel.getPosition().x * GameMenuInfo.ratio_w, vialModel.getPosition().y
//				* GameMenuInfo.ratio_h,
//				width* GameMenuInfo.ratio_w, height* GameMenuInfo.ratio_h);
//		vialModel.applyForceToCenter(20, 0);
		
//		GlobalVars.ge.getRenderer().render
//		(image, 10, 20,
//				width* GameMenuInfo.ratio_w, height* GameMenuInfo.ratio_h);
		
		
//		GlobalVars.ge.getRenderer().render(image, Helper.p2w(vialModel.getPosition().x)* GameMenuInfo.ratio_w, Helper.p2w(vialModel.getPosition().y)* GameMenuInfo.ratio_h,
//				height* GameMenuInfo.ratio_w, width* GameMenuInfo.ratio_w,
//				height/2* GameMenuInfo.ratio_w, width/2* GameMenuInfo.ratio_w, (float) (Math.toDegrees(vialModel.getAngle())), 1, 1);
		
	}

	String imagePath;
	String binPath;
	String fixturePath;
	private String bodyType = null; 

	private Body vialModel;
	private World world;
	
	public short categoryBits = 2;
	
	public Body getBody()
	{
		return vialModel;
	}
	public PhysicsBody(float x, float y, float width, float height,
			String binPath, String imagePath,String fixturePath, World world, 
			short categoryBits,String bodyType) {
		super(x/ GameMenuInfo.ratio_w, y/ GameMenuInfo.ratio_h, width/ GameMenuInfo.ratio_w, 
				height/ GameMenuInfo.ratio_h, 1);
		this.imagePath = imagePath;
		this.binPath = binPath;
		this.fixturePath = fixturePath;
		this.world = world;
		this.categoryBits = categoryBits;
		this.bodyType = bodyType;
		createVialModel();
	}
	
	public PhysicsBody(float x, float y, float width, float height,
			String binPath, String imagePath,String fixturePath, World world, short categoryBits) {
		super(x / GameMenuInfo.ratio_w, y / GameMenuInfo.ratio_h,
				width / GameMenuInfo.ratio_w, height / GameMenuInfo.ratio_h, 1);	
		this.imagePath = imagePath;
		this.binPath = binPath;
		this.fixturePath = fixturePath;
		this.world = world;
		this.categoryBits = categoryBits;
		this.bodyType = bodyType;
		createVialModel();
	}

	
	
	public PhysicsBody(float width, float height, Body body, String image) {
		vialModel = body;
		this.width = width;
		this.height = height;
		this.image = Helper.getImageFromAssets(image);
	}
	

	

	private void createVialModel() {
		// Create a FixtureAtlas which will automatically load the fixture
		// list for every body defined with the editor.
		// FixtureAtlas atlas = new
		// FixtureAtlas(Gdx.files.internal("data/bodies.bin"));
//		FixtureAtlas atlas = new FixtureAtlas(Gdx.files
//				.internal("data/basket/basket.bin"));

		FixtureAtlas atlas = new FixtureAtlas(Gdx.files
				.internal(AssetConstants.BOX2D_BIN_FOLDER + binPath));
		
		// Creation of the vial model:
		// 1. Create a BodyDef, as usual.
		BodyDef vialBodyDef = new BodyDef();
		vialBodyDef.position.set(new Vector2(Helper.w2p(x), Helper.w2p(y)));
//		vialBodyDef.position.set(new Vector2(Helper.w2p(x/GameMenuInfo.ratio_w), Helper.w2p(y
//				/GameMenuInfo.ratio_h)));
		
//		Helper.println("Pos: wx: " + x + " wy: " + y );
//		Helper.println("Pos: x: " + Helper.w2p(x) + " y: " + Helper.w2p(y) ); 
		vialBodyDef.type = BodyType.DynamicBody;

		// 2. Create a Body from the BodyDef, as usual.
		vialModel = world.createBody(vialBodyDef);
		vialModel.setUserData(this);

		// 3. Create its fixtures automatically by using the FixtureAtlas. Note
		// that the fixture name must exactly match the one used in the
		// editor. It has no real relationship with the asset used itself.
//		atlas.createFixtures(vialModel, "gfx\\test01.png", width, height);
		
		FixtureDef fixtureDef = new FixtureDef();
		
		fixtureDef.filter.categoryBits = categoryBits;
		fixtureDef.filter.maskBits = 1;
		
//		fixtureDef.shape = ;
		fixtureDef.friction = 1;
		fixtureDef.density = 2 ;
		fixtureDef.restitution = 0.3f;
		atlas.createFixtures(vialModel, fixturePath, 
				Helper.w2p(width), Helper.w2p(height), fixtureDef);
//		atlas.createFixtures(vialModel, fixturePath, 
//				Helper.w2p(width/GameMenuInfo.ratio_w), Helper.w2p(height/GameMenuInfo.ratio_h), fixtureDef);
//		vialModel.createFixture(fixtureDef);
		// System.out.println("vialsize.x :"+
		// VIAL_SIZE.x+"vialsize.y :"+VIAL_SIZE.y);

	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	/**
	 * Add all the texture needed by this element in this function
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
//		assetPack.addTexture(imagePath);
	}


	@Override
	public void init() {
	}

	@Override
	public void step(long stepTime) {
	}


	
	private void load()
	{
		
	}
}
