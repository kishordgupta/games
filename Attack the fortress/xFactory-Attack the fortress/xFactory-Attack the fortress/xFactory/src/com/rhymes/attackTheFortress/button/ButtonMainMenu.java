package com.rhymes.attackTheFortress.button;

import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;

public class ButtonMainMenu extends Button {

	public ButtonMainMenu(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonMainMenu(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onEvent(Point p) {
		
		if(this.checkHit(p)){
			//this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_BTN_BACK_DOWN));
			//Helper.println("Back to main menu...");
			StartupInfo.sound_handler.pauseMusic();
			StartupInfo.sound_handler.playMusic(musicType.MUSIC_BGM);
		
			GlobalVars.ge.loadStage(new MainView());			
		}
	}

}
