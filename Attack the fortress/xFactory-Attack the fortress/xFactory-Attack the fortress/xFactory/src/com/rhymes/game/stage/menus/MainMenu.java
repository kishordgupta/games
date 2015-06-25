package com.rhymes.game.stage.menus;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.menu.ButtonEnterGameMenu;
import com.rhymes.game.entity.elements.menu.ButtonExitGame;
import com.rhymes.game.entity.elements.menu.ButtonOptionGameMenu;
import com.rhymes.game.entity.elements.piku.PlanePathSet;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.ISingleCollision;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class MainMenu extends StageBase {

	ISingleCollision collider;
	PlanePathSet pathSet;
	
	float x;
	float y;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BACK_MENU);
		assetPack.addTexture(AssetConstants.IMG_PLAY_MENU);
		assetPack.addTexture(AssetConstants.IMG_PLAY_MENU_PRESSED);
		assetPack.addTexture(AssetConstants.IMG_OPTIONS);
		assetPack.addTexture(AssetConstants.IMG_OPTIONS_PRESSED);
		assetPack.addTexture(AssetConstants.IMG_EXIT);
		assetPack.addTexture(AssetConstants.IMG_EXIT_PRESSED);


		return assetPack;
	}

	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BACK_MENU));

		//start game
//		x = Gdx.graphics.getWidth()/10- Helper.getImageFromAssets(AssetConstants.IMG_PLAY).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
//		y = 3 * Gdx.graphics.getHeight()/4 - Helper.getImageFromAssets(AssetConstants.IMG_PLAY).getRegionHeight()/2;// * GameMenuInfo.ratio_h;
		
		x = 48*GameMenuInfo.ratio_w- Helper.getImageFromAssets(AssetConstants.IMG_PLAY_MENU).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
		y = 641*GameMenuInfo.ratio_h - Helper.getImageFromAssets(AssetConstants.IMG_PLAY_MENU).getRegionHeight()/2;// * GameMenuInfo.ratio_h;

		System.out.println("screenwidth : "+Gdx.graphics.getWidth()+" \nscreenheight : "+Gdx.graphics.getHeight());
		System.out.println("ratio wl : "+GameMenuInfo.ratio_w_img+" \nratio hl : "+GameMenuInfo.ratio_h_img);
		System.out.println("image w : "+Helper.getImageFromAssets(AssetConstants.IMG_PLAY_MENU).getRegionWidth()*GameMenuInfo.ratio_w_img+" \nimage h : "+Helper.getImageFromAssets(AssetConstants.IMG_PLAY_MENU).getRegionHeight()*GameMenuInfo.ratio_h_img);

		ButtonEnterGameMenu buttonEntergame = new ButtonEnterGameMenu(x, y, Helper.getImageFromAssets(AssetConstants.IMG_PLAY_MENU).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_PLAY_MENU).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_PLAY_MENU);
		this.elements.add(buttonEntergame);
		subscribeToControllingInteraction(buttonEntergame, InteractionTouch.class);
	
		//option
//		y = Gdx.graphics.getHeight()/4 - Helper.getImageFromAssets(AssetConstants.IMG_OPTIONS).getRegionHeight()/2;//* GameMenuInfo.ratio_h;
		y = 120*GameMenuInfo.ratio_h;// - Helper.getImageFromAssets(AssetConstants.IMG_OPTIONS).getRegionHeight()/2;//* GameMenuInfo.ratio_h;
		
		ButtonOptionGameMenu buttonOption = new ButtonOptionGameMenu(x, y, Helper.getImageFromAssets(AssetConstants.IMG_OPTIONS).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_OPTIONS).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_OPTIONS);
		this.elements.add(buttonOption);
		subscribeToControllingInteraction(buttonOption, InteractionTouch.class);
	
		//exit
//		x = (Gdx.graphics.getWidth() - (90 * GameMenuInfo.ratio_w))- Helper.getImageFromAssets(AssetConstants.IMG_EXIT).getRegionWidth()/2;

//		x = 8 *Gdx.graphics.getWidth()/10 - Helper.getImageFromAssets(AssetConstants.IMG_EXIT).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
//		y = (Gdx.graphics.getHeight() /10)+ (50);//* GameMenuInfo.ratio_h);
		
		x = 384*GameMenuInfo.ratio_w - Helper.getImageFromAssets(AssetConstants.IMG_EXIT).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
		y = 120*GameMenuInfo.ratio_h;//* GameMenuInfo.ratio_h);
		
		ButtonExitGame buttonExitgame = new ButtonExitGame(x, y, Helper.getImageFromAssets(AssetConstants.IMG_EXIT).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_EXIT).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_EXIT);
		this.elements.add(buttonExitgame);
		subscribeToControllingInteraction(buttonExitgame, InteractionTouch.class);
	
	}

	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}

}
