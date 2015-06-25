package com.rhymes.game.entity.elements.solitaire.cards;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.solitaire.ConfigConstants;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Card extends ElementBase implements InteractionTouchCallbacks {

	private int card_id;
	private int global_card_id;
	private boolean visible;
	private boolean touched;
	
	private TextureRegion card_front_image;
	public boolean selected = false;
	
	public float pX, pY;

	public Card(int card_id, int global_id, boolean visibility) {
		super();
		setCard_id(card_id);
		setGlobal_card_id(global_id);
		set_visible(visibility);
	}

	@Override
	public void getAssets(AssetPack assetPack) {

	}
	

	@Override
	public void init() {
		setImage(Helper.getImageFromAssets(AssetConstants.CARD_BACK_4));
		setWidth(ConfigConstants.card_width * ConfigConstants.ratio_w);
		setHeight(ConfigConstants.card_height * ConfigConstants.ratio_h);
		setzIndex(1);
		
	}

	
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
		
	}

	
	
	@Override
	public void onEvent(Point hitPoint) {
		if(checkHit(hitPoint)){
			
			set_touched(true);
		}
	}
	
	public float getScreenX()
	{
		return x-width/2f;
	}
	
	public float getScreenY()
	{
		return y-height/2f;
	}
	
	
	@Override
	public void render() {	
		if(!is_visible())
			GlobalVars.ge.getRenderer().render(image, x-width/2f, y-height/2f, width, height);

		else {
			if(selected)
				GlobalVars.ge.getScreen().getBatch().setColor(0.8f, 0.8f, 1, 1f);
			GlobalVars.ge.getRenderer().render(card_front_image, x-width/2f, y-height/2f, width, height);
			GlobalVars.ge.getScreen().getBatch().setColor(1, 1, 1, 1f);
		}
	}
	

	public void renderFromStack() {
		if(!is_visible())
			GlobalVars.ge.getRenderer().render(image, x-width/2f, y-height/2f, width, height);

		else {
			if(selected)
				GlobalVars.ge.getScreen().getBatch().setColor(0.8f, 0.8f, 1, 1f);
			GlobalVars.ge.getRenderer().render(card_front_image, x-width/2f, y-height/2f, width, height);
			GlobalVars.ge.getScreen().getBatch().setColor(1, 1, 1, 1f);
		}
	}
	
	protected boolean checkHit(Point p){
//		Helper.println("\n\nChecking hit point..." + p + " for: " + this.getClass());
//		Helper.printKeyVal("x", this.x);
//		Helper.printKeyVal("r", this.getRight());
//		
//		Helper.printKeyVal("y", this.y);
//		Helper.printKeyVal("t", this.getTop());
		
		
		if(this.getLeft() <= p.x && this.getRight()>= p.x && this.getTop() >= p.y && this.getBottom() <= p.y){
			return true;
		}
		
		return false;
	}
	

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

	public int getCard_id() {
		return card_id;
	}

	public void set_visible(boolean is_visible) {
		this.visible = is_visible;
	}

	public boolean is_visible() {
		return visible;
	}

	public void set_touched(boolean is_touched) {
		this.touched = is_touched;
	}

	public boolean is_touched() {
		return touched;
	}

	public void setGlobal_card_id(int global_card_id) {
		this.global_card_id = global_card_id;
	}

	public int getGlobal_card_id() {
		return global_card_id;
	}

	public void setCard_front_image(TextureRegion card_front_image) {
		this.card_front_image = card_front_image;
	}

	public TextureRegion getCard_front_image() {
		return card_front_image;
	}
}
