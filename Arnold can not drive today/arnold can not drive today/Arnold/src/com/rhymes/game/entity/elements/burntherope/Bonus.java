package com.rhymes.game.entity.elements.burntherope;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.animations.common.AniPopOut;
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

public class Bonus extends ElementBase{
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
		aniPop.step(stepTime);
	}

	private static float DEFAULT_HEIGHT = 30;
	private static float DEFAULT_WIDTH = 35;
	
	
	@Override
	public void render() {
		if(aniPop.isFinished())
			GlobalVars.ge.getCurrentStage().postDestroyed(this);
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
		GlobalVars.ge.getRenderer().render(image, x-width/2f, y - height/2f, width, height, width/2f, height/2f, this.rotateAngle+90/*rotateAngle-270*/, aniPop.getCurrentScale(), aniPop.getCurrentScale());
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
	}

	public Bonus(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
	
	public Bonus(float x, float y, String imagePath) {
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, 1);
		image = Helper.getImageFromAssets(imagePath);
		init();
	}

	@Override
	public void getAssets(AssetPack assetPack) {
	}

	private AniPopOut aniPop;
	@Override
	public void init() {
		aniPop = new AniPopOut(this, 60, 0.25f, 1.5f);
		aniPop.init();
	}
}
