package com.rhymes.game.entity.elements.piku;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.animations.common.AniRotateRange;
import com.rhymes.game.entity.elements.path.Path;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.interactions.rangeTraversal.ICRangePathTraversal;
import com.rhymes.game.interactions.rangeTraversal.InfoRangeTraversal;
import com.rhymes.game.stage.levels.XLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler.soundType;
import com.rhymes.helpers.Helper;

public class Transporter extends ElementBase implements ICRangePathTraversal, ICSingleCollisionCallbacks{
	private static float DEFAULT_HEIGHT = 40;
	private static float DEFAULT_WIDTH = 40;
	private InfoRangeTraversal traverseInfo = null;
	private Boolean collided = false;
	
	private TraversePointInfo destinationInfo = null; 
	
	private Boat boat;
	
	private float currentDistance = 0;
	private float totalDistance = 0;
	Point targetPoint = new Point();
	private boolean movingDone = false;
	
	public void updateTargetPoint(float stepDistance)
	{
		stepDistance = stepDistance+0.8f;
		this.getLocation().getPointAtDistance(this.getLocation(), this.destinationInfo.getTraverseLocation(),
				targetPoint, stepDistance);
		
		this.setLocation(targetPoint);
		this.boat.setLocation(targetPoint);
		
		currentDistance += stepDistance;
		if(currentDistance > totalDistance){
			movingDone = true;
			boat.setActive(true);
			TraversePointInfo tinfo = new TraversePointInfo();
			tinfo.initialize(destinationInfo);
			boat.setTraverseInfo(tinfo);
			
			tinfo = new TraversePointInfo();
			tinfo.initialize(destinationInfo);

			if(boat.getPathTraversalDirection() == Path.METHOD_RIGHT)
				tinfo.getPath().traverse(tinfo, 17, Path.METHOD_LEFT,false);
			else
				tinfo.getPath().traverse(tinfo, 17, Path.METHOD_RIGHT, false);
			
			((XLevel)GlobalVars.ge.getCurrentStage()).addTransportedPlane
			(tinfo, boat.getPathTraversalDirection() == Path.METHOD_LEFT? Path.METHOD_RIGHT : 
				Path.METHOD_LEFT, boat.getPlaneType());
			
			GlobalVars.ge.getCurrentStage().postDestroyed(this);
		}
	}
	
	
	AniRotateRange aniRotate = new AniRotateRange(this, 100, 3, -20, 20);
	
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
		aniRotate.step(stepTime);
		if(collided && !movingDone)
		{
			updateTargetPoint(1f);
		}
	}
	
	@Override
	public void render() {
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
		GlobalVars.ge.getRenderer().render(image, x, y-height/2f, width, height, 0, height/2f, rotateAngle+90/*rotateAngle-270*/);
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
	}

	public Transporter(InfoRangeTraversal info, TraversePointInfo destinationInfo) {
		super(info.traverseInfo.getTraverseLocation().x, info.traverseInfo.getTraverseLocation().y, DEFAULT_WIDTH, DEFAULT_HEIGHT, 1);
		traverseInfo = info;
		this.setDestinationInfo(destinationInfo); 
	}
	
	public Transporter(InfoRangeTraversal info, float width, float height, int zIndex, TraversePointInfo destinationInfo) {
		super(info.traverseInfo.getTraverseLocation().x, info.traverseInfo.getTraverseLocation().y, width, height, zIndex);
		traverseInfo = info;
		this.setDestinationInfo(destinationInfo);
	}
	
	public Transporter(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		traverseInfo = null;
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BRUSH_BLUE_3);
	}

	@Override
	public void init() {
		setImage(Helper.getImageFromAssets(AssetConstants.IMG_BRUSH_BLUE_3));
	}


	@Override
	public InfoRangeTraversal getRangeTraverseInfo() {
		return traverseInfo;
	}

	@Override
	public void setRangeTraverseInfo(InfoRangeTraversal traverseInfo) {
		this.traverseInfo = traverseInfo;
	}
	
	private Boolean moving = false;
	@Override
	public void onCollision(ICSingleCollisionCallbacks collider) {
		Helper.println("Transporter collided with: " + collider.getClass());	
		
		if(collider instanceof Boat)
		{
//			StartupInfo.sound_handler.playSound(soundType.SOUND_TRANSPORT);
			
			boat = (Boat)collider;
//			boat.die();
//			boat = ((XLevel)GlobalVars.ge.getCurrentStage()).addTransportedPlane(tinfo, boat.getPathTraversalDirection(), boat.getPlaneType());
			boat = ((XLevel)GlobalVars.ge.getCurrentStage()).addTransportedPlane(new Boat(), boat.getPathTraversalDirection(), boat.getPlaneType());;
			boat.setActive(false);
			
			
			
			this.collided = true;
			this.active = false;
			
			this.totalDistance = this.getLocation().distancePoint2Point(this.destinationInfo.getTraverseLocation());
			
			this.setSize(this.width * 1.5f, this.height * 1.5f);
		}
	}

	@Override
	public boolean isCollided() {
		return collided;
	}

	@Override
	public void onPathTraverseFinished() {
		// TODO Auto-generated method stub
		
	}

	public void setDestinationInfo(TraversePointInfo destinationInfo) {
		this.destinationInfo = destinationInfo;
	}

	public TraversePointInfo getDestinationInfo() {
		return destinationInfo;
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
