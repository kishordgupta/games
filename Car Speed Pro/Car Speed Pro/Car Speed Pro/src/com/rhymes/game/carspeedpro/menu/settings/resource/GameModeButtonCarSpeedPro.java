package com.rhymes.game.carspeedpro.menu.settings.resource;

import com.rhymes.game.carspeedpro.AssetConstantsCarSpeedPro;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class GameModeButtonCarSpeedPro extends Button{

	String imagePath;
	public static boolean free = true;
	public GameModeButtonCarSpeedPro(float x, float y, float width,
			float height, int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;
	}

	public String setSpecifiedImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstantsCarSpeedPro.free;
		else
			this.imagePath = AssetConstantsCarSpeedPro.time;
		
		return imagePath;
	}
	
	@Override
	public void onEvent(Point htiPoint) {
		if(checkHit(htiPoint)){
			if(free)
				free = false;
			else 
				free = true;
			this.image = Helper.getImageFromAssets(setSpecifiedImagePath(free));			
		}
	}
}
