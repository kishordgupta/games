package com.rhymes.game.entity.elements.physical;

import com.badlogic.gdx.physics.box2d.World;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Ball2 extends Ball {
	public Ball2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ball2(float x, float y, float radius, World world,
			boolean isStatic) {
//		super();
		super(x, y, radius, world, isStatic);
		this.getBody().getFixtureList().get(this.getBody().getFixtureList().size()-1).setRestitution(0.9f);

	}
	public Ball2(float x, float y, float radius, String imagePath, World world,
			boolean isStatic) {
//		super();
		super(x, y, radius,imagePath, world, isStatic);
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub
		super.getAssets(assetPack);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
//		super.init();
		this.image = Helper.getImageFromAssets(AssetConstants.IMG_RUBBER_BALL);
	}

}
