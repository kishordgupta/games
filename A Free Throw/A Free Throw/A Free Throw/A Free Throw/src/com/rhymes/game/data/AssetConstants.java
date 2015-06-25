package com.rhymes.game.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetConstants {

	//adding Prefix
	private static final String PREFIX						= "games/freeThrow/";
	//bin folder
	public static final String BOX2D_BIN_FOLDER = PREFIX + "data/";
	//adding backgrounds
	public static final String IMG_BKG_TEAL					= PREFIX + "images/background/m-playbg.png";
	public static final String IMG_BKG_MAIN					= PREFIX + "data/control/back.png";
	public static final String IMG_BKG_LEVEL7 = PREFIX + "images/background/l3-ba.png";
	public static final String IMG_BKG_CS = PREFIX + "images/background/l3-ba.png";
	//adding assets for fonts
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


	public static final String IMG_SKIP = PREFIX + "data/control/button1-skip.png";
	public static final String IMG_RELOAD = PREFIX + "data/control/m-b2-n.png";
	public static final String IMG_PAUSE = PREFIX + "data/control/m-stop-n.png";
	public static final String IMG_QUIT = PREFIX + "data/control/m-b3-n.png";
	public static final String IMG_PLAY =PREFIX + "data/control/m-b1-n.png";
	public static final String IMG_JOINT = PREFIX + "images/joint.png";
	
	//adding asssets for front
	public static final String FONT_IMAGICA = PREFIX + "fonts/Imagica.ttf";
	public static final String FONT_SUPER_STAR = PREFIX + "fonts/SuperstarM54.ttf";
	public static final String FONT_NEON = PREFIX + "fonts/Neon.ttf";
	public static final String FONT_PLOK = PREFIX + "fonts/Plok.ttf";
	///adding asset constant for ball
	public static final String IMG_BASKET_BALL 	= PREFIX + "data/ball/m-playball.png";
	public static final String IMG_RUBBER_BALL 	= PREFIX + "data/ball/m-playball2.png";
	public static final String IMG_BOWL_BALL 	= PREFIX + "data/ball/m-playball3.png";
	// adding asset constant for Physics Bodies	
	public static final String IMG_BALLSTICK = PREFIX + "data/ballstick/gfx/m-ballstick.png";
	public static final String IMG_PLAYGROUND_BCKG = PREFIX + "images/background/m-playbg.png";
	public static final String IMG_ARROW = PREFIX + "data/arrow/fasal.png";
	public static final String IMG_GROUND = PREFIX + "data/ground/gfx/ground.png";
	public static final String IMG_HOUSE 	= PREFIX + "data/house/gfx/warehouse.png";
	public static final String IMG_lADDER = PREFIX + "data/ladder/gfx/ladder.png";
	public static final String IMG_TOWNHOUSE = PREFIX + "data/town/gfx/house.png";
	public static final String IMG_HOUSE2 = PREFIX + "data/house2/gfx/house2-1.png";
	public static final String IMG_BALLOON =PREFIX + "data/balloon/gfx/balloon.png";
	public static final String IMG_LADDER = PREFIX + "data/ladder1/gfx/ladder.png";
	public static final String IMG_UTILITY_POLE = PREFIX + "data/utilityPole/gfx/utilitypole.png";
	public static final String IMG_LEVEL_ELEMENT1 = PREFIX + "data/levelelement/gfx/l3-e.png";
	public static final String IMG_LEVEL_ELEMENT6 = PREFIX + "data/levelelement6/gfx/l3-e6.png";
	public static final String IMG_BY_HOUSE = PREFIX + "data/levelelement8/gfx/l3-e10.png";
	public static final String IMG_TREE = PREFIX + "data/tree/gfx/tree.png";
	public static final String IMG_WAREHOUSE = PREFIX + "data/warehouse/gfx/warehouse.png";
	public static final String IMG_CHAIR = PREFIX + "data/chair/gfx/chair.png";
	public static final String IMG_BALLOON2 = PREFIX + "data/balloon2/gfx/balloon2-1.png";
	public static final String IMG_DOUBLE_LADDER = PREFIX + "data/doubleladder/gfx/ladder2-2.png";
	public static final String IMG_WOODSTOCK = PREFIX + "data/woodstock/gfx/woodstock1.png";
	public static final String IMG_SINGLELADDER = PREFIX + "data/singleladder/gfx/ladder2-0.png";
	public static final String IMG_ELEMENT1 = PREFIX + "data/levelelement1/gfx/l3-e1.png";
	public static final String IMG_ELEMENT6 = PREFIX + "data/levelelement6/gfx/l3-e6.png";
	public static final String IMG_ELEMENT4 = PREFIX + "data/levelelement4/gfx/l3-e4.png";
	public static final String IMG_ELEMENT3 = PREFIX + "data/levelelement3/gfx/l3-e3.png";
	public static final String IMG_ELEMENT2 = PREFIX + "data/levelelement2/gfx/l3-e2.png";
	public static final String IMG_ELEMENT5 = PREFIX + "data/levelelement5/gfx/l3-e5.png";
	public static final String IMG_ELEMENT7 = PREFIX + "data/levelelement7/gfx/l3-e7.png";
	
	
	//adding asset constant for controls
//	public static final String IMG_RELOADBOUNCE = PREFIX + "data/control/m-b2-n.png";
//	public static final String IMG_PAUSEBOUNCE = PREFIX + "data/control/m-stop-n.png";
//	public static final String IMG_QUITBOUNCE = PREFIX + "data/control/m-b3-n.png";
//	public static final String IMG_PLAYBOUNCE = PREFIX + "data/control/m-b1-n.png";
	
	
	
	//adding asset constants for sound
	
//	public static final Sound HI_SOUND = Gdx.audio.newSound(Gdx.files.internal("data/hit.ogg"));
	public static final String SOUND_BALL_THROW = PREFIX + "data/sound/button_click.wav";
	public static final String MUSIC_MENU = PREFIX + "data/sound/menu.wav";
	public static final String MUSIC_PLAYING = PREFIX + "data/sound/enter_effect.wav";
	public static final String SOUND_HIT = PREFIX + "data/sound/hit.wav";
	public static final String SOUND_CLICK = PREFIX + "data/sound/button_click.wav";
	public static final String SOUND_ENTRR_EFFECT = PREFIX + "data/sound/enter_effect.wav";
	public static final String SOUND_GOAL_EFFECT = PREFIX + "data/sound/goal_effect.wav";
	public static final String SOUND_NO_CLICK = PREFIX + "data/sound/no_click.wav";
	
	
	///adding asset constants for shot_ball
	public static final String IMG_SHOT_BALL_1 = PREFIX + "images/ball/m-ball.png";
	public static final String IMG_AFTER_SHOT_BALL_1 = PREFIX + "images/ball/m-ball1.png";
	public static final String IMG_SHOT_BALL_2 = PREFIX + "images/ball/m-ball.png";
	public static final String IMG_SHOT_BALL_3 = PREFIX + "images/ball/m-ball1.png";
	//adding asset constants for gameover screen
	public static final String IMG_TRYAGAIN = PREFIX + "data/gameOver/m-tryagainbg.png";
	public static final String IMG_GREATSHOT = PREFIX + "data/gameOver/m-greatshotbg.png";
	public static final String IMG_GAME_FAILED_SCREEN = PREFIX + "data/gameOver/m-tryagainbg.png";
	public static final String IMG_GAMEOVER_SCREEN =PREFIX + "data/gameOver/m-greatshotbg.png";
	//adding asset constants for menu controls
	public static final String IMG_BACK_TO = PREFIX +"data/gameOver/m-greatshotbg.png";
	public static final String IMG_RELOAD_LEVEL = PREFIX + "images/joint.png";
	public static final String IMG_NEXT = PREFIX + "data/control/m-b1-n.png";
	public static final String IMG_BACK = PREFIX + "data/control/c-back-n.png";
	public static final String IMG_EXIT = PREFIX + "data/control/m-b3-n.png";
	public static final String IMG_EXIT_GAME = PREFIX + "data/control/m-stop-n.png";
	public static final String IMG_PLAY_MENU =  PREFIX + "data/control/m-b1-n.png";
	public static final String IMG_SOUND_ON =  PREFIX + "data/control/sound.png";
	public static final String IMG_SOUND_OFF =  PREFIX + "data/control/unsound.png";
	public static final String IMG_LEVEL_SELECTOR = PREFIX +"data/gameOver/3point.png";
	public static final String IMG_MUSIC_OFF = PREFIX + "data/control/unsound.png";
	public static final String IMG_MUSIC_ON =  PREFIX + "data/control/sound-f.png";
	public static final String IMG_OPTIONS = PREFIX + "data/control/options.png";
//	public static final String IMG_BACK_MENU = PREFIX + "data/credits/c-back-d.png";
	public static final String IMG_CPB = PREFIX + "data/chooseBallandPlayer/chooseplayerball.png";
	public static final String IMG_INS = PREFIX + "data/instruction/instructions.png";
	//adding assets for showing successful basketing
	public static final String IMG_STARS =	PREFIX + "data/gameOver/m-starbig4.png";
	public static final String IMG_EMPTY_STAR = PREFIX + "data/gameOver/m-starbig2.png";
	//adding assets for menu buttons pressed	
//	public static final String IMG_OPTIONS_PRESSED = PREFIX + "data/control/options_pressed.png";
	public static final String IMG_MUSIC_OFF_PRESSED =PREFIX + "data/control/sound.png";
	public static final String IMG_MUSIC_ON_PRESSED =  PREFIX + "data/control/unsound.png";
	public static final String IMG_PLAY_MENU_PRESSED = PREFIX +  "data/control/m-b1-n.png";
	public static final String IMG_EXIT_PRESSED = PREFIX +  "data/control/m-b3-n.png";
	public static final String IMG_LEVEL_PACK_SELECTOR = PREFIX +"data/gameOver/3point.png";
	public static final String IMG_BACK_PRESSED = PREFIX +"data/credits/c-back-n.png";
	public static final String IMG_SOUND_ON_PRESSED = PREFIX + "data/control/unsound.png";
	public static final String IMG_SOUND_OFF_PRESSED = PREFIX + "data/control/sound.png";
	public static final String IMG_CLASSIC_MODE = null;
	public static final String IMG_CLASSIC_MODE_PRESSED = null;
	public static final String IMG_INFO_PRESSED = PREFIX + "data/control/button2-d.png";
	public static final String IMG_CPB_BKG = PREFIX + "data/chooseBallandPlayer/chap-bg.png";
	//adding assets for choose player and ball
	public static final String IMG_PAUL = PREFIX + "data/chooseBallandPlayer/chap-1-n.png";
	public static final String IMG_JAMES = PREFIX + "data/chooseBallandPlayer/chap-2-n.png";
	public static final String IMG_JOHN = PREFIX + "data/chooseBallandPlayer/chap-3-n.png";
	public static final String IMG_CHARLES = PREFIX + "data/chooseBallandPlayer/chap-4-n.png";
	public static final String IMG_AMY = PREFIX + "data/chooseBallandPlayer/chap-5-n.png";
	public static final String IMG_POLAR = PREFIX + "data/chooseBallandPlayer/chap-6-n.png";
	public static final String IMG_BALL_BASKET = PREFIX + "data/chooseBallandPlayer/chap-7-n.png";
	public static final String IMG_BALL_RUBBER = PREFIX + "data/chooseBallandPlayer/chap-8-n.png";
	public static final String IMG_BALL_BOWL = PREFIX + "data/chooseBallandPlayer/chap-9-n.png";
//	public static final String IMG_CLICK = null;
	public static final String IMG_SCORESTAR = PREFIX + "data/score/s.png";
	//adding assets for choose location
	public static final String IMG_LOC_PLAYGROUND = PREFIX + "data/chooseLocation/basics-n.png";
	public static final String IMG_LOC_BACKYARD = PREFIX + "data/chooseLocation/by-n.png";
	public static final String IMG_LOC_CAMP = PREFIX + "data/chooseLocation/camp-n.png";
	public static final String IMG_LOC_RANCH = PREFIX + "data/chooseLocation/ranch-n.png";
	public static final String IMG_LOC_CAMP_LOCKED = PREFIX + "data/chooseLocation/camp.png";
	public static final String IMG_LOC_RANCH_LOCKED = PREFIX + "data/chooseLocation/ranch.png";
	public static final String IMG_CL_BKG = PREFIX + "data/credits/c-bg.png";
	public static final String IMG_CL = PREFIX + "data/chooseLocation/chooselocation.png";
	//adding assets for instructions
	public static final String IMG_INS1 = PREFIX + "data/instruction/01.png";
	public static final String IMG_INS2 = PREFIX + "data/instruction/02.png";
	public static final String IMG_INS3 = PREFIX + "data/instruction/03.png";
	public static final String IMG_INS4 = PREFIX + "data/instruction/04.png";
	public static final String IMG_INS5 = PREFIX + "data/instruction/05.png";
	public static final String IMG_INS6 = PREFIX + "data/instruction/06.png";
	public static final String IMG_INS_BCK = PREFIX + "data/instruction/next-d.png";
	public static final String IMG_INS_BCK_PRESSED = PREFIX + "data/instruction/next-n.png";
	//adding assets for choose shot and shot numbers
	public static final String IMG_CS = PREFIX + "data/chooseShot/button/chooseshot.png";
	public static final String IMG_SHOT1 = PREFIX + "data/chooseShot/button/shot1-n-iPad.png";
	public static final String IMG_SHOT2 = PREFIX + "data/chooseShot/button/shot2-n-iPad.png";
	public static final String IMG_SHOT3 = PREFIX + "data/chooseShot/button/shot3-n-iPad.png";
	public static final String IMG_SHOT4 = PREFIX + "data/chooseShot/button/shot4-n-iPad.png";
	public static final String IMG_SHOT5 = PREFIX + "data/chooseShot/button/shot5-iPad.png";
	public static final String IMG_SHOT6 = PREFIX + "data/chooseShot/button/shot6-iPad.png";
	public static final String IMG_SHOT7 = PREFIX + "data/chooseShot/button/shot7-iPad.png";
	public static final String IMG_SHOT8 = PREFIX + "data/chooseShot/button/shot8-iPad.png";
	public static final String IMG_SHOT9 = PREFIX + "data/chooseShot/button/shot9-iPad.png";
	public static final String IMG_SHOT10 = PREFIX + "data/chooseShot/button/shot10-iPad.png";
	public static final String IMG_SHOT11 = PREFIX + "data/chooseShot/button/shot11-iPad.png";
	public static final String IMG_SHOT12 = PREFIX + "data/chooseShot/button/shot12-iPad.png";
	public static final String IMG_SHOT_LOCK = PREFIX + "data/chooseShot/button/shotlock.png";
	public static final String IMG_CHECK = PREFIX + "data/chooseShot/button/check.png";
	//adding player informations
	public static final String IMG_INFO_PAUL = PREFIX + "data/playerInformation/ss-select1.png";
	public static final String IMG_INFO_JAMES = PREFIX + "data/playerInformation/ss-select2.png";
	public static final String IMG_INFO_JOHN = PREFIX + "data/playerInformation/ss-select3.png";
	public static final String IMG_INFO_AMY = PREFIX + "data/playerInformation/ss-select4.png";
	public static final String IMG_INFO_POLAR = PREFIX + "data/playerInformation/ss-select5.png";
	public static final String IMG_INFO = PREFIX + "data/control/button2-n.png";
	public static final String IMG_GO = PREFIX + "data/control/m-b1-n.png";
	public static final String IMG_PLAY_TEXT = PREFIX + "images/background/play-n.png";
	
	

	//adding assets for showing image text
	public static String IMG_SCORE = PREFIX + "data/score/m-score.png";
////addding assets for player Paul
	public static String IMG_PLAYER1_NORMAL1 = PREFIX + "data/players/player1/p1-normal1.png";
	public static String IMG_PLAYER1_NORMAL2 = PREFIX + "data/players/player1/p1-normal2.png";
	public static String IMG_PLAYER1_THROW1 = PREFIX + "data/players/player1/p1-throw1.png";
	public static String IMG_PLAYER1_THROW2 = PREFIX + "data/players/player1/p1-throw2.png";
	public static String IMG_PLAYER1_END1 = PREFIX + "data/players/player1/p1-end1.png";
	public static String IMG_PLAYER1_END2 = PREFIX + "data/players/player1/p1-end2.png";
//adding assets for player2 (James)
	public static String IMG_PLAYER2_NORMAL1 = PREFIX + "data/players/player2/p2-normal1.png";
	public static String IMG_PLAYER2_NORMAL2 = PREFIX + "data/players/player2/p2-normal2.png";
	public static String IMG_PLAYER2_THROW1 = PREFIX + "data/players/player2/p2-throw1.png";
	public static String IMG_PLAYER2_THROW2 = PREFIX + "data/players/player2/p2-throw2.png";
	public static String IMG_PLAYER2_END1 = PREFIX + "data/players/player2/p2-end1.png";
	public static String IMG_PLAYER2_END2 = PREFIX + "data/players/player2/p2-end2.png";
	//adding assets for John
	public static String IMG_PLAYER3_NORMAL1 = PREFIX + "data/players/player3/p3-normal1.png";
	public static String IMG_PLAYER3_NORMAL2 = PREFIX + "data/players/player3/p3-normal2.png";
	public static String IMG_PLAYER3_THROW1 = PREFIX + "data/players/player3/p3-throw1.png";
	public static String IMG_PLAYER3_THROW2 = PREFIX + "data/players/player3/p3-throw2.png";
	public static String IMG_PLAYER3_END1 = PREFIX + "data/players/player3/p3-end1.png";
	public static String IMG_PLAYER3_END2 = PREFIX + "data/players/player3/p3-end2.png";
	//adding assets for Charles
	public static String IMG_PLAYER4_NORMAL1 = PREFIX + "data/players/player4/p4-normal1.png";
	public static String IMG_PLAYER4_NORMAL2 = PREFIX + "data/players/player4/p4-normal2.png";
	public static String IMG_PLAYER4_THROW1 = PREFIX + "data/players/player4/p4-throw1.png";
	public static String IMG_PLAYER4_THROW2 = PREFIX + "data/players/player4/p4-throw2.png";
	public static String IMG_PLAYER4_END1 = PREFIX + "data/players/player4/p4-end1.png";
	public static String IMG_PLAYER4_END2 = PREFIX + "data/players/player4/p4-end2.png";
	//adding assets for Amy
	public static String IMG_PLAYER5_NORMAL1 = PREFIX + "data/players/player5/p5-man-normal1.png";
	public static String IMG_PLAYER5_NORMAL2 = PREFIX + "data/players/player5/p5-man-normal2.png";
	public static String IMG_PLAYER5_THROW1 = PREFIX + "data/players/player5/p5-man-throw1.png";
	public static String IMG_PLAYER5_THROW2 = PREFIX + "data/players/player5/p5-man-throw2.png";
	public static String IMG_PLAYER5_END1 = PREFIX + "data/players/player5/p5-man-end1.png";
	public static String IMG_PLAYER5_END2 = PREFIX + "data/players/player5/p5-man-end2.png";
	//adding assets for Ploar
	public static String IMG_PLAYER6_NORMAL1 = PREFIX + "data/players/player6/p6-normal1.png";
	public static String IMG_PLAYER6_NORMAL2 = PREFIX + "data/players/player6/p6-normal2.png";
	public static String IMG_PLAYER6_THROW1 = PREFIX + "data/players/player6/p6-throw1.png";
	public static String IMG_PLAYER6_THROW2 = PREFIX + "data/players/player6/p6-throw2.png";
	public static String IMG_PLAYER6_END1 = PREFIX + "data/players/player6/p6-end1.png";
	public static String IMG_PLAYER6_END2 = PREFIX + "data/players/player6/p6-end2.png";
	
	



}


