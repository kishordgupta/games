package com.rhymes.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.entity.elements.pinball.Ball;
import com.rhymes.game.stage.levels.TestLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.helpers.Helper;


public class PhysicalWorld {

	// Box2D operates in meters and 32 pixels make up 1 meter (1 unit in box2d world)
	private static final float 	BOX2D_RATIO = 32.0f;

	public static final short 	COLLIDE_CAT_BALL 		= 0x0001;
	public static final short 	COLLIDE_CAT_FLOOR 		= 0x0002;
	public static final short 	COLLIDE_CAT_GROUND 		= 0x0004;
	
	private static final short	COLLIDE_MASK_BALL 	= COLLIDE_CAT_FLOOR | COLLIDE_CAT_GROUND;
	private static final short	COLLIDE_MASK_FLOOR	= COLLIDE_CAT_BALL;
	private static final short	COLLIDE_MASK_GROUND	= COLLIDE_CAT_BALL;
	
	// Pixels to Meters conversions
	public static final float	w2p(float n) { return n * BOX2D_RATIO; }
	// Meters to Pixels conversions
	public static final float	p2w(float n) { return n / BOX2D_RATIO; }

	private World 				world;
	private Array<Body>			borderFrames = new Array<Body>(3);
	private Body				ballBody;	// the ball in the game
	
	private Vector2 worldGravity = new Vector2(1.0f, -9.8f);
	
	public PhysicalWorld() {
		reset();
	}
	
	/** Resets the world */
	public void reset() {
		// Create the box2d world
		Helper.println("Creating new world with Gravity: Gx = " 
				+ worldGravity.x + " Gy: " + worldGravity.y);
		world = new World(worldGravity, true);

		// set up collision listeners
		// and delegates
//		collisionListener = new CollisionListener();
//		world.setContactListener(collisionListener);
		
		// set up the boundaries of the game world so the ball won't leave the screen
		setupBounds();
	}
	
	/** Sets up the boundaries of the world with kinematic bodies */
	private void setupBounds() {
		// add bottom border frame
		borderFrames.add(createFrame(new Vector2(0, 0), new Vector2(p2w(Gdx.graphics.getWidth()), 0)));

		// add left border frame
		borderFrames.add(createFrame(new Vector2(0, 0), new Vector2(0, p2w(Gdx.graphics.getHeight()))));
		
		// add right border frame
		borderFrames.add(createFrame(new Vector2(p2w(Gdx.graphics.getWidth()), p2w(Gdx.graphics.getHeight())), 
				new Vector2(p2w(Gdx.graphics.getWidth()), 0)));
	}

	/**
	 * Creates a kinematic body at the specified x,y position with a specified size.
	 * Values are expressed in world coordinates 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	private Body createFrame(Vector2 v1, Vector2 v2) {
		// setting up bottom box to act as edge
		BodyDef 		frameEdgeDef = new BodyDef();
		EdgeShape 		frameBorder = new EdgeShape();
		FixtureDef 		frameBoxFixtureDef = new FixtureDef();
		
		////////////////////////////////////
		// setting up the box definition
	
		// Using a kinematic body allows to set its velocity regardless of 
		// the forces in the world. Its mass == 0
		frameEdgeDef.type = BodyType.KinematicBody;
//		frameEdgeDef.position.set(v1);
		
		// sets the size of the box
		frameBorder.set(v1, v2);

		// create the fixture for the box and set collision filters
		frameBoxFixtureDef.shape = frameBorder;
		// collision filter and mask
		frameBoxFixtureDef.filter.categoryBits = COLLIDE_CAT_GROUND;
		frameBoxFixtureDef.filter.maskBits = COLLIDE_MASK_GROUND;

		// create the body and add the fixture
		Body frame = world.createBody(frameEdgeDef);
		frame.createFixture(frameBoxFixtureDef);
		
		// DON'T FORGET to do this!
		// dispose of the shapes to avoid memory leaks.
		frameBorder.dispose();
		
		return frame;
	}
	
	/** Creates and adds the ball body to the world with values from the global configuration
	 * It also returns the created body */
	public Body addBallBody(Ball ball) {
		BodyDef bodyDef = new BodyDef();
		
		// make the body Dynamic so the forces will act on it
		bodyDef.type = BodyType.DynamicBody;
		// position the body at middle top of the screen
		bodyDef.position.set(new Vector2(p2w(ball.getX()), 
				p2w(ball.getY())));
		bodyDef.linearDamping = 0.1f;
		bodyDef.allowSleep = false;
		
		ballBody = world.createBody(bodyDef);
		CircleShape shape = new CircleShape();
		shape.setRadius(p2w(ball.getWidth()));
//		shape.setPosition(new Vector2(-shape.getRadius() / 2, -shape.getRadius() / 2));
		
		
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
//		fixtureDef.filter.categoryBits = COLLIDE_CAT_BALL;
//		fixtureDef.filter.maskBits = COLLIDE_MASK_BALL;
		fixtureDef.friction = 0.9f;
		fixtureDef.density = 100;
		fixtureDef.restitution = 0;
		ballBody.createFixture(fixtureDef);
		
		// update the ball object's position and rotation and 
		// add it as user data to the box2d body
		
		
		ballBody.setUserData(ball);


		// DON'T FORGET to do this!
		// dispose of the shapes to avoid memory leaks.
		shape.dispose();
		Helper.println("Ball added");
		return ballBody;
	}

	
	/** Creates and adds a body based on the Floor object. It returns the newly created object *//* 
	public Body addFloorBody(Floor floor) {
		BodyDef floorBodyDef 		= new BodyDef();
		FixtureDef floorFixtureDef 	= new FixtureDef();
		PolygonShape floorShape 	= new PolygonShape();

		// the engine will update the position
		floorBodyDef.type = BodyType.KinematicBody;
		
		// set the position just outside the world (at vertical padding) vertically 
		// and aligned with the floor's position horizontally
//		floorBodyDef.position.set(p2w(floor.getX()), -p2w(config.getFloorMargin()));
		floorBodyDef.position.set(p2w(floor.getX() + floor.getFloorWidth() / 2),  p2w(floor.getY() - config.getFloorsMargin()));
		
		Body floorBody = world.createBody(floorBodyDef);

		floorFixtureDef.shape = floorShape;
		floorFixtureDef.filter.categoryBits = COLLIDE_CAT_FLOOR;
		floorFixtureDef.filter.maskBits = COLLIDE_MASK_FLOOR;

		floorShape.setAsBox(p2w(floor.getFloorWidth() / 2), 0.25f);
		
		floorBody.createFixture(floorFixtureDef);

		// Adding the floor to the user data so the renderer can access
		// information about it to properly render it.
		floorBody.setUserData(floor);

		// DON'T FORGET to do this!
		// dispose of the shapes to avoid memory leaks.
		floorShape.dispose();

		// add floor to the array to retain the reference
		floors.add(floorBody);
		// create bidirectional reference
		floor.setBody(floorBody);	
		floorBody.setLinearVelocity(0, p2w(getScrollingVelocity()));
		return floorBody;
	}

	
	// ~ getters -----
	
	public Body getBallBody() {
		return ballBody;
	}
	public Array<Body> getFloors() {
		return floors;
	}
*/	
	
	/** Updates the box2d world. Simulation takes place */
	public void update(float deltaTime) {
		world.step(deltaTime, 8, 1);
		Ball  ball = (Ball) ballBody.getUserData(); 
		ball.setX(w2p(ballBody.getPosition().x));
		ball.setY(w2p(ballBody.getPosition().y));
//		((TestLevel)GlobalVars.ge.getCurrentStage()).ball.setX(w2p(ballBody.getPosition().x));
//		((TestLevel)GlobalVars.ge.getCurrentStage()).ball.setY(w2p(ballBody.getPosition().y));
//		((TestLevel)GlobalVars.ge.getCurrentStage()).ball.setRotateAngle(ballBody.getAngle());
//		ballBody.setUserData(((TestLevel)GlobalVars.ge.getCurrentStage()).ball);
		
		Helper.println("Ball id: " +  ((ElementBase)ballBody.getUserData()).getId());
		Helper.printKeyVal("Ball-> x ", w2p(ballBody.getPosition().x));
		Helper.printKeyVal("Ball-> y ", w2p(ballBody.getPosition().y));
		Helper.printKeyVal("Ball-> angle ", ballBody.getAngle());
		
//		Ball ball = (Ball) ballBody.getUserData();
//		ball.setPosition(w2p(ballBody.getPosition().x), w2p(ballBody.getPosition().y));
//		ball.setRotation(ballBody.getAngle());
//		ballBody.setUserData(ball);
	}
	
	public Vector2 getGravity() {
		return world == null ? null : world.getGravity();
	}
	
	public void setGravity(Vector2 gravity) {
		world.setGravity(gravity);
	}
	
	
	
	/** removes the body from the world **/
	public void removeBody(Body body) {
		world.destroyBody(body);
	}

	
	
	public void applyLinearImpulse(Vector2 vector)
	{
		ballBody.applyForceToCenter(vector);
//		ballBody.applyLinearImpulse(1000, 10000, 10, 10);
//		ballBody.applyLinearImpulse(vector, ballBody.getWorldCenter());
//		System.out.println("Applying impulse");
	}
}