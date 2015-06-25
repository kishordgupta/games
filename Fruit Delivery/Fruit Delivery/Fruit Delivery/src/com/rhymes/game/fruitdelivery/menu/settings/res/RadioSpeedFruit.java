package com.rhymes.game.fruitdelivery.menu.settings.res;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.fruitdelivery.AssetConstantsFruit;
import com.rhymes.game.fruitdelivery.menu.settings.SettingsMenuFruit;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class RadioSpeedFruit extends Button {

	String imagePath;
	private boolean active;
	private int gameMode;
	public RadioSpeedFruit(float x, float y, float width, float height, int zIndex,
			String imagePath, int gameMode) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;
		this.gameMode = gameMode;

	}

	public String setSpecifiedImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstantsFruit.sel_image;
		else
			this.imagePath = AssetConstantsFruit.sel_plexi_image;
		
		return imagePath;
	}

	@Override
	public void onEvent(Point htiPoint) {
		if(checkHit(htiPoint)){
			///work to do
//			((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).consumeTouchRadio();
//			selected_value();

//			setActive(true);
		}
	}
	
	public void selected_value(){
		if(this == ((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).radio[0]){
			((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).speed_slow.setActive(true);
		}
		
		else if(this == ((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).radio[1]){
			((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).speed_normal.setActive(true);
		}
		
		else if(this == ((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).radio[2]){
			((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).speed_fast.setActive(true);
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		((SettingsMenuFruit)GlobalVars.ge.getCurrentStage()).set_image_speed();
		this.setImage(Helper.getImageFromAssets(setSpecifiedImagePath(active)));
		if(this.active){
			BikeLevel.gameMode = gameMode;
			Helper.println("Game mode: " + gameMode);
		}
	}

}
