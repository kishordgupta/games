package com.rhymes.game.carspeedpro.menu.main;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.carspeedpro.AssetConstantsCarSpeedPro;
import com.rhymes.game.carspeedpro.BackGroundUniversal;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.carspeedpro.menu.levelselection.SelectLevelMenuCarSpeedPro;
import com.rhymes.game.carspeedpro.menu.settings.SettingsMenuCarSpeedPro;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class MainmenuCarspeedpro extends StageBase{
	
	BackGroundUniversal bg_menu;
	ButtonStageLoad butt_play, butt_settings;

	InteractionTouch interaction_touch;
	
	float x, y;
	
	@Override
	public void loadElements() {
		
		interaction_touch = new InteractionTouch();
		this.interactions.add(this.interaction_touch);

		set_main_menu();
	}
	
	public void set_main_menu(){
		x = 0f;
		y = 0f;
		bg_menu = new BackGroundUniversal(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, 
				AssetConstantsCarSpeedPro.main_bg);
		this.elements.add(bg_menu);
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.play_n).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w;
		y = Gdx.graphics.getHeight()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.play_n).getRegionHeight()/2f*AssetConstantsCarSpeedPro.ratio_h;
		butt_play = new ButtonStageLoad(x, y,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.play_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.play_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h, 
				1, 
				AssetConstantsCarSpeedPro.play_n, 
				new SelectLevelMenuCarSpeedPro());
		this.elements.add(butt_play);
		this.interaction_touch.subscribe(butt_play);
		
		y = y - 1.5f * Helper.getImageFromAssets(AssetConstantsCarSpeedPro.settings_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h;
		butt_settings = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.settings_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w,
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.settings_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h,
				1, 
				AssetConstantsCarSpeedPro.settings_n, 
				new SettingsMenuCarSpeedPro());
		this.elements.add(butt_settings);
		this.interaction_touch.subscribe(butt_settings);
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstantsCarSpeedPro.main_bg);
//		assetPack.addTexture(AssetConstantsCarSpeedPro.play_h);
		assetPack.addTexture(AssetConstantsCarSpeedPro.play_n);
//		assetPack.addTexture(AssetConstantsCarSpeedPro.settings_h);
		assetPack.addTexture(AssetConstantsCarSpeedPro.settings_n);

		return assetPack;
	}

	@Override
	public void tick(long stepTime) {
		
	}

}
