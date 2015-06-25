package com.rhymes.game.stage.levels;

import java.util.Random;

import com.rhymes.game.entity.elements.unsorted.TestElement;
import com.rhymes.game.interactions.inputs.InteractionLeftRight;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;

public class LevelTest extends StageBase {

	public void loadElements() {
		/******* Load the Interactions *******/
		// this.interactions.add(new InteractionTest());
		this.interactions.add(new InteractionLeftRight());

		/******* Load the elements *******/
		ElementBase temp;
		for (int i = 0; i < 500; i++) {
			temp = new TestElement(new Random().nextInt(400), new Random()
					.nextInt(600), 14,24, 0);
			this.elements.add(temp);
			subscribeToInteraction((InteractionCallbacks) temp,
					InteractionLeftRight.class);
		}
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
