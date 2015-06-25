package com.rhymes.game.entity.elements.burntherope;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.animations.common.AniScroll;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.animations.common.AniRotate;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.game.entity.states.StateElementNormal;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.interactions.rangeTraversal.ICRangePathTraversal;
import com.rhymes.game.interactions.rangeTraversal.InfoRangeTraversal;
import com.rhymes.game.stage.levels.XLevel;
import com.rhymes.game.stage.result.ResultBTRMAP;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Birds extends ElementBase{
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
		aniBirds.step(stepTime);
	}

	private static float DEFAULT_HEIGHT = 30;
	private static float DEFAULT_WIDTH = 35;
	private Boolean collided = false;
	
	
	@Override
	public void render() {
		width = image.getRegionWidth();
		height = image.getRegionHeight();
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,0.7f);
		GlobalVars.ge.getRenderer().render(image, x-width/2f, y - height/2f, width, height, width/2f, height/2f, this.rotateAngle+90/*rotateAngle-270*/, 0.6f, 0.6f);
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
	}

	public Birds(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

	@Override
	public void getAssets(AssetPack assetPack) {
	}

	private AnimationBase aniBirds;
	@Override
	public void init() {
		TextureRegion[] images = new TextureRegion[6];
		for(int i =2 ; i <= 7; i++)
		{
			images[i-2] = Helper.getImageFromAssets(AssetConstants.IMG_BIRDS_FOLDER_PATH + i + ".png");
		}
		
		this.state = new StateElementNormal(this, new AniLoop(this, images, true));
		this.state.init();
		
		aniBirds = new AniScroll(this);
		aniBirds.init();
	}

}
