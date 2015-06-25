package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.menus.ChoosePlayerandBall;
import com.rhymes.game.stage.menus.Instructions;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonInfoBack extends Button {

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

	public ButtonInfoBack(float x, float y, float width, float height,
			int zIndex, String imagePath, boolean b) {
		super(x, y, width, height, zIndex, imagePath);
		this.isInfoBack = b;
//		((ChoosePlayerandBall)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(this, InteractionTouch.class);
	}
	
	@Override
	public void onTouch(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
//			if(isInfoBack==true)
//				((ChoosePlayerandBall)GlobalVars.ge.getCurrentStage()).postDestroyed(buttonInfoBack);

//			((ChoosePlayerandBall)GlobalVars.ge.getCurrentStage()).removeElement(((ChoosePlayerandBall)GlobalVars.ge.getCurrentStage()).buttonInfoBack);
			GlobalVars.ge.loadStage(new ChoosePlayerandBall());
	
						
		}
	}

	/* (non-Javadoc)
	 * @see com.rhymes.game.entity.elements.ui.Button#checkHit(com.rhymes.ge.core.renderer.Point)
	 */
	@Override
	protected boolean checkHit(Point p) {
		// TODO Auto-generated method stub
//		return super.checkHit(p);
		if(this.getLeft()-10 <= p.x && this.getRight()-10>= p.x && this.getTop()-10 >= p.y && this.getBottom()-10 <= p.y)
			return true;
		return false;
	}

	public ButtonInfoBack(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}


}
