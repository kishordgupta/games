package com.rhymes.game.entity.elements.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.controller.FontController;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Text extends ElementBase{

	private String text = "";
	private BitmapFont font = null;
	private String fontName;
	private FontController fontController;
	
	private static String FONT_CHARACTERS= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
	private boolean isShow=true;	

	
	
	public Text(float x, float y, float charWidthScale, float charHeightScale, FontController fontController,  String fontName,
			String text) {
		super(x, y, charWidthScale, charHeightScale, 1);
		
//		this.font = font;
		this.fontName = fontName;
		this.fontController = fontController;
		this.font = fontController.getFont(fontName);
		font.setScale(charWidthScale, charHeightScale);
		this.text = text;
	}

//	public Text(float x, float y, float width, float height, int zIndex, String fontFileName) {
//		super(x, y, width, height, zIndex);
//		font = new BitmapFont(Gdx.files.internal(fontFileName),false);
//
//	}
	
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

	
	public void setFontScale(float scaleX, float scaleY)
	{
		this.width = scaleX;
		this.height = scaleY;
	}

	@Override
	public void render() {
		fontController.getFont(this.fontName).draw(GlobalVars.ge.getScreen().getBatch(), text, x, y);
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}

	public BitmapFont getFont() {
		return font;
	}
	public void setActive(boolean b){
		this.isShow = b;
	}
	
	public boolean isActive(){
		return isShow;
	}
}
