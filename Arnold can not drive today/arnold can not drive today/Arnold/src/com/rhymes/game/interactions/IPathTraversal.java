package com.rhymes.game.interactions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.rhymes.game.entity.elements.burntherope.Boat;
import com.rhymes.game.entity.elements.path.Path;
import com.rhymes.game.entity.elements.path.PathSet;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.game.entity.elements.unsorted.BPath;
import com.rhymes.game.interactions.inputs.InteractionLeftRight;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class IPathTraversal extends InteractionBase {
	private static final float TOPSPEED = 2;
	private long elapsedTime;
	private float speed = 0;
	private int method = Path.METHOD_LEFT;
	private Path path;
	private TraversePointInfo info;

	private boolean shallRotate = false;
	private float accX;

	public IPathTraversal() {
		super();
	}

	@Override
	public void checkCondition(long elapsedTime) {
		this.elapsedTime = elapsedTime;
		// Helper.println("Elapsed: " + elapsedTime);
	}

	private boolean validateInfo(TraversePointInfo info) {
		if (info == null)
			return false;

		path = info.getPath();
		if (info.getPath() == null)
			return false;

		return true;

	}


	public float accAng() {
		/******* Accelerometer Output *******/
		if (Gdx.app.getType() == Application.ApplicationType.Android) {
			accX = Gdx.input.getAccelerometerX();
			accY = Gdx.input.getAccelerometerY();
		} else if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				accAngle--;
				accX++;
				accY++;
			} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				accX--;
				accY--;
				accAngle++;
			}
		}

		accAngle = (float) (Math.atan(accY / accX) * 180f / Math.PI);
		if (accX > 0f && accY < 0f) {
			accAngle = accAngle + 360f;
//			Helper.println("\n Angle1: " + accAngle);
		} else if (accX < 0f) {
			accAngle = accAngle + 180f;
//			Helper.println("\n Angle2: " + accAngle);

		}
//		Helper.println("\naccX: " + accX + ", accy: " + accY + " Angle: "
//				+ accAngle);
		return accAngle;
	}

	Point testA = new Point(20,20);
	Point testB = new Point(20,40);
	public float speedFactor(Point pointA, Point pointB) {
		float accAngle = 0;
		if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
//				if(Boat.handSetAngle > -43)
					Boat.handSetAngle -= 2.5;
			} else if (Gdx.input.isKeyPressed(Keys.RIGHT)){
//				if(Boat.handSetAngle < 43)
				Boat.handSetAngle += 2.5;
			}
			accAngle = Boat.handSetAngle;
		}
		else if (Gdx.app.getType() == Application.ApplicationType.Android) {
			accAngle = accAng();
			Boat.handSetAngle = accAngle;
		}
		
		if(Boat.handSetAngle > 360)
			Boat.handSetAngle -= 360;
		if(Boat.handSetAngle < -360)
			Boat.handSetAngle += 360;
//		pointA = testA;
//		pointB = testB;
		
		accAngle = (float)Math.ceil(accAngle);
		
		float acceptedAngleDif = 45f;
		float planeAngle = ((float) Math.atan((pointB.y - pointA.y)
				/ (pointB.x - pointA.x)) * 180 / (float) Math.PI);
		
		if ((pointB.x - pointA.x) > 0 && (pointB.y - pointA.y) < 0) {
			planeAngle = planeAngle + 360f;
		}
		else if ((pointB.x - pointA.x) < 0) {
			planeAngle = planeAngle + 180f;
		}
		
//		planeAngle = Helper.getAngle(pointA, pointB);
//		Helper.println("AccANgle: " + accAngle);
		
		float AngDif = Math.abs(accAngle - planeAngle);
		float SpeedFac = (float) (Math.abs(Math.cos(AngDif * Math.PI / 180)));

		if (AngDif > acceptedAngleDif && AngDif < (360 - acceptedAngleDif)) {
			SpeedFac = 0f;
		}
		
//		if(AngDif > 45 || AngDif <-45)
//			SpeedFac = 0f;
//		
//		Helper.println("A.x" + pointA.x + " A.y:" + pointA.y + " B.x:"
//				+ pointB.x + " B.y:" + pointB.y);
		
//		if(SpeedFac != 0)
//			Helper.println("accAngle:" + accAngle + " planeAngle:" + planeAngle
//				+ " AngleDif:" + AngDif + " SpeedFac:" + SpeedFac);

		return SpeedFac;
	}

	float accY = 1f;
	float accAngle;

	
	
	private float getSpeed(float elapsedTime, ElementBase element) {
		// return elapsedTime * 0.169f;
		speed = 0;
		if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				speed = -2;
				Boat.handSetAngle -= 2;
			} else if (Gdx.input.isKeyPressed(Keys.RIGHT)){
				speed = 2;
				Boat.handSetAngle += 2;
			}
			else
				return 0;
		} 

		if (speed > 0)
			((ICPathTraversal)element).setPathTraversalDirection(Path.METHOD_RIGHT);
		else
			((ICPathTraversal)element).setPathTraversalDirection(Path.METHOD_LEFT);
		
		return Math.abs(speed);
	}

	
	
	
	@Override
	public void takeAction() {
		if (elements.size == 0)
			return;

		for (InteractionCallbacks element : elements) {
			if (element == null)
				continue;

			info = ((ICPathTraversal) element).getTraverseInfo();
			if (!validateInfo(info))
				return;
			
			if(element instanceof Boat)
			{
				if(!((Boat)element).isActive())
					continue;
			}

			
				if(((ICPathTraversal)element).getPathTraversalDirection() == Path.METHOD_RIGHT)
				{
					if(info.getPathNode().getNextNode() == null){
						info.setLastTraversedDistance(0);
						((ICPathTraversal)element).actPathTraversal();
//						Helper.println("next null");
						continue;
					}
					else
						speed  = TOPSPEED * speedFactor(info.getPathNode().getLocation()
								, info.getPathNode().getNextNode().getLocation());
//					Helper.println("return next");
				}
				else if(((ICPathTraversal)element).getPathTraversalDirection() == Path.METHOD_LEFT)
				{
					if(info.getPathNode().getPrevNode() == null){
						info.setLastTraversedDistance(0);
						((ICPathTraversal)element).actPathTraversal();
//						Helper.println("prev null");
						continue;
					}
					else
						speed  = TOPSPEED * speedFactor(info.getPathNode().getLocation()
								, info.getPathNode().getPrevNode().getLocation());
//					Helper.println("retrun prev");
				}
				else
					continue;
//				Helper.println("SPeed:" + speed);
				if(speed == 0)
				{
					info.setLastTraversedDistance(0);
					((ICPathTraversal)element).actPathTraversal();
					continue;
				}
				
				
				
			if (element instanceof Boat) {
				if (((Boat) element).getPlaneType() == ((BPath) path)
						.getColor()
						|| ((BPath) path).getColor() == Boat.PLANE_NORMAL)
					path.traverse(info, speed, ((ICPathTraversal)element).getPathTraversalDirection(), true);
			} 
			else
				path.traverse(info, speed, ((ICPathTraversal)element).getPathTraversalDirection(), false);

//			if(speed > 0 && info.getLastTraversedDistance() == 0)
//			{
//				if(((Boat)element).c++ > 5)
//					((Boat)element).die();
//					Helper.println("Die Path traversal");
//			}
//			else
//				((Boat)element).c = 0;
//			
			((ICPathTraversal)element).actPathTraversal();
//			Helper.println("Speed: " + speed);
//			Helper.println("Last traversed distance: "
//					+ info.getLastTraversedDistance());
//			Helper.println("Total traversed distance: "
//					+ info.getTotalDistanceInPath());

			// take action to the element
			if (info.getPathNode() != null) {
				((ElementBase) element).setLocation(info.getTraverseLocation());
				((ElementBase) element).setRotateAngle(info.getPathNode()
						.getRightAngle());
				if (isShallRotate()) {
					if (path.getNodes().getLast() == info.getPathNode()
							&& method == Path.METHOD_RIGHT)
						info.setPathNode(null);
					else if (path.getNodes().getFirst() == info.getPathNode()
							&& info.getDistance() == 0
							&& method == Path.METHOD_LEFT)
						info.setPathNode(null);
				}
			}
		}
	}

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public void setShallRotate(boolean shallRotate) {
		this.shallRotate = shallRotate;
	}

	public boolean isShallRotate() {
		return shallRotate;
	}

}
