package com.rhymes.game.entity.elements.testtile;

import com.badlogic.gdx.graphics.Texture;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;

public class scorelbl extends ElementBase {
	private Texture tex;
	private boolean isShow=true;
	
	
	public void setActive(boolean b){
		this.isShow = b;
	}
	
	public boolean isActive(){
		return isShow;
	}
	
	public scorelbl() {
		// TODO Auto-generated constructor stub
	}
	public scorelbl(float x, float y, float width,float height, Texture tex) {
		super(x, y, width, height, 1);
		this.tex=tex;
	}

	public scorelbl(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub

	}
	@Override
	public void render() {
		if(!isShow)
			return;
		GlobalVars.ge.getScreen().getBatch().draw(tex,
		x,y ,width, height, 0, 0,
		tex.getWidth(), tex.getHeight(), false, false);
	
	}


	@Override
	public void init() {
		//tex = Helper.getImageFromAssets(AssetConstants.IMG_LBL_BONUS).getTexture();

	}

}
