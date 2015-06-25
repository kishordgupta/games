package com.rhymes.game.entity.elements.nonphysical;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.elements.nonphysical.Player;
import com.rhymes.game.entity.elements.nonphysical.PlayerAnimation;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Player1 extends Player {

	public Player1() {
		super();
	}

	public Player1(float x, float y, float width, float height,
			float rotateAngle, int zIndex) {
		super(x, y, width, height, rotateAngle, zIndex);
	}

	public Player1(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		super.getAssets(assetPack);
	}

	@Override
	public void init() {
		TextureRegion[] imgNormal = new TextureRegion[2];
		TextureRegion[] imgThrow = new TextureRegion[2];
		TextureRegion[] imgEnd = new TextureRegion[2];
		// TODO Auto-generated method stub
		// ((BounceTest)GlobalVars.ge.getCurrentStage()).getLevelNumber();
		// if(playerNo == 1){
		
		imgNormal[0] = Helper
				.getImageFromAssets(AssetConstants.IMG_PLAYER1_NORMAL1);
		imgNormal[1] = Helper
				.getImageFromAssets(AssetConstants.IMG_PLAYER1_NORMAL2);
		// imgNormal[2] =
		// Helper.getImageFromAssets(AssetConstants.IMG_PLAYER_NORMAL1);
		imgThrow[0] = Helper
				.getImageFromAssets(AssetConstants.IMG_PLAYER1_THROW1);
		imgThrow[1] = Helper
				.getImageFromAssets(AssetConstants.IMG_PLAYER1_THROW2);
		imgEnd[0] = Helper
				.getImageFromAssets(AssetConstants.IMG_PLAYER1_END1);
		imgEnd[1] = Helper
				.getImageFromAssets(AssetConstants.IMG_PLAYER1_END2);
		
		this.setImages(imgNormal);
		this.playerAnimation = new PlayerAnimation(this, ctlFlag, images);
		playerAnimation.init();

		aniLoopNormal = new AniLoop(this, imgNormal, false);
		aniLoopNormal.init();
		aniLoopThrow = new AniLoop(this, imgThrow, false);
		aniLoopThrow.init();
		aniLoopEnd = new AniLoop(this, imgEnd, false);
		aniLoopEnd.init();
	}
}
