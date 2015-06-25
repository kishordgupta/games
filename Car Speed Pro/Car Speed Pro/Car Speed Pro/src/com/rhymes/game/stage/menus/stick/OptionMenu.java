package com.rhymes.game.stage.menus.stick;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
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
		assetPack.addTexture(AssetConstants.IMG_MUSIC);
		assetPack.addTexture(AssetConstants.IMG_BTN_BACK);
		return assetPack;
	}
	private scorelbl sound;

	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_SETTINGS_BKG));
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

		x=350f*LevelInfo.ratioX;
		y=400f*LevelInfo.ratioY;
		sound=new scorelbl(x,y ,200f*LevelInfo.ratioX, 70f*LevelInfo.ratioY,Helper.getImageFromAssets(AssetConstants.IMG_SOUND).getTexture());
		this.elements.add(sound);
		
		x=350f*LevelInfo.ratioX;
		y=300f*LevelInfo.ratioY;
		sound=new scorelbl(x,y ,200f*LevelInfo.ratioX, 70f*LevelInfo.ratioY,Helper.getImageFromAssets(AssetConstants.IMG_MUSIC).getTexture());
		this.elements.add(sound);
		
		x=604f*LevelInfo.ratioX;
		y=400f*LevelInfo.ratioY;
		ButtonSoundControl buttonSound = new ButtonSoundControl(x, y, 70f*LevelInfo.ratioX,70f*LevelInfo.ratioY, 1,imagePathSound );
		this.elements.add(buttonSound);
		subscribeToControllingInteraction(buttonSound, InteractionTouch.class);

		x=604f*LevelInfo.ratioX;
		y=300f*LevelInfo.ratioY;
		ButtonMusicControl music = new ButtonMusicControl(x, y, 70f*LevelInfo.ratioX,70f*LevelInfo.ratioY, 1, imagePathMusic);
		this.elements.add(music);
		subscribeToControllingInteraction(music, InteractionTouch.class);
		
		x=350f*LevelInfo.ratioX;
		y=100f*LevelInfo.ratioY;
		ButtonBackToMainmenu buttonBack = new ButtonBackToMainmenu(x, y, 258f*LevelInfo.ratioX, 77f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_BACK);
		this.elements.add(buttonBack);
		subscribeToControllingInteraction(buttonBack, InteractionTouch.class);
	
	}

	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}
}
