package com.rhymes.game.stage.menus.stick;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonNewGame extends Button {

	
	public ButtonNewGame(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}
	
	
//	int levelNumber = 1;
//	Result res = new ResultBTRMAP();
//	Result res = new ResultBTRClassic();
//	Result res = new ResultBTRTime();
	@Override
	public void onEvent(Point p) {
		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
//			this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_BTN_BTNSTART_DOWN));
			Helper.println("Button NewGame Hit...");
//			GlobalVars.ge.loadStage(new TestTileLevel());
			GlobalVars.ge.loadStage(new LevelMenu(0));
		}
	}

	public ButtonNewGame(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
