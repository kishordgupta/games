package com.rhymes.game.entity.elements.burntherope;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.animations.common.AniRotate;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.game.entity.states.StateElementNormal;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.interactions.rangeTraversal.ICRangePathTraversal;
import com.rhymes.game.interactions.rangeTraversal.InfoRangeTraversal;
import com.rhymes.game.stage.levels.XLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Joint extends ElementBase implements ICRangePathTraversal, ICSingleCollisionCallbacks{
	private static float DEFAULT_HEIGHT = 20;
	private static float DEFAULT_WIDTH = 20;
	private InfoRangeTraversal traverseInfo = null;
	private Boolean collided = false;	
	
	private TraversePointInfo destinationInfo = null;
	
	private Array<JointsInfo> jointsInfoArray; 
	
	@Override
	public void render() {
		GlobalVars.ge.getRenderer().getBatch().setColor(1f,1f,1f,1f);
		GlobalVars.ge.getRenderer().render(image, x-width/2f, y-width/2f, width, height, width/2f, height/2f, rotateAngle /*90*//*rotateAngle-270*/);
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
	}

	public Joint(InfoRangeTraversal info, Array<JointsInfo> jointsInfo) {
		super(info.traverseInfo.getTraverseLocation().x, info.traverseInfo.getTraverseLocation().y, DEFAULT_WIDTH, DEFAULT_HEIGHT, 1);
		traverseInfo = info;
		this.jointsInfoArray = jointsInfo;
	}
	
	public Joint(InfoRangeTraversal info, float width, float height, int zIndex, Array<JointsInfo> jointsInfo) {
		super(info.traverseInfo.getTraverseLocation().x, info.traverseInfo.getTraverseLocation().y, width, height, zIndex);
		traverseInfo = info;
		this.jointsInfoArray = jointsInfo;
	}
	
	public Joint(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		traverseInfo = null;
		this.jointsInfoArray = null;
	}

	@Override
	public void getAssets(AssetPack assetPack) {
	}

	@Override
	public void init() {
		setImage(Helper.getImageFromAssets(AssetConstants.IMG_JOINT));
		this.state = new StateElementNormal(this, new AniRotate(this));
		this.state.init();
	}


	@Override
	public InfoRangeTraversal getRangeTraverseInfo() {
		return traverseInfo;
	}

	@Override
	public void setRangeTraverseInfo(InfoRangeTraversal traverseInfo) {
		this.traverseInfo = traverseInfo;
	}

/*	@Override
	public void onCollision(ICSingleCollisionCallbacks collider) {
		Helper.println("Transformer collided with: " + collider.getClass());	
		
		if(collider instanceof Boat)
		{ 
			TraversePointInfo info;
			for(int i=0; i<this.jointsInfoArray.size; i++){
				info = jointsInfoArray.get(i);
				((XLevel)GlobalVars.ge.getCurrentStage()).addPlane(info);
			}
			
//			((ElementBase)collider).setLocation(this.destinationInfo.getTraverseLocation());
//			((Plane)collider).setTraverseInfo(destinationInfo);
		}
		
		GlobalVars.ge.getCurrentStage().postDestroyed(this);
		
		this.collided = true;
	}*/

	
	

	private static int steping = 0;
	@Override
	public void onCollision(ICSingleCollisionCallbacks collider) {
//		Helper.println("Joint collided with: " + collider.getClass());	
		
		if(collider instanceof Boat)
		{ 
			TraversePointInfo info;
			Helper.println("joints size: " + jointsInfoArray.size);
			for(int i=0; i<this.jointsInfoArray.size; i++){
				info = jointsInfoArray.get(i).info;
//				if(info.getPathNode() == ((Boat)collider).getTraverseInfo().getPathNode())
//				{
//					Helper.println("Ase nai");
//					continue;
//				}
//				Helper.println("AIse");	
				((XLevel)GlobalVars.ge.getCurrentStage()).addJointPlanes(info, ((Boat)collider), this);
				
			}
			((Boat)collider).die();
			
//			if(steping++ > 4)
//				System.exit(0);
		}
//		GlobalVars.ge.getCurrentStage().postDestroyed(this);
		
//		((XLevel)GlobalVars.ge.getCurrentStage()).printBoatsInfo();
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

	public void setJointsInfoArray(Array<JointsInfo> jointsInfoArray) {
		this.jointsInfoArray = jointsInfoArray;
	}

	public Array<JointsInfo> getJointsInfoArray() {
		return jointsInfoArray;
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
