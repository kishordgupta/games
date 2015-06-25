package com.rhymes.game.entity.elements.burntherope;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.path.Path;
import com.rhymes.game.entity.elements.path.PathNode;
import com.rhymes.game.entity.elements.path.PathSet;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.game.entity.elements.unsorted.BPath;
import com.rhymes.game.entity.states.StateSimulateFire;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.game.stage.levels.XLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.states.StateDefault;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class PlanePathSet extends PathSet implements InteractionTouchCallbacks {
	private boolean started;
	public PlanePathSet(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
//		paths.add(new Path(60));
//		paths.add(new Path(20));
//		paths.add(new Path(30));
//		paths.add(new Path(15));
//		paths.add(new Path(20));
//		paths.add(new Path(20));
//		paths.add(new Path(30));
//		paths.add(new Path(15));

	}
	

	@Override
	public void getAssets(AssetPack assetPack) {
		Helper.println("getAssets: " + this);
		assetPack.addTexture(AssetConstants.IMG_ROAD_NORMAL);
	}
	
	@Override
	public void init() {
		started = false;
		this.state = new StateDefault(this, AssetConstants.IMG_ROAD_NORMAL);
		this.state.init();
//		GlobalVars.ge.getCurrentStage().subscribeToInteraction(this,
//				InteractionTouch.class);
	}
	
	@Override
	public void onEvent(Point hitPoint) {
//		Helper.println("hit to lit fire!   hitpoint: " + hitPoint.toString());
		if(started)
			return;
		
//		PathNode r;
//		hitPoint.y = (int) (Gdx.graphics.getHeight() - hitPoint.y);
//		Helper.printKeyVal("New Event, Hit Point: ", "" + hitPoint);
		for (Path path : paths) {
			if(((BPath)path).getColor() != Boat.PLANE_NORMAL)
				continue;
//			r = path.getPathNode(hitPoint, 5);
			TraversePointInfo info = path.startTraverse(hitPoint, 10);
//			TraversePointInfo info = path.startTraverse();
			if (info != null) {
				((XLevel)GlobalVars.ge.getCurrentStage()).bringPlane(info);
				started = true;
				return;
//				r = info.getPathNode();
//				Helper.printKeyVal("RopeNode point pathnode:", info.getPathNode().toString());
//				Helper.printKeyVal("RopeNode point got hit:", info.getTraverseLocation().toString());
//				try {
////					Helper.println("Next node: " + r.getNextNode().toString());
////					Helper.println("Prev node: " + r.getPrevNode().toString());
//				} catch (Exception e) {
//				}
//				return;
			}
		}
//		GlobalVars.ge.getCurrentStage().unSubscribeToInteraction(this, InteractionTouch.class);
	}


	public void reset() {
		for(int i = 0; i < paths.size; i++){
			paths.get(i).reset();
		}
	}


	public float getTotalDistance() {
		float total = 0;
		for(int i =0 ; i < paths.size; i++)
		{
			total += paths.get(i).getTotalDistance(false);
		}
		return total;
	}
}
