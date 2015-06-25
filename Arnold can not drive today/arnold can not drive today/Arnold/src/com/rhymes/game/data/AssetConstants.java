package com.rhymes.game.data;

import com.badlogic.gdx.Gdx;
import com.rhymes.helpers.Helper;

public class AssetConstants {


//	private static final String PREFIX						= "games/burnTheRope/";
	
	
	private static String PREFIX						= "games/arnold/";
	static{
		if(Gdx.graphics.getWidth() > 480)
			PREFIX = PREFIX + "3x/";
		else if(Gdx.graphics.getWidth() > 350)
			PREFIX = PREFIX + "2x/";
		else 
			PREFIX = PREFIX + "1x/";
		
		Helper.println("\n\nSetting AssetConstants prefix: " + PREFIX);
	}
	
	
	public static final String IMG_BKG_BLUE 				= PREFIX + "images/bkg_blue.png";
	public static final String IMG_BKG_BROWN 				= PREFIX + "images/bkg_brown.png";
	public static final String IMG_BKG_GREEN 				= PREFIX + "images/bkg_green.png";
	public static final String IMG_BKG_PURPLE 				= PREFIX + "images/bkg_purple.png";
	public static final String IMG_BKG_RED 					= PREFIX + "images/bkg_red.png";
	public static final String IMG_BKG_TEAL					= PREFIX + "images/bkg_teal.png";
	public static final String FLOOR_LEFT_SHADOW			= PREFIX + "images/platform_shadow_headLeft.png";
	public static final String FLOOR_RIGHT_SHADOW			= PREFIX + "images/platform_shadow_headRight.png";
	public static final String STITCH						= PREFIX + "images/stitch.png";
	public static final String IMG_BKG_GRADIENT				= PREFIX + "images/bkg_btm_gradient.png";
	public static final String IMG_STITCH					= PREFIX + "images/bkg_stitch.png";
	
	public static final String IMG_BKG_BLUE_TRANSITION 		= PREFIX + "images/bkg_blue_transition.png";
	public static final String IMG_BKG_BROWN_TRANSITION 	= PREFIX + "images/bkg_brown_transition.png";
	public static final String IMG_BKG_GREEN_TRANSITION		= PREFIX + "images/bkg_green_transition.png";
	public static final String IMG_BKG_PURPLE_TRANSITION	= PREFIX + "images/bkg_purple_transition.png";
	public static final String IMG_BKG_RED_TRANSITION		= PREFIX + "images/bkg_red_transition.png";
	public static final String IMG_BKG_TEAL_TRANSITION		= PREFIX + "images/bkg_teal_transition.png";

	public static final String SOUND_BALL_BOUNCE			= PREFIX + "audio/sfx_colorwheel_ball_bounce.wav";
	public static final String SOUND_FREEZE					= PREFIX + "audio/sfx_colorwheel_powerup_freeze.wav";
	public static final String SOUND_POINTS					= PREFIX + "audio/sfx_colorwheel_powerup_points.wav";
	public static final String SOUND_SNAIL					= PREFIX + "audio/sfx_colorwheel_powerup_snail.wav";
	public static final String SOUND_WINGS					= PREFIX + "audio/sfx_colorwheel_powerup_wings.wav";
	public static final String SOUND_ENDGAME				= PREFIX + "audio/sfx_colorwheel_endgame.wav";
	public static final String MUSIC_GAMEPLAY				= PREFIX + "audio/mus_color_wheel_gameplay.wav";

	public static final String IMG_PLANE 				= PREFIX + "images/plane.png";
	
	
	public static final String IMG_ATLAS 					= PREFIX + "images/packed/pack";
	
	public static final String IMG_END_ANI 					= PREFIX + "images/endani.png";
	public static final int    END_ANI_NUM 					= 18;
	
	public static final String CONF_GAME 					= PREFIX + "portal/game.json";
	public static final String CONF_CW_URL	 				= "https://pp-configs.s3.amazonaws.com/color_wheel/CW-config.json";
	
	public static final String REGION_BALL 					= "ball";
	public static final String REGION_FLOOR 				= "floor";
	public static final String REGION_POWERUP_BONUS 		= "powerup-bonus";
	public static final String REGION_POWERUP_FREEZE 		= "powerup-freeze";
	public static final String REGION_POWERUP_SLOWDOWN 		= "powerup-slowdown";
	public static final String REGION_POWERUP_SPEEDUP 		= "powerup-speedup";
	

	public static final String FONT_00		= PREFIX + "images/score_00.png";
	public static final String FONT_01		= PREFIX + "images/score_01.png";
	public static final String FONT_02		= PREFIX + "images/score_02.png";
	public static final String FONT_03		= PREFIX + "images/score_03.png";
	public static final String FONT_04		= PREFIX + "images/score_04.png";
	public static final String FONT_05		= PREFIX + "images/score_05.png";
	public static final String FONT_06		= PREFIX + "images/score_06.png";
	public static final String FONT_07		= PREFIX + "images/score_07.png";
	public static final String FONT_08		= PREFIX + "images/score_08.png";
	public static final String FONT_09		= PREFIX + "images/score_09.png";
	public static final String FONT_MINUS	= PREFIX + "images/score_minus.png";
	
	public static final String IMG_ROAD_RED = PREFIX + "images/path_red.png";
	public static final String IMG_ROAD_NORMAL = PREFIX + "images/path_normal.png";
	public static final String IMG_ROAD_GREEN = PREFIX + "images/path_green.png";
	public static final String IMG_ROAD_WHITE = PREFIX + "images/path_white.png";
	
//	public static final String IMG_BACK_2 = PREFIX + "images/back.jpg";
	public static final String IMG_BRUSH_BLUE_3 = PREFIX + "images/brush_blue_3.png";
	
	//red plane
	public static final String IMG_SHIP = PREFIX + "images/ship.jpg";
	
	
	public static final String IMG_STAR = PREFIX + "images/star.png";
	public static final String IMG_DESTROYER = PREFIX + "images/destroyer.png";
	public static final String IMG_PEARL = PREFIX + "images/pearl.png";
//	public static final String IMG_DESTROYER = PREFIX + "images/destroyer.png";
	
//	public static final String IMG_BACK_MENU = PREFIX + "images/back_menu_home.jpg";
	public static final String IMG_RELOAD = PREFIX + "images/reload.png";
	public static final String IMG_PAUSE = PREFIX + "images/pause.png";
	public static final String IMG_QUIT = PREFIX + "images/quit.png";
	public static final String IMG_PLAY = PREFIX + "images/play.png";
	public static final String IMG_JOINT = PREFIX + "images/joint.png";
	
	public static final String FONT_1 = PREFIX + "data/font1.fnt";
	
	
	public static final String IMG_BONUS_5 = PREFIX + "images/bonus_5.png";
	
	
	public static final String IMG_BOAT = PREFIX + "images/boat1.png";
	public static final String IMG_SAIL = PREFIX + "images/sail_redl.png";
	
	public static final String IMG_BIRDS_FOLDER_PATH = PREFIX + "images/birds/bird_";
	public static final String IMG_STAR_FOLDER_PATH = PREFIX + "images/star/star";
	public static final String IMG_CLOUDS_FOLDER_PATH = PREFIX + "images/clouds/cloud";
	public static final String IMG_MAGICAL_OBJECT_PATH = PREFIX + "images/magical_object/magic";
	
	
	public static final String IMG_TRANSFORMER_GREEN_PATH = PREFIX + "images/green_cloud/green_transformer";
	public static final String IMG_TRANSFORMER_RED_PATH = PREFIX + "images/red_cloud/red_transformer";
	public static final String IMG_TRANSFORMER_BLACK_PATH = PREFIX + "images/black_cloud/black_transformer";
	public static final String IMG_CLOSE = PREFIX + "images/button_blue_close.png";
	
	public static final String IMG_BUTTON_HOLDER = PREFIX + "images/btn_holder1.png";
	
	public static final String FILE_LEVEL_INFO = PREFIX + "data/levels/Level";
	
	
	public static final String IMG_CLOUD_BALL = PREFIX + "images/ball.png";
	public static final String IMG_BOTTOM_BAR = PREFIX + "images/platform_segment.png";
	
	


	///menu///
	public static final String IMG_MAP_BACK_MENU = PREFIX + "images/menu/old_world_map.jpg";
	public static final String IMG_BACK_MENU = PREFIX + "images/menu/menu_bg.png";
	public static final String IMG_GAMEOVER_SCREEN = PREFIX + "images/menu/gmover_bg.jpg";
	public static final String IMG_GAME_FAILED_SCREEN = PREFIX + "images/menu/gmover_bg_failed2.jpg";

//	public static final String IMG_RELOAD = PREFIX + "images/reload.png";
//	public static final String IMG_PAUSE = PREFIX + "images/pause.png";
//	public static final String IMG_QUIT = PREFIX + "images/quit.png";
	
	public static final String IMG_PLAY_MENU = PREFIX + "images/menu/play.png";
	public static final String IMG_PLAY_MENU_PRESSED = PREFIX + "images/menu/play_pressed.png";

	public static final String IMG_OPTIONS = PREFIX + "images/menu/options.png";
	public static final String IMG_OPTIONS_PRESSED = PREFIX + "images/menu/options_pressed.png";
	
	public static final String IMG_EXIT = PREFIX + "images/menu/exit.png";
	public static final String IMG_EXIT_PRESSED = PREFIX + "images/menu/exit_pressed.png";
	
	public static final String IMG_BACK = PREFIX + "images/menu/back.png";
	public static final String IMG_BACK_PRESSED = PREFIX + "images/menu/back_pressed.png";

	public static final String IMG_MUSIC_ON = PREFIX + "images/menu/music_On.png";
	public static final String IMG_MUSIC_ON_PRESSED = PREFIX + "images/menu/music_On_pressed.png";

	public static final String IMG_MUSIC_OFF = PREFIX + "images/menu/music_Off.png";
	public static final String IMG_MUSIC_OFF_PRESSED = PREFIX + "images/menu/music_Off_pressed.png";

	public static final String IMG_SOUND_ON = PREFIX + "images/menu/sound_On.png";
	public static final String IMG_SOUND_ON_PRESSED = PREFIX + "images/menu/sound_On_pressed.png";

	public static final String IMG_SOUND_OFF = PREFIX + "images/menu/sound_Off_pressed.png";
	public static final String IMG_SOUND_OFF_PRESSED = PREFIX + "images/menu/sound_Off_presd.png";

	public static final String IMG_MAP_MODE = PREFIX + "images/menu/map_mode.png";
	public static final String IMG_MAP_MODE_PRESSED = PREFIX + "images/menu/map_mode_pressed.png";

	public static final String IMG_TIME_MODE = PREFIX + "images/menu/time_mode.png";
	public static final String IMG_TIME_MODE_PRESSED = PREFIX + "images/menu/time_mode_pressed.png";

	public static final String IMG_CLASSIC_MODE = PREFIX + "images/menu/classic_mode.png";
	public static final String IMG_CLASSIC_MODE_PRESSED = PREFIX + "images/menu/classic_mode_pressed.png";

	public static final String IMG_LEVEL_PACK_SELECTOR = PREFIX + "images/menu/level_selector.png";
	public static final String IMG_LEVEL_SELECTOR = PREFIX + "images/menu/level_pack_selector.png";
	
	public static final String IMG_STARS = PREFIX + "images/menu/s.png";
	public static final String IMG_EMPTY_STAR = PREFIX + "images/menu/Star_not_collected.png";
	public static final String IMG_BACK_TO = PREFIX + "images/menu/back_to_pack.png";
	public static final String IMG_NEXT = PREFIX + "images/menu/next.png";
	public static final String IMG_RELOAD_LEVEL = PREFIX + "images/menu/reset.png";
	public static final String IMG_EXIT_GAME = PREFIX + "images/menu/exit_system.png";

	public static final String SCORE_FONT_00		= PREFIX + "images/menu/fonts_for_score/0.png";
	public static final String SCORE_FONT_01		= PREFIX + "images/menu/fonts_for_score/1.png";
	public static final String SCORE_FONT_02		= PREFIX + "images/menu/fonts_for_score/2.png";
	public static final String SCORE_FONT_03		= PREFIX + "images/menu/fonts_for_score/3.png";
	public static final String SCORE_FONT_04		= PREFIX + "images/menu/fonts_for_score/4.png";
	public static final String SCORE_FONT_05		= PREFIX + "images/menu/fonts_for_score/5.png";
	public static final String SCORE_FONT_06		= PREFIX + "images/menu/fonts_for_score/6.png";
	public static final String SCORE_FONT_07		= PREFIX + "images/menu/fonts_for_score/7.png";
	public static final String SCORE_FONT_08		= PREFIX + "images/menu/fonts_for_score/8.png";
	public static final String SCORE_FONT_09		= PREFIX + "images/menu/fonts_for_score/9.png";
	public static final String SCORE_FONT_EQUAL		= PREFIX + "images/menu/fonts_for_score/equal.png";
	public static final String SCORE_FONT_BY		= PREFIX + "images/menu/fonts_for_score/by.png";
	public static final String SCORE_FONT_PERCENT	= PREFIX + "images/menu/fonts_for_score/percentage.png";

	///sound
//	public static final String MUSIC_PLAYING = PREFIX + "sounds/sound_bg.mp3";
//	public static final String SOUND_TOUCH = PREFIX + "sounds/sound_touch.mp3";
//	public static final String SOUND_TRANSFORMER = PREFIX + "sounds/sound_transformer.mp3";
//	public static final String SOUND_TRANSPORTER = PREFIX + "sounds/sound_transporter.mp3";
//	public static final String SOUND_DESTROYER = PREFIX + "sounds/sound_destroy.mp3";

	
	/**
	 * 	CARD
	 */
	
	//card back
	public static final String CARD_BACK_1	= PREFIX + "images/cards/back/back_blue_1.jpg";
	public static final String CARD_BACK_2	= PREFIX + "images/cards/back/back_blue_2.jpg";
	public static final String CARD_BACK_3	= PREFIX + "images/cards/back/back_blue_3.jpg";
	public static final String CARD_BACK_4	= PREFIX + "images/cards/back/back_blue_4.jpg";
	public static final String CARD_BACK_5	= PREFIX + "images/cards/back/back_red_1.jpg";
	public static final String CARD_BACK_6	= PREFIX + "images/cards/back/back_red_2.jpg";
	public static final String CARD_BACK_7	= PREFIX + "images/cards/back/back_red_3.jpg";
	public static final String CARD_BACK_8	= PREFIX + "images/cards/back/back_red_4.jpg";
	
	
	//stack image
	public static final String STACK_B	= PREFIX + "images/cards/joker_b.png";
	public static final String STACK_R	= PREFIX + "images/cards/joker_r.png";

	
	//clubs
	public static final String CLUBS_1		= PREFIX + "images/cards/clubs/clubs_1.png";
	public static final String CLUBS_2		= PREFIX + "images/cards/clubs/clubs_2.png";
	public static final String CLUBS_3		= PREFIX + "images/cards/clubs/clubs_3.png";
	public static final String CLUBS_4		= PREFIX + "images/cards/clubs/clubs_4.png";
	public static final String CLUBS_5		= PREFIX + "images/cards/clubs/clubs_5.png";
	public static final String CLUBS_6		= PREFIX + "images/cards/clubs/clubs_6.png";
	public static final String CLUBS_7		= PREFIX + "images/cards/clubs/clubs_7.png";
	public static final String CLUBS_8		= PREFIX + "images/cards/clubs/clubs_8.png";
	public static final String CLUBS_9		= PREFIX + "images/cards/clubs/clubs_9.png";
	public static final String CLUBS_10		= PREFIX + "images/cards/clubs/clubs_10.png";
	public static final String CLUBS_11		= PREFIX + "images/cards/clubs/clubs_11.png";
	public static final String CLUBS_12		= PREFIX + "images/cards/clubs/clubs_12.png";
	public static final String CLUBS_13		= PREFIX + "images/cards/clubs/clubs_13.png";
	
	//dice
	public static final String DICE_1		= PREFIX + "images/cards/dice/diamonds_1.png";
	public static final String DICE_2		= PREFIX + "images/cards/dice/diamonds_2.png";
	public static final String DICE_3		= PREFIX + "images/cards/dice/diamonds_3.png";
	public static final String DICE_4		= PREFIX + "images/cards/dice/diamonds_4.png";
	public static final String DICE_5		= PREFIX + "images/cards/dice/diamonds_5.png";
	public static final String DICE_6		= PREFIX + "images/cards/dice/diamonds_6.png";
	public static final String DICE_7		= PREFIX + "images/cards/dice/diamonds_7.png";
	public static final String DICE_8		= PREFIX + "images/cards/dice/diamonds_8.png";
	public static final String DICE_9		= PREFIX + "images/cards/dice/diamonds_9.png";
	public static final String DICE_10		= PREFIX + "images/cards/dice/diamonds_10.png";
	public static final String DICE_11		= PREFIX + "images/cards/dice/diamonds_11.png";
	public static final String DICE_12		= PREFIX + "images/cards/dice/diamonds_12.png";
	public static final String DICE_13		= PREFIX + "images/cards/dice/diamonds_13.png";
	
	//hearts
	public static final String HEARTS_1		= PREFIX + "images/cards/hearts/hearts_1.png";
	public static final String HEARTS_2		= PREFIX + "images/cards/hearts/hearts_2.png";
	public static final String HEARTS_3		= PREFIX + "images/cards/hearts/hearts_3.png";
	public static final String HEARTS_4		= PREFIX + "images/cards/hearts/hearts_4.png";
	public static final String HEARTS_5		= PREFIX + "images/cards/hearts/hearts_5.png";
	public static final String HEARTS_6		= PREFIX + "images/cards/hearts/hearts_6.png";
	public static final String HEARTS_7		= PREFIX + "images/cards/hearts/hearts_7.png";
	public static final String HEARTS_8		= PREFIX + "images/cards/hearts/hearts_8.png";
	public static final String HEARTS_9		= PREFIX + "images/cards/hearts/hearts_9.png";
	public static final String HEARTS_10	= PREFIX + "images/cards/hearts/hearts_10.png";
	public static final String HEARTS_11	= PREFIX + "images/cards/hearts/hearts_11.png";
	public static final String HEARTS_12	= PREFIX + "images/cards/hearts/hearts_12.png";
	public static final String HEARTS_13	= PREFIX + "images/cards/hearts/hearts_13.png";
	
	//spades
	public static final String SPADE_1		= PREFIX + "images/cards/spades/spades_1.png";
	public static final String SPADE_2		= PREFIX + "images/cards/spades/spades_2.png";
	public static final String SPADE_3		= PREFIX + "images/cards/spades/spades_3.png";
	public static final String SPADE_4		= PREFIX + "images/cards/spades/spades_4.png";
	public static final String SPADE_5		= PREFIX + "images/cards/spades/spades_5.png";
	public static final String SPADE_6		= PREFIX + "images/cards/spades/spades_6.png";
	public static final String SPADE_7		= PREFIX + "images/cards/spades/spades_7.png";
	public static final String SPADE_8		= PREFIX + "images/cards/spades/spades_8.png";
	public static final String SPADE_9		= PREFIX + "images/cards/spades/spades_9.png";
	public static final String SPADE_10		= PREFIX + "images/cards/spades/spades_10.png";
	public static final String SPADE_11		= PREFIX + "images/cards/spades/spades_11.png";
	public static final String SPADE_12		= PREFIX + "images/cards/spades/spades_12.png";
	public static final String SPADE_13		= PREFIX + "images/cards/spades/spades_13.png";
	
	
	public static final String CARD_LAYER		= PREFIX + "images/cards/back/card_layer.png";

	
	//menu
	public static final String MENU_PANEL		= PREFIX + "images/cards/menu/menu_panel.png";
	public static final String BUTTON_MENU		= PREFIX + "images/cards/menu/menu_button.png";
	public static final String BUTTON_CROSS		= PREFIX + "images/cards/cross.png";

	public static final String SUB_MENU_PANEL		= PREFIX + "images/cards/menu/back_panel.png";

	public static final String BUTTON_ON		= PREFIX + "images/cards/menu/off.png";
	public static final String BUTTON_OFF		= PREFIX + "images/cards/menu/on.png";
	public static final String GAME_OVER		= PREFIX + "images/cards/game_over.png";
	
	public static final String sound_off		= PREFIX + "images/cards/menu/sound_off.png";
	public static final String sound_on		= PREFIX + "images/cards/menu/sound_on.png";
	public static final String left_off		= PREFIX + "images/cards/menu/left_off.png";
	public static final String left_on		= PREFIX + "images/cards/menu/left_on.png";
	public static final String status_off		= PREFIX + "images/cards/menu/status_off.png";
	public static final String status_on		= PREFIX + "images/cards/menu/status_on.png";
	public static final String time_off		= PREFIX + "images/cards/menu/time_off.png";
	public static final String time_on		= PREFIX + "images/cards/menu/time_on.png";
	public static final String draw_3_off		= PREFIX + "images/cards/menu/draw_3_off.png";
	public static final String draw_3_on		= PREFIX + "images/cards/menu/draw_3_on.png";
	public static final String score_none_off		= PREFIX + "images/cards/menu/scoring_none_off.png";
	public static final String score_none_on		= PREFIX + "images/cards/menu/scoring_none_on.png";
	public static final String score_standard_off		= PREFIX + "images/cards/menu/scoring_standard_off.png";
	public static final String score_standard_on		= PREFIX + "images/cards/menu/scoring_standard_on.png";
	public static final String score_vegas_off		= PREFIX + "images/cards/menu/scoring_vegas_off.png";
	public static final String score_vegas_on		= PREFIX + "images/cards/menu/scoring_vegas_on.png";

	
	public static final String NEW_GAME			= PREFIX + "images/cards/menu/new_game.png";
	public static final String RESTART			= PREFIX + "images/cards/menu/restart.png";
	public static final String QUIT_BUTT		= PREFIX + "images/cards/menu/quit.png";
	
	public static final String OPTIONS_SOL		= PREFIX + "images/cards/menu/options.png";
	public static final String THEME_SOL		= PREFIX + "images/cards/menu/theme.png";
	public static final String GAME_SOL			= PREFIX + "images/cards/menu/game.png";
	public static final String HINTS_SOL		= PREFIX + "images/cards/menu/hints.png";
	public static final String UNDO_SOL			= PREFIX + "images/cards/menu/undo.png";

	//fonts
	public static final String FONT = PREFIX + "images/cards/fonts/DroidSerif_BoldItalic.ttf";
	
	//background
	public static final String IMG_BACK_2 = PREFIX + "images/cards/bg/back.jpg";
	public static final String IMG_BACK_3 = PREFIX + "images/cards/bg/back_2.jpg";
	public static final String IMG_BACK_4 = PREFIX + "images/cards/bg/back_3.jpg";
	public static final String IMG_BACK_5 = PREFIX + "images/cards/bg/back_4.jpg";
	
	///sound solitaire
	public static final String MUSIC_PLAYING = PREFIX + "images/cards/sound/solitaire_bg.mp3";


	
	/**
	 * ARNOLD
	 */
	
	///splash screen
	public static final String IMG_SPLASH_ARNOLD = "games/arnold/Default/Default.png";
	
	
	////game elements
	
	///ui
	public static final String BACK_UI_ARNOLD_d = PREFIX + "MainSence/Component/gback_d.png";
	public static final String BACK_UI_ARNOLD_n = PREFIX + "MainSence/Component/gback_n.png";

	public static final String SCORE_UI_ARNOLD 		= PREFIX + "MainSence/Score/mscore.png";
	public static final String HIGHSCORE_UI_ARNOLD 	= PREFIX + "MainSence/Score/hscore.png";
	
	public static final String score_over_arnold 	= PREFIX + "MainSence/Component/score.png";
	
	public static final String sensor_button_arnold_on = PREFIX + "MainSence/sensor_on.png";
	public static final String sensor_button_arnold_off = PREFIX + "MainSence/sensor_off.png";
	
	public static final String arrow_right_arnold = PREFIX + "MainSence/right_arrow.png";
	public static final String arrow_left_arnold = PREFIX + "MainSence/left_arrow.png";

	
	///backGround
	///1
	public static final String ARNOLD_BG_1_1_1 = PREFIX + "MainSence/Background/001/bg1_1.png";
	public static final String ARNOLD_BG_1_2_1 = PREFIX + "MainSence/Background/001/bg1_2.png";
	public static final String ARNOLD_BG_1_3_1 = PREFIX + "MainSence/Background/001/bg1_3.png";
	public static final String ARNOLD_BG_1_4_1 = PREFIX + "MainSence/Background/001/bg1_4.png";

	
	///2
	public static final String ARNOLD_BG_2_1_1 = PREFIX + "MainSence/Background/002/bg2_1.png";
	public static final String ARNOLD_BG_2_2_1 = PREFIX + "MainSence/Background/002/bg2_2.png";
	public static final String ARNOLD_BG_2_3_1 = PREFIX + "MainSence/Background/002/bg2_3.png";
	public static final String ARNOLD_BG_2_4_1 = PREFIX + "MainSence/Background/002/bg2_4.png";

	
	///3
	public static final String ARNOLD_BG_3_1_1 = PREFIX + "MainSence/Background/003/bg3_1.png";
	public static final String ARNOLD_BG_3_2_1 = PREFIX + "MainSence/Background/003/bg3_2.png";
	public static final String ARNOLD_BG_3_3_1 = PREFIX + "MainSence/Background/003/bg3_3.png";
	public static final String ARNOLD_BG_3_4_1 = PREFIX + "MainSence/Background/003/bg3_4.png";


	
	///normal images
	
	public static final String normal_1 = PREFIX + "MainSence/Player/normal work/n1_1.png";
	public static final String normal_2 = PREFIX + "MainSence/Player/normal work/n1_2.png";
	public static final String normal_3 = PREFIX + "MainSence/Player/normal work/n1_3.png";
	public static final String normal_4 = PREFIX + "MainSence/Player/normal work/n1_4.png";
	public static final String normal_5 = PREFIX + "MainSence/Player/normal work/n1_5.png";


	
	///left images
	
	///left_1
	public static final String left_1_1 = PREFIX + "MainSence/Player/left turn/l2_1.png";
	public static final String left_1_2 = PREFIX + "MainSence/Player/left turn/l2_2.png";
	public static final String left_1_3 = PREFIX + "MainSence/Player/left turn/l2_3.png";
	public static final String left_1_4 = PREFIX + "MainSence/Player/left turn/l2_4.png";
	public static final String left_1_5 = PREFIX + "MainSence/Player/left turn/l2_5.png";

	///left_2
	public static final String left_2_1 = PREFIX + "MainSence/Player/left turn/l3_1.png";
	public static final String left_2_2 = PREFIX + "MainSence/Player/left turn/l3_2.png";
	public static final String left_2_3 = PREFIX + "MainSence/Player/left turn/l3_3.png";
	public static final String left_2_4 = PREFIX + "MainSence/Player/left turn/l3_4.png";
	public static final String left_2_5 = PREFIX + "MainSence/Player/left turn/l3_5.png";
	
	///left_3
	public static final String left_3_1 = PREFIX + "MainSence/Player/left turn/l4_1.png";
	public static final String left_3_2 = PREFIX + "MainSence/Player/left turn/l4_2.png";
	public static final String left_3_3 = PREFIX + "MainSence/Player/left turn/l4_3.png";
	public static final String left_3_4 = PREFIX + "MainSence/Player/left turn/l4_4.png";
	public static final String left_3_5 = PREFIX + "MainSence/Player/left turn/l4_5.png";
	
	///left_4
	public static final String left_4_1 = PREFIX + "MainSence/Player/left turn/l5_1.png";
	public static final String left_4_2 = PREFIX + "MainSence/Player/left turn/l5_2.png";
	public static final String left_4_3 = PREFIX + "MainSence/Player/left turn/l5_3.png";
	public static final String left_4_4 = PREFIX + "MainSence/Player/left turn/l5_4.png";
	public static final String left_4_5 = PREFIX + "MainSence/Player/left turn/l5_5.png";
	

	
	///right images
	
	///right_1
	public static final String right_1_1 = PREFIX + "MainSence/Player/right turn/r1_1.png";
	public static final String right_1_2 = PREFIX + "MainSence/Player/right turn/r1_2.png";
	public static final String right_1_3 = PREFIX + "MainSence/Player/right turn/r1_3.png";
	public static final String right_1_4 = PREFIX + "MainSence/Player/right turn/r1_4.png";
	public static final String right_1_5 = PREFIX + "MainSence/Player/right turn/r1_5.png";

	///right_2
	public static final String right_2_1 = PREFIX + "MainSence/Player/right turn/r2_1.png";
	public static final String right_2_2 = PREFIX + "MainSence/Player/right turn/r2_2.png";
	public static final String right_2_3 = PREFIX + "MainSence/Player/right turn/r2_3.png";
	public static final String right_2_4 = PREFIX + "MainSence/Player/right turn/r2_4.png";
	public static final String right_2_5 = PREFIX + "MainSence/Player/right turn/r2_5.png";
	
	///right_3
	public static final String right_3_1 = PREFIX + "MainSence/Player/right turn/r3_1.png";
	public static final String right_3_2 = PREFIX + "MainSence/Player/right turn/r3_2.png";
	public static final String right_3_3 = PREFIX + "MainSence/Player/right turn/r3_3.png";
	public static final String right_3_4 = PREFIX + "MainSence/Player/right turn/r3_4.png";
	public static final String right_3_5 = PREFIX + "MainSence/Player/right turn/r3_5.png";
	
	///right_4
	public static final String right_4_1 = PREFIX + "MainSence/Player/right turn/r4_1.png";
	public static final String right_4_2 = PREFIX + "MainSence/Player/right turn/r4_2.png";
	public static final String right_4_3 = PREFIX + "MainSence/Player/right turn/r4_3.png";
	public static final String right_4_4 = PREFIX + "MainSence/Player/right turn/r4_4.png";
	public static final String right_4_5 = PREFIX + "MainSence/Player/right turn/r4_5.png";

	
	
	///fall down images
	
	///fall left
	public static final String fall_1_1 = PREFIX + "MainSence/Player/fall down/left1.png";
	public static final String fall_1_2 = PREFIX + "MainSence/Player/fall down/left2.png";
	public static final String fall_1_3 = PREFIX + "MainSence/Player/fall down/left3.png";

	///fall right
	public static final String fall_2_1 = PREFIX + "MainSence/Player/fall down/right1.png";
	public static final String fall_2_2 = PREFIX + "MainSence/Player/fall down/right2.png";
	public static final String fall_2_3 = PREFIX + "MainSence/Player/fall down/right3.png";
	
	
	
	///menu
	
	//main menu
	public static final String main_menu_arnold_bg = PREFIX + "MainMenu/bg.png";
	
	public static final String play_arnold_d = PREFIX + "MainMenu/play_d.png";
	public static final String play_arnold_n = PREFIX + "MainMenu/play_n.png";

	public static final String settings_arnold_d = PREFIX + "MainMenu/setting_d.png";
	public static final String settings_arnold_n = PREFIX + "MainMenu/setting_n.png";
	
	public static final String leaderboard_arnold_d = PREFIX + "MainMenu/leader_board_d.png";
	public static final String leaderboard_arnold_n = PREFIX + "MainMenu/leader_board_n.png";
	
	
	//setting menu
	public static final String settings_menu_arnold_bg = PREFIX + "Setting/sbg.png";

	public static final String player_sound_on_arnold = PREFIX + "Setting/player_sound_on.png";
	public static final String player_sound_off_arnold = PREFIX + "Setting/player_sound_off.png";

	public static final String bg_sound_on_arnold = PREFIX + "Setting/sound_on.png";
	public static final String bg_sound_off_arnold = PREFIX + "Setting/sound_off.png";
	
	public static final String back_to_main_menu_arnold_d = PREFIX + "Setting/back_d.png";
	public static final String back_to_main_menu_arnold_n = PREFIX + "Setting/back_n.png";

	
	//level menu
	public static final String level_menu_arnold_bg = PREFIX + "Select Level/cbg.png";

	public static final String radio_button_arnold_on = PREFIX + "Select Level/1_n.png";
	public static final String radio_button_arnold_off = PREFIX + "Select Level/1_d.png";

	public static final String level_arnold_1_d = PREFIX + "Select Level/level1_d.png";
	public static final String level_arnold_1_n = PREFIX + "Select Level/level1_n.png";
	
	public static final String level_arnold_2_d = PREFIX + "Select Level/level2_d.png";
	public static final String level_arnold_2_n = PREFIX + "Select Level/level2_n.png";
	
	public static final String level_arnold_3_d = PREFIX + "Select Level/level3_d.png";
	public static final String level_arnold_3_n = PREFIX + "Select Level/level3_n.png";
	
	
	//game over menu
	public static final String game_clear_menu_arnold_bg = PREFIX + "Gameover and Clear/gameclear.png";
	public static final String game_over_menu_arnold_bg = PREFIX + "Gameover and Clear/gameover.png";

	public static final String back_from_over_arnold_menu_d = PREFIX + "Gameover and Clear/gback_d.png";
	public static final String back_from_over_arnold_menu_n = PREFIX + "Gameover and Clear/gback_n.png";

	public static final String next_arnold_d = PREFIX + "Gameover and Clear/next_d.png";
	public static final String next_arnold_n = PREFIX + "Gameover and Clear/next_n.png";
	
	public static final String select_level_from_over_arnold_d = PREFIX + "Gameover and Clear/selection_level_d.png";
	public static final String select_level_from_over_arnold_n = PREFIX + "Gameover and Clear/selection_level_n.png";
	
	
	/// sounds arnold
	public static final String sound_menu_bg = "games/arnold/Sound/menu.wav";
	public static final String sound_playing_bg = "games/arnold/Sound/background_music.wav";
	
	///font arnold
	public static final String font_arnold = "games/arnold/Font/Imagica.ttf";

}
