package com.rhymes.ge.core.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.rhymes.game.stage.levels.TestTileLevel;
import com.rhymes.helpers.Helper;

public class Screen {
	private SpriteBatch batch;
	public OrthographicCamera cam;
	private Rectangle glViewport;
	GL10 gl ;
	public Screen() {
//		Gdx.gl.glClearColor(0.6f, 0.7f, 0.8f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//        glViewport = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        gl = Gdx.graphics.getGL10();
//        gl.glViewport((int) glViewport.x, (int) glViewport.y,
//                      (int) glViewport.width, (int) glViewport.height);
//        
//        
//		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());            
// //       cam.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
//		cam.position.set(0, 0, 0);
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

	public void update()	
	{
//		cam.rotate(1, 0, 0, 1);
//		cam.zoom-=0.2f;
//        cam.update();
//        cam.apply(gl);
	}
}
