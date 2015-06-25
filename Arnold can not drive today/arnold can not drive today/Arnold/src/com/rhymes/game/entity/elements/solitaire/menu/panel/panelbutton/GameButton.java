package com.rhymes.game.entity.elements.solitaire.menu.panel.panelbutton;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;

public class GameButton extends Button{
	
	String imagePath;
	private boolean active;

	public GameButton(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;
	}

	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			if(!isActive()){
				setActive(true);
//				((Deck)GlobalVars.ge.getCurrentStage()).last_touched_button = this;
			}
			
			else{
				setActive(false);
			}
		}
		
	}


	public void setActive(boolean active) {
		this.active = active;
		
//		if(active){
//			((Deck)GlobalVars.ge.getCurrentStage()).unsubscribe_cards_interaction();
//			((Deck)GlobalVars.ge.getCurrentStage()).subscribe_menu_interaction();
//		}
//		
//		else{
//			((Deck)GlobalVars.ge.getCurrentStage()).unsubscribe_menu_interaction();
//			((Deck)GlobalVars.ge.getCurrentStage()).subscribe_cards_interaction();
//		}
	}


	public boolean isActive() {
		return active;
	}
}
