package com.rhymes.ge.core.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.rhymes.helpers.Helper;

public class Screen {
	private SpriteBatch batch;
	public OrthographicCamera cam;
	private Rectangle glViewport;
	GL10 gl ;
	final Matrix4 matrix = new Matrix4();
	public Screen() {
		Gdx.gl.glClearColor(0.6f, 0.7f, 0.8f, 1f);
//		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);


		cam = new OrthographicCamera(Helper.getScreenWidth(), Helper.getScreenHeight(), 25);
        cam.position.set(/*Helper.getScreenWidth()/2f*/0, Helper.getScreenHeight()/2f, 0);
//		cam.position.set(0, 0, 0);
        
//        cam.position.mul(30);
        cam.near = 1;
        cam.far = 1500;
//        matrix.setToRotation(new Vector3(1, 0, 0), 30);
//        matrix.setToRotation(new Vector3(0, 0, 1), -30);
//        matrix.rotate(0, 0, 1, -30);
//        matrix.rotate(0, 1, 0, 10);
        matrix.rotate(1, 0, 0, 30);
//        matrix.rotate(0, 0, 1, -90);
//        matrix.rotate(0, 1, 0, 90);
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
        batch.setTransformMatrix(matrix);
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
	
	
	public void setCamController(boolean enable)
	{
		if(enable){

	        Gdx.input.setInputProcessor(new InputProcessor() {
				
				@Override
				public boolean touchUp(int x, int y, int pointer, int button) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean touchMoved(int x, int y) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean touchDragged(int x, int y, int pointer) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean touchDown(int x, int y, int pointer, int button) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean scrolled(int amount) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean keyUp(int keycode) {
					return false;
				}
				
				@Override
				public boolean keyTyped(char character) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean keyDown(int keycode) {
					if(keycode == Keys.LEFT)
						cam.translate(-10, 0, 0);
					else if(keycode == Keys.RIGHT)
						cam.translate(10, 0, 0);
					else if(keycode == Keys.UP)
						cam.translate(0, 10, 0);
					else if(keycode == Keys.DOWN)
						cam.translate(0, -10, 0);
					else if(keycode == Keys.Z)
						cam.translate(0, 0, 10);
					else if(keycode == Keys.X)
						cam.translate(0, 0, -10);
					else if(keycode == Keys.A)
						cam.zoom--;
					else if(keycode == Keys.S)
						cam.zoom++;
					return false;
				}
			});
		}
			
	}
	
}
