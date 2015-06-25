package com.rhymes.game.entity.animations.actiondriver;

import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;

public class AniCarSteepJump extends AnimationBase {

	float maxDy;
	float dy;
	float curDy;
	boolean directionUP;
	public AniCarSteepJump(ElementBase element, float dy, float maxDy) {
		super(element);
		this.maxDy = maxDy;
		this.dy = dy;
		directionUP = true;
		curDy = 0;
	}

	@Override
	public void init() {

	}

	@Override
	public void step(long stepTime) {
		if(finished)
			return;
		if(directionUP)
		{
			this.element.setY(this.element.getY() + dy);
			this.curDy += dy;
			if(this.curDy >= this.maxDy){
				directionUP = false;
				curDy = 0;
			}
		}
		else
		{
			this.element.setY(this.element.getY() - dy);
			this.curDy += dy;
			if(this.curDy >= this.maxDy)
				this.setFinished(true);
		}
	}

	@Override
	public void pause() {

	}

	@Override
	public void reset() {

	}

}
