package com.rhymes.game.entity.elements.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Text extends ElementBase{

	@Override
	public void resume() {
		this.font = createFont(fontPath);
		font.setScale(0.35f * GameMenuInfo.ratio_w, 0.35f * GameMenuInfo.ratio_w);
	}

	private String text = "";
	private static BitmapFont font = null;
	private boolean isScoreText=false;
	private static String FONT_CHARACTERS= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
	private String fontPath;

	
	
//	public Text(float x, float y, float charWidthScale, float charHeightScale, BitmapFont font,
//			String text) {
//		super(x, y, charWidthScale, charHeightScale, 1);
//		
//		this.font = font;
//		font.setScale(charWidthScale, charHeightScale);
//		this.text = text;
//	}
	
	private BitmapFont createFont(String fontPath) {
		return TrueTypeFontFactory.createBitmapFont(Gdx.files
				.internal(fontPath), Text.getFrontChars(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0.5f,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	
	public Text(float x, float y, float charWidthScale, float charHeightScale, BitmapFont font,
			String text,boolean isScoreText) {
		super(x, y, charWidthScale, charHeightScale, 1);
		
		this.fontPath = AssetConstants.FONT_IMAGICA;
		this.font = font;		
//		charWidthScale = 0.35f * GameMenuInfo.ratio_w;
//		charHeightScale = 0.35f * GameMenuInfo.ratio_w;
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
//			font.setScale(0.3f * (float)Gdx.graphics.getWidth() / 480f, 0.3f 
//					* (float)Gdx.graphics.getHeight() / 320f);
			font.setScale(0.5f, 0.5f 
					);
			if(Gdx.graphics.getHeight() > 500)
				font.setScale(0.4f, 0.4f);
				
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
