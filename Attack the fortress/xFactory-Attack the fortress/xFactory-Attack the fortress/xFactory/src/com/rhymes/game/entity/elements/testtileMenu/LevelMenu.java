package com.rhymes.game.entity.elements.testtileMenu;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class LevelMenu extends StageBase {
	
	float x, y;
	ButtonStartChallengeLevel challengeLevel;
	
	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BTN_BKGMAPVIEW);
		assetPack.addTexture(AssetConstants.IMG_BTN_1BTNLEVEL);
		assetPack.addTexture(AssetConstants.IMG_BTN_2BTNLEVEL);
		assetPack.addTexture(AssetConstants.IMG_BTN_3BTNLEVEL);
		assetPack.addTexture(AssetConstants.IMG_BTN_4BTNLEVEL);
		assetPack.addTexture(AssetConstants.IMG_BTN_5BTNLEVEL);
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

		x=160f*LevelInfo.ratioX;
		y=70f*LevelInfo.ratioY;
		ButtonBackToLevelMenu buttonBack = new ButtonBackToLevelMenu(x, y, 180f*LevelInfo.ratioX, 50f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_BACK);
		this.elements.add(buttonBack);
		subscribeToControllingInteraction(buttonBack, InteractionTouch.class);
		
		x = 100f*LevelInfo.ratioX;
		y = 120f*LevelInfo.ratioY;
		challengeLevel = new ButtonStartChallengeLevel(x, y,64f*LevelInfo.ratioX, 64f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_1BTNLEVEL, 20);
		this.elements.add(challengeLevel);
		subscribeToControllingInteraction(challengeLevel, InteractionTouch.class);
		
		x = 160f*LevelInfo.ratioX;
		y = 140f*LevelInfo.ratioY;
		challengeLevel = new ButtonStartChallengeLevel(x, y,64f*LevelInfo.ratioX, 64f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_2BTNLEVEL, 21);
		this.elements.add(challengeLevel);
		subscribeToControllingInteraction(challengeLevel, InteractionTouch.class);
		x = 220f*LevelInfo.ratioX;
		y = 150f*LevelInfo.ratioY;
		challengeLevel = new ButtonStartChallengeLevel(x, y,64f*LevelInfo.ratioX, 64f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_3BTNLEVEL, 22);
		this.elements.add(challengeLevel);
		subscribeToControllingInteraction(challengeLevel, InteractionTouch.class);
		x = 280f*LevelInfo.ratioX;
		y = 140f*LevelInfo.ratioY;
		challengeLevel = new ButtonStartChallengeLevel(x, y,64f*LevelInfo.ratioX, 64f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_4BTNLEVEL, 23);
		this.elements.add(challengeLevel);
		subscribeToControllingInteraction(challengeLevel, InteractionTouch.class);
		x = 340f*LevelInfo.ratioX;
		y = 120*LevelInfo.ratioY;
		challengeLevel = new ButtonStartChallengeLevel(x, y,64f*LevelInfo.ratioX, 64f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_5BTNLEVEL, 24);
		this.elements.add(challengeLevel);
		subscribeToControllingInteraction(challengeLevel, InteractionTouch.class);
	}
	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}

	
}
