package com.rhymes.game.stage;

import com.rhymes.game.interactions.inputs.InteractionLeftRight;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;

public class LoadingScreen extends StageBase {

	public void loadElements() {
		/******* Load the Interactions *******/
		// this.interactions.add(new InteractionTest());
		this.interactions.add(new InteractionLeftRight());

		/******* Load the elements *******/
	}

	@Override
	public void tick(long stepTime) {

	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub
		return null;
	}
}
