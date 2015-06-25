package com.rhymes.game.data;

import aurelienribon.box2deditor.FixtureAtlas;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.Point;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class App implements ApplicationListener {

	// -------------------------------------------------------------------------
	// Static fields
	// -------------------------------------------------------------------------
	
	private static final Vector2 WORLD_SIZE = new Vector2(10, 15);
	private static final Vector2 VIAL_SIZE = new Vector2(3, 3);
	private static final Vector2 BALL_SIZE = new Vector2(0.6f, 0.6f);
//	private static final Vector2 VIAL_POS = new Vector2(-4, -7);
	private static final Vector2 VIAL_POS = new Vector2(0, -7);
	private static final Vector2 VIAL_BSKT_POS = new Vector2(200, 10);

	private static final int MAX_BALL_COUNT = 10;

	// -------------------------------------------------------------------------
	// Public API
	// -------------------------------------------------------------------------
	
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	private OrthographicCamera camera;

	private World world;

	private Body vialModel;
	private Texture vialTexture;
	private Sprite vialSprite;
	
	
	private Body[] ballModels;
	private Texture ballTexture;
	private Sprite[] ballSprites;

	private Timer timer;
	private int timerBallIndex;

	private final Random rand = new Random();
	private final Vector2 tmpVec = new Vector2();
	

	@Override
	public void create() {
		Gdx.input.setInputProcessor(inputProcessor);

		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		camera = new OrthographicCamera(WORLD_SIZE.x, WORLD_SIZE.y);
		camera.position.set(0, 0, 0);
		camera.update();

		world = new World(new Vector2(0, -5), true);

		createVialModel();
//		createVialModelBasket();
		createBallModels();
		createSprites();

		restart();
	}

	private void createVialModel() {
		// Create a FixtureAtlas which will automatically load the fixture
		// list for every body defined with the editor.
//		FixtureAtlas atlas = new FixtureAtlas(Gdx.files.internal("data/bodies.bin"));
		FixtureAtlas atlas = new FixtureAtlas(Gdx.files.internal("data/basket/basket.bin"));

		// Creation of the vial model:
		// 1. Create a BodyDef, as usual.
		BodyDef vialBodyDef = new BodyDef();
		vialBodyDef.position.set(VIAL_POS);
		vialBodyDef.type = BodyType.StaticBody;

		// 2. Create a Body from the BodyDef, as usual.
		vialModel = world.createBody(vialBodyDef);

		// 3. Create its fixtures automatically by using the FixtureAtlas. Note
		//    that the fixture name must exactly match the one used in the
		//    editor. It has no real relationship with the asset used itself.
		atlas.createFixtures(vialModel, "gfx\\test01.png", VIAL_SIZE.x*2, VIAL_SIZE.y*2);	
		
		
		
		System.out.println("vialsize.x :"+ VIAL_SIZE.x+"vialsize.y :"+VIAL_SIZE.y);
		
//		atlas.createFixtures(vialModel, "gfx\\m-ballstick.png", VIAL_SIZE.x, VIAL_SIZE.y);
	}
	
//	private void createVialModelBasket() {
//		// Create a FixtureAtlas which will automatically load the fixture
//		// list for every body defined with the editor.
//		FixtureAtlas atlas = new FixtureAtlas(Gdx.files.internal("data/basket/basket.bin"));
//
//		// Creation of the vial model:
//		// 1. Create a BodyDef, as usual.
//		BodyDef vialBodyDef = new BodyDef();
//		vialBodyDef.position.set(VIAL_BSKT_POS);
//		vialBodyDef.type = BodyType.StaticBody;
//
//		// 2. Create a Body from the BodyDef, as usual.
//		vialModel = world.createBody(vialBodyDef);
//
//		// 3. Create its fixtures automatically by using the FixtureAtlas. Note
//		//    that the fixture name must exactly match the one used in the
//		//    editor. It has no real relationship with the asset used itself.
//		atlas.createFixtures(vialModel, "gfx\\m-ballstick.png", VIAL_SIZE.x, VIAL_SIZE.y);
//	}

	private void createBallModels() {
		BodyDef ballBodyDef = new BodyDef();
		ballBodyDef.type = BodyType.DynamicBody;

		CircleShape ballShape = new CircleShape();
		ballShape.setRadius(BALL_SIZE.x/2);

		ballModels = new Body[MAX_BALL_COUNT];
		for (int i=0; i<MAX_BALL_COUNT; i++) {
			ballModels[i] = world.createBody(ballBodyDef);
			ballModels[i].createFixture(ballShape, 1);
		}
	}

	private void createSprites() {
		// Define the Sprite, as usual.
//		vialTexture = new Texture(Gdx.files.internal("data/gfx/vial.png"));
		vialTexture = new Texture(Gdx.files.internal("data/basket/gfx/test01.png"));
		vialTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		vialSprite = new Sprite(vialTexture);
		vialSprite.setSize(VIAL_SIZE.x *2, VIAL_SIZE.y*2);

		// "vialModel.getPosition()" returns the reference point, which is the
		// bottom left corner of the asset used for each body defined with the
		// FixtureAtlas.
		Vector2 vialPos = vialModel.getPosition();
		vialSprite.setPosition(vialPos.x, vialPos.y);
		System.out.println("vialposition.x :"+ vialPos.x+"vialposition.y :"+vialPos.y);
		
		//for basket
//		
//		// Define the Sprite, as usual.
//		vialTexture = new Texture(Gdx.files.internal("data/gfx/vial.png"));
//		vialTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//		vialSprite = new Sprite(vialTexture);
//		vialSprite.setSize(VIAL_SIZE.x, VIAL_SIZE.y);
//
//		// "vialModel.getPosition()" returns the reference point, which is the
//		// bottom left corner of the asset used for each body defined with the
//		// FixtureAtlas.
//		Vector2 vialPos = vialModel.getPosition();
//		vialSprite.setPosition(vialPos.x, vialPos.y);
		///basket end

		ballTexture = new Texture(Gdx.files.internal("data/basket/gfx/playball.png"));
		ballTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		ballSprites = new Sprite[MAX_BALL_COUNT];
		for (int i=0; i<MAX_BALL_COUNT; i++) {
			ballSprites[i] = new Sprite(ballTexture);
			ballSprites[i].setSize(BALL_SIZE.x, BALL_SIZE.y);
			ballSprites[i].setOrigin(BALL_SIZE.x/2, BALL_SIZE.y/2);
		}
	}

	@Override
	public void dispose() {
		timer.cancel();
		vialTexture.dispose();
		ballTexture.dispose();
		spriteBatch.dispose();
		font.dispose();
		world.dispose();
	}

	@Override
	public void render() {
		// Update
//		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		world.step(1 / 60f, 10, 10);
		for (int i=0; i<MAX_BALL_COUNT; i++) {
			Vector2 pos = ballModels[i].getPosition().sub(
				ballSprites[i].getWidth()/2,
				ballSprites[i].getHeight()/2);
			float angleDeg = ballModels[i].getAngle() * MathUtils.radiansToDegrees;

			ballSprites[i].setPosition(pos.x, pos.y);
			ballSprites[i].setRotation(angleDeg);
		}

		// Render
//		GL10 gl = Gdx.gl10;
//		gl.glClearColor(1, 1, 1, 1);
//		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		vialSprite.draw(spriteBatch);
		for (int i=0; i<MAX_BALL_COUNT; i++)
			ballSprites[i].draw(spriteBatch);
		spriteBatch.end();

		spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0,
			Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		spriteBatch.begin();
		font.draw(spriteBatch, "Touch the screen to restart", 5, 25);
		spriteBatch.end();
	}

	@Override
	public void resize(int i, int i1) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {

	}
	
	// -------------------------------------------------------------------------
	// Internals
	// -------------------------------------------------------------------------

	private void restart() {
		for (int i=0; i<MAX_BALL_COUNT; i++) {
			float tx = rand.nextFloat() * 1.0f - 0.5f;
			float ty = WORLD_SIZE.y/2 + BALL_SIZE.y*5;
			float angle = rand.nextFloat() * MathUtils.PI * 2;

			ballModels[i].setActive(false);
			ballModels[i].setLinearVelocity(tmpVec.set(0, 0));
			ballModels[i].setAngularVelocity(0);
			ballModels[i].setTransform(tmpVec.set(tx, ty), angle);
		}

		if (timer != null)
			timer.cancel();
		
		timerBallIndex = 0;
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override public void run() {
				if (timerBallIndex < ballModels.length) {
					ballModels[timerBallIndex].setAwake(true);
					ballModels[timerBallIndex].setActive(true);
					timerBallIndex += 1;
				} else {
					timer.cancel();
				}
			}
		}, 100, 100);
	}

	// -------------------------------------------------------------------------
	// Inputs
	// -------------------------------------------------------------------------

	private final InputProcessor inputProcessor = new InputAdapter() {
		@Override
		public boolean touchDown(int x, int y, int pointer, int button) {
//			restart();
			Vector2 f;
			
			if((x> Gdx.graphics.getWidth()/2)&&(y> Gdx.graphics.getWidth()/2))
			{
				 f = new Vector2(50, 50);	
			}
			else
				 f = new Vector2(-50, 50);	
			
			
			for (int i=0; i<MAX_BALL_COUNT; i++) {
//				float tx = rand.nextFloat() * 1.0f - 0.5f;
//				float ty = WORLD_SIZE.y/2 + BALL_SIZE.y*5;
//				float angle = rand.nextFloat() * MathUtils.PI * 2;
//
//				ballModels[i].setActive(false);
//				ballModels[i].setLinearVelocity(tmpVec.set(0, 0));
//				ballModels[i].setAngularVelocity(0);
//				ballModels[i].setTransform(tmpVec.set(tx, ty), angle);
//				Vector2 v = new Vector2(ballSprites[i].getOriginX(),ballSprites[i].getOriginY() );
				Vector2 v = new Vector2 (ballModels[i].getWorldCenter());
				ballModels[i].applyForce(f, v);
//				ballModels[i].applyTorque(5f);
//				ballModels[i].applyLinearImpulse(f, v);
//				ballModels[i].getWorldCenter();
			}
	
			return false;
		}
	};
}
