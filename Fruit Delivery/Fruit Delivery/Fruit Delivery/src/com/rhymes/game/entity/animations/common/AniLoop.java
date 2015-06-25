package com.rhymes.game.entity.animations.common;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.animations.AnimationDefault;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.helpers.Helper;

public class AniLoop extends AnimationBase {
	private boolean repeat = true;
	
	private TextureRegion[] images;
	private int currentIndex = 0;
	private boolean isFinished;
	private long stepTime = 100;
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

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	private long stepTimeLeft = 100;
	
	public AniLoop(ElementBase element) {
		super(element);
	}

	public AniLoop(ElementBase element, TextureRegion[] images, boolean repeat) {
		super(element);
		this.setImages(images);
		this.setRepeat(repeat);
	}

	
	
	@Override
	public void step(long stepTime) {
		
		if(!this.repeat && this.isFinished)
			return;
		
		if(images == null)
			return;
		
//		Helper.printKeyVal("Steptime: ", stepTime);
//		Helper.printKeyVal("Steptime left: ", stepTimeLeft);
		if(stepTime < stepTimeLeft){
			stepTimeLeft -= stepTime;
			return;
		}
			stepTimeLeft = this.stepTime;
		
		this.element.setImage(images[currentIndex++]);
		if(currentIndex == images.length)
		{
			if(repeat)
				currentIndex = 0;
			else
				this.isFinished = true;
		}
		
	}

	@Override
	public void init() {
		if(images != null)
			if(images.length > 0)
				this.element.setImage(images[0]);
			else
				this.element.setImage(getImageFromAssets(AssetConstants.IMG_BACK));
	}

	@Override
	public void reset() {
		this.element.setImage(images[0]);
		currentIndex = 0;
		this.isFinished = false;
	}

	@Override
	public void pause() {

	}

	public void setImages(TextureRegion[] images) {
		this.images = images;
	}

	public TextureRegion[] getImages() {
		return images;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public boolean isRepeat() {
		return repeat;
	}
	
	public boolean isFinished() {
		return isFinished;
	}
}
