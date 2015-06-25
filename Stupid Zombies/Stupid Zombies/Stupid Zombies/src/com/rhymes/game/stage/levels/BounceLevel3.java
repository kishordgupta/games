package com.rhymes.game.stage.levels;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.physical.PhysicsBody;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.game.stage.result.Result;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class BounceLevel3 extends BounceTest {

//	private PhysicsBody house2;
	private PhysicsBody town;
	private PhysicsBody ladder;
	private PhysicsBody house2;
	private PhysicsBody balloon;
	private PhysicsBody ladder1;

	public BounceLevel3() {
		// TODO Auto-generated constructor stub
	}

	public BounceLevel3(int levelNumber, Result result) {
		super(levelNumber, result);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub
		
//		return super.getAssets(assetPack);
		assetPack.addTexture(AssetConstants.IMG_PLAYGROUND_BCKG);
		assetPack.addTexture(AssetConstants.IMG_TOWNHOUSE);
		assetPack.addTexture(AssetConstants.IMG_lADDER);

		return assetPack;
	}

	/* (non-Javadoc)
	 * @see com.rhymes.game.stage.levels.BounceTest#loadGameElements()
	 */
	@Override
	public void loadGameElements() {
		// TODO Auto-generated method stub
//		super.loadGameElements();

		background = new Background(0, 0, 480*GameMenuInfo.ratio_w, 320*GameMenuInfo.ratio_h, 1,
				AssetConstants.IMG_PLAYGROUND_BCKG);
		addElement(background);
//		ground = new PhysicsBody(0, 0, 480*GameMenuInfo.ratio_w, 22*GameMenuInfo.ratio_h, "/ground/ground.bin",
//				AssetConstants.IMG_GROUND, "gfx\\ground.png", world, ((short) 3));
//		addElement(ground);
		ground = new PhysicsBody(0, 10*GameMenuInfo.ratio_h, 480*GameMenuInfo.ratio_w, 2*GameMenuInfo.ratio_h, "/ground/ground.bin",
				AssetConstants.IMG_GROUND, "gfx\\ground.png", world, ((short) 3));
		background = new Background(0, 0, 480*GameMenuInfo.ratio_w, 22*GameMenuInfo.ratio_h, 1,
				AssetConstants.IMG_GROUND);
		addElement(background);
		///adding ballStick
		ballStick = new PhysicsBody(300*GameMenuInfo.ratio_w, 10*GameMenuInfo.ratio_h, 70*GameMenuInfo.ratio_w, 169*GameMenuInfo.ratio_h,
				"/ballstick/ballstickFinal.bin", AssetConstants.IMG_BALLSTICK,
				"gfx\\ballstickTrns.png", world, (short) 2);
		addElement(ballStick);
		///adding house two
		house2 = new PhysicsBody(381*GameMenuInfo.ratio_w, 10*GameMenuInfo.ratio_h, 99*GameMenuInfo.ratio_w, 173*GameMenuInfo.ratio_h,
				"/house2/house2.bin", AssetConstants.IMG_HOUSE2,
				"gfx\\house2-1.png", world, (short) 7);
		addElement(house2);
		///adding house two
		balloon = new PhysicsBody(150*GameMenuInfo.ratio_w, 100*GameMenuInfo.ratio_h, 88*GameMenuInfo.ratio_w, 147*GameMenuInfo.ratio_h,
				"//balloon/balloon.bin", AssetConstants.IMG_BALLOON,
				"gfx\\balloon.png", world, (short) 6);
		addElement(balloon);
		ladder1 = new PhysicsBody(5*GameMenuInfo.ratio_w, 10*GameMenuInfo.ratio_h, 95*GameMenuInfo.ratio_w, 143*GameMenuInfo.ratio_h,
				"//ladder1/ladder1.bin", AssetConstants.IMG_LADDER,
				"gfx\\ladder.png", world, (short) 5);
		addElement(ladder1);
		this.setPlayerPositionY(120f*GameMenuInfo.ratio_h);
		this.setPlayerPositionX(40f*GameMenuInfo.ratio_w);
		this.setBallPositionY(160*GameMenuInfo.ratio_h);
		this.setBallPositionX(73*GameMenuInfo.ratio_w);
		
	
	}
	

}
