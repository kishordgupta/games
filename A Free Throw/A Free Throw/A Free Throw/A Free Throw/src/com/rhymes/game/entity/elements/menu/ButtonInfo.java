package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.menus.ChoosePlayerandBall;
import com.rhymes.game.stage.menus.Instructions;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonInfo extends Button {

	boolean isTouched = false;
	private boolean isInfoBack;
	/**
	 * @return the isInfoBack
	 */
	public boolean isInfoBack() {
		return isInfoBack;
	}

	/**
	 * @param isInfoBack the isInfoBack to set
	 */
	public void setInfoBack(boolean isInfoBack) {
		this.isInfoBack = isInfoBack;
	}

	@Override
	public void render() {
//		if(isTouched){
//			GlobalVars.ge.getScreen().getBatch().setColor(0, 1, 0, 1f);
//		}
		super.render();
//		GlobalVars.ge.getScreen().getBatch().setColor(1, 1, 1, 1f);
	}

	public ButtonInfo(float x, float y, float width, float height,
			int zIndex, String imagePath, boolean b) {
		super(x, y, width, height, zIndex, imagePath);
		this.isInfoBack = b;
	}
	
	@Override
	public void onTouch(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
//			if(isInfoBack==true)
//				((ChoosePlayerandBall)GlobalVars.ge.getCurrentStage()).removeElement(((ChoosePlayerandBall)GlobalVars.ge.getCurrentStage()).buttonInfoBack);

			((ChoosePlayerandBall)GlobalVars.ge.getCurrentStage()).showPlayerInfo();
			Helper.println("show player info");
			if(isTouched != false)
			{
				this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_INFO));
				isTouched = false;
			}
			else if(isTouched == false)
				{
				this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_INFO_PRESSED));
				
				isTouched = true;
				}
						
		}
	}

	public ButtonInfo(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}


}
