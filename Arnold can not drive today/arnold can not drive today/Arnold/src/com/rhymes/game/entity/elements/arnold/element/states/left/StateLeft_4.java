package com.rhymes.game.entity.elements.arnold.element.states.left;

import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.entity.states.StateBase;

public class StateLeft_4 extends StateBase{

	public StateLeft_4(ElementBase element, AnimationBase animation) {
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
