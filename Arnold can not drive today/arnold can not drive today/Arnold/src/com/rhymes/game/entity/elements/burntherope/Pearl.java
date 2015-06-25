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
import com.rhymes.game.stage.result.ResultBTRMAP;
import com.rhymes.game.stage.result.ResultBTRTime;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Pearl extends ElementBase implements ICSingleCollisionCallbacks{
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
//		if(aniLoop != null)
//		this.aniLoop.step(stepTime);
	}


	private static float DEFAULT_HEIGHT = 20;
	private static float DEFAULT_WIDTH = 20;
	private Boolean collided = false;

	public void setCollided(Boolean collided) {
		this.collided = collided;
	}

	@Override
	public void render() {
		if(collided)
			return;
		GlobalVars.ge.getRenderer().getBatch().setColor(1f,1f,1,0.7f);
		GlobalVars.ge.getRenderer().render(image, x-5, y-5, 10, 10, 5, 5, -90);
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
	}

	
	AniLoop aniLoop;
	public void init_images()
	{
//		TextureRegion[] images = new TextureRegion[4];
//		for(int i =1 ; i <= 4; i++)
//		{
//			images[i-1] = Helper.getImageFromAssets(AssetConstants.IMG_MAGICAL_OBJECT_PATH + i + ".png");
//		}
//		aniLoop = new AniLoop(this, images, true);
//		aniLoop.init();
	}
	
	public Pearl(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		setImage(Helper.getImageFromAssets(AssetConstants.IMG_PEARL));
//		init_images();
	}

	public Pearl(Point point) {
		super(point.x, point.y, DEFAULT_WIDTH, DEFAULT_HEIGHT, 1);
		setImage(Helper.getImageFromAssets(AssetConstants.IMG_PEARL));
//		init_images();
	}
	
	@Override
	public void getAssets(AssetPack assetPack) {
	}

	
	
	@Override
	public void init() {
	}

	
	@Override
	public void onCollision(ICSingleCollisionCallbacks collider) {
		if(((XLevel)GlobalVars.ge.getCurrentStage()).result instanceof ResultBTRMAP){
			((ResultBTRMAP)((XLevel)GlobalVars.ge.getCurrentStage()).result).consumePearl();
		}
		else if(((XLevel)GlobalVars.ge.getCurrentStage()).result instanceof ResultBTRTime){
			((ResultBTRTime)((XLevel)GlobalVars.ge.getCurrentStage()).result).consumePearl();
		}
		
		((XLevel)GlobalVars.ge.getCurrentStage()).brintBonus(this.x, this.y, XLevel.BONUS_5);
		this.collided = true;		
	}

	@Override
	public boolean isCollided() {
		return collided;
	}
}
