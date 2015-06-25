package com.rhymes.attackTheFortress.button;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonCloseGame extends Button{

	public ButtonCloseGame(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}

	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("Exit game...");
			Gdx.app.exit();
//			System.exit(0);
		}
	}

	public ButtonCloseGame(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
