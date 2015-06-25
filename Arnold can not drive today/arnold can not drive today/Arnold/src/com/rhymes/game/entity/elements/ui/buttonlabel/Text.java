package com.rhymes.game.entity.elements.ui.buttonlabel;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;

public class Text extends ElementBase{

	private String text = "";
	private BitmapFont font = null;
	private static String FONT_CHARACTERS= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
		
	
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

//	char[] d;

	@Override
	public void render() {
		font = GlobalVars.ge.getCurrentStage().getFont();
		if(font != null){
			font.draw(GlobalVars.ge.getScreen().getBatch(), text, x, y);
		}
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}

	public BitmapFont getFont() {
		return font;
	}
}
