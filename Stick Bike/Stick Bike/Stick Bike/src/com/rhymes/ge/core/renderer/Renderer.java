package com.rhymes.ge.core.renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.view.Screen;
import com.rhymes.helpers.Helper;

public class Renderer {
	
	protected Screen screen; 
	private SpriteBatch batch;
	
	private float scaleX = 1;
	private float scaleY = 1;
	
	
	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public Renderer(Screen screen)
	{
		this.screen = screen;
		this.batch = this.screen.getBatch();
	}
	
	/**
	 * @param element Draws image using x, y, width, height of the passed ElementBase
	 * IF you need to have different render method for any element please write it in
	 * this class. By that, you would easily find what custom rendering did you need. 
	 */
	public void render(ElementBase element)
	{
		if(element.getImage() == null)
		{
			Helper.println("Renderer.java - Texture is null: " + element.getClass());
			return;
		}
		
		TextureRegion textureRegion = element.getImage();
		
		if(textureRegion == null)
		{
//			Helper.println("Renderer.java - Texture is null");
			return;
		}
		batch.draw(textureRegion.getTexture(), element.getX(), element.getY(),
				element.getWidth(), element.getHeight(),
				0, 0, (int) textureRegion
						.getRegionWidth(), textureRegion.getRegionHeight(),
				false, false);
	}
	
	public void render(ElementBase element, float angle)
	{
		if(element.getImage() == null)
		{
//			Helper.println("Renderer.java - Texture is null");
			return;
		}
		   batch.draw(element.getImage(),
				     element.getX(), element.getY(),
				     // the bottom left corner of the box,
				     // unrotated
				     //origin_x, origin_y, 
				     (element.getWidth()/2f), 0,
				     // the rotation
				     // center
				     // relative
				     // to the
				     // bottom
				     // left
				     // corner of
				     // the box
				     element.getWidth(),element.getHeight(), 
				     // the width and height
				     // of the box
				     1f , 1f ,
				     // the scale on the x-
				     // and y-axis
				     //angle[i], false(); // the
				   angle,true);
	}
	
	public void render(TextureRegion image, float x, float y, float width, float height, float originX, float origingY, float angle)
	{
		if(image == null)
		{
			return;
		}
		
		batch.draw(image,
			     x,y,
			     originX, origingY,
			      width,height, 
			     1f , 1f ,
			     angle,true);
	}
	
	public void render(TextureRegion image, float x, float y, float width, float height, float originX, float origingY, float angle, float scaleX, float scaleY)
	{
		if(image == null)
		{
			Helper.println("Renderer.java - Texture is null - " + image);
			return;
		}
	   batch.draw(image,
			     x,y,
			     originX, origingY,
			      width,height, 
			     scaleX , scaleY ,
			     angle,true);
	}
	
	
	public void render(TextureRegion image, float x, float y, float width, float height)
	{
		if(image == null)
		{
			Helper.println("Renderer.java - Texture is null" + image);
			return;
		}
//	    batch.draw(image,
//			     x,y,
//			     0,0,
//			     width,height, 
//			     1f , 1f ,
//			     0,true);
		batch.draw(image.getTexture(),
			     x,y,
			     width,height, 
				0, 0, (int) image
						.getRegionWidth(), image.getRegionHeight(),
				false, false);
	}
	
	public void startRendering()
	{
		if(batch != null){
			batch.begin();
			screen.clearScreen();
		}
	}	
	
	public void stopRendering()
	{
		if(batch != null)
			batch.end();
	}
}
