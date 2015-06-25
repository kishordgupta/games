package com.rhymes.game.entity.elements.ui;

import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Button extends ElementBase implements InteractionTouchCallbacks{
	@Override
	public void render() {
//		GlobalVars.ge.getScreen().getBatch().setColor(1, 1, 1, 0.5f);
		super.render();
//		GlobalVars.ge.getScreen().getBatch().setColor(1, 1, 1, 1f);
	}

	String imagePath;
	public Button(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
	
	public Button(float x, float y, float width, float height, int zIndex, String imagePath) {
		super(x, y, width, height, zIndex);
		this.imagePath = imagePath;
	}
	/**
	 * Add all the texture needed by this element in this function
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
//		Helper.println("gettting ass");
//		assetPack.addTexture(AssetConstants.IMG_BACK_2);
	}
	

	@Override
	public void init() {
		if(imagePath != null)
			this.image = Helper.getImageFromAssets(imagePath);
	}

	@Override
	public void step(long stepTime) {
//		this.state.step(stepTime);
	}

	@Override
	public void onEvent(Point htiPoint) {
//		StartupInfo.sound_handler.playSound(SoundHandler.soundType.SOUND_DESTROY);

	}
	
	protected boolean checkHit(Point p)
	{
//		Helper.println("\n\nChecking hitpoint..." + p + " for: " + this.getClass());
//		Helper.printKeyVal("x", this.x);
//		Helper.printKeyVal("r", this.getRight());
//		
//		Helper.printKeyVal("y", this.y);
//		Helper.printKeyVal("t", this.getTop());
		
//		if((this.getLeft() - this.getWidth()) <= p.x && (this.getRight() + this.getWidth())>= p.x 
//				&& (this.getTop() + this.getWidth()) >= p.y && (this.getBottom() - this.getWidth()) <= p.y)
		if(this.getLeft() <= p.x && this.getRight()>= p.x && this.getTop() >= p.y && this.getBottom() <= p.y)
		{
			set_pressed(true);
			return true;
		}
		return false;
	}
	
	boolean is_presssed = false;
	boolean is_pressed_finished = false;
	String temp_imagepath;
	
	public void set_pressed(boolean p){
		this.is_presssed = p;
	}
	
	public boolean get_pressed(){
		return is_presssed;
	}
	
	public void set_animation(String img){
		if(!get_pressed())
			return;
		
		else{
//			set_pressed(true);
		temp_imagepath = this.imagePath;
		this.imagePath = img;
		this.image = Helper.getImageFromAssets(imagePath);
		}
		
		is_pressed_finished = true;
	}
	
	public void adjust_animation(){
		if(is_pressed_finished){
			this.imagePath = temp_imagepath;
			this.image = Helper.getImageFromAssets(imagePath);
			}
	}
}
