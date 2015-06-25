package com.rhymes.game.entity.elements.actiondriver;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class GearSwitch extends ElementBase{

	float maxScale = 3;
	float stepScale = 0.2f;
	float curScale = 0f;
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
		curScale += stepScale;
		if(curScale > maxScale)
			GlobalVars.ge.getCurrentStage().postDestroyed(this);
		this.font.setScale(curScale);
	}

	private String text = "";
	private BitmapFont font = null;
	private static String FONT_CHARACTERS= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
	private boolean isShow=true;	

	public GearSwitch(float x, float y, float maxScale, float stepScale, BitmapFont font,
			String text) {
		super(x, y, 1, 1, 1);
		
		this.font = font;
		this.font.setScale(0.1f,0.2f);
		this.text = text;
		this.maxScale = maxScale;
		this.stepScale = stepScale;
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
	public void render(){
		font.setColor(Color.YELLOW);
//		Helper.println("X: " + x);
//		Helper.println("text width: " + this.font.getBounds(this.text).width/2f);
//		Helper.println("render x: " + (x-this.font.getBounds(this.text).width/2f) + Helper.getCameraX());
		font.draw( GlobalVars.ge.getScreen().getBatch(), text, x+ Helper.getCameraX()-this.font.getBounds(this.text).width/2f, y -this.font.getBounds(this.text).height/2f );
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
