package com.rhymes.game.entity.states;

import com.rhymes.game.entity.animations.AnimationSimulateFire;
import com.rhymes.game.entity.animations.XAnimation;
import com.rhymes.game.entity.elements.path.PathNode;
import com.rhymes.game.entity.elements.path.PathSet;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.entity.states.StateBase;

public class StateSimulateFire extends StateBase {
	private PathNode currentNode = null;
	public StateSimulateFire(ElementBase element) {
		super(element);
		init();
	}

	public StateSimulateFire(ElementBase element, PathNode r) {
		super(element);
		this.currentNode = r;
		init();
	}	

	@Override
	public void init() {
		this.animation = new AnimationSimulateFire(this.element, currentNode);
		animation.init();
	}
	
	public void reset(ElementBase element, PathNode r)
	{
		this.element = element;
		this.currentNode = currentNode;
	}
}
