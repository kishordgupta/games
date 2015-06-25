package com.rhymes.attackTheFortress.button;

import com.rhymes.attackTheFortress.AttackTheFortressLevel;
import com.rhymes.attackTheFortress.LevelInfo;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;
import com.rhymes.helpers.Helper;

public class ButtonModeNormal extends Button {

	public ButtonModeNormal(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonModeNormal(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		Helper.println("ARIF.......", true);
	}
	@Override
	public void onEvent(Point p) {
		
		if(this.checkHit(p)){
			//this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_BTN_BACK_DOWN));
			//Helper.println("Back to main menu...");
			LevelInfo.GAME_MODE=2;
			StartupInfo.sound_handler.pauseMusic();
			StartupInfo.sound_handler.playMusic(musicType.MUSIC_LEVEL);
		
			GlobalVars.ge.loadStage(new AttackTheFortressLevel());
			
		}
	}

}
