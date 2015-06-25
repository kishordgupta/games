package com.rhymes.game.interactions.actiondriver;


import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class InteractionCarController implements InputProcessor{
	Point startPoint = new Point();
	Point endPoint = new Point();
	
	long startTime = 0;
	float speed;
	float angle;
	ICCarController target;
	
	public InteractionCarController(ICCarController target)
	{
		this.target = target;
	}

	@Override
	public boolean keyDown(int keycode) {
		if(Gdx.app.getType() == ApplicationType.Desktop){
			if(keycode == Keys.UP)
				target.changeSpeed(0, 7f);
			else if(keycode == Keys.DOWN)
				target.changeSpeed(0, -7f);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		target.setSpeed(target.getSpeedX(), 0);
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
//		Helper.println("Touch down");
		this.startPoint.x =  x;
		this.startPoint.y = Gdx.graphics.getHeight() - y;
		startTime = System.currentTimeMillis();
		started = true;
//		Helper.println("\n\n\n***********************Started");
		return false;
	}
	
	
	private void takeAction() {
		target.setSpeed(target.getSpeedX(), 0);
		started = false;
	}
	
	
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
//		Helper.println("Touch Dragg");
		y = Gdx.graphics.getHeight() - y;
		endPoint.setLocation(x, y);
		
		angle = (Helper.getAngle(startPoint, endPoint) + 90);
		
//		Helper.println("Angle: " + angle);
//		Helper.println("Target speed y " + target.getSpeedY());
		
		if(angle < 180 && angle > 0){
			if(target.getSpeedY() < 0)
				target.setSpeed(target.getSpeedX(), 0);
			target.changeSpeed(0, 1f);
//			Helper.println("Target change speed y++ " + target.getSpeedY());
		}
		else{
			if(target.getSpeedY() > 0)
				target.setSpeed(target.getSpeedX(), 0);
			target.changeSpeed(0, -1f);
//			Helper.println("Target change speed y-- " + target.getSpeedY());
		}
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
//		Helper.println("Touch up");
		if(started)
		{
			y = Gdx.graphics.getHeight() - y;
			endPoint.setLocation(x, y);
			takeAction();
		}
			
			
		return false;
	}

}
