package com.rhymes.game.entity.elements.arnold.stage.menu.gameovermenu.button;

import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Score_Gameover_UI_Arnold extends ElementBase{
	String imagePath;


	public Score_Gameover_UI_Arnold(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex);
		
		this.imagePath = imagePath;
	}
	
	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(imagePath);

	}

	@Override
	public void init() {
		this.image = Helper.getImageFromAssets(imagePath);

	}


}
