package com.rhymes.game.entity.elements.testtile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.testtile.tiles.TileDefault;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;
import com.rhymes.sundayLawn.XmlRead;

public class TileSet extends ElementBase {
	public static int MAX_ROW = 20;
	public static int MAX_COL = 20;

	public static float TILE_SIZE_X =  (32*LevelInfo.ratioX);
	public static float TILE_SIZE_Y =  (32*LevelInfo.ratioX);

	
	// declare the tile types here
	public static final int TILE_NONE = -2;
	public static final int TILE_BLANK = -1;
	
	public static final int TILE_TYPE_1 = 2;
	public static final int TILE_TYPE_2 = 1;

	public static final int MOVE_NONE = 0;
	public static final int MOVE_UP = 1;
	public static final int MOVE_DOWN = 2;
	public static final int MOVE_LEFT = 3;
	public static final int MOVE_RIGHT = 4;
	public static int MOW_NUMBER = 0;

	public TextureRegion[] images = new TextureRegion[2];

	public static Tile[][] tiles ;
	
	public TileSet(int row, int col)
	{
		MAX_ROW = row;
		MAX_COL = col;
		if(TILE_SIZE_X>64)
			TILE_SIZE_X = 64;
		if(TILE_SIZE_Y>64)
			TILE_SIZE_Y = 64;
		tiles = new Tile[MAX_ROW][MAX_COL];
		Helper.println("Tile w: " + TILE_SIZE_X);
	}

	@Override
	public void init() {
		loadTiles();
	}

	private void loadTiles() {
		TextureRegion img = null;
		MOW_NUMBER=0;
		for(int i=0;i<XmlRead.worlds.size;i++)
		{	
			img=Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+XmlRead.worlds.get(i).tileImageName+".png");
			tiles[XmlRead.worlds.get(i).row][XmlRead.worlds.get(i).col] = new TileDefault(XmlRead.worlds.get(i).row, XmlRead.worlds.get(i).col, XmlRead.worlds.get(i).tileTypes, 
					img);
			if(XmlRead.worlds.get(i).tileTypes==1)
			{
				MOW_NUMBER++;
				
			}
		}
		Helper.println("mownumber: "+MOW_NUMBER);
	}

	
	public TextureRegion getTileImage(int row, int col)
	{
		return tiles[row][col].image;
	}
	
//	float k = 0;
	
	@Override
	public void render() {
		float count = 0;
		for (int i = 0; i < MAX_ROW; i++)
			for (int j = 0; j < MAX_COL; j++) {
				if(tiles[i][j] == null)
					continue;
				if (tiles[i][j].type != TILE_BLANK 
						&& tiles[i][j].type != TILE_NONE){

					if((float)(j+1) * TILE_SIZE_X < GlobalVars.ge.getScreen().cam.position.x - Gdx.graphics.getWidth()/2f)
						continue;
					if((float)j * TILE_SIZE_X > GlobalVars.ge.getScreen().cam.position.x + Gdx.graphics.getWidth()/2f)
						continue;

					if((float)(i+1) * TILE_SIZE_Y < GlobalVars.ge.getScreen().cam.position.y - Gdx.graphics.getHeight()/2f)
						continue;
					if((float)i * TILE_SIZE_Y > GlobalVars.ge.getScreen().cam.position.y + Gdx.graphics.getHeight()/2f)
						continue;
					
//					Helper.println("Rendering tile: row -> " + j + " col -> " + i );
//					count++;
//					
//					GlobalVars.ge.getRenderer().render(getTileImage(i,j),
//							j * TILE_SIZE_X, i * TILE_SIZE_Y, TILE_SIZE_X,
//							TILE_SIZE_Y, TILE_SIZE_X / 2, TILE_SIZE_Y / 2,
//							rotateAngle+90f, 1, 1);
					
					GlobalVars.ge.getScreen().getBatch().draw(getTileImage(i,j).getTexture(),
							(float)j * TILE_SIZE_X, (float)i * TILE_SIZE_Y, (float)TILE_SIZE_X, (float)TILE_SIZE_Y, 0, 0,
							getTileImage(i,j).getRegionWidth(), getTileImage(i,j)
									.getRegionHeight(), false, false);
				}
			}
//		Helper.println("Total tile rendered: " + count);
	}

	public void setTileType(int rowIndex, int colIndex, int tileType) {
		tiles[rowIndex][colIndex].type = tileType;
	}

	public int getTileTypeOnMove(int rowIndex, int colIndex, int move) {
		switch (move) {
		case MOVE_UP:
			if (rowIndex < MAX_ROW -1) {
				return tiles[rowIndex + 1][colIndex].type;
			}
			break;
		case MOVE_DOWN:
			if (rowIndex > 0) {
				return tiles[rowIndex - 1][colIndex].type;
			}
			break;
		case MOVE_LEFT:
			if (colIndex > 0) {
				return tiles[rowIndex][colIndex - 1].type;
			}
			break;
		case MOVE_RIGHT:
			if (colIndex < MAX_COL - 1) {
				return tiles[rowIndex][colIndex + 1].type;
			}
			break;
		default:
			break;
		}
		return TILE_NONE;
	}

	/**
	 * Add all the texture needed by this element in this function
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_1);
		assetPack.addTexture(AssetConstants.IMG_GREENPILLER_HIGH);
		assetPack.addTexture(AssetConstants.IMG_GREENPILLER_LOW);
		assetPack.addTexture(AssetConstants.IMG_GREENSWITCH);
		assetPack.addTexture(AssetConstants.IMG_GREENSWITCH_BLANK);
		assetPack.addTexture(AssetConstants.IMG_YELLOWPILLER_HIGH);
		assetPack.addTexture(AssetConstants.IMG_YELLOWPILLER_LOW);
		assetPack.addTexture(AssetConstants.IMG_YELLOWSWITCH);
		assetPack.addTexture(AssetConstants.IMG_YELLOWSWITCH_BLANK);
		assetPack.addTexture(AssetConstants.IMG_REDPILLER_HIGH);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_21);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_22);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_23);
		assetPack.addTexture(AssetConstants.IMG_REDPILLER_LOW);
		assetPack.addTexture(AssetConstants.IMG_REDSWITCH);
		assetPack.addTexture(AssetConstants.IMG_REDSWITCH_BLANK);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_27);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_28);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_29);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_30);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_31);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_32);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_33);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_34);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_35);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_41);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_42);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_43);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_44);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_45);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_36);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_37);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_38);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_39);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_40);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_46_lt);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_47_rt);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_48_lb);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_49_rb);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_50_lt);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_51_rt);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_52_lb);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_53_rb);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_54_lt);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_55_rt);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_56_lb);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_57_rb);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_0);
	}


	@Override
	public void step(long stepTime) {
	}

	/**
	 * 
	 * @param row
	 *            Row index of the tile
	 * @param col
	 *            Column Index of the tile
	 * @param bufferPoint
	 *            If null, creates a new Point, else updates the passed point
	 * @return bottom-left corner point
	 */
	public Point getTilePoint(int row, int col, Point bufferPoint) {
		if (bufferPoint == null)
			bufferPoint = new Point();
		bufferPoint.x = row * TILE_SIZE_X;
		bufferPoint.y = row * TILE_SIZE_Y;
		return bufferPoint;
	}
}
