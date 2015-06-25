package com.rhymes.attackTheFortress.button;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rhymes.attackTheFortress.AttackTheFortressLevel;
import com.rhymes.attackTheFortress.LevelInfo;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonSellTower extends Button {

	public Text sellText;
	public String info="";
	public BitmapFont font;
	public int totalValue=0;
	public int sellValue=0;
//	public int towertype=-1;

	public ButtonSellTower(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonSellTower(float x, float y, float width, float height,
			int zIndex, String imagePath, BitmapFont font) {
		super(x, y, width, height, zIndex, imagePath);
		if(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower() != null)	
		{
//			towertype=(int) ((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().getTowerType();
			totalValue=(int) ((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().getTotalValue();
//			Helper.println("Type "+towertype);
		}
		this.font=font;
	}
	@Override
	public void init() {
		sellValue=(int) (totalValue*0.75);
		info="sell tower at "+sellValue+"G";
		testString();
		super.init();
	}

	@Override
	public void onEvent(Point htiPoint) {
		if(this.checkHit(htiPoint)){
			totalValue=(int) ((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().getTotalValue();
			sellValue=(int) (totalValue*0.75);
			((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().destroy();
			((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).setGold(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getGold()+sellValue);
		Helper.println("btnUpgrade: Sell");	
		}
	}

	public void testString(){
		sellText=new Text(this.x+20f*LevelInfo.ratioX,this.y+9f*LevelInfo.ratioY,	0.2f*LevelInfo.ratioX, 0.2f*LevelInfo.ratioY, this.font, info);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(sellText);
	}
	public void Destroy(){
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(this);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(sellText);
	}
	public void setInfo(int k){
		sellValue +=(int)(k*0.75f);
		sellText.setText("sell tower at "+sellValue+"G");
	}
}
