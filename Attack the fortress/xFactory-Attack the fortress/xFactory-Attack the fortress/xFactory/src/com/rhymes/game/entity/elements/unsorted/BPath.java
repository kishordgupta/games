package com.rhymes.game.entity.elements.unsorted;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.path.Path;
import com.rhymes.game.entity.elements.path.PathNode;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.game.entity.elements.piku.Boat;
import com.rhymes.game.entity.elements.piku.Pearl;
import com.rhymes.game.entity.elements.piku.PlanePathSet;
import com.rhymes.game.interactions.ISingleCollision;
import com.rhymes.game.stage.levels.XLevel;
import com.rhymes.game.stage.result.ResultBTRMAP;
import com.rhymes.game.stage.result.ResultBTRTime;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class BPath extends Path {

	private float pearlDistance = 5; 
	private Pearl pearl;
	private int nodesPassed = 0;
	public void addPearls(PlanePathSet pathSet) {
		for(Path p: pathSet.getPaths()){
			for(PathNode pn:p.getNodes())
			if(nodesPassed++ > pearlDistance)
			{
				nodesPassed = 0;
				pearl = new Pearl( pn.getLocation());
				((XLevel)GlobalVars.ge.getCurrentStage()).addPearl(pearl);
			}
		}
	}

	private int color = Boat.PLANE_NORMAL;

	public BPath(int i) {
		super(i);
	}
	public BPath()
	{
		super();
	}

	public void setColor(int color) {
		if(color == Boat.PLANE_RED){
			setImage(Helper.getImageFromAssets(AssetConstants.IMG_ROAD_RED));
//			setImage(Helper.getImageFromAssets(AssetConstants.IMG_BKG_BLUE_TRANSITION));
		}
		else if(color == Boat.PLANE_GREEN){
			setImage(Helper.getImageFromAssets(AssetConstants.IMG_ROAD_GREEN));
		}
		else if(color == Boat.PLANE_NORMAL){
			setImage(Helper.getImageFromAssets(AssetConstants.IMG_ROAD_NORMAL));
		}
		
		this.color = color;
		
	}

	public int getColor() {
		return color;
	}
}
