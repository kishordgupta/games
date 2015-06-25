package com.rhymes.game.entity.animations;

import com.badlogic.gdx.utils.Array;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.burntherope.Boat;
import com.rhymes.game.entity.elements.path.PathNode;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class AnimationSimulateFire extends AnimationBase{
	private static final long NEXT_NODE_TIME = 400;
	private PathNode currentNode = null;
	private long elapsedTime = 0; 
	Boat testPlane = null;
/**
 * Simulates fire tranversing
 * 
 */
	public AnimationSimulateFire(ElementBase element) {
		super(element);
	}

	public AnimationSimulateFire(ElementBase element, PathNode currentNode) {
		super(element);
		this.currentNode = currentNode;
	}

	@Override
	public void init() {
		this.element.setImage(getImageFromAssets(AssetConstants.IMG_BKG_RED));
		Array<ElementBase> ret = GlobalVars.ge.getCurrentStage().getElemensByType(Boat.class);
		if(ret.size > 0)
			testPlane = (Boat) ret.get(0);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void step(long stepTime) {		
		if(currentNode == null)
			return;
		else{
//			Helper.println("Steptime: " + stepTime);
//			Helper.println("Elapsed Time: " + elapsedTime);
			elapsedTime +=stepTime;
			if(elapsedTime > NEXT_NODE_TIME){
				Helper.println("Activated " + currentNode.toString());
				currentNode.setActive(true);
				elapsedTime = 0;
				currentNode = currentNode.getNextNode();

				//Take the test plane to the new node location
				if(testPlane != null && currentNode != null)
					testPlane.setLocation(new Point(currentNode.getX(), currentNode.getY()));
			}
		}
		
	}

	public void setCurrentNode(PathNode currentNode) {
		this.currentNode = currentNode;
	}

	public PathNode getCurrentNode() {
		return currentNode;
	}

}
