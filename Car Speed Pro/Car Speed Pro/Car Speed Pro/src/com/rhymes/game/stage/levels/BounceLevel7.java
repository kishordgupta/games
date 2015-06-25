package com.rhymes.game.stage.levels;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.physical.PhysicsBody;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.game.stage.result.Result;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class BounceLevel7 extends BounceTest {

	private PhysicsBody house2;
	private PhysicsBody balloon;
	private PhysicsBody ladder1;
	private PhysicsBody levelelement;
	private PhysicsBody levelelement6;
	private PhysicsBody wareHouse;
	private Background tree;
	private Background chair;
	private PhysicsBody balloon2;
	private PhysicsBody doubleladder;
	private PhysicsBody singleladder;
	private PhysicsBody woodstock2;
	private PhysicsBody woodstock3;

	public BounceLevel7() {
		// TODO Auto-generated constructor stub
	}

	public BounceLevel7(int levelNumber, Result result) {
		super(levelNumber, result);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub
		
//		return super.getAssets(assetPack);
		assetPack.addTexture(AssetConstants.IMG_BKG_LEVEL7);
		assetPack.addTexture(AssetConstants.IMG_LEVEL_ELEMENT6);
		assetPack.addTexture(AssetConstants.IMG_LEVEL_ELEMENT1);

		return assetPack;
	}

	/* (non-Javadoc)
	 * @see com.rhymes.game.stage.levels.BounceTest#loadGameElements()
	 */
	@Override
	public void loadGameElements() {
		// TODO Auto-generated method stub
//		super.loadGameElements();
		//loading background

		
		//loading background
		background = new Background(0, 0, 480*GameMenuInfo.ratio_w, 320*GameMenuInfo.ratio_h, 1,
				AssetConstants.IMG_PLAYGROUND_BCKG);
		addElement(background);

		///adding ballStick
		ballStick = new PhysicsBody(320*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 70*GameMenuInfo.ratio_w, 169*GameMenuInfo.ratio_h,
				"/ballstick/ballstickFinal.bin", AssetConstants.IMG_BALLSTICK,
				"gfx\\ballstickTrns.png", world, (short) 2);
		addElement(ballStick);
		///adding house two
		house2 = new PhysicsBody(380*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 99*GameMenuInfo.ratio_w, 173*GameMenuInfo.ratio_h,
				"/house2/house2.bin", AssetConstants.IMG_HOUSE2,
				"gfx\\house2-1.png", world, (short) 7);
		addElement(house2);

		wareHouse = new PhysicsBody(180*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 82*GameMenuInfo.ratio_w, 50*GameMenuInfo.ratio_h,
				"//warehouse/warehouse.bin", AssetConstants.IMG_WAREHOUSE,
				"gfx\\warehouse.png", world, (short) 10);
		addElement(wareHouse);

		tree = new Background(0, 40*GameMenuInfo.ratio_h, 113*GameMenuInfo.ratio_w, 108*GameMenuInfo.ratio_h, 1,
				AssetConstants.IMG_TREE);
		addElement(tree);

		chair = new Background(110*GameMenuInfo.ratio_w, 40*GameMenuInfo.ratio_h, 48*GameMenuInfo.ratio_w, 26*GameMenuInfo.ratio_h, 1,
				AssetConstants.IMG_CHAIR);
		addElement(chair);
		///adding house two
		balloon2 = new PhysicsBody(280*GameMenuInfo.ratio_w, 220*GameMenuInfo.ratio_h, 148*GameMenuInfo.ratio_w, 48*GameMenuInfo.ratio_h,
				"//balloon2/balloon2-1.bin", AssetConstants.IMG_BALLOON2,
				"gfx\\balloon2-1.png", world, (short) 11);
		addElement(balloon2);

		doubleladder = new PhysicsBody(0, 0, 63*GameMenuInfo.ratio_w,164*GameMenuInfo.ratio_h,
		"/doubleladder/doubleladder.bin", AssetConstants.IMG_DOUBLE_LADDER,
		"gfx\\ladder2-2.png", world, (short) 12);
		addElement(doubleladder);
		
		singleladder = new PhysicsBody(165*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 17*GameMenuInfo.ratio_w,104*GameMenuInfo.ratio_h,
				"/singleladder/singleladder.bin", AssetConstants.IMG_SINGLELADDER,
				"gfx\\ladder2-0.png", world, (short) 13);
				addElement(singleladder);

		
//		ground = new PhysicsBody(0, 0, 480*GameMenuInfo.ratio_w, 22*GameMenuInfo.ratio_h, "/ground/ground.bin",
//				AssetConstants.IMG_GROUND, "gfx\\ground.png", world, ((short) 3));
//		addElement(ground);
				ground = new PhysicsBody(0, 20*GameMenuInfo.ratio_h, 480*GameMenuInfo.ratio_w, 2*GameMenuInfo.ratio_h, "/ground/ground.bin",
						AssetConstants.IMG_GROUND, "gfx\\ground.png", world, ((short) 3));
				background = new Background(0, 0, 480*GameMenuInfo.ratio_w, 22*GameMenuInfo.ratio_h, 1,
						AssetConstants.IMG_GROUND);
				addElement(background);
				this.setPlayerPositionX(12*GameMenuInfo.ratio_w);
				this.setBallPositionY(199*GameMenuInfo.ratio_h);
				this.setPlayerPositionY(158f*GameMenuInfo.ratio_h);
				this.setBallPositionX(45f*GameMenuInfo.ratio_w);

		
	
	}
	

}
