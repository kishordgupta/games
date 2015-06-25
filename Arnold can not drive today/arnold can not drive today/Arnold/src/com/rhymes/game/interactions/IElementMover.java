package com.rhymes.game.interactions;

import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class IElementMover extends InteractionBase{
	
	public Point startPoint = new Point();
	public Point endPoint = new Point();
	private Point curPoint = new Point();
	
	ElementBase element;
	
	int i;
	
	public boolean active = false;
	public float distance = 0;
	public float stepDistance = 1f;
	
	
	public ElementBase getElement() {
		return element;
	}

	public void setElement(ElementBase element) {
		this.element = element;
	}
	
	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	public void setStartPoint(float x, float y) {
		this.startPoint.x = x;
		this.startPoint.y = y;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	public void setEndPoint(float x, float y) {
		this.endPoint.x = x;
		this.endPoint.y = y;
	}


	
	public float getStepDistance() {
		return stepDistance;
	}

	public void setStepDistance(float stepDistance) {
		this.stepDistance = stepDistance;
	}

	public boolean isActive() {
		return active;
	}
	
	
	public void setActive(boolean active) {
		this.active = active;
		
		if(!active){
			element.resetElementPosition();
		}
	}
	
	
	public void start(Point startPoint, Point endPoint, ElementBase element)
	{
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		
		this.element = element;
		
		this.distance = 0;
		this.setActive(true);
		
	}
	
	private void updateElement(){
		if(element == null)
			return;
		
		element.setLocation(curPoint);
	}
	
	
	@Override
	public void checkCondition(long elapsedTime) {
		
	}

	@Override
	public void takeAction() {
		if(active)
		{
			startPoint.getPointAtDistance(startPoint, endPoint, curPoint, distance);
			printInfo();
			updateElement();
			distance += stepDistance ;
			if(distance > startPoint.distancePoint2Point(endPoint))
			{
				curPoint.setLocation(endPoint);
				updateElement();
				this.setActive(false);
			}
		}
	}
	
	
	private void printInfo() {
		Helper.println("\nelement mover\nStart Point: " + startPoint + "\n" + 
				"End Point: " + endPoint + "\n" +
				"Cur Point: " + curPoint + "\n" +
				"Distance: " + distance + "\n" );
	}
}
