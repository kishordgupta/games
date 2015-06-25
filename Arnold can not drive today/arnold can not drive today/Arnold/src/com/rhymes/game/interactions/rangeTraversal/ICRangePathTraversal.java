package com.rhymes.game.interactions.rangeTraversal;

import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.ge.core.interactions.InteractionCallbacks;

public interface ICRangePathTraversal extends InteractionCallbacks{
	public InfoRangeTraversal getRangeTraverseInfo();
	public void setRangeTraverseInfo(InfoRangeTraversal traverseInfo);
	public void actRangeTraversal();
	public boolean isActive();
	public void setActive(boolean active);
	
}
