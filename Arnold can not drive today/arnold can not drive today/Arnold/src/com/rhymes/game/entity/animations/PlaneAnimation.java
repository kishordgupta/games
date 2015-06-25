package com.rhymes.game.entity.animations;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;

public class PlaneAnimation  extends AnimationBase{

	public PlaneAnimation(ElementBase element) {
		super(element);
	}

	@Override
	public void init() {
		this.element.setImage(getImageFromAssets(AssetConstants.IMG_BKG_TEAL));
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void step(long stepTime) {
		// TODO Auto-generated method stub
		
	}

}
