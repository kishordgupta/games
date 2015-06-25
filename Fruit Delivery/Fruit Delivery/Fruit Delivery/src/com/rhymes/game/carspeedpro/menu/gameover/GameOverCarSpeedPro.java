package com.rhymes.game.carspeedpro.menu.gameover;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.carspeedpro.AssetConstantsCarSpeedPro;
import com.rhymes.game.carspeedpro.BackGroundUniversal;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.carspeedpro.menu.levelselection.SelectLevelMenuCarSpeedPro;
import com.rhymes.game.carspeedpro.menu.levelselection.resources.SelectionButtonCarSpeedPro.ButtonSpecific_tag;
import com.rhymes.game.carspeedpro.menu.vehicleselection.SelectVehicleMenuCarSpeedPro;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class GameOverCarSpeedPro extends StageBase{
	
	BackGroundUniversal bg, again;
	ButtonStageLoad yes, no;
	
	InteractionTouch interaction_touch;
	
	float x, y, gap;

	@Override
	public void loadElements() {
		interaction_touch = new InteractionTouch();
		this.interactions.add(interaction_touch);
		
		set_game_over_menu();
		
	}
	
	
	public void set_game_over_menu(){
		select_bg();
		
		gap = 40f;
		
		x = Gdx.graphics.getWidth()/2f - gap*AssetConstantsCarSpeedPro.ratio_w - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.yes_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w;
		y = 50*AssetConstantsCarSpeedPro.ratio_h;
		yes = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.yes_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.yes_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h, 
				1, AssetConstantsCarSpeedPro.yes_n, new SelectLevelMenuCarSpeedPro());
		this.elements.add(yes);
		this.interaction_touch.subscribe(yes);
		
		x = Gdx.graphics.getWidth()/2f + gap*AssetConstantsCarSpeedPro.ratio_w;	
		no = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.yes_n).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.yes_n).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h, 
				1, AssetConstantsCarSpeedPro.no_n, new SelectVehicleMenuCarSpeedPro());
		this.elements.add(no);
		this.interaction_touch.subscribe(no);
		
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsCarSpeedPro.again).getRegionWidth()/2f*AssetConstantsCarSpeedPro.ratio_w;
		y = Gdx.graphics.getHeight()/2f - 180*AssetConstantsCarSpeedPro.ratio_h;
		again = new BackGroundUniversal(x, y, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.again).getRegionWidth()*AssetConstantsCarSpeedPro.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsCarSpeedPro.again).getRegionHeight()*AssetConstantsCarSpeedPro.ratio_h, 
				1, AssetConstantsCarSpeedPro.again);
		this.elements.add(again);
	
	}
	
	public void select_bg(){
		x = 0f;
		y = 0f;
		
		if(SelectLevelMenuCarSpeedPro.selected_level_mode_button.specific_tag == ButtonSpecific_tag.city){
			bg = new BackGroundUniversal(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, AssetConstantsCarSpeedPro.city_image_city);

		}
		
		else if(SelectLevelMenuCarSpeedPro.selected_level_mode_button.specific_tag == ButtonSpecific_tag.desert){
			bg = new BackGroundUniversal(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, AssetConstantsCarSpeedPro.desert_image_desert);

		}
		
		else if(SelectLevelMenuCarSpeedPro.selected_level_mode_button.specific_tag == ButtonSpecific_tag.tropic){
			bg = new BackGroundUniversal(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, AssetConstantsCarSpeedPro.tropic_image_tropic);

		}
		
		this.elements.add(bg);

	}
	

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstantsCarSpeedPro.city_image_city);
		assetPack.addTexture(AssetConstantsCarSpeedPro.desert_image_desert);
		assetPack.addTexture(AssetConstantsCarSpeedPro.tropic_image_tropic);

//		assetPack.addTexture(AssetConstantsCarSpeedPro.yes_h);
		assetPack.addTexture(AssetConstantsCarSpeedPro.yes_n);
//		assetPack.addTexture(AssetConstantsCarSpeedPro.no_h);
		assetPack.addTexture(AssetConstantsCarSpeedPro.no_n);
		assetPack.addTexture(AssetConstantsCarSpeedPro.again);

		return assetPack;
	}

	@Override
	public void tick(long stepTime) {
		
	}

}
