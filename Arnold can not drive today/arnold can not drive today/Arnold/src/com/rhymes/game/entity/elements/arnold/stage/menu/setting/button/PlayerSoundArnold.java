package com.rhymes.game.entity.elements.arnold.stage.menu.setting.button;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.arnold.stage.menu.setting.SettingMenuArnold;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.helpers.Helper;

public class PlayerSoundArnold  extends Button{
	
	SoundHandler soundhandler = StartupInfo.sound_handler;

	String imagePath;

	public PlayerSoundArnold(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;

	}

	
	public String setImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstants.player_sound_on_arnold;
		
		else
			this.imagePath = AssetConstants.player_sound_off_arnold;
		
		return imagePath;
		
	}


	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			
			if(soundhandler.is_arnold_active()){
//				soundhandler.set_music_active(false);	
				soundhandler.set_arnold_active(false);	
				
				this.setImage(Helper.getImageFromAssets(setImagePath(false)));
				SettingMenuArnold.is_arnold_sound_active = false;

			}
			else{
//				soundhandler.set_music_active(true);
				soundhandler.set_arnold_active(true);	
				
				this.setImage(Helper.getImageFromAssets(setImagePath(true)));
				SettingMenuArnold.is_arnold_sound_active = true;

			}
		}
	}

}
