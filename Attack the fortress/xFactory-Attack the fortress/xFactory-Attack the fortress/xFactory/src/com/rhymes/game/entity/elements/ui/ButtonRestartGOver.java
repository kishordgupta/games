package com.rhymes.game.entity.elements.ui;

import com.rhymes.game.stage.levels.XLevel;
import com.rhymes.game.stage.menus.LevelMenu;
import com.rhymes.game.stage.menus.ModeMenu;
import com.rhymes.game.stage.result.Result;
import com.rhymes.game.stage.result.ResultBTRClassic;
import com.rhymes.game.stage.result.ResultBTRMAP;
import com.rhymes.game.stage.result.ResultBTRTime;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.helpers.Helper;

public class ButtonRestartGOver extends Button {

	private int level_id;
	private Result res;
	public ButtonRestartGOver(float x, float y, float width, float height,
			int zIndex, String imagePath, int level_id, Result result) {
		super(x, y, width, height, zIndex, imagePath);
		this.level_id = level_id;
		this.res = result;
	}

	@Override
	public void onEvent(Point p) {
		if(this.checkHit(p)){
			Helper.println("Button Restart Hit in game over screen: " + p.toString());

//			GlobalVars.ge.getCurrentStage().setGameState(GameState.PLAYING);			
//			GlobalVars.ge.getCurrentStage().reload();			
			
			if(res instanceof ResultBTRMAP)
				GlobalVars.ge.loadStage(new XLevel(level_id, new ResultBTRMAP()));
			else if(res instanceof ResultBTRClassic)
				GlobalVars.ge.loadStage(new XLevel(level_id, new ResultBTRClassic()));
			else if(res instanceof ResultBTRTime)
				GlobalVars.ge.loadStage(new XLevel(level_id, new ResultBTRTime()));
			
		}
	}

	public ButtonRestartGOver(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

}
