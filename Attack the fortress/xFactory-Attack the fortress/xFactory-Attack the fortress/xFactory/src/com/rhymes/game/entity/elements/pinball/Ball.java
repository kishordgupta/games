package com.rhymes.game.entity.elements.pinball;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.animations.AniPinBall;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.animations.common.AniPopOut;
import com.rhymes.game.entity.animations.common.AniRotate;
import com.rhymes.game.stage.levels.TestLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Ball extends ElementBase {
	
	AniPinBall aniPinBall ;
	AniRotate aniRotate;
	AniPopOut aniPopOut;

	@Override
	public void render() {
		Helper.printKeyVal("Redering Ball ID: ", this.getId());
		GlobalVars.ge.getScreen().getBatch().setColor(0f, 1f, 0f, 1f);
		super.render();
		GlobalVars.ge.getScreen().getBatch().setColor(1f, 1f, 1f, 1f);
		
//		GlobalVars.ge.getScreen().getBatch().setColor(1f, 0f, 0f, 1f);
//		Helper.printKeyVal("Rotate: ", rotateAngle);
//		GlobalVars.ge.getRenderer().render(this.image, x, y, width, height, width/2f, height/2f, rotateAngle, 1, 1);
//		GlobalVars.ge.getRenderer().render(this.image, x+10, y+10, width, height, width/2f, height/2f, rotateAngle, 1, 1);
//		GlobalVars.ge.getRenderer().render(this.image, x-10, y-10, width, height, width/2f, height/2f, rotateAngle, 1, 1);
//		GlobalVars.ge.getRenderer().render(this.image, x-10, y+10, width, height, width/2f, height/2f, rotateAngle, 1, 1);
//		GlobalVars.ge.getRenderer().render(this.image, x-10, y+10, width, height, width/2f, height/2f, rotateAngle, 1, 1);
//		GlobalVars.ge.getScreen().getBatch().setColor(1, 1f, 1f, 1f);
	}

	
	
	
	float dx = 2, dy = 2;
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
		
//		aniPopOut.step(stepTime);
//		aniPinBall.step(stepTime);
//		aniRotate.step(stepTime);
		
	}

	public Ball() {
		super();
		((TestLevel)GlobalVars.ge.getCurrentStage()).physicalWorld.addBallBody(this);
	}

	public Ball(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		((TestLevel)GlobalVars.ge.getCurrentStage()).physicalWorld.addBallBody(this);
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BUTTON_CLOSE);
	}

	@Override
	public void init() {
		this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_BUTTON_CLOSE));
		
		this.setOriginX(this.width / 2f);
		this.setOriginY(this.height / 2f);
		
//		aniPinBall = new AniPinBall(this);
//		aniPinBall.init();
		
//		aniRotate = new AniRotate(this, 100, 20);
//		aniRotate.init();
//		
//		aniPopOut = new AniPopOut(this, 60, 0.25f, 1.5f);
//		aniPopOut.init();
	}
}
