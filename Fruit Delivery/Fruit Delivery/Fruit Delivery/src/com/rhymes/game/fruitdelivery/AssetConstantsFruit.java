package com.rhymes.game.fruitdelivery;

import com.badlogic.gdx.Gdx;
import com.rhymes.helpers.Helper;

public class AssetConstantsFruit {

	public static float ratio_w						= (float)Gdx.graphics.getWidth() / 960f;
	public static float ratio_h 					= (float)Gdx.graphics.getHeight() / 640f;
	

	private static String PREFIX				= "games/FruitDelivery/";
	
	static{
		if(Gdx.graphics.getWidth() > 480)
			PREFIX = PREFIX + "1x/";
		else if(Gdx.graphics.getWidth() > 350)
			PREFIX = PREFIX + "1x/";
		else 
			PREFIX = PREFIX + "1x/";
		
		Helper.println("\n\nSetting AssetConstants prefix: " + PREFIX);
	}
	
	
	
	
	//splash
	
	public static final String splash_bg 			= "games/FruitDelivery/default/splash.png";
	
	

	/**
	 * Image Resources
	 */
	
	
	
	//main menu
	
	public static final String main_bg 				= PREFIX+"images/main_menu/main_bg.png";
	
	public static final String play_h 				= PREFIX+"images/main_menu/play_high.png";
	public static final String play_n 				= PREFIX+"images/main_menu/play_nor.png";
	public static final String settings_h 			= PREFIX+"images/main_menu/settings_high.png";
	public static final String settings_n 			= PREFIX+"images/main_menu/settings_nor.png";
	
	
	//settings
	
	public static final String speed_img	 		= PREFIX+"images/settings_menu/speed.png";
	public static final String speed_slow 			= PREFIX+"images/settings_menu/slow.png";
	public static final String speed_normal			= PREFIX+"images/settings_menu/normal.png";
	public static final String speed_fast			= PREFIX+"images/settings_menu/fast.png";
	public static final String sound_img			= PREFIX+"images/settings_menu/sound.png";
	public static final String sound_on 			= PREFIX+"images/settings_menu/on.png";
	public static final String sound_off 			= PREFIX+"images/settings_menu/off.png";
	public static final String sel_image 			= PREFIX+"images/settings_menu/sel.png";
	public static final String sel_plexi_image		= PREFIX+"images/settings_menu/sel_plexi.png";
	public static final String back_settings_d		= PREFIX+"images/settings_menu/back_down.png";
	public static final String back_settings_nor	= PREFIX+"images/settings_menu/back_normal.png";


	
	//select vehicle menu
	
	public static final String back_h				= PREFIX+"images/select_vehicle_menu/back_high.png";
	public static final String back_nor				= PREFIX+"images/select_vehicle_menu/back_nor.png";
	public static final String ok_h					= PREFIX+"images/select_vehicle_menu/ok_high.png";
	public static final String ok_nor				= PREFIX+"images/select_vehicle_menu/ok_nor.png";
	
	public static final String truck_1				= PREFIX+"images/select_vehicle_menu/truck_1.png";
	public static final String truck_1_sel			= PREFIX+"images/select_vehicle_menu/truck_1_sel.png";
	public static final String truck_2				= PREFIX+"images/select_vehicle_menu/truck_2.png";
	public static final String truck_2_sel			= PREFIX+"images/select_vehicle_menu/truck_2_sel.png";
	public static final String truck_3				= PREFIX+"images/select_vehicle_menu/truck_3.png";
	public static final String truck_3_sel			= PREFIX+"images/select_vehicle_menu/truck_3_sel.png";

	
	
	//game over menu

	public static final String score_img						= PREFIX+"images/game_over/score.png";
	public static final String retry_nor						= PREFIX+"images/game_over/retry_normal.png";
	public static final String retry_d							= PREFIX+"images/game_over/retry_down.png";
	public static final String yes_h							= PREFIX+"images/game_over/yes_high.png";
	public static final String yes_nor							= PREFIX+"images/game_over/yes_nor.png";
	public static final String no_h								= PREFIX+"images/game_over/no_high.png";
	public static final String no_nor							= PREFIX+"images/game_over/no_nor.png";
	public static final String again							= PREFIX+"images/game_over/again.png";

	
	//level menu
	
	public static final String btn_backward						= PREFIX+"images/select_level_menu/backward.png";
	public static final String btn_forward						= PREFIX+"images/select_level_menu/forward.png";
	public static final String ls_back							= PREFIX+"images/select_level_menu/level_selection_button_back.png";
	
	
	//game
		
		//building
	public static final String recieve							= PREFIX+"images/game/buiding/receive.png";
	public static final String sender							= PREFIX+"images/game/buiding/sender.png";
	public static final String sender_pan_1						= PREFIX+"images/game/buiding/sender_pan_1.png";
	public static final String sender_pan_2						= PREFIX+"images/game/buiding/sender_pan_2.png";
	public static final String sender_pan_3						= PREFIX+"images/game/buiding/sender_pan_3.png";
	public static final String tree1							= PREFIX+"images/game/buiding/tree1.png";
	public static final String tree2							= PREFIX+"images/game/buiding/tree2.png";

		//ground
	public static final String ground_bg						= PREFIX+"images/game/ground/ground_bg.png";
	public static final String ground_pattern					= PREFIX+"images/game/ground/ground_pattern.png";
	public static final String pattern_0						= PREFIX+"images/game/ground/pattern_0.png";
	public static final String pattern_1						= PREFIX+"images/game/ground/pattern_1.png";
	public static final String pattern_3						= PREFIX+"images/game/ground/pattern_3.png";
	
		//other
	public static final String game_bg							= PREFIX+"images/game/other/bg.png";
	public static final String backward_act						= PREFIX+"images/game/other/backward_act.png";
	public static final String backward_nor						= PREFIX+"images/game/other/backward_nor.png";
	public static final String forward_act						= PREFIX+"images/game/other/forward_act.png";
	public static final String forward_nor						= PREFIX+"images/game/other/forward_nor.png";
	public static final String pause_act						= PREFIX+"images/game/other/pause_act.png";
	public static final String pause_nor						= PREFIX+"images/game/other/pause_nor.png";
	public static final String progress							= PREFIX+"images/game/other/progress.png";
	public static final String sprite_bg						= PREFIX+"images/game/other/sprite_bg.png";
	public static final String sprite_bgipad					= PREFIX+"images/game/other/sprite_bgipad.png";

		//stone
	public static final String stone_1						= PREFIX+"images/game/stone/stone_1.png";
	public static final String stone_2						= PREFIX+"images/game/stone/stone_2.png";
	public static final String stone_3						= PREFIX+"images/game/stone/stone_3.png";
	public static final String stone_4						= PREFIX+"images/game/stone/stone_4.png";
	public static final String stone_5						= PREFIX+"images/game/stone/stone_5.png";

		//truck
	public static final String truck_1_body					= PREFIX+"images/game/truck/truck_1_body.png";
	public static final String truck_1_head					= PREFIX+"images/game/truck/truck_1_head.png";
	public static final String truck_2_body					= PREFIX+"images/game/truck/truck_2_body.png";
	public static final String truck_2_head					= PREFIX+"images/game/truck/truck_2_head.png";
	public static final String truck_3_body					= PREFIX+"images/game/truck/truck_3_body.png";
	public static final String truck_3_head					= PREFIX+"images/game/truck/truck_3_head.png";
	public static final String truck_fire					= PREFIX+"images/game/truck/truck_fire.png";
	public static final String truck_wheel					= PREFIX+"images/game/truck/truck_wheel.png";
	
	
	/**
	 * Sound Resources
	 */

	public static final String cutd		 					= "games/FruitDelivery/sounds/cutd.mp3";
	public static final String menuloop 					= "games/FruitDelivery/sounds/menuloop.mp3";
	public static final String race 						= "games/FruitDelivery/sounds/race.mp3";
	public static final String stone_strike					= "games/FruitDelivery/sounds/stone_strike.caf";
	public static final String truck_drive 					= "games/FruitDelivery/sounds/truck_drive.mp3";
	
	
	/**
	 * Fonts
	 */

	public static String RES_PREFIX="games/";
	
	// Font
	public static final String FONT_1 			= RES_PREFIX + "font/candarab.ttf";
	public static final String FONT_2 			= RES_PREFIX + "font/imagica.ttf";
	public static final String FONT_NEON2	 	= RES_PREFIX + "font/imagica.ttf";
}
