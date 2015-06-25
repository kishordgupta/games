package com.rhymes.attackTheFortress.button;

import com.rhymes.attackTheFortress.AttackTheFortressLevel;
import com.rhymes.attackTheFortress.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;

public class ButtonCloseModeSelecView extends Button {

	public ButtonCloseModeSelecView(float x, float y, float width,
			float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonCloseModeSelecView(float x, float y, float width,
			float height, int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onEvent(Point p) {
		
		if(this.checkHit(p)){
			((RoundSelectView)GlobalVars.ge.getCurrentStage()).getModeSelectView().Destroy();
		}
	}

}
