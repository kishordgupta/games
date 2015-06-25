package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonStartLevel extends Button {

	public ButtonStartLevel(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}

	@Override
	public void onTouch(Point p) {
		Helper.println("Checking hitpoint...");
		if(this.checkHit(p) || true){
			Helper.println("Starting game...");
			GlobalVars.ge.loadStage(new BounceTest());			
		}
	}

	public ButtonStartLevel(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

}
