package com.rhymes.game.entity.elements.testtile;

import com.badlogic.gdx.Gdx;

public class LevelInfo {
	public static int LEVEL_NUMBER		= 1;
	public static int GAME_MODE			= 0;
	public static int LEVEL_DESTROYED	= 2;
	public static int LEVEL_COMPLETE	= 3;
	public static int LEVEL_PLAY		= 4;
	public static int LEVEL_OVER		= 5;
	public static boolean GAME_COMPLETE	= false;
	public static long score=0;
	public static long timeBonus=0;
	public static long totalScore=0;
	public static float ratioX=Gdx.graphics.getWidth()/480f;
	public static float ratioY=Gdx.graphics.getHeight()/320f;
}
