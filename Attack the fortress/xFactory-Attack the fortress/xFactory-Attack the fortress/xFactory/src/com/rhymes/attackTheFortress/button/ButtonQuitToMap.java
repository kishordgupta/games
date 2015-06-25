package com.rhymes.attackTheFortress.button;

//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.levels.TestTileLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonQuitToMap extends Button {

	public ButtonQuitToMap(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonQuitToMap(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onEvent(Point p) {
		if(this.checkHit(p)){
			Helper.println("Exit Level...");
//			TestTileLevel.gameMode=1;
			GlobalVars.ge.loadStage(new RoundSelectView());
		}
	}


}
