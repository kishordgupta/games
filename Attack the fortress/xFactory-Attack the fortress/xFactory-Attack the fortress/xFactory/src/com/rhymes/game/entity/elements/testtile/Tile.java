package com.rhymes.game.entity.elements.testtile;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {
	public Tile(int row, int col, int type, TextureRegion image) {
		this.row = row;
		this.col = col;
		this.type = type;
		this.image = image;
	}

	public int row, col, type;
	public TextureRegion image;
	
	public TextureRegion getImage()
	{
		return image;
	}
	
	public void setImage(TextureRegion image)
	{
		this.image = image;
	}
}
