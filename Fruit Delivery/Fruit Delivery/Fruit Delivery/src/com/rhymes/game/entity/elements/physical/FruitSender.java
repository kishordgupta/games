
package com.rhymes.game.entity.elements.physical;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.PhysicsBody2;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;


public class FruitSender extends ElementBase {


	private Body fruitSender;
	FixtureDef fixtureDef;
	private World world;
	private TextureRegion imageFruitConsumer;
	private Boolean startGenarate = true;
	private Body fruitPath;
	private float pathwidth=250f;
	private float pathAngle= MathUtils.degreesToRadians*-38f;
	private int zindexpath;

	public FruitSender() {
		// TODO Auto-generated constructor stub
	}


	public FruitSender(World world,float x, float y, float width, float height, int zIndex,int zindexpath) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
		this.world = world;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.zIndex = zIndex;
		this.zindexpath = zindexpath;
		this.createFruitSender();
		
	}

	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.imageFruitConsumer = Helper.getImageFromAssets(AssetConstants.PHY_IMG_CONSUMER);
	}

	@Override
	public void step(long stepTime) {
		// TODO Auto-generated method stub
//		super.step(stepTime);
		



	}


	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub
		assetPack.addTexture(AssetConstants.PHY_IMG_SENDER);
		assetPack.addTexture(AssetConstants.PHY_IMG_SENDER_PATH1);
		
	}
	
	@Override
	public void render() {
		if (startGenarate) {   
			// TODO Auto-generated method stub
			//		super.render();
//			for(int i = 0; i< fruits.size();i++)
//			{
//				fruitConsumer = fruits.get(i);
//			this.update(Gdx.graphics.getDeltaTime());
//			GlobalVars.ge.getRenderer().render(imageFruitConsumer, PhysicsHelper.ConvertToWorld(fruitConsumer.getPosition().x), PhysicsHelper.ConvertToWorld(fruitConsumer.getPosition().y),
//					GameMenuInfo.ratio_w * this.width,
//					GameMenuInfo.ratio_h * this.height,
//					GameMenuInfo.ratio_w * this.width / 2,
//					GameMenuInfo.ratio_w * this.height / 2,
//					(MathUtils.radiansToDegrees * fruitConsumer.getAngle()), 1, 1);
//			startGenarate = false;
//			}
			       }
	}


//int i =0;



	private void createFruitSender()
	{
		
		PhysicsBody2 pb2 = new PhysicsBody2((this.x+50),
				(this.y+390),
				(pathwidth),
				01f,
				AssetConstants.PHY_SENDER_PATH,
				AssetConstants.PHY_IMG_SENDER_PATH1,
				"fruitpath", 
				world,
				((short) -1),
				((short) 24),
				((short) (22)),    /// here 1 == carbody; 11== ground 15==rope // 20 = fruit
				"fruitpath",zindexpath,
				BodyType.StaticBody);
		
//		GlobalVars.ge.getCurrentStage().addElementZSorted(pb2);
		GlobalVars.ge.getCurrentStage().addElement(pb2);
		pb2.getBody().setTransform(pb2.getBody().getPosition(),
				pb2.getBody().getAngle() + pathAngle);
		fruitPath = pb2.getBody();
		fruitPath.setUserData("fruitpath");
		fruitPath.setActive(true);
		
		
		
		
		PhysicsBody2 pb = new PhysicsBody2((this.x),
				(this.y),
				(width),
				01f,
				AssetConstants.PHY_SENDER ,
				AssetConstants.PHY_IMG_SENDER,
				"sender", 
				world,
				((short) -2),
				((short) 23),
				((short) (22)),    /// here 1 == carbody; 11== ground 15==rope // 20 = fruit
				"sender",zIndex,
				BodyType.StaticBody);
		
//		GlobalVars.ge.getCurrentStage().addElementZSorted(pb);
		GlobalVars.ge.getCurrentStage().addElement(pb);
		fruitSender = pb.getBody();
		fruitSender.setUserData("sender");
		fruitSender.setActive(true);
		
		

		
		
		
		
		
		
	
	}
	

    
    public void update(float dt){
		    
    }

}
