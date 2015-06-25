package com.rhymes.game.fruitdelivery.menu.selectvehicle.res;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.fruitdelivery.menu.selectvehicle.SelectVehicleMenuFruit;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class SelectButtonFruit extends Button{
	String imagePath_1, imagePath_2;
	private boolean active;
	
	public ButtonGroupFruit groupTagFruit;
	public SpecificButtonTagFruit specificTagFruit;
	
	public static enum ButtonGroupFruit{
		vehicle
	}
	
	public static enum SpecificButtonTagFruit{
		vehicle_1,
		vehicle_2,
		vehicle_3
	}

	public SelectButtonFruit(float x, float y, float width, float height,
			int zIndex, String imagePath_1, String imagePath_2, ButtonGroupFruit group, SpecificButtonTagFruit tag) {
		super(x, y, width, height, zIndex, imagePath_1);
		
		this.imagePath_1 = imagePath_1;
		this.imagePath_2 = imagePath_2;
		
		this.groupTagFruit = group;
		this.specificTagFruit = tag;
		
		setActive(false);
	}
	
	public void consumeTouch(){
		if(this.groupTagFruit == ButtonGroupFruit.vehicle){
			((SelectVehicleMenuFruit)GlobalVars.ge.getCurrentStage()).consumeTouchVehicle();

		}
	}
	
	
	public void selectedButtonValue(){
		if(this.groupTagFruit == ButtonGroupFruit.vehicle){
			SelectVehicleMenuFruit.selected_vehicle_button = this;
			
		}
	}
	
	public String setSpecifiedImagePath(boolean active){
		String path = "";
		
		if(active)
			path = imagePath_2;
		else
			path = imagePath_1;
		
		return path;
	}
	
	@Override
	public void onEvent(Point hitPoint) {
		if(checkHit(hitPoint)){
			
			consumeTouch();
			selectedButtonValue();
			
			if(!isActive()){
				setActive(true);
			}
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		this.setImage(Helper.getImageFromAssets(setSpecifiedImagePath(active)));
	}

}
