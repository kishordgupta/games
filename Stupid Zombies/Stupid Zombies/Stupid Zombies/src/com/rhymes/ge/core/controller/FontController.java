package com.rhymes.ge.core.controller;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.helpers.Helper;

public class FontController {
	private static String FONT_CHARACTERS= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
	public static float worldWidth = 12.5f;
	public static float worldHeight = 6.5f;
	class FontInfo{
		public FontInfo(String fontName, String fontPath, BitmapFont font, float worldWidth, float worldHeight) {
			this.fontName = fontName;
			this.fontPath = fontPath;
			this.font = font;
			this.worldWidth = worldWidth;
			this.worldHeight = worldHeight;
		}
		
		public String fontName;
		public String fontPath;
		public BitmapFont font;
		public float worldWidth = 12.5f;
		public float worldHeight = 6.5f;
	};
	
	private HashMap<String, FontInfo> fontCache = new HashMap<String, FontInfo>();
	private HashMap<String, String> fontPathCache = new HashMap<String, String>();
	
	private BitmapFont loadFont(String fontPath, float worldWidth, float worldHeight) {
		return font = TrueTypeFontFactory.createBitmapFont  
		(Gdx.files.internal(fontPath), FONT_CHARACTERS,
				 worldWidth, worldHeight, 1.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		deviceWidth * fontPixelsHeight / worldWidth
//		font.setColor(0.8f, 0.8f, 0.8f, 0.8f);
	}
	
	
	BitmapFont font;
	FontInfo fontInfo;
	public BitmapFont addFont(String fontName, String fontPath, float worldWidth, float worldHeight)
	{
		fontInfo = fontCache.get(fontName);		
		Helper.println("fontinfo: " + fontInfo);
//		if(!fontCache.containsKey(fontName)){
		if(fontInfo == null){
			Helper.println("loading font " + fontPath);
//			Helper.println("Image is not loaded, loading from file: " + imageConstant);
			try {
//				Helper.println("Image loaded successfully, loading from file: " + imageConstant);
				
				font = loadFont(fontPath, worldWidth, worldHeight);

			} catch (Exception e) {
				Helper.println("Font can not be loaded, loading from file: " + fontPath);
				e.printStackTrace();
				return null;
			} 
			fontInfo = new FontInfo(fontName, fontPath, font, worldWidth, worldHeight);
			fontCache
				.put(fontName, fontInfo);
		}
//		else
//			Helper.println("Image already loaded, loading from file: " + imageConstant);
		return fontInfo.font;
	}

	public BitmapFont addFont(String fontName, String fontPath)
	{
		fontInfo = fontCache.get(fontName);		
		Helper.println("fontinfo: " + fontInfo);
//		if(!fontCache.containsKey(fontName)){
		if(fontInfo == null){
			Helper.println("loading font " + fontPath);
//			Helper.println("Image is not loaded, loading from file: " + imageConstant);
			try {
//				Helper.println("Image loaded successfully, loading from file: " + imageConstant);
				
				font = loadFont(fontPath, worldWidth, worldHeight);

			} catch (Exception e) {
				Helper.println("Font can not be loaded, loading from file: " + fontPath);
				e.printStackTrace();
				return null;
			} 
			fontInfo = new FontInfo(fontName, fontPath, font, worldWidth, worldHeight);
			fontCache
				.put(fontName, fontInfo);
		}
//		else
//			Helper.println("Image already loaded, loading from file: " + imageConstant);
		return fontInfo.font;
	}
	
	
	public BitmapFont getFont(String fontName)
	{

		fontInfo = fontCache.get(fontName);		
		if(fontInfo == null){
			Helper.println("Font not found: " + fontName);
			return null;
		}
		else
			return fontInfo.font;
	}
	
	
	public void reloadFonts()
	{
		for(FontInfo fontInfo:fontCache.values())
		{
			fontInfo.font = loadFont(fontInfo.fontPath, fontInfo.worldWidth, fontInfo.worldHeight);
		}
	}
	
	public void destroy(){
		for(FontInfo fontInfo:fontCache.values()){
			fontInfo.font.dispose();
		}
		
		fontCache.clear();
	}
	public static String getFrontChars()
	{
		return FONT_CHARACTERS;
	}

}
