package com.rhymes.game.entity.elements.nonphysical;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.game.interactions.inputs.InteractionTouchCompasCallbacks;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.helpers.Helper;

public class Arrow extends ElementBase implements InteractionTouchCompasCallbacks {

	public Arrow() {
		// TODO Auto-generated constructor stub
	}

	public Arrow(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public Arrow(float x, float y, float width, float height, float angle,int zIndex) {
		super(x, y, width, height, zIndex);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rotateAngle = angle;
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub
		assetPack.addTexture(AssetConstants.IMG_ARROW);
	}
	@Override
	public void render() {
		if(this.showArrow)
			GlobalVars.ge.getRenderer().render
			(this.image, x, y-height/2f, width, height, 0, height/2f, rotateAngle, 1, 1);
		
	}
	@Override
	public void init() {
		this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_ARROW));
		
	}

	
	boolean isTouched = false;
	public boolean showArrow = false;
	Point p = new Point();
	Point q = new Point();
	@Override
	public void onTouch(Point hitPoint) {
		if(GlobalVars.ge.getCurrentStage() instanceof BounceTest){
			if(!((BounceTest)GlobalVars.ge.getCurrentStage()).startNow)
				return ;
		}
		if(((BounceTest)GlobalVars.ge.getCurrentStage()).shotOngoing )
			return;
		this.showArrow = true;
//		p.setLocation(hitPoint.x / GameMenuInfo.ratio_w, hitPoint.y / GameMenuInfo.ratio_h);
		this.setLocation(((BounceTest)GlobalVars.ge.getCurrentStage()).getBall().getRenderLocation());
		this.setRotateAngle(Helper.getAngle(this.getLocation(),
				hitPoint) + 90);
		
		
		
		width = hitPoint.distancePoint2Point(((BounceTest)GlobalVars.ge.getCurrentStage()).getBall().getRenderLocation());
//		this.setRotateAngle(0);
		
//		Helper.printKeyVal("Arrow angle: ", rotateAngle);
//		Helper.printKeyVal("Width: ", width);
	}

	private boolean checkHit(Point p) {
		// TODO Auto-generated method stub
			if((this.getLeft()+100) <= p.x && (this.getRight()+400)>= p.x && (this.getTop()+300) >= p.y && (this.getBottom()+200) <= p.y)
			{				
//			Helper.println("elementleft"+this.getLeft()+"hitX"+p.x+"top"+this.getTop()+"hitY"+p.y+"bottom::"+this.getBottom());
				return true;
			}
			else
			return false;
	}
	}



