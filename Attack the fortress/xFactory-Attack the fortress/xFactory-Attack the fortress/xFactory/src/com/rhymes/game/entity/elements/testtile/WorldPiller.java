package com.rhymes.game.entity.elements.testtile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.stage.levels.TestTileLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class WorldPiller extends ElementBase {

	
	private float pillerX=0;
	private float pillerY=0;
	private float pillerHeight=0;
	private float pillerWidth=0;
	private String imageName;
	private TextureRegion text;
	private int pillerRow;
	private int pillerCol;
	
	public int getRow()
	{
		return pillerRow;
	}
	public int getCol()
	{
		return pillerCol;
	}
	public float getX()
	{
		return pillerX;
	}
	public float getY()
	{
		return pillerY;
	}
	public WorldPiller() {
		// TODO Auto-generated constructor stub
	}
	public WorldPiller(int row, int col,int TileType,String imgName) {
		this.pillerX = col*TileSet.TILE_SIZE_X;
		this.pillerY = row*TileSet.TILE_SIZE_Y;
		this.pillerHeight=TileSet.TILE_SIZE_Y;
		this.pillerWidth=TileSet.TILE_SIZE_X;
		this.imageName= imgName;
		this.pillerCol=col;
		this.pillerRow=row;
		
	}


	public WorldPiller(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub

	}
	@Override
	public void init() {
		text = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+imageName+".png");
		TileSet.tiles[pillerRow][pillerCol].type=2;
		if(TileSet.tiles[pillerRow][pillerCol].image==Helper.getImageFromAssets(AssetConstants.IMG_TBLOCK_0))
			TileSet.MOW_NUMBER--;
		TileSet.tiles[pillerRow][pillerCol].image=text;
	}

	@Override
	public void step(long stepTime) {
		
			}

	@Override
	public void render() {
		
		
//		GlobalVars.ge.getScreen().getBatch().draw(text,
//				pillerX,pillerY , pillerWidth, pillerHeight, 0, 0,
//				text.getWidth(), text.getHeight(), false, false);
	}

}
