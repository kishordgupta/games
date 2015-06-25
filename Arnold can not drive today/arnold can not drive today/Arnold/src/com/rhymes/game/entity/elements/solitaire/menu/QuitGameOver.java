package com.rhymes.game.entity.elements.solitaire.menu;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;

public class QuitGameOver extends Button{
	String imagePath;
	float y;

	public QuitGameOver(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

		this.imagePath = imagePath;
	}

	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			System.exit(0);

		}
	}
}
