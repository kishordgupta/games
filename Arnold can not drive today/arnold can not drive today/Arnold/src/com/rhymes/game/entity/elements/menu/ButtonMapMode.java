package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.menus.MapModeMenu;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonMapMode extends Button{

	public ButtonMapMode(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}

	

	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("go to Map mode...");
			GlobalVars.ge.loadStage(new MapModeMenu());			
		}
	}

	public ButtonMapMode(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
	
}
