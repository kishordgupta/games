package com.rhymes.attackTheFortress;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class LifeBar extends ElementBase {
	int type=0;
	private ElementBase element;
	private int Character=0;
	private float maxwidth=0;
	private boolean isShow=true;
	@Override
	public void render() {
		if(!isShow)
			return;
		
		super.render();
	}

	public LifeBar(float X,float Y,float width,float height,int type,ElementBase t,int charType) {
		this.Character=charType;
		this.x=X;
		this.y=Y;
		this.width=width;
		this.height=height;
		this.element=t;
		this.maxwidth=width;
//		Helper.println("lifePox: "+this.x+"  "+this.y, true);
//		Helper.println("width,height: "+this.width+"  "+this.height, true);
		this.type=type;
		if(this.type==1){
			this.image=Helper.getImageFromAssets(AssetConstants.IMG_LIFE_BAR_GREEN);
		}
		else if(this.type==2){
			this.image=Helper.getImageFromAssets(AssetConstants.IMG_LIFE_BAR_YELLOW);
	}
	}

	public LifeBar(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub

	}

	@Override
	public void step(long stepTime) {
//		Helper.println("coming....1");
		if(this.Character==2){
		if(this.type==1)
		this.width=(this.maxwidth*((Enemy) element).getLife())/((Enemy) element).getMaxLife();
//		Helper.println("coming....2");
		this.x=((Enemy)element).getlocation().x;
		this.y=((Enemy)element).getlocation().y;
		}
		else if(this.Character==1){
			if(this.type==1)
				this.width=(this.maxwidth*((Tower) element).getLife())/((Tower) element).getMaxLife();
				}
		if(this.Character==1)
			isShow=true;
		else if(this.x>9f*LevelInfo.ratioX && this.x<(Gdx.graphics.getWidth()-9f*LevelInfo.ratioX) && this.y>8f*LevelInfo.ratioY && y<Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()/15f+10f*LevelInfo.ratioY))
			isShow=true;
		else 
			isShow=false;
		super.step(stepTime);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
