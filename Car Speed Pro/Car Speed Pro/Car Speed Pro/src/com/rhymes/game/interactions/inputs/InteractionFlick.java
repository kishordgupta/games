package com.rhymes.game.interactions.inputs;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.rhymes.game.entity.elements.nonphysical.ResultBounce;
import com.rhymes.game.entity.elements.physical.Ball;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class InteractionFlick extends InteractionBase implements InputProcessor{
	Point startPoint = new Point();
	Point hitPoint = new Point();
	Point prevPoint = new Point();
	
	long startTime = 0;
	float speed;
	float angle;
	ElementBase target;
	
	public InteractionFlick(ElementBase target)
	{
		this.target = target;
	}
	
	@Override
	public void checkCondition(long elapsedTime) {
	}

	
	@Override
	public void takeAction() {
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
	
	boolean hit = false;
	
	private void takeHit() {
		hit = true;
//		Helper.println("Start: " + startPoint);
//		Helper.println("Hit: " + hitPoint);
//		Helper.println("Prev: " + prevPoint);
		Helper.println("Time: " + (System.currentTimeMillis() - startTime));
		Helper.println("Distance: " + hitPoint.distancePoint2Point(startPoint));
		Helper.println("Angle: " + (Helper.getAngle(prevPoint, hitPoint) + 90));
		angle = Helper.getAngle(prevPoint, hitPoint) + 90;
		if((System.currentTimeMillis() - startTime)!=0)
		{
		speed = hitPoint.distancePoint2Point(startPoint)
				/ (System.currentTimeMillis() - startTime) * 500;
		}
		else speed = 100;
			((Ball) target).startThrow = true;

			((Ball) target).applyForce(speed * 0.15f, angle);
		
		// ((Ball)target).applyForce(100, 45);
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
//		Helper.println("\n\n\n***********************Dragging" );

		if(hit)
			return false;	
		y = Gdx.graphics.getHeight() - y;
		int o = 40;
		if(target.getX() < x && target.getX()-o+ target.getWidth()+o > x){
			if(target.getY()-o < y && target.getY() + target.getHeight()+o > y){
				hitPoint.setLocation(x, y);
				takeHit();
				return false;
			}
		}
		
		prevPoint.setLocation(x, y);	
		
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
//		Helper.println("\n\n\n***********************Ended");
		return false;
	}

}
