package com.rhymes.game.carspeedpro;

import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class BackGroundUniversal extends ElementBase implements InteractionTouchCallbacks{
	
	String imagePath;
	
	public BackGroundUniversal(float x, float y, float width, float height, int zIndex, String imagePath) {
		super(x, y, width, height, zIndex);
		
		this.imagePath = imagePath; 
	}

	@Override
	public void init() {
		this.image = Helper.getImageFromAssets(imagePath);

	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(imagePath);

	}

	@Override
	public void onEvent(Point hitPoint) {
		
	}
	
	
	protected boolean checkHit(Point p)
	{
		if(this.getLeft() <= p.x && this.getRight()>= p.x && this.getTop() >= p.y && this.getBottom() <= p.y)
			return true;
		return false;
	}

}
