package com.rhymes.ge.core.entity.animations;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;

public abstract class AnimationBase {
	protected ElementBase element;
	protected boolean finished;
	
	float skipTime = 100;
	float curTime = 0;
	
	public boolean skipTime(float stepTime)
	{
		curTime += stepTime;
		if(curTime > skipTime){
			curTime = skipTime-curTime;
			return true;
		}
		return false;
	}
	
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public AnimationBase(ElementBase element)
	{
		this.setElement(element);
		curTime = 0;
	}
	public abstract void init();
	public abstract void step(long stepTime);
	public abstract void pause();
	public abstract void reset();
	public void setElement(ElementBase element) {
		this.element = element;
	}
	public ElementBase getElement() {
		return element;
	}
	
	public TextureRegion getImageFromAssets(String imageConstant)
	{
		return new TextureRegion(GlobalVars.ge.getAssetHandler().getTexture(
				imageConstant));
	}
	
}
