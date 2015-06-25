package com.rhymes.game.stage.menus.stick;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonForward extends Button {

	public ButtonForward(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonForward(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onEvent(Point p) {
		
		if(this.checkHit(p)){
	
			Helper.println("Button forward Hit...");
//			((InstructionsMenu)GlobalVars.ge.getCurrentStage()).instruction.setImage(1);
			((InstructionsMenu)GlobalVars.ge.getCurrentStage()).switchInstruction(true);
			
		}
	}
}
