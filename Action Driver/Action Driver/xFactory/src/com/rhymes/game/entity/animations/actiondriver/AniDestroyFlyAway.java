package com.rhymes.game.entity.animations.actiondriver;

import com.rhymes.game.data.Constants;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.helpers.Helper;

public class AniDestroyFlyAway extends AnimationBase {

	float dx, dy;
	public AniDestroyFlyAway(ElementBase element, float dx, float dy) {
		super(element);
		this.dx = dx;
		this.dy = dy;
	}

	@Override
	public void init() {

	}

	@Override
	public void step(long stepTime) {
		if(this.finished)
			return;
		dy = 15 * Constants.ry;
		dx = 15 * Constants.rx;
//		this.element.setX(this.element.getX()+dx/2f);
//		this.element.setWidth(this.element.getWidth()-dx);
//		if(this.element.getWidth() <= 0)
//			setFinished(true);
		
		this.element.setX(this.element.getX()-dx);
		this.element.setY(this.element.getY()+dy);
//		this.element.setHeight(this.element.getHeight()-dy);
		if(this.element.getY() > Helper.getScreenHeight())
			setFinished(true);
	}

	@Override
	public void pause() {

	}

	@Override
	public void reset() {

	}
}
