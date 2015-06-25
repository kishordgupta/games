package com.rhymes.game.interactions.inputs;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.rhymes.game.entity.elements.nonphysical.ResultBounce;
import com.rhymes.game.entity.elements.physical.Ball;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class InteractionBounce extends InteractionBase implements InputProcessor{
	Point startPoint = new Point();
	Point hitPoint = new Point();
	Point prevPoint = new Point();
	
	long startTime = 0;
	float speed;
	float angle;
	ElementBase target;
	
	public boolean startNow = false;
	
	public InteractionBounce(ElementBase target)
	{
		this.target = target;
		this.startNow = false;
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
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if(GlobalVars.ge.getCurrentStage() instanceof BounceTest){
			if(!((BounceTest)GlobalVars.ge.getCurrentStage()).startNow)
				return false;
		}
		
		if(!startNow)
			return false;

//		Helper.println("Touch down: ");
		this.startPoint.x =  x;
		this.startPoint.y = Gdx.graphics.getHeight() - y;
		return false;
	}
	
	boolean hit = false;
	Point p = new Point();
	private void takeHit() {

		if(!startNow)
			return;
		hit = true;
		
//		Helper.println("Start: " + startPoint);
//		Helper.println("Hit: " + hitPoint);
//		Helper.println("Prev: " + prevPoint);
//		Helper.println("Time: " + (System.currentTimeMillis() - startTime));
		
		
		angle = Helper.getAngle(target.getLocation(), hitPoint) + 90;
		hitPoint.setLocation(hitPoint.x / GameMenuInfo.ratio_w, hitPoint.y / GameMenuInfo.ratio_h);
		
		Helper.println("Distance: " + hitPoint.distancePoint2Point(startPoint));
		Helper.println("Angle: " + (Helper.getAngle(target.getLocation(), hitPoint) + 90));

		Helper.println("IB: calc speed; ratio_h: " + GameMenuInfo.ratio_h );
		speed = (float) Math.sqrt(hitPoint.distancePoint2Point(target.getLocation()) ) * 1.5f;
		
		((Ball) target).startThrow = true;

		try {
			((Ball) target).applyForce(speed, angle);
		} catch (Exception e) {
		}
		
		if(GlobalVars.ge.getCurrentStage() instanceof BounceTest)
			((BounceTest)GlobalVars.ge.getCurrentStage()).startNow = false;
	}

	boolean s = false;
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if(GlobalVars.ge.getCurrentStage() instanceof BounceTest){
			if(!((BounceTest)GlobalVars.ge.getCurrentStage()).startNow)
				return false;
		}

		if(!startNow)
			return false;
		if(hit)
			return false;
		s = true;
		Helper.println("Touch dragged: ");
		y = Gdx.graphics.getHeight() - y;
		hitPoint.setLocation(x, y);	
		
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {

		if(!startNow)
			return false;
		if(GlobalVars.ge.getCurrentStage() instanceof BounceTest){
			if(!((BounceTest)GlobalVars.ge.getCurrentStage()).startNow)
				return false;
		}

		if(hit)
			return false;
		Helper.println("Touch up: ");
		hitPoint.setLocation(x, Gdx.graphics.getHeight() - y);	
		if(s)
			takeHit();
		s = false;
		return false;
	}
	
//	public void reset()
//	{
//		this.s = false;
//		this.hit = false;
//	}

}
