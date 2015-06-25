package com.rhymes.game.data;

import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.menus.MainMenu;
import com.rhymes.game.stage.menus.MenuHome;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;

public class StartupInfo {
	private static StageBase startupStage;
	public static int debugLevel = 0; //0 for deployment

	public static SoundHandler sound_handler;

	/**
	 * Must set the startup stage in this function
	 * 
	 * @return
	 */
	public static StageBase getStartupStage() {
		// Change the startup stage here
		if (startupStage == null) {
			// startupStage = new XLevel(0, new ResultBTRTime());
			// startupStage = new XLevel(5, new ResultBTRMAP(3));
			// startupStage = new MenuHome();
			// startupStage = new LevelTest();
			sound_handler = new SoundHandler();
			sound_handler.set_music_active(true);
			sound_handler.set_sound_active(true);
			if(Constants.musicOn)
				StartupInfo.sound_handler.playMusic(musicType.MUSIC_MENU);
			else StartupInfo.sound_handler.pauseMusic();
			startupStage = new MainMenu();
		}
		// startupStage = new BounceTest();
		return startupStage;

	}
}
