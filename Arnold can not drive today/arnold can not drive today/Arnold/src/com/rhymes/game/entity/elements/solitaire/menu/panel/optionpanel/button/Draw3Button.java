package com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.button;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class Draw3Button extends Button{
	private boolean active;
	String imagePath;
	
	public Draw3Button(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

		this.imagePath = imagePath;
	}
	
	public String setImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstants.draw_3_on;
		
		else
			this.imagePath = AssetConstants.draw_3_off;
		
		return imagePath;
		
	}
	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
//			Helper.println("draw3 button pressed");

			if(!isActive()){
				setActive(true);
//				((Deck)GlobalVars.ge.getCurrentStage()).last_touched_button = this;
				
				((Deck)GlobalVars.ge.getCurrentStage()).draw_3card = true;
				((Deck)GlobalVars.ge.getCurrentStage()).reArrange_deck_cards_for_draw_3_new(((Deck)GlobalVars.ge.getCurrentStage()).draw_3card);
				
				this.setImage(Helper.getImageFromAssets(setImagePath(true)));

			}
			
			else{
				setActive(false);
				
				((Deck)GlobalVars.ge.getCurrentStage()).draw_3card = false;
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
