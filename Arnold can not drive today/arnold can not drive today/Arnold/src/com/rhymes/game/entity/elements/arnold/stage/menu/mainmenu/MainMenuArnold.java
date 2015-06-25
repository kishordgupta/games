package com.rhymes.game.entity.elements.arnold.stage.menu.mainmenu;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.arnold.ArnoldGameConstants;
import com.rhymes.game.entity.elements.arnold.stage.menu.mainmenu.button.LeaderBoardArnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.mainmenu.button.PlayArnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.mainmenu.button.Settings_ButtonArnold;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class MainMenuArnold extends StageBase{
	
	public InteractionTouch interaction_touch_main_menu;
	float x, y;
	
	public Main_Arnold_B_G bg;
	public PlayArnold play_button;
	public Settings_ButtonArnold settings_button;
	public LeaderBoardArnold leaderboard_button;
	

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		
		assetPack.addTexture(AssetConstants.play_arnold_d);
		assetPack.addTexture(AssetConstants.play_arnold_n);

		assetPack.addTexture(AssetConstants.settings_arnold_d);
		assetPack.addTexture(AssetConstants.settings_arnold_n);

//		assetPack.addTexture(AssetConstants.leaderboard_arnold_d);
//		assetPack.addTexture(AssetConstants.leaderboard_arnold_n);

		return assetPack;
	}

	@Override
	public void loadElements() {
		this.interaction_touch_main_menu = new InteractionTouch();
		this.interactions.add(this.interaction_touch_main_menu);
		
		set_main_menu_elements();
	}
	
	private void set_main_menu_elements(){
		x = 0f;
		y = 0f;
		bg = new Main_Arnold_B_G(x, y, 
				Gdx.graphics.getWidth(), 
				Gdx.graphics.getHeight(), 
				1,
				AssetConstants.main_menu_arnold_bg);
		this.elements.add(bg);
		
		
		x = Gdx.graphics.getWidth()/2f;
		y = Gdx.graphics.getHeight()/2f - ArnoldGameConstants.button_height/2f;
		
		play_button = new PlayArnold(x, y, 
				Helper.getImageFromAssets(AssetConstants.play_arnold_d).getRegionWidth()*ArnoldGameConstants.ratio_w  /*ArnoldGameConstants.button_width*/, 
				Helper.getImageFromAssets(AssetConstants.play_arnold_d).getRegionHeight()*ArnoldGameConstants.ratio_h  /*ArnoldGameConstants.button_height*/,
				1, 
				AssetConstants.play_arnold_d,
				AssetConstants.play_arnold_n);
		this.elements.add(play_button);
		this.interaction_touch_main_menu.subscribe(play_button);
		
		
		y = (y - 50f * ArnoldGameConstants.ratio_h)-
		/*(Helper.getImageFromAssets(AssetConstants.settings_arnold_d).getRegionHeight() * */ArnoldGameConstants.button_height;
		
		settings_button = new Settings_ButtonArnold(x, y, 
				Helper.getImageFromAssets(AssetConstants.settings_arnold_d).getRegionWidth()*ArnoldGameConstants.ratio_w /* ArnoldGameConstants.button_width*/,
				Helper.getImageFromAssets(AssetConstants.settings_arnold_d).getRegionHeight()*ArnoldGameConstants.ratio_h  /*ArnoldGameConstants.button_height*/, 
				1, 
				AssetConstants.settings_arnold_d,
				AssetConstants.settings_arnold_n);
		this.elements.add(settings_button);
		this.interaction_touch_main_menu.subscribe(settings_button);

		
//		y = (y - 50f * ArnoldGameConstants.ratio_h)-
//		/*(Helper.getImageFromAssets(AssetConstants.settings_arnold_d).getRegionHeight() **/ ArnoldGameConstants.button_height;
//		
//		leaderboard_button = new LeaderBoardArnold(x, y, 
//				/*Helper.getImageFromAssets(AssetConstants.leaderboard_arnold_d).getRegionWidth() **/ ArnoldGameConstants.button_width,
//				/*Helper.getImageFromAssets(AssetConstants.leaderboard_arnold_d).getRegionHeight() **/ ArnoldGameConstants.button_height, 
//				1, 
//				AssetConstants.leaderboard_arnold_d,
//				AssetConstants.leaderboard_arnold_n);
//		this.elements.add(leaderboard_button);
//		this.interaction_touch_main_menu.subscribe(leaderboard_button);

		
	}

	@Override
	public void tick(long stepTime) {
		
	}

}
