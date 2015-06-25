package com.rhymes.game.carspeedpro.menu.settings.resource;

import com.rhymes.game.carspeedpro.AssetConstantsCarSpeedPro;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;

public class SoundButtonCarSpeedPro extends Button{
	
	String imagePath;
	
	public SoundButtonCarSpeedPro(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

		this.imagePath = imagePath;
	}

	public String setSpecifiedImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstantsCarSpeedPro.on;
		else
			this.imagePath = AssetConstantsCarSpeedPro.off;
		
		return imagePath;
	}

	@Override
	public void onEvent(Point htiPoint) {
		if(checkHit(htiPoint)){
			///work to do
		}
	}
	
	
}
