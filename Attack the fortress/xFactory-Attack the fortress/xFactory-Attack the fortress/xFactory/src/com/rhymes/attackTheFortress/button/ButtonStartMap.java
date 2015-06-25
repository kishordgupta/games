package com.rhymes.attackTheFortress.button;

//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.attackTheFortress.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.levels.TestTileLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonStartMap extends Button {
	private int round=1;

	public ButtonStartMap(float x, float y, float width,
			float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonStartMap(float x, float y, float width,
			float height, int zIndex, String imagePath, int k) {
		super(x, y, width, height, zIndex, imagePath);
		this.round=k;
	}

	@Override
	public void onEvent(Point p) {

		if(this.checkHitPrev(p)){
			Helper.println("Button Restart Hit : " + p.toString());
			Helper.println("Level Number : " + round);
			LevelInfo.ROUND_NUMBER=round;
			ModeSelectView mode=new ModeSelectView();
			((RoundSelectView)GlobalVars.ge.getCurrentStage()).addElement(mode);
			((RoundSelectView)GlobalVars.ge.getCurrentStage()).setModeSelectView(mode);
		}
	}


}
