package com.rhymes.game.stage.menus.stick;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class NextLevel extends Button {

	public NextLevel(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public NextLevel(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onEvent(Point p) {
//		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-240f*LevelInfo.ratioX;
//		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-160f*LevelInfo.ratioY;

		if(this.checkHit(p)){
			Helper.println("Button Restart Hit : " + p.toString());
			int k=LevelInfo.currentLevelNumber;
//			GlobalVars.ge.getCurrentStage().setGameState(GameState.PLAYING);			
//			GlobalVars.ge.loadStage(stage)
//			if(LevelInfo.GAME_MODE==2){
				LevelInfo.currentLevelNumber=k+1;
				GlobalVars.ge.loadStage(new BikeLevel(LevelInfo.currentLevelNumber));
			
//			}
//			else if(LevelInfo.GAME_MODE==1)
//			{
//				LevelInfo.LEVEL_NUMBER=k+1;
//				GlobalVars.ge.loadStage(new TestTileLevel(k+1));
//				
//			}
//			
		}
	}


}
