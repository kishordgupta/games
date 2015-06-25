package com.rhymes.attackTheFortress;

import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;

public interface IRangeDetection extends InteractionCallbacks {
	
	public void onRange(ElementBase target);
	public float getRange();
}
