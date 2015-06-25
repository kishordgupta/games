package com.rhymes.game.entity.states;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.animations.AnimationTest;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.animations.common.AniRotate;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.entity.states.StateBase;
import com.rhymes.ge.core.entity.states.StateDefault;
import com.rhymes.helpers.Helper;

public class StateElementNormal extends StateDefault{
	
	public StateElementNormal(ElementBase element, AnimationBase animation) {
		super(element);
		this.animation = animation;
	}
	@Override
	public void init() {
		if(this.animation != null){
			animation.init();
		}
	}


}
