package com.rhymes.game.stage.levels;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.physical.PhysicsBody;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.game.stage.result.Result;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class BounceLevel9 extends BounceTest {

	private PhysicsBody house2;
	private PhysicsBody balloon;
	private PhysicsBody levelelement1;
	private PhysicsBody levelelement11;
	private PhysicsBody levelelement12;
	private PhysicsBody levelelement13;
	private PhysicsBody levelelement6;
	private PhysicsBody levelelement4;

	public BounceLevel9() {
		// TODO Auto-generated constructor stub
	}

	public BounceLevel9(int levelNumber, Result result) {
		super(levelNumber, result);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub
		
//		return super.getAssets(assetPack);
		assetPack.addTexture(AssetConstants.IMG_BKG_LEVEL7);
		assetPack.addTexture(AssetConstants.IMG_HOUSE2);
		assetPack.addTexture(AssetConstants.IMG_BALLOON);

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
		
		this.setPlayerPositionX(30*GameMenuInfo.ratio_w);
		this.setBallPositionY(158*GameMenuInfo.ratio_h);
		this.setPlayerPositionY(118f*GameMenuInfo.ratio_h);
		this.setBallPositionX(62f*GameMenuInfo.ratio_w);

//		this.setPlayerPositionX(-40*GameMenuInfo.ratio_w);
//		this.setBallPositionY(160*GameMenuInfo.ratio_h);
//		this.setPlayerPositionY(142f*GameMenuInfo.ratio_h);
//		this.setBallPositionX(61f*GameMenuInfo.ratio_w);
		
		background = new Background(0, 0, 480*GameMenuInfo.ratio_w, 320*GameMenuInfo.ratio_h, 1,
				AssetConstants.IMG_BKG_LEVEL7);
		addElement(background);
			///adding ballStick
		ballStick = new PhysicsBody(300*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 70*GameMenuInfo.ratio_w, 169*GameMenuInfo.ratio_h,
				"/ballstick/ballstickFinal.bin", AssetConstants.IMG_BALLSTICK,
				"gfx\\ballstickTrns.png", world, (short) 2);
		addElement(ballStick);
		///adding house two
		house2 = new PhysicsBody(380*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 99*GameMenuInfo.ratio_w, 173*GameMenuInfo.ratio_h,
				"/house2/house2.bin", AssetConstants.IMG_HOUSE2,
				"gfx\\house2-1.png", world, (short) 7);
		addElement(house2);
		///adding house two
//		balloon = new PhysicsBody(150, 100, 88, 147,
//				"//balloon/balloon.bin", AssetConstants.IMG_BALLOON,
//				"gfx\\balloon.png", world, (short) 2);
//		addElement(balloon);
		levelelement11 = new PhysicsBody(220*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 39*GameMenuInfo.ratio_w, 19*GameMenuInfo.ratio_h,
				"//levelelement1/levelelement11.bin", AssetConstants.IMG_ELEMENT1,
				"gfx\\l3-e1.png", world, (short) 19);
		addElement(levelelement11);
		levelelement12 = new PhysicsBody(240*GameMenuInfo.ratio_w, 37*GameMenuInfo.ratio_h, 39*GameMenuInfo.ratio_w, 19*GameMenuInfo.ratio_h,
				"//levelelement1/levelelement11.bin", AssetConstants.IMG_ELEMENT1,
				"gfx\\l3-e1.png", world, (short) 20);
		addElement(levelelement12);
		levelelement13 = new PhysicsBody(257*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 39*GameMenuInfo.ratio_w, 19*GameMenuInfo.ratio_h,
				"//levelelement1/levelelement11.bin", AssetConstants.IMG_ELEMENT1,
				"gfx\\l3-e1.png", world, (short) 21);
		addElement(levelelement13);
		levelelement6 = new PhysicsBody(150*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 35*GameMenuInfo.ratio_w, 31*GameMenuInfo.ratio_h,
				"//levelelement6/levelelement6.bin", AssetConstants.IMG_ELEMENT6,
				"gfx\\l3-e6.png", world, (short) 18);
		addElement(levelelement6);
		levelelement4 = new PhysicsBody(10*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 59*GameMenuInfo.ratio_w, 151*GameMenuInfo.ratio_h,
				"//levelelement4/levelelement4.bin", AssetConstants.IMG_ELEMENT4,
				"gfx\\l3-e4.png", world, (short) 17);
		addElement(levelelement4);
//		
//		ground = new PhysicsBody(0, 0, 480*GameMenuInfo.ratio_w, 22*GameMenuInfo.ratio_h, "/ground/ground.bin",
//				AssetConstants.IMG_GROUND, "gfx\\ground.png", world, ((short) 3));
//		addElement(ground);
		ground = new PhysicsBody(0, 20*GameMenuInfo.ratio_h, 480*GameMenuInfo.ratio_w, 2*GameMenuInfo.ratio_h, "/ground/ground.bin",
				AssetConstants.IMG_GROUND, "gfx\\ground.png", world, ((short) 3));
		background = new Background(0, 0, 480*GameMenuInfo.ratio_w, 22*GameMenuInfo.ratio_h, 1,
				AssetConstants.IMG_GROUND);
		addElement(background);


	
	}
	

}
