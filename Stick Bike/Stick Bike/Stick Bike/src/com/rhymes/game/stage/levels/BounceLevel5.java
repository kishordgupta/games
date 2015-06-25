package com.rhymes.game.stage.levels;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.physical.PhysicsBody;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.game.stage.result.Result;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class BounceLevel5 extends BounceTest {

	private PhysicsBody house2;
	private PhysicsBody balloon;
	private PhysicsBody ladder1;
	private PhysicsBody byHouse;
	private Background tree;
	private Background chair;
	private PhysicsBody wareHouse;
	private PhysicsBody balloon2;

	public BounceLevel5() {
		// TODO Auto-generated constructor stub
	}

	public BounceLevel5(int levelNumber, Result result) {
		super(levelNumber, result);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub
		
//		return super.getAssets(assetPack);
		assetPack.addTexture(AssetConstants.IMG_PLAYGROUND_BCKG);

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

//		ground = new PhysicsBody(0, 0, 480*GameMenuInfo.ratio_w, 22*GameMenuInfo.ratio_h, "/ground/ground.bin",
//				AssetConstants.IMG_GROUND, "gfx\\ground.png", world, ((short) 3));
//		addElement(ground);
		ground = new PhysicsBody(0, 20*GameMenuInfo.ratio_h, 480*GameMenuInfo.ratio_w, 2*GameMenuInfo.ratio_h, "/ground/ground.bin",
				AssetConstants.IMG_GROUND, "gfx\\ground.png", world, ((short) 3));
		background = new Background(0, 0, 480*GameMenuInfo.ratio_w, 22*GameMenuInfo.ratio_h, 1,
				AssetConstants.IMG_GROUND);
		addElement(background);
		//adding backyard house two
//		byHouse = new PhysicsBody(360, 30, 128,100,
//				"/levelelement8/levelelement8.bin", AssetConstants.IMG_BY_HOUSE,
//				"gfx\\l3-e10.png", world, (short) 2);
//		addElement(byHouse);
//		tree = new PhysicsBody(0, 35, 113, 108,
//		"//tree/tree.bin", AssetConstants.IMG_TREE,
//		"gfx\\tree.png", world, (short) 2);
//		chair = new PhysicsBody(150, 40, 48, 26,
//		"//chair/chair.bin", AssetConstants.IMG_CHAIR,
//		"gfx\\chair.png", world, (short) 2);
		
//		ladder1 = new PhysicsBody(5, 10, 95, 143,
//		"//ladder1/ladder1.bin", AssetConstants.IMG_LADDER,
//		"gfx\\ladder.png", world, (short) 2);
//addElement(ladder1);
//ladder1.getBody().setTransform(ladder1.getBody().getPosition(), 30);
//ladder1.setRotateAngle(30);
		this.setPlayerPositionX(20*GameMenuInfo.ratio_w);
		this.setPlayerPositionY(20*GameMenuInfo.ratio_h);
		this.setBallPositionX(52*GameMenuInfo.ratio_w);
		this.setBallPositionY(61*GameMenuInfo.ratio_h);
	
	}
	

}
