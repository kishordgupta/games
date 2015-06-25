package com.rhymes.game.entity.elements.solitaire.menu.panel;

import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class CrossButton extends Button{

	private boolean active;
	String imagePath;
	
	public CrossButton(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

		
		this.imagePath = imagePath;
		
		setActive(false);
	}
	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
//			Helper.println("cross button pressed");

			if(!isActive()){
				setActive(true);
//				((Deck)GlobalVars.ge.getCurrentStage()).last_touched_button = this;
				
//				((Deck)GlobalVars.ge.getCurrentStage()).unsubscribe_menu_interaction();
//				((Deck)GlobalVars.ge.getCurrentStage()).subscribe_cards_interaction();
				
				((Deck)GlobalVars.ge.getCurrentStage()).inActiveAllMenu();
//				((Deck)GlobalVars.ge.getCurrentStage()).subscribe_menu_button_interaction();

			}
			
			else{
				setActive(false);
				
				((Deck)GlobalVars.ge.getCurrentStage()).subscribe_menu_button_interaction();

//				((Deck)GlobalVars.ge.getCurrentStage()).unsubscribe_cards_interaction();
//				((Deck)GlobalVars.ge.getCurrentStage()).subscribe_menu_interaction();
			}
		}
	}

	public void setActive(boolean active) {
		this.active = active;
		
		if(active){
//			((Deck)GlobalVars.ge.getCurrentStage()).inActiveAllMenu();
			
			((Deck)GlobalVars.ge.getCurrentStage()).unsubscribe_menu_interaction();
			((Deck)GlobalVars.ge.getCurrentStage()).subscribe_cards_interaction();
			
//			((Deck)GlobalVars.ge.getCurrentStage()).inActiveAllMenu();
			
		}
		
		else{
			///consume hint
			((Deck)GlobalVars.ge.getCurrentStage()).hint_button.consume_hint();
		}
		
//		else{
//			((Deck)GlobalVars.ge.getCurrentStage()).subscribe_menu_button_interaction();
//
//		}
	}

	public boolean isActive() {
		return active;
	}

}
