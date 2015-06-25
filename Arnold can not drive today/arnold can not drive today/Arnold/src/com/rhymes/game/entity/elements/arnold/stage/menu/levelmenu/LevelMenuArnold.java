package com.rhymes.game.entity.elements.arnold.stage.menu.levelmenu;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.arnold.ArnoldGameConstants;
import com.rhymes.game.entity.elements.arnold.stage.menu.levelmenu.button.LevelSelectorArnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.levelmenu.button.RadioButtonArnold;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class LevelMenuArnold extends StageBase{
	
	public final static int num_of_level = 3;

	public InteractionTouch interaction_touch_level_menu;
	float x, y;
	
	LeveL_Arnold_B_G bg;
	int i;
	
	public RadioButtonArnold radio[] = new RadioButtonArnold[num_of_level];
	public LevelSelectorArnold level_button[] = new LevelSelectorArnold[num_of_level];
	
	public LevelMenuArnold(){
		
	}
	
	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		
		assetPack.addTexture(AssetConstants.radio_button_arnold_on);
		assetPack.addTexture(AssetConstants.radio_button_arnold_off);

		assetPack.addTexture(AssetConstants.level_arnold_1_d);
		assetPack.addTexture(AssetConstants.level_arnold_1_n);

		assetPack.addTexture(AssetConstants.level_arnold_2_d);
		assetPack.addTexture(AssetConstants.level_arnold_2_n);

		assetPack.addTexture(AssetConstants.level_arnold_3_d);
		assetPack.addTexture(AssetConstants.level_arnold_3_n);

		return assetPack;

	}

	@Override
	public void loadElements() {
		this.interaction_touch_level_menu = new InteractionTouch();
		this.interactions.add(this.interaction_touch_level_menu);
		set_level_menu_elements();
	}

	private void set_level_menu_elements() {
		
		x = 0f;
		y = 0f;
		bg = new LeveL_Arnold_B_G(x, y, 
				Gdx.graphics.getWidth(), 
				Gdx.graphics.getHeight(), 
				1,
				AssetConstants.level_menu_arnold_bg);
		this.elements.add(bg);
		
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstants.radio_button_arnold_off).getRegionWidth()/2f*ArnoldGameConstants.ratio_w/*ArnoldGameConstants.radio_width/2f */
				- (40f * ArnoldGameConstants.ratio_w) - Helper.getImageFromAssets(AssetConstants.radio_button_arnold_off).getRegionWidth()*ArnoldGameConstants.ratio_w/*ArnoldGameConstants.radio_width*/;
		y =Gdx.graphics.getHeight() -  75 * ArnoldGameConstants.ratio_h;
		
		radio[0] = new RadioButtonArnold(x, y, 
				/*ArnoldGameConstants.radio_width*/Helper.getImageFromAssets(AssetConstants.radio_button_arnold_off).getRegionWidth()*ArnoldGameConstants.ratio_w, 
				/*ArnoldGameConstants.radio_height*/Helper.getImageFromAssets(AssetConstants.radio_button_arnold_off).getRegionHeight()*ArnoldGameConstants.ratio_h, 
				1, 
				AssetConstants.radio_button_arnold_on,
				0);
		radio[0].setActive(true);
		this.elements.add(radio[0]);
		this.interaction_touch_level_menu.subscribe(radio[0]);
		
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstants.radio_button_arnold_off).getRegionWidth()/2f*ArnoldGameConstants.ratio_w/*ArnoldGameConstants.radio_width/2f*/;
		
		radio[1] = new RadioButtonArnold(x, y, 
				/*ArnoldGameConstants.radio_width*/Helper.getImageFromAssets(AssetConstants.radio_button_arnold_off).getRegionWidth()*ArnoldGameConstants.ratio_w, 
				/*ArnoldGameConstants.radio_height*/Helper.getImageFromAssets(AssetConstants.radio_button_arnold_off).getRegionHeight()*ArnoldGameConstants.ratio_h, 
				1, 
				AssetConstants.radio_button_arnold_off,
				1);
		this.elements.add(radio[1]);
		this.interaction_touch_level_menu.subscribe(radio[1]);
		
		
		x = Gdx.graphics.getWidth()/2f + Helper.getImageFromAssets(AssetConstants.radio_button_arnold_off).getRegionWidth()/2f*ArnoldGameConstants.ratio_w/*ArnoldGameConstants.radio_width/2f */
				+ 40f *ArnoldGameConstants.ratio_w;
		
		radio[2] = new RadioButtonArnold(x, y, 
				/*ArnoldGameConstants.radio_width*/Helper.getImageFromAssets(AssetConstants.radio_button_arnold_off).getRegionWidth()*ArnoldGameConstants.ratio_w, 
				/*ArnoldGameConstants.radio_height*/Helper.getImageFromAssets(AssetConstants.radio_button_arnold_off).getRegionHeight()*ArnoldGameConstants.ratio_h, 
				1, 
				AssetConstants.radio_button_arnold_off,
				2);
		this.elements.add(radio[2]);
		this.interaction_touch_level_menu.subscribe(radio[2]);
		
		
		
		x = Gdx.graphics.getWidth()/2f -/*ArnoldGameConstants.level_ui_width/2f*/Helper.getImageFromAssets(AssetConstants.level_arnold_1_d).getRegionWidth()/2f*ArnoldGameConstants.ratio_w;
		y = - Gdx.graphics.getHeight();
		
		level_button[0] = new LevelSelectorArnold(x, y,
				/*ArnoldGameConstants.level_ui_width*/Helper.getImageFromAssets(AssetConstants.level_arnold_1_d).getRegionWidth()*ArnoldGameConstants.ratio_w, 
				/*ArnoldGameConstants.level_ui_height*/Helper.getImageFromAssets(AssetConstants.level_arnold_1_d).getRegionHeight()*ArnoldGameConstants.ratio_h, 
				1, 
				AssetConstants.level_arnold_1_d,
				AssetConstants.level_arnold_1_n,
				0);
		this.elements.add(level_button[0]);
		this.interaction_touch_level_menu.subscribe(level_button[0]);
		
		level_button[1] = new LevelSelectorArnold(x, y,
				Helper.getImageFromAssets(AssetConstants.level_arnold_1_d).getRegionWidth()*ArnoldGameConstants.ratio_w/*ArnoldGameConstants.level_ui_width*/, 
				Helper.getImageFromAssets(AssetConstants.level_arnold_1_d).getRegionHeight()*ArnoldGameConstants.ratio_h/*ArnoldGameConstants.level_ui_height*/, 
				1, 
				AssetConstants.level_arnold_2_d,
				AssetConstants.level_arnold_2_n,
				1);
		this.elements.add(level_button[1]);
		this.interaction_touch_level_menu.subscribe(level_button[1]);
		
		level_button[2] = new LevelSelectorArnold(x, y,
				Helper.getImageFromAssets(AssetConstants.level_arnold_1_d).getRegionWidth()*ArnoldGameConstants.ratio_w/*ArnoldGameConstants.level_ui_width*/, 
				Helper.getImageFromAssets(AssetConstants.level_arnold_1_d).getRegionHeight()*ArnoldGameConstants.ratio_h/*ArnoldGameConstants.level_ui_height*/, 
				1, 
				AssetConstants.level_arnold_3_d,
				AssetConstants.level_arnold_3_n,
				2);
		this.elements.add(level_button[2]);
		this.interaction_touch_level_menu.subscribe(level_button[2]);
	}

	
	
	@Override
	public void tick(long stepTime) {
	
		check_level_selector();
	
	}

	
	int j;
	float pos_upper = Gdx.graphics.getHeight()/2f - ArnoldGameConstants.level_ui_height/2f - 95*ArnoldGameConstants.ratio_h;
	float pos_under = -2*Gdx.graphics.getHeight();
	
	private void check_level_selector() {
	
	if(radio[0].isActive()){
		level_button[0].setY(pos_upper);
		radio[1].setActive(false);
//		level_button[1].setY(pos_under);
		radio[2].setActive(false);
//		level_button[2].setY(pos_under);
	}
	
	else if(!radio[0].isActive()){
		level_button[0].setY(pos_under);
	}
	
	if(radio[1].isActive()){
		level_button[1].setY(pos_upper);
		radio[0].setActive(false);
//		level_button[0].setY(pos_under);
		radio[2].setActive(false);
//		level_button[2].setY(pos_under);
	}
	
	else if (!radio[1].isActive()) {
		level_button[1].setY(pos_under);
	}
	
	if(radio[2].isActive()){
		level_button[2].setY(pos_upper);
		radio[0].setActive(false);
//		level_button[0].setY(pos_under);
		radio[1].setActive(false);
//		level_button[1].setY(pos_under);
	}
	
	else if(!radio[2].isActive()){
		level_button[2].setY(pos_under);
	}
	
	}

	public void clearRadioSelections()
	{
		if(radio[0] != null)
			radio[0].setActive(false);
		if(radio[1] != null)
			radio[1].setActive(false);
		if(radio[2] != null)
			radio[2].setActive(false);
	}
}
