package com.rhymes.game.stage.menus.stick;

public class ScoreManage {

	public static long distance=0;
	public static long truckedFruit=0;
	public static long totalTruckedFruit=0;
	public static long totalIssuedFruit=0;




	public static double time=0;
	public static double percent=0;
	
	public static void reset()
	{
		distance = 0;
		truckedFruit=0;
		time = 0;
		percent = 0;
	}
	
	
	public static double getPercent() {
		return percent;
	}
	public static void setPercent(double percent) {
		ScoreManage.percent = percent;
	}
	public ScoreManage() {
		// TODO Auto-generated constructor stub
	}
public long getDistance(){
	return this.distance;
}
public void setDistance(long dist){
	this.distance=dist;
}

public static long getTruckedFruit() {
	return truckedFruit;
}


public static void setTruckedFruit(long truckedFruit) {
	ScoreManage.truckedFruit = truckedFruit;
}

public static long getTotalTruckedFruit() {
	return totalTruckedFruit;
}


public static void setTotalTruckedFruit(long totalTruckedFruit) {
	ScoreManage.totalTruckedFruit = totalTruckedFruit;
}

public long getTime(){
	return (long)this.time;
}
public void setTime(long stepTime){
//	this.tempTime=this.tempTime+stepTime;
	this.time=(this.time + stepTime/1000f);
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static long getTotalIssuedFruit() {
		return totalIssuedFruit;
	}


	public static void setTotalIssuedFruit(long totalIssuedFruit) {
		ScoreManage.totalIssuedFruit = totalIssuedFruit;
	}



}
