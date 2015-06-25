package com.rhymes.game.stage.menus.stick;

import com.badlogic.gdx.Gdx;

public class LevelInfo {

	public static float ratioX=Gdx.graphics.getWidth()/1024f;
	public static float ratioY=Gdx.graphics.getHeight()/640f;
	public static int levelNumber=25;
	public static int currentLevelNumber=0;
	public final static int GAME_FAILED=1;
	public final static int GAME_WIN=2;
	
}
