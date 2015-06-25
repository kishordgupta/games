package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.helpers.Helper;

public class ButtonSoundControl extends Button{
	
	SoundHandler soundhandler = new SoundHandler();
	private String imagePath;

	public ButtonSoundControl(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

		this.imagePath = imagePath;

	}
	
	public String setImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstants.IMG_SOUND_OFF;
		
		else
			this.imagePath = AssetConstants.IMG_SOUND_ON;
		
		return imagePath;
		
	}
	
	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("Sound control...");
//			GlobalVars.ge.loadStage(new XLevel());		
			
			if(soundhandler.is_sound_active()){
				soundhandler.set_sound_active(false);			
				this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_SOUND_OFF_PRESSED));
				this.setImage(Helper.getImageFromAssets(setImagePath(false)));
			}
			
			else{
				soundhandler.set_sound_active(true);
				this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_SOUND_ON_PRESSED));
				this.setImage(Helper.getImageFromAssets(setImagePath(true)));
			}
		}
	}

	public ButtonSoundControl(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
