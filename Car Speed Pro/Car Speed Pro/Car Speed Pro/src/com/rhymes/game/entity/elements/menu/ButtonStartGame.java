package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.data.Constants;
import com.rhymes.game.entity.elements.nonphysical.ResultBounce;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.result.Result;
import com.rhymes.game.stage.result.ResultBTRClassic;
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
	
	
	int levelNumber ;
//	Result res = new ResultBTRMAP();
//	Result res = new ResultBTRClassic();
	Result res = new ResultBounce();////making result object res an instance of another class ResultBounce
	@Override
	public void onEvent(Point p) {
		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("Starting game...");
//			GlobalVars.ge.loadStage(new XLevel(levelNumber, res));
//			if(Constants.levelSelected==1)
			GlobalVars.ge.loadStage(new BounceTest(getLevelNumber(), res));
//			else if(Constants.levelSelected==2)
//				GlobalVars.ge.loadStage(new BounceTestLevel2(getLevelNumber(), res));
		}
	}

	public ButtonStartGame(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
