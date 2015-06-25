package com.rhymes.game.entity.elements.unsorted;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.states.StateTest;
import com.rhymes.game.interactions.InteractionTestCallbacks;
import com.rhymes.game.interactions.inputs.InteractionLeftRight;
import com.rhymes.game.interactions.inputs.InteractionLeftRightCallbacks;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.entity.states.StateDefault;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Background extends ElementBase {
	@Override
	public void render() {
		super.render();
//		GlobalVars.ge.getRenderer().render(this.image, x, y, width, height, 0,0, rotateAngle, 1, 1);
	}

	String imagePath;
	public Background(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
	
	public Background(float x, float y, float width, float height, int zIndex, String imagePath) {
		super(x, y, width, height, zIndex);
		this.imagePath = imagePath; 
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
		Helper.println("Back image is set: " + image);
	}

	@Override
	public void step(long stepTime) {
	}
}
