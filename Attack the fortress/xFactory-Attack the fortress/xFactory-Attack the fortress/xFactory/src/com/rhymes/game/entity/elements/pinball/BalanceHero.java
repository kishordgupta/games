package com.rhymes.game.entity.elements.pinball;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.animations.AniPinBall;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.animations.common.AniPopOut;
import com.rhymes.game.entity.animations.common.AniRotate;
import com.rhymes.game.pinball.ICLeftRight;
import com.rhymes.game.pinball.ILeftRight;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class BalanceHero extends ElementBase implements ICLeftRight{
	
	AniPinBall aniPinBall ;
	AniRotate aniRotate;
	AniPopOut aniPopOut;

	float z = 1;
	@Override
	public void render() {
//		super.render();
		GlobalVars.ge.getRenderer().render(image, x, y , width, height, width/2, height/2, 
				rotateAngle, y/20/1.07f,y/20/1.07f );
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
		if(this.y > Gdx.graphics.getHeight())
			this.y = 0;

		this.y = this.y + 0.05f;
	}

	public BalanceHero() {
		super();
	}

	public BalanceHero(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BOTTOM_BAR);
	}

	@Override
	public void init() {
		this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_BOTTOM_BAR));
		this.rotateAngle = 0;
	}

	@Override
	public void onEvent(int ctl) {
		if(ctl == ILeftRight.CTL_RIGHT)
		{
			this.rotateAngle--;
			if(this.rotateAngle<-20)
				rotateAngle = -20;
		}
		else if(ctl == ILeftRight.CTL_LEFT)
		{
			this.rotateAngle++;
			if(this.rotateAngle>20)
				rotateAngle = 20;
		}
	}
}
