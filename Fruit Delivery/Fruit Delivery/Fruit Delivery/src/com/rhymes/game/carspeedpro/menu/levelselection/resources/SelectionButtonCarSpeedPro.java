package com.rhymes.game.carspeedpro.menu.levelselection.resources;

import com.rhymes.game.carspeedpro.menu.levelselection.SelectLevelMenuCarSpeedPro;
import com.rhymes.game.carspeedpro.menu.vehicleselection.SelectVehicleMenuCarSpeedPro;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class SelectionButtonCarSpeedPro extends Button{
	
	String imagepath_1, imagepath_2;
	private boolean active;
	
	public ButtonGroup group_tag;
	public ButtonSpecific_tag specific_tag;
	
	public static enum ButtonGroup{
		level_number,
		level_mode,
		color,
		vehicle
	}
	
	public static enum ButtonSpecific_tag{
		level_0,
		level_1,
		level_2,
		level_3,
		level_4,
		
		city,
		desert,
		tropic,
		
		clr_0,
		clr_1,
		clr_2,
		clr_3,
		clr_4,
		
		benga,
		huwwer,
		krac,
		military,
		police,
		school
	}

	
	public SelectionButtonCarSpeedPro(float x, float y, float width,
			float height, int zIndex, String imagePath1, String imagePath2, ButtonGroup group_type, ButtonSpecific_tag specific_tag) {
		super(x, y, width, height, zIndex, imagePath1);
		
		this.imagepath_1 = imagePath1;
		this.imagepath_2 = imagePath2;
		
		this.group_tag = group_type;
		this.specific_tag = specific_tag;
		
		setActive(false);
	}
	
	public String setSpecifiedImagePath(boolean active){
		String path = "";
		
		if(active)
			path = imagepath_2;
		else
			path = imagepath_1;
		
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

	
	public void consumeTouch(){
		if(this.group_tag == ButtonGroup.color){
			((SelectVehicleMenuCarSpeedPro)GlobalVars.ge.getCurrentStage()).consumeTouchColor();
		
		}
		else if(this.group_tag == ButtonGroup.vehicle){
			((SelectVehicleMenuCarSpeedPro)GlobalVars.ge.getCurrentStage()).consumeTouchVehicle();
		
		}
		else if(this.group_tag == ButtonGroup.level_mode){
			((SelectLevelMenuCarSpeedPro)GlobalVars.ge.getCurrentStage()).consumeTouchLevelMode();
	
		}
		else if(this.group_tag == ButtonGroup.level_number){
			((SelectLevelMenuCarSpeedPro)GlobalVars.ge.getCurrentStage()).consumeTouchLevelNumber();
	
		}
	}
	
	
	public void selectedButtonValue(){
		if(this.group_tag == ButtonGroup.color){
			SelectVehicleMenuCarSpeedPro.selected_color_button = this;
			
		}
		else if(this.group_tag == ButtonGroup.vehicle){
			SelectVehicleMenuCarSpeedPro.selected_vehicle_button = this;
		
		}
		else if(this.group_tag == ButtonGroup.level_mode){
			SelectLevelMenuCarSpeedPro.selected_level_mode_button = this;
		
		}
		else if(this.group_tag == ButtonGroup.level_number){
			SelectLevelMenuCarSpeedPro.selected_level_number_button = this;
		
		}
	}
	
	
	public void controlVisualSelecetion(){
		if(this.group_tag == ButtonGroup.color){
			if(SelectVehicleMenuCarSpeedPro.selected_color_button == this){
//				this.setImage(Helper.getImageFromAssets(setSpecifiedImagePath(false)));
				setActive(true);
			}
			
//			else{
//				this.setImage(Helper.getImageFromAssets(setSpecifiedImagePath(false)));
//
//			}
		}
		else if(this.group_tag == ButtonGroup.vehicle){
			if(SelectVehicleMenuCarSpeedPro.selected_vehicle_button == this){
//				this.setImage(Helper.getImageFromAssets(setSpecifiedImagePath(false)));
				setActive(true);
			}
			
//			else{
//				this.setImage(Helper.getImageFromAssets(setSpecifiedImagePath(false)));
//
//			}		
		}
		else if(this.group_tag == ButtonGroup.level_mode){
			if(SelectLevelMenuCarSpeedPro.selected_level_mode_button == this){
//				this.setImage(Helper.getImageFromAssets(setSpecifiedImagePath(false)));
				setActive(true);		
			}
			
//			else{
//				this.setImage(Helper.getImageFromAssets(setSpecifiedImagePath(false)));
//
//			}
		}
		else if(this.group_tag == ButtonGroup.level_number){
			if(SelectLevelMenuCarSpeedPro.selected_level_number_button == this){
//				this.setImage(Helper.getImageFromAssets(setSpecifiedImagePath(false)));
				setActive(true);
			}
			
//			else{
//				this.setImage(Helper.getImageFromAssets(setSpecifiedImagePath(false)));
//
//			}
		}
	}
	
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		Helper.println("balsal btn");
		this.setImage(Helper.getImageFromAssets(setSpecifiedImagePath(active)));
	}
}
