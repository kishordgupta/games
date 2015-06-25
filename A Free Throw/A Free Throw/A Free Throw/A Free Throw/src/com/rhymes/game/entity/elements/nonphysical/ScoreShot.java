package com.rhymes.game.entity.elements.nonphysical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.animations.common.AniRotate;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.result.Result;
import com.rhymes.game.stage.result.ResultBTRClassic;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class ScoreShot extends ElementBase {
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
	}

	private static float DEFAULT_HEIGHT = 35;
	private static float DEFAULT_WIDTH = 35;

	private Result result;

	private int shotNumber, bounceCollected, shotTaken;
	private int ballSelected;
	private TextureRegion imageset1;
	private TextureRegion imageset2;
	
	private void loadTexture(int ballSelected2) {
		// TODO Auto-generated method stub
		imageset1 = Helper.getImageFromAssets(AssetConstants.IMG_AFTER_SHOT_BALL_1);
		imageset2 = Helper.getImageFromAssets(AssetConstants.IMG_SHOT_BALL_1);
	}
	@Override
	public void render() {
		// width = image.getRegionWidth();
		// height = image.getRegionHeight();
		if (result instanceof ResultBounce) {
			shotTaken = ((ResultBounce) result).getShotTaken();
			shotNumber = ((ResultBounce) result).getShotNumber();
		} 
//		else if (result instanceof ResultBTRTime) {
//			bounceCollected = ((ResultBTRTime) result).getStarCollected();
//			ballNumber = ((ResultBTRTime) result).getLevelStarNumber();
//		} else if (result instanceof ResultBTRClassic) {
//			bounceCollected = ((ResultBTRClassic) result).getStarCollected();
//			ballNumber = ((ResultBTRClassic) result).getLevelStarNumber();
//		}
		this.loadTexture(ballSelected);
		for (int i = 0; i < shotNumber; i++) {
			if (i < shotTaken) {
				setImage(imageset1);
				GlobalVars.ge.getRenderer().render(image, x + i * width + 10,
						y, width, height, width / 2f, height / 2f, 0);
			} else {
				setImage(imageset2);
				TextureRegion imageAfterShot;
				GlobalVars.ge.getRenderer().render(image, x + i * width + 10,
						y, width, height, width / 2f, height / 2f, 0);
			}
		}
		/*
		 * GlobalVars.ge.getRenderer().getBatch().setColor(1f,1f,1,0.9f);
		 * GlobalVars.ge.getRenderer().render(image, x-width/2f, y - height/2f,
		 * width, height, width/2f, height/2f, this.rotateAngle);
		 * GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
		 */
	}



	public ScoreShot(float x, float y, float width, float height, int zIndex, int ballSelected) {
		super(x, y, width, height, zIndex);
//		if(Constants.levelSelected==1)
//		{
			if (((BounceTest) GlobalVars.ge.getCurrentStage()).result instanceof ResultBounce)
			this.result = (ResultBounce) ((BounceTest) GlobalVars.ge
					.getCurrentStage()).result;
			this.ballSelected = ballSelected;
//		}
//		else if(Constants.levelSelected==2)
//		{
////			GlobalVars.ge.setCurrentStage(new BounceTestLevel2());
////			if (((BounceTestLevel2) GlobalVars.ge.getCurrentStage()).result instanceof ResultBounce)
//			this.result = (ResultBounce) ((BounceTest) GlobalVars.ge
//					.getCurrentStage()).result;
//		}
////		else if (((BounceTest) GlobalVars.ge.getCurrentStage()).result instanceof ResultBTRTime)
//			this.result = (ResultBTRTime) ((BounceTest) GlobalVars.ge
//					.getCurrentStage()).result;
//		else if (((BounceTest) GlobalVars.ge.getCurrentStage()).result instanceof ResultBTRClassic)
//			this.result = (ResultBTRClassic) ((BounceTest) GlobalVars.ge
//					.getCurrentStage()).result;
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_SHOT_BALL_1);
		assetPack.addTexture(AssetConstants.IMG_AFTER_SHOT_BALL_1);
		assetPack.addTexture(AssetConstants.IMG_SHOT_BALL_2);
//		assetPack.addTexture(AssetConstants.IMG_AFTER_SHOT_BALL_2);
		assetPack.addTexture(AssetConstants.IMG_SHOT_BALL_3);
//		assetPack.addTexture(AssetConstants.IMG_AFTER_SHOT_BALL_3);
	}

	@Override
	public void init() {
//		setImage(Helper.getImageFromAssets(AssetConstants.IMG_SHOT_BALL));
//		setImage(Helper.getImageFromAssets(AssetConstants.IMG_AFTER_SHOT_BALL));

	}
}
