package com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.button;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class LeftHandedButton extends Button {
	private boolean active;
	String imagePath;
	
	public LeftHandedButton(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

		this.imagePath = imagePath;

	}
	
	
	public String setImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstants.left_on;
		
		else
			this.imagePath = AssetConstants.left_off;
		
		return imagePath;
		
	}
	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
//			Helper.println("lefhanded button pressed");

			if(!isActive()){
				setActive(true);
//				((Deck)GlobalVars.ge.getCurrentStage()).last_touched_button = this;
				
				((Deck)GlobalVars.ge.getCurrentStage()).init_deck(true);
				((Deck)GlobalVars.ge.getCurrentStage()).reArrange_cards();	
				
				((Deck)GlobalVars.ge.getCurrentStage()).adjust_stack_height_for_left();
				
				((Deck)GlobalVars.ge.getCurrentStage()).reArrange_deck_cards_for_draw_3_new(((Deck)GlobalVars.ge.getCurrentStage()).draw_3card);
				
				this.setImage(Helper.getImageFromAssets(setImagePath(true)));

			}
			
			else{
				setActive(false);
				
				((Deck)GlobalVars.ge.getCurrentStage()).init_deck(false);
				((Deck)GlobalVars.ge.getCurrentStage()).reArrange_cards();	
				
				((Deck)GlobalVars.ge.getCurrentStage()).adjust_stack_height_for_left();

				((Deck)GlobalVars.ge.getCurrentStage()).reArrange_deck_cards_for_draw_3_new(((Deck)GlobalVars.ge.getCurrentStage()).draw_3card);
				
				this.setImage(Helper.getImageFromAssets(setImagePath(false)));
			}
		}
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

}
