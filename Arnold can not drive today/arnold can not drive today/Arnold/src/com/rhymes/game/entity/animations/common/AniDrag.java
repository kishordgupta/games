package com.rhymes.game.entity.animations.common;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.animations.AnimationDefault;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.helpers.Helper;

public class AniDrag extends AnimationBase {

	float currentX = 0;
	float currentY = 0;
	long waitTime = 10000;
	long elapsedTime = 0;
	
	private float stepScrollX = 3;
	public float getStepScrollX() {
		return stepScrollX;
	}

	public void setStepScrollX(float stepScrollX) {
		this.stepScrollX = stepScrollX;
	}

	Random rand;
	public AniDrag(ElementBase element) {
		super(element);
		this.element.setX(Gdx.graphics.getWidth() * 1.5f);
	}
	
	public AniDrag(ElementBase element, float stepScrollX) {
		super(element);
		this.stepScrollX = stepScrollX;
	}

	@Override
	public void step(long stepTime) {
		currentX = currentX - stepScrollX;
		getElement().setX(currentX);
		getElement().setY(currentY);
	}

	@Override
	public void init() {
		currentX = Gdx.graphics.getWidth();
		currentY = element.getY();
		rand = new Random();
		waitTime = 1000 /*+ rand.nextInt(2000)*/;
	}

	@Override
	public void reset() {
		currentX = 0;
	}

	@Override
	public void pause() {

	}
}
