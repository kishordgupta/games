package com.rhymes.game.entity.elements.testtileMenu;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;

public class GameModeMenu extends StageBase {

	float x;
	float y;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BTN_BKGMAPVIEW);
		assetPack.addTexture(AssetConstants.IMG_BTN_CHALLENGES);
		assetPack.addTexture(AssetConstants.IMG_BTN_ARCADEPLAY);		
		assetPack.addTexture(AssetConstants.IMG_BTN_BACK);
		return assetPack;
	}

	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BTN_BKGMAPVIEW));
		GlobalVars.ge.getScreen().cam.position.set( Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f,0f);

		//back to mode menu
//		x = 48*GameMenuInfo.ratio_w- Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
//		y = 120*GameMenuInfo.ratio_h;\
		x=150f*LevelInfo.ratioX;
		y=155f*LevelInfo.ratioY;
		ButtonChallenges challenge = new ButtonChallenges(x, y, 180f*LevelInfo.ratioX, 50f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_CHALLENGES);
		this.elements.add(challenge);
		subscribeToControllingInteraction(challenge, InteractionTouch.class);

		x=150f*LevelInfo.ratioX;
		y=100f*LevelInfo.ratioY;
		StartArcadeGame arcadeGame = new StartArcadeGame(x, y, 180f*LevelInfo.ratioX, 50f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_ARCADEPLAY);
		this.elements.add(arcadeGame);
		subscribeToControllingInteraction(arcadeGame, InteractionTouch.class);

		x=150f*LevelInfo.ratioX;
		y=45f*LevelInfo.ratioY;
		ButtonBackToMainmenu buttonBack = new ButtonBackToMainmenu(x, y, 180f*LevelInfo.ratioX, 50f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_BACK);
		this.elements.add(buttonBack);
		subscribeToControllingInteraction(buttonBack, InteractionTouch.class);

	}
	


	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}

}
