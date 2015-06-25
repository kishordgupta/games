package com.rhymes.ge.core.renderer;

import com.badlogic.gdx.math.MathUtils;
import com.rhymes.helpers.Helper;

//import com.rhymes.game.entity.elements.path.PathNode;

public class Point {
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}

	public float x, y;

	public Point()
	{
		setLocation(0, 0);
	}
	public Point(float x, float y)
	{
		setLocation(x, y);
	}
	public Point(Point point) {
		this.x = point.x;
		this.x = point.y;
	}
	public void setLocation(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString()
	{
		String s;
		s = "(X,Y) ==>  ( " + x + ", " + y + " )";
//		Helper.println(s);
		return s;
	}
	
	public static float distancePointToLine(Point A, Point B, Point hitPoint)
	{
		 float normalLength = (float) Math.sqrt((B.x - A.x) * (B.x - A.x) + (B.y - A.y) * (B.y - A.y));
		 return Math.abs((hitPoint.x - A.x) * (B.y - A.y) - (hitPoint.y - A.y) * (B.x - A.x)) / normalLength;
	}
	
	public static float distancePoint2Point(Point p, Point n){
		return (float) Math.abs(Math.sqrt((n.y - p.y) * (n.y - p.y) + (n.x - p.x) * (n.x - p.x)));
	}
	

	public float distancePoint2Point(Point n){
		Point p = this;
		return (float) Math.abs(Math.sqrt((n.y - p.y) * (n.y - p.y) + (n.x - p.x) * (n.x - p.x)));
	}
	
	public void getPointAtDistance(Point srcPoint, Point destinationPoint, Point retPoint, float distance)
	{		
		double d = Point.distancePoint2Point(srcPoint, destinationPoint);
		double k = d - distance;
		double j = distance;
		
		retPoint.x = (float) ((j * destinationPoint.x + k * srcPoint.x) / (d));
		retPoint.y = (float) ((j * destinationPoint.y + k * srcPoint.y) / (d));
	}                                                                                                                                                                                                            

	public void getPointAtDistance(Point srcPoint, float angle, float distance, Point retPoint)
	{		
//		float d = (float) (Math.sqrt((srcPoint.x * srcPoint.x + srcPoint.y * srcPoint.y)) + distance);
		float d /*= (float) (srcPoint.y/Math.sinh(angle * MathUtils.degreesToRadians) + distance)*/;
//		Helper.println("Calculated Distance 1: " +  d);
//		d = (float) (Math.sqrt((srcPoint.x * srcPoint.x + srcPoint.y * srcPoint.y)) + distance);
		d = distance;
//		Helper.println("Calculated Distance 2: " +  d);
		
		retPoint.x = (float) ( Math.cos(angle * MathUtils.degreesToRadians) * d + srcPoint.x) ;
		retPoint.y = (float) ( Math.sin(angle * MathUtils.degreesToRadians) * d + srcPoint.y) ;
	}                                                                                                                                                                                                            

		
	
}
