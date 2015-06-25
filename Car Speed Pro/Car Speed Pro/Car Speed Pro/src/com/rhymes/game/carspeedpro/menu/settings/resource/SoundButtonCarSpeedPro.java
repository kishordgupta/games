package com.rhymes.game.carspeedpro.menu.settings.resource;

import com.rhymes.game.carspeedpro.AssetConstantsCarSpeedPro;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class SoundButtonCarSpeedPro extends Button{
	
	String imagePath;
	
	public SoundButtonCarSpeedPro(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

		this.imagePath = setSpecifiedImagePath(StartupInfo.sound_handler.is_music_active());
//		Helper.printTest("Sound state: " + StartupInfo.sound_handler.is_music_active());
	}

	public String setSpecifiedImagePath(boolean active){
//		Helper.printTest("Sound state: " + active);
		if(active)
			this.imagePath = AssetConstantsCarSpeedPro.on;
		else
			this.imagePath = AssetConstantsCarSpeedPro.off;
		this.image = Helper.getImageFromAssets(imagePath);
		return imagePath;
	}

	@Override
	public void onEvent(Point htiPoint) {
		if(checkHit(htiPoint)){
			if(StartupInfo.sound_handler.is_music_active()){
				StartupInfo.sound_handler.set_music_active(false);
				StartupInfo.sound_handler.pauseMusic();
				StartupInfo.sound_handler.set_sound_active(false);
				this.setImage(Helper.getImageFromAssets(setSpecifiedImagePath(false)));
			}
			else{
				StartupInfo.sound_handler.set_music_active(true);
				StartupInfo.sound_handler.set_sound_active(true);			
//				StartupInfo.sound_handler.playMusic(musicType.MUSIC_BGM);
		//		this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_MUSIC_ON_PRESSED));
				this.setImage(Helper.getImageFromAssets(setSpecifiedImagePath(true)));
			}
		}
	}
	
	
}
