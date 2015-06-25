package com.rhymes.game.entity.states;

import com.rhymes.game.entity.animations.XAnimation;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.entity.states.StateBase;

public class XState extends StateBase{

	public XState(ElementBase element) {
		super(element);
	}

	@Override
	public void init() {
		this.animation = new XAnimation(this.element);
		animation.init();
	}

}
