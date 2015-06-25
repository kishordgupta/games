package com.rhymes.game.entity.elements.actiondriver;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.interactions.ICFlick;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.interactions.actiondriver.ICCarController;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Car extends ElementBase implements ICSingleCollisionCallbacks{
	protected float totalDistance = 0f;

	private float upperBoundaryY = Helper.getScreenHeight() - 10 * Constants.ry;
	private float lowerBoundaryY = 20 * Constants.ry;
	
	private float speedX ;
	private float speedY;
	
	private float delSpeed = 0.003f;
	private float stepHitX = 3;
	
	@Override
	public void render() {
		super.render();
	}

	public void stopCar()
	{
		this.setSpeed(0, 0);
	}
	
	
	
	public void moveCar()
	{
//		this.x += speedX / 16 * GlobalVars.ge.stepTime ;
//		this.y += speedY / 16 * GlobalVars.ge.stepTime;
		this.x += speedX;
		this.y += speedY;
		this.setTotalDistance(this.getTotalDistance() + speedX);
		
		if(this.y <= lowerBoundaryY)
			hitLowwerBoundary();
		if(this.y + this.height >= upperBoundaryY)
			hitUpperBoundary();
		
		
	}
	
	private void hitUpperBoundary() {
		this.speedY = 0;
		this.y-=10 * Constants.ry;
	}


	private void hitLowwerBoundary() {
		this.speedY = 0;
		this.y+=10 * Constants.ry;
	}


	@Override
	public void step(long stepTime) {
		moveCar();
	}


	public Car(float x, float y, float width, float height, int zIndex, float speedX) {
		super(x, y, width, height, zIndex);
		this.speedX = speedX;
		this.speedY = 0;
		
		this.upperBoundaryY = Constants.roadUpperBoundary;
		this.lowerBoundaryY = Constants.roadLowerBoundary;
		this.image = Helper.getImageFromAssets(AssetConstants.IMG_CAR_RED);
	}



	public void setSpeed(float speedX, float speedY){
		this.speedX = speedX;
		this.speedY = speedY;
		
//		if(this.speedX < 0)
//			speedX = 0.1f;
//		if(this.speedX > 10)
//			speedX = 10f;
//		
//		if(this.speedY < 0)
//			speedY = 0f;
//		if(this.speedY > 4)
//			speedY = 4f;
//		
//		Helper.println("SpeedX: " + speedX);
//		Helper.println("SpeedY: " + speedY);
		
	}
	public void changeSpeed(float dx, float dy)
	{
		if(Math.abs(speedX) < Constants.carMaxSpeedX)
			speedX += dx;
		if(Math.abs(speedY) < Constants.carMaxSpeedY)
			speedY += dy;
		
		if(speedX < 0)
			speedX = 0;
		
//		Helper.println("\n\nchange SpeedX: " + speedX);
//		Helper.println("change SpeedY: " + speedY);
	}
	
	
	@Override
	public void init() {
		this.image = Helper.getImageFromAssets(AssetConstants.IMG_CAR_RED);
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_CAR_RED);
	}

	public float getUpperBoundaryY() {
		return upperBoundaryY;
	}



	public void setUpperBoundaryY(float upperBoundaryY) {
		this.upperBoundaryY = upperBoundaryY;
	}



	public float getLowerBoundaryY() {
		return lowerBoundaryY;
	}



	public void setLowerBoundaryY(float lowerBoundaryY) {
		this.lowerBoundaryY = lowerBoundaryY;
	}



	public float getSpeedX() {
		return speedX;
	}



	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}



	public float getSpeedY() {
		return speedY;
	}



	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}


	public float getTotalDistance() {
		return totalDistance;
	}


	public void setTotalDistance(float totalDistance) {
		this.totalDistance = totalDistance;
	}


	@Override
	public void onCollision(ICSingleCollisionCallbacks collider) {
		if(collider instanceof Car)
		{
//			Helper.println("Car Collided with car");
			
			if(this.x < ((ElementBase)collider).getX())
			{
				this.x -= stepHitX;
				((Car)collider).setX(((Car)collider).getX() + stepHitX);
			}
			else
			{
				this.x += stepHitX;
				((Car)collider).setX(((Car)collider).getX() - stepHitX);
			}
			
			if(this.y < ((ElementBase)collider).getY())
			{
				this.y -= stepHitX;
				((Car)collider).setY(((Car)collider).getY() + stepHitX);
			}
			else
			{
				this.y += stepHitX;
				((Car)collider).setY(((Car)collider).getY() - stepHitX);
			}
		}
//		collided = true;
	}

//	boolean collided = false;
	@Override
	public boolean isCollided() {
		return false;
	}

}
