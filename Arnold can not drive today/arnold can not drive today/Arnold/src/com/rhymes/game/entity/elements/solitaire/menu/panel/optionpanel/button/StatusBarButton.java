package com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.button;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.solitaire.ConfigConstants;
import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class StatusBarButton extends Button {
	private boolean active;
	String imagePath;
	
	public StatusBarButton(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;
	}
	
	
	public String setImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstants.status_on;
		
		else
			this.imagePath = AssetConstants.status_off;
		
		return imagePath;
		
	}
	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
//			Helper.println("status button pressed");

			if(!isActive()){
				setActive(true);
//				((Deck)GlobalVars.ge.getCurrentStage()).last_touched_button = this;
				
				set_status();
				
			}
			
			else{
				setActive(false);
				
				unset_status();

			}
		}
	}
	
	public void set_status(){
		((Deck)GlobalVars.ge.getCurrentStage()).status_panel.
		setY((Gdx.graphics.getHeight() - ((ConfigConstants.menu_panel_height + 25f)* ConfigConstants.ratio_h)));
		
		((Deck)GlobalVars.ge.getCurrentStage()).score_standard.setY(Gdx.graphics.getHeight() - (60 * ConfigConstants.ratio_h));
		((Deck)GlobalVars.ge.getCurrentStage()).score_vegas.setY(Gdx.graphics.getHeight() - (60 * ConfigConstants.ratio_h));
		
		((Deck)GlobalVars.ge.getCurrentStage()).score_time.setY(Gdx.graphics.getHeight() - (60 * ConfigConstants.ratio_h));
		
		this.setImage(Helper.getImageFromAssets(setImagePath(true)));

	}
	
	public void unset_status(){
		((Deck)GlobalVars.ge.getCurrentStage()).status_panel.
		setY( - (Gdx.graphics.getHeight() - (ConfigConstants.menu_panel_height * ConfigConstants.ratio_h)));
		
		if(!((Deck)GlobalVars.ge.getCurrentStage()).standard_scoring_button.isActive())
			((Deck)GlobalVars.ge.getCurrentStage()).score_standard.setY(- Gdx.graphics.getHeight());
		
		if(!((Deck)GlobalVars.ge.getCurrentStage()).vegas_scoring_button.isActive())
			((Deck)GlobalVars.ge.getCurrentStage()).score_vegas.setY(- Gdx.graphics.getHeight());

		if(!((Deck)GlobalVars.ge.getCurrentStage()).time_per_move_button.isActive())
			((Deck)GlobalVars.ge.getCurrentStage()).score_time.setY(- Gdx.graphics.getHeight());
		
		this.setImage(Helper.getImageFromAssets(setImagePath(false)));

	}

	public void setActive(boolean active) {
		this.active = active;
		
//		if(active){
//			((Deck)GlobalVars.ge.getCurrentStage()).score_standard.setY(Gdx.graphics.getHeight() - (30 * ConfigConstants.ratio_h));
//			((Deck)GlobalVars.ge.getCurrentStage()).score_vegas.setY(Gdx.graphics.getHeight() - (30 * ConfigConstants.ratio_h));
//			
//			((Deck)GlobalVars.ge.getCurrentStage()).score_time.setY(Gdx.graphics.getHeight() - (30 * ConfigConstants.ratio_h));
//		}
//		
//		else{
//			((Deck)GlobalVars.ge.getCurrentStage()).score_standard.setY(- Gdx.graphics.getHeight());
//			((Deck)GlobalVars.ge.getCurrentStage()).score_vegas.setY(- Gdx.graphics.getHeight());
//			
//			((Deck)GlobalVars.ge.getCurrentStage()).score_time.setY(- Gdx.graphics.getHeight());
//		}
	}
	public boolean isActive() {
		return active;
	}
	
}
