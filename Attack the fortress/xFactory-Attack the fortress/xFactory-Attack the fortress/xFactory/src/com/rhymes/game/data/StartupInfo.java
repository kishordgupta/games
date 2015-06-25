package com.rhymes.game.data;

//import com.rhymes.game.stage.menus.MainMenu;
import com.rhymes.attackTheFortress.AttackTheFortressLevel;
import com.rhymes.attackTheFortress.button.LogoView;
import com.rhymes.attackTheFortress.button.MainView;
import com.rhymes.attackTheFortress.button.ScoreView;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;

public class StartupInfo {
	private static StageBase startupStage; 
	public static int debugLevel = 0;
	public static SoundHandler sound_handler;

	private static boolean soundEnabled = true;
	
	/**
	 * Must set the startup stage in this function
	 * @return
	 */
	public static StageBase getStartupStage()
	{
		// Change the startup stage here
		if(startupStage == null)
  //  <<--sundaylawn starting point
//			startupStage = new AttackTheFortressLevel();
//			startupStage = new XLevel(0, new ResultBTRTime());
//			startupStage = new XLevel(5, new ResultBTRMAP(3));
//			startupStage = new MenuHome();
//			startupStage = new LevelTest();
			
			if(soundEnabled){
				sound_handler = new SoundHandler();
				sound_handler.set_music_active(true);
				sound_handler.set_sound_active(true);
				sound_handler.playMusic(musicType.MUSIC_BGM);
			}
//			startupStage = new MainMenu();   //  <<--burn the rope starting point
//			startupStage = new TestLevel();
		
//		startupStage = new TestTileLevel();
		startupStage = new LogoView(); 
		return startupStage;
	}
}
