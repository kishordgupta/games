package com.rhymes.game.carspeedpro.menu.vehicleselection;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.carspeedpro.AssetConstantsCarSpeedPro;
import com.rhymes.game.carspeedpro.BackGroundUniversal;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.carspeedpro.menu.gameover.GameOverCarSpeedPro;
import com.rhymes.game.carspeedpro.menu.levelselection.SelectLevelMenuCarSpeedPro;
import com.rhymes.game.carspeedpro.menu.levelselection.resources.SelectionButtonCarSpeedPro;
import com.rhymes.game.carspeedpro.menu.levelselection.resources.SelectionButtonCarSpeedPro.ButtonGroup;
import com.rhymes.game.carspeedpro.menu.levelselection.resources.SelectionButtonCarSpeedPro.ButtonSpecific_tag;
import com.rhymes.game.carspeedpro.menu.vehicleselection.resources.VehicleImageCarSpeedPro;
import com.rhymes.game.carspeedpro.menu.vehicleselection.resources.VehicleImageCarSpeedPro.colorTpe;
import com.rhymes.game.carspeedpro.menu.vehicleselection.resources.VehicleImageCarSpeedPro.vehicleType;
import com.rhymes.game.interactions.inputs.InteractionTouch;
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
	
	
	VehicleImageCarSpeedPro benga_0, benga_1, benga_2, benga_3, benga_4;
	
	
	public ArrayList<VehicleImageCarSpeedPro> benga_list = new ArrayList<VehicleImageCarSpeedPro>();
	public ArrayList<VehicleImageCarSpeedPro> huwwer_list = new ArrayList<VehicleImageCarSpeedPro>();
	public ArrayList<VehicleImageCarSpeedPro> krac_list = new ArrayList<VehicleImageCarSpeedPro>();
	public ArrayList<VehicleImageCarSpeedPro> militray_list = new ArrayList<VehicleImageCarSpeedPro>();
	public ArrayList<VehicleImageCarSpeedPro> police_list = new ArrayList<VehicleImageCarSpeedPro>();
	public ArrayList<VehicleImageCarSpeedPro> school_list = new ArrayList<VehicleImageCarSpeedPro>();
	
	
	
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
		
		init_vehicle_images();
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
	
	
	
	public void init_vehicle_images(){
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_alpha).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w/2f;
		y = Gdx.graphics.getHeight()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_alpha).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h/2f;
		
		benga_0 = new VehicleImageCarSpeedPro(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_alpha).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_alpha).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h, 
				vehicleType.benga, colorTpe.color_0);
		
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
		okay = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.ok_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.ok_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1, AssetConstantsCarSpeedPro.ok_n, new GameOverCarSpeedPro());
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
	
	float upper_y = Gdx.graphics.getHeight()/2f - 100*AssetConstantsCarSpeedPro.ratio_h;
	float under_y = - Gdx.graphics.getHeight();
	
	public void adjust_vehicle_image(){
		
	}

	@Override
	public void tick(long stepTime) {
		
	}

}
