/**
 * 
 */
package com.rhymes.game.entity.elements.physical;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.game.stage.menus.stick.LevelInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;
import com.rhymes.helpers.XMLReader;

/**
 * @author ASUS
 * 
 */
public class Elevator extends ElementBase {

	private World world;
	private int COUNT = 0;
	private float elevetorWidth;
	private float elevetorHeight;
	private Vector2 startPoint;
	private Vector2 endPoint;
	ArrayList<Body> elevators = new ArrayList<Body>();
	ArrayList<Vector2> vertexes = new ArrayList<Vector2>();
	private Body elevetor;

	ArrayList<ChainShape> chains = new ArrayList<ChainShape>();

	public Body getLastElevetor() {

		return elevators.get(elevators.size() - 1);
	}

	public Vector2 getLastVertex() {
		((ChainShape) elevetor.getFixtureList()
				.get(elevetor.getFixtureList().size() - 1).getShape())
				.getVertex(elevetor.getFixtureList().get(0).getShape()
						.getChildCount() - 1, vertex);
		return vertex;

	}

	private TextureRegion imageelevator;
	private boolean first = true;
	private Vector2 anchor = new Vector2();
	Vector2 vertex = new Vector2();
	private Vector2 vertexNext = new Vector2();
	private float calculatedAngle = 0f;
	private double calculatedWidth = 0f;
	private float liftWidth;

	/**
	 * 
	 */
	public Elevator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param zIndex
	 */
	public Elevator(World world, int COUNT, Vector2 startPoint,
			Vector2 endPoint, float width, float height, int zIndex) {
		// super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub]
		// this.x = startPoint.x;
		// this.y = startPoint.y;
		this.width = width;
		this.height = height;
		this.elevetorWidth = PhysicsHelper.ConvertToBox(width);
		this.elevetorHeight = PhysicsHelper.ConvertToBox(height);
		this.COUNT = COUNT;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.world = world;
		createElevetors();
	}

	public Elevator(World world, float height, int zIndex) {
		// super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub]
		// this.x = startPoint.x;
		// this.y = startPoint.y;
		this.width = width;
		this.height = height;
		this.elevetorWidth = PhysicsHelper.ConvertToBox(width);
		this.elevetorHeight = PhysicsHelper.ConvertToBox(height);
		this.COUNT = COUNT;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.world = world;
		createElevetors();
	}

	private float fallY = 9999;

	private void createElevetors() {
		// TODO Auto-generated method stub
		BodyDef bodyDef = new BodyDef();
		for (int i = 0; i < XMLReader.ropes.size; i++) {

			// if(XMLReader.ropes.get(i).pathType == "path")
			Helper.println("pathtype" + XMLReader.ropes.get(i).pathType);
			XMLReader.ropes.get(i).pathType = XMLReader.ropes.get(i).pathType.toLowerCase();
			// if(true)
			if ( !XMLReader.ropes.get(i).pathType.contains("lift")  ) {
				Vector2 vs[] = new Vector2[XMLReader.ropes.get(i).getNodes()
						.size()];
				// for (int vertexCount
				// =0;vertexCount<XMLReader.ropes.get(i).getNodes().size();vertexCount++)
				// {
				// vs[vertexCount] = new Vector2();
				// }
				for (int vertexCount = 0; vertexCount < XMLReader.ropes.get(i)
						.getNodes().size(); vertexCount++) {

					if (XMLReader.ropes.get(i).getNodes().get(vertexCount)
							.getY() < fallY)
						fallY = XMLReader.ropes.get(i).getNodes()
								.get(vertexCount).getY();
					Helper.println("fallY::"+ fallY);
//					 Helper.println("vertex count::"+ vertexCount);
					// vs[vertexCount] = new Vector2(vs[vertexCount-1].x +
					// elevetorWidth, vs[vertexCount-1].y * 1.10f);
					// Helper.println("vertex position in pixel:::"+PhysicsHelper.ConvertToWorld((vs[vertexCount-1].x)
					// ));
					// Helper.println("value of i:::"+i+"value of certexCpunt::"+vertexCount);
					try {
						// Helper.println("xml reader given rope no::::;"+XMLReader.ropes.get(i)+"xml reader given rope nodes ate::::;"+XMLReader.ropes.get(i).getNodes().get(vertexCount).getX());
						vs[vertexCount] = new Vector2(
								PhysicsHelper.ConvertToBox(XMLReader.ropes
										.get(i).getNodes().get(vertexCount)
										.getX()),
								PhysicsHelper.ConvertToBox(XMLReader.ropes
										.get(i).getNodes().get(vertexCount)
										.getY()));

						// Helper.println("rope  no:"+i+"rope node no:"+vertexCount+"rope node position"+PhysicsHelper.ConvertToWorld(vs[vertexCount].x)+"rope node position"+PhysicsHelper.ConvertToWorld(vs[vertexCount].y));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				ChainShape chain = new ChainShape();
				// chain.setPrevVertex(new Vector2(vs[0].x - elevetorWidth,
				// vs[0].y));
				//
				// chain.setNextVertex(new Vector2(vs[vs.length-1].x +
				// elevetorWidth, vs[vs.length-1].y * 1.05f));
				chain.createChain(vs);
				// chain.setRadius(.625f);
				FixtureDef chainShepDef = new FixtureDef();
				chainShepDef.friction = 250f;
				chainShepDef.shape = chain;
				chainShepDef.density = 0f;
				chainShepDef.restitution = 0f;
				// chainShepDef.filter.groupIndex = 1;
				chainShepDef.filter.categoryBits = 11;
				chainShepDef.filter.maskBits = 14 | 13 | 4 | 5 | 6 | 7 | 1;

				chains.add(chain);

				// bodyDef.position = anchor.add(new Vector2(0, i * 0.25f));
				// anchor.add(new Vector2(i*.25f, 0));
				// anchor.set(new Vector2(i*0f, i*0.55f));
				// anchor.add(PhysicsHelper.ConvertToBox(startPoint.x),
				// PhysicsHelper.ConvertToBox(startPoint.y));
				// anchor.add(startPoint);
				// calculatedAngle = Helper.getAngle(p, q);
				// bodyDef.angle = MathUtils.degreesToRadians*calculatedAngle;

				elevetor = world.createBody(bodyDef);
				elevetor.setUserData("elevator");
				elevetor.createFixture(chainShepDef);
				elevetor.setType(BodyType.StaticBody);
				elevators.add(elevetor);
				Helper.println("body angle is :::" + elevetor.getAngle());

			} else if (XMLReader.ropes.get(i).pathType
					.contains("lift")) {
				Vector2 vs1[] = new Vector2[XMLReader.ropes.get(i).getNodes()
						.size()];
				// for (int vertexCount
				// =0;vertexCount<XMLReader.ropes.get(i).getNodes().size();vertexCount++)
				// {
				// vs1[vertexCount] = new Vector2();
				// }

				for (int vertexCount = 0; vertexCount < XMLReader.ropes.get(i)
						.getNodes().size(); vertexCount++) {
//					if (XMLReader.ropes.get(i).getNodes().get(vertexCount)
//							.getY() < fallY)
//						fallY = XMLReader.ropes.get(i).getNodes()
//								.get(vertexCount).getY();
					// Helper.println("value of i:::"+i+"value of certexCpunt::"+vertexCount);
					vs1[vertexCount] = new Vector2(
							PhysicsHelper.ConvertToBox(XMLReader.ropes.get(i)
									.getNodes().get(vertexCount).getX()),
							PhysicsHelper.ConvertToBox(XMLReader.ropes.get(i)
									.getNodes().get(vertexCount).getY()));
					if (vertexCount == XMLReader.ropes.get(i).getNodes().size() - 1)
						liftWidth = PhysicsHelper
								.ConvertToWorld((float) Helper
										.pointToPointDistance(vs1[vertexCount],
												vs1[0]));
					Helper.println("lift width ::" + liftWidth);

				}
				
				boolean b = XMLReader.ropes.get(i).pathType.contains("up")  ;
				Helper.println("\n\n\n\n\n*****************************\nLift rope number :::" + i);
				Lift lift = new Lift(new Vector2(
						PhysicsHelper.ConvertToWorld(vs1[0].x),
						PhysicsHelper.ConvertToWorld(vs1[0].y)), null, null,
						null, 1, world, true, liftWidth, 7 * LevelInfo.ratioY, b);
				// lift.getBody().setTransform(lift.getBody().getPosition(),
				// (float) (lift.getBody().getAngle()+ Math.PI/2));
				GlobalVars.ge.getCurrentStage().addElement(lift);
				// elevators.add(lift.getBody());
				// elevators.add(elevetor);
			}

			// elevators.add(i, elevetor);
		}

		((BikeLevel) GlobalVars.ge.getCurrentStage()).setElevatorInfo(
				XMLReader.ropes.get(XMLReader.ropes.size - 1).getNodes()
						.getLast(), fallY);
	}

	// private void createElevetors() {
	// BodyDef bodyDef = new BodyDef();
	//
	//
	// Helper.println("Last node: " +
	// XMLReader.ropes.get(XMLReader.ropes.size-1).getNodes().getLast());
	//
	// for (int i = 0; i < XMLReader.ropes.size; i++) {
	//
	// Vector2 vs[] = new Vector2[XMLReader.ropes.get(i).getNodes().size()];
	// // for (int vertexCount
	// // =0;vertexCount<XMLReader.ropes.get(i).getNodes().size();vertexCount++)
	// // {
	// // vs[vertexCount] = new Vector2();
	// // }
	// for (int vertexCount = 0; vertexCount < XMLReader.ropes.get(i)
	// .getNodes().size(); vertexCount++) {
	//
	// if(XMLReader.ropes.get(i)
	// .getNodes().get(vertexCount).getY() < fallY)
	// fallY = XMLReader.ropes.get(i)
	// .getNodes().get(vertexCount).getY() ;
	// // Helper.println("vertex count::"+ vertexCount);
	// // vs[vertexCount] = new Vector2(vs[vertexCount-1].x +
	// // elevetorWidth, vs[vertexCount-1].y * 1.10f);
	// //
	// Helper.println("vertex position in pixel:::"+PhysicsHelper.ConvertToWorld((vs[vertexCount-1].x)
	// // ));
	// //
	// Helper.println("value of i:::"+i+"value of certexCpunt::"+vertexCount);
	// try {
	// //
	// Helper.println("xml reader given rope no::::;"+XMLReader.ropes.get(i)+"xml reader given rope nodes ate::::;"+XMLReader.ropes.get(i).getNodes().get(vertexCount).getX());
	// vs[vertexCount] = new Vector2(
	// PhysicsHelper.ConvertToBox(XMLReader.ropes.get(i)
	// .getNodes().get(vertexCount).getX()),
	// PhysicsHelper.ConvertToBox(XMLReader.ropes.get(i)
	// .getNodes().get(vertexCount).getY()));
	// Helper.println("rope  no:"+i+"rope node no:"+vertexCount+"rope node position"+PhysicsHelper.ConvertToWorld(vs[vertexCount].x)+"rope node position"+PhysicsHelper.ConvertToWorld(vs[vertexCount].y));
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	//
	// // vs[0]= startPoint;
	// // vs[0] = new Vector2(startPoint.x, startPoint.y);
	//
	// // vs[1]=new Vector2(vs[0].x+elevetorWidth, 0.8f);
	// // vs[2]=new Vector2(vs[1].x+elevetorWidth, 0.6f);
	// // vs[3]=new Vector2(vs[2].x+elevetorWidth, 0.4f);
	//
	// // for (int vertexCount =1;vertexCount<COUNT;vertexCount++)
	// // {
	// // Helper.println("vertex count::"+ vertexCount);
	// // vs[vertexCount] = new Vector2(vs[vertexCount-1].x +
	// // elevetorWidth, vs[vertexCount-1].y * 1.10f);
	// //
	// Helper.println("vertex position in pixel:::"+PhysicsHelper.ConvertToWorld((vs[vertexCount-1].x)
	// // ));
	// // }
	//
	// ChainShape chain = new ChainShape();
	// // chain.setPrevVertex(new Vector2(vs[0].x - elevetorWidth,
	// // vs[0].y));
	// //
	// // chain.setNextVertex(new Vector2(vs[vs.length-1].x +
	// // elevetorWidth, vs[vs.length-1].y * 1.05f));
	// chain.createChain(vs);
	// chains.add(chain);
	// // chain.setRadius(.625f);
	//
	// FixtureDef chainShepDef = new FixtureDef();
	// chainShepDef.friction = 0.5f;
	// chainShepDef.shape = chain;
	// chainShepDef.density = 100.0f;
	// chainShepDef.restitution = 0f;
	// // chainShepDef.filter.groupIndex = 1;
	// chainShepDef.filter.categoryBits = 11;
	// chainShepDef.filter.maskBits = 13 | 4 | 5 | 6 | 7 | 1;
	//
	// // bodyDef.position = anchor.add(new Vector2(0, i * 0.25f));
	// // anchor.add(new Vector2(i*.25f, 0));
	// // anchor.set(new Vector2(i*0f, i*0.55f));
	// // anchor.add(PhysicsHelper.ConvertToBox(startPoint.x),
	// // PhysicsHelper.ConvertToBox(startPoint.y));
	// // anchor.add(startPoint);
	//
	// // bodyDef.angle = MathUtils.degreesToRadians * calculatedAngle;
	// elevetor = world.createBody(bodyDef);
	// elevetor.setUserData("elevator");
	// elevetor.createFixture(chainShepDef);
	// elevetor.setType(BodyType.StaticBody);
	//
	// elevators.add(elevetor);
	// // elevators.add(i, elevetor);
	// }
	// // for(int j =0;j<vs.length;j++)
	// // {
	// // vertexes.add(vs[j]);
	// // chain.getVertex(j, vertex);
	// // vertexes.add(vertex);
	// // Helper.println("add to vertex array :: vertexes.add(vs[j]);:  "+
	// // PhysicsHelper.ConvertToWorld(vertexes.get(j).x));
	// // }
	// // }
	// // links.get(0).setType(BodyType.StaticBody);
	// // links.get(COUNT-1).setType(BodyType.StaticBody);
	// // Connects links together. First link is connected directly to the
	// // "ceiling".
	// // Body lastLink = null;
	// // for (Body elevetor : elevators) {
	// // DistanceJointDef distanceJointDef = new DistanceJointDef();
	// // if (lastLink == null) {
	// // // distanceJointDef.initialize(bodyA, bodyB, anchorA, anchorB);
	// // distanceJointDef.initialize(elevetor,sBody , elevetor.getPosition(),
	// // anchor);
	// // distanceJointDef.localAnchorA.set(e.getPosition().add(new
	// // Vector2(0f,.0125f)));
	// // distanceJointDef.localAnchorB.set(anchor);
	// // } else {
	// // distanceJointDef.initialize(link, lastLink, link.getPosition(),
	// // lastLink.getPosition());
	// // distanceJointDef.initialize(link, lastLink, link.getPosition(),
	// // lastLink.getPosition());
	// // distanceJointDef.localAnchorA.set(link.getPosition());
	// // distanceJointDef.localAnchorB.set(lastLink.getPosition());
	// //
	// Helper.println("link Position is ::"+link.getPosition()+"Last LinkPosition is ::"+
	// // lastLink.getPosition());
	// // }
	// // world.createJoint(distanceJointDef);
	// //
	// // lastLink = link;
	// // }
	//
	// ((BikeLevel)GlobalVars.ge.getCurrentStage()).setElevatorInfo(
	// XMLReader.ropes.get(XMLReader.ropes.size-1).getNodes().getLast(), fallY);
	//
	// }

	public void update(float dt) {

		PhysicsHelper.accumulator += dt;
		// Helper.println("i'm here in update"+PhysicsHelper.accumulator+"and dt:::"+dt);
		while (PhysicsHelper.accumulator > PhysicsHelper.BOX_STEP) {
			// world.step(PhysicsHelper.BOX_STEP,
			// PhysicsHelper.BOX_VELOCITY_ITERATIONS,
			// PhysicsHelper.BOX_POSITION_ITERATIONS);
//			this.setLocation(
//					PhysicsHelper.ConvertToWorld(elevetor.getPosition().x),
//					PhysicsHelper.ConvertToWorld(elevetor.getPosition().y));
			PhysicsHelper.accumulator -= PhysicsHelper.BOX_STEP;
		}

	}

	ChainShape ct;
	Vector2 v = new Vector2();
	Point p = new Point(), q = new Point();

	@Override
	public void render() {
		this.update(Gdx.graphics.getDeltaTime());

		for (int i = 0; i < chains.size(); i++) {
			ct = chains.get(i);

			for (int j = 1; j <= ct.getChildCount(); j++) {
				ct.getVertex(j - 1, vertex);
				ct.getVertex(j, v);

				p.setLocation(PhysicsHelper.ConvertToWorld(vertex.x)
						* LevelInfo.ratioX,
						PhysicsHelper.ConvertToWorld(vertex.y)
								* LevelInfo.ratioX);
				q.setLocation(PhysicsHelper.ConvertToWorld(v.x)
						* LevelInfo.ratioX, PhysicsHelper.ConvertToWorld(v.y)
						* LevelInfo.ratioX);

				

				if(q.x < Helper.getCameraLeft())
					continue;
				else if(p.x > Helper.getCameraRight())
					continue;
				
				GlobalVars.ge.getScreen().getBatch()
						.setColor(0.1f, 0.1f, 0.1f, 0.8f);

				// Helper.println("Angle: " + j + " -> " + Helper.getAngle(p,
				// q));
				GlobalVars.ge.getRenderer().render(imageelevator, p.x,
						p.y - this.height / 2f, p.distancePoint2Point(q),
						/* GameMenuInfo.ratio_h* */this.height, 0,
						this.height / 2f, Helper.getAngle(p, q) + 90, 1, 1);

				GlobalVars.ge.getScreen().getBatch().setColor(1f, 1f, 1f, 1f);

			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rhymes.ge.core.entity.elements.ElementBase#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		// this.imageelevator =
		// Helper.getImageFromAssets(AssetConstants.IMG_PATH);
		this.imageelevator = Helper.getImageFromAssets(AssetConstants.IMG_AXLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rhymes.ge.core.entity.elements.ElementBase#getAssets(com.rhymes.ge
	 * .pw.assets.AssetPack)
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub
		assetPack.addTexture(AssetConstants.IMG_AXLE);
	}

}
