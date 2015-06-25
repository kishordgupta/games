/**
 * 
 */
package com.rhymes.game.entity.elements.testtile;

import com.badlogic.gdx.graphics.Texture;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.interactions.testtile.ICPorcupine;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

/**
 * @author MajorArif
 *
 */
public class Porcupine extends ElementBase implements ICPorcupine {

	public int row = 0;
	public int col = 0;
	public float PorcupinesizeX=TileSet.TILE_SIZE_X;
	public float PorcupinesizeY=TileSet.TILE_SIZE_Y;
	public String imgname="";
	public Texture tex;
	public float PorcupinePosX=0f;
	public float PorcupinePosY=0f;
	public int PathTile=0;
	public boolean direction=true;
	public int PrevRow=2000;
	public int PrevCol=2000;
	public int pathId=0;
	public float pathLength=0f;
	public Porcupine() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render() {
		
//		GlobalVars.ge.getRenderer().render(image, col * TileSet.TILE_SIZE_X,
//		row * TileSet.TILE_SIZE_Y, herosizeX,
//		herosizeY, TileSet.TILE_SIZE_X / 2,
//		TileSet.TILE_SIZE_Y / 2, rotateAngle+90f, 1, 1);
		
		GlobalVars.ge.getScreen().getBatch().draw(tex,
				PorcupinePosX,PorcupinePosY , PorcupinesizeX, PorcupinesizeY, 0, 0,
				tex.getWidth(), tex.getHeight(), false, false);
	}
	
	public Porcupine(int row, int col,int HeroType,String imgName) {
		this.row = row;
		this.col = col;
		imgname= imgName;
	}

	
	public Porcupine(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.rhymes.ge.core.entity.elements.ElementBase#getAssets(com.rhymes.ge.pw.assets.AssetPack)
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
		
	}
	/* (non-Javadoc)
	 * @see com.rhymes.ge.core.entity.elements.ElementBase#init()
	 */
	@Override
	public void init() {
		tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+imgname+".png").getTexture();
		PorcupinePosX=col * TileSet.TILE_SIZE_X;
		PorcupinePosY=row * TileSet.TILE_SIZE_Y;
		PorcupinesizeX=TileSet.TILE_SIZE_X;
		PorcupinesizeY=TileSet.TILE_SIZE_Y;
//		if(imgname.compareTo("1_downHero1")==0  || imgname.compareTo("1_upHero1")==0)
//		{
//			PorcupinesizeX=TileSet.TILE_SIZE_X;
//			PorcupinesizeY=TileSet.TILE_SIZE_Y;
//			
//		}
//		else if(imgname.compareTo("1_leftHero1")==0  || imgname.compareTo("1_rightHero1")==0)
//		{
//			PorcupinesizeX=TileSet.TILE_SIZE_X;
//			PorcupinesizeY=TileSet.TILE_SIZE_Y;
//			
//		}
	}
	
	
	@Override
	public void step(long stepTime) {
		
	}

	@Override
	public void onCollision(HeroTile hero) {
//		Helper.println("Collided with hero: " + hero.getId());
		collided = true;
//		if(direction){
//			this.direction=false;
//		}
//		else
//		{
//			this.direction=false;
//		}
	}

	boolean collided = false;
	@Override
	public boolean isCollided() {
		return collided;
	}

}
