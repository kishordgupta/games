package com.rhymes.game.bulletwar.menu.main.res;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;

public class ExitBullet extends Button{

	String imagePath;
	
	public ExitBullet(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;
	}

	@Override
	public void onEvent(Point htiPoint) {
		if(checkHit(htiPoint))
			System.exit(0);
	
	}

}
