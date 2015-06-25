package com.rhymes.attackTheFortress.button;

import com.rhymes.attackTheFortress.AttackTheFortressLevel;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonPause extends Button {
public static boolean isPlay = true;
private String imagePath="";
	public ButtonPause(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonPause(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		this.imagePath = imagePath;
	}
	public String setImagePath(boolean active){
		if(active)
			this.imagePath = AssetConstants.IMG_BTN_GAME_PAUSE;
		
		else
			this.imagePath = AssetConstants.IMG_BTN_GAME_PLAY;
		
		return imagePath;
		
	}
//	private ButtonResume resume;
//	public ButtonResume getButtonResume(){
//		return resume;
//	}
	@Override
 	public void onEvent(Point p) {
		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
//			this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_BTN_BTNSTART_DOWN));
			Helper.println("pause game...");
			if(isPlay){
				((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).setPaused(true);
				GlobalVars.ge.getCurrentStage().pause();
				this.setImage(Helper.getImageFromAssets(setImagePath(false)));
				isPlay=false;
			}
			else{
				GlobalVars.ge.getCurrentStage().resume();
				((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).setPaused(false);
				this.setImage(Helper.getImageFromAssets(setImagePath(true)));
				isPlay=true;
			}
		}
	}


}
