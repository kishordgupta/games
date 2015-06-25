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
	private String fontPath;
	private FontController fontController;
	
	private boolean isShow=true;
	
	private float r, g, b, a;
	
	public Text(float x, float y, FontController fontController,  String fontName,
			String text) {
		super(x, y, 1, 1, 1);
		
//		this.font = font;
		this.fontName = fontName;
		this.fontController = fontController;
		this.font = fontController.getFont(fontName);
		this.text = text;
		r = g = b = a = 1;
	}
	
	public Text(float x, float y, float charWidthScale, float charHeightScale, FontController fontController,  String fontName,
			String text) {
		super(x, y, charWidthScale, charHeightScale, 1);
		
//		this.font = font;
		this.fontName = fontName;
		this.fontController = fontController;
		this.font = fontController.getFont(fontName);
		font.setScale(charWidthScale, charHeightScale);
		this.text = text;
		r = g = b = a = 1;
	}

	public Text(float x, float y, float charWidthScale, float charHeightScale, FontController fontController,  String fontName, String fontPath,
			String text) {
		super(x, y, charWidthScale, charHeightScale, 1);
		
//		this.font = font;
		this.fontName = fontName;
		this.fontPath = fontPath;
		this.fontController = fontController;
		this.font = fontController.addFont(fontName, fontPath);
		this.width = charWidthScale;
		this.height = charHeightScale;
//		font.setScale(charWidthScale, charHeightScale);
		this.text = text;
		r = g = b = a = 1;
		
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

	
	
	public void setColor(float r, float g, float b, float a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	@Override
	public void render() {
//		fontController.getFont(this.fontName).setScale(this.width, this.height);
		fontController.getFont(this.fontName).setColor(r, g, b, a);
		fontController.getFont(this.fontName).draw(GlobalVars.ge.getScreen().getBatch(), text, x, y);
//		fontController.getFont(this.fontName).drawWrapped(GlobalVars.ge.getScreen().getBatch(), text, x, y-200, 100);		
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
