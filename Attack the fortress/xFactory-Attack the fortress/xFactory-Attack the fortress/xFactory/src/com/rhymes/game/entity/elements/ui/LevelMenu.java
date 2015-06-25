package com.rhymes.game.entity.elements.ui;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.menu.ButtonBackToMapModeMenu;
import com.rhymes.game.entity.elements.menu.ButtonLevel;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class LevelMenu extends StageBase {
	
	float x, y;
	
	private int pack_id;
	private int level_id;
	private ArrayList<ButtonLevel> levelCache = new ArrayList<ButtonLevel>();
	private ButtonLevel[] button_level;
	private float button_x, button_y;
	
	public LevelMenu(int levelpackIndex){
		set_pack_id(levelpackIndex);
		Helper.println("pack id : "+get_pack_id());
		
	}
	
	public ButtonLevel[] getButtonLevel(){
		return button_level;
	}
	

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_MAP_BACK_MENU);
		assetPack.addTexture(AssetConstants.IMG_PLAY_MENU);
		assetPack.addTexture(AssetConstants.IMG_LEVEL_SELECTOR);

		assetPack.addTexture(AssetConstants.IMG_BACK);
		assetPack.addTexture(AssetConstants.IMG_BACK_PRESSED);

		return assetPack;
	}
	
	

	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_MAP_BACK_MENU));

		///////level grid
		button_level = new ButtonLevel[GameMenuInfo.num_of_total_level];
		setLevelCache();
		select_Level_visible(get_pack_id());
		
		if(levelCache.size() == GameMenuInfo.num_of_total_level){
			for(level_id = 0; level_id < GameMenuInfo.num_of_total_level; level_id++){
				button_level[level_id] = levelCache.get(level_id);
				
				//show level pack's levels
				if(button_level[level_id].is_level_visible()){
					this.elements.add(button_level[level_id]);
					subscribeToControllingInteraction(button_level[level_id], InteractionTouch.class);
				}
			}
		}
		
		x = 48*GameMenuInfo.ratio_w- Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
		y = 120*GameMenuInfo.ratio_h;
		ButtonBackToMapModeMenu buttonBackToMapModeMenu = new ButtonBackToMapModeMenu(x, y, Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_BACK);
		this.elements.add(buttonBackToMapModeMenu);
		subscribeToControllingInteraction(buttonBackToMapModeMenu, InteractionTouch.class);
	}
	
	///set levels visible for pack
	public void select_Level_visible(int pack_id){
		if(pack_id == 0){
			for(level_id = 0; level_id < GameMenuInfo.num_of_level_in_a_pack; level_id++){
				levelCache.get(level_id).set_level_visibility(true);
			}
		}
			
			else if(pack_id == 1){
				for(level_id = GameMenuInfo.num_of_level_in_a_pack; level_id < 2 * GameMenuInfo.num_of_level_in_a_pack ; level_id++){
					levelCache.get(level_id).set_level_visibility(true);
				}
			}
				
			else if(pack_id == 2){
				for(level_id = 2 * GameMenuInfo.num_of_level_in_a_pack; level_id < 3 * GameMenuInfo.num_of_level_in_a_pack; level_id++){
					levelCache.get(level_id).set_level_visibility(true);
					}
			}
	}
	
	
	
	public ArrayList<ButtonLevel> getLevelCache(){
		return levelCache;
	}
	

	public void setLevelCache(){
//		while(level_id < GameMenuInfo.num_of_total_level){
		
		///1st pack	
		
		level_id = 0;

			
//			button_x = (410* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (739 * GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			
//			button_x = (2.5f * Gdx.graphics.getWidth()/9 - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = ((5 * Gdx.graphics.getHeight()/7) - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;
			button_x = (89*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (343*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;

//			button_x = (340* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (701* GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10);

			
//			button_x = (4 * Gdx.graphics.getWidth()/9 - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = ((6 * Gdx.graphics.getHeight()/7) - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;
			button_x = (143*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (412*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
//			button_x = (276* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (763* GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10);

			
//			button_x = (5.5f * Gdx.graphics.getWidth()/9 - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = ((5.5f * Gdx.graphics.getHeight()/7) - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;
			button_x = (196*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (378*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
//			button_x = (190* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (714* GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10);

			
			button_x = (196*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (446*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;


			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
//			button_x = (130* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (679* GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10);

			
			button_x = (267*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (446*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;


			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
//			button_x = (170* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (611* GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10);

			
			button_x = (303*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (378*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
			///2nd pack
			
//			button_x = (308* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (355* GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10);

			
			button_x = (89*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (275*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
//			button_x = (270* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (405* GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10);

			
			button_x = (143*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (275*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
//			button_x = (250* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (505* GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			
			button_x = (178*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (205*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
//			button_x = (162* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (476* GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10);

			
			button_x = (178*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (309*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
//			button_x = (358* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (493* GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10);

			button_x = (232*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (309*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
//			button_x = (430* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (489* GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			
			button_x = (285*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (275*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
			//3rd pack
			
//			button_x = (304* GameMenuInfo.ratio_w);//  - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (203 * GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10);

			
			button_x = (89*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (69*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			
			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
//			button_x = (246* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (79 * GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10);

			
			button_x = (107*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (138*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			
			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
//			button_x = (156* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (100 * GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10);

			
			button_x = (143*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (102*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
//			button_x = (110* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (113* GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			
			button_x = (196*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (138*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
//			button_x = (372* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (136* GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			
			button_x = (196*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (69*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
			level_id++;
			
//			button_x = (422* GameMenuInfo.ratio_w);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
//			button_y = (119 * GameMenuInfo.ratio_h);// - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10);

			
			button_x = (249*GameMenuInfo.ratio_wl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10);
			button_y = (102*GameMenuInfo.ratio_hl - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10) ;

			levelCache.add(level_id,new ButtonLevel(button_x, button_y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_SELECTOR).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_SELECTOR, level_id));
		
//		}
	}
	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}

	public void set_pack_id(int level_pack_index) {
		this.pack_id = level_pack_index;
	}

	public int get_pack_id() {
		return pack_id;
	}


	public void setLevel_index(int level_index) {
		this.level_id = level_index;
	}


	public int getLevel_index() {
		return level_id;
	}

}
