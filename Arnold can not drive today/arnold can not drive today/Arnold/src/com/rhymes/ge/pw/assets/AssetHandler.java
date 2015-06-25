package com.rhymes.ge.pw.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.data.AssetConstants;

public class AssetHandler {
	private AssetManager assetManager;
	
	public AssetManager getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}

	public AssetHandler()
	{
		assetManager = new AssetManager();
	}
	
	/** Loads all the assets in the assetPack. */
	public void loadAssets (AssetPack assetPack) {
		Array<AssetDescriptor> assets = assetPack.getDescriptors();
		for (int i = 0, n = assets.size; i < n; i++) {
			AssetDescriptor asset = assets.get(i);
			assetManager.load(asset.fileName, asset.type, asset.params);
			System.out.println("Loading file: " + asset.fileName);
		}
		assetManager.finishLoading();
	}
	
	/** Clears and disposes all assets and the preloading queue. */
	public void clearAssets()
	{
		assetManager.clear();
	}

	public Texture getTexture(String filename) {
		return assetManager.get(filename, Texture.class);
	}

	public void addTexture(String fileName)
	{
		AssetPack assetPack = new AssetPack();		
		assetPack.addTexture(AssetConstants.IMG_BRUSH_BLUE_3);
		loadAssets(assetPack);
	}

}
