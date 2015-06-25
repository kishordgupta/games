package com.rhymes.game.interactions.rangeTraversal;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.rhymes.attackTheFortress.Enemy;
import com.rhymes.game.entity.elements.path.Path;
import com.rhymes.game.entity.elements.path.PathSet;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.game.entity.elements.piku.Destroyer;
import com.rhymes.game.entity.elements.piku.Transporter;
import com.rhymes.game.interactions.ICPathTraversal;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class IRangePathTraversal extends InteractionBase {
	private long elapsedTime;
	private float speed = 0;
	private int method = Path.METHOD_LEFT;
	private Path path;
	private InfoRangeTraversal info;
	
	Point tmp;
	float dst;
	int i = 0;
	private boolean shallRotate = false;
	
	public IRangePathTraversal() {
		super();
	}

	@Override
	public void checkCondition(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	private boolean validateInfo(InfoRangeTraversal info)
	{
		if(info == null)
			return false;
		
		path = info.traverseInfo.getPath();
		if(info.traverseInfo.getPath() == null)
			return false;
		return true;
	}
	
	@Override
	public void takeAction() {
		
		if(elements.size == 0)
			return;
		
		for(InteractionCallbacks element:elements){
			if(element == null)
				continue;
			
//			Helper.printKeyVal("Eleemnt size", elements.size);
//			Helper.println("Taking action to : Id: " + ((ElementBase)element).getId() + " " + ((ElementBase)element).getClass().getName());
			
			if( !((ICRangePathTraversal)element).isActive() )
				continue;
			
			
			info  = ((ICRangePathTraversal)element).getRangeTraverseInfo();
			if(!validateInfo(info))
				continue;
//			Helper.println("\nSpeed:"+ speed);
			
			if(info.leftRange == 0 && info.rightRange == 0)
				continue;
			
			tmp = info.traverseInfo.getTraverseLocation();
//			speed = 1f ;
			if(element instanceof Enemy)
			{
				speed = ((Enemy)element).getSpeed();
			}

			float ddst = 0;
			i = 0;
	
			path.traverse(info.traverseInfo, speed, info.method);
			
			if(info.method == Path.METHOD_RIGHT)
				info.currentDistance = info.currentDistance + speed;
			else
				info.currentDistance = info.currentDistance - speed;
			
			
//			Helper.printKeyVal("\nNew currentDistance", info.currentDistance);
			
			if(info.currentDistance < info.leftRange)
				info.method = Path.METHOD_RIGHT;
			else if(info.currentDistance > info.rightRange){
//				info.method = Path.METHOD_LEFT;
				((ICRangePathTraversal)element).setActive(false) ;
				((ICRangePathTraversal)element).onPathTraverseFinished();
			}
			
//			Helper.printKeyVal("Method: ", method);

			method = info.method;
			// take action to the element
			
			if(info.traverseInfo.getPathNode() != null){
//				Helper.println("Id: " + ((ElementBase)element).getId() + " " + ((ElementBase)element).getClass().getName());
				((ElementBase)element).setLocation(info.traverseInfo.getTraverseLocation());
				
				((ElementBase)element).setRotateAngle(this.method==Path.METHOD_RIGHT
						? info.traverseInfo.getPathNode().getRightAngle()
						: info.traverseInfo.getPathNode().getLeftAngle());
			
				if(isShallRotate()){
					if(path.getNodes().getLast() == info.traverseInfo.getPathNode() && method == Path.METHOD_RIGHT)
						info.traverseInfo.setPathNode(null);
					else if(path.getNodes().getFirst() == info.traverseInfo.getPathNode() && info.traverseInfo.getDistance() == 0 && method == Path.METHOD_LEFT)
						info.traverseInfo.setPathNode(null);
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
