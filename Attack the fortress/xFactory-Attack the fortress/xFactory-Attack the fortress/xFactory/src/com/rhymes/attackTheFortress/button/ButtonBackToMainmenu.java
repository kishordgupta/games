package com.rhymes.attackTheFortress.button;

import com.rhymes.game.data.AssetConstants;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
//import com.rhymes.game.stage.menus.MainMenu;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonBackToMainmenu extends Button{

	public ButtonBackToMainmenu(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}
	
	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("Back to main menu...");
			GlobalVars.ge.loadStage(new MainView());			
		}
	}

	public ButtonBackToMainmenu(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
