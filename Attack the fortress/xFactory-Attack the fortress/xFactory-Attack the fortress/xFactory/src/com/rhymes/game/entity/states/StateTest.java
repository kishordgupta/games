package com.rhymes.game.entity.states;

import com.rhymes.game.entity.animations.AnimationTest;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.entity.states.StateBase;
import com.rhymes.ge.core.entity.states.StateDefault;
import com.rhymes.helpers.Helper;

public class StateTest extends StateDefault{

	public StateTest(ElementBase element) {
		super(element);
	}
	
	@Override
	public void init() {
//		Helper.println("setting animation test");
		this.animation = new AnimationTest(element);
		animation.init();
	}


}
