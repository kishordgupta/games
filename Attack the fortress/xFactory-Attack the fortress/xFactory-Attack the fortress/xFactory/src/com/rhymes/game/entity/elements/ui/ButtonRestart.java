package com.rhymes.game.entity.elements.ui;

import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.stage.levels.TestTileLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.helpers.Helper;

public class ButtonRestart extends Button {

	public ButtonRestart(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}

	@Override
	public void onEvent(Point p) {
		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-240f*LevelInfo.ratioX;
		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-160f*LevelInfo.ratioY;

		if(this.checkHitPrev(p)){
			Helper.println("Button Restart Hit : " + p.toString());
			
			int k=LevelInfo.LEVEL_NUMBER;			
//			GlobalVars.ge.loadStage(stage)
			if(LevelInfo.GAME_MODE==2){
				LevelInfo.totalScore=0;
				LevelInfo.LEVEL_NUMBER=1;
				GlobalVars.ge.loadStage(new TestTileLevel());
				
			}
			else if(LevelInfo.GAME_MODE==1)
			{
				GlobalVars.ge.loadStage(new TestTileLevel(k));
			}
			
		}
	}

	public ButtonRestart(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

}
