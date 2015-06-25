package com.rhymes.game.entity.elements.arnold.element.states.normal;

import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.entity.states.StateBase;

public class StateNormal extends StateBase{
	
	public StateNormal(ElementBase element, AnimationBase animation) {
		super(element);
		
		this.animation = animation;
		
	}
	

	@Override
	public void init() {
		if(this.animation != null){
			this.animation.init();
			
		}
		
	}
	
}
