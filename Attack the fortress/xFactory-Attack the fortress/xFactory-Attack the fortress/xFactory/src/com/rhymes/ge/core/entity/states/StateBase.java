package com.rhymes.ge.core.entity.states;

import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;

public abstract class StateBase {
	protected AnimationBase animation;
	protected ElementBase element;
	public StateBase(ElementBase element)
	{
		this.element = element;
	}
	public void step(long stepTime) {
		if(animation != null)
			animation.step(stepTime);
	}

	public abstract void init();	
}
