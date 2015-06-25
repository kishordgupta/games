package com.rhymes.game.stage.menus;

import com.badlogic.gdx.Gdx;



public class GameMenuInfo {
	public static int num_of_total_level = 18;
	public static int num_of_level_pack = 3;
	public static int num_of_level_in_a_pack = 6;
	
	public static float ratio_w = (float)Gdx.graphics.getWidth() / 480f;
	public static float ratio_h = (float)Gdx.graphics.getHeight() / 854f;

	public static float ratio_w_img = (float)Gdx.graphics.getWidth() / 640f;
	public static float ratio_h_img = (float)Gdx.graphics.getHeight() / 960f;


	public static float ratio_wl = (float)Gdx.graphics.getWidth() / 320f;
	public static float ratio_hl = (float)Gdx.graphics.getHeight() / 480f;
}