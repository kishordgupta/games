package com.rhymes.ge.core.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Screen {
	private SpriteBatch batch;

	public Screen() {
		Gdx.gl.glClearColor(0.6f, 0.7f, 0.8f, 1f);
		batch = new SpriteBatch();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}
	
	public void clearScreen()
	{
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);		
	}

}
