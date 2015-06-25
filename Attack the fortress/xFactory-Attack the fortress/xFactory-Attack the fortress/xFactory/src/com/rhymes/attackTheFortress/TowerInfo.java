package com.rhymes.attackTheFortress;

import java.util.ArrayList;
import java.util.LinkedList;

public class TowerInfo {
	public float number;
	public float maxLife;
	public float speed;
	public float damage;
	public float range;
	public float cost;
//	public	ArrayList<Integer> speedUpgradeValue;
	public LinkedList<Integer> speedUpgradeValue;
	public LinkedList<Integer> speedUpgradeCost;
	public LinkedList<Integer> damageUpgradeValue;
	public LinkedList<Integer> damageUpgradeCost;
	public LinkedList<Integer> rangeUpgradeValue;
	public LinkedList<Integer> rangeUpgradeCost;
	public TowerInfo(){
		number=0f;
		maxLife=0f;
		speed=0f;
		damage=0f;
		range=0f;
		cost=0f;
		speedUpgradeCost=new LinkedList<Integer>();
		speedUpgradeValue=new LinkedList<Integer>();
		damageUpgradeCost=new LinkedList<Integer>();
		damageUpgradeValue=new LinkedList<Integer>();
		rangeUpgradeCost=new LinkedList<Integer>();
		rangeUpgradeValue=new LinkedList<Integer>();
	}
}
