package com.rhymes.game.entity.animations;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.path.PathNode;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;

public class AnimationRopeSetDefault extends AnimationBase{
	private PathNode currentNode = null;
	
	
	public AnimationRopeSetDefault(ElementBase element) {
		super(element);
	}

	@Override
	public void init() {
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
//		if(currentNode == null)
//			return;
//		else
//			currentNode.setActive(false);
		
	}

	public void renderRopeSet()
	{
		
	}
	
	public void setCurrentNode(PathNode currentNode) {
		this.currentNode = currentNode;
	}

	public PathNode getCurrentNode() {
		return currentNode;
	}

}
