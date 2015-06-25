package com.rhymes.game.entity.elements.pinball;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.pinball.ICLeftRight;
import com.rhymes.game.pinball.ILeftRight;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class BottomBar extends ElementBase implements ICLeftRight{

	@Override
	public void render() {
//		super.render();
		
//		GlobalVars.ge.getScreen().getBatch().setColor(1f, 0f, 0f, 1f);
//		GlobalVars.ge.getRenderer().render(this.image, x, y, width, height, width/2f, height/2f, 0, 1, 1);
//		Helper.printKeyVal("image widht: ", image.getRegionWidth());
//		Helper.printKeyVal("image height: ", image.getRegionHeight());
		GlobalVars.ge.getRenderer().render(this.image, 160, 240, image.getRegionWidth(), image.getRegionHeight(), 0,0, 270, 1, 1);
//		GlobalVars.ge.getScreen().getBatch().setColor(1, 1f, 1f, 1f);
	}

	float dx = 1;
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
		
	/*	this.x += dx;
	
		if(this.x >= Gdx.graphics.getWidth() || this.x <= 0)
		{
			this.dx *=-1;
		}*/
	}

	public BottomBar() {
		super();
	}

	public BottomBar(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BOTTOM_BAR);
	}

	@Override
	public void init() {
		this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_BOTTOM_BAR));
		this.setRotateAngle(180);
//		this.setHeight(100);
		this.setOriginX(this.width / 2f);
		this.setOriginY(this.height / 2f);
	}

	@Override
	public void onEvent(int ctl) {
		if(ctl == ILeftRight.CTL_LEFT){
			if(this.x<=0)
				return;
			this.x-=2;
		}
		else if(ctl == ILeftRight.CTL_RIGHT){
			if(this.x>= Gdx.graphics.getWidth()-width)
				return;
			this.x+=2;
		}
	}
}
