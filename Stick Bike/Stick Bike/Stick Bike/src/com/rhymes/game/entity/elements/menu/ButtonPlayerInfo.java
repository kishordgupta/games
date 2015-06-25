package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.menus.ChooseLocation;
import com.rhymes.game.stage.menus.ChoosePlayerandBall;
import com.rhymes.game.stage.menus.OptionMenu;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonPlayerInfo extends Button{
	boolean isTouched = false;
	@Override
	public void render() {
		if(isTouched){
			GlobalVars.ge.getScreen().getBatch().setColor(0, 1, 0, 1f);
		}
		super.render();
		GlobalVars.ge.getScreen().getBatch().setColor(1, 1, 1, 1f);
	}

	public ButtonPlayerInfo(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}
	
	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			isTouched = true;
			Helper.println("go to chooseLevel");
//			this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_OPTIONS_PRESSED));
//			GlobalVars.ge.loadStage(new PlayerInfo());			
		}
	}

	public ButtonPlayerInfo(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

}
