package com.rhymes.game.data;

import com.rhymes.game.stage.levels.LevelActionDriver;
import com.rhymes.game.stage.menus.GameOverScreen;
import com.rhymes.game.stage.menus.MenuActionDriver;
import com.rhymes.game.stage.menus.Splash;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;

public class StartupInfo {
	
	public static int DEV_STATE_DEVELOPING = 1;
	public static int DEV_STATE_TEST = 2;
	public static int DEV_STATE_DEPLOY = 3;
	
	private static StageBase startupStage; 
	public static int debugLevel = 0;
	public static SoundHandler sound_handler;

	private static boolean soundEnabled = true;
	public static int devState = DEV_STATE_DEVELOPING;
	
	
	public static boolean soundActive = true;
	
	
	/**
	 * Must set the startup stage in this function
	 * @return
	 */
	public static StageBase getStartupStage()
	{
		// Change the startup stage here
		if(startupStage == null)			
			if(soundEnabled){
				sound_handler = new SoundHandler();
				sound_handler.set_music_active(true);
				sound_handler.set_sound_active(true);
				sound_handler.playMusic(musicType.MUSIC_BACK);
			}
//		startupStage = new LevelActionDriver();
//		startupStage = new MenuActionDriver();
		startupStage = new Splash();
//		startupStage = new GameOverScreen(new Score());
		return startupStage;
	}
}
