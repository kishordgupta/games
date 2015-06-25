package com.rhymes.game.entity.elements.solitaire.menu;

import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class MenuPanel extends ElementBase{
	
	String imagePath;
//	private boolean touched;
	
	public MenuPanel(float x, float y, float width, float height,
			int zIndex, String imagePath){
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
		
//		setVisibile(false);
	}
	
	@Override
	public void render() {
//		if(isVisibile()){
			GlobalVars.ge.getRenderer().render(image, x, y, width, height);
//		}
	}

	
}
