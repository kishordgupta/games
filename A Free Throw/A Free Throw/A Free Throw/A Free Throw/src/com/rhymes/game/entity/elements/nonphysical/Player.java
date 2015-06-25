package com.rhymes.game.entity.elements.nonphysical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.helpers.Helper;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.elements.nonphysical.PlayerAnimation;
import com.rhymes.game.stage.levels.BounceTest;

public class Player extends ElementBase {
	
	protected PlayerAnimation playerAnimation;
	protected static int ctlFlag = 0;
	protected long stepTime;
	protected String imagePath;
	public TextureRegion[] images = new TextureRegion[6];
	
	public long getStepTime() {
		return stepTime;
	}

	public void setStepTime(long stepTime) {
		this.stepTime = stepTime;
	}

	public Player() {
		// TODO Auto-generated constructor stub
	}

	public Player(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		ctlFlag = 0;
	}
	public Player(float x, float y, float width, float height,float rotateAngle, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rotateAngle = rotateAngle;
		ctlFlag = 0;
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub
		//getting asset for player2
		assetPack.addTexture(AssetConstants.IMG_PLAYER1_NORMAL1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER1_NORMAL2);
		assetPack.addTexture(AssetConstants.IMG_PLAYER1_THROW1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER1_THROW2);
		assetPack.addTexture(AssetConstants.IMG_PLAYER1_END1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER1_END2);
		///getting asset for player2
		assetPack.addTexture(AssetConstants.IMG_PLAYER2_NORMAL1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER2_NORMAL2);
		assetPack.addTexture(AssetConstants.IMG_PLAYER2_THROW1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER2_THROW2);
		assetPack.addTexture(AssetConstants.IMG_PLAYER2_END1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER2_END2);
		//getting asset for player3
		assetPack.addTexture(AssetConstants.IMG_PLAYER3_NORMAL1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER3_NORMAL2);
		assetPack.addTexture(AssetConstants.IMG_PLAYER3_THROW1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER3_THROW2);
		assetPack.addTexture(AssetConstants.IMG_PLAYER3_END1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER3_END2);
		///getting asset for player4
		assetPack.addTexture(AssetConstants.IMG_PLAYER4_NORMAL1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER4_NORMAL2);
		assetPack.addTexture(AssetConstants.IMG_PLAYER4_THROW1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER4_THROW2);
		assetPack.addTexture(AssetConstants.IMG_PLAYER4_END1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER4_END2);
		//getting asset for player5
		assetPack.addTexture(AssetConstants.IMG_PLAYER5_NORMAL1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER5_NORMAL2);
		assetPack.addTexture(AssetConstants.IMG_PLAYER5_THROW1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER5_THROW2);
		assetPack.addTexture(AssetConstants.IMG_PLAYER5_END1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER5_END2);
		///getting asset for player6
		assetPack.addTexture(AssetConstants.IMG_PLAYER6_NORMAL1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER6_NORMAL2);
		assetPack.addTexture(AssetConstants.IMG_PLAYER6_THROW1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER6_THROW2);
		assetPack.addTexture(AssetConstants.IMG_PLAYER6_END1);
		assetPack.addTexture(AssetConstants.IMG_PLAYER6_END2);
		
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();
//		GlobalVars.ge.getRenderer().render(this.image, x, y, width, height, 0, , rotateAngle, 1, 1);
			
	}
	@Override
	public void step(long stepTime) {
		if(ctlFlag  == 0)
		{
			aniLoopNormal.step(stepTime);
			if(aniLoopNormal.isFinished()){
				((BounceTest)GlobalVars.ge.getCurrentStage()).getBall().startThrow = false;
//				this.ctlFlag = 1;
			}
		}
		else if(ctlFlag == 1)
		{
//			playerAnimation.step(stepTime);
			
//			StartupInfo.sound_handler.playSound(SoundHandler.soundType.SOUND_CLICK);
			aniLoopThrow.step(stepTime);
			if(aniLoopThrow.isFinished()){
				((BounceTest)GlobalVars.ge.getCurrentStage()).getBall().startThrow = true;
				this.ctlFlag = 2;
//				StartupInfo.sound_handler.stopSound();
			}
			
				
		}
		else if(ctlFlag == 2)
		{
//			playerAnimation.step(stepTime);
			aniLoopEnd.step(stepTime);
			if(aniLoopEnd.isFinished()){
				((BounceTest)GlobalVars.ge.getCurrentStage()).getBall().startThrow = false;
				this.ctlFlag = 0;
			}
		}

	}
	protected AniLoop aniLoopNormal;
	protected AniLoop aniLoopThrow;
	protected AniLoop aniLoopEnd;
	@Override
	public void init() {
//		int playerNo;
		TextureRegion[] imgNormal = new TextureRegion[2];
		TextureRegion[] imgThrow = new TextureRegion[2];
		TextureRegion[] imgEnd = new TextureRegion[2];
		this.setImages(imgNormal);
		this.playerAnimation = new PlayerAnimation(this,ctlFlag,images);
		playerAnimation.init();
		aniLoopNormal = new AniLoop(this, imgNormal, false);
		aniLoopNormal.init();
		aniLoopThrow = new AniLoop(this, imgThrow, false);
		aniLoopThrow.init();
		aniLoopEnd = new AniLoop(this, imgEnd, false);
		aniLoopEnd.init();
		ctlFlag = 0;
	}
	public TextureRegion[] getImages() {
		return images;
	}
	public void setImages(TextureRegion[] images) {

		this.images = images;
	}
	public int getCtlFlag() {
		return ctlFlag;
	}
	public static void setCtlFlag(int ctlFlag) {
		Player.ctlFlag = ctlFlag;
	}
	public void setImagesSet(int playerNo) {
		// TODO Auto-generated method stub
	}
}
