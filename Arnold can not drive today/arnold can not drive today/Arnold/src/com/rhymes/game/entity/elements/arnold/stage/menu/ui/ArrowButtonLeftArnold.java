package com.rhymes.game.entity.elements.arnold.stage.menu.ui;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;


public class ArrowButtonLeftArnold extends Button{

	String imagePath;
	
	public ArrowButtonLeftArnold(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

		this.imagePath = imagePath;
	}
	
	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			
			
		}
	}
}