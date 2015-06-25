package com.rhymes.attackTheFortress.input;

import com.badlogic.gdx.InputProcessor;
import com.rhymes.ge.core.interactions.InteractionBase;

public class InteractionInputListener extends InteractionBase implements InputProcessor{

	@Override
	public void checkCondition(long elapsedTime) {
	}

	@Override
	public void takeAction() {
	}

	@Override
	public boolean keyDown(int arg0) {
		for(int i = 0; i < elements.size; i++)
		{
			if(elements.get(i) instanceof InputProcessor)
			{
				((InputProcessor)elements.get(i)).keyDown(arg0);
			}
		}
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		for(int i = 0; i < elements.size; i++)
		{
			if(elements.get(i) instanceof InputProcessor)
			{
				((InputProcessor)elements.get(i)).keyTyped(arg0);
			}
		}
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		for(int i = 0; i < elements.size; i++)
		{
			if(elements.get(i) instanceof InputProcessor)
			{
				((InputProcessor)elements.get(i)).keyUp(arg0);
			}
		}
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		for(int i = 0; i < elements.size; i++)
		{
			if(elements.get(i) instanceof InputProcessor)
			{
				((InputProcessor)elements.get(i)).scrolled(arg0);
			}
		}
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		for(int i = 0; i < elements.size; i++)
		{
			if(elements.get(i) instanceof InputProcessor)
			{
				((InputProcessor)elements.get(i)).touchDown(arg0, arg1, arg2, arg3);
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		for(int i = 0; i < elements.size; i++)
		{
			if(elements.get(i) instanceof InputProcessor)
			{
				((InputProcessor)elements.get(i)).touchDragged(arg0, arg1, arg2);
			}
		}
		return false;
	}

	@Override
	public boolean touchMoved(int arg0, int arg1) {
		for(int i = 0; i < elements.size; i++)
		{
			if(elements.get(i) instanceof InputProcessor)
			{
				((InputProcessor)elements.get(i)).touchMoved(arg0, arg1);
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		for(int i = 0; i < elements.size; i++)
		{
			if(elements.get(i) instanceof InputProcessor)
			{
				((InputProcessor)elements.get(i)).touchUp(arg0, arg1, arg2, arg3);
			}
		}
		return false;
	}

}
