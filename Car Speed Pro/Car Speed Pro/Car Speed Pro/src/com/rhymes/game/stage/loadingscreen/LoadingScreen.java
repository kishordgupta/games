package com.rhymes.game.stage.loadingscreen;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.stage.menus.stick.LevelInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class LoadingScreen extends StageBase {
	
	int state = 1;
	StageBase stage;
	
	LoadingProgressBar loadingProgressBar;
	public LoadingScreen(StageBase stage)
	{
		this.stage = stage;
	}

	@Override
	public void loadElements() {
		addElement(new Background(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, 
				AssetConstants.IMG_SPLASH));
		loadingProgressBar = new LoadingProgressBar(40 * LevelInfo.ratioX , 10 * LevelInfo.ratioY,
				Gdx.graphics.getWidth() - 80 * LevelInfo.ratioX , 40 * LevelInfo.ratioY ,
				AssetConstants.IMG_LOADING_BACK, AssetConstants.IMG_LOADING_FRONT);
		addElement(loadingProgressBar);
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		return assetPack;
	}

	@Override
	public void tick(long stepTime) {
		loadStage();
	}

	StageBase temp;
	private void loadStage() {
//		Helper.println("State : " + state);
		switch (state) {
		case 1:
			GlobalVars.ge.getAssetHandler().loadAssets(stage.getAssets(new AssetPack()));
			state = 2;
			break;
		case 2:
			if(updateProgress())
				state = 3;
			break;
		case 3:
			stage.setGameState(GameState.LOADING);
			temp = GlobalVars.ge.getCurrentStage();
			GlobalVars.ge.setCurrentStage(stage);
			stage.loadElements();
			GlobalVars.ge.setCurrentStage(temp);
			state = 4;
			break;
		case 4:
			GlobalVars.ge.getAssetHandler().loadAssets(stage.getElementsAssets(new AssetPack()));
			state = 5;
			break;
		case 5:
			if(updateProgress())
			{
				GlobalVars.ge.setCurrentStage(stage);
				stage.init();
			}
			break;
		default:
			break;
		}
	}

	private boolean updateProgress() {
		if(!GlobalVars.ge.getAssetHandler().getAssetManager().update()){
			Helper.println("Loading asset: " + GlobalVars.ge.getAssetHandler().getAssetManager().getProgress());
			loadingProgressBar.setProgress(GlobalVars.ge.getAssetHandler().getAssetManager().getProgress());
			return false;
		}
		return true;
	}

}
