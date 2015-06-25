package com.rhymes.game.carspeedpro.menu.settings;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.carspeedpro.AssetConstantsCarSpeedPro;
import com.rhymes.game.carspeedpro.BackGroundUniversal;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.carspeedpro.menu.main.MainmenuCarspeedpro;
import com.rhymes.game.carspeedpro.menu.settings.resource.GameModeButtonCarSpeedPro;
import com.rhymes.game.carspeedpro.menu.settings.resource.SoundButtonCarSpeedPro;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class SettingsMenuCarSpeedPro extends StageBase{
	
	BackGroundUniversal bg, sound_img, game_mode_img;
	SoundButtonCarSpeedPro sound;
	GameModeButtonCarSpeedPro game_mode;
	
	ButtonStageLoad back;
	
	float x, y;
	int i;
	InteractionTouch interaction_touch;
	
	public void set_settings_menu(){
		x = 0f;
		y = 0f;
		bg = new BackGroundUniversal(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, AssetConstantsCarSpeedPro.main_bg);
		this.elements.add(bg);
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.sound).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w;
		y = Gdx.graphics.getHeight()/2f + 40f*AssetConstantsCarSpeedPro.ratio_h - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.sound).getRegionHeight()/2f*AssetConstantsCarSpeedPro.ratio_h;
		sound_img = new BackGroundUniversal(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.sound).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.sound).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1, AssetConstantsCarSpeedPro.sound);
		this.elements.add(sound_img);
		
		x = Gdx.graphics.getWidth()/2f + 2.5f*Helper.getImageFromAssets(AssetConstantsCarSpeedPro.on).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w;
		sound = new SoundButtonCarSpeedPro(x, y,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.on).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.on).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h, 
				1, get_game_sound_image());
		this.elements.add(sound);
		this.interaction_touch.subscribe(sound);
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.game_mode).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w;
		y = y - 2.5f * Helper.getImageFromAssets(AssetConstantsCarSpeedPro.game_mode).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h;
		game_mode_img = new BackGroundUniversal(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.game_mode).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.game_mode).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h, 
				1, AssetConstantsCarSpeedPro.game_mode);
		this.elements.add(game_mode_img);
		
		x = Gdx.graphics.getWidth()/2f + 2.5f*Helper.getImageFromAssets(AssetConstantsCarSpeedPro.free).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w;
		game_mode = new GameModeButtonCarSpeedPro(x, y,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.free).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.free).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1, get_mode_image());
		this.elements.add(game_mode);
		this.interaction_touch.subscribe(game_mode);

		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.back_n).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w;
		y = 35f*AssetConstantsCarSpeedPro.ratio_h;
		
		back = new ButtonStageLoad(x, y,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.back_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.back_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h, 
				1, AssetConstantsCarSpeedPro.back_n, new MainmenuCarspeedpro());
		this.elements.add(back);
		this.interaction_touch.subscribe(back);
	}
	
	public static boolean is_game_sound_active = true;
	String game_sound_image = null;
	public String get_game_sound_image(){
		
		if(is_game_sound_active)
			game_sound_image = AssetConstantsCarSpeedPro.on;
		else
			game_sound_image = AssetConstantsCarSpeedPro.off;
		
		return game_sound_image;
	}
	
	
	public static boolean is_game_mode = true;
	String mode_image = null;
	public String get_mode_image(){
		
		if(is_game_mode)
			mode_image = AssetConstantsCarSpeedPro.free;
		else
			mode_image = AssetConstantsCarSpeedPro.time;
		
		return mode_image;
	}


	
	@Override
	public void loadElements() {
		interaction_touch = new InteractionTouch();
		this.interactions.add(this.interaction_touch);
		
		set_settings_menu();
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstantsCarSpeedPro.sound);
		assetPack.addTexture(AssetConstantsCarSpeedPro.on);
		assetPack.addTexture(AssetConstantsCarSpeedPro.off);
		assetPack.addTexture(AssetConstantsCarSpeedPro.game_mode);
		assetPack.addTexture(AssetConstantsCarSpeedPro.free);
		assetPack.addTexture(AssetConstantsCarSpeedPro.time);
//		assetPack.addTexture(AssetConstantsCarSpeedPro.back_h);
		assetPack.addTexture(AssetConstantsCarSpeedPro.back_n);

		return assetPack;
	}

	@Override
	public void tick(long stepTime) {
		
	}

}
