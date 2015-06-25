package com.rhymes.game.entity.elements.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Text2 extends ElementBase{

	private String text = "";
	private BitmapFont font = null;
	private boolean isScoreText=false;
	private static String FONT_CHARACTERS= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
		

	
	
//	public Text(float x, float y, float charWidthScale, float charHeightScale, BitmapFont font,
//			String text) {
//		super(x, y, charWidthScale, charHeightScale, 1);
//		
//		this.font = font;
//		font.setScale(charWidthScale, charHeightScale);
//		this.text = text;
//	}
	
	
	public Text2(float x, float y, float charWidthScale, float charHeightScale, BitmapFont font,
			String text,boolean isScoreText) {
		super(x, y, charWidthScale, charHeightScale, 1);
		
		this.font = font;
		font.setScale(charWidthScale, charHeightScale);
		this.text = text;
		this.isScoreText=isScoreText;
	}
	
	
	@Override
	public void getAssets(AssetPack assetPack) {
	}

	@Override
	public void init() {
	}
	
	
	public static String getFrontChars()
	{
		return FONT_CHARACTERS;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

//	char[] d;

	@Override
	public void render() {
		if(text.compareTo("") == 0)
			return;
		if(font != null){
			font.draw(GlobalVars.ge.getScreen().getBatch(), text, x, y);
		}
		if(isScoreText)
		{
			this.y = y + 2;
		}
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}

	public BitmapFont getFont() {
		return font;
	}
}
