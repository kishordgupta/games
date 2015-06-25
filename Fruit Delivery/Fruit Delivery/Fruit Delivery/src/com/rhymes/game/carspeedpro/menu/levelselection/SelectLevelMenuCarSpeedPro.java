package com.rhymes.game.carspeedpro.menu.levelselection;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.carspeedpro.AssetConstantsCarSpeedPro;
import com.rhymes.game.carspeedpro.BackGroundUniversal;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.carspeedpro.menu.levelselection.resources.SelectionButtonCarSpeedPro;
import com.rhymes.game.carspeedpro.menu.levelselection.resources.SelectionButtonCarSpeedPro.ButtonGroup;
import com.rhymes.game.carspeedpro.menu.levelselection.resources.SelectionButtonCarSpeedPro.ButtonSpecific_tag;
import com.rhymes.game.carspeedpro.menu.main.MainmenuCarspeedpro;
import com.rhymes.game.carspeedpro.menu.vehicleselection.SelectVehicleMenuCarSpeedPro;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class SelectLevelMenuCarSpeedPro extends StageBase{

	public static int number_of_mode = 3;
	public static int number_of_level = 5;
	
	public static SelectionButtonCarSpeedPro selected_level_number_button = null;
	public static SelectionButtonCarSpeedPro selected_level_mode_button = null;
	
	BackGroundUniversal bg;
	
	public SelectionButtonCarSpeedPro[] level_mode;
	public SelectionButtonCarSpeedPro[] level_number;
	
	ButtonStageLoad okay, back;
	
	float x, y, gap;
	
	InteractionTouch interaction_touch;
	
	
	public SelectLevelMenuCarSpeedPro(){
		level_mode = new SelectionButtonCarSpeedPro[number_of_mode];
		level_number = new SelectionButtonCarSpeedPro[number_of_level];
		
	}
	
	
	@Override
	public void loadElements() {
		interaction_touch = new InteractionTouch();
		this.interactions.add(this.interaction_touch);
		
		set_select_level_menu();
		set_level_mode_selected();
		set_level_number_selected();
	}
	
	
	public void set_level_mode_selected(){
		for(i = 0; i < number_of_mode; i++){
			if(selected_level_mode_button != null){
				if(level_mode[i] == selected_level_mode_button){
//					level_mode[i].setActive(true);
					level_mode[i].setImage(Helper.getImageFromAssets(level_mode[i].setSpecifiedImagePath(true)));
				}
			}
		}
	}
	
	public void set_level_number_selected(){
		for(i = 0; i < number_of_level; i++){
			if(selected_level_number_button != null){
				if(level_number[i] == selected_level_number_button){
//					level_number[i].setActive(true);
					level_number[i].setImage(Helper.getImageFromAssets(level_number[i].setSpecifiedImagePath(true)));
				}
			}
		}
	}
	
	int i;
	public void consumeTouchLevelMode(){
		for(i = 0; i < number_of_mode; i++){
			if(level_mode[i].isActive()){
				level_mode[i].setActive(false);
			}
		}
	}
	
	public void consumeTouchLevelNumber(){
		for(i = 0; i < number_of_level; i++){
			if(level_number[i].isActive()){
				level_number[i].setActive(false);
			}
		}
	}
	
	public void set_select_level_menu(){
		x = 0f;
		y = 0f;
		bg = new BackGroundUniversal(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, AssetConstantsCarSpeedPro.select_bg);
		this.elements.add(bg);
		
		
		///level number
		
		y = Gdx.graphics.getHeight()/2f + 150f * AssetConstantsCarSpeedPro.ratio_h;
		gap = 60f;
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w;
		level_number[2] = new SelectionButtonCarSpeedPro(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1,
				AssetConstantsCarSpeedPro.n_3,
				AssetConstantsCarSpeedPro.d_3,
				ButtonGroup.level_number,
				ButtonSpecific_tag.level_2);
		this.elements.add(level_number[2]);
		this.interaction_touch.subscribe(level_number[2]);
//		level_number[2].controlVisualSelecetion();
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w
				- gap*AssetConstantsCarSpeedPro.ratio_w - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w;
		
		level_number[1] = new SelectionButtonCarSpeedPro(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1, 
				AssetConstantsCarSpeedPro.n_2, 
				AssetConstantsCarSpeedPro.d_2,
				ButtonGroup.level_number,
				ButtonSpecific_tag.level_1);
		this.elements.add(level_number[1]);
		this.interaction_touch.subscribe(level_number[1]);
//		level_number[1].controlVisualSelecetion();
	
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w
				- gap*AssetConstantsCarSpeedPro.ratio_w - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w
				- gap*AssetConstantsCarSpeedPro.ratio_w - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w;
		
		level_number[0] = new SelectionButtonCarSpeedPro(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1, 
				AssetConstantsCarSpeedPro.n_1, 
				AssetConstantsCarSpeedPro.d_1,
				ButtonGroup.level_number,
				ButtonSpecific_tag.level_0);
		this.elements.add(level_number[0]);
		this.interaction_touch.subscribe(level_number[0]);
		level_number[0].setActive(true);
		selected_level_number_button = level_number[0];
//		level_number[0].controlVisualSelecetion();

		x = Gdx.graphics.getWidth()/2f + Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w
				+ gap*AssetConstantsCarSpeedPro.ratio_w;
		
		level_number[3] = new SelectionButtonCarSpeedPro(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1, 
				AssetConstantsCarSpeedPro.n_4, 
				AssetConstantsCarSpeedPro.d_4,
				ButtonGroup.level_number,
				ButtonSpecific_tag.level_3);
		this.elements.add(level_number[3]);
		this.interaction_touch.subscribe(level_number[3]);
//		level_number[3].controlVisualSelecetion();

		x = Gdx.graphics.getWidth()/2f + Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w
				+ gap*AssetConstantsCarSpeedPro.ratio_w + Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w
				+ gap*AssetConstantsCarSpeedPro.ratio_w;
		
		level_number[4] = new SelectionButtonCarSpeedPro(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.d_3).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1, 
				AssetConstantsCarSpeedPro.n_5, 
				AssetConstantsCarSpeedPro.d_5,
				ButtonGroup.level_number,
				ButtonSpecific_tag.level_4);
		this.elements.add(level_number[4]);
		this.interaction_touch.subscribe(level_number[4]);
//		level_number[4].controlVisualSelecetion();
	
		
		
		///mode
		
		gap = 70f;
		y = Gdx.graphics.getHeight()/2f 
				- 50f*AssetConstantsCarSpeedPro.ratio_h - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.desert_d).getRegionHeight()/2f*AssetConstantsCarSpeedPro.ratio_h;
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.desert_d).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w;
		level_mode[1] = new SelectionButtonCarSpeedPro(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.desert_d).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.desert_d).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1,
				AssetConstantsCarSpeedPro.desert_n,
				AssetConstantsCarSpeedPro.desert_d,
				ButtonGroup.level_mode,
				ButtonSpecific_tag.desert);
		this.elements.add(level_mode[1]);
		this.interaction_touch.subscribe(level_mode[1]);
//		level_mode[1].controlVisualSelecetion();
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.desert_d).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w
				- gap*AssetConstantsCarSpeedPro.ratio_w - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.city_d).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w;
		level_mode[0] = new SelectionButtonCarSpeedPro(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.city_d).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.city_d).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1,
				AssetConstantsCarSpeedPro.city_n,
				AssetConstantsCarSpeedPro.city_d,
				ButtonGroup.level_mode,
				ButtonSpecific_tag.city);
		this.elements.add(level_mode[0]);
		this.interaction_touch.subscribe(level_mode[0]);	
		level_mode[0].setActive(true);
		selected_level_mode_button = level_mode[0];
//		level_mode[0].controlVisualSelecetion();

		x = Gdx.graphics.getWidth()/2f + Helper.getImageFromAssets(AssetConstantsCarSpeedPro.desert_d).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w
				+ gap*AssetConstantsCarSpeedPro.ratio_w;
		level_mode[2] = new SelectionButtonCarSpeedPro(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.city_d).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.city_d).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1,
				AssetConstantsCarSpeedPro.tropic_n,
				AssetConstantsCarSpeedPro.tropic_d,
				ButtonGroup.level_mode,
				ButtonSpecific_tag.tropic);
		this.elements.add(level_mode[2]);
		this.interaction_touch.subscribe(level_mode[2]);
//		level_mode[2].controlVisualSelecetion();

		
		gap = 40f;
		x = Gdx.graphics.getWidth()/2f - gap*AssetConstantsCarSpeedPro.ratio_w
				- Helper.getImageFromAssets(AssetConstantsCarSpeedPro.ok_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w;
		y = 35 * AssetConstantsCarSpeedPro.ratio_h;
		okay = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.ok_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.ok_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1, AssetConstantsCarSpeedPro.ok_n, new SelectVehicleMenuCarSpeedPro());
		this.elements.add(okay);
		this.interaction_touch.subscribe(okay);
		
		x = Gdx.graphics.getWidth()/2f + gap*AssetConstantsCarSpeedPro.ratio_w;	
		back = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.back_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.back_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1, AssetConstantsCarSpeedPro.back_n, new MainmenuCarspeedpro());
		this.elements.add(back);
		this.interaction_touch.subscribe(back);
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstantsCarSpeedPro.select_bg);
		assetPack.addTexture(AssetConstantsCarSpeedPro.d_1);
		assetPack.addTexture(AssetConstantsCarSpeedPro.d_2);
		assetPack.addTexture(AssetConstantsCarSpeedPro.d_3);
		assetPack.addTexture(AssetConstantsCarSpeedPro.d_4);
		assetPack.addTexture(AssetConstantsCarSpeedPro.d_5);
		assetPack.addTexture(AssetConstantsCarSpeedPro.n_1);
		assetPack.addTexture(AssetConstantsCarSpeedPro.n_2);
		assetPack.addTexture(AssetConstantsCarSpeedPro.n_3);
		assetPack.addTexture(AssetConstantsCarSpeedPro.n_4);
		assetPack.addTexture(AssetConstantsCarSpeedPro.n_5);
		assetPack.addTexture(AssetConstantsCarSpeedPro.city_d);
		assetPack.addTexture(AssetConstantsCarSpeedPro.city_n);
		assetPack.addTexture(AssetConstantsCarSpeedPro.tropic_d);
		assetPack.addTexture(AssetConstantsCarSpeedPro.tropic_n);
		assetPack.addTexture(AssetConstantsCarSpeedPro.desert_d);
		assetPack.addTexture(AssetConstantsCarSpeedPro.desert_n);
//		assetPack.addTexture(AssetConstantsCarSpeedPro.ok_h);
		assetPack.addTexture(AssetConstantsCarSpeedPro.ok_n);
//		assetPack.addTexture(AssetConstantsCarSpeedPro.back_h);
		assetPack.addTexture(AssetConstantsCarSpeedPro.back_n);

		return assetPack;
	}

	@Override
	public void tick(long stepTime) {
		
	}

}
