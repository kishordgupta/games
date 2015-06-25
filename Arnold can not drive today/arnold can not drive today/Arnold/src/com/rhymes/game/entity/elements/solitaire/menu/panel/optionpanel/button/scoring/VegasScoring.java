package com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.button.scoring;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.solitaire.ConfigConstants;
import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class VegasScoring extends Button{
	private boolean active;
	String imagePath;

	public VegasScoring(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

		this.imagePath = imagePath;
	}
	
	
	public String setImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstants.score_vegas_on;
		
		else
			this.imagePath = AssetConstants.score_vegas_off;
		
		return imagePath;
		
	}
	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			if(!isActive()){
				setActive(true);
//				((Deck)GlobalVars.ge.getCurrentStage()).last_touched_button = this;
				
//				((Deck)GlobalVars.ge.getCurrentStage()).score_vegas.setY(Gdx.graphics.getHeight() - (30 * ConfigConstants.ratio_h));
				
				this.setImage(Helper.getImageFromAssets(setImagePath(true)));
			}
			
			else{
				setActive(false);
				
//				((Deck)GlobalVars.ge.getCurrentStage()).score_vegas.setY(- Gdx.graphics.getHeight());
				
				this.setImage(Helper.getImageFromAssets(setImagePath(false)));
			}
		}
	}

	public void setActive(boolean active) {
		this.active = active;
		
		if(active){
			this.setImage(Helper.getImageFromAssets(setImagePath(true)));
			
//			if(!((Deck)GlobalVars.ge.getCurrentStage()).options_button.isActive())
				((Deck)GlobalVars.ge.getCurrentStage()).score_vegas.setY(Gdx.graphics.getHeight() - (60 * ConfigConstants.ratio_h));
				
				if(((Deck)GlobalVars.ge.getCurrentStage()).none_scoring_button.isActive()){
					((Deck)GlobalVars.ge.getCurrentStage()).none_scoring_button.setActive(false);
				}
		}
		else{
			this.setImage(Helper.getImageFromAssets(setImagePath(false)));
			((Deck)GlobalVars.ge.getCurrentStage()).score_vegas.setY(- Gdx.graphics.getHeight());
		}
	}

	public boolean isActive() {
		return active;
	}

}
