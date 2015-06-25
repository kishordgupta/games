package com.rhymes.game.entity.elements.arnold.stage.menu.setting;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.arnold.ArnoldGameConstants;
import com.rhymes.game.entity.elements.arnold.stage.StageArnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.setting.button.BackToMainMenuArnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.setting.button.GameSoundArnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.setting.button.PlayerSoundArnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.ui.SensorButtonArnold;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class SettingMenuArnold extends StageBase{
	
	public InteractionTouch interaction_touch_setting_menu;
	float x, y;
	
	Setting_Arnold_B_G bg;
	
	GameSoundArnold game_sound_button;
	PlayerSoundArnold player_sound_button;
	SensorButtonArnold sensor_button;
	
	BackToMainMenuArnold back_to_main_button;
	

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.player_sound_on_arnold);
		assetPack.addTexture(AssetConstants.player_sound_off_arnold);

		assetPack.addTexture(AssetConstants.bg_sound_on_arnold);
		assetPack.addTexture(AssetConstants.bg_sound_off_arnold);

		assetPack.addTexture(AssetConstants.back_to_main_menu_arnold_d);
		assetPack.addTexture(AssetConstants.back_to_main_menu_arnold_n);
		
		assetPack.addTexture(AssetConstants.sensor_button_arnold_on);
		assetPack.addTexture(AssetConstants.sensor_button_arnold_off);

		return assetPack;
	}

	@Override
	public void loadElements() {
		interaction_touch_setting_menu = new InteractionTouch();
		this.interactions.add(interaction_touch_setting_menu);
		
		set_settings_menu();
	}


	private void set_settings_menu() {
		x = 0f;
		y = 0f;
		
		bg = new Setting_Arnold_B_G(x, y, 
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(),
				1,
				AssetConstants.settings_menu_arnold_bg);
		this.elements.add(bg);
		
		
		x = Gdx.graphics.getWidth()/2f - ArnoldGameConstants.button_width/2f;
		y = Gdx.graphics.getHeight()/2f - ArnoldGameConstants.button_height/2f + 120f*ArnoldGameConstants.ratio_h;
		
		sensor_button = new SensorButtonArnold(x, y, 
				Helper.getImageFromAssets(AssetConstants.sensor_button_arnold_off).getRegionWidth()/*100*/ * ArnoldGameConstants.ratio_w/*ArnoldGameConstants.radio_width*/,
				Helper.getImageFromAssets(AssetConstants.sensor_button_arnold_off).getRegionHeight()/*50*/ * ArnoldGameConstants.ratio_h/*ArnoldGameConstants.radio_height*/,
				1,
				get_sensor_image());
		this.elements.add(sensor_button);
		this.interaction_touch_setting_menu.subscribe(sensor_button);
		
		
		
		x = Gdx.graphics.getWidth()/2f - 
		/*(Helper.getImageFromAssets(AssetConstants.bg_sound_on_arnold).getRegionHeight()/2f **/ ArnoldGameConstants.button_width/2f;
		y = Gdx.graphics.getHeight()/2f - 
		/*(Helper.getImageFromAssets(AssetConstants.bg_sound_on_arnold).getRegionHeight()/2f **/ ArnoldGameConstants.button_height/2f;
		
		game_sound_button = new GameSoundArnold(x, y, 
				Helper.getImageFromAssets(AssetConstants.bg_sound_on_arnold).getRegionWidth()*ArnoldGameConstants.ratio_w  /*ArnoldGameConstants.button_width*/,
				Helper.getImageFromAssets(AssetConstants.bg_sound_on_arnold).getRegionHeight()*ArnoldGameConstants.ratio_h /* ArnoldGameConstants.button_height*/, 
				1, 
				get_game_sound_image());
		this.elements.add(game_sound_button);
		this.interaction_touch_setting_menu.subscribe(game_sound_button);
		
		
		y = (y - 50f * ArnoldGameConstants.ratio_h)-
		/*(Helper.getImageFromAssets(AssetConstants.settings_arnold_d).getRegionHeight() **/ ArnoldGameConstants.button_height/2f;
		
		player_sound_button = new PlayerSoundArnold(x, y, 
				Helper.getImageFromAssets(AssetConstants.player_sound_on_arnold).getRegionWidth() *ArnoldGameConstants.ratio_w /*ArnoldGameConstants.button_width*/,
				Helper.getImageFromAssets(AssetConstants.player_sound_on_arnold).getRegionHeight() *ArnoldGameConstants.ratio_h  /*ArnoldGameConstants.button_height*/, 
				1, 
				get_arnold_sound_image());
		this.elements.add(player_sound_button);
		this.interaction_touch_setting_menu.subscribe(player_sound_button);
		
		
		y = (y - 50f * ArnoldGameConstants.ratio_h)-
		/*(Helper.getImageFromAssets(AssetConstants.settings_arnold_d).getRegionHeight() **/ ArnoldGameConstants.button_height/2f;
		
		back_to_main_button = new BackToMainMenuArnold(x, y, 
				Helper.getImageFromAssets(AssetConstants.back_to_main_menu_arnold_d).getRegionWidth() *ArnoldGameConstants.ratio_w  /*ArnoldGameConstants.button_width*/,
				Helper.getImageFromAssets(AssetConstants.back_to_main_menu_arnold_d).getRegionHeight() *ArnoldGameConstants.ratio_h /* ArnoldGameConstants.button_height*/, 
				1, 
				AssetConstants.back_to_main_menu_arnold_d,
				AssetConstants.back_from_over_arnold_menu_n);
		this.elements.add(back_to_main_button);
		this.interaction_touch_setting_menu.subscribe(back_to_main_button);
	}
	
	
	
	public String get_sensor_image(){
		String butt_img = null;
		
		if(StageArnold.is_sensor_active)
			butt_img = AssetConstants.sensor_button_arnold_on;
		else
			butt_img =  AssetConstants.sensor_button_arnold_off;
	
		return butt_img;
	}
	
	
	public static boolean is_game_sound_active = true;
	public static boolean is_arnold_sound_active = true;

	String game_sound_image = null;
	public String get_game_sound_image(){
		
		if(is_game_sound_active)
			game_sound_image = AssetConstants.bg_sound_on_arnold;
		else
			game_sound_image = AssetConstants.bg_sound_off_arnold;
		
		return game_sound_image;
	}
	
	String arnold_sound_image = null;
	public String get_arnold_sound_image(){
		
		if(is_game_sound_active)
			arnold_sound_image = AssetConstants.player_sound_on_arnold;
		else
			arnold_sound_image = AssetConstants.player_sound_off_arnold;
		
		return arnold_sound_image;
	}

	@Override
	public void tick(long stepTime) {
		
	}

}
