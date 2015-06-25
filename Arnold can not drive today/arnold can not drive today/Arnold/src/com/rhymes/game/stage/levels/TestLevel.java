package com.rhymes.game.stage.levels;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.pinball.Ball;
import com.rhymes.game.entity.elements.pinball.BottomBar;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.pinball.ILeftRight;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;

public class TestLevel extends StageBase {

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		return null;
	}

	@Override
	public void loadElements() {
		ILeftRight iLeftRight = new ILeftRight();
		InteractionTouch it = new InteractionTouch();
		this.interactions.add(iLeftRight);
		this.interactions.add(it);

		addElement(new Background(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BACK_2));
		addElement(new Ball(10, 20, 30, 30, 1));
		
		BottomBar bottomBar = new BottomBar(0, 0, 90, 13, 1);
		addElement(bottomBar);
		
		iLeftRight.subscribe( (InteractionCallbacks) bottomBar );
	}

	@Override
	public void tick(long stepTime) {

	}

}

