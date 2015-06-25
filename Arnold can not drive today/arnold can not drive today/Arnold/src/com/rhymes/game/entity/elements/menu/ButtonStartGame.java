package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.levels.XLevel;
import com.rhymes.game.stage.result.Result;
import com.rhymes.game.stage.result.ResultBTRClassic;
import com.rhymes.game.stage.result.ResultBTRMAP;
import com.rhymes.game.stage.result.ResultBTRTime;
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
		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("Starting game...");
			GlobalVars.ge.loadStage(new XLevel(levelNumber, res));			
		}
	}

	public ButtonStartGame(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
