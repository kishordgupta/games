package com.rhymes.game.fruitdelivery.menu.settings.res;

import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.fruitdelivery.AssetConstantsFruit;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class SoundFruit extends Button{
	String imagePath;

	public SoundFruit(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = setSpecifiedImagePath(StartupInfo.sound_handler.is_music_active());

	}

	
	@Override
	public void init()
	{
		this.image = Helper.getImageFromAssets(imagePath);
	}
	
	public String setSpecifiedImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstantsFruit.sound_on;
		else
			this.imagePath = AssetConstantsFruit.sound_off;
		
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
