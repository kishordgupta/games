package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonExitGame extends Button{

	public ButtonExitGame(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}

	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("Exit game...");
			this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_EXIT_PRESSED));
//			GlobalVars.ge.loadStage(new XLevel());	
			
			System.exit(0);
		}
	}

	public ButtonExitGame(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
