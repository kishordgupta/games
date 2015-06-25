package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonTimeMode extends Button{

	public ButtonTimeMode(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}

	@Override
	public void onTouch(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("time mode game...");
//			GlobalVars.ge.loadStage(new XLevel());			
		}
	}

	public ButtonTimeMode(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
