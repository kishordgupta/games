package com.rhymes.game.entity.animations;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;

public class XAnimation extends AnimationBase{

	public XAnimation(ElementBase element) {
		super(element);
	}

	@Override
	public void init() {
		this.element.setImage(getImageFromAssets(AssetConstants.IMG_BKG_TEAL));
//		this.element.setHeight(this.element.getHeight() * 2f);
	}

	@Override
	public void reset() {
		
	}

	@Override
	public void step(long stepTime) {
//		this.element.setX( new Random().nextInt(400));
//		this.element.setY( new Random().nextInt(600));
	}

	@Override
	public void pause() {
		
	}

}
