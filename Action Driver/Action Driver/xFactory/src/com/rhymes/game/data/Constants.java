package com.rhymes.game.data;

import com.rhymes.helpers.Helper;

public class Constants {
	public static float rx = Helper.getScreenWidth()/1024;
	public static float ry = Helper.getScreenHeight()/640;
	
	//environment
	public static float roadUpperBoundary = 580 * ry;
	public static float roadLowerBoundary = 60 * ry;
	
	public static float generationUpperBoundary = 480 * ry;
	public static float generationLowerBoundary = 60 * ry;
	
	public static float carWidth = 166 * rx;
	public static float carHeight = 87 * ry;

	
	// car speed
	public static float carStartingSpeedX = 5 * rx;
	public static float carStartingSpeedY = 3 * ry;
	public static float carMaxSpeedX = 20 * rx;
	public static float carMaxSpeedY = 6 * ry;
	
	
	// crack
	public static float smallCrackWidth = 90 * rx;	
	public static float smallCrackHeight = 80 * rx;
	public static float bigCrackWidth = 260 * rx;
	
	public static float coneWidth = 48 * rx;
	public static float coneHeight = 60 * rx;
	
	public static float boxWidth = 80 * rx;
	public static float boxHeight = 100 * rx;
	
	public static float drumWidth = 250 * rx;
	public static float drumHeight = 200 * rx;
	
	public static float fallingRockWidth = 120 * rx;
	public static float fallingRockHeight = 100 * rx;
	
	
	//cash
	public static float cashWidth = 64 * Constants.rx;
	public static float cashHeight = 64 * Constants.ry;
	
	public static final int CASH_AMOUNT_1 = 50;
	public static final int CASH_AMOUNT_2 = 100;
	
	// collidable type
	public static final int COLLIDABLE_CRACK = 1;
	public static final int COLLIDABLE_FALLING_ROCK = 2;
	public static final int COLLIDABLE_STEEP_JUMPER = 3;
	public static final int COLLIDABLE_CASH_1 = 4;
	public static final int COLLIDABLE_CASH_2 = 5;
	public static final int COLLIDABLE_CONE = 6;
	public static final int COLLIDABLE_DRUM = 7;
	public static final int COLLIDABLE_BOX = 8;
	public static final int COLLIDABLE_PEEBLE = 9;
	
	
	//SCORES
	public static final int OBSTACLES_SCORE= 10;
	public static final int GIFT_SCORE= 50;
	
	
	//Font
	public static final String fontName1 = "score";
	public static final String fontName2 = "gear";
	

	public static float fontScoreScaleX = 1f;
	public static float fontScoreScaleY = 1f;
	
	// score
	public static final float scoresY = Helper.getScreenHeight() - 38 * ry;
	public static final float scoreX = 380 * rx;
	public static final float carCrashedX = 730 * rx;
	public static final float cashX = 25 * rx;

	public static float enemeyDamageAfterHit = 10;

	//Gameoverscreen
	public static final float scoresYg = Helper.getScreenHeight() - 38 * ry;
	public static final float scoreXg = 380 * rx;
	public static final float carCrashedXg = 730 * rx;
	public static final float cashXg = 25 * rx;
	
}
