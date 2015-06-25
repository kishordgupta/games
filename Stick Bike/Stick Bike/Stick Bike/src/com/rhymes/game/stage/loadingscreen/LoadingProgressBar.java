package com.rhymes.game.stage.loadingscreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.stage.menus.stick.LevelInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class LoadingProgressBar extends ElementBase{

	float w;
	@Override
	public void render() {
		GlobalVars.ge.getRenderer().render(backImage, x, y, width, height);
		
		GlobalVars.ge.getScreen().getBatch().setColor(0.2f, 0.6f, 0.7f,1f);
		w = width * progress - 40 * LevelInfo.ratioX;
		GlobalVars.ge.getRenderer().render(barImage, x+10 * LevelInfo.ratioX, y, w < 0 ? 0:width * progress - 40 * LevelInfo.ratioX, height);
		GlobalVars.ge.getScreen().getBatch().setColor(Color.WHITE);
	}

	private String backgroundImagePath;
	private String progressBarImagePath;
	
	private TextureRegion backImage;
	private TextureRegion barImage;
	
	private float progress = 0.5f;

	public LoadingProgressBar(float x, float y, float width, float height, String backgroundImagePath, String progressBarImagePath)
	{
		super(x, y, width, height, 1);
		this.backgroundImagePath = backgroundImagePath;
		this.progressBarImagePath = progressBarImagePath;
	}
	
	@Override
	public void init() {
		this.backImage = Helper.getImageFromAssets(backgroundImagePath);
		this.barImage = Helper.getImageFromAssets(progressBarImagePath);
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
}
