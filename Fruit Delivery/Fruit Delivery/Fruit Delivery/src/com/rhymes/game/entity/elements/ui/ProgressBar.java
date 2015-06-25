package com.rhymes.game.entity.elements.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.stage.menus.stick.LevelInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class ProgressBar extends ElementBase{

	float w, r = 1, g = 1 , b = 1 , a = 1;
	String text;
	BitmapFont font;
	@Override
	public void render() {
		GlobalVars.ge.getRenderer().render(backImage, x, y, width, height);
		
		GlobalVars.ge.getScreen().getBatch().setColor(r, g, b,a);
		w = width * progress - 40 * LevelInfo.ratioX;
		GlobalVars.ge.getRenderer().render(barImage, x+10 * LevelInfo.ratioX, y, w < 0 ? 0:width * progress - 40 * LevelInfo.ratioX, height);
		GlobalVars.ge.getScreen().getBatch().setColor(Color.WHITE);
		
		
		text = prefix + (int)(progress * 100) + "%";
		if(textEnabled){
			font = GlobalVars.ge.getCurrentStage().getFontController().getFont(fontName);
			font.setColor(Color.DARK_GRAY);
			font.draw(
					GlobalVars.ge.getScreen().getBatch(), text,
					this.x + (width-font
							.getBounds(text).width)/2f, this.y + 10 * LevelInfo.ratioY);
		}
	}

	private String backgroundImagePath;
	private String progressBarImagePath;
	
	private TextureRegion backImage;
	private TextureRegion barImage;
	
	private float progress = 0.0f;
	
	private String fontName, prefix;
	private boolean textEnabled = false;
	public ProgressBar(float x, float y, float width, float height, String backgroundImagePath, String progressBarImagePath)
	{
		super(x, y, width, height, 1);
		this.backgroundImagePath = backgroundImagePath;
		this.progressBarImagePath = progressBarImagePath;
		textEnabled = false;
	}
	
	public ProgressBar(float x, float y, float width, float height, String backgroundImagePath, String progressBarImagePath, 
			String prefix, String fontName)
	{
		super(x, y, width, height, 1);
		this.backgroundImagePath = backgroundImagePath;
		this.progressBarImagePath = progressBarImagePath;
		this.fontName = fontName;
		this.prefix = prefix;
		textEnabled = true;
	}
	
	@Override
	public void init() {
		this.backImage = Helper.getImageFromAssets(backgroundImagePath);
		this.barImage = Helper.getImageFromAssets(progressBarImagePath);
//		setColor(0.2f, 0.6f, 0.7f,1f);
		setColor(0.8f, 0.6f, 0.7f,1f);
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(backgroundImagePath);
		assetPack.addTexture(progressBarImagePath);
	}

	public float getProgress() {
		return progress;
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}
	
	public void setColor(float r, float g, float b, float a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
}
