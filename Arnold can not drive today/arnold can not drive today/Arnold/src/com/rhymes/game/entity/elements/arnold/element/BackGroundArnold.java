package com.rhymes.game.entity.elements.arnold.element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.entity.elements.arnold.ArnoldGameConstants;
import com.rhymes.game.entity.elements.arnold.stage.StageArnold;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class BackGroundArnold extends ElementBase implements InteractionTouchCallbacks{

	public float TargetTime = 0f;
	
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
		
		if(StageArnold.is_sensor_active)
			check_region_for_sensor();
		
//		Helper.println("region ::: "+region);
//		Helper.println("is sensor active ::: "+StageArnold.is_sensor_active);
		
//		zoom_i += 0.5f * ArnoldGameConstants.ratio_w ;
		zoom_i = zoom_i + Gdx.graphics.getWidth()/2f * stepTime / TargetTime;
		zoom_h += (Gdx.graphics.getWidth()/2f * stepTime / TargetTime) / ArnoldGameConstants.aspectRatio ;
	}


	public final static int REGION_RIGHT = 1;
	public final static int REGION_LEFT = 2;
	
	private int region;
	private boolean touched;

	String imagePath_1, imagePath_2, imagePath_3, imagePath_4;
	TextureRegion image2, image3, image4;
	
	
	public BackGroundArnold(float x, float y, float width, float height, int zIndex,
			String imagePath_1, String imagePath_2, String imagePath_3, String imagePath_4){
		super(x, y, width, height, zIndex);

		this.imagePath_1 = imagePath_1; 
		this.imagePath_2 = imagePath_2;
		this.imagePath_3 = imagePath_3;
		this.imagePath_4 = imagePath_4;
		
	}
	
	
	float render_x, render_y;
	
	
	@Override
	public void render() {
		zooming();
	}


	float zoom_i = 0;
	float zoom_h = 0;
	public void zooming(){
//		Helper.println("\nZooming: zoom_i width: " + zoom_i);
//		Helper.println("Zooming: aspectRatio: " + ArnoldGameConstants.aspectRatio);
//		Helper.println("Zooming: zoom_i height: " + zoom_h);
//		Helper.println("Zooming: width: " + this.width/2f);
//		Helper.println("Zooming: height: " + this.height/2f);
		if(-this.width/2f + zoom_i <0){
			GlobalVars.ge.getRenderer().render(image, -this.width/2f + zoom_i, this.height/2f, this.width - zoom_i, this.height - zoom_h);
			GlobalVars.ge.getRenderer().render(image2, -this.width/2f + zoom_i, -this.height/2f + zoom_h, this.width - zoom_i, this.height - zoom_h);
			GlobalVars.ge.getRenderer().render(image3, this.width/2f, -this.height/2f + zoom_h, this.width - zoom_i, this.height - zoom_h);
			GlobalVars.ge.getRenderer().render(image4, this.width/2f, this.height/2f, this.width - zoom_i, this.height - zoom_h);
		}
		
		else{
			GlobalVars.ge.getRenderer().render(image, 0f, this.height/2f, this.width/2f, this.height/2f);
			GlobalVars.ge.getRenderer().render(image2, 0f, 0f, this.width/2f, this.height/2f);
			GlobalVars.ge.getRenderer().render(image3, this.width/2f, 0f, this.width/2f, this.height/2f);
			GlobalVars.ge.getRenderer().render(image4, this.width/2f, this.height/2f, this.width/2f, this.height/2f);
		}

	}


	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(imagePath_1);
		assetPack.addTexture(imagePath_2);
		assetPack.addTexture(imagePath_3);
		assetPack.addTexture(imagePath_4);
		
	}

	@Override
	public void init() {
		this.image = Helper.getImageFromAssets(imagePath_1);
		this.image2 = Helper.getImageFromAssets(imagePath_2);
		this.image3 = Helper.getImageFromAssets(imagePath_3);
		this.image4 = Helper.getImageFromAssets(imagePath_4);

	}

	@Override
	public void onEvent(Point hitPoint) {

		if(checkHit(hitPoint)){
			setTouched(true);
			
//			regionCheck(hitPoint , StageArnold.is_sensor_active);
			if(!StageArnold.is_sensor_active)
				check_region_for_touch(hitPoint);
			
			((StageArnold)GlobalVars.ge.getCurrentStage()).user_input_region = getRegion();
			
		}
	}
	
	
	/**
	 * FOR Touch input
	 */
	public void check_region_for_touch(Point hitPoint){
		if(hitPoint.x > this.width/2f && hitPoint.x <= this.width){
			setRegion(REGION_RIGHT);
		}
		
		else if(hitPoint.x >= 0 && hitPoint.x < this.width/2f){
			setRegion(REGION_LEFT);
		}
	}

	
	/**
	 * FOR Sensor input
	 */
	public void check_region_for_sensor(){
//		Helper.println("ACCX: " + Gdx.input.getAccelerometerY());
		if(Gdx.input.getAccelerometerY() <= 0){
			setRegion(REGION_LEFT);
		}
		
		else if(Gdx.input.getAccelerometerY() > 0){
			setRegion(REGION_RIGHT);
		}
		
		((StageArnold)GlobalVars.ge.getCurrentStage()).user_input_region = getRegion();
	}
	
	
	protected boolean checkHit(Point p){
		if(this.getLeft() <= p.x && this.getRight()>= p.x && this.getTop() >= p.y && this.getBottom() <= p.y){
			return true;
		}
		return false;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public int getRegion() {
		return region;
	}

	public void setTouched(boolean touched) {
		this.touched = touched;
	}

	public boolean isTouched() {
		return touched;
	}
}
