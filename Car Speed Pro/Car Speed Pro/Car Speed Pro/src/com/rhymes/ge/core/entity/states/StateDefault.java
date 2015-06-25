package com.rhymes.ge.core.entity.states;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.entity.animations.AnimationDefault;
import com.rhymes.ge.core.entity.elements.ElementBase;

public class StateDefault extends StateBase {
	String imagePath = AssetConstants.IMG_BKG_CS;
	public StateDefault(ElementBase element) {
		super(element);
		init();
	}
	public StateDefault(ElementBase element, String imagePath)
	{
		super(element);
		this.imagePath = imagePath;
	}

	@Override
	public void step(long stepTime) {
		if(animation != null)
			animation.step(stepTime);
	}

	@Override
	public void init() {
			this.animation = new AnimationDefault(element, imagePath);
			this.animation.init();
	}

}
