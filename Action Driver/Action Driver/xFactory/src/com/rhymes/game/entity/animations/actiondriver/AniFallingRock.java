package com.rhymes.game.entity.animations.actiondriver;

import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.helpers.Helper;

public class AniFallingRock extends AnimationBase {

	float dy;
	float lastY ;
	public AniFallingRock(ElementBase element, float dy, float lastY) {
		super(element);
		this.element.setY(Helper.getScreenHeight());
		this.lastY = lastY;
		this.dy = dy;
	}

	@Override
	public void init() {
	}

	
	@Override
	public void step(long stepTime) {
		if(this.isFinished())
			return;
		if(this.element.getX() > Helper.getCameraX() + Helper.getScreenWidth())
			return;
		this.element.setY(this.element.getY() - dy);
		
		if(this.element.getY() < lastY)
			this.setFinished(true);
	}

	@Override
	public void pause() {

	}

	@Override
	public void reset() {
		this.element.setY(Helper.getScreenHeight());
	}

}
