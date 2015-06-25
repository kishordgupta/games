package com.rhymes.game.entity.animations.common;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;

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
			waitTime = 3000 + rand.nextInt(6000);
			firstTime = false;
			currentX = Gdx.graphics.getWidth()+element.getWidth();
			currentY = rand.nextInt(Math.abs((int) (Gdx.graphics.getHeight()-20)));
			
//			if(element instanceof Clouds){
				this.element.setSize(50 + rand.nextInt(100), 50 + rand.nextInt(200));
				this.element.setRotateAngle(rand.nextInt(60));
//			}
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
		waitTime = 2500 + rand.nextInt(2000);
	}

	@Override
	public void reset() {
		currentX = 0;
	}

	@Override
	public void pause() {

	}
}
