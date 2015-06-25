package com.rhymes.game.data;

import com.rhymes.game.fruitdelivery.menu.gameover.GameOverMenuFruit;
import com.rhymes.game.fruitdelivery.menu.main.MainMenuFruit;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.game.stage.menus.stick.LevelInfo;
import com.rhymes.game.stage.menus.stick.MainMenu;
import com.rhymes.game.stage.menus.stick.ScoreScreen;
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
//			startupStage = new BikeLevel(1);
//			startupStage = new MainMe
			startupStage = new MainMenuFruit();
//			startupStage = new GameOverMenuFruit(false);
//			startupStage = new ScoreScreen(LevelInfo.GAME_FAILED);
			
		}
		// startupStage = new BounceTest();
		return startupStage;

	}
}
