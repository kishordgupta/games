package com.rhymes.attackTheFortress;

import com.rhymes.game.entity.elements.ui.Button;

public class TSVInfo extends Button {
public int towerNumber=0;
	public TSVInfo(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public TSVInfo(float x, float y, float width, float height, int zIndex,
			String imagePath,int num) {
		super(x, y, width, height, zIndex, imagePath);
		towerNumber=num;
	}

}
