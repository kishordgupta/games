package com.rhymes.game.entity.elements.unsorted;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
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

public class TestElement extends ElementBase implements InteractionLeftRightCallbacks{
	public TestElement(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

	/**
	 * Add all the texture needed by this element in this function
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BKG_RED);
	}
	

	@Override
	public void init() {
		this.state = new StateTest(this);
	}

	@Override
	public void step(long stepTime) {
		this.state.step(stepTime);
	}

	@Override
	public void onEvent(int event) {
		if(event == InteractionLeftRight.NONE)
			return;
		x = new Random().nextInt(400);
		y = new Random().nextInt(600);
	}
}
