package com.rhymes.game.fruitdelivery.menu.settings.res;

import com.rhymes.game.carspeedpro.BackGroundUniversal;
import com.rhymes.game.fruitdelivery.menu.settings.SettingsMenuFruit;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;

public class SpeedImageButton extends BackGroundUniversal{


	String imagePath;
	speedTypeFruit speed_type;
	private boolean active;
	
	public static enum speedTypeFruit{
		speed_slow,
		speed_normal,
		speed_fast
	}
	
	public SpeedImageButton(float x, float y, float width, float height, int zIndex,
			String imagePath, speedTypeFruit speed_type) {
		super(x, y, width, height, zIndex, imagePath);

		this.imagePath = imagePath;
		
		this.speed_type = speed_type;
	}

	@Override
	public void onEvent(Point hitPoint) {
		if(this.checkHit(hitPoint)){
			consumeTouch();
			selectedValue();
			setActive(true);
			
		}
	}
	
	
	public void consumeTouch(){
		((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).consumeTouchSpeed();

	}

	
	public void selectedValue(){
		if(this.speed_type == speedTypeFruit.speed_slow){
			((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).radio[0].setActive(true);
			((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).radio[2].setActive(false);
			((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).radio[1].setActive(false);

		}
		
		else if(this.speed_type == speedTypeFruit.speed_normal){
			((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).radio[1].setActive(true);
			((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).radio[0].setActive(false);
			((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).radio[2].setActive(false);

		}
		
		else if(this.speed_type == speedTypeFruit.speed_fast){
			((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).radio[2].setActive(true);
			((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).radio[0].setActive(false);
			((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).radio[1].setActive(false);

		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
