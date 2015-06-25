package com.rhymes.game.entity.animations.common;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.helpers.Helper;

public class AniLoop extends AnimationBase {
	private boolean repeat = true;
	
	private TextureRegion[] images;
	private int currentIndex = 0;
	private boolean isFinished;
	private long stepTime = 90;//150
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

	private long stepTimeLeft = stepTime;
	
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
		if(this.isFinished)
			return;
		
		if(images == null){
		Helper.println("images is null");
			return;
		}
//		Helper.printKeyVal("Steptime: ", stepTime);
//		Helper.printKeyVal("Steptime left: ", stepTimeLeft);
//		
		
		if(stepTime == 0)
			stepTime = 1;
		if(stepTimeLeft > 0){
//		if(stepTime < stepTimeLeft){
			stepTimeLeft -= stepTime;
			return;
		}
			stepTimeLeft = this.stepTime;
//		Helper.println("aniloop stepping index: " + currentIndex);
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
//		if(images != null)
//			if(images.length > 0)
//				this.element.setImage(images[0]);
//			else
//				this.element.setImage(getImageFromAssets(AssetConstants.IMG_BKG_RED));
	}

	@Override
	public void reset() {

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
