package com.rhymes.game.entity.elements.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Textold extends ElementBase{

	private String text = "";
	private BitmapFont font = null;
	
	public Textold(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		font = new BitmapFont();
	}

	public Textold(float x, float y, float width, float height, int zIndex, String fontFileName) {
		super(x, y, width, height, zIndex);
		font = new BitmapFont(Gdx.files.internal(fontFileName),false);
	}
	
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

	char[] d;

	@Override
	public void render() {
		if(font != null){
			font.setColor(0.3f, 0.4f, 1.0f, 1.0f);
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
