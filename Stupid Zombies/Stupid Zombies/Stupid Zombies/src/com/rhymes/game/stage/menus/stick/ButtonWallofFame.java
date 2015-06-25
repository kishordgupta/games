package com.rhymes.game.stage.menus.stick;

//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonWallofFame extends Button{

	public ButtonWallofFame(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}
	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
						Helper.println("go to option menu...");
//			this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_OPTIONS_PRESSED));
//			WallOfFame wall=new WallOfFame();
//			((MainView)GlobalVars.ge.getCurrentStage()).addElement(wall);
		}
	}

	public ButtonWallofFame(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

}
