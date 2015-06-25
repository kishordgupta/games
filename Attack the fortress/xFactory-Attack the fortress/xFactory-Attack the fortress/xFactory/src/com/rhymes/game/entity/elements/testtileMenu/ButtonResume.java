package com.rhymes.game.entity.elements.testtileMenu;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.testtile.scorelbl;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.levels.TestTileLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonResume extends Button {

	public ButtonResume(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonResume(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onEvent(Point p) {
		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-240f*LevelInfo.ratioX;
		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-160f*LevelInfo.ratioY;
		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
//			this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_BTN_BTNSTART_DOWN));
			Helper.println("resume game...");			
			GlobalVars.ge.getCurrentStage().resume();
			
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(this);
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(((TestTileLevel)GlobalVars.ge.getCurrentStage()).getpouseScreen());
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(((TestTileLevel)GlobalVars.ge.getCurrentStage()).getButtonRestart());
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(((TestTileLevel)GlobalVars.ge.getCurrentStage()).getButtonExitLevel());
			
			
			
		}

}
}