package com.rhymes.game.stage.menus;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.menu.ButtonBackToMainmenu;
import com.rhymes.game.entity.elements.menu.ButtonMusicControl;
import com.rhymes.game.entity.elements.menu.ButtonSoundControl;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class OptionMenu extends StageBase{
	
	float x;
	float y;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BACK_MENU);
		
		assetPack.addTexture(AssetConstants.IMG_BACK);
		assetPack.addTexture(AssetConstants.IMG_BACK_PRESSED);
		
		assetPack.addTexture(AssetConstants.IMG_MUSIC_ON);
		assetPack.addTexture(AssetConstants.IMG_MUSIC_ON_PRESSED);
		assetPack.addTexture(AssetConstants.IMG_MUSIC_OFF);
		assetPack.addTexture(AssetConstants.IMG_MUSIC_OFF_PRESSED);
		
		assetPack.addTexture(AssetConstants.IMG_SOUND_ON);
		assetPack.addTexture(AssetConstants.IMG_SOUND_ON_PRESSED);
		assetPack.addTexture(AssetConstants.IMG_SOUND_OFF);
		assetPack.addTexture(AssetConstants.IMG_SOUND_OFF_PRESSED);


		return assetPack;
	}

	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BACK_MENU));

		//music
//		x = Gdx.graphics.getWidth()/10 - Helper.getImageFromAssets(AssetConstants.IMG_MUSIC_OFF).getRegionWidth()/2;
//		y = 3 * Gdx.graphics.getHeight()/4- Helper.getImageFromAssets(AssetConstants.IMG_MUSIC_OFF).getRegionHeight()/2;
		
		x = 48*GameMenuInfo.ratio_w - Helper.getImageFromAssets(AssetConstants.IMG_MUSIC_OFF).getRegionWidth()/2;
		y = 640.5f*GameMenuInfo.ratio_h- Helper.getImageFromAssets(AssetConstants.IMG_MUSIC_OFF).getRegionHeight()/2;
		
		ButtonMusicControl buttonMusic = new ButtonMusicControl(x, y, Helper.getImageFromAssets(AssetConstants.IMG_MUSIC_OFF).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_MUSIC_OFF).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_MUSIC_OFF);
		this.elements.add(buttonMusic);
		subscribeToControllingInteraction(buttonMusic, InteractionTouch.class);
	
		//sound
//		y = Gdx.graphics.getHeight()/4 - Helper.getImageFromAssets(AssetConstants.IMG_SOUND_OFF).getRegionHeight()/2;
		y = 213.5f*GameMenuInfo.ratio_h - Helper.getImageFromAssets(AssetConstants.IMG_SOUND_OFF).getRegionHeight()/2;

		ButtonSoundControl buttonSound = new ButtonSoundControl(x, y, Helper.getImageFromAssets(AssetConstants.IMG_SOUND_OFF).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_SOUND_OFF).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_SOUND_OFF);
		this.elements.add(buttonSound);
		subscribeToControllingInteraction(buttonSound, InteractionTouch.class);
	
		//back
//		x = 8 *Gdx.graphics.getWidth()/10 - Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
//		y = (Gdx.graphics.getHeight() /10)+ (50);//* GameMenuInfo.ratio_h);
		
		x = 384*GameMenuInfo.ratio_w - Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
		y = 135.5f*GameMenuInfo.ratio_h;//* GameMenuInfo.ratio_h);
		
		ButtonBackToMainmenu buttonBack = new ButtonBackToMainmenu(x, y, Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_BACK);
		this.elements.add(buttonBack);
		subscribeToControllingInteraction(buttonBack, InteractionTouch.class);
	
	}

	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}
}
