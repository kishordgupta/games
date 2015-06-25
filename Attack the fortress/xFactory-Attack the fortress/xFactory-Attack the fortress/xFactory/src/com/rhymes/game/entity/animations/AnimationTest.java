package com.rhymes.game.entity.animations;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.animations.AnimationDefault;
import com.rhymes.ge.core.entity.elements.ElementBase;

public class AnimationTest extends AnimationBase {

	public AnimationTest(ElementBase element) {
		super(element);
	}

	@Override
	public void step(long stepTime) {
//		 getElement().setX(new Random().nextInt(400));
//		 getElement().setY(new Random().nextInt(600));
	}

	@Override
	public void init() {
		this.element.setImage(getImageFromAssets(AssetConstants.IMG_BKG_RED));
	}

	@Override
	public void reset() {

	}

	@Override
	public void pause() {

	}
}
