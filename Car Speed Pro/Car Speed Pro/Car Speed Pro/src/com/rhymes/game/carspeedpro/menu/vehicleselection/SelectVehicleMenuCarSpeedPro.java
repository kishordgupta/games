package com.rhymes.game.carspeedpro.menu.vehicleselection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.carspeedpro.AssetConstantsCarSpeedPro;
import com.rhymes.game.carspeedpro.BackGroundUniversal;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.carspeedpro.menu.gameover.GameOverCarSpeedPro;
import com.rhymes.game.carspeedpro.menu.levelselection.SelectLevelMenuCarSpeedPro;
import com.rhymes.game.carspeedpro.menu.levelselection.resources.SelectionButtonCarSpeedPro;
import com.rhymes.game.carspeedpro.menu.levelselection.resources.SelectionButtonCarSpeedPro.ButtonGroup;
import com.rhymes.game.carspeedpro.menu.levelselection.resources.SelectionButtonCarSpeedPro.ButtonSpecific_tag;
import com.rhymes.game.data.Constants;
import com.rhymes.game.entity.elements.physical.Car;
import com.rhymes.game.entity.elements.ui.PhysicsBody2;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.game.stage.menus.stick.LevelInfo;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class SelectVehicleMenuCarSpeedPro extends StageBase{

	public static int number_of_vehicle = 6;
	public static int number_of_colors = 5;
	
	public static SelectionButtonCarSpeedPro selected_color_button = null;
	public static SelectionButtonCarSpeedPro selected_vehicle_button = null;
	
	public SelectionButtonCarSpeedPro[] color;
	public SelectionButtonCarSpeedPro[] vehicle;
	
	BackGroundUniversal bg;
	ButtonStageLoad okay, back;

	InteractionTouch interaction_touch;
	
	float x, y, gap;
	
	
	public SelectVehicleMenuCarSpeedPro(){
		color = new SelectionButtonCarSpeedPro[number_of_colors];
		vehicle = new SelectionButtonCarSpeedPro[number_of_vehicle];
		
	}
	
	
	@Override
	public void loadElements() {
		interaction_touch = new InteractionTouch();
		this.interactions.add(this.interaction_touch);
		
		set_select_menu();
		set_color_selected();
		set_vehicle_selected();

		if(selected_color_button != null && selected_vehicle_button != null)
			setVehicle(selected_vehicle_button.specific_tag, selected_color_button.specific_tag);
		
	}
	
	
	public void setVehicle(ButtonSpecific_tag vehicle, ButtonSpecific_tag color){
		
		Helper.println("\n\n\nSetting vehicle");
		if(vehicle == ButtonSpecific_tag.benga){
			car = addCarView(Constants.carTypeBenga);
			
			if(color == ButtonSpecific_tag.clr_0){
				car.setOverlayColor(1, 0, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_1){
				car.setOverlayColor(1, 1, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_2){
				car.setOverlayColor(0, 0, 1, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_3){
				car.setOverlayColor(0, 1, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_4){
				car.setOverlayColor(1, 1, 1, 1);

			}
		}
		
		
		else if(vehicle == ButtonSpecific_tag.huwwer){
			car = addCarView(Constants.carTypeHuwwer);
			
			if(color == ButtonSpecific_tag.clr_0){
				car.setOverlayColor(1, 0, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_1){
				car.setOverlayColor(1, 1, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_2){
				car.setOverlayColor(0, 0, 1, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_3){
				car.setOverlayColor(0, 1, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_4){
				car.setOverlayColor(1, 1, 1, 1);

			}
		}
		
		
		else if(vehicle == ButtonSpecific_tag.krac){
			car = addCarView(Constants.carTypeKrac);
			
			if(color == ButtonSpecific_tag.clr_0){
				car.setOverlayColor(1, 0, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_1){
				car.setOverlayColor(1, 1, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_2){
				car.setOverlayColor(0, 0, 1, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_3){
				car.setOverlayColor(0, 1, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_4){
				car.setOverlayColor(1, 1, 1, 1);

			}
		}
		
		else if(vehicle == ButtonSpecific_tag.military){
			car = addCarView(Constants.carTypeSuper);
			
			if(color == ButtonSpecific_tag.clr_0){
				car.setOverlayColor(1, 0, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_1){
				car.setOverlayColor(1, 1, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_2){
				car.setOverlayColor(0, 0, 1, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_3){
				car.setOverlayColor(0, 1, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_4){
				car.setOverlayColor(1, 1, 1, 1);

			}
		}
		
		else if(vehicle == ButtonSpecific_tag.police){
			car = addCarView(Constants.carTypePolice);
			
			if(color == ButtonSpecific_tag.clr_0){
				car.setOverlayColor(1, 0, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_1){
				car.setOverlayColor(1, 1, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_2){
				car.setOverlayColor(0, 0, 1, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_3){
				car.setOverlayColor(0, 1, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_4){
				car.setOverlayColor(1, 1, 1, 1);

			}
		}

		else if(vehicle == ButtonSpecific_tag.school){
			car = addCarView(Constants.carTypeSchoolbus);
			
			if(color == ButtonSpecific_tag.clr_0){
				car.setOverlayColor(1, 0, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_1){
				car.setOverlayColor(1, 1, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_2){
				car.setOverlayColor(0, 0, 1, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_3){
				car.setOverlayColor(0, 1, 0, 1);

			}
			
			else if(color == ButtonSpecific_tag.clr_4){
				car.setOverlayColor(1, 1, 1, 1);

			}
		}


	}
	
	public void removeCarElements(){
		for(i = 0; i < this.elements.size; i++){
			Helper.println("element: " + this.elements.get(i).getClass());
			if(this.elements.get(i) instanceof Car){
				this.elements.removeIndex(i);
				i--;
			}
			else if(this.elements.get(i) instanceof PhysicsBody2){
				this.elements.removeIndex(i);
				i--;
			}
		}
	}
	
	
	Car car;
	World world;
	private Car addCarView(String carType) {
		
		removeCarElements();
		
		if(world != null)
			world.dispose();
		x = Gdx.graphics.getWidth()/2f - 340 * LevelInfo.ratioX;
		
		
		if(carType.compareTo(Constants.carTypeBenga) == 0){
			if(Gdx.graphics.getWidth() > 1100)
			{
				y = Gdx.graphics.getHeight()/2f - 105*LevelInfo.ratioY 
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_alpha).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h/2f;	
			}
			else if(Gdx.graphics.getWidth() > 500)
			{
				y = Gdx.graphics.getHeight()/2f - 85*LevelInfo.ratioY 
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_alpha).getRegionHeight()*LevelInfo.ratioY/2f;	
			}
			else if(Gdx.graphics.getWidth() > 400)
			{
				y  = 200 * LevelInfo.ratioY;
				x = Gdx.graphics.getWidth()/2f - 150 * LevelInfo.ratioX;
			}
			else
			{	
				y  = 200 * LevelInfo.ratioY;
				x = Gdx.graphics.getWidth()/2f - 100 * LevelInfo.ratioX;
			}
//			y = Gdx.graphics.getHeight()/2f - 85*LevelInfo.ratioY 
//			- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_alpha).getRegionHeight()*LevelInfo.ratioY/2f;
//			y  = 250 * LevelInfo.ratioY;
		}
		else if(carType.compareTo(Constants.carTypeHuwwer) == 0 || carType.compareTo(Constants.carTypeSuper) == 0){
			if(Gdx.graphics.getWidth() > 1100)
			{
				y = Gdx.graphics.getHeight()/2f - 35*LevelInfo.ratioY 
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_alpha).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h/2f;	
			}
			else if(Gdx.graphics.getWidth() > 500)
			{
				y = Gdx.graphics.getHeight()/2f + 45*LevelInfo.ratioY 
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_alpha).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h/2f;
			}
			else if(Gdx.graphics.getWidth() > 400)
			{
				y  = 400 * LevelInfo.ratioY;
				x = Gdx.graphics.getWidth()/2f - 240 * LevelInfo.ratioX;
			}
			else
			{	
				y  = 450 * LevelInfo.ratioY;
				x = Gdx.graphics.getWidth()/2f - 140 * LevelInfo.ratioX;
			}
//			y = Gdx.graphics.getHeight()/2f + 45*LevelInfo.ratioY 
//			- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_alpha).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h/2f;
//			y  = 400 * LevelInfo.ratioY;
		}
		else{
			if(Gdx.graphics.getWidth() > 1100)
			{
				y = Gdx.graphics.getHeight()/2f - 75*LevelInfo.ratioY 
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_alpha).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h/2f;	
			}
			else if(Gdx.graphics.getWidth() > 500)
			{
				y = Gdx.graphics.getHeight()/2f - 25*LevelInfo.ratioY 
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_alpha).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h/2f;	
			}
			else if(Gdx.graphics.getWidth() > 400)
			{
				y  = 300 * LevelInfo.ratioY;
				x = Gdx.graphics.getWidth()/2f - 200 * LevelInfo.ratioX;
			}
			else
			{	
				y  = 450 * LevelInfo.ratioY;
				x = Gdx.graphics.getWidth()/2f - 40 * LevelInfo.ratioX;
			}
//			y = Gdx.graphics.getHeight()/2f - 25*LevelInfo.ratioY 
//				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_alpha).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h/2f;
//			y  = 400 * LevelInfo.ratioY;
	}
		
		world = new World(new Vector2(0, -10f), true);
		
		GameMenuInfo.carSizeCoeff = GameMenuInfo.carSizeCoeffMenu;
		car= new Car(x, y,
		400 * LevelInfo.ratioX, 300 * LevelInfo.ratioY,
		0f,carType, world, false, false);
		addElement(car);
		GameMenuInfo.carSizeCoeff = GameMenuInfo.carSizeCoeffGV;
		
		return car;
	}


	public void set_color_selected(){
		for(i = 0; i < number_of_colors; i++){
			if(selected_color_button != null){
				if(color[i] == selected_color_button){
//					color[i].setActive(true);
					color[i].setImage(Helper.getImageFromAssets(color[i].setSpecifiedImagePath(true)));
				}
			}
		}
	}
	
	public void set_vehicle_selected(){
		for(i = 0; i < number_of_vehicle; i++){
			if(selected_vehicle_button != null){
				if(vehicle[i] == selected_vehicle_button){
//					vehicle[i].setActive(true);
					vehicle[i].setImage(Helper.getImageFromAssets(vehicle[i].setSpecifiedImagePath(true)));
				}
			}
		}
	}
	
	
	
	int i;
	public void consumeTouchColor(){
		for(i = 0; i < number_of_colors; i++){
			if(color[i].isActive()){
				color[i].setActive(false);
			}
		}
	}
	
	public void consumeTouchVehicle(){
		for(i = 0; i < number_of_vehicle; i++){
			if(vehicle[i].isActive()){
				vehicle[i].setActive(false);
			}
		}
	}
	
	
	public void set_select_menu(){
		x = 0f;
		y = 0f;
		bg = new BackGroundUniversal(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, AssetConstantsCarSpeedPro.select_bg);
		this.elements.add(bg);
		
		
		//vehicle
		
		gap = 1.5f;
		y = Gdx.graphics.getHeight() - 100f*AssetConstantsCarSpeedPro.ratio_h;
		
		x = Gdx.graphics.getWidth()/2f - gap/2f * AssetConstantsCarSpeedPro.ratio_w
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w;
		vehicle[2] = new SelectionButtonCarSpeedPro(x, y,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1,
				AssetConstantsCarSpeedPro.military_n, AssetConstantsCarSpeedPro.military_d,
				ButtonGroup.vehicle,
				ButtonSpecific_tag.military);
		this.elements.add(vehicle[2]);
		this.interaction_touch.subscribe(vehicle[2]);
//		vehicle[2].controlVisualSelecetion();

		x = Gdx.graphics.getWidth()/2f - gap/2f * AssetConstantsCarSpeedPro.ratio_w
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w
				- gap*AssetConstantsCarSpeedPro.ratio_w
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.huwwer_d).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w;
		vehicle[1] = new SelectionButtonCarSpeedPro(x, y,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.huwwer_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.huwwer_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1,
				AssetConstantsCarSpeedPro.benga_n, AssetConstantsCarSpeedPro.benga_d,
				ButtonGroup.vehicle,
				ButtonSpecific_tag.benga);
		this.elements.add(vehicle[1]);
		this.interaction_touch.subscribe(vehicle[1]);
//		vehicle[1].controlVisualSelecetion();
		
		x = Gdx.graphics.getWidth()/2f - gap/2f * AssetConstantsCarSpeedPro.ratio_w
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w
				- gap*AssetConstantsCarSpeedPro.ratio_w
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.huwwer_d).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w
				- gap*AssetConstantsCarSpeedPro.ratio_w
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.huwwer_d).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w;
		vehicle[0] = new SelectionButtonCarSpeedPro(x, y,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1,
				AssetConstantsCarSpeedPro.huwwer_n, AssetConstantsCarSpeedPro.huwwer_d,
				ButtonGroup.vehicle,
				ButtonSpecific_tag.huwwer);
		this.elements.add(vehicle[0]);
		this.interaction_touch.subscribe(vehicle[0]);
		vehicle[0].setActive(true);
		selected_vehicle_button = vehicle[0];
//		vehicle[0].controlVisualSelecetion();

		x = Gdx.graphics.getWidth()/2f + gap/2f * AssetConstantsCarSpeedPro.ratio_w;
		vehicle[3] = new SelectionButtonCarSpeedPro(x, y,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1,
				AssetConstantsCarSpeedPro.krac_n, AssetConstantsCarSpeedPro.krac_d,
				ButtonGroup.vehicle,
				ButtonSpecific_tag.krac);
		this.elements.add(vehicle[3]);
		this.interaction_touch.subscribe(vehicle[3]);
//		vehicle[3].controlVisualSelecetion();
		
		x = Gdx.graphics.getWidth()/2f + gap/2f * AssetConstantsCarSpeedPro.ratio_w
				+ Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_d).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w
				+ gap*AssetConstantsCarSpeedPro.ratio_w;
		vehicle[4] = new SelectionButtonCarSpeedPro(x, y,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1,
				AssetConstantsCarSpeedPro.police_n, AssetConstantsCarSpeedPro.police_d,
				ButtonGroup.vehicle,
				ButtonSpecific_tag.police);
		this.elements.add(vehicle[4]);
		this.interaction_touch.subscribe(vehicle[4]);
//		vehicle[4].controlVisualSelecetion();

		x = Gdx.graphics.getWidth()/2f + gap/2f * AssetConstantsCarSpeedPro.ratio_w
				+ Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_d).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w
				+ gap*AssetConstantsCarSpeedPro.ratio_w
				+ Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_d).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w
				+ gap*AssetConstantsCarSpeedPro.ratio_w;
		vehicle[5] = new SelectionButtonCarSpeedPro(x, y,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1,
				AssetConstantsCarSpeedPro.school_n, AssetConstantsCarSpeedPro.school_d,
				ButtonGroup.vehicle,
				ButtonSpecific_tag.school);
		this.elements.add(vehicle[5]);
		this.interaction_touch.subscribe(vehicle[5]);
//		vehicle[5].controlVisualSelecetion();

		
		
		//color
		gap = 45f;
		y = y - 70*AssetConstantsCarSpeedPro.ratio_h;
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w;
		color[2] = new SelectionButtonCarSpeedPro(x, y,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h, 
				1,
				AssetConstantsCarSpeedPro.clr_2, AssetConstantsCarSpeedPro.clr_s2,
				ButtonGroup.color,
				ButtonSpecific_tag.clr_2);
		this.elements.add(color[2]);
		this.interaction_touch.subscribe(color[2]);
//		color[2].controlVisualSelecetion();

		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w
				- gap * AssetConstantsCarSpeedPro.ratio_w
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w;
		color[1] = new SelectionButtonCarSpeedPro(x, y,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h, 
				1,
				AssetConstantsCarSpeedPro.clr_1, AssetConstantsCarSpeedPro.clr_s1,
				ButtonGroup.color,
				ButtonSpecific_tag.clr_1);
		this.elements.add(color[1]);
		this.interaction_touch.subscribe(color[1]);
//		color[1].controlVisualSelecetion();
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w
				- gap * AssetConstantsCarSpeedPro.ratio_w
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w
				- gap *AssetConstantsCarSpeedPro.ratio_w
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w;
		color[0] = new SelectionButtonCarSpeedPro(x, y,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h, 
				1,
				AssetConstantsCarSpeedPro.clr_0, AssetConstantsCarSpeedPro.clr_s0,
				ButtonGroup.color,
				ButtonSpecific_tag.clr_0);
		this.elements.add(color[0]);
		this.interaction_touch.subscribe(color[0]);
		color[0].setActive(true);
		selected_color_button = color[0];
//		color[0].controlVisualSelecetion();

		x = Gdx.graphics.getWidth()/2f + Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w
				+ gap*AssetConstantsCarSpeedPro.ratio_w;
		color[3] = new SelectionButtonCarSpeedPro(x, y,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h, 
				1,
				AssetConstantsCarSpeedPro.clr_3, AssetConstantsCarSpeedPro.clr_s3,
				ButtonGroup.color,
				ButtonSpecific_tag.clr_3);
		this.elements.add(color[3]);
		this.interaction_touch.subscribe(color[3]);
//		color[3].controlVisualSelecetion();

		x = Gdx.graphics.getWidth()/2f + Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w
				+ gap*AssetConstantsCarSpeedPro.ratio_w
				+ Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w
				+ gap*AssetConstantsCarSpeedPro.ratio_w;
		color[4] = new SelectionButtonCarSpeedPro(x, y,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.clr_0).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h, 
				1, 
				AssetConstantsCarSpeedPro.clr_4, AssetConstantsCarSpeedPro.clr_s4,
				ButtonGroup.color,
				ButtonSpecific_tag.clr_4);
		this.elements.add(color[4]);
		this.interaction_touch.subscribe(color[4]);
//		color[4].controlVisualSelecetion();

		
		gap = 40f;
		x = Gdx.graphics.getWidth()/2f - gap*AssetConstantsCarSpeedPro.ratio_w
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.ok_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w;
		y = 35 * AssetConstantsCarSpeedPro.ratio_h;
		
		
		Helper.println("\n\nlevel info :\nmode :"+SelectLevelMenuCarSpeedPro.selected_level_mode_button.specific_tag
				+"\nlevel_number :"+SelectLevelMenuCarSpeedPro.selected_level_number_button.specific_tag
				+"\n\nvehicle info :\ncolor"+selected_color_button.specific_tag
				+"\nvehicle type :"+selected_vehicle_button.specific_tag);
		
		okay = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.ok_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.ok_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1, AssetConstantsCarSpeedPro.ok_n, new BikeLevel());
		this.elements.add(okay);
		this.interaction_touch.subscribe(okay);
		
		x = Gdx.graphics.getWidth()/2f + gap*AssetConstantsCarSpeedPro.ratio_w;	
		back = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.back_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.back_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1, AssetConstantsCarSpeedPro.back_n, new SelectLevelMenuCarSpeedPro());
		this.elements.add(back);
		this.interaction_touch.subscribe(back);
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstantsCarSpeedPro.benga_d);
		assetPack.addTexture(AssetConstantsCarSpeedPro.benga_n);
		assetPack.addTexture(AssetConstantsCarSpeedPro.huwwer_d);
		assetPack.addTexture(AssetConstantsCarSpeedPro.huwwer_n);
		assetPack.addTexture(AssetConstantsCarSpeedPro.police_d);
		assetPack.addTexture(AssetConstantsCarSpeedPro.police_n);
		assetPack.addTexture(AssetConstantsCarSpeedPro.school_d);
		assetPack.addTexture(AssetConstantsCarSpeedPro.school_n);
		assetPack.addTexture(AssetConstantsCarSpeedPro.krac_d);
		assetPack.addTexture(AssetConstantsCarSpeedPro.krac_n);
		assetPack.addTexture(AssetConstantsCarSpeedPro.military_d);
		assetPack.addTexture(AssetConstantsCarSpeedPro.military_n);
		
		assetPack.addTexture(AssetConstantsCarSpeedPro.clr_0);
		assetPack.addTexture(AssetConstantsCarSpeedPro.clr_1);
		assetPack.addTexture(AssetConstantsCarSpeedPro.clr_2);
		assetPack.addTexture(AssetConstantsCarSpeedPro.clr_3);
		assetPack.addTexture(AssetConstantsCarSpeedPro.clr_4);
		assetPack.addTexture(AssetConstantsCarSpeedPro.clr_s0);
		assetPack.addTexture(AssetConstantsCarSpeedPro.clr_s1);
		assetPack.addTexture(AssetConstantsCarSpeedPro.clr_s2);
		assetPack.addTexture(AssetConstantsCarSpeedPro.clr_s3);
		assetPack.addTexture(AssetConstantsCarSpeedPro.clr_s4);

//		assetPack.addTexture(AssetConstantsCarSpeedPro.back_h);
		assetPack.addTexture(AssetConstantsCarSpeedPro.back_n);
//		assetPack.addTexture(AssetConstantsCarSpeedPro.ok_h);
		assetPack.addTexture(AssetConstantsCarSpeedPro.ok_n);

		assetPack.addTexture(AssetConstantsCarSpeedPro.benga_01);
		assetPack.addTexture(AssetConstantsCarSpeedPro.benga_02);
		assetPack.addTexture(AssetConstantsCarSpeedPro.benga_03);
		assetPack.addTexture(AssetConstantsCarSpeedPro.benga_alpha);
		assetPack.addTexture(AssetConstantsCarSpeedPro.benga_wheel_alpha);
		assetPack.addTexture(AssetConstantsCarSpeedPro.benga_wheel);

		assetPack.addTexture(AssetConstantsCarSpeedPro.benga_01);
		assetPack.addTexture(AssetConstantsCarSpeedPro.benga_02);
		assetPack.addTexture(AssetConstantsCarSpeedPro.benga_03);
		assetPack.addTexture(AssetConstantsCarSpeedPro.benga_alpha);
		assetPack.addTexture(AssetConstantsCarSpeedPro.benga_wheel_alpha);
		assetPack.addTexture(AssetConstantsCarSpeedPro.benga_wheel);
		
		assetPack.addTexture(AssetConstantsCarSpeedPro.huwwer_01);
		assetPack.addTexture(AssetConstantsCarSpeedPro.huwwer_02);
		assetPack.addTexture(AssetConstantsCarSpeedPro.huwwer_03);
		assetPack.addTexture(AssetConstantsCarSpeedPro.huwwer_alpha);
		assetPack.addTexture(AssetConstantsCarSpeedPro.huwwer_wheel_alpha);
		assetPack.addTexture(AssetConstantsCarSpeedPro.huwwer_wheel);
		
		assetPack.addTexture(AssetConstantsCarSpeedPro.krac_01);
		assetPack.addTexture(AssetConstantsCarSpeedPro.krac_02);
		assetPack.addTexture(AssetConstantsCarSpeedPro.krac_03);
		assetPack.addTexture(AssetConstantsCarSpeedPro.krac_alpha);
		assetPack.addTexture(AssetConstantsCarSpeedPro.krac_wheel_alpha);
		assetPack.addTexture(AssetConstantsCarSpeedPro.krac_wheel);
		
		assetPack.addTexture(AssetConstantsCarSpeedPro.military_01);
		assetPack.addTexture(AssetConstantsCarSpeedPro.military_02);
		assetPack.addTexture(AssetConstantsCarSpeedPro.military_03);
		assetPack.addTexture(AssetConstantsCarSpeedPro.military_alpha);
		assetPack.addTexture(AssetConstantsCarSpeedPro.military_wheel_alpha);
		assetPack.addTexture(AssetConstantsCarSpeedPro.military_wheel);
		
		assetPack.addTexture(AssetConstantsCarSpeedPro.police_01);
		assetPack.addTexture(AssetConstantsCarSpeedPro.police_02);
		assetPack.addTexture(AssetConstantsCarSpeedPro.police_03);
		assetPack.addTexture(AssetConstantsCarSpeedPro.police_alpha);
		assetPack.addTexture(AssetConstantsCarSpeedPro.police_wheel_alpha);
		assetPack.addTexture(AssetConstantsCarSpeedPro.police_wheel);
		
		assetPack.addTexture(AssetConstantsCarSpeedPro.schoolbus_01);
		assetPack.addTexture(AssetConstantsCarSpeedPro.schoolbus_02);
		assetPack.addTexture(AssetConstantsCarSpeedPro.schoolbus_wheel_alpha);
		assetPack.addTexture(AssetConstantsCarSpeedPro.schoolbus_wheel);
		
		return assetPack;
	}
	

	public static boolean consumed = true;
	@Override
	public void tick(long stepTime) {
//		if(selected_vehicle_button != null){
		if(!consumed){
			setVehicle(selected_vehicle_button.specific_tag, selected_color_button.specific_tag);
			consumed = true;
		}
//		}
	}

}
