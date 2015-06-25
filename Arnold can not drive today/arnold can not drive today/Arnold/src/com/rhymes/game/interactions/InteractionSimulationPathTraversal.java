package com.rhymes.game.interactions;

import com.rhymes.game.entity.elements.burntherope.Boat;
import com.rhymes.game.entity.elements.path.Path;
import com.rhymes.game.entity.elements.path.PathSet;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.helpers.Helper;

public class InteractionSimulationPathTraversal extends InteractionBase {

	private ElementBase element;
	private PathSet pathSet;
	private int pathIndex;
	private long elapsedTime;
	private TraversePointInfo info = null;
	private int method = Path.METHOD_RIGHT;
	
	public InteractionSimulationPathTraversal(ElementBase element, PathSet pathSet, int pathIndex)
	{
		this.element = element;
		this.pathSet = pathSet;
		this.pathIndex = pathIndex;
		info = new TraversePointInfo();
		elapsedTime = 0;
	}
	
	@Override
	public void checkCondition(long elapsedTime) {
		this.elapsedTime = elapsedTime;
//		Helper.println("Elapsed: " + elapsedTime);
	}
	
	
	long time = 0;
	float lastDistanceTraversed;
	Path path;
	@Override
	public void takeAction() {				
		time = System.currentTimeMillis();
//		TraversePointInfo po;
		path = pathSet.getPaths().get(pathIndex);
	
//		lastDistanceTraversed+= elapsedTime * 0.5;
//		Helper.printKeyVal("speed: ", elapsedTime * 0.2 + "");
//		path.getTraversePoint(lastDistanceTraversed, Path.METHOD_RIGHT);
		
//		Helper.println("SIM: current info: " + info.getPathNode());
//		Helper.println("SIM: current info point: " + info.getPoint());
//		Helper.printKeyVal("SIM: path traverse-takeaction: elapsed time", elapsedTime);
		
		
		
		
		path.traverse(info, elapsedTime * 0.159f, method);
		
		if(info.getPathNode() != null){
//			Helper.printKeyVal("SIM: PathNode index: ", path.getNodes().indexOf(info.getPathNode()));
			element.setLocation(info.getTraverseLocation());

			if((element instanceof Boat) == true)
				element.setRotateAngle(this.method==Path.METHOD_RIGHT?info.getPathNode().getRightAngle():info.getPathNode().getLeftAngle());
			
			
			if(info.getTotalDistanceInPath()> path.getTotalDistance(false))
				info.setPathNode(null);
			}
			if(path.getNodes().getLast() == info.getPathNode() && method == Path.METHOD_RIGHT)
				info.setPathNode(null);
			else if(path.getNodes().getFirst() == info.getPathNode() && method == Path.METHOD_LEFT)
				info.setPathNode(null);
			
			
			if(info.getPathNode() == null)
				pathIndex++;
			
			if(pathIndex >= pathSet.getPaths().size )
				pathIndex = 0;
			
//			Helper.printKeyVal("SIM: New Point: ", info.getPoint().toString());
//			Helper.printKeyVal("SIM: ditance: ", info.getTotalDistanceInPath());
//			Helper.printKeyVal("SIM: Action step time: ", (System.currentTimeMillis()-time));	
	}
	

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}


}
