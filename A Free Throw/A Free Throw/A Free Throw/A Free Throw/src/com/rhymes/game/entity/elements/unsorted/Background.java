package com.rhymes.game.entity.elements.unsorted;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.elements.nonphysical.ResultBounce;
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
	String imagePath;
//	public Background(float x, float y, float width, float height, int zIndex) {
//		super(x, y, width, height, zIndex);
//	}
	public Background(){
		super();
	}
	public Background(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public Background(float x, float y, float width, float height, int zIndex, String imagePath) {
		super(x, y, width, height, zIndex);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.imagePath = imagePath; 
	}
	/**
	 * Add all the texture needed by this element in this function
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(imagePath); 
	}
	
	public void render() {
	
		GlobalVars.ge.getRenderer().render(image, x, y, width, height);
		
	}
	
	private AniLoop aniLoop;
	@Override
	public void init() {
		this.image = Helper.getImageFromAssets(imagePath);
//		TextureRegion[] images = new TextureRegion[2];
//		images[0] = Helper.getImageFromAssets(AssetConstants.IMG_BACK_2);
//		images[1] = Helper.getImageFromAssets(AssetConstants.IMG_BACK_3);
//		aniLoop = new AniLoop(this, images, true);
//		aniLoop.init();
//		aniLoop.setStepTime(300);
	}

	@Override
	public void step(long stepTime) {
//		this.state.step(stepTime);
//		this.aniLoop.step(stepTime);
	}
}
