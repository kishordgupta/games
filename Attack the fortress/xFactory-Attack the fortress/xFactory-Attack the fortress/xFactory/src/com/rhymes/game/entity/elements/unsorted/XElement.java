package com.rhymes.game.entity.elements.unsorted;

import java.util.Random;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.states.StateTest;
import com.rhymes.game.entity.states.XState;
import com.rhymes.game.interactions.inputs.InteractionLeftRight;
import com.rhymes.game.interactions.inputs.InteractionLeftRightCallbacks;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;

public class XElement extends ElementBase implements InteractionLeftRightCallbacks{

	public XElement(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BKG_TEAL);
		
	}

	@Override
	public void init() {
		this.state = new XState(this);
//		this.state = new StateTest(this);
		this.state.init();
		
		GlobalVars.ge.getCurrentStage().subscribeToInteraction(this, InteractionLeftRight.class);
	}

	@Override
	public void onEvent(int event) {
		if(event == InteractionLeftRight.NONE)
			return;
		x = new Random().nextInt(400);
		y = new Random().nextInt(600);
	}
}
