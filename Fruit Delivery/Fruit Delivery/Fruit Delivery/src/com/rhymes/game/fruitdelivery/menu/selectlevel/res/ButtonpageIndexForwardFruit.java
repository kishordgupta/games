package com.rhymes.game.fruitdelivery.menu.selectlevel.res;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.fruitdelivery.menu.selectlevel.LevelMenuFruit;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonpageIndexForwardFruit extends Button {

	public int curPage=0;
	public int totalPage=0;
	
	public ButtonpageIndexForwardFruit(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonpageIndexForwardFruit(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		// TODO Auto-generated constructor stub
	}
	public void onEvent(Point p) {
		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
//			this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_BTN_BTNSTART_DOWN));
			Helper.println("Button NewGame Hit...");

			curPage = ((LevelMenuFruit)GlobalVars.ge.getCurrentStage()).pageIndex;
			totalPage = ((LevelMenuFruit)GlobalVars.ge.getCurrentStage()).tpageNumber;
			if(curPage<totalPage-1)
				curPage++;
			GlobalVars.ge.loadStageWithoutLoadingScreen(new LevelMenuFruit(curPage));
		}
	}

	

}
