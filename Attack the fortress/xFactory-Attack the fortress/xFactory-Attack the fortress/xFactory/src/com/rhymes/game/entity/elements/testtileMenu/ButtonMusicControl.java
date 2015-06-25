package com.rhymes.game.entity.elements.testtileMenu;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;
import com.rhymes.ge.pw.sound.SoundHandler.soundType;
import com.rhymes.helpers.Helper;

public class ButtonMusicControl extends Button {

//	SoundHandler soundhandler = new SoundHandler();
	private String imagePath;
	
	public ButtonMusicControl(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;
	}
	
	public String setImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstants.IMG_BTN_SPEAKER_ON;
	
		
		else
			this.imagePath = AssetConstants.IMG_BTN_SPEAKER_OFF;
		
		return imagePath;
		
	}
	
	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-240f*LevelInfo.ratioX;
		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-160f*LevelInfo.ratioY;

		if(this.checkHit(p)){
			Helper.println("Music control...");
//			GlobalVars.ge.loadStage(new XLevel());
			
			if(StartupInfo.sound_handler.is_music_active()){
				StartupInfo.sound_handler.set_music_active(false);
				StartupInfo.sound_handler.pauseMusic();
		//		this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_MUSIC_OFF_PRESSED));
				this.setImage(Helper.getImageFromAssets(setImagePath(false)));
			}
			
			else{
				StartupInfo.sound_handler.set_music_active(true);
				StartupInfo.sound_handler.playMusic(musicType.MUSIC_BGM);
		//		this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_MUSIC_ON_PRESSED));
				this.setImage(Helper.getImageFromAssets(setImagePath(true)));
			}
		}
	}

	public ButtonMusicControl(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
