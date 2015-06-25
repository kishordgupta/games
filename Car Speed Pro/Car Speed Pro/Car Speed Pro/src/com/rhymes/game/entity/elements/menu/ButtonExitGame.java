package com.rhymes.game.entity.elements.menu;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonExitGame extends Button{

	private boolean isSystemExit;

	public ButtonExitGame(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}
	public ButtonExitGame(float x, float y,float width, float height,
			int zIndex,String imagePath, boolean isSystemExit) {
		// TODO Auto-generated constructor stub
		super(x, y, width, height, zIndex, imagePath);
		this.isSystemExit = isSystemExit;
	}

	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("Exit game...");
			this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_EXIT_PRESSED));
//			GlobalVars.ge.loadStage(new XLevel());	
			if(isSystemExit)
				Gdx.app.exit();
		}
	}

	public ButtonExitGame(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}


}
