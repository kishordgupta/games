package com.rhymes.game.entity.elements.path;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.states.StateSimulateFire;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.entity.states.StateDefault;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class PathSet extends ElementBase {

	public Array<Path> paths;

	public PathSet(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		paths = new Array<Path>();
	}

	@Override
	public void getAssets(AssetPack assetPack) {
//		assetPack.addTexture(AssetConstants.IMG_ROAD_NORMAL);
	}

	@Override
	public void init() {
//		this.state = new StateDefault(this);
		this.image = Helper.getImageFromAssets(AssetConstants.IMG_ROAD_NORMAL);
	}

	@Override
	public void render() {
//		if (state instanceof StateSimulateFire)
//			renderSimulation();
//		else {
			renderDefault();
//		}
	}

	private void renderSimulation() {
		for (Path rope : paths)
			rope.renderSimulation(Helper.getImageFromAssets(AssetConstants.IMG_BKG_BROWN), 
					Helper.getImageFromAssets(AssetConstants.IMG_BKG_RED));
	}
	int i;
	private void renderDefault() {
		for(i = 0; i < paths.size; i++)
			paths.get(i).renderPoints(getImage());
	}



	public Array<Path> getPaths() {
		return paths;
	}
	
	public void addPathSet(Array<Path> pathSet)
	{
		for(Path path: pathSet)
			paths.add(path);
	}

	public void addPath(Path path)
	{
		paths.add(path);
	}
	
	public void removePath(Path path)
	{
		paths.removeValue(path, true);
	}
}
