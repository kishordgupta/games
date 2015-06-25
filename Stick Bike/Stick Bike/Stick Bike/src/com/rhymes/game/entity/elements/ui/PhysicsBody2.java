package com.rhymes.game.entity.elements.ui;

import aurelienribon.bodyeditor.BodyEditorLoader;
import aurelienribon.box2deditor.FixtureAtlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.rhymes.game.stage.menus.stick.LevelInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class PhysicsBody2 extends ElementBase{
	Sprite s ;	
	Vector2 modelOrigin;
	Vector2 pos = new Vector2();
	@Override
	public void render() {
//		if(true)
//			return;
		

		pos = vialModel.getPosition();
		pos = vialModel.getPosition().sub(modelOrigin);
		s.setPosition(Helper.p2w(pos.x)* LevelInfo.ratioX, Helper.p2w(pos.y) * LevelInfo.ratioX);
//		Helper.println("Origin: " + this.bodyID + " -> " + Helper.p2w(modelOrigin.x)/*toString()*/);
//		s.setOrigin(Helper.p2w(modelOrigin.x), Helper.p2w(modelOrigin.y));
		s.setOrigin(0, 0);
		s.setRotation(vialModel.getAngle() * MathUtils.radiansToDegrees);
		s.draw(GlobalVars.ge.getScreen().getBatch(),1f);
		
//		Helper.println("PBody location: "+ this.getBodyID() + " x: " + s.getX() + " y: " + s.getY()) ;
	}

	String imagePath;
	String binPath;
	String fixturePath;
	private String bodyID = null; 
	private BodyType bodyType;
	private Body vialModel;
	private World world;
	
	public short categoryBits = 2;
	private short maskBits = 1;
	private short groupIndex= -1;
	private float density= 1f;
	private float friction= 0.5f;
	private float restitution= 0f;
	
	public Body getBody()
	{
		return vialModel;
	}
	
	
	public PhysicsBody2(float x, float y, float width, float height,
			String binPath, String imagePath,String fixturePath, World world,
			float density,
			float friction,
			float restitution,
			short groupIndex, 
			short categoryBits, short maskBits, String bodyID, int zIndex, BodyType bodyType) {
		super(x, y, width, 
				height, zIndex);
		this.imagePath = imagePath;
		this.binPath = binPath;
		this.fixturePath = fixturePath;
		this.world = world;
		this.density = density;
		this.friction = friction;
		this.restitution = restitution;
		this.groupIndex = groupIndex;
		this.categoryBits = categoryBits;
		this.maskBits = maskBits;
		this.bodyID = bodyID;
		this.bodyType = bodyType;
		createVialModel();
	}
	
	public PhysicsBody2(float x, float y, float width, float height,
			String binPath, String imagePath,String fixturePath, World world,short groupIndex, 
			short categoryBits, short maskBits, String bodyID, int zIndex, BodyType bodyType) {
		super(x, y, width, 
				height, zIndex);
		this.imagePath = imagePath;
		this.binPath = binPath;
		this.fixturePath = fixturePath;
		this.world = world;
		this.groupIndex = groupIndex;
		this.categoryBits = categoryBits;
		this.maskBits = maskBits;
		this.bodyID = bodyID;
		this.bodyType = bodyType;
		createVialModel();
	}
	
	public PhysicsBody2(float x, float y, float width, float height,
			String binPath, String imagePath,String fixturePath, World world, 
			short categoryBits, short maskBits, String bodyID, int zIndex, BodyType bodyType) {
		super(x, y, width, 
				height, zIndex);
		this.imagePath = imagePath;
		this.binPath = binPath;
		this.fixturePath = fixturePath;
		this.world = world;
		this.categoryBits = categoryBits;
		this.maskBits = maskBits;
		this.bodyID = bodyID;
		this.bodyType = bodyType;
		createVialModel();
	}
	
	public PhysicsBody2(float x, float y, float width, float height,
			String binPath, String imagePath,String fixturePath, World world, 
			short categoryBits,String bodyID, int zIndex, BodyType bodyType) {
		super(x, y, width, 
				height, zIndex);
		this.imagePath = imagePath;
		this.binPath = binPath;
		this.fixturePath = fixturePath;
		this.world = world;
		this.categoryBits = categoryBits;
		this.bodyID = bodyID;
		this.bodyType = bodyType;
		createVialModel();
	}
	
	public PhysicsBody2(float x, float y, float width, float height,
			String binPath, String imagePath,String fixturePath, World world, short categoryBits, int zIndex) {
		super(x, y, width, 
				height, zIndex);	
		this.imagePath = imagePath;
		this.binPath = binPath;
		this.fixturePath = fixturePath;
		this.world = world;
		this.categoryBits = categoryBits;		
		this.bodyType = bodyType.StaticBody;
		createVialModel();
	}
	

	private void createVialModel() {
	    BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal(binPath));

	    BodyDef vialBodyDef = new BodyDef();
		vialBodyDef.position.set(new Vector2(Helper.w2p(x), Helper.w2p(y)));
		vialBodyDef.type = this.bodyType;
	 
	    FixtureDef fd = new FixtureDef();
	    fd.density = density;/*5;*/
	    fd.friction = friction;/*0.3f;*/
	    fd.restitution = restitution;/*0.3f;*/
	    fd.filter.groupIndex = groupIndex;
		fd.filter.categoryBits = categoryBits;
		fd.filter.maskBits = maskBits;
	 
	    vialModel = world.createBody(vialBodyDef);
	    vialModel.setUserData(this);
	 
	    loader.attachFixture(vialModel, fixturePath, fd, Helper.w2p(width));

		this.x = Helper.p2w(vialModel.getPosition().x);
		this.y = Helper.p2w(vialModel.getPosition().y);
//		Helper.println("PBody location C: " + this.getLocation());
		
		modelOrigin = loader.getOrigin(fixturePath, Helper.w2p(width)).cpy();
	}

	public String getBodyID() {
		return bodyID;
	}

	public void setBodyID(String bodyID) {
		this.bodyID = bodyID;
	}

	/**
	 * Add all the texture needed by this element in this function
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(imagePath);
	}


	@Override
	public void init() {
		this.image = Helper.getImageFromAssets(imagePath);
		s = new Sprite(image);
//		s.setSize(image.getRegionWidth(), image.getRegionHeight());
		s.setSize(width * LevelInfo.ratioX, width/image.getRegionWidth() * image.getRegionHeight() * LevelInfo.ratioX);
//		s.scale(LevelInfo.ratioX);
//		this.setSize(s.getWidth(), s.getHeight());
	}

	@Override
	public void step(long stepTime) {
	}


}
