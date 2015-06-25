package com.rhymes.attackTheFortress.button;

import com.rhymes.attackTheFortress.AttackTheFortressLevel;
import com.rhymes.attackTheFortress.Blow;
import com.rhymes.attackTheFortress.Bullet;
import com.rhymes.attackTheFortress.Enemy;
import com.rhymes.attackTheFortress.LevelInfo;
import com.rhymes.attackTheFortress.xmlEnemyReader;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;

public class ButtonPlayLevel extends Button {

	public ButtonPlayLevel(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonPlayLevel(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		// TODO Auto-generated constructor stub
	}
public Enemy enemy;
public Bullet bullet;
public Blow blow;
	@Override
	public void onEvent(Point htiPoint) {
		if(this.checkHit(htiPoint)){
			((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).setCreateeEnemy(true);
//			((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).notice.Destroy();
					}
		super.onEvent(htiPoint);
	}

}
