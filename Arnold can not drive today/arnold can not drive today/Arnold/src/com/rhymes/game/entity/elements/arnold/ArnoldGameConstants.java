package com.rhymes.game.entity.elements.arnold;

import com.badlogic.gdx.Gdx;

public class ArnoldGameConstants {

	public static float ratio_w = (float)Gdx.graphics.getWidth() / 960f;//640f;
	public static float ratio_h = (float)Gdx.graphics.getHeight() / 640f;//960f;
	public static float aspectRatio = (float)Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight();
	
	
	public static float button_width	 	= 180f * ratio_w;
	public static float button_height	 	= 90f * ratio_h;
	
	public static float button_width_ui	 	= 120f * ratio_w;
	public static float button_height_ui	= 35f * ratio_h;
	
	public static float score_width_ui	 	= 120f * ratio_w;
	public static float score_height_ui		= 50f * ratio_h;
	
	public static float radio_width	 		= 80f * ratio_w;
	public static float radio_height		= 80f * ratio_h;
	
	public static float level_ui_width	 	= 450f * ratio_w;
	public static float level_ui_height		= 360f * ratio_h;
	
	public static float arrow_w	 			= 100f * ratio_w;
	public static float arrow_h				= 50f * ratio_h;
	
}
