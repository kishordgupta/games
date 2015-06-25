package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonClassicMode extends Button{

	public ButtonClassicMode(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}

	

	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("classic game...");
//			GlobalVars.ge.loadStage(new XLevel());			
		}
	}

	public ButtonClassicMode(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
