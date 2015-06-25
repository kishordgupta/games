package com.rhymes.game.entity.elements.nonphysical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.animations.common.AniRotate;
import com.rhymes.game.entity.elements.ui.NumericText;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.stage.levels.BounceLevel1;
import com.rhymes.game.stage.levels.BounceLevel10;
import com.rhymes.game.stage.levels.BounceLevel11;
import com.rhymes.game.stage.levels.BounceLevel12;
import com.rhymes.game.stage.levels.BounceLevel2;
import com.rhymes.game.stage.levels.BounceLevel3;
import com.rhymes.game.stage.levels.BounceLevel4;
import com.rhymes.game.stage.levels.BounceLevel5;
import com.rhymes.game.stage.levels.BounceLevel6;
import com.rhymes.game.stage.levels.BounceLevel7;
import com.rhymes.game.stage.levels.BounceLevel8;
import com.rhymes.game.stage.levels.BounceLevel9;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.result.Result;
import com.rhymes.game.stage.result.ResultBTRClassic;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class ScoreBounce extends NumericText{
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
		this.y = y+2f;	
		if(this.y > Gdx.graphics.getHeight())
		{
			GlobalVars.ge.getCurrentStage().removeElement(this);
		}
	}

	@Override
	public void render() {
		GlobalVars.ge.getScreen().getBatch().setColor(1f, 0.4f, 0f, 1f);
		super.render();
		GlobalVars.ge.getScreen().getBatch().setColor(1, 1, 1, 1f);
	}

	public ScoreBounce(float x, float y, int score) {
		super(x, y, 15, 25, 1);
		this.setNumber(score);
	}


	@Override
	public void getAssets(AssetPack assetPack) {
		
	}

	@Override
	public void init() {
	}
	
}
