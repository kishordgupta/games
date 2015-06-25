package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.menus.MainMenu;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.helpers.Helper;

public class ButtonSkipChance extends Button{

	public ButtonSkipChance(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}
	
	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		Helper.println("check hit Skipping..." + p);
		if(this.checkHit(p)){
			Helper.println("Skipping...");
			((BounceTest)GlobalVars.ge.getCurrentStage()).result.setState(GameState.OVER);
			
		}
	}

	public ButtonSkipChance(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
