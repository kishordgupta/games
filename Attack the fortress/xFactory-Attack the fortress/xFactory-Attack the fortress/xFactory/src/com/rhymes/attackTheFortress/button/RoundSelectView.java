package com.rhymes.attackTheFortress.button;

import com.badlogic.gdx.Gdx;
import com.rhymes.attackTheFortress.LevelInfo;
import com.rhymes.game.data.AssetConstants;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class RoundSelectView extends StageBase {
	
	float x, y;
	private ModeSelectView modeSelectView;
	
	public ModeSelectView getModeSelectView(){
		return this.modeSelectView;
	}
	public void setModeSelectView(ModeSelectView mode){
		this.modeSelectView=mode;
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_ROUND_SELECTVIEW);
		assetPack.addTexture(AssetConstants.IMG_BTN_Map_1);
		assetPack.addTexture(AssetConstants.IMG_BTN_Map_2);
		assetPack.addTexture(AssetConstants.IMG_BTN_Map_3);
		assetPack.addTexture(AssetConstants.IMG_BTN_Map_4);
		assetPack.addTexture(AssetConstants.IMG_BTN_Map_5);
		assetPack.addTexture(AssetConstants.IMG_BTN_Map_6);
		assetPack.addTexture(AssetConstants.IMG_BTN_EXIT_TO_MENU);
		assetPack.addTexture(AssetConstants.IMG_MODE_VIEW);
		assetPack.addTexture(AssetConstants.IMG_BTN_EASY);
		assetPack.addTexture(AssetConstants.IMG_BTN_NORMAL);
		assetPack.addTexture(AssetConstants.IMG_BTN_HARD);
		assetPack.addTexture(AssetConstants.IMG_BTN_CLOSE);
		return assetPack;
	}
	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_ROUND_SELECTVIEW));
		ButtonStartMap challengeLevel;
		
		x=20f*LevelInfo.ratioX;
		y=20f*LevelInfo.ratioY;
		ButtonExitToMainView buttonBack = new ButtonExitToMainView(x, y, 130f*LevelInfo.ratioX, 30f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_EXIT_TO_MENU);
		this.elements.add(buttonBack);
		subscribeToControllingInteraction(buttonBack, InteractionTouch.class);
		
		x = 65f*LevelInfo.ratioX;
		y = 80f*LevelInfo.ratioY;
		challengeLevel = new ButtonStartMap(x, y,35f*LevelInfo.ratioX, 50f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_Map_1, 1);
		this.elements.add(challengeLevel);
		subscribeToControllingInteraction(challengeLevel, InteractionTouch.class);
		
		x = 225f*LevelInfo.ratioX;
		y = 50f*LevelInfo.ratioY;
		challengeLevel = new ButtonStartMap(x, y,35f*LevelInfo.ratioX, 50f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_Map_2, 2);
		this.elements.add(challengeLevel);
		subscribeToControllingInteraction(challengeLevel, InteractionTouch.class);
		x = 355f*LevelInfo.ratioX;
		y = 56f*LevelInfo.ratioY;
		challengeLevel = new ButtonStartMap(x, y,35f*LevelInfo.ratioX, 50f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_Map_3, 3);
		this.elements.add(challengeLevel);
		subscribeToControllingInteraction(challengeLevel, InteractionTouch.class);
		x = 97f*LevelInfo.ratioX;
		y = 178f*LevelInfo.ratioY;
		challengeLevel = new ButtonStartMap(x, y,35f*LevelInfo.ratioX, 50f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_Map_4, 4);
		this.elements.add(challengeLevel);
		subscribeToControllingInteraction(challengeLevel, InteractionTouch.class);
		x = 380f*LevelInfo.ratioX;
		y = 205*LevelInfo.ratioY;
		challengeLevel = new ButtonStartMap(x, y,35f*LevelInfo.ratioX, 50f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_Map_5, 5);
		this.elements.add(challengeLevel);
		subscribeToControllingInteraction(challengeLevel, InteractionTouch.class);
		x = 260f*LevelInfo.ratioX;
		y = 175*LevelInfo.ratioY;
		challengeLevel = new ButtonStartMap(x, y,35f*LevelInfo.ratioX, 50f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_Map_6, 6);
		this.elements.add(challengeLevel);
		subscribeToControllingInteraction(challengeLevel, InteractionTouch.class);

	}
	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}

	
}
