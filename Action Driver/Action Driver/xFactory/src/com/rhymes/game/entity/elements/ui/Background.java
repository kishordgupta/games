package com.rhymes.game.entity.elements.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Background extends ElementBase {
	String imagePath;
	public Background(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
	
	public Background(float x, float y, float width, float height, int zIndex, String imagePath) {
		super(x, y, width, height, zIndex);
		this.imagePath = imagePath; 
	}
	
	public Background(float x, float y, float width, float height, int zIndex, TextureRegion image) {
		super(x, y, width, height, zIndex);
		this.image = image; 
	}
	
	/**
	 * Add all the texture needed by this element in this function
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(imagePath);
	}
	
	private AniLoop aniLoop;
	
	@Override
	public void init() {
		this.image = Helper.getImageFromAssets(imagePath);
//		Helper.println("Back image is set: " + image);
	}

	@Override
	public void step(long stepTime) {
	}
}
