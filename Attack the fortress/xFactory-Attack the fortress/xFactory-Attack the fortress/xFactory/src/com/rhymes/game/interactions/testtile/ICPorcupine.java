package com.rhymes.game.interactions.testtile;

import com.rhymes.game.entity.elements.testtile.HeroTile;
import com.rhymes.ge.core.interactions.InteractionCallbacks;

public interface ICPorcupine extends InteractionCallbacks {
	public void onCollision(HeroTile hero);
	public boolean isCollided();
}
