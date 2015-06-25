package com.rhymes.game.entity.elements.testtileMenu;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.result.Result;
import com.rhymes.game.stage.result.ResultBTRMAP;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonStartGame extends Button {

	public int getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	public Result getRes() {
		return res;
	}

	public void setRes(Result res) {
		this.res = res;
	}

	public ButtonStartGame(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}
	
	
	int levelNumber = 1;
	Result res = new ResultBTRMAP();
//	Result res = new ResultBTRClassic();
//	Result res = new ResultBTRTime();
	@Override
	public void onEvent(Point p) {
		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-240f*LevelInfo.ratioX;
		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-160f*LevelInfo.ratioY;
		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_BTN_BTNSTART_DOWN));
			Helper.println("Starting game...");
//			GlobalVars.ge.loadStage(new TestTileLevel());
			GlobalVars.ge.loadStage(new GameModeMenu());
		}
	}

	public ButtonStartGame(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
