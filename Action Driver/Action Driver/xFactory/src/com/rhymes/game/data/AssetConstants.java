package com.rhymes.game.data;

import com.badlogic.gdx.Gdx;
import com.rhymes.helpers.Helper;

public class AssetConstants {

	private static String PREFIX						= "games/action driver/";
	private static String RES_PREFIX						= "games/action driver/";
	static{
		
		if(StartupInfo.devState == StartupInfo.DEV_STATE_DEVELOPING){
			PREFIX = PREFIX + "dev/";
		}
		else{
			if(Gdx.graphics.getWidth() > 480)
				PREFIX = PREFIX + "3x/";
			else if(Gdx.graphics.getWidth() > 350)
				PREFIX = PREFIX + "2x/";
			else 
				PREFIX = PREFIX + "1x/";
		}
		Helper.println("\n\nSetting AssetConstants prefix: " + PREFIX);
	}
	
	//COMMON
	public static final String IMG_SPLASH  				= PREFIX + "images/menu/splash.png";
	public static final String IMG_SPEAKER_ON  				= PREFIX + "images/menu/speaker_on.png";
	public static final String IMG_SPEAKER_OFF  				= PREFIX + "images/menu/speaker_off.png";
	
	// MENU_VIEW
	public static final String IMG_MENU_BACK 				= PREFIX + "images/menu/menu_back.png";
	public static final String IMG_START 				= PREFIX + "images/menu/start.png";
	public static final String IMG_QUIT 				= PREFIX + "images/menu/quit.png";
	
	public static final String IMG_PAUSE 				= PREFIX + "images/menu/pause.png";
	public static final String IMG_PLAY 				= PREFIX + "images/menu/play.png";
	
	// Game Over Screen
	public static final String IMG_RELOAD 				= PREFIX + "images/menu/reload.png";
	public static final String IMG_QUIT_ICON 				= PREFIX + "images/menu/quit_icon.png";
	
	// GAME VIEW
	public static final String IMG_ROAD_1 				= PREFIX + "images/road.png";
	public static final String IMG_CAR_RED 				= PREFIX + "images/car_red.png";
	public static final String IMG_CAR 				= PREFIX + "images/car.png";
	
	public static final String IMG_CONE = PREFIX + "images/cone.png";
	public static final String IMG_BOX = PREFIX + "images/box.png";
	public static final String IMG_CASH = PREFIX + "images/cash.png";
//	public static final String IMG_GREEN_BIN = PREFIX + "images/green_bin.png";
	public static final String IMG_GREEN_BIN = PREFIX + "images/TirePile.png";
	public static final String IMG_RED_GAS_CAN = PREFIX + "images/redgascan.png";
	public static final String IMG_CARGO = PREFIX + "images/cargo.png";
	public static final String IMG_GIFT_1 = PREFIX + "images/gift1.png";
	public static final String IMG_ROCK = PREFIX + "images/rock.png";
	
	// Font
	public static final String FONT_1 = RES_PREFIX + "font/CANDARAB.TTF";
	public static final String FONT_2 = RES_PREFIX + "font/Imagica.ttf";
	
	
	// Sound
	public static final String S_MENU = RES_PREFIX + "sound/menu.wav";
	public static final String S_SCORE = RES_PREFIX + "sound/enter_effect.wav";
	public static final String S_OBSTACLES = RES_PREFIX + "sound/hit.wav";
	public static final String S_DIE = RES_PREFIX + "sound/goal_effect.wav";
	public static final String S_CAR = RES_PREFIX + "sound/truck_drive.wav";
	
	
}

