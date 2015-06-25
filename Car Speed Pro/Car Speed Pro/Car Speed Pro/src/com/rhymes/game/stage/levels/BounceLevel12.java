package com.rhymes.game.stage.levels;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.physical.PhysicsBody;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.game.stage.result.Result;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class BounceLevel12 extends BounceTest {

	private PhysicsBody house2;
	private PhysicsBody balloon;
	private PhysicsBody ladder1;
	private ElementBase levelelement4;
	private PhysicsBody levelelement6;
	private PhysicsBody levelelement51;
	private PhysicsBody levelelement52;
	private PhysicsBody levelelement53;
	private PhysicsBody levelelement7;

	public BounceLevel12() {
		// TODO Auto-generated constructor stub
	}

	public BounceLevel12(int levelNumber, Result result) {
		super(levelNumber, result);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub
		
//		return super.getAssets(assetPack);
		assetPack.addTexture(AssetConstants.IMG_PLAYGROUND_BCKG);
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
		float ex = 200*GameMenuInfo.ratio_w;
		float ey = 20*GameMenuInfo.ratio_h;
		levelelement51 = new PhysicsBody(ex, ey, 98*GameMenuInfo.ratio_w, 76*GameMenuInfo.ratio_h,
				"//levelelement5/levelelement5.bin", AssetConstants.IMG_ELEMENT5,
				"gfx\\l3-e5.png", world, (short) 32);
		addElement(levelelement51);
//		levelelement52 = new PhysicsBody(ex+95, ey+75, 98, 76,
//				"//levelelement5/levelelement5.bin", AssetConstants.IMG_ELEMENT5,
//				"gfx\\l3-e5.png", world, (short) 2);
//		addElement(levelelement52);
//		levelelement53 = new PhysicsBody(ex+95, ey, 98, 76,
//				"//levelelement5/levelelement5.bin", AssetConstants.IMG_ELEMENT5,
//				"gfx\\l3-e5.png", world, (short) 2);
//		addElement(levelelement53);
		levelelement6 = new PhysicsBody(150*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 35*GameMenuInfo.ratio_w, 31*GameMenuInfo.ratio_h,
				"//levelelement6/levelelement6.bin", AssetConstants.IMG_ELEMENT6,
				"gfx\\l3-e6.png", world, (short) 18);
		addElement(levelelement6);
		levelelement7 = new PhysicsBody(347*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 34*GameMenuInfo.ratio_w, 31*GameMenuInfo.ratio_h,
				"//levelelement7/levelelement7.bin", AssetConstants.IMG_ELEMENT7,
				"gfx\\l3-e7.png", world, (short) 31);
		addElement(levelelement7);
		levelelement4 = new PhysicsBody(10*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 59*GameMenuInfo.ratio_w, 151*GameMenuInfo.ratio_h,
				"//levelelement4/levelelement4.bin", AssetConstants.IMG_ELEMENT4,
				"gfx\\l3-e4.png", world, (short) 17);
		addElement(levelelement4);
		
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
