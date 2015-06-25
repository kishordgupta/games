package com.rhymes.game.entity.animations;

import com.badlogic.gdx.Gdx;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.helpers.Helper;

public class AniPinBall extends AnimationBase {

	public AniPinBall(ElementBase element) {
		super(element);
	}

	@Override
	public void init() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void reset() {

	}

	float dx = 2, dy = 2;
	@Override
	public void step(long stepTime) {
		this.element.setX(this.element.getX() + dx);
		this.element.setY(this.element.getY() + dy);
		
		Helper.printKeyVal("X: ", this.element.getX());
		Helper.printKeyVal("SW: ", Gdx.graphics.getWidth());
		Helper.printKeyVal("EW: ", this.element.getWidth());
		if(this.element.getX() >= Gdx.graphics.getWidth()-this.element.getWidth() 
				|| this.element.getX() <= 0)
		{
			this.dx *=-1;
		}
		if(this.element.getY() >= Gdx.graphics.getHeight()- this.element.getHeight() 
				|| this.element.getY() <= 0)
		{
			this.dy *=-1;
		}
	}

}
