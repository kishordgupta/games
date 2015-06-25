package com.rhymes.game.entity.elements.burntherope;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.path.Path;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.game.entity.elements.unsorted.BPath;
import com.rhymes.game.interactions.ICPathTraversal;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.stage.levels.XLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Boat extends ElementBase implements ICPathTraversal, ICSingleCollisionCallbacks{
	private static float DEFAULT_HEIGHT = 20;
	private static float DEFAULT_WIDTH = 30;
	private TraversePointInfo traverseInfo = null;
	
	private int pathTraversalDirection = Path.METHOD_RIGHT;
	
	public static final int PLANE_RED = 0;
	public static final int PLANE_GREEN = 1;
	public static final int PLANE_NORMAL = 2;
	
	private int planeType = PLANE_NORMAL;
	public static float handSetAngle = 0f;
	private TextureRegion sailIMage;
	
	private boolean active = true;
	
	public Transformer lastCollidedTransformer = null;
	public Joint lastCollidedJoint = null;
	
	public void printInfo()
	{
		Helper.printKeyVal("\n\nBoat info", "");
		Helper.printKeyVal("Plane Type: ", this.planeType);
		Helper.printKeyVal("Method: ", this.pathTraversalDirection);
		Helper.printKeyVal("Path id: ", ((XLevel)GlobalVars.ge.getCurrentStage()).getPlanePathSet().getPaths().indexOf(this.traverseInfo.getPath(), true));
		Helper.printKeyVal("Last traversed distance: ", this.traverseInfo.getLastTraversedDistance());
		Helper.printKeyVal("Active: ", this.active + "");
		
	}
	
	@Override
	public void render() {
//		x = y = 200;
//		rotateAngle = 0;
//		handSetAngle = 0;
		if(pathTraversalDirection == Path.METHOD_LEFT)
			GlobalVars.ge.getRenderer().render(image, x-width/2f, y-height/2f, width, height, width/2f, height/2f, rotateAngle-90, 1.5f, 0.8f);
		else
			GlobalVars.ge.getRenderer().render(image, x-width/2f, y-height/2f, width, height, width/2f, height/2f, rotateAngle-270, 1.5f, 0.8f);

		// setting sail image according to boat color
		if(this.planeType == PLANE_GREEN)
			GlobalVars.ge.getRenderer().getBatch().setColor(0,0.8f,0.91f,1f);
		else if(this.planeType == PLANE_RED)
			GlobalVars.ge.getRenderer().getBatch().setColor(0.91f,0.3f,0.9f,1f);
		
		GlobalVars.ge.getRenderer().render(sailIMage, x, y , width, height, 0, 0, handSetAngle, 1.2f, 0.7f);
		
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
		
//		GlobalVars.ge.getRenderer().render(image, x-width/2f, y, width, height, 0, 0, 0);
	}

	public Boat(TraversePointInfo info) {
		super(info.getTraverseLocation().x, info.getTraverseLocation().y, DEFAULT_WIDTH, DEFAULT_HEIGHT, 1);
		traverseInfo = info;
		this.rotateAngle = info.getPathNode().getRightAngle();
		init();
		Helper.printKeyVal("Boat created: ", getId());
	}
	
	public Boat(TraversePointInfo info, float width, float height, int zIndex) {
		super(info.getTraverseLocation().x, info.getTraverseLocation().y, width, height, zIndex);
		traverseInfo = info;
		this.rotateAngle = info.getPathNode().getRightAngle();
	}
	
	public Boat(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		traverseInfo = null;
	}

	public Boat()
	{
		super(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT, 1);
		init();
	}
	@Override
	public void getAssets(AssetPack assetPack) {
		
	}

	@Override
	public void init() {
		stage = (XLevel)GlobalVars.ge.getCurrentStage();
		lastDistanceTraversed = 0;
		sailIMage = Helper.getImageFromAssets(AssetConstants.IMG_SAIL);
//		setImage(Helper.getImageFromAssets(AssetConstants.IMG_PLANE));
//		setImage(Helper.getImageFromAssets(AssetConstants.IMG_PLANE_RED_1));
//		this.state = new StatePlaneNormal(this);
//		setPlaneType(PLANE_GREEN);
	}
	

	@Override
	public void setTraverseInfo(TraversePointInfo traverseInfo) {
		this.traverseInfo = traverseInfo;
	}
	
	@Override
	public TraversePointInfo getTraverseInfo() {
		return traverseInfo;
	}

	
	public boolean check()
	{
		if(this.pathTraversalDirection == Path.METHOD_LEFT)
		{
			if(traverseInfo.getPathNode() == traverseInfo.getPath().getNodes().getFirst())
				return false;
		}
		else if(this.pathTraversalDirection == Path.METHOD_RIGHT)
		{
			if(traverseInfo.getPathNode() == traverseInfo.getPath().getNodes().getLast())
				return false;
		}
		return true;
	}
	
	
	
	@Override
	public void onCollision(ICSingleCollisionCallbacks collider) {
		if(collider instanceof Pearl){
			
		}
		else if(collider instanceof Destroyer){
			die();
		}
		else 
			collidedElements.add((ElementBase) collider);
	}

	@Override
	public boolean isCollided() {
		return false;
	}

	@Override
	public int getPathTraversalDirection() {
		return pathTraversalDirection;
	}

	@Override
	public void setPathTraversalDirection(int pathTraversalDirection) {
		this.pathTraversalDirection = pathTraversalDirection;
	}

	private float lastDistanceTraversed = 0;
	
	StageBase stage;
	private long damage = 0 ;
	private long MaxDamage = 2000;
	
	public int c = 0; 
	@Override
	public void actPathTraversal() {
		lastDistanceTraversed += traverseInfo.getLastTraversedDistance();
				
		if(this.planeType != ((BPath)(this.traverseInfo.getPath())).getColor() && ((BPath)traverseInfo.getPath()).getColor() != PLANE_NORMAL)
		{
			this.die();
			Helper.println("Die 1");
		}
		else{ 
			if(this.pathTraversalDirection == Path.METHOD_LEFT)
			{
				if(traverseInfo.getPathNode() == traverseInfo.getPath().getNodes().getFirst()){
					this.die();
					Helper.println("Die 2");
				}
				else if(!traverseInfo.getPathNode().getPrevNode().isActive()){
					this.die();
					Helper.println("Die 3");
				}
			}
			else if(this.pathTraversalDirection == Path.METHOD_RIGHT)
			{
				if(traverseInfo.getPathNode() == traverseInfo.getPath().getNodes().getLast()){
					this.die();
					Helper.println("Die 4");
				}
				else if(!traverseInfo.getPathNode().getNextNode().isActive()){
					this.die();
					Helper.println("Die 5");
				}
			}
		}
		
//		Helper.println("Last distance traversed: " + this.traverseInfo.getLastTraversedDistance());
//		Helper.println("Total distance traversed: " + this.lastDistanceTraversed);
		
//		Helper.printKeyVal("Damage: ", damage);
		if(traverseInfo.getLastTraversedDistance() == 0){
			damage += GlobalVars.ge.stepTime;
//			Helper.printKeyVal("Damage: ", damage);
			if(damage > MaxDamage)
			{
				die();
				return;
			}
		}
		else
			damage = 0;
	}

	public void setPlaneType(int planeType) {
		Helper.println("Type: " + planeType);
		this.planeType = planeType;
		setImage(Helper.getImageFromAssets(AssetConstants.IMG_BOAT));
	}

	public int getPlaneType() {
		return planeType;
	}
	
	
	public void die()
	{
		Helper.printKeyVal("Boat destroyed: ", getId());
		((XLevel)GlobalVars.ge.getCurrentStage()).removePlane(this);
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}
	
	public void addCollider(ICSingleCollisionCallbacks collider)
	{
		this.collidedElements.add((ElementBase) collider);
		Helper.printKeyVal("\n\nBoat id: " + this.getId() + "Collided element size: ", collidedElements.size);
	}
	
	public Array<ElementBase> collidedElements = new Array<ElementBase>();
	public boolean checkCollision(ICSingleCollisionCallbacks collider)
	{
//		if(collider instanceof Joint)
//			Helper.println("Boat: " + this.getId() + " Checking if already collided: " + ((ElementBase)collider).getId() + collider.getClass());
		if(!this.isActive())
			return false;
		if(collider instanceof Pearl)
			return true;
		for(int i = 0; i < collidedElements.size; i++){
			if(collidedElements.get(i).equals(((ElementBase)collider))){
//				Helper.println("ALready collided: " + ((ElementBase)collider).getId());
				return false;
			}
		}
		return true;
	}
}