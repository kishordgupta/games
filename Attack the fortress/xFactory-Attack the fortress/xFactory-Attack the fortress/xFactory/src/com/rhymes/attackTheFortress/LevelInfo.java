package com.rhymes.attackTheFortress;

import com.badlogic.gdx.Gdx;

public class LevelInfo {
	public static int ROUND_NUMBER		= 1;
	public static int GAME_MODE			= 0;
	public static boolean GAME_VICTORY	= false;
	public static RoundResult ROUND_RESULT_1=new RoundResult();
	public static RoundResult ROUND_RESULT_2=new RoundResult();
	public static RoundResult ROUND_RESULT_3=new RoundResult();
	public static RoundResult ROUND_RESULT_4=new RoundResult();
	public static RoundResult ROUND_RESULT_5=new RoundResult();
	public static RoundResult ROUND_RESULT_6=new RoundResult();
	public static float ratioX=Gdx.graphics.getWidth()/480f;
	public static float ratioY=Gdx.graphics.getHeight()/320f;
}
