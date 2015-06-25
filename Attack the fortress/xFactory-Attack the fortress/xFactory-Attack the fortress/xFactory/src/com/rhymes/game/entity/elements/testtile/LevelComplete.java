package com.rhymes.game.entity.elements.testtile;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.menus.ScoreScreen;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class LevelComplete extends Button {

	public LevelComplete(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}
	
	public LevelComplete(float x, float y, float width, float height,	int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		// TODO Auto-generated constructor stub
	}
		
	@Override
	public void onEvent(Point p) {
		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-240f*LevelInfo.ratioX;
		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-160f*LevelInfo.ratioY;
		if(this.checkHit(p)){
			Helper.println("back to mode menu game...");
			
			if(LevelInfo.GAME_MODE==2 && LevelInfo.LEVEL_NUMBER==8){
			LevelInfo.GAME_COMPLETE=true;	
			}
			else if(LevelInfo.GAME_MODE==1 && LevelInfo.LEVEL_NUMBER==24){
				LevelInfo.GAME_COMPLETE=true;
				
			}
			GlobalVars.ge.loadStage(new ScoreScreen());		
			
		}
	}


}
