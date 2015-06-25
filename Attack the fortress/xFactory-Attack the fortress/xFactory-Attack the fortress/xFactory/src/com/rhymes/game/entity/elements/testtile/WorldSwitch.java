package com.rhymes.game.entity.elements.testtile;

import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.stage.levels.TestTileLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class WorldSwitch extends ElementBase {

	private float switchX=0;
	private float switchY=0;
	private float switchHeight=0;
	private float switchWidth=0;
	private String imageName;
	private TextureRegion text;
	private int switchRow;
	private int switchCol;
	private boolean isSwitch=false;
	public ArrayList<WorldPiller> Wpl = new ArrayList<WorldPiller>();
	public void setActiveSwitch(boolean b){
		this.isSwitch = b;
	}
	
	public boolean isActiveSwitch(){
		return isSwitch;
	}
	
	public WorldSwitch() {
		// TODO Auto-generated constructor stub
	}
	
	public WorldSwitch(int row, int col,int TileType,String imgName) {
		this.switchX = col*TileSet.TILE_SIZE_X;
		this.switchY = row*TileSet.TILE_SIZE_Y;
		this.switchHeight=TileSet.TILE_SIZE_Y;
		this.switchWidth=TileSet.TILE_SIZE_X;
		this.imageName= imgName;
		this.switchRow=row;
		this.switchCol=col;
		
	}

	public WorldSwitch(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub

	}

	public int getRow()
	{
		return switchRow;
	}
	public int getCol()
	{
		return switchRow;
	}
	public float getX()
	{
		return switchX;
	}
	public float getY()
	{
		return switchY;
	}
	@Override
	public void init() {
		text = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+imageName+".png");
		TileSet.tiles[switchRow][switchCol].type=1;
		if(TileSet.tiles[switchRow][switchCol].image==Helper.getImageFromAssets(AssetConstants.IMG_TBLOCK_0))
		TileSet.MOW_NUMBER--;
		TileSet.tiles[switchRow][switchCol].image=text;
	}

	
	@Override
	public void render() {
		
		
//		GlobalVars.ge.getScreen().getBatch().draw(text,
//				switchX,switchY , switchWidth, switchHeight, 0, 0,
//				text.getWidth(), text.getHeight(), false, false);
	}
	
	@Override
	public void step(long stepTime) {
		if(((TestTileLevel)GlobalVars.ge.getCurrentStage()).hero().getRow()==this.switchRow && ((TestTileLevel)GlobalVars.ge.getCurrentStage()).hero().getCol()==this.switchCol)
		{
			//toggleSwitch();
		if(TileSet.tiles[switchRow][switchCol].image==Helper.getImageFromAssets(AssetConstants.IMG_GREENSWITCH)){
			TileSet.tiles[switchRow][switchCol].image=Helper.getImageFromAssets(AssetConstants.IMG_GREENSWITCH_BLANK);
		}
			
		
		else if(TileSet.tiles[switchRow][switchCol].image==Helper.getImageFromAssets(AssetConstants.IMG_YELLOWSWITCH)){
			TileSet.tiles[switchRow][switchCol].image=Helper.getImageFromAssets(AssetConstants.IMG_YELLOWSWITCH_BLANK);

		}
					
		else if(TileSet.tiles[switchRow][switchCol].image==Helper.getImageFromAssets(AssetConstants.IMG_REDSWITCH))
		{
			TileSet.tiles[switchRow][switchCol].image=Helper.getImageFromAssets(AssetConstants.IMG_REDSWITCH_BLANK);
		}
		//Helper.println("toggle: "+this.isActiveSwitch());
	}
		}
	
	
	
}
