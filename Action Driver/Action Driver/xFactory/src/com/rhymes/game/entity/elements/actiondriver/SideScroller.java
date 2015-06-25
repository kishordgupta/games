package com.rhymes.game.entity.elements.actiondriver;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Background;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class SideScroller extends ElementBase {
	
	class child{
		public child(float x, float y, float width, float height,
				TextureRegion image) {
			super();
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.image = image;
		}

		float x, y, width, height ;
		TextureRegion image;
		
		public float getRight(){
			return x+width;
		}
	}
	
	private Array<ElementBase> childElements;
	private Array<child> currentChilds;
	private Array<child> toBeRemoved;
	
	private float startX;
	private float startY;
	
	private int currentIndex = 0;
	private boolean repeat = true;
	
	@Override
	public void render() {
//		Helper.println("\n\nRendering:\n");
		for(int i = 0; i < currentChilds.size; i++){
//			Helper.println("child: " + i + " width: " + currentChilds.get(i).width);
//			Helper.println("child: " + i + " height: " + currentChilds.get(i).height);
			GlobalVars.ge.getRenderer().render(currentChilds.get(i).image,
					currentChilds.get(i).x, startY +currentChilds.get(i).y, 
					currentChilds.get(i).width, currentChilds.get(i).height
					);
		}
	}
	
	private void cleanUP()
	{
//		Helper.println("Comparing: Camera: " + Helper.getCameraX());
		for(int i = 0; i < currentChilds.size; i++)
		{
//			Helper.println("child: " + i + " right: " + currentChilds.get(i).getRight());
			if(currentChilds.get(i).getRight() <= Helper.getCameraX()){
				toBeRemoved.add(currentChilds.get(i));
//				Helper.println("Removing child: " + i );
			}
		}
		
		for(child e:toBeRemoved)
		{
			currentChilds.removeValue(e, true);
		}
		toBeRemoved.clear();
	}

	@Override
	public void step(long stepTime) {
		if(currentChilds.get(currentChilds.size-1).x < Helper.getCameraX() + Helper.getScreenWidth() )
			addNextChild(currentChilds.get(currentChilds.size -1).getRight());
	}


	private void addNextChild(float x) {
		child c = new child(x, childElements.get(currentIndex).getY(), childElements.get(currentIndex).getWidth(), childElements.get(currentIndex).getHeight(),
				childElements.get(currentIndex).getImage());
		currentChilds.add(c);
//		Helper.println("Added new child to the sidescroller, total size: " + currentChilds.size);
		
		currentIndex++;
		if(currentIndex >= childElements.size)
		{
			if(repeat)
				currentIndex = 0;
			else
				GlobalVars.ge.getCurrentStage().postDestroyed(this);
		}
	}

	@Override
	public void init() {
		this.image = Helper.getImageFromAssets(AssetConstants.IMG_ROAD_1);
		this.currentIndex = 0;
		currentChilds = new Array<child>();
		toBeRemoved = new Array<child>();
		
		addNextChild(startX);
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_ROAD_1);
	}

	public SideScroller(Array<ElementBase> childElements, float startX, float startY, boolean repeat) {
		this.startX = startX;
		this.startY = startY;
		this.repeat = repeat;
		this.childElements = childElements;
		this.zIndex = 0;
	}

}
