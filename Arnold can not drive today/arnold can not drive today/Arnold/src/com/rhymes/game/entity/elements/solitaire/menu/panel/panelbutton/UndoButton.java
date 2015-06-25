package com.rhymes.game.entity.elements.solitaire.menu.panel.panelbutton;

import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class UndoButton extends Button{
	
	String imagePath;
	
	public UndoButton(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	
		this.imagePath = imagePath;
	}

	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			if(((Deck) GlobalVars.ge.getCurrentStage()).game_status.getSource_stack() != null &&
					((Deck) GlobalVars.ge.getCurrentStage()).game_status.getDestination_stack() != null)
			((Deck) GlobalVars.ge.getCurrentStage()).undo();
			
//			if(((Deck) GlobalVars.ge.getCurrentStage()).lastTouched == null)
//				Helper.println("action table null");
//			else
//				Helper.println("action table not null");
			
			Helper.println("undo button pressed");
//			Stack.action_table.undo();
		}
	}
}
