package com.rhymes.game.entity.elements.solitaire.menu;

import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class MenuPanelButton extends Button {
	
	private boolean active;
	String imagePath;

	public MenuPanelButton(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;
		
		setActive(false);
	}
	
	int i, j;
	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			if(!isActive()){
				setActive(true);
				
//				Helper.println("menu button pressed");

				
//				((Deck)GlobalVars.ge.getCurrentStage()).last_touched_button = this;
				
				((Deck)GlobalVars.ge.getCurrentStage()).unsubscribe_cards_interaction();
				((Deck)GlobalVars.ge.getCurrentStage()).subscribe_menu_interaction();
				
				((Deck)GlobalVars.ge.getCurrentStage()).menuActive();
			}
			
			else{
				setActive(false);
				
				((Deck)GlobalVars.ge.getCurrentStage()).unsubscribe_menu_interaction();
				((Deck)GlobalVars.ge.getCurrentStage()).subscribe_cards_interaction();
				
				((Deck)GlobalVars.ge.getCurrentStage()).menuInactive();
				
				///consume hint
				((Deck)GlobalVars.ge.getCurrentStage()).hint_button.consume_hint();

			}
		}
	}

	public void setActive(boolean active) {
		this.active = active;
		
//		if(active){
//			((Deck)GlobalVars.ge.getCurrentStage()).menuActive();
//			
//			((Deck)GlobalVars.ge.getCurrentStage()).unsubscribe_cards_interaction();
//			((Deck)GlobalVars.ge.getCurrentStage()).subscribe_menu_interaction();
//		}
//		else{
//			((Deck)GlobalVars.ge.getCurrentStage()).menuInactive();
//			
//			((Deck)GlobalVars.ge.getCurrentStage()).unsubscribe_menu_interaction();
//			((Deck)GlobalVars.ge.getCurrentStage()).subscribe_cards_interaction();
//		}

	}

	public boolean isActive() {
		return active;
	}

}
