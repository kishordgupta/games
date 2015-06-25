package com.rhymes.game.entity.elements.testtileMenu;

import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.levels.TestTileLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class StartArcadeGame extends Button {

	public StartArcadeGame(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public StartArcadeGame(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		// TODO Auto-generated constructor stub
	}

	public void onEvent(Point p) {
		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-240f*LevelInfo.ratioX;
		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-160f*LevelInfo.ratioY;
		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("Starting game...");
			LevelInfo.GAME_MODE=2;
			LevelInfo.LEVEL_NUMBER=1;
			GlobalVars.ge.loadStage(new TestTileLevel());
			
//			GlobalVars.ge.loadStage(new MapModeMenu());
		}
	}

}
