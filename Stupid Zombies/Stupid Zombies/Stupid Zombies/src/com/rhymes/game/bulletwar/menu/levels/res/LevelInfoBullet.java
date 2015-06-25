package com.rhymes.game.bulletwar.menu.levels.res;

import com.badlogic.gdx.Gdx;

public class LevelInfoBullet {

	public static float ratioX=Gdx.graphics.getWidth()/1024f;
	public static float ratioY=Gdx.graphics.getHeight()/640f;
	public static int levelNumber=10;
	public static int packNumber=8;
	public static int currentpackNumber=0;
	public static int currentLevelNumber=0;
	public final static int GAME_FAILED=1;
	public final static int GAME_WIN=2;
}
