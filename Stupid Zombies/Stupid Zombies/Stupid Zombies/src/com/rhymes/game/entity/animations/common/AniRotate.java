package com.rhymes.game.entity.animations.common;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.animations.AnimationDefault;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.helpers.Helper;

public class AniRotate extends AnimationBase {
	private float currentAngle = 0;
	private long stepTime = 100;
	private long stepTimeLeft = 64;
	private float angleStep = 5;
	
	
	public AniRotate(ElementBase element) {
		super(element);
	}
	
	public AniRotate(ElementBase element, long stepTime, float angleStep) {
		super(element);
		this.angleStep = angleStep;
		this.stepTime = stepTime;
	}
	
	
	@Override
	public void step(long stepTime) {
//		Helper.printKeyVal("Steptime: ", stepTime);
//		if(stepTime < stepTimeLeft){
//			stepTimeLeft -= stepTime;
//			return;
//		}
//		stepTimeLeft = this.stepTime;
		currentAngle = currentAngle + (angleStep / this.stepTime) * (float)stepTime;
		
		this.element.setRotateAngle(currentAngle);		
		if(currentAngle >= 360)
		{
			currentAngle = (currentAngle-360);
		}
	}

	@Override
	public void init() {
	}

	@Override
	public void reset() {

	}

	@Override
	public void pause() {

	}
}
