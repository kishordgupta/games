package com.rhymes.game.entity.elements.testtileMenu;

import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.levels.TestTileLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonStartChallengeLevel extends Button {
	private int levelNumber=1;

	public ButtonStartChallengeLevel(float x, float y, float width,
			float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonStartChallengeLevel(float x, float y, float width,
			float height, int zIndex, String imagePath, int k) {
		super(x, y, width, height, zIndex, imagePath);
		this.levelNumber=k;
	}

	@Override
	public void onEvent(Point p) {
		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-240f*LevelInfo.ratioX;
		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-160f*LevelInfo.ratioY;

		if(this.checkHitPrev(p)){
			Helper.println("Button Restart Hit : " + p.toString());
			Helper.println("Level Number : " + levelNumber);
			LevelInfo.LEVEL_NUMBER=levelNumber;
//			GlobalVars.ge.getCurrentStage().setGameState(GameState.PLAYING);			
//			GlobalVars.ge.loadStage(stage)
			GlobalVars.ge.loadStage(new TestTileLevel(levelNumber));
			
		}
	}


}
