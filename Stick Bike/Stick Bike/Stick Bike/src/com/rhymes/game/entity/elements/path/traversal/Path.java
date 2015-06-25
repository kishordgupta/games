package com.rhymes.game.entity.elements.path.traversal;

import java.util.LinkedList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class Path {
	protected LinkedList<PathNode> nodes;
	protected LinkedList<PathNode> renderNodes;
//	protected LinkedList<Point> segmentPoints;
	protected PathRenderMethods pathRenderer;
	protected int renderingSegmentSize = 10;
	private float totalDistance = 0;	
	
	public static int METHOD_LEFT = 1;
	public static int METHOD_RIGHT = 2;

	private TextureRegion image = null;
	
	public float renderWidth = 5;
	
	public float getRenderWidth() {
		return renderWidth;
	}

	public void setRenderWidth(float renderWidth) {
		this.renderWidth = renderWidth;
	}

	public TextureRegion getImage() {
		return image;
	}

	public void setImage(TextureRegion image) {
		this.image = image;
	}

	public float getDefaultPathWidth() {
		return defaultPathWidth;
	}

	public void setDefaultPathWidth(float defaultPathWidth) {
		this.defaultPathWidth = defaultPathWidth;
	}

	public int getRenderingSegmentSize() {
		return renderingSegmentSize;
	}

	public void setRenderingSegmentSize(int segmentSize) {
		this.renderingSegmentSize = segmentSize;
	}

	private float defaultPathWidth = 5;
	private TextureRegion imageInActive;
	public Path() {
		nodes = new LinkedList<PathNode>();
		renderNodes = new LinkedList<PathNode>();
//		segmentPoints = new LinkedList<Point>();
		pathRenderer = new PathRenderMethods(this);
//		this.imageInActive = Helper.getImageFromAssets(AssetConstants.IMG_ROAD_WHITE);
	}
	
	public Path(int size) {
		nodes = new LinkedList<PathNode>();
		renderNodes = new LinkedList<PathNode>();
//		segmentPoints = new LinkedList<Point>();
		pathRenderer = new PathRenderMethods(this);
		generateRandomPath(size);
	}

	public void setNodes(LinkedList<PathNode> nodes) {
		this.nodes = nodes;
	}

	public LinkedList<PathNode> getNodes() {
		return nodes;
	}

	public PathNode getPathNode(Point hitPoint, float maxDistance) {
		for (int i = 0; i < nodes.size() - 1; i++) {
//			Helper.printKeyVal("node: " + nodes.get(i)+ "  to  " + nodes.get(i+1), Helper.pointToLineDistance(nodes.get(i).getLocation(), nodes
//					.get(i + 1).getLocation(), hitPoint));
			if (Helper.pointToLineDistance(nodes.get(i).getLocation(), nodes
					.get(i + 1).getLocation(), hitPoint) <= maxDistance)
				return nodes.get(i);
		}
		return null;
	}

	public void printDistance(Point hitPoint) {
		for (int i = 0; i < nodes.size() - 1; i++) {
			Helper.println("Distance from: (X,Y) ==>  ( " + nodes.get(i).getX()
					+ ", " + nodes.get(i).getY() + " )");
			Helper.println("To: (X,Y) ==>  ( " + nodes.get(i + 1).getX() + ", "
					+ nodes.get(i + 1).getY() + " )");
			Helper.println(""
					+ Helper.pointToLineDistance(nodes.get(i).getLocation(),
							nodes.get(i + 1).getLocation(), hitPoint));
		}
	}
	
//	float dist = 0;
	public void renderPoints(TextureRegion ropeTexture) {
//		time = System.currentTimeMillis();
		
		if(image != null)
			ropeTexture = image;

		for (int i = 0; i < nodes.size()-1; i++) {
			if(nodes.get(i).isActive())
				pathRenderer.renderPathSegment(nodes.get(i), nodes.get(i + 1),
					 ropeTexture,
					 renderWidth);
			else
				pathRenderer.renderPathSegment(nodes.get(i), nodes.get(i + 1),
						 imageInActive,
						 renderWidth);				
		}
		
		
//		counter = 0;
//		for (int i = 0; i < segmentPoints.size() - 1; i++) {
//			if(i%2f == 0)
//				continue;
//			pathRenderer.renderPathSegment(segmentPoints.get(i), segmentPoints
//					.get(i + 1), ropeTexture, renderingSegmentSize);
			
//			if (counter == 0)
//				counter = 1;
//			else
//				counter = 0;
//			pathRenderer.renderPathSegment(segmentPoints.get(i), segmentPoints
//					.get(i + 1), (counter == 0) ? ropeTexture : Helper
//					.getImageFromAssets(AssetConstants.IMG_BKG_PURPLE), 5);
//		}

//		if (!pathRenderer.onceDrawn) {
//			pathRenderer.onceDrawn = true;
////			Helper.println("\n\n");
////			Helper.printKeyVal("Total Distance: ", "" + calculateTotalDistance());
//		}
//		GlobalVars.ge.getRenderer().getBatch().setColor(1f, 1f, 1f, 1f);
//		System.out.println("" + (System.currentTimeMillis() - time));
	}

	public void renderSimulation(TextureRegion ropeNormalImage,
			TextureRegion ropeBurnedImage) {
		for (int i = 0; i < nodes.size() - 1; i++) {
			if (nodes.get(i).isActive())
				pathRenderer.renderPathSegment(nodes.get(i), nodes.get(i + 1),
						ropeBurnedImage);
			else
				pathRenderer.renderPathSegment(nodes.get(i), nodes.get(i + 1),
						ropeNormalImage);
		}
		if (!pathRenderer.onceDrawn) {
			pathRenderer.onceDrawn = true;
//			Helper.println("\n\n");
		}
	}
	
	/*
	public void addRenderNodes(PathNode p)
	{
		PathNode t;
		PathNode prev;
		if(renderNodes.size() > 0)
		{
			t = renderNodes.getLast();
			prev = t;
			float distance = t.getLocation().distancePoint2Point(p.getLocation());
//			Helper.printKeyVal("Distance: ", distance);
			
			for(float i = renderingSegmentSize; i < distance; i += renderingSegmentSize)
			{
//				Helper.printKeyVal("i: ", i);
				renderNodes.add(new PathNode(t.getPointAtDistance(t, p, i, Path.METHOD_RIGHT), renderNodes.getLast(), null));
//				Helper.printKeyVal("Render node location:", renderNodes.getLast().getLocation().toString());
				prev.setNextNode(renderNodes.getLast());
				prev = renderNodes.getLast();
			}
		}
		else
			renderNodes.add(new PathNode(p.getLocation(), null, null));
	}
*/
	
	public void addNewNode(PathNode p, LinkedList<PathNode> nodes, float maxLinkLength)
	{
		PathNode t;
		PathNode prev;
		if(nodes.size() > 0)
		{
			t = nodes.getLast();
			prev = t;
			float distance = t.getLocation().distancePoint2Point(p.getLocation());
//			Helper.printKeyVal("Distance: ", distance);
			
			for(float i = maxLinkLength; ; i += maxLinkLength)
			{
				if(i < distance){
	//				Helper.printKeyVal("i: ", i);
					nodes.add(new PathNode(t.getPointAtDistance(t, p, i, Path.METHOD_RIGHT), nodes.getLast(), null));
	//				Helper.printKeyVal("Render node location:", renderNodes.getLast().getLocation().toString());
					prev.setNextNode(nodes.getLast());
					prev = nodes.getLast();
				}
				else if(i == distance){
					nodes.add(new PathNode(t.getPointAtDistance(t, p, i, Path.METHOD_RIGHT), nodes.getLast(), null));
	//				Helper.printKeyVal("Render node location:", renderNodes.getLast().getLocation().toString());
					prev.setNextNode(nodes.getLast());
					prev = nodes.getLast();
					break;
				}
				else if(i > distance){
					nodes.add(new PathNode(p.getLocation(), nodes.getLast(), null));
					prev.setNextNode(nodes.getLast());
					prev = nodes.getLast();
					break;
				}
			}
		}
		else
			nodes.add(new PathNode(p.getLocation(), null, null));
	}
	
	
	public void addRenderNodes(PathNode p)
	{
		addNewNode(p, renderNodes, renderingSegmentSize);
	}

	private void addTraversePoints(Point p, Point n,
			LinkedList<Point> pointsList) {
		float d = Point.distancePoint2Point(p, n);
		int k = (int) (d / renderingSegmentSize);
		int m = 0;
		Point t;
		while (k > 0) {
			t = new Point();
			t.x = (m * n.x + k * p.x) / (m + k);
			t.y = (m * n.y + k * p.y) / (m + k);
			pointsList.add(t);
			k--;
			m++;
		}
		return;
	}
	public void addLast(Point point) {
		PathNode t = new PathNode(point.x, point.y, null, null);
		PathNode last;
		if(nodes.size() == 0)
			last = null;
		else
			last = nodes.getLast();
		if(last != null)
		{			
			last.setNextNode(t);
			t.setPrevNode(last);
			t.setNextNode(null);	
			nodes.add(t);
//			addTraversePoints(nodes.get(nodes.size()-2).getLocation(), t.getLocation(),
//					this.segmentPoints);
			addRenderNodes(t);
		}
		else{
			t.setNextNode(null);
			t.setPrevNode(null);
			nodes.add(t);
			addRenderNodes(t);
		}
		
		calculateTotalDistance();
	}
	public void generateRandomPath(int size) {
		PathNode temp = null;
		Random rand = new Random();
		PathNode prevNode = null;
		
		for (int i = 0; i < size; i++) {
			if (temp == null) {
//				temp = new PathNode(rand.nextInt(100), rand.nextInt(100), null,
//						null);
				temp = new PathNode(rand.nextInt(Gdx.graphics.getWidth())
						+ renderingSegmentSize * 2, rand.nextInt(Gdx.graphics.getHeight() + renderingSegmentSize * 2),
						prevNode, null);
//				segmentPoints.add(temp.getLocation());
				addRenderNodes(temp);
			} else {
/*				temp = new PathNode(temp.getX() + rand.nextInt(20)
						+ renderingSegmentSize * 2, temp.getY()
						+ +renderingSegmentSize * 2 + rand.nextInt(80),
						prevNode, null);*/
				
				temp = new PathNode(rand.nextInt(Gdx.graphics.getWidth())
						+ renderingSegmentSize * 2, rand.nextInt(Gdx.graphics.getHeight() + renderingSegmentSize * 2),
						prevNode, null);
				
//				temp.setActive(rand.nextInt(100)> 30 ? true : false);
				
				prevNode.setNextNode(temp);
				temp.setPrevNode(prevNode);

//				addTraversePoints(prevNode.getLocation(), temp.getLocation(),
//						this.segmentPoints);
				addRenderNodes(temp);

			}
			prevNode = temp;
			nodes.add(temp);
		}
		nodes = renderNodes;
		calculateTotalDistance();
//		Helper.println("Generated rope: ");
//		for (PathNode r : nodes)
//			Helper.println("Rope node: (X,Y) ==>  ( " + r.getX() + ", "
//					+ r.getY() + " )");
	}

	public void startRendering()
	{
		nodes = renderNodes;
		calculateTotalDistance();
	}
	
	public void updateRenderingInfo(TraversePointInfo info, int method){
		
	}
	
	public Point getTraversePoint(float distance, int method) {		
		try {
			float totalDistance = 0;
			if (nodes.size() == 0){
				System.out.println("" + (System.currentTimeMillis() - time));
				return null;
			}
			
			PathNode t = this.nodes.get(0);
			Point ret = null;
			while (true) {
				if ((totalDistance + t.getRightDistance()) > distance) {
					ret = t.getPointAtDistance(distance - totalDistance);
					break;
				} else
					totalDistance += t.getRightDistance();
				t = t.getNextNode();
				if(t == null)
					return null;
			}		
			return ret;
		} catch (Exception e) {
				e.printStackTrace();
				return null;
		}
	}

	public float calculateTotalDistance()
	{
		if(nodes.size() == 0)
			return 0;
		float totalDistance = 0;
		for(PathNode p: nodes)
		{
			totalDistance += p.getRightDistance();
		}		
		this.totalDistance = totalDistance;
		return totalDistance;
	}
	
	public float getTotalDistance(boolean shallRecalculated)
	{
		return this.totalDistance;
	}
	
	private boolean insideCheck(Point p, Point n, Point hitPoint)
	{
		if(p.x <= hitPoint.x && n.x >= hitPoint.x )
			return true;
		return false;
	}

	
	public TraversePointInfo startTraverse(Point hitPoint, float maxDistance) {
		float distance = 9999, smallestDistance = 9999;
		nodes = renderNodes;
		PathNode t = null;
		for (int i = 0; i < nodes.size(); i++) {
			distance = nodes.get(i).getLocation().distancePoint2Point(hitPoint);
//			Helper.printKeyVal("PathNode: ", nodes.get(i).getLocation().toString());
//			Helper.printKeyVal("Distance", distance);
			if(distance < smallestDistance)
			{
//				Helper.printKeyVal("New smallest point: ", nodes.get(i).getLocation().toString());
//				Helper.printKeyVal("New smallest Distance", distance);
				smallestDistance = distance;
				t = nodes.get(i);
			}
		}
		
		if(smallestDistance > maxDistance)
			return null;
		
		TraversePointInfo info = new TraversePointInfo();
		info.setPathNode(t);			
		info.setTraverseLocation(t.getLocation());				
		info.setDistance(0);
						
		distance = 0;
		for (int i = 0; i < nodes.size(); i++) {
			if(nodes.get(i).equals(t))
				break;
			distance+= nodes.get(i).getRightDistance();
		}
		info.setTotalDistanceInPath(distance);
//		Helper.printKeyVal("Total Distance Set: ", info.getTotalDistanceInPath());
		info.setPath(this);
		
		return info;
	}

	
	
	/**
	 * Starts traversing with given hit point and maxDistance from the hit.
	 * It returns closest point on the closest segment.
	 * @param hitPoint Any point on the screen. 
	 * @param maxDistance Maximum distance from the hit-point to the landing point on the comparing path segment.
	 * Needed for detection.
	 * @return
	 * Using the given hit-point, returns TraversePointInfo for the landing point on the Path 
	 */
	public TraversePointInfo startTraverse0(Point hitPoint, float maxDistance) {
		Helper.println("\n\n\nStarting: ");
		float distance, d;
		for (int i = 0; i < nodes.size() - 1; i++) {
//			if(!insideCheck(nodes.get(i).getLocation(), nodes.get(i+1).getLocation(), hitPoint))
//				continue;
			distance =  (float) Helper.pointToLineDistance(nodes.get(i).getLocation(), nodes
					.get(i + 1).getLocation(), hitPoint);
//			Helper.printKeyVal("node: " + nodes.get(i)+ "  to  " + nodes.get(i+1), distance);
			
			if (distance <= maxDistance)
			{
//				Helper.printKeyVal("Node selected: ", nodes.get(i).getLocation().toString());
				
				TraversePointInfo info = new TraversePointInfo();
				info.setPathNode(nodes.get(i));
				
				d = (float) Math.pow(info.getPathNode().getLocation().distancePoint2Point(hitPoint), 2);
				distance = distance * distance;
//				Helper.printKeyVal("Distance measured: ", Math.sqrt(d-distance));
				distance = (float) Math.sqrt(d-distance);
				
				info.setTraverseLocation(info.getPathNode().getPointAtDistance(distance));				
//				Helper.printKeyVal("Point Set: ", info.getTraverseLocation().toString());
				
				info.setDistance(distance);
//				Helper.printKeyVal("Distance Set: ", info.getDistance());
								
				for(int j = 0; j <= i; j++)
				{
					distance+= nodes.get(j).getLeftDistance();
				}
				
				info.setTotalDistanceInPath(distance);
//				Helper.printKeyVal("Total Distance Set: ", info.getTotalDistanceInPath());
				info.setPath(this);
				
				return info;
			}
		}
		return null;
	}

	
	/**Starts traversing with given distance and method from the starting point of the path
	 * @param distance Distance along the path from the start point.
	 * @param method Method of traversal. Use method constants. 
	 * @return returns TraversePointInfo for the landing point on the Path
	 */
		public TraversePointInfo startTraverse(float distance, int method) {		
			try {
				float totalDistance = 0;
				totalDistance = 0;
				if(nodes == null)
					return null;
				if (nodes.size() == 0)
					return null;
				
				PathNode t = this.nodes.get(0);
				Point ret = t.getLocation();
				while (true) {
					if(t.getNextNode() == null){
						TraversePointInfo info = new TraversePointInfo();			
						info.initialize(t.getLocation(), t, this.totalDistance, this.totalDistance, this,this.totalDistance); 
						return info;
					}
					if ((totalDistance + t.getDistance(method)) > distance) {
						ret = t.getPointAtDistance(distance - totalDistance, method);
						break;
					} else
						totalDistance += t.getDistance(method);
					t = t.getNextNode();
				}		
				
				TraversePointInfo info = new TraversePointInfo();			
				info.initialize(ret, t, distance-totalDistance, distance, this, distance); 
				return info;
			} catch (Exception e) {
					e.printStackTrace();
					return null;
			}
		}
	
	/**Starts traversing from the starting point of the path
	 * @return returns TraversePointInfo for the landing point on the Path
	 */
	public TraversePointInfo startTraverse()
	{
		TraversePointInfo info = new TraversePointInfo();
		
		if(nodes == null)
			return null;
		if(nodes.size() == 0)
			return null;
		
		
		info.setPathNode(nodes.get(0));
		info.setTraverseLocation(nodes.get(0).getLocation());
		info.setDistance(0);
		info.setTotalDistanceInPath(0);
		info.setPath(this);
		return info;
	}
	
	

	public void traverseLeft(TraversePointInfo info, float distanceFromCurPoint, int method, boolean affectRender) {
		PathNode t;
		
		float dtv;
		float dis;
		
		/****************************************************
		 * 
		 * 			  <-----dtv----->
		 *      ********************* <-- t
		 * 
		 * **************************************************/
		
		t = info.getPathNode();
		if(t == null){
			t = info.getPath().nodes.getLast();
			info.initialize(t.getLocation(), t, t.getLeftDistance(), totalDistance, this, 0);
			if(affectRender)
				t.setActive(false);
//			Helper.println("Return from: " + t.getLocation().toString());
			return;
		}

//		Helper.printKeyVal("Step 1: DFCP", distanceFromCurPoint);
//		Helper.printKeyVal("Step 1: current distance", info.getDistance());
		
		if(t.getNextNode() == null)
			dtv = distanceFromCurPoint;
		else{ 
			if(info.getDistance() > 0){
				dtv = t.getRightDistance() - info.getDistance() + distanceFromCurPoint;
//				if(affectRender)
//					t.setActive(false);
				t = t.getNextNode();				
			}
			else{
				dtv = distanceFromCurPoint;
			}
		}
		
//		Helper.printKeyVal("Step 1: DTV", dtv);
//		Helper.printKeyVal("Step 1: t.getLeftDistance()", t.getLeftDistance());
		while(true)
		{
			if(dtv > t.getLeftDistance())
			{
//				Helper.printKeyVal("\nStep 2: DTV", dtv);
//				Helper.printKeyVal("Step 2: t.getLeftDistance(): ", t.getLeftDistance());
				if(t.getPrevNode() == null)
				{
					if(affectRender)
						t.setActive(false);
					info.initialize(t.getLocation(), t, 0, info.getTotalDistanceInPath()-(distanceFromCurPoint-dtv), this, distanceFromCurPoint-dtv);
//					Helper.printKeyVal("Retrun: 1: ", info.getDistance());
					return;
				}
				
				dtv = dtv - t.getLeftDistance();
				if(affectRender)
					t.setActive(false);
				t = t.getPrevNode();
				
				if(!t.isActive()){
					info.setLastTraversedDistance(0);
					return;
				}
//				Helper.printKeyVal("Step 2: New DTV", dtv);
//				Helper.printKeyVal("Step 2: Switched to new Node: ", t.getLocation().toString());
//				Helper.printKeyVal("Step 2: New Node index: ", info.getPath().nodes.indexOf(t));
			}
			else
				break;
		}
		/****************************************************/
		
		Point p = t.getPointAtDistance(dtv, Path.METHOD_LEFT);		
		info.initialize(p, t.getPrevNode(), t.getLeftDistance() - dtv, info.getTotalDistanceInPath() - distanceFromCurPoint, this, distanceFromCurPoint);
//		t.setActive(false);
		
		
//		Helper.printKeyVal("Step 2: New Node index: ", info.getPath().nodes.indexOf(t));
//		Helper.printKeyVal("Step 2: New Node index: ", info.getPath().nodes.indexOf(t.getPrevNode()));		
		
//		Helper.printKeyVal("Retruning point: ", p.toString());
//		Helper.printKeyVal("Retrun: 2", info.getDistance());
	}
	
	
	public void traverseRight(TraversePointInfo info, float distanceFromCurPoint, int method, boolean affectRender) {
		
		//if info.pathnode is null, then according to mehtod get one of the end path nodes		
		if(info.getPathNode() == null){
			PathNode pt = nodes.getFirst();
			info.initialize(pt.getLocation(), pt, 0, 0, this, 0);
			
			if(affectRender)
				pt.setActive(false);
//			Helper.printKeyVal("info pathnode in null check: ", info.getPathNode().toString());
			return;
		}
		
		//info.pathnode is not null
		
//		Helper.printKeyVal("\n\nCurrent distance: ", info.getDistance());
//		Helper.println("distance from current point: " + distanceFromCurPoint);
		try {
			float traversedDistance = 0;
			float distanceToTraverse = distanceFromCurPoint + info.getDistance();
			float distance = info.getDistance();
			
			PathNode t = info.getPathNode();

			while (true) {
				nextNode = t.getNextTraversingNode(method);
				
				// current is the last node
				if(nextNode == null){
					if(affectRender)
						t.setActive(false);
					info.initialize(t.getLocation(), t, this.totalDistance, this.totalDistance, this, traversedDistance);
//					Helper.println("2 Right Return last");
					return;					
				}
				
				if(!nextNode.isActive()){
					info.setLastTraversedDistance(0);
					return;
				}
				
//				Helper.printKeyVal("2 t.linkDistance: ", t.getDistance(method));
//				Helper.printKeyVal("2 out of distanceToTraverse: ", distanceToTraverse);

				linkDistance = t.getDistance(method);				
				if (linkDistance >= distanceToTraverse) {
					traversedDistance += distanceToTraverse;
					
//					Helper.printKeyVal("\n3 traveresed distance: ", "" + traversedDistance);
//					Helper.printKeyVal("3 out of : ", distanceToTraverse);

					Point retP = t.getPointAtDistance(distanceToTraverse, method) ;
					if(affectRender)
						t.setActive(false);
//					Helper.printKeyVal("getPointAtDistance() returned point: ", retP.toString());
					
//					Helper.printKeyVal("Right traverse Last distance: ", traversedDistance + distanceToTraverse-info.getDistance());
					info.initialize(retP, t, distanceToTraverse, 
								info.getTotalDistanceInPath() + distanceFromCurPoint, this, distanceFromCurPoint);
//					Helper.println("2 Return good");					
					return;
				} 
				traversedDistance += linkDistance;
				
				distanceToTraverse = distanceToTraverse - linkDistance;
				distance = 0;

				t = nextNode;
//				Helper.printKeyVal("Switching to new node: ", t.toString());
			}		
			
		} catch (Exception e) {
				e.printStackTrace();
		}

	}
	

	long time = 0;
	float linkDistance;
	PathNode nextNode;
	public String pathType;
	/**
	 * This will traverse from current point to another given "distance" amount
	 * using the given method.
	 * @param info Current traversal info. 
	 * @param distanceToTraverse How much to traverse on the path from current point
	 * @param method Method of traversal. Use method constants.
	 * @param affectRender Shall traversing this mark the points as traversed?
	 * If yes then all the pathnodes being traversed would not be drawn next time.
	 * If no, then everying stays the same for rendering.
	 */
	public void traverse(TraversePointInfo info, float distanceFromCurPoint, int method, boolean affectRender) {
		if(distanceFromCurPoint == 0)
			info.setLastTraversedDistance(0);
		if(info == null || nodes.size() == 0){
			return;
		}
		if(method == METHOD_LEFT){
			traverseLeft(info, distanceFromCurPoint, method, affectRender);
		}
		else if(method == METHOD_RIGHT){
			traverseRight(info, distanceFromCurPoint, method, affectRender);
		}
//		updateRenderingInfo(distanceFromCurPoint);
//		if(nodes.getLast() == info.getPathNode() && )
//			info.initialize(nodes.getLast().getLocation(), nodes.getLast(), 0, 0, this, 0);
		}
	
	/**
	 * This will traverse from current point to another given "distance" amount
	 * using the given method.
	 * @param info Current traversal info. 
	 * @param distanceToTraverse How much to traverse on the path from current point
	 * @param method Method of traversal. Use method constants.
	 * This will not affect rendering. Meaning would not mark any pathnode as traversed.
	 * To do that use the other method.
	 */
	public void traverse(TraversePointInfo info, float distanceFromCurPoint, int method) {
		traverse(info, distanceFromCurPoint, method, false);		
	}

	public void reset() {
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).setActive(true);
		}
	}	
}