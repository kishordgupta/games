package com.rhymes.game.stage.levels;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.physical.PhysicsBody;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.game.stage.result.Result;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class BounceLevel4 extends BounceTest {

	private PhysicsBody byHouse;
	private PhysicsBody balloon;
	private PhysicsBody wareHouse;
	private PhysicsBody tree;
	private PhysicsBody ladder;
	private PhysicsBody town;

	public BounceLevel4() {
		// TODO Auto-generated constructor stub
	}

	public BounceLevel4(int levelNumber, Result result) {
		super(levelNumber, result);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub
		
//		return super.getAssets(assetPack);
		assetPack.addTexture(AssetConstants.IMG_PLAYGROUND_BCKG);
		assetPack.addTexture(AssetConstants.IMG_BY_HOUSE);
		assetPack.addTexture(AssetConstants.IMG_TREE);
		assetPack.addTexture(AssetConstants.IMG_LADDER);

		return assetPack;
	}

	/* (non-Javadoc)
	 * @see com.rhymes.game.stage.levels.BounceTest#loadGameElements()
	 */
	@Override
	public void loadGameElements() {
		// TODO Auto-generated method stub
//		super.loadGameElements();
		addElement(new Background(0, 0, 480*GameMenuInfo.ratio_w, 320*GameMenuInfo.ratio_h, 1,
				AssetConstants.IMG_PLAYGROUND_BCKG));
		
		//adding ballStick
		ballStick = new PhysicsBody(360*GameMenuInfo.ratio_w, 15*GameMenuInfo.ratio_h, 70*GameMenuInfo.ratio_w, 169*GameMenuInfo.ratio_h,
				"/ballstick/ballstickFinal.bin", AssetConstants.IMG_BALLSTICK,
				"gfx\\ballstickTrns.png", world, (short) 2);
		addElement(ballStick);
		//adding ladder
		ladder = new PhysicsBody(420*GameMenuInfo.ratio_w, 15*GameMenuInfo.ratio_h, 60*GameMenuInfo.ratio_w, 115*GameMenuInfo.ratio_h,
				"/ladder/ladder.bin", AssetConstants.IMG_lADDER,
				"gfx\\ladder.png", world, (short) 9);
		addElement(ladder);
		//		adding townHouse
		town = new PhysicsBody(-35*GameMenuInfo.ratio_w, 15*GameMenuInfo.ratio_h, 343*GameMenuInfo.ratio_w, 240*GameMenuInfo.ratio_h,
				"/town/town.bin", AssetConstants.IMG_TOWNHOUSE,
				"gfx\\house.png", world, (short) 8);
		addElement(town);
		//adding ground
//		ground = new PhysicsBody(0, 0, 480*GameMenuInfo.ratio_w, 22*GameMenuInfo.ratio_h, "/ground/ground.bin",
//				AssetConstants.IMG_GROUND, "gfx\\ground.png", world, ((short) 3));
//		addElement(ground);
		ground = new PhysicsBody(0, 15*GameMenuInfo.ratio_h, 480*GameMenuInfo.ratio_w, 2*GameMenuInfo.ratio_h, "/ground/ground.bin",
				AssetConstants.IMG_GROUND, "gfx\\ground.png", world, ((short) 3));
		background = new Background(0, 0, 480*GameMenuInfo.ratio_w, 22*GameMenuInfo.ratio_h, 1,
				AssetConstants.IMG_GROUND);
		addElement(background);
		this.setPlayerPositionX(20f*GameMenuInfo.ratio_w);
		this.setPlayerPositionY(133f*GameMenuInfo.ratio_h);
		this.setBallPositionX(56*GameMenuInfo.ratio_w);
		this.setBallPositionY(179*GameMenuInfo.ratio_h);
			
	}
	

}
