package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.menus.ModeMenu;
import com.rhymes.game.stage.menus.OptionMenu;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonBackToModeMenu extends Button{

	public ButtonBackToModeMenu(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}
	
	@Override
	public void onTouch(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("back to mode menu game...");
			GlobalVars.ge.loadStage(new ModeMenu());			
		}
	}

	public ButtonBackToModeMenu(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

}
