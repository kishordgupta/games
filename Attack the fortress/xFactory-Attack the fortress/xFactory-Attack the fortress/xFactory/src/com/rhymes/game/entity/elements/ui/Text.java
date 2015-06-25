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

public class Text extends ElementBase{

	private String text = "";
	private BitmapFont font = null;
	private static String FONT_CHARACTERS= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
	private boolean isShow=true;	

	
	
	public Text(float x, float y, float charWidthScale, float charHeightScale, BitmapFont font,
			String text) {
		super(x, y, charWidthScale, charHeightScale, 1);
		
		this.font = font;
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
	
//	char[] d;
float w=0f,h=0f;
	@Override
	public void render() {
		/* compare the screen size
		 * if its greater than a certain value
		 * font.setScale(width/p, height/p)
		 * 
		 * here p is to be set to the percentage
		*/
		this.w=this.width;
		this.h=this.height;
		if(Gdx.graphics.getWidth()<400){
			this.w=this.width*1.5f;
			this.h=this.height*1.5f;
		}
		else if(Gdx.graphics.getWidth()>=400 && Gdx.graphics.getWidth()<480){
			this.w=this.width*1.35f;
			this.h=this.height*1.35f;
		}
		else if(Gdx.graphics.getWidth()>600 && Gdx.graphics.getWidth()<=800){
			this.w=this.width/1.75f;
			this.h=this.height/1.75f;
		}
		else if(Gdx.graphics.getWidth()>800 && Gdx.graphics.getWidth()<1100){
			this.w=this.width/2f;
			this.h=this.height/2f;
		}
		
		else if(Gdx.graphics.getWidth()>1100){
			this.w=this.width/2.5f;
			this.h=this.height/2.5f;
		}
		if(!isShow)
			return;
		if(font != null){
			font.setScale(w, h);
			font.draw(GlobalVars.ge.getScreen().getBatch(), text, x, y);
		}
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
