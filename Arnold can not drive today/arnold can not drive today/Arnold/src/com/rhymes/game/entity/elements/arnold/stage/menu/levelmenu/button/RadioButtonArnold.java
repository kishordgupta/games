package com.rhymes.game.entity.elements.arnold.stage.menu.levelmenu.button;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.arnold.stage.menu.levelmenu.LevelMenuArnold;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class RadioButtonArnold extends Button{

	private boolean active;
	String imagePath;
	
	private int button_id;
	
	
	public RadioButtonArnold(float x, float y, float width, float height,
			int zIndex, String imagePath, int button_id) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;
		this.setButton_id(button_id);

		setActive(false);

	}
	
	public String setImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstants.radio_button_arnold_on;
		
		else
			this.imagePath = AssetConstants.radio_button_arnold_off;
		
		return imagePath;
		
	}
	
	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit_radio(p)){
			
			Helper.println("radio "+button_id+" touched");
			
			if(!isActive()){
				setActive(true);

			}
			
//			else{
//				setActive(true);
//			}
		}
	}
	
	
	protected boolean checkHit_radio(Point p)
	{
		if(this.getLeft() - this.width/2f <= p.x && this.getRight() + this.width/2f >= p.x
				&&
				this.getTop() + this.height/2f >= p.y && this.getBottom() - this.height/2f <= p.y){
			
			set_pressed(true);
			return true;
		}
		return false;
	}


	public void setActive(boolean active) {
		if(active){
			((LevelMenuArnold)GlobalVars.ge.getCurrentStage()).clearRadioSelections();
			this.active = active;
			this.setImage(Helper.getImageFromAssets(setImagePath(true)));
			Helper.println("radio "+button_id+" activated");
		}
		
		else if(!active){
			this.setImage(Helper.getImageFromAssets(setImagePath(false)));
			this.active = active;
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setButton_id(int button_id) {
		this.button_id = button_id;
	}

	public int getButton_id() {
		return button_id;
	}

}
