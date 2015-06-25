package com.rhymes.game.entity.elements.nonphysical;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.helpers.Helper;

public class PlayerAnimation extends AnimationBase {
	
	private int ctlFlag = 4  ;
	private TextureRegion[] images;
	public static final int ANI_NORMAL = 0;
	public static final int ANI_THROW = 1;
	public static final int ANI_END = 2;

	public PlayerAnimation(ElementBase element) {
		super(element);
		// TODO Auto-generated constructor stub
	}
	public PlayerAnimation(Player element,int ctlFlag, TextureRegion[] images) {
		super(element);
		this.ctlFlag = ctlFlag;
		this.images = images;
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}
	public void init(int ctlFlag2) {
		// TODO Auto-generated method stub
		this.ctlFlag = ctlFlag2;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void step(long stepTime) {
		
		this.ctlFlag = ((Player)element).getCtlFlag();	

			if(this.ctlFlag == ANI_NORMAL)
			{
	
				Player.setCtlFlag(0);
			}
			if(this.ctlFlag == ANI_THROW)
			{
				Player.setCtlFlag(1);
			
				
			}
			if(this.ctlFlag == ANI_END)
			{
				Player.setCtlFlag(2);
			
			}
			if(this.ctlFlag == 4)
			{
				Player.setCtlFlag(4);
			}
					
//			com.rhymes.ge.core.renderer.Point p = this.getElement().getLocation();
//		
//			this.getElement().setLocation(p);
	

	
	}

}
