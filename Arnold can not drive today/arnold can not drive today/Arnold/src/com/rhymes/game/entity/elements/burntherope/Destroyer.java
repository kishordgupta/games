package com.rhymes.game.entity.elements.burntherope;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.animations.common.AniRotate;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.game.entity.states.StateElementNormal;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.interactions.rangeTraversal.ICRangePathTraversal;
import com.rhymes.game.interactions.rangeTraversal.InfoRangeTraversal;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Destroyer extends ElementBase implements ICRangePathTraversal, ICSingleCollisionCallbacks{
	private static float DEFAULT_HEIGHT = 20;
	private static float DEFAULT_WIDTH = 25;
	private InfoRangeTraversal traverseInfo = null;
	private Boolean collided = false;
	
	
	@Override
	public void render() {
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,0.8f);
		GlobalVars.ge.getRenderer().render(image, x-width/2f, y - height/2f, width, height, width/2f, height/2f, this.rotateAngle+90/*rotateAngle-270*/);
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
	}

	public Destroyer(InfoRangeTraversal info) {
		super(info.traverseInfo.getTraverseLocation().x, info.traverseInfo.getTraverseLocation().y, DEFAULT_WIDTH, DEFAULT_HEIGHT, 1);
		traverseInfo = info;
	}
	
	public Destroyer(InfoRangeTraversal info, float width, float height, int zIndex) {
		super(info.traverseInfo.getTraverseLocation().x, info.traverseInfo.getTraverseLocation().y, width, height, zIndex);
		traverseInfo = info;
	}
	
	public Destroyer(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		traverseInfo = null;
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_DESTROYER);
	}

	@Override
	public void init() {
		setImage(Helper.getImageFromAssets(AssetConstants.IMG_DESTROYER));
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
		Helper.println("Transformer collided with: " + collider.getClass());			
		GlobalVars.ge.getCurrentStage().postDestroyed(this);
		this.collided = true;
	}

	@Override
	public boolean isCollided() {
		return collided;
	}

	@Override
	public void actRangeTraversal() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;
	}
	private boolean active = true;
}
