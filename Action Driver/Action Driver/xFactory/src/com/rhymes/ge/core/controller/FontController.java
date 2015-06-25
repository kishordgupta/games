package com.rhymes.ge.core.controller;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.helpers.Helper;

public class FontController {
	class FontInfo{
		public FontInfo(String fontName, String fontPath, BitmapFont font) {
			this.fontName = fontName;
			this.fontPath = fontPath;
			this.font = font;
		}
		public String fontName;
		public String fontPath;
		public BitmapFont font;		
	};
	
	private HashMap<String, FontInfo> fontCache = new HashMap<String, FontInfo>();
	private HashMap<String, String> fontPathCache = new HashMap<String, String>();
	
	private BitmapFont loadFont(String fontPath) {
		return font = TrueTypeFontFactory.createBitmapFont  
		(Gdx.files.internal(fontPath), com.rhymes.game.entity.elements.ui.Text.getFrontChars(),
				12.5f, 7.5f, 1.0f, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		
//		font.setColor(0.8f, 0.8f, 0.8f, 0.8f);
	}
	
	
	BitmapFont font;
	FontInfo fontInfo;
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
				
				font = loadFont(fontPath);

			} catch (Exception e) {
				Helper.println("Font can not be loaded, loading from file: " + fontPath);
				e.printStackTrace();
				return null;
			} 
			fontInfo = new FontInfo(fontName, fontPath, font);
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
			fontInfo.font = loadFont(fontInfo.fontPath);
		}
	}
	
	public void destroy(){
		for(FontInfo fontInfo:fontCache.values()){
			fontInfo.font.dispose();
		}
		
		fontCache.clear();
	}
}
