package com.rhymes.game.entity.elements.testtileMenu;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.testtile.scorelbl;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class OptionMenu extends StageBase{
	
	float x;
	float y;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_SOUND);
		
		assetPack.addTexture(AssetConstants.IMG_BTN_SPEAKER_ON);
		assetPack.addTexture(AssetConstants.IMG_BTN_SPEAKER_OFF);
		assetPack.addTexture(AssetConstants.IMG_BTN_BKGMAPVIEW);
		assetPack.addTexture(AssetConstants.IMG_BTN_CHALLENGES);
		assetPack.addTexture(AssetConstants.IMG_BTN_ARCADEPLAY);
		assetPack.addTexture(AssetConstants.IMG_MUSIC);
		assetPack.addTexture(AssetConstants.IMG_BTN_BACK);
//		
//		assetPack.addTexture(AssetConstants.IMG_SOUND_ON);
//		assetPack.addTexture(AssetConstants.IMG_SOUND_ON_PRESSED);
//		assetPack.addTexture(AssetConstants.IMG_SOUND_OFF);
//		assetPack.addTexture(AssetConstants.IMG_SOUND_OFF_PRESSED);
		return assetPack;
	}
	private scorelbl sound;

	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BTN_BKGMAPVIEW));
		GlobalVars.ge.getScreen().cam.position.set( Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f,0f);
		
		String imagePathMusic;
		if(StartupInfo.sound_handler.is_music_active())
			{
				imagePathMusic=AssetConstants.IMG_BTN_SPEAKER_ON;
			}
		else
			{
				imagePathMusic=AssetConstants.IMG_BTN_SPEAKER_OFF;
			}
		String imagePathSound;
		if(StartupInfo.sound_handler.is_sound_active())
		{
			imagePathSound=AssetConstants.IMG_BTN_SPEAKER_ON;
		}
	else
		{
		imagePathSound=AssetConstants.IMG_BTN_SPEAKER_OFF;
		}
		//music
//		x = Gdx.graphics.getWidth()/10 - Helper.getImageFromAssets(AssetConstants.IMG_MUSIC_OFF).getRegionWidth()/2;
//		y = 3 * Gdx.graphics.getHeight()/4- Helper.getImageFromAssets(AssetConstants.IMG_MUSIC_OFF).getRegionHeight()/2;
		
//		x = 48*GameMenuInfo.ratio_w - Helper.getImageFromAssets(AssetConstants.IMG_MUSIC_OFF).getRegionWidth()/2;
//		y = 640.5f*GameMenuInfo.ratio_h- Helper.getImageFromAssets(AssetConstants.IMG_MUSIC_OFF).getRegionHeight()/2;

		x=160f*LevelInfo.ratioX;
		y=160f*LevelInfo.ratioY;
		sound=new scorelbl(x,y ,128f*LevelInfo.ratioX, 32f*LevelInfo.ratioY,Helper.getImageFromAssets(AssetConstants.IMG_SOUND).getTexture());
		this.elements.add(sound);
		
		x=160f*LevelInfo.ratioX;
		y=110f*LevelInfo.ratioY;
		sound=new scorelbl(x,y ,128f*LevelInfo.ratioX, 32f*LevelInfo.ratioY,Helper.getImageFromAssets(AssetConstants.IMG_MUSIC).getTexture());
		this.elements.add(sound);
		
		x=290f*LevelInfo.ratioX;
		y=160f*LevelInfo.ratioY;
		ButtonSoundControl buttonSound = new ButtonSoundControl(x, y, 32f*LevelInfo.ratioX,32f*LevelInfo.ratioY, 1,imagePathSound );
		this.elements.add(buttonSound);
		subscribeToControllingInteraction(buttonSound, InteractionTouch.class);
		x=290f*LevelInfo.ratioX;
		y=110f*LevelInfo.ratioY;
		ButtonMusicControl music = new ButtonMusicControl(x, y, 32f*LevelInfo.ratioX,32f*LevelInfo.ratioY, 1, imagePathMusic);
		this.elements.add(music);
		subscribeToControllingInteraction(music, InteractionTouch.class);
//		x=285f;
//		y=80f;
//		ResetArcadeplay resetarcadeplay = new ResetArcadeplay(x, y, 60f, 30f, 1, AssetConstants.IMG_BTN_ARCADEPLAY);
//		this.elements.add(resetarcadeplay);
//		subscribeToControllingInteraction(resetarcadeplay, InteractionTouch.class);
	
		//sound
//		y = Gdx.graphics.getHeight()/4 - Helper.getImageFromAssets(AssetConstants.IMG_SOUND_OFF).getRegionHeight()/2;
//		y = 213.5f*GameMenuInfo.ratio_h - Helper.getImageFromAssets(AssetConstants.IMG_SOUND_OFF).getRegionHeight()/2;
			
		//back
//		x = 8 *Gdx.graphics.getWidth()/10 - Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
//		y = (Gdx.graphics.getHeight() /10)+ (50);//* GameMenuInfo.ratio_h);
		
//		x = 384*GameMenuInfo.ratio_w - Helper.getImageFromAssets(AssetConstants.IMG_BACK).getRegionWidth()/2;// * GameMenuInfo.ratio_w;
//		y = 135.5f*GameMenuInfo.ratio_h;//* GameMenuInfo.ratio_h);
		x=160f*LevelInfo.ratioX;
		y=50f*LevelInfo.ratioY;
		ButtonBackToMainmenu buttonBack = new ButtonBackToMainmenu(x, y, 170f*LevelInfo.ratioX, 50f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_BACK);
		this.elements.add(buttonBack);
		subscribeToControllingInteraction(buttonBack, InteractionTouch.class);
	
	}

	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}
}
