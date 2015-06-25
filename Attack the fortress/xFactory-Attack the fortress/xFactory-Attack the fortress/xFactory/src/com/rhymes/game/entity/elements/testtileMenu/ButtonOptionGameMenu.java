package com.rhymes.game.entity.elements.testtileMenu;

import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonOptionGameMenu extends Button{

	public ButtonOptionGameMenu(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}
	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-240f*LevelInfo.ratioX;
		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-160f*LevelInfo.ratioY;

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
