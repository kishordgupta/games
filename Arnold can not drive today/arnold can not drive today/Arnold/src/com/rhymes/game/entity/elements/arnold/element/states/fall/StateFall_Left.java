package com.rhymes.game.entity.elements.arnold.element.states.fall;

import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.entity.states.StateBase;

public class StateFall_Left extends StateBase{

	public StateFall_Left(ElementBase element, AnimationBase animation) {
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
