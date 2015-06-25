package com.rhymes.game.entity.elements.arnold.stage.menu.ui;

import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.arnold.stage.menu.mainmenu.MainMenuArnold;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;
import com.rhymes.helpers.Helper;

public class BackToLevelMenuFrom_UI_Arnold extends Button{
	String imagePath_1, imagePath_2;

	public BackToLevelMenuFrom_UI_Arnold(float x, float y, float width, float height,
			int zIndex, String imagePath_1, String imagePath_2) {
		super(x, y, width, height, zIndex, imagePath_1);

		this.imagePath_1 = imagePath_1;
		this.imagePath_2 = imagePath_2;
	}


	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			
			this.image = Helper.getImageFromAssets(imagePath_2);
			
			StartupInfo.sound_handler.stopMusic();
			StartupInfo.sound_handler.playMusic(musicType.MUSIC_MENU);
			
			GlobalVars.ge.loadStage(new MainMenuArnold());		

		}
	}
}
