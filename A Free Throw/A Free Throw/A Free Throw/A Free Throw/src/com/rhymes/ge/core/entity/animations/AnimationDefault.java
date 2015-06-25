package com.rhymes.ge.core.entity.animations;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;

public class AnimationDefault extends AnimationBase {
	String imagePath;
	public AnimationDefault(ElementBase element, String imagePath) {
		super(element);
		this.imagePath = imagePath;
	}

	protected TextureRegion image;
	@Override
	public void init() {
		this.element.setImage(new TextureRegion(GlobalVars.ge.getAssetHandler().getTexture(
				imagePath)));
	}

	@Override
	public void step(long stepTime) {
	
	}

	@Override
	public void reset() {

	}

	@Override
	public void pause() {
		
	}

}
