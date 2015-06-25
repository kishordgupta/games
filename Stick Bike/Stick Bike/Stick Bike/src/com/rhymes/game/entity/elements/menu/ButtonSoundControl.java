package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.helpers.Helper;

public class ButtonSoundControl extends Button{
	
//	SoundHandler soundhandler = new SoundHandler();
	private String imagePath;
	private boolean stateOn = true;

	public ButtonSoundControl(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

		this.imagePath = imagePath;

	}
	
//	public String setImagePath(boolean active){
//		if(active)
//			this.imagePath = AssetConstants.IMG_SOUND_OFF;
//		
//		else
//			this.imagePath = AssetConstants.IMG_SOUND_ON;
//		
//		return imagePath;
//		
//	}
//	
	@Override
	public void onEvent(Point p) {
		if(this.checkHit(p)){
			Helper.println("Sound control...");
			stateOn  = !stateOn;
			StartupInfo.sound_handler.set_sound_active(stateOn);

			if(stateOn)
			{
				//set the picture when sound is on
				this.imagePath = AssetConstants.IMG_SOUND_ON;
				this.setImage(Helper.getImageFromAssets(imagePath));
			}
			else
			{
				//set the picture when sound is off
				this.imagePath = AssetConstants.IMG_SOUND_OFF;
				this.setImage(Helper.getImageFromAssets(imagePath));
			}
		}
	}

	public ButtonSoundControl(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
