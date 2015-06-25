package com.rhymes.game.entity.elements.path.traversal;


import com.badlogic.gdx.Gdx;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class PathNode{
	private float x;
	private float y;
	private PathNode nextNode;
	private PathNode prevNode;
	private boolean active = true;
	
	private float leftDistance = 0;
	private float rightDistance = 0;
	private float leftAngle = 0;
	private float rightAngle = 0;
	
	private Point location;
	
	public void setLocation(Point location) {
		this.location = location;
	}
	public PathNode()
	{
		this.setX(0);
		this.setY(0);
		this.location = null;
		this.setNextNode(null);
		this.setPrevNode(null);
	}

	public PathNode(float x, float y, PathNode prevNode, PathNode nextNode)
	{
		this.setX(x);
		this.setY(y);
		this.location = new Point(x, y);
		this.setNextNode(nextNode);
		this.setPrevNode(prevNode);
	}
	
	public PathNode(Point location, PathNode prevNode, PathNode nextNode)
	{
		this.setX(location.x);
		this.setY(location.y);
		this.location = new Point(x, y);
		this.setNextNode(nextNode);
		this.setPrevNode(prevNode);
	}
	
	public Point getLocation()
	{
		return location;
	}
	public void setX(float x) {
		this.x = x;
	}

	public float getX() {
		return x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getY() {
		return y;
	}

	public void setNextNode(PathNode nextNode) {
		this.nextNode = nextNode;
		adjustLinks();
	}

	public PathNode getNextNode() {
		return nextNode;
	}

	public void setPrevNode(PathNode prevNode) {
		this.prevNode = prevNode;
		adjustLinks();
	}

	public PathNode getPrevNode() {
		return prevNode;
	}
	
	@Override
	public String toString()
	{
		String s;
		s = "PathNode: (X,Y) ==>  ( " + getX() + ", " + getY() + " )";
//		Helper.println(s);
		return s;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}
	
	public void adjustLinks()
	{
		if(this.prevNode != null){
			setLeftDistance(Point.distancePoint2Point(prevNode.getLocation(), this.getLocation()));
			setLeftAngle(Helper.getAngle(prevNode.getLocation(), getLocation())-180);
		}
		else{
			setLeftDistance(0);
			setLeftAngle(0);
		}

		if(this.nextNode != null){
			setRightDistance(Point.distancePoint2Point(nextNode.getLocation(), this.getLocation()));
			setRightAngle(Helper.getAngle(getLocation(), nextNode.getLocation()));
		}
		else{
			setRightDistance(0);
			setRightAngle(0);
		}
	}

	public void setLeftDistance(float leftDistance) {
		this.leftDistance = leftDistance;
	}

	public float getLeftDistance() {
		return leftDistance;
	}

	public void setRightDistance(float rightDistance) {
		this.rightDistance = rightDistance;
	}

	public float getRightDistance() {
		return rightDistance;
	}

	
	public float getDistance(int method)
	{
		if(method == Path.METHOD_RIGHT)
			return this.getLocation().distancePoint2Point(nextNode.getLocation());
//			return getRightDistance();
		else 
			return getLeftDistance();
	}

	public PathNode getNextTraversingNode(int method)
	{
		if(method == Path.METHOD_RIGHT)
			return getNextNode();
		else 
			return getPrevNode();
	}
	
	
	PathNode nextTraversingNode;
	Point t = new Point();
	public Point getPointAtDistance(PathNode node, float distance, int method)
	{		
		nextTraversingNode = getNextTraversingNode(method);
//		Helper.printKeyVal("getPointAtDistance(): Next Traversing node:", nextTraversingNode.toString());
		if(nextTraversingNode == null)
			return node.getLocation();		
		Point p = node.getLocation();
		Point n = nextTraversingNode.getLocation();
		
		double d = Point.distancePoint2Point(p, n);
		double k = d - distance;
		double j = distance;
		
		
//		t = new Point();
		t.x = (float) ((j * n.x + k * p.x) / (j+k));
		t.y = (float) ((j * n.y + k * p.y) / (j+k));
		
//		Helper.printKeyVal("\n\nP2P passed distance: ", distance);
//		Helper.printKeyVal("P2P distance: ", t.distancePoint2Point(node.getLocation()));
//		Helper.printKeyVal("P2P difference: ", distance - t.distancePoint2Point(node.getLocation()));
//		Helper.printKeyVal("getPointAtDistance(): return point:", t.toString());
		
		return t;
	}
	
	

	public Point getPointAtDistance(PathNode node, PathNode nextNode,float distance, int method)
	{		
		nextTraversingNode = nextNode;
//		nextTraversingNode = getNextTraversingNode(method);
//		Helper.printKeyVal("getPointAtDistance(): Next Traversing node:", nextTraversingNode.toString());
		if(nextTraversingNode == null)
			return node.getLocation();		
		Point p = node.getLocation();
		Point n = nextTraversingNode.getLocation();
		
		double d = Point.distancePoint2Point(p, n);
		double k = d - distance;
		double j = distance;
		
		Point t;
		t = new Point();
		t.x = (float) ((j * n.x + k * p.x) / (j+k));
		t.y = (float) ((j * n.y + k * p.y) / (j+k));
		
//		Helper.printKeyVal("\n\nP2P passed distance: ", distance);
//		Helper.printKeyVal("P2P distance: ", t.distancePoint2Point(node.getLocation()));
//		Helper.printKeyVal("P2P difference: ", distance - t.distancePoint2Point(node.getLocation()));
//		Helper.printKeyVal("getPointAtDistance(): return point:", t.toString());
		
		return t;
	}
	
	
	public Point getPointAtDistance(float distance, int method)	
	{
		return getPointAtDistance(this, distance, method);
	}
	
	public Point getPointAtDistance(float distance)	
	{
		return getPointAtDistance(this, distance, Path.METHOD_RIGHT);
	}

	public void setLeftAngle(float leftAngle) {
		this.leftAngle = leftAngle;
	}

	public float getLeftAngle() {
		return leftAngle;
	}

	public void setRightAngle(float rightAngle) {
		this.rightAngle = rightAngle;
	}

	public float getRightAngle() {
		return rightAngle;
	}
}
