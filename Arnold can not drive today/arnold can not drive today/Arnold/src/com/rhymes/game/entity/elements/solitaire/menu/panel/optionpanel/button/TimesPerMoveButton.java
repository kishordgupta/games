package com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.button;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.solitaire.ConfigConstants;
import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class TimesPerMoveButton extends Button{

	private boolean active;
	String imagePath;
	
	public TimesPerMoveButton(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

	}
	
	public String setImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstants.time_on;
		
		else
			this.imagePath = AssetConstants.time_off;
		
		return imagePath;
		
	}
	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
//			Helper.println("time/move button pressed");

			if(!isActive()){
//				setActive(true);
//				((Deck)GlobalVars.ge.getCurrentStage()).last_touched_button = this;
				
				set_time();
			}
			
			else{
				setActive(false);
				
				unset_time();
			}
		}
	}
	
	
	public void set_time(){
		setActive(true);
		
		this.setImage(Helper.getImageFromAssets(setImagePath(true)));
		((Deck)GlobalVars.ge.getCurrentStage()).score_time.setY(Gdx.graphics.getHeight() - (60 * ConfigConstants.ratio_h));
	}

	public void unset_time(){
		setActive(false);

		this.setImage(Helper.getImageFromAssets(setImagePath(false)));
		((Deck)GlobalVars.ge.getCurrentStage()).score_time.setY(- Gdx.graphics.getHeight());
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

}
