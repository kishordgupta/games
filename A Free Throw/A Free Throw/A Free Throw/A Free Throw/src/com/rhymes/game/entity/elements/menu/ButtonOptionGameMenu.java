package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.menus.OptionMenu;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonOptionGameMenu extends Button{

	public ButtonOptionGameMenu(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}
	@Override
	public void onTouch(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("go to option menu...");
//			this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_OPTIONS_PRESSED));
			GlobalVars.ge.loadStage(new OptionMenu());			
		}
	}

	public ButtonOptionGameMenu(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

}
