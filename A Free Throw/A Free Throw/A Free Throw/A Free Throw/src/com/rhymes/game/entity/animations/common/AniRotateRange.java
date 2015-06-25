package com.rhymes.game.entity.animations.common;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.animations.AnimationDefault;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.helpers.Helper;

public class AniRotateRange extends AnimationBase {
	private float currentAngle = 0;
	private float stepTime = 0;
	private float stepTimeLeft = 64;
	private float angleStep = 2;
	
	private float startAngle = 0;
	private float endAngle = 360;
	
	public AniRotateRange(ElementBase element) {
		super(element);
	}
	
	public AniRotateRange(ElementBase element, float stepTime, float angleStep, float startAngle, float endAngle) {
		super(element);
		this.angleStep = angleStep;
		this.stepTime = stepTime;
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		init();
	}
	
	
	@Override
	public void step(long stepTime) {
		currentAngle = currentAngle + (angleStep / this.stepTime) * (float)stepTime;
		this.element.setRotateAngle(currentAngle);		
		if(currentAngle >= endAngle)
		{
			angleStep*=-1;
		}
		else if(currentAngle <= startAngle)
		{
			angleStep*=-1;
		}	
	}

	@Override
	public void init() {
		this.currentAngle = this.startAngle;
	}

	@Override
	public void reset() {
		this.currentAngle = this.startAngle;
	}

	@Override
	public void pause() {

	}
}
