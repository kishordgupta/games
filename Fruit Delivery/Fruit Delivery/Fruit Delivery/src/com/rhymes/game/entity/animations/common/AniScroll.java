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

public class AniScroll extends AnimationBase {

	float currentX = 0;
	float currentY = 0;
	long waitTime = 3000;
	long elapsedTime = 0;
	
	private float stepScrollX = 3;
	public float getStepScrollX() {
		return stepScrollX;
	}

	public void setStepScrollX(float stepScrollX) {
		this.stepScrollX = stepScrollX;
	}

	Random rand;
	public AniScroll(ElementBase element) {
		super(element);
		this.element.setX(Gdx.graphics.getWidth() * 1.5f);
	}
	
	public AniScroll(ElementBase element, float stepScrollX) {
		super(element);
		this.stepScrollX = stepScrollX;
		this.element.setX(Gdx.graphics.getWidth() * 1.5f);		
	}

	boolean firstTime = true;
	@Override
	public void step(long stepTime) {
		if(currentX+element.getWidth() <= 0 || firstTime){
			elapsedTime += stepTime;
			if(elapsedTime<waitTime)
				return;
			elapsedTime = 0;
			waitTime = 3000 + rand.nextInt(3000);
			firstTime = false;
			
			currentX = Gdx.graphics.getWidth()+ element.getWidth();
			currentY = rand.nextInt(Math.abs((int) (Gdx.graphics.getHeight()-20)));
		}
		currentX = currentX - stepScrollX;
		getElement().setX(currentX);
		getElement().setY(currentY);
		
		
//		getElement().setY(new Random().nextInt(600));
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
