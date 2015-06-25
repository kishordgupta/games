package com.rhymes.game.entity.animations.common;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.animations.AnimationDefault;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.helpers.Helper;

public class AniPopOut extends AnimationBase {
	private float currentScale = 0;
	private boolean finished = false;
	

	private long stepTime = 60;
	private long stepTimeLeft = 64;
	private float scaleStep = 2;
	private float maxScale = 3;

	public AniPopOut(ElementBase element) {
		super(element);
	}
	
	public AniPopOut(ElementBase element, long stepTime, float scaleStep, float maxScale) {
		super(element);
		this.scaleStep = scaleStep;
		this.stepTime = stepTime;
		this.maxScale = maxScale;
	}
	
	
	@Override
	public void step(long stepTime) {
		if(finished)
			return;
//		Helper.printKeyVal("Steptime: ", stepTime);
		if(stepTime < stepTimeLeft){
			stepTimeLeft -= stepTime;
			return;
		}
		stepTimeLeft = this.stepTime;
		
		
		this.element.setRotateAngle(currentScale);		
		currentScale+= scaleStep;
		if(currentScale >= maxScale)
		{
			currentScale = 0;
			finished = true;
		}
	}

	@Override
	public void init() {
		stepTimeLeft = stepTime;
		currentScale = 0;
	}

	@Override
	public void reset() {
		init();
		finished = true;
	}

	@Override
	public void pause() {

	}
	
	
	public long getStepTime() {
		return stepTime;
	}

	public void setStepTime(long stepTime) {
		this.stepTime = stepTime;
	}

	public long getStepTimeLeft() {
		return stepTimeLeft;
	}

	public void setStepTimeLeft(long stepTimeLeft) {
		this.stepTimeLeft = stepTimeLeft;
	}

	public float getScaleStep() {
		return scaleStep;
	}

	public void setScaleStep(float scaleStep) {
		this.scaleStep = scaleStep;
	}
	
	public float getMaxScale() {
		return maxScale;
	}

	public void setMaxScale(float maxScale) {
		this.maxScale = maxScale;
	}

	public void setCurrentScale(float currentScale) {
		this.currentScale = currentScale;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public boolean isFinished() {
		return finished;
	}

	public float getCurrentScale() {
		return currentScale;
	}
}
