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
import com.rhymes.game.stage.result.ResultBTRClassic;
import com.rhymes.game.stage.result.ResultBTRMAP;
import com.rhymes.game.stage.result.ResultBTRTime;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Star extends ElementBase implements ICRangePathTraversal, ICSingleCollisionCallbacks{
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
		this.aniRotate.step(stepTime);
	}


	private static float DEFAULT_HEIGHT = 25;
	private static float DEFAULT_WIDTH = 25;
	private InfoRangeTraversal traverseInfo = null;
	private Boolean collided = false;
	
	
	@Override
	public void render() {
//		width = image.getRegionWidth();
//		height = image.getRegionHeight();
		GlobalVars.ge.getRenderer().getBatch().setColor(1f,1f,1,0.9f);
		GlobalVars.ge.getRenderer().render(image, x-width/2f, y - height/2f, width, height, width/2f, height/2f, this.rotateAngle+90);
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
	}

	public Star(InfoRangeTraversal info) {
		super(info.traverseInfo.getTraverseLocation().x, info.traverseInfo.getTraverseLocation().y, DEFAULT_WIDTH, DEFAULT_HEIGHT, 1);
		traverseInfo = info;
	}
	
	public Star(InfoRangeTraversal info, float width, float height, int zIndex) {
		super(info.traverseInfo.getTraverseLocation().x, info.traverseInfo.getTraverseLocation().y, width, height, zIndex);
		traverseInfo = info;
	}
	
	public Star(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		traverseInfo = null;
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_STAR);
	}

	
	private AniRotate aniRotate;
	@Override
	public void init() {
		setImage(Helper.getImageFromAssets(AssetConstants.IMG_STAR));
/*		TextureRegion[] images = new TextureRegion[1];
		for(int i =1 ; i < 2; i++)
		{
			images[i-1] = Helper.getImageFromAssets(AssetConstants.IMG_STAR_FOLDER_PATH + i + ".png");
		}
		this.state = new StateElementNormal(this, new AniRotate(this) new AniLoop(this, images, true));
		this.state.init();
*/		
		aniRotate = new AniRotate(this, 32, 7);
		aniRotate.init();
		
	}


	@Override
	public InfoRangeTraversal getRangeTraverseInfo() {
		return traverseInfo;
	}

	@Override
	public void setRangeTraverseInfo(InfoRangeTraversal traverseInfo) {
		this.traverseInfo = traverseInfo;
	}

	@Override
	public void onCollision(ICSingleCollisionCallbacks collider) {
		if(((XLevel)GlobalVars.ge.getCurrentStage()).result instanceof ResultBTRClassic){
			Helper.println("Collected Star");			
			((ResultBTRClassic)((XLevel)GlobalVars.ge.getCurrentStage()).result).consumeStar();
			GlobalVars.ge.getCurrentStage().postDestroyed(this);
			this.collided = true;
		}
		else if(((XLevel)GlobalVars.ge.getCurrentStage()).result instanceof ResultBTRTime){
			Helper.println("Collected Star");			
			((ResultBTRTime)((XLevel)GlobalVars.ge.getCurrentStage()).result).consumeStar();
			GlobalVars.ge.getCurrentStage().postDestroyed(this);
			this.collided = true;
		}
	}

	@Override
	public boolean isCollided() {
		return collided;
	}

	@Override
	public void actRangeTraversal() {
		
	}
	
	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;
	}
	private boolean active = true;
}
