package com.rhymes.ge.core.entity.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.states.StateBase;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public abstract class ElementBase {
	private static long GLOBAL_ID = 0;
	private long id;
	
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected int zIndex;
	protected float rotateAngle;

	protected TextureRegion image;
	protected StateBase state;
	
	protected Point point = new Point();
	
	public ElementBase()
	{
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.zIndex = 1;
		this.id = GLOBAL_ID++;
		this.point.y = this.point.x = 0;
	}
	public ElementBase(float x, float y, float width, float height, int zIndex) {
		this.x = x;
		this.y = y;
		this.point.x = x;
		this.point.y = y;
		this.width = width;
		this.height = height;
		this.zIndex = zIndex;
		this.id = GLOBAL_ID++;
	}
	
	public abstract void init();

	
	public float getRotateAngle() {
		return rotateAngle;
	}

	public void setRotateAngle(float rotateAngle) {
		this.rotateAngle = rotateAngle;
	}
	
	public float getLeft()
	{
		return getX();
	}
	
	public float getRight()
	{
		return getX() + getWidth();
	}
	
	public float getTop()
	{
		return getY() + getHeight();
	}
	
	public float getBottom()
	{
		return getY();
	}
	
	//added by asad
	public float getOriginX() {
		return x/2;
	}

	public void setOriginX(float x) {
		this.x = x;
		this.point.x = x;
	}
	public float getOriginY() {
		return y/2;
	}

	public void setOriginY(float y) {
		this.y = y;
		this.point.y = y;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
		this.point.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
		this.point.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getzIndex() {
		return zIndex;
	}

	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	public TextureRegion getImage() {
		return image;
	}

	public void setImage(TextureRegion image) {
		this.image = image;
	}
	
	public StateBase getState() {
		return state;
	}

	public void setState(StateBase state) {
		this.state = state;
	}
	
	/**
	 * @param Draws image using x, y, width, height of this Element 
	 */
	public void render()
	{
		GlobalVars.ge.getRenderer().render(this);
	}
	
	public abstract void getAssets(AssetPack assetPack);

	public void step(long stepTime){
		if(this.state != null)
			this.state.step(stepTime);
	}

	public void setSize(float width, float height) {
		this.width = width;
		this.height = height;
	}

	public Point getLocation() {
		point.x = x;
		point.y = y;
		return point;
	}
	public void setLocation(float x, float y) {
		if(this.point == null)
			this.point = new Point();
		this.setX(x);
		this.setY(y);
		this.point.x = x;
		this.point.y = y;
	}
	public void setLocation(Point point) {
		this.setX(point.x);
		this.setY(point.y);
		this.point.x = point.x;
		this.point.y = point.y;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	
	public void resume()
	{
		
	}
}