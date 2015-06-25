package com.rhymes.game.bulletwar.menu.levels.res;

import com.rhymes.game.bulletwar.menu.levels.LevelMenuBullet;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonBackwardIndexBullet extends Button {
	public int curPage=0;
	public int totalPage=0;
	

	public ButtonBackwardIndexBullet(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);

	}

	public ButtonBackwardIndexBullet(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

	}
	
	public void onEvent(Point p) {
		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
//			this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_BTN_BTNSTART_DOWN));
			Helper.println("Button NewGame Hit...");
			curPage = ((LevelMenuBullet)GlobalVars.ge.getCurrentStage()).pageIndex;
			totalPage = ((LevelMenuBullet)GlobalVars.ge.getCurrentStage()).tpageNumber;
			if(curPage>0)
				curPage--;
			GlobalVars.ge.loadStageWithoutLoadingScreen(new LevelMenuBullet(curPage, LevelMenuBullet.packIndex));		
			
		}
	}
}
