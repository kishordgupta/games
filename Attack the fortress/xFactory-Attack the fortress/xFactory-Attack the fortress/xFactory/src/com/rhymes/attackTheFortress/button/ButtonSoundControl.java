package com.rhymes.attackTheFortress.button;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;
import com.rhymes.helpers.Helper;

public class ButtonSoundControl extends Button{
	
	SoundHandler soundhandler = StartupInfo.sound_handler;
	private String imagePath;

	public ButtonSoundControl(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

		this.imagePath = imagePath;

	}
	
	public String setImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstants.IMG_BTN_SOUND_ASE;
		
		else
			this.imagePath = AssetConstants.IMG_BTN_SOUND_NAI;
		
		return imagePath;
		
	}
	
	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");

		if(this.checkHit(p)){
			Helper.println("Sound control...");
//			GlobalVars.ge.loadStage(new XLevel());		
			
		//	if(soundhandler.is_sound_active()){
			if(soundhandler.is_sound_active()){
				soundhandler.set_sound_active(false);			
//				this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_SOUND_OFF_PRESSED));
				this.setImage(Helper.getImageFromAssets(setImagePath(false)));
				soundhandler.set_music_active(false);
				soundhandler.pauseMusic();
			}
			
			else{
				soundhandler.set_sound_active(true);
				soundhandler.set_music_active(true);
				soundhandler.playMusic(musicType.MUSIC_BGM);
//				this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_SOUND_ON_PRESSED));
				this.setImage(Helper.getImageFromAssets(setImagePath(true)));
			}
		}
	}

	public ButtonSoundControl(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
