package com.rhymes.game.entity.elements.physical;

import java.util.ArrayList;
import java.util.logging.Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
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
import com.rhymes.game.stage.menus.stick.LevelInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Lift extends ElementBase {

	private int COUNT;
	private boolean movable = false;
	private World world;
	private Vector2 anchor = new Vector2();
	private Body startBody;
	private Body endBody;
	private boolean first = true;
	private TextureRegion imageRope;
	private Body link;
	// private float elevetorWidth= 20f;
	// private float elevetorHeight= 05f;

	ArrayList<Body> links = new ArrayList<Body>();
	private Vector2 startPoint = new Vector2();
	private Body nLink;
	private float calculatedBodyangle;
	private Body steelBall;
	private Body elevetor;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.imageRope = Helper.getImageFromAssets(AssetConstants.IMG_PATH);
	}

	public Lift(Vector2 startPoint, Body sBody, Vector2 endPoint, Body eBody,
			int count, World world, boolean movable, float width, float height, boolean liftUp) {
		// TODO Auto-generated constructor stub
		this.x = startPoint.x;
		this.y = startPoint.y;
		this.width = width;
		this.height = height;
		this.COUNT = count;
		this.startPoint = startPoint;
		anchor.set(PhysicsHelper.ConvertToBox(startPoint.x),
				PhysicsHelper.ConvertToBox(startPoint.y));
		this.world = world;
		this.startBody = sBody;
		this.endBody = eBody;
		this.movable = movable;
		
		if(!liftUp)
			direction = -1;

		createElevator();
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub

		assetPack.addTexture(AssetConstants.IMG_PATH);
	}

	@Override
	public void render() {
//		Helper.println("Lift rendering");
		this.update(Gdx.graphics.getDeltaTime());
		
		GlobalVars.ge.getScreen().getBatch().setColor(0.3f, 0.5f, 0.5f,1f);
		GlobalVars.ge.getRenderer().render(imageRope, this.x * LevelInfo.ratioX, this.y * LevelInfo.ratioX,
				LevelInfo.ratioX* this.width,
				LevelInfo.ratioY * this.height,
				0,
				0,
				(MathUtils.radiansToDegrees * elevetor.getAngle()), 1, 1f);
		GlobalVars.ge.getScreen().getBatch().setColor(Color.WHITE);
		// Helper.println("Iam here and renderinr points are::"+link.getPosition()+"angle::"+MathUtils.radiansToDegrees*link.getAngle());
		if (first) {
			// Helper.println("Car X from phy to game world: " +
			// player.getPosition().x);
			// Helper.println("delta time :::"+1/Gdx.graphics.getDeltaTime());

			// first = false;
		}

		if (Gdx.input.isKeyPressed(Keys.L)) {
			// if(PhysicsHelper.ConvertToWorld(elevetor.getPosition().y)<30)
			{
				if (first) {
					elevetor.setTransform(elevetor.getPosition().x,
							elevetor.getPosition().y + .5f, elevetor.getAngle());
					first = false;
				} else {
					// elevetor.setTransform(elevetor.getPosition().x,
					// elevetor.getPosition().y-1f, elevetor.getAngle());
					first = true;
				}
				// Helper.println("lift position:::"+PhysicsHelper.ConvertToWorld(elevetor.getPosition().y));
				// elevetor.applyLinearImpulse(0,
				// 50f,elevetor.getPosition().x+PhysicsHelper.ConvertToBox(width/2)
				// , elevetor.getPosition().y);
				// elevetor.setType(BodyType.KinematicBody);
				// elevetor.applyForceToCenter(0, -10f);
				// elevetor. applyLinearImpulse(1,02, elevetor.getPosition().x,
				// elevetor.getPosition().y);
			}

		}
	}

	public void update(float dt) {

	/*	PhysicsHelper.accumulator += dt;
		// Helper.println("i'm here in update"+PhysicsHelper.accumulator+"and dt:::"+dt);
		while (PhysicsHelper.accumulator > PhysicsHelper.BOX_STEP) {
//			world.step(PhysicsHelper.BOX_STEP,
//					PhysicsHelper.BOX_VELOCITY_ITERATIONS,
//					PhysicsHelper.BOX_POSITION_ITERATIONS);
			this.setLocation(
					PhysicsHelper.ConvertToWorld(elevetor.getPosition().x),
					PhysicsHelper.ConvertToWorld(elevetor.getPosition().y));
			PhysicsHelper.accumulator -= PhysicsHelper.BOX_STEP;
			// elevetor.setGravityScale(-1);
		}
		// if(PhysicsHelper.ConvertToWorld(elevetor.getPosition().y)<30)

		// elevetor.applyLinearImpulse(0,
		// 50f,elevetor.getPosition().x+PhysicsHelper.ConvertToBox(width/2) ,
		// elevetor.getPosition().y);
		// elevetor.setType(BodyType.KinematicBody);
		// elevetor.applyForceToCenter(0, -10f);
		// elevetor.applyLinearImpulse(1,02, elevetor.getPosition().x,
		// elevetor.getPosition().y);
*/
	}

	float muly = 0.025f * LevelInfo.ratioY;
	float mulyDown = 0.025f* LevelInfo.ratioY;
	float ty;
	int direction = 1;
	float targettime = 4000;
	float waitTime = 4000;
	float startWaitTime = 4000;

boolean start = false;
	@Override
	public void step(long stepTime) {
		super.step(stepTime);

		if(!start)
			return;
		

		if(startWaitTime > 0){
			startWaitTime -= stepTime;
			return;
		}
			
		if (ty > targettime) {
			waitTime-=stepTime;
			if(waitTime < 0){
				direction = -direction;
				ty = 0;
				waitTime = 4000;
			}
			else return;
		}

		if (direction < 0) {
			elevetor.setTransform(elevetor.getPosition().x,
					elevetor.getPosition().y - mulyDown, elevetor.getAngle());
			ty++;
		}
		if (direction > 0) {
			elevetor.setTransform(elevetor.getPosition().x,
					elevetor.getPosition().y + muly, elevetor.getAngle());
			ty++;
		}

		// Helper.println("mulyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy::"+elevetor.getTransform().getPosition());
		// Helper.println("lift position:::"+PhysicsHelper.ConvertToWorld(elevetor.getPosition().y));
	}

	public void createElevator() {
		// ceiling polygon shape
		PolygonShape polygonShape = new PolygonShape();
		// polygonShape.setAsBox(PhysicsHelper.ConvertToBox(width/2),PhysicsHelper.ConvertToBox(height/2));
		polygonShape.setAsBox(PhysicsHelper.ConvertToBox(width / 2),
				PhysicsHelper.ConvertToBox(height / 2),
				new Vector2(PhysicsHelper.ConvertToBox(width / 2),
						PhysicsHelper.ConvertToBox(height / 2)), 0f);
		// startPoint fixture;
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = 1;
		fixtureDef.friction = 1;
		fixtureDef.restitution = 0.5f;
		fixtureDef.shape = polygonShape;
		// ceiling body
		BodyDef bodyDef = new BodyDef();
		// bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(PhysicsHelper.ConvertToBox(startPoint.x),
				PhysicsHelper.ConvertToBox(startPoint.y));
		// sbody creation;
		elevetor = world.createBody(bodyDef);
		elevetor.createFixture(fixtureDef);
		elevetor.setUserData(this);
		elevetor.setActive(true);

	}

	// this is the core of the script: despite the official docs which suggest
	// to use Initialize,
	// use this method instead

	// }

	
	public Body elevator() {
		// TODO Auto-generated method stub
		return elevetor;
	}

	public Body getBody() {
		// TODO Auto-generated method stub
		return elevetor;
	}

	public void start() {
		start = true;
	}
}
