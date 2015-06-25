package com.rhymes.game.entity.elements.ui;

import java.util.Random;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.interactions.InteractionTestCallbacks;
import com.rhymes.game.interactions.inputs.ICClick;
import com.rhymes.game.interactions.inputs.InteractionLeftRight;
import com.rhymes.game.interactions.inputs.InteractionLeftRightCallbacks;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.entity.states.StateDefault;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Button extends ElementBase implements InteractionTouchCallbacks{
	@Override
	public void render() {
//		GlobalVars.ge.getScreen().getBatch().setColor(0, 1, 0, 1f);
		super.render();
//		GlobalVars.ge.getScreen().getBatch().setColor(1, 1, 1, 1f);
	}

	String imagePath;
	public Button(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
	
	public Button(float x, float y, float width, float height, int zIndex, String imagePath) {
		super(x, y, width, height, zIndex);
		this.imagePath = imagePath;
	}
	/**
	 * Add all the texture needed by this element in this function
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
		
//		assetPack.addTexture(AssetConstants.IMG_BACK_2);
		assetPack.addTexture(imagePath);
	}
	

	@Override
	public void init() {
		if(imagePath != null)
			this.image = Helper.getImageFromAssets(imagePath);
//		Helper.println("gettting ass");
	}

	@Override
	public void step(long stepTime) {
//		this.state.step(stepTime);
	}

	@Override
	public void onEvent(Point htiPoint) {
	}
	
	protected boolean checkHit(Point p)
	{
		if(this.getLeft() <= p.x && this.getRight()>= p.x && this.getTop() >= p.y && this.getBottom() <= p.y)
			return true;
		return false;
	}
}
