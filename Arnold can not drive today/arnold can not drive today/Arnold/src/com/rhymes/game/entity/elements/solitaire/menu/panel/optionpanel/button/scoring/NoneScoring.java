package com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.button.scoring;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class NoneScoring extends Button{
	private boolean active;
	String imagePath;
	
	public NoneScoring(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;
	}
	
	public String setImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstants.score_none_on;
		
		else
			this.imagePath = AssetConstants.score_none_off;
		
		return imagePath;
		
	}
	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			if(!isActive()){
				setActive(true);
//				((Deck)GlobalVars.ge.getCurrentStage()).last_touched_button = this;
				
//				((Deck)GlobalVars.ge.getCurrentStage()).standard_scoring_button.setActive(false);
//				((Deck)GlobalVars.ge.getCurrentStage()).vegas_scoring_button.setActive(false);
				
				this.setImage(Helper.getImageFromAssets(setImagePath(true)));

			}
			
			else{
				setActive(false);
				
				this.setImage(Helper.getImageFromAssets(setImagePath(false)));

			}
		}
	}

	public void setActive(boolean active) {
		this.active = active;
		
		if(active){
			this.setImage(Helper.getImageFromAssets(setImagePath(true)));
			((Deck)GlobalVars.ge.getCurrentStage()).standard_scoring_button.setActive(false);
			((Deck)GlobalVars.ge.getCurrentStage()).vegas_scoring_button.setActive(false);
		}
		else{
			this.setImage(Helper.getImageFromAssets(setImagePath(false)));
		
		}
	}

	public boolean isActive() {
		return active;
	}

}
