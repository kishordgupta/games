package com.rhymes.attackTheFortress;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Texture;
import com.rhymes.game.data.AssetConstants; //import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Bullet extends ElementBase {
	private float sourseX = 0;
	private float sourseY = 0;
	private float destinationX = 0;
	private float destinationY = 0;
	private int bulletType = -1;
	private int bulletdamage = 0;
	private int bulletSpeed = 0;
	private Texture tex;
	private LinkedList<Float> listX = new LinkedList<Float>();
	private LinkedList<Float> listY = new LinkedList<Float>();
	private IBulletHit enemy;

	public int getDamage() {
		return this.bulletdamage;
	}

	public Bullet(float soursex, float soursey, float destx, float desty,
			int type, int damage, int speed, ElementBase enemy) {
		super.x = this.sourseX = soursex;
		super.y = this.sourseY = soursey;
		this.destinationX = destx;
		this.destinationY = desty;
		this.bulletType = type;
		this.bulletSpeed = speed;
		this.bulletdamage = damage;
		this.enemy = (IBulletHit) enemy;
	}

	public Bullet(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

	@Override
	public void render() {
		GlobalVars.ge.getScreen().getBatch().draw(tex, this.x, this.y,
				this.width, this.height, 0, 0, tex.getWidth(), tex.getHeight(),
				false, false);
//		super.render();
	}

	@Override
	public void getAssets(AssetPack assetPack) {
	}

	@Override
	public void init() {

		super.width = this.width = 20f * LevelInfo.ratioX;
		super.height = this.height = 20f * LevelInfo.ratioY;
		Helper.println("Bullet Type: " + bulletType, true);
		if (this.bulletType == 1)
		{
			this.tex = Helper.getImageFromAssets(AssetConstants.IMG_BULLET_1)
					.getTexture();
			this.image=Helper.getImageFromAssets(AssetConstants.IMG_BULLET_1);
		}
		else if (this.bulletType == 2)
		{
			this.tex = Helper.getImageFromAssets(AssetConstants.IMG_BULLET_2)
			.getTexture();
			this.image=Helper.getImageFromAssets(AssetConstants.IMG_BULLET_2);
		}
		else if (this.bulletType == 3)
		{
			this.tex = Helper.getImageFromAssets(AssetConstants.IMG_BULLET_3)
					.getTexture();
			this.image=Helper.getImageFromAssets(AssetConstants.IMG_BULLET_3);
		}
		else if (this.bulletType == 4)
		{
			this.tex = Helper.getImageFromAssets(AssetConstants.IMG_BULLET_4)
					.getTexture();
			this.image=Helper.getImageFromAssets(AssetConstants.IMG_BULLET_4);
		}
		
		else if (this.bulletType == 5)
		{
			this.tex = Helper.getImageFromAssets(AssetConstants.IMG_BULLET_5)
					.getTexture();
			this.image=Helper.getImageFromAssets(AssetConstants.IMG_BULLET_5);
		}
		else if (this.bulletType == 6)
		{
			this.tex = Helper.getImageFromAssets(AssetConstants.IMG_BULLET_6)
					.getTexture();
			this.image=Helper.getImageFromAssets(AssetConstants.IMG_BULLET_6);
		}
//		setPoints();
		dstPoint.setLocation(destinationX, destinationY);
		pathDistance = this.getLocation().distancePoint2Point(dstPoint);
		stepDistance = this.bulletSpeed/1.5f;
//		Helper.println("Step Distance: " + stepDistance + " of " + enemy.getClass(), true);
//		Helper.println("Bullet Speed: " + bulletSpeed+ " of " + enemy.getClass(), true );
//		Helper.println("Path distance: " + pathDistance+ " of " + enemy.getClass(), true );
	}

	Point dstPoint = new Point();
	private int Z = 0;

	float pathDistance = 0;
	float curDistance = 0;
	float stepDistance;
	Point curPoint = new Point();
	@Override
	public void step(long stepTime) {
//		if (Z >= listX.size()) {
//			Destroy();
//			return;
//		}

		curDistance += stepDistance;

		
//		Helper.println("Bullet stepping: " + curDistance + " of " + enemy.getClass(), true);
		this.getLocation().
				getPointAtDistance(dstPoint, curPoint, stepDistance);
		
		if(curDistance>pathDistance)
		{
			enemy.onBulletHit(this);
			Destroy();
		}
		
		this.setLocation(curPoint);
//		this.x = listX.get(Z);
//		this.y = listY.get(Z);
//		Z++;
		super.step(stepTime);
	}

	public float getDistance(float pX, float pY, float qX, float qY) {
		float distance = 0;
		distance = (float) Math.sqrt(((qX - pX) * (qX - pX))
				+ ((qY - pY) * (qY - pY)));
		return distance;
	}

	public void setPoints() {
		float distance = getDistance(this.sourseX, this.sourseY,
				this.destinationX, this.destinationY);
		int N = (int) (distance / this.bulletSpeed);
		for (int i = 0; i < N; i++) {
			float X = (i * this.destinationX + N * this.sourseX) / N;
			float Y = (i * this.destinationY + N * this.sourseY) / N;
			listX.addLast(X);
			listY.addLast(Y);
			N--;
		}
	}

	public void Destroy() {
		((AttackTheFortressLevel) GlobalVars.ge.getCurrentStage())
				.postDestroyed(this);
	}

	public void onCollission() {
		Destroy();
	}
}
