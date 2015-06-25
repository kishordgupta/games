package com.rhymes.game.entity.elements.arnold.stage.menu.gameovermenu;

import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class GameOver_Arnold_B_G extends ElementBase{

	String imagePath_1, imagePath_2;
	boolean is_gameOver;
	
	public GameOver_Arnold_B_G(float x, float y, float width, float height, int zIndex,
			String imagePath_1, String imagePath_2, boolean isGameOver) {
		super(x, y, width, height, zIndex);
		
		this.imagePath_1 = imagePath_1;
		this.imagePath_2 = imagePath_2;
		
		this.is_gameOver = isGameOver;
	}
	
	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(imagePath_1);
		assetPack.addTexture(imagePath_2);
	}

	@Override
	public void init() {
		if(is_gameOver)
			this.image = Helper.getImageFromAssets(imagePath_1);
		else
			this.image = Helper.getImageFromAssets(imagePath_2);
	}

}
