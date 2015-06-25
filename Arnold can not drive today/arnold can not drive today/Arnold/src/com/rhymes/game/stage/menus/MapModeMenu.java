package com.rhymes.game.stage.menus;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.burntherope.PlanePathSet;
import com.rhymes.game.entity.elements.menu.ButtonBackToModeMenu;
import com.rhymes.game.entity.elements.menu.ButtonEnterGameMenu;
import com.rhymes.game.entity.elements.menu.ButtonExitGame;
import com.rhymes.game.entity.elements.menu.ButtonLevelPack;
import com.rhymes.game.entity.elements.menu.ButtonOptionGameMenu;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.ISingleCollision;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class MapModeMenu extends StageBase {

	private int pack_id;
	private ArrayList<ButtonLevelPack> pack_Cache = new ArrayList<ButtonLevelPack>();
	ButtonLevelPack[] buttonLevelpack;
	
	float x;
	float y;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_MAP_BACK_MENU);
		assetPack.addTexture(AssetConstants.IMG_PLAY_MENU);
		assetPack.addTexture(AssetConstants.IMG_LEVEL_PACK_SELECTOR);

		
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

		buttonLevelpack = new ButtonLevelPack[GameMenuInfo.num_of_level_pack];
		setLevelPackCache();
		

		////level pack button
		for(pack_id = 0; pack_id < pack_Cache.size(); pack_id++){
			buttonLevelpack[pack_id] = pack_Cache.get(pack_id);
			buttonLevelpack[pack_id].setActivePack(false);
			this.elements.add(buttonLevelpack[pack_id]);
			subscribeToControllingInteraction(buttonLevelpack[pack_id], InteractionTouch.class);

		}
		
		
		//back to mode menu
		x = 48*GameMenuInfo.ratio_w- Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
		y = 120*GameMenuInfo.ratio_h;
		ButtonBackToModeMenu buttonBackToModeMenu = new ButtonBackToModeMenu(x, y, Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_BACK);
		this.elements.add(buttonBackToModeMenu);
		subscribeToControllingInteraction(buttonBackToModeMenu, InteractionTouch.class);
	
	}
	

	////add level pack button info to the cache
	public void setLevelPackCache(){
		
		pack_id = 0;
		
		x = Gdx.graphics.getWidth()/2 - (Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_PACK_SELECTOR).getRegionWidth()/10);// * GameMenuInfo.ratio_w;
//		y = (250 * GameMenuInfo.ratio_h - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_PACK_SELECTOR).getRegionHeight()/10);
		y = 5 *Gdx.graphics.getHeight()/ 6 - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_PACK_SELECTOR).getRegionHeight()/10;

		pack_Cache.add(pack_id,new ButtonLevelPack(x, y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_PACK_SELECTOR).getRegionWidth()/5*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_PACK_SELECTOR).getRegionHeight()/5*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_PACK_SELECTOR, pack_id));
		pack_id++;
		
//		y = y + (250 - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_PACK_SELECTOR).getRegionHeight()/10) * GameMenuInfo.ratio_h;
		y = Gdx.graphics.getHeight()/ 2 - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_PACK_SELECTOR).getRegionHeight()/10;

		pack_Cache.add(pack_id,new ButtonLevelPack(x, y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_PACK_SELECTOR).getRegionWidth()/5*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_PACK_SELECTOR).getRegionHeight()/5*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_PACK_SELECTOR, pack_id));
		pack_id++;
		
//		y = y + (250 - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_PACK_SELECTOR).getRegionHeight()/10) * GameMenuInfo.ratio_h;
		y = Gdx.graphics.getHeight()/ 6 - Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_PACK_SELECTOR).getRegionHeight()/10;
		pack_Cache.add(pack_id,new ButtonLevelPack(x, y, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_PACK_SELECTOR).getRegionWidth()/5*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_LEVEL_PACK_SELECTOR).getRegionHeight()/5*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_LEVEL_PACK_SELECTOR, pack_id));

	}

	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}

}
