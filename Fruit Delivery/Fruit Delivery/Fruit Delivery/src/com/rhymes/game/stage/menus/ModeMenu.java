package com.rhymes.game.stage.menus;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.menu.ButtonBackToMainmenu;
import com.rhymes.game.entity.elements.menu.ButtonClassicMode;
import com.rhymes.game.entity.elements.menu.ButtonEnterGameMenu;
import com.rhymes.game.entity.elements.menu.ButtonExitGame;
import com.rhymes.game.entity.elements.menu.ButtonOptionGameMenu;
import com.rhymes.game.entity.elements.menu.ButtonTimeMode;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class ModeMenu extends StageBase {
	
	float x;
	float y;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_CLASSIC_MODE_PRESSED);
		assetPack.addTexture(AssetConstants.IMG_BACK);
		assetPack.addTexture(AssetConstants.IMG_BACK_PRESSED);
		return assetPack;
	}
	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BACK));

/*		
		//map mode
//		x = Gdx.graphics.getWidth()/10;
//		x = 8 *Gdx.graphics.getWidth()/10 - Helper.getImageFromAssets(AssetConstants.IMG_MAP_MODE).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
//		y = (Gdx.graphics.getHeight() - Helper.getImageFromAssets(AssetConstants.IMG_MAP_MODE).getRegionHeight() - 100* GameMenuInfo.ratio_h) ;
		
		x = 390*GameMenuInfo.ratio_w - Helper.getImageFromAssets(AssetConstants.IMG_MAP_MODE).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
		y = (870*GameMenuInfo.ratio_h - Helper.getImageFromAssets(AssetConstants.IMG_MAP_MODE).getRegionHeight()) ;
		
		ButtonMapMode buttonMapMode = new ButtonMapMode(x, y, Helper.getImageFromAssets(AssetConstants.IMG_MAP_MODE).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_MAP_MODE).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_MAP_MODE);
		this.elements.add(buttonMapMode);
		subscribeToControllingInteraction(buttonMapMode, InteractionTouch.class);
	
		//time mode
//		x = Gdx.graphics.getWidth()/10;
//		y = (y - Helper.getImageFromAssets(AssetConstants.IMG_TIME_MODE).getRegionHeight() - 80* GameMenuInfo.ratio_h) ;
		
		x = 48*GameMenuInfo.ratio_w;
		y = (y - Helper.getImageFromAssets(AssetConstants.IMG_TIME_MODE).getRegionHeight()/2) ;
		
		ButtonTimeMode buttonTimeMode = new ButtonTimeMode(x, y, Helper.getImageFromAssets(AssetConstants.IMG_TIME_MODE).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_TIME_MODE).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_TIME_MODE);
		this.elements.add(buttonTimeMode);
		subscribeToControllingInteraction(buttonTimeMode, InteractionTouch.class);*/
	
		//classic mode
		x = 390*GameMenuInfo.ratio_w - Helper.getImageFromAssets(AssetConstants.IMG_CLASSIC_MODE).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
		y = (100* GameMenuInfo.ratio_h) ;
		ButtonClassicMode buttonClassicMode = new ButtonClassicMode(x, y, Helper.getImageFromAssets(AssetConstants.IMG_CLASSIC_MODE).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_CLASSIC_MODE).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_CLASSIC_MODE);
		this.elements.add(buttonClassicMode);
		subscribeToControllingInteraction(buttonClassicMode, InteractionTouch.class);
	
		//back to main menu
//		x = 8 *Gdx.graphics.getWidth()/10 - Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
		x = 48*GameMenuInfo.ratio_w- Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
		y = 120*GameMenuInfo.ratio_h;
		ButtonBackToMainmenu back = new ButtonBackToMainmenu(x, y, Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_BACK);
		this.elements.add(back);
		subscribeToControllingInteraction(back, InteractionTouch.class);
	
	}

	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}

}
