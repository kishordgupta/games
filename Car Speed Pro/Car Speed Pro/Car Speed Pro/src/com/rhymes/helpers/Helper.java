package com.rhymes.helpers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;

public class Helper {
	
	private static HashMap<String, TextureRegion> textureCache = new HashMap<String, TextureRegion>();
	
	public static void printTest(String s){
//		System.out.println(s);
	}
	
	public static void println(String s){
//		System.out.println(s);
	}
	
	public static void printKeyVal(String key, String val){
		if(StartupInfo.debugLevel < 2)
			return;
		System.out.println(formatStringKeyVal(key, val));
	}

	public static void printKeyVal(String key, double val){
		if(StartupInfo.debugLevel < 2)
			return;
		System.out.println(formatStringKeyVal(key, val+""));
	}
	
	public static String formatStringKeyVal(String key, String val){
		if(StartupInfo.debugLevel < 2)
			return null;
		return new String("" + key + ": " + val);
	}
	
	public static float getAngle(float x1, float y1, float x2, float y2)
	{
		float angle =(float) Math.atan((y2 - y1)
				/ (x2-x1))
				* 180 / (float) Math.PI -90;
		if(x2-x1 < 0)
			angle += 180;
		return angle;
	}
	
	
	public static double pointToLineDistance(Point A, Point B, Point hitPoint)
	{
		 double normalLength = Math.sqrt((B.x - A.x) * (B.x - A.x) + (B.y - A.y) * (B.y - A.y));
		 return Math.abs((hitPoint.x - A.x) * (B.y - A.y) - (hitPoint.y - A.y) * (B.x - A.x)) / normalLength;
	}
	
	
	public static double pointToPointDistance(Point A, Point B)
	{
		 double normalLength = Math.sqrt((B.x - A.x) * (B.x - A.x) + (B.y - A.y) * (B.y - A.y));
		 return normalLength;
	}
		
	public static boolean insideRegion(Point p, ElementBase e2)
	{
		if(p.x <= e2.getRight() && p.x >=e2.getLeft())
		{
			return true;
		}
		return false;
	}

	public static float getAngle(Point p, Point n) {
		return getAngle(p.x, p.y, n.x, n.y);
	}
	
	public static boolean checkHit(Point p, ElementBase e)
	{
		if(e.getLeft() <= p.x && e.getRight()>= p.x && e.getTop() >= p.y && e.getBottom() <= p.y)
			return true;
		return false;
	}
	
	static TextureRegion image;
	public static TextureRegion getImageFromAssets(String imageConstant)
	{

		image = textureCache.get(imageConstant);
		if(image == null){
			try {
				image = new TextureRegion(GlobalVars.ge.getAssetHandler().getTexture(
						imageConstant));
			} catch (Exception e) {
//				e.printStackTrace();
				return null;
			} 
			textureCache
				.put(imageConstant, image);
		}
		return image;
	}
	
	public static TextureRegion getImageFromAssets(String imageConstant, String imagePath)
	{

		image = textureCache.get(imageConstant);
		if(image == null){
			try {
				image = new TextureRegion(GlobalVars.ge.getAssetHandler().getTexture(
						imagePath));
			} catch (Exception e) {
//				e.printStackTrace();
				return null;
			} 
			textureCache
				.put(imageConstant, image);
		}
		return image;
	}
	
	
	public static float w2p(float w)
	{
		return w/32;
//		return w;
	}
	
	public static float p2w(float p)
	{
		return p*32;
//		return p;
	}
	public static void disposeTextures()
	{
		for(TextureRegion t:textureCache.values())
			t.getTexture().dispose();
		GlobalVars.ge.getAssetHandler().clearAssets();
		textureCache.clear();
	}
	
	public static float getScreenWidth() {		
		return Gdx.graphics.getWidth();
	}

	public static float getScreenHeight() {		
		return Gdx.graphics.getHeight();
	}

	static Point p = new Point(), q = new Point();
	public static double pointToPointDistance(Vector2 vector1, Vector2 vector2) {
		p.x = vector1.x;
		p.y = vector1.y;
		q.x = vector2.x;
		q.y = vector2.y;
		return pointToPointDistance(p,q);
	}

	
	public static void addAssetsIterative(
			AssetPack assetPack, String folderPath, int startIndex, int endIndex, String suffix)
	{
		for(int i = startIndex; i<=endIndex; i++)
			assetPack.addTexture(folderPath + i + suffix);
	}

	public static float getCameraBottom()
	{
		return GlobalVars.ge.getScreen().cam.position.y - Gdx.graphics.getHeight()/2f;
	}
	public static float getCameraLeft()
	{
		return GlobalVars.ge.getScreen().cam.position.x - Gdx.graphics.getWidth()/2f;
	}
	public static float getCameraTop()
	{
		return GlobalVars.ge.getScreen().cam.position.y + Gdx.graphics.getHeight()/2f;
	}
	public static float getCameraRight()
	{
		return GlobalVars.ge.getScreen().cam.position.x + Gdx.graphics.getWidth()/2f;
	}
}
