package com.rhymes.game.entity.elements.ui;

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
		if(this.checkHit(p)){
			Helper.println("Button Restart Hit : " + p.toString());

//			GlobalVars.ge.getCurrentStage().setGameState(GameState.PLAYING);			
			GlobalVars.ge.getCurrentStage().reload();			
		}
	}

	public ButtonRestart(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

}
