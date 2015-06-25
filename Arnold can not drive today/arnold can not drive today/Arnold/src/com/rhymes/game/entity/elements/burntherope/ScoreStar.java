package com.rhymes.game.entity.elements.burntherope;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.animations.common.AniRotate;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.game.entity.states.StateElementNormal;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.interactions.rangeTraversal.ICRangePathTraversal;
import com.rhymes.game.interactions.rangeTraversal.InfoRangeTraversal;
import com.rhymes.game.stage.levels.XLevel;
import com.rhymes.game.stage.result.Result;
import com.rhymes.game.stage.result.ResultBTRClassic;
import com.rhymes.game.stage.result.ResultBTRMAP;
import com.rhymes.game.stage.result.ResultBTRTime;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class ScoreStar extends ElementBase{
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
	}

	private static float DEFAULT_HEIGHT = 35;
	private static float DEFAULT_WIDTH = 35;
	
	private Result result;
	
	int starNumber, starCollected;
	@Override
	public void render() {
//		width = image.getRegionWidth();
//		height = image.getRegionHeight();
		
		if(result instanceof ResultBTRTime)
		{
			starCollected = ((ResultBTRTime)result).getStarCollected();
			starNumber = ((ResultBTRTime)result).getLevelStarNumber();
		}
		else if(result instanceof ResultBTRClassic)
		{
			starCollected = ((ResultBTRClassic)result).getStarCollected();
			starNumber = ((ResultBTRClassic)result).getLevelStarNumber();
		} 
		
		for(int i = 0; i < starNumber; i++)
		{
			if( i < starCollected)
			{
				GlobalVars.ge.getRenderer().getBatch().setColor(1f,1f,1,1f);
				GlobalVars.ge.getRenderer().render(image, x+ i*width +10, y, width, height, width/2f, height/2f,0);
				GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
			}
			else{
			GlobalVars.ge.getRenderer().getBatch().setColor(1f,1f,1,0.4f);
			GlobalVars.ge.getRenderer().render(image, x+ i*width +10, y, width, height, width/2f, height/2f, 0);
			GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
			}
		}
		/*
		GlobalVars.ge.getRenderer().getBatch().setColor(1f,1f,1,0.9f);
		GlobalVars.ge.getRenderer().render(image, x-width/2f, y - height/2f, width, height, width/2f, height/2f, this.rotateAngle);
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);*/
	}
	
	public ScoreStar(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		if(((XLevel)GlobalVars.ge.getCurrentStage()).result instanceof ResultBTRTime)
			this.result = (ResultBTRTime) ((XLevel)GlobalVars.ge.getCurrentStage()).result;
		else if(((XLevel)GlobalVars.ge.getCurrentStage()).result instanceof ResultBTRClassic)
			this.result = (ResultBTRClassic) ((XLevel)GlobalVars.ge.getCurrentStage()).result;
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_STAR);
	}

	@Override
	public void init() {
		setImage(Helper.getImageFromAssets(AssetConstants.IMG_STAR));		
	}
}
