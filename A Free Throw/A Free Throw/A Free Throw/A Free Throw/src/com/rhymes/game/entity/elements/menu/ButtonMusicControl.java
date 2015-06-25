package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.helpers.Helper;

public class ButtonMusicControl extends Button {

//	SoundHandler soundhandler = new SoundHandler();
	private String imagePath;
	private boolean stateOn = true;
	
	public ButtonMusicControl(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;
	}
	
//	public String setImagePath(boolean active){
//		if(active)
//			this.imagePath = AssetConstants.IMG_MUSIC_ON;
//	
//		
//		else
//			this.imagePath = AssetConstants.IMG_MUSIC_OFF;
//		
//		return imagePath;
//		
//	}
	
	@Override
	public void onTouch(Point p) {
		
		if(this.checkHit(p)){
			Helper.println("Music control...............................................");
			stateOn  = !stateOn;
			StartupInfo.sound_handler.set_music_active(stateOn);
//			this.setImagePath(stateOn);
			
			if(stateOn)
			{
				//set the picture when music is on
				this.imagePath = AssetConstants.IMG_MUSIC_ON;
				this.setImage(Helper.getImageFromAssets(imagePath));
			}
			else
			{
//				//set the picture when music is off
				this.imagePath = AssetConstants.IMG_MUSIC_OFF;
				this.setImage(Helper.getImageFromAssets(imagePath));
			}
			
		}
	}

	public ButtonMusicControl(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
