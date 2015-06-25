package com.rhymes.helpers;

import java.util.LinkedList;

public class Joint {
	public int JointInitRopeID;
    public float JointInitRopeDistance;
    public LinkedList<Integer> JointNodeRopeID;
    public LinkedList<Float> JointNodeRopeDistance;

    public Joint()
    {
        JointInitRopeID = 0;
        JointInitRopeDistance = 0f;
        JointNodeRopeID = new LinkedList<Integer>();
        JointNodeRopeDistance = new LinkedList<Float>();
    }

}
