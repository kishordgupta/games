package com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.button;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;
import com.rhymes.helpers.Helper;

public class SoundButton extends Button {
	SoundHandler soundhandler = StartupInfo.sound_handler;

	private boolean active;
	String imagePath;
	
	public SoundButton(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;

	}
	
	public String setImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstants.sound_on;
		
		else
			this.imagePath = AssetConstants.sound_off;
		
		return imagePath;
		
	}
	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			
//			Helper.println("sound button pressed");
			if(!isActive()){
				setActive(true);
//				((Deck)GlobalVars.ge.getCurrentStage()).last_touched_button = this;
			}
			
			else{
				setActive(false);
			}
			
			if(soundhandler.is_sound_active()){
				soundhandler.set_sound_active(false);	
				
				soundhandler.pauseMusic();
				
				this.setImage(Helper.getImageFromAssets(setImagePath(false)));
			}
			
			else{
				soundhandler.set_sound_active(true);
				
				soundhandler.playMusic(musicType.MUSIC_ARNOLD);
				
				this.setImage(Helper.getImageFromAssets(setImagePath(true)));
			}
		}
	}


	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

}
