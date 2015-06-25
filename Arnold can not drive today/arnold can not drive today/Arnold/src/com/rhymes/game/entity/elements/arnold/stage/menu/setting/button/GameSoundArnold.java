package com.rhymes.game.entity.elements.arnold.stage.menu.setting.button;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.arnold.stage.menu.setting.SettingMenuArnold;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;
import com.rhymes.helpers.Helper;

public class GameSoundArnold extends Button{
	
	SoundHandler soundhandler = StartupInfo.sound_handler;

	String imagePath;

	public GameSoundArnold(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

		this.imagePath = imagePath;

	}

	
	public String setImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstants.bg_sound_on_arnold;
		
		else
			this.imagePath = AssetConstants.bg_sound_off_arnold;
		
		return imagePath;
		
	}


	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			
			if(soundhandler.is_menu_active()){
//				soundhandler.set_music_active(false);
				soundhandler.set_menu_active(false);
				
				soundhandler.pauseMusic(musicType.MUSIC_MENU);
				
				this.setImage(Helper.getImageFromAssets(setImagePath(false)));
				SettingMenuArnold.is_game_sound_active = false;
			}
			
			else{
//				soundhandler.set_music_active(true);
				soundhandler.set_menu_active(true);
				
				soundhandler.playMusic(musicType.MUSIC_MENU);
				
				this.setImage(Helper.getImageFromAssets(setImagePath(true)));
				SettingMenuArnold.is_game_sound_active = true;

			}
			
		}
	}

}
