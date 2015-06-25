package com.rhymes.game.entity.elements.testtile;

import com.badlogic.gdx.graphics.Texture;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.stage.levels.TestTileLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class MinusScore extends ElementBase {
	float posX_minus=0f;
	float posY_minus=0f;
	float posX_yuak=0f;
	float posY_yuak=0f;
	Texture tex_minusscore;
	Texture tex_yuak;

	public MinusScore() {
		// TODO Auto-generated constructor stub
	}
public MinusScore(float x, float y){
	this.posX_minus=x;
	this.posY_minus=y;
}
	public MinusScore(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render()
	{
		float x=posX_minus;
		float y=posY_minus;
		GlobalVars.ge.getScreen().getBatch().draw(tex_minusscore,x+16f*LevelInfo.ratioX,y+16f*LevelInfo.ratioY , 45f*LevelInfo.ratioX, 26f*LevelInfo.ratioY, 0, 0,tex_minusscore.getWidth(), tex_minusscore.getHeight(), false, false);
		GlobalVars.ge.getScreen().getBatch().draw(tex_yuak,posX_yuak,posY_yuak , 45f*LevelInfo.ratioX, 26f*LevelInfo.ratioY, 0, 0,tex_yuak.getWidth(), tex_yuak.getHeight(), false, false);
//		if(isShit){
//			
//		}
	}
	
	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub

	}
	float skiptime=1000f;
	float elaspedtime=0f;
	@Override
	public void step(long stepTime) 
	{
		elaspedtime+=stepTime;
//		posX_minus=posX_minus;
		posY_minus+=1f;
		posX_yuak=(((TestTileLevel)GlobalVars.ge.getCurrentStage()).hero().getX())+5f;
		posY_yuak=(((TestTileLevel)GlobalVars.ge.getCurrentStage()).hero().getY())+57f;
		if(elaspedtime>skiptime){
			elaspedtime=0f;
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).removeElement(this);
			
		}
	}

	@Override
	public void init() {
		tex_minusscore=Helper.getImageFromAssets(AssetConstants.IMG_MINUS_SCORE).getTexture();
		tex_yuak=Helper.getImageFromAssets(AssetConstants.IMG_YUCK).getTexture();
	}

}
