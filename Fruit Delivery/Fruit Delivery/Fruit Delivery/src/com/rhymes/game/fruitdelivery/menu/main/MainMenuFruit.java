package com.rhymes.game.fruitdelivery.menu.main;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.carspeedpro.BackGroundUniversal;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.fruitdelivery.AssetConstantsFruit;
import com.rhymes.game.fruitdelivery.menu.selectvehicle.SelectVehicleMenuFruit;
import com.rhymes.game.fruitdelivery.menu.settings.SettingsMenuFruit;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class MainMenuFruit extends StageBase{
	BackGroundUniversal bg;
	ButtonStageLoad play, settings;

	InteractionTouch interaction_touch;
	float x, y;
	
	public MainMenuFruit(){
		
	}
	
	@Override
	public void loadElements() {
		interaction_touch = new InteractionTouch();
		this.interactions.add(interaction_touch);
		
		set_main_menu();
		
	}
	
	public void set_main_menu(){
		x = 0f;
		y = 0f;
		bg = new BackGroundUniversal(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, 
				AssetConstantsFruit.main_bg);
		this.elements.add(bg);
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsFruit.play_n).getRegionWidth()/2f*AssetConstantsFruit.ratio_w;
		y = Gdx.graphics.getHeight()/2f - 120*AssetConstantsFruit.ratio_h
				- Helper.getImageFromAssets(AssetConstantsFruit.play_n).getRegionHeight()/2f*AssetConstantsFruit.ratio_h;
		play = new ButtonStageLoad(x, y,
				Helper.getImageFromAssets(AssetConstantsFruit.play_n).getRegionWidth()*AssetConstantsFruit.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsFruit.play_n).getRegionHeight()*AssetConstantsFruit.ratio_h, 
				1, 
				AssetConstantsFruit.play_n, 
				new SelectVehicleMenuFruit());
		this.elements.add(play);
		this.interaction_touch.subscribe(play);
		
		y = y - 1.5f * Helper.getImageFromAssets(AssetConstantsFruit.settings_n).getRegionHeight()*AssetConstantsFruit.ratio_h;
		settings = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsFruit.settings_n).getRegionWidth()*AssetConstantsFruit.ratio_w,
				Helper.getImageFromAssets(AssetConstantsFruit.settings_n).getRegionHeight()*AssetConstantsFruit.ratio_h,
				1, 
				AssetConstantsFruit.settings_n, 
				new SettingsMenuFruit());
		this.elements.add(settings);
		this.interaction_touch.subscribe(settings);
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstantsFruit.main_bg);
		assetPack.addTexture(AssetConstantsFruit.play_h);
		assetPack.addTexture(AssetConstantsFruit.play_n);
		assetPack.addTexture(AssetConstantsFruit.settings_h);
		assetPack.addTexture(AssetConstantsFruit.settings_n);
		return assetPack;
	}

	@Override
	public void tick(long stepTime) {
		
	}

}
