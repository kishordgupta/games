package com.rhymes.game.entity.elements.arnold.stage.menu.ui;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.arnold.stage.StageArnold;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class SensorButtonArnold extends Button{

	String imagePath;
	private boolean active;

	
	public SensorButtonArnold(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

		this.imagePath = imagePath;
	}
	
	
	public String getImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstants.sensor_button_arnold_on;
		
		else
			this.imagePath = AssetConstants.sensor_button_arnold_off;
		
		return imagePath;
		
	}
	
	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			
			if(!isActive()){
				setActive(true);
			}
			else{
				setActive(false);
			}
		}
	}

	public void setActive(boolean active) {
		this.active = active;
		Helper.println("Active: " + active);
		if(active){
			this.setImage(Helper.getImageFromAssets(getImagePath(active)));
			StageArnold.is_sensor_active = active;
//			((StageArnold)GlobalVars.ge.getCurrentStage()).addInputStatus(this.x + this.width , this.y + this.height/4f, "Sensor input activated.");
		}
		else if(!active){
			this.setImage(Helper.getImageFromAssets(getImagePath(active)));
			StageArnold.is_sensor_active = active;
//			((StageArnold)GlobalVars.ge.getCurrentStage()).addInputStatus(this.x + this.width , this.y+ this.height/4f, "Touch input activated.");
		}
	}

	public boolean isActive() {
		return active;
	}

}
