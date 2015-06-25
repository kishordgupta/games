package com.rhymes.game.entity.elements.solitaire.menu.panel.panelbutton;

import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class OptionsButton extends Button {
	
	private boolean active;
	String imagePath;

	public OptionsButton(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;
	}

	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			if(!isActive()){
				setActive(true);
				
//				Helper.println("option button pressed");
				
//				((Deck)GlobalVars.ge.getCurrentStage()).last_touched_button = this;
				
				((Deck)GlobalVars.ge.getCurrentStage()).unsubscribe_menu_button_interaction();
				
				((Deck)GlobalVars.ge.getCurrentStage()).optionPanelActive();
			}
			
			else{
				setActive(false);
				
				((Deck)GlobalVars.ge.getCurrentStage()).subscribe_menu_button_interaction();

				((Deck)GlobalVars.ge.getCurrentStage()).optionPanelInactive();
			}
		}
	}


	public void setActive(boolean active) {
		this.active = active;
		
//		if(active){
//			((Deck)GlobalVars.ge.getCurrentStage()).unsubscribe_menu_button_interaction();
//			
//			((Deck)GlobalVars.ge.getCurrentStage()).optionPanelActive();
//		}
//		
//		else{
//			((Deck)GlobalVars.ge.getCurrentStage()).subscribe_menu_button_interaction();
//
//			((Deck)GlobalVars.ge.getCurrentStage()).optionPanelInactive();
//		}
	}


	public boolean isActive() {
		return active;
	}
}
