package com.rhymes.attackTheFortress.button;

import com.rhymes.game.data.AssetConstants;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.result.Result;
import com.rhymes.game.stage.result.ResultBTRMAP;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonPlay extends Button {

	
	public ButtonPlay(float x, float y, float width, float height,
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
			Helper.println("Starting game...");
//			GlobalVars.ge.loadStage(new TestTileLevel());
			GlobalVars.ge.loadStage(new RoundSelectView());
		}
	}

	public ButtonPlay(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
