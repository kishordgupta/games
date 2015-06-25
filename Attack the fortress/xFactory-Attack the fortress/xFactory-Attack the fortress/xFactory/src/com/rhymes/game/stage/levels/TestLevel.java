package com.rhymes.game.stage.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.PhysicalWorld;
import com.rhymes.game.entity.elements.pinball.BalanceHero;
import com.rhymes.game.entity.elements.pinball.BalancePath;
import com.rhymes.game.entity.elements.pinball.Ball;
import com.rhymes.game.entity.elements.pinball.BottomBar;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.pinball.ILeftRight;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;

public class TestLevel extends StageBase {
	public PhysicalWorld physicalWorld;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		return null;
	}

	public Ball ball;
	@Override
	public void loadElements() {
		physicalWorld = new PhysicalWorld();
		
		ILeftRight iLeftRight = new ILeftRight();
		InteractionTouch it = new InteractionTouch();
		this.interactions.add(iLeftRight);
		this.interactions.add(it);

		addElement(new Background(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BACK_2));
		
		ball = new Ball(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 32, 32, 1);
		addElement(ball);
		
//		addElement(new Ball(100, 100, 30, 30, 1));
//		addElement(new Ball(200, 200, 30, 30, 1));
//		addElement(new Ball(200, 300, 30, 30, 1));
		
//		BottomBar bottomBar = new BottomBar(0, 0, 90, 103, 1);
//		addElement(bottomBar);
		
//		BalancePath balancePath = new BalancePath(Gdx.graphics.getWidth()/2-10, 50, 20, Gdx.graphics.getHeight()-40, 1);
//		addElement(balancePath);
//		
//		BalanceHero balanceHero = new BalanceHero(Gdx.graphics.getWidth()/2-50, Gdx.graphics.getHeight()/6f-10, 100, 20, 1);
//		addElement(balanceHero);
		
//		iLeftRight.subscribe(balanceHero);
//		iLeftRight.subscribe( bottomBar );
	}

	@Override
	public void tick(long stepTime) {
		this.physicalWorld.applyLinearImpulse(new Vector2(10,10));
		this.physicalWorld.update(stepTime);		
//		ball.setX(ball.getX()+10);
		
	}

}

