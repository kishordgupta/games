package com.rhymes.game.entity.elements.path.traversal;

import com.rhymes.game.entity.elements.path.Path;
import com.rhymes.game.entity.elements.path.PathNode;
import com.rhymes.ge.core.renderer.Point;

public class TraversePointInfo {
	
	private Path path;
	private PathNode pathNode;
	private Point traverseLocation;
	private float distance;
	private float totalDistanceInPath;
	private float lastTraversedDistance;
	
	public Point getTraverseLocation() {
		return traverseLocation;
	}
	public void setTraverseLocation(Point point) {
		this.traverseLocation = point;
	}
	public PathNode getPathNode() {
		return pathNode;
	}
	public void setPathNode(PathNode pathNode) {
		this.pathNode = pathNode;
	}
	
	public TraversePointInfo()
	{
		this.traverseLocation = null;
		this.pathNode = null;
		distance = totalDistanceInPath = 0;
	}
	public TraversePointInfo(Point point, PathNode pathNode, float distance, float totalDistanceInPath, Path path) {
		this.path = path;
		this.traverseLocation = point;
		this.pathNode = pathNode;
		this.distance = distance;
		this.totalDistanceInPath = totalDistanceInPath;
		this.lastTraversedDistance = 0;
	}
	public void initialize(Point point, PathNode pathNode, float distance, float totalDistanceInPath, Path path, float lastTraversedDistance) {
		this.path = path;
		this.traverseLocation = point;
		this.pathNode = pathNode;
		this.distance = distance;
		this.totalDistanceInPath = totalDistanceInPath;
		this.lastTraversedDistance = lastTraversedDistance;
	}
	
	public void initialize(TraversePointInfo info) {
		if(info == null)
			return;
		this.path = info.getPath();
		this.traverseLocation = info.getTraverseLocation();
		this.pathNode = info.getPathNode();
		this.distance = info.getDistance();
		this.totalDistanceInPath = info.getTotalDistanceInPath();
		this.lastTraversedDistance = info.lastTraversedDistance;
	}
	
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		if(distance < 0)
			this.distance = -distance;
		else
			this.distance = distance;
	}
	public float getTotalDistanceInPath() {
		return totalDistanceInPath;
	}
	public void setTotalDistanceInPath(float totalDistanceInPath) {
		this.totalDistanceInPath = Math.abs(totalDistanceInPath);
	}
	public void setPath(Path path) {
		this.path = path;
	}
	public Path getPath() {
		return path;
	}
	public void setLastTraversedDistance(float lastTraversedDistance) {
		this.lastTraversedDistance = lastTraversedDistance;
	}
	public float getLastTraversedDistance() {
		return lastTraversedDistance;
	}
	
}
