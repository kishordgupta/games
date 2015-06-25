package com.rhymes.game.fruitdelivery.menu.selectvehicle;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.carspeedpro.BackGroundUniversal;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.fruitdelivery.AssetConstantsFruit;
import com.rhymes.game.fruitdelivery.menu.gameover.GameOverMenuFruit;
import com.rhymes.game.fruitdelivery.menu.main.MainMenuFruit;
import com.rhymes.game.fruitdelivery.menu.selectlevel.LevelMenuFruit;
import com.rhymes.game.fruitdelivery.menu.selectvehicle.res.SelectButtonFruit;
import com.rhymes.game.fruitdelivery.menu.selectvehicle.res.SelectButtonFruit.ButtonGroupFruit;
import com.rhymes.game.fruitdelivery.menu.selectvehicle.res.SelectButtonFruit.SpecificButtonTagFruit;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class SelectVehicleMenuFruit extends StageBase{
	BackGroundUniversal bg;
	ButtonStageLoad okay, back;

	InteractionTouch interaction_touch;
	
	public static SelectButtonFruit selected_vehicle_button = null;
	
	public static int number_of_vehicle = 3;
	SelectButtonFruit[] vehicle;
	
	float x, y;

	public SelectVehicleMenuFruit(){
		vehicle = new SelectButtonFruit[number_of_vehicle];

	}
	
	@Override
	public void loadElements() {
		interaction_touch = new InteractionTouch();
		this.interactions.add(interaction_touch);
		
		set_select_vehicle_menu();
		
		if(selected_vehicle_button == null)
			selected_vehicle_button = vehicle[0];
		
//		init_vehicle_state_image();
		Helper.println("vehicle : "+selected_vehicle_button.specificTagFruit);
	}

	float gap;
	public void set_select_vehicle_menu(){
		x = 0f;
		y = 0f;
		bg = new BackGroundUniversal(x, y, 
				Gdx.graphics.getWidth(), 
				Gdx.graphics.getHeight(),
				1, AssetConstantsFruit.main_bg);
		this.elements.add(bg);
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsFruit.truck_2).getRegionWidth()/2f*AssetConstantsFruit.ratio_w;
		y = Gdx.graphics.getHeight()/2f + 100*AssetConstantsFruit.ratio_h;
		
		vehicle[1] = new SelectButtonFruit(x, y,
				Helper.getImageFromAssets(AssetConstantsFruit.truck_2).getRegionWidth()*AssetConstantsFruit.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsFruit.truck_2).getRegionHeight()*AssetConstantsFruit.ratio_h, 
				1, 
				AssetConstantsFruit.truck_2,
				AssetConstantsFruit.truck_2_sel,
				ButtonGroupFruit.vehicle, 
				SpecificButtonTagFruit.vehicle_2);
		this.elements.add(vehicle[1]);
		this.interaction_touch.subscribe(vehicle[1]);
		
		x = x - 170*AssetConstantsFruit.ratio_w - Helper.getImageFromAssets(AssetConstantsFruit.truck_1).getRegionWidth()/2f * AssetConstantsFruit.ratio_w;
		
		vehicle[0] = new SelectButtonFruit(x, y,
				Helper.getImageFromAssets(AssetConstantsFruit.truck_2).getRegionWidth()*AssetConstantsFruit.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsFruit.truck_2).getRegionHeight()*AssetConstantsFruit.ratio_h, 
				1, 
				AssetConstantsFruit.truck_1,
				AssetConstantsFruit.truck_1_sel,
				ButtonGroupFruit.vehicle, 
				SpecificButtonTagFruit.vehicle_1);
		this.elements.add(vehicle[0]);
		this.interaction_touch.subscribe(vehicle[0]);
//		vehicle[0].setActive(true);
//		selected_vehicle_button = vehicle[0];
		
		x = Gdx.graphics.getWidth()/2f 
				- Helper.getImageFromAssets(AssetConstantsFruit.truck_2).getRegionWidth()/2f*AssetConstantsFruit.ratio_w 
				+ 170*AssetConstantsFruit.ratio_w + Helper.getImageFromAssets(AssetConstantsFruit.truck_1).getRegionWidth()/2f * AssetConstantsFruit.ratio_w;
		
		vehicle[2] = new SelectButtonFruit(x, y,
				Helper.getImageFromAssets(AssetConstantsFruit.truck_2).getRegionWidth()*AssetConstantsFruit.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsFruit.truck_2).getRegionHeight()*AssetConstantsFruit.ratio_h, 
				1, 
				AssetConstantsFruit.truck_3,
				AssetConstantsFruit.truck_3_sel,
				ButtonGroupFruit.vehicle, 
				SpecificButtonTagFruit.vehicle_3);
		this.elements.add(vehicle[2]);
		this.interaction_touch.subscribe(vehicle[2]);
		
		
		gap = 40f;
		x = Gdx.graphics.getWidth()/2f - gap*AssetConstantsFruit.ratio_w
				- Helper.getImageFromAssets(AssetConstantsFruit.ok_nor).getRegionWidth()*AssetConstantsFruit.ratio_w;
		y = Gdx.graphics.getHeight()/2f - 185 * AssetConstantsFruit.ratio_h;
		okay = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsFruit.ok_nor).getRegionWidth()*AssetConstantsFruit.ratio_w,
				Helper.getImageFromAssets(AssetConstantsFruit.ok_nor).getRegionHeight()*AssetConstantsFruit.ratio_h,
				1, AssetConstantsFruit.ok_nor, new LevelMenuFruit(0));
		this.elements.add(okay);
		this.interaction_touch.subscribe(okay);
		
		x = Gdx.graphics.getWidth()/2f + gap*AssetConstantsFruit.ratio_w;	
		back = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsFruit.back_nor).getRegionWidth()*AssetConstantsFruit.ratio_w,
				Helper.getImageFromAssets(AssetConstantsFruit.back_nor).getRegionHeight()*AssetConstantsFruit.ratio_h,
				1, AssetConstantsFruit.back_nor, new MainMenuFruit());
		this.elements.add(back);
		this.interaction_touch.subscribe(back);
	}
	
	int i;
	public void consumeTouchVehicle(){
		for(i = 0; i < number_of_vehicle; i++){
			if(vehicle[i].isActive()){
				vehicle[i].setActive(false);
			}
		}
	}
	
	
	public String init_vehicle_state_image(){
		String path = "";
		if(selected_vehicle_button != null){
			if(selected_vehicle_button == vehicle[0]){
				
			}
			
			else if(selected_vehicle_button == vehicle[1]){

			}
			
			else if(selected_vehicle_button == vehicle[2]){

			}
		}
		return path;
	}
	
	
	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstantsFruit.main_bg);
		assetPack.addTexture(AssetConstantsFruit.ok_nor);
		assetPack.addTexture(AssetConstantsFruit.ok_h);
		assetPack.addTexture(AssetConstantsFruit.back_nor);
		assetPack.addTexture(AssetConstantsFruit.back_h);


		assetPack.addTexture(AssetConstantsFruit.truck_1);
		assetPack.addTexture(AssetConstantsFruit.truck_1_sel);
		assetPack.addTexture(AssetConstantsFruit.truck_2);
		assetPack.addTexture(AssetConstantsFruit.truck_2_sel);
		assetPack.addTexture(AssetConstantsFruit.truck_3);
		assetPack.addTexture(AssetConstantsFruit.truck_3_sel);

		return assetPack;
	}

	@Override
	public void tick(long stepTime) {
		Helper.println("vehicle :: "+selected_vehicle_button.specificTagFruit);
	}

}
