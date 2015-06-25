package com.rhymes.ge.core.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rhymes.helpers.Helper;

public class Screen {
	private SpriteBatch batch;
	public OrthographicCamera cam;
	GL10 gl ;
	public Screen() {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
//		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		cam = new OrthographicCamera(Helper.getScreenWidth(), Helper.getScreenHeight());
        cam.position.set(Helper.getScreenWidth()/2f, Helper.getScreenHeight()/2f, 0);

//		cam.position.set(0, 0, 0);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

	}
	
	public void reset()
	{
		cam.position.set(Helper.getScreenWidth()/2f, Helper.getScreenHeight()/2f, 0);
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

	public void update()	
	{
        cam.update();
        batch.setProjectionMatrix(cam.combined);
	}

	public void setColor(float r, float g, float b, float a) {
		if(this.batch != null)
			batch.setColor(r, g, b, a);
		
	}

	public void setColor(int color) {
		if(this.batch != null)
			batch.setColor(color);		
	}

	public void setColor(Color color) {
		if(this.batch != null)
			batch.setColor(color);		
	}
}
