package com.rhymes.game.interactions;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class InteractionFlick implements InputProcessor{
	Point startPoint = new Point();
	Point endPoint = new Point();
	
	long startTime = 0;
	float speed;
	float angle;
	ICFlick target;
	
	public InteractionFlick(ICFlick target)
	{
		this.target = target;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	boolean started = false;
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		this.startPoint.x =  x;
		this.startPoint.y = Gdx.graphics.getHeight() - y;
		startTime = System.currentTimeMillis();
		started = true;
//		Helper.println("\n\n\n***********************Started");
		return false;
	}
	
	
	private void takeAction() {
//		Helper.println("Start: " + startPoint);
//		Helper.println("Hit: " + hitPoint);
//		Helper.println("Prev: " + prevPoint);
		Helper.println("Time: " + (System.currentTimeMillis() - startTime));
		Helper.println("Distance: " + endPoint.distancePoint2Point(startPoint));
		Helper.println("Angle: " + (Helper.getAngle(startPoint, endPoint) + 90));
		angle = Helper.getAngle(startPoint, endPoint) + 90;
//		if((System.currentTimeMillis() - startTime)!=0)
//		{
//		speed = hitPoint.distancePoint2Point(startPoint)
//				/ (System.currentTimeMillis() - startTime) * 500;
//		}
//		else speed = 100;
//		

		target.onFlick(System.currentTimeMillis() - startTime,  endPoint.distancePoint2Point(startPoint), 
				Helper.getAngle(startPoint, endPoint) + 90, startPoint, endPoint);
		
		started = false;
		
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		y = Gdx.graphics.getHeight() - y;
		endPoint.setLocation(x, y);
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if(started)
		{
			y = Gdx.graphics.getHeight() - y;
			endPoint.setLocation(x, y);
			takeAction();
		}
			
			
		return false;
	}

}
