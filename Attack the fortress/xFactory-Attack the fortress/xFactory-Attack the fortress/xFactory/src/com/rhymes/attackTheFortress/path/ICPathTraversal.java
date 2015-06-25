package com.rhymes.attackTheFortress.path;

import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.ge.core.interactions.InteractionCallbacks;

public interface ICPathTraversal extends InteractionCallbacks{
	public TraversePointInfo getTraverseInfo();
	public void setTraverseInfo(TraversePointInfo traverseInfo);
	public int getPathTraversalDirection();
	public void setPathTraversalDirection(int pathTraversalDirection);
	public void actPathTraversal();
}
