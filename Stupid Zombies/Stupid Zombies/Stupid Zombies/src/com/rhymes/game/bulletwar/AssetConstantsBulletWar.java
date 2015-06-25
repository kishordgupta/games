package com.rhymes.game.bulletwar;

import com.badlogic.gdx.Gdx;
import com.rhymes.helpers.Helper;

public class AssetConstantsBulletWar {

	
	public static float ratio_w						= (float)Gdx.graphics.getWidth() / 960f;
	public static float ratio_h 					= (float)Gdx.graphics.getHeight() / 640f;
	

	private static String PREFIX				= "games/StupidZombies/";
	
	static{
		if(Gdx.graphics.getWidth() > 480)
			PREFIX = PREFIX + "2x/";
		else if(Gdx.graphics.getWidth() > 350)
			PREFIX = PREFIX + "1x/";
		
		Helper.println("\n\nSetting AssetConstants prefix: " + PREFIX);
	}
	
	
	//splash
	
		public static final String splash_bg 			= "games/StupidZombies/default/default.png";
		
	
	//main
		
		public static final String main_bg 					= PREFIX+"images/menu/main/main_bg.png";
		public static final String music_on 				= PREFIX+"images/menu/main/on.png";
		public static final String music_off 				= PREFIX+"images/menu/main/off.png";
		public static final String play 					= PREFIX+"images/menu/main/play.png";
		public static final String exit 					= PREFIX+"images/menu/main/exit.png";

		
	//pack
		public static final String pack_bg 					= PREFIX+"images/menu/pack/level_bg.png";
		public static final String back 					= PREFIX+"images/menu/pack/back.png";
		public static final String pack 					= PREFIX+"images/menu/pack/pack.png";
		public static final String pack_lock 				= PREFIX+"images/menu/pack/pack_lock.png";
		
		
	//level	
		public static final String level_bg 				= PREFIX+"images/menu/level/level_bg.png";
		public static final String level 					= PREFIX+"images/menu/level/level.png";
		public static final String level_lock 				= PREFIX+"images/menu/level/level_lock.png";
		public static final String level_img 				= PREFIX+"images/menu/level/level_image.png";
		public static final String nexxt	 				= PREFIX+"images/menu/pack/nexxt.png";

		
	//over	
		public static final String score_bg 				= PREFIX+"images/menu/over/score_bg.png";
		public static final String complete 				= PREFIX+"images/menu/over/level_complete.png";
		public static final String failed	 				= PREFIX+"images/menu/over/level_failed.png";
		public static final String new_high 				= PREFIX+"images/menu/over/new_high.png";
		public static final String next		 				= PREFIX+"images/menu/over/nextlevel.png";
		public static final String retry	 				= PREFIX+"images/menu/over/retry.png";
		public static final String star_active 				= PREFIX+"images/menu/over/star_active.png";
		public static final String star_deactive			= PREFIX+"images/menu/over/star_deactive.png";
		
		
	//pause	
		public static final String paused	 				= PREFIX+"images/menu/over/paused.png";
		public static final String resume	 				= PREFIX+"images/menu/over/resume.png";
		
		
	
		public static String RES_PREFIX="games/";
		
		// Font
		public static final String FONT_1 			= RES_PREFIX + "font/candarab.ttf";
		public static final String FONT_2 			= RES_PREFIX + "font/imagica.ttf";
		public static final String FONT_NEON2	 	= RES_PREFIX + "font/imagica.ttf";

}
