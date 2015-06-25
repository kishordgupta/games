package com.rhymes.attackTheFortress;

import java.util.LinkedList;

import com.rhymes.game.entity.elements.path.PathNode;

public class RoadMap {
	protected LinkedList<PathNode> nodes;
	public RoadMap()
	{
		nodes = new LinkedList<PathNode>();
	}
}
