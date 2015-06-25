package com.rhymes.game.entity.elements.burntherope;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.elements.path.Path;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.interactions.rangeTraversal.ICRangePathTraversal;
import com.rhymes.game.interactions.rangeTraversal.InfoRangeTraversal;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Transformer extends ElementBase implements ICRangePathTraversal, ICSingleCollisionCallbacks{
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
	
		if(this.aniLoop != null)
			this.aniLoop.step(stepTime);
	}

	private static float DEFAULT_HEIGHT = 30;
	private static float DEFAULT_WIDTH = 30;
	private InfoRangeTraversal traverseInfo = null;
	private Boolean collided = false;

	private int planeColor = Boat.PLANE_NORMAL;
	
	@Override
	public void render() {
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
		GlobalVars.ge.getRenderer().render(image, x-width/2f, y, width, height, width/2f, height/2f, this.rotateAngle+90/*rotateAngle-270*/);
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
	}

	public Transformer(InfoRangeTraversal info, int colorType) {
		super(info.traverseInfo.getTraverseLocation().x, info.traverseInfo.getTraverseLocation().y, DEFAULT_WIDTH, DEFAULT_HEIGHT, 1);
		traverseInfo = info;
		this.setPlaneColor(colorType); 
	}
	
	public Transformer(InfoRangeTraversal info, float width, float height, int zIndex, int colorType) {
		super(info.traverseInfo.getTraverseLocation().x, info.traverseInfo.getTraverseLocation().y, width, height, zIndex);
		traverseInfo = info;
		this.setPlaneColor(colorType);
	}
	
	public Transformer(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		traverseInfo = null;
	}

	@Override
	public void getAssets(AssetPack assetPack) {
	}

	private AniLoop aniLoop;
	@Override
	public void init() {
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
		
		if(collider instanceof Boat)
		{
			((Boat)collider).setPlaneType(this.planeColor);
		}
		
//		GlobalVars.ge.getCurrentStage().postDestroyed(this);
		
//		this.collided = true;
	}

	@Override
	public boolean isCollided() {
		return collided;
	}

	@Override
	public void actRangeTraversal() {
		// TODO Auto-generated method stub
		
	}
	private TextureRegion[] images;
	public void setPlaneColor(int planeColor) {
		this.planeColor = planeColor;
		if(this.planeColor == Boat.PLANE_GREEN)
		{
			images = new TextureRegion[7];
			for(int i =1 ; i <= 7; i++)
			{
				images[i-1] = Helper.getImageFromAssets(AssetConstants.IMG_TRANSFORMER_GREEN_PATH + i + ".png");
			}
			if(aniLoop == null)
				aniLoop = new AniLoop(this);
			aniLoop.setImages(images);
			aniLoop.init();
		}
		else if(this.planeColor == Boat.PLANE_RED)
		{
			images = new TextureRegion[7];
			for(int i =1 ; i <= 7; i++)
			{
				images[i-1] = Helper.getImageFromAssets(AssetConstants.IMG_TRANSFORMER_RED_PATH + i + ".png");
			}
			if(aniLoop == null)
				aniLoop = new AniLoop(this);
			aniLoop.setImages(images);
			aniLoop.init();
		}
		else if(this.planeColor == Boat.PLANE_NORMAL)
		{
			images = new TextureRegion[7];
			for(int i =1 ; i <= 7; i++)
			{
				images[i-1] = Helper.getImageFromAssets(AssetConstants.IMG_TRANSFORMER_BLACK_PATH + i + ".png");
			}
			if(aniLoop == null)
				aniLoop = new AniLoop(this);
			aniLoop.setImages(images);
			aniLoop.init();
		}
		
	}

	public int getPlaneColor() {
		return planeColor;
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
