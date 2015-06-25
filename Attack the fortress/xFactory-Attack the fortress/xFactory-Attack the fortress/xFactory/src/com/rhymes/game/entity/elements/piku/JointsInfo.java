package com.rhymes.game.entity.elements.piku;

import com.rhymes.game.entity.elements.path.Path;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;

public class JointsInfo {
	public JointsInfo(TraversePointInfo info, int method) {
		super();
		this.info = info;
		this.method = method;
	}
	public TraversePointInfo info;
	public int method = Path.METHOD_RIGHT;
	
}
