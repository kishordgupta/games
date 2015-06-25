package com.rhymes.attackTheFortress.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Buttons;
import com.rhymes.ge.core.interactions.InteractionCallbacks;

public interface ICInputListener extends InputProcessor, InteractionCallbacks {
	/** Called when a key was pressed
	 * 
	 * @param keycode one of the constants in {@link Input.Keys}
	 * @return whether the input was processed */
	public boolean keyDown (int keycode);

	/** Called when a key was released
	 * 
	 * @param keycode one of the constants in {@link Input.Keys}
	 * @return whether the input was processed */
	public boolean keyUp (int keycode);

	/** Called when a key was typed
	 * 
	 * @param character The character
	 * @return whether the input was processed */
	public boolean keyTyped (char character);

	/** Called when the screen was touched or a mouse button was pressed. The button parameter will be {@link Buttons#LEFT} on
	 * Android.
	 * 
	 * @param x The x coordinate, origin is in the upper left corner
	 * @param y The y coordinate, origin is in the upper left corner
	 * @param pointer the pointer for the event.
	 * @param button the button
	 * @return whether the input was processed */
	public boolean touchDown (int x, int y, int pointer, int button);

	/** Called when a finger was lifted or a mouse button was released. The button parameter will be {@link Buttons#LEFT} on
	 * Android.
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param pointer the pointer for the event.
	 * @param button the button
	 * @return whether the input was processed */
	public boolean touchUp (int x, int y, int pointer, int button);

	/** Called when a finger or the mouse was dragged.
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param pointer the pointer for the event.
	 * @return whether the input was processed */
	public boolean touchDragged (int x, int y, int pointer);

	/** Called when the mouse was moved without any buttons being pressed. Will not be called on Android.
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return whether the input was processed */
	public boolean touchMoved (int x, int y);

	/** Called when the mouse wheel was scrolled. Will not be called on Android.
	 * @param amount the scroll amount, -1 or 1 depending on the direction the wheel was scrolled.
	 * @return whether the input was processed. */
	public boolean scrolled (int amount);

}
