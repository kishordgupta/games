package com.rhymes.game.data;

import com.rhymes.game.bulletwar.menu.main.MainMenuBullet;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;

public class StartupInfo {
	private static StageBase startupStage;
	public static int debugLevel = 0;

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
			StartupInfo.sound_handler.playGameMusic(musicType.MUSIC_MENU);
//			startupStage = new MainMenu();
//			startupStage = new BikeLevel(3);
//			startupStage = new MainMenu();
//			startupStage = new GameOverMenuBullet(false, 4, 3);
			startupStage = new MainMenuBullet();

//			startupStage = new ScoreScreen(LevelInfo.GAME_FAILED);
			
		}
		// startupStage = new BounceTest();
		return startupStage;

	}
}
