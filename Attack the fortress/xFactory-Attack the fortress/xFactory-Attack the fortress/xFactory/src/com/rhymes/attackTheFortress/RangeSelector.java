package com.rhymes.attackTheFortress;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class RangeSelector extends ElementBase {

	private boolean correctPosition =true;
	Texture tax;
	float range=0;
	public boolean getCorrectPosition(){
		return this.correctPosition;
	}
	@Override
	public void render() {
		GlobalVars.ge.getRenderer().render(image, x-range, y - range, 
				range*2f, range*2f, 0, 0, 0);
//		super.render();
	}
	public void setCorrectPosition(boolean bool){
		this.correctPosition=bool;
	}
	public RangeSelector(float X,float Y ,int towerType) {
		this.x=X;
		this.y=Y;
		range=xmlTowerReader.towers.get(towerType-1).range*LevelInfo.ratioX/2f;
		this.height=40f*LevelInfo.ratioX;
		this.width=40f*LevelInfo.ratioX;
		
		this.image=Helper.getImageFromAssets(AssetConstants.IMG_REGION_CORRECT);
		this.tax=this.image.getTexture();
	}

	public RangeSelector(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub

	}

	@Override
	public void step(long stepTime) {
		
		if(this.correctPosition)
		{
			this.image=Helper.getImageFromAssets(AssetConstants.IMG_REGION_CORRECT);
			this.tax=this.image.getTexture();
		}
		else
		{
			this.image=Helper.getImageFromAssets(AssetConstants.IMG_REGION_FALSE);
			this.tax=this.image.getTexture();
		}
		super.step(stepTime);
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
