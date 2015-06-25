package com.rhymes.game.stage.menus.stick;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonBackward extends Button {

	public ButtonBackward(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonBackward(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

	}
	@Override
	public void onEvent(Point p) {
		
		if(this.checkHit(p)){

			Helper.println("Button backward Hit...");
//			((InstructionsMenu)GlobalVars.ge.getCurrentStage()).instruction.setImage(2);
			((InstructionsMenu)GlobalVars.ge.getCurrentStage()).switchInstruction(false);
			
		}
	}
}
