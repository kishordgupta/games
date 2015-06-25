
package com.rhymes.game.entity.elements.physical;

import java.util.ArrayList;
import java.util.Random;

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
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.game.stage.menus.stick.ScoreManage;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;


public class Fruits extends ElementBase {


	private Body fruit;
	FixtureDef fixtureDef;
	private World world;
	private TextureRegion imageFruit;
	private Boolean startGenarate = false;
	private Boolean isInGround = false;
	private Boolean isInTruck = false;
	public long currentTruckedFruit = 0;
	public long totalTruckedFruit = 0;
	
	private float density= 0.1f;
	private float friction= 0.01f;
	private float restitution=0.0f;

	private float demotime;
	public ArrayList<Body> fruits = new ArrayList<Body>();
	private ArrayList<Body> groundedFruit = new ArrayList<Body>();
	private ArrayList<Body> truckedFruit = new ArrayList<Body>();
	private int fruitCount = 20;




	public Fruits() {
		// TODO Auto-generated constructor stub
	}


	public Fruits(World world,float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.zIndex = zIndex;
		this.world = world;
//		this.createFruit();
	}
	

	int j=0;
	Random random = new Random();
	
	public void startGenaration()
	{
//		random.nextInt(06);
		if(  j < fruitCount)
		{
//				Helper.println("Starting genarateing fruit no. :::"+(random.nextInt(06)+1));
//				if((random.nextInt(06)+1)==1)
				
				this.createFruit((random.nextInt(05)+1));
//				this.createFruit(3);
				j++;
		}
	}

	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.imageFruit = Helper.getImageFromAssets(AssetConstants.IMG_AXLE);
//		 demotime =  05;
	}

	@Override
	public void step(long stepTime) {
		// TODO Auto-generated method stub
//		super.step(stepTime);
		



	}


	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub
		assetPack.addTexture(AssetConstants.IMG_AXLE);
		assetPack.addTexture(AssetConstants.PHY_IMG_FRUIT1);
	}
	
	@Override
	public void render() {
//		if (startGenarate) {   
			// TODO Auto-generated method stub
			//		super.render();
//			for(int i = 0; i< fruits.size();i++)
//			{
//				fruit = fruits.get(i);
////			this.update(Gdx.graphics.getDeltaTime());
//			GlobalVars.ge.getRenderer().render(imageFruit, PhysicsHelper.ConvertToWorld(fruit.getPosition().x), PhysicsHelper.ConvertToWorld(fruit.getPosition().y),
//					GameMenuInfo.ratio_w * this.width,
//					GameMenuInfo.ratio_h * this.height,
//					GameMenuInfo.ratio_w * this.width / 2,
//					GameMenuInfo.ratio_w * this.height / 2,
//					(MathUtils.radiansToDegrees * fruit.getAngle()), 1, 1);
////			Helper.println("fruit isngenerated at postion:::"+ (PhysicsHelper.ConvertToWorld(fruit.getPosition())));
////			startGenarate = false;
//			}
//			       }
	}
public Boolean getIsInGround() {
		return isInGround;
	}


	public void setIsInGround(Boolean isInGround) {
		this.isInGround = isInGround;
	}
	
	public Boolean getIsInTruck() {
		return isInTruck;
	}


	public void setIsInTruck(Boolean isInTruck) {
		this.isInTruck = isInTruck;
	}

	public ArrayList<Body> getGroundedFruit() {
		return groundedFruit;
	}


	public void setGroundedFruit(ArrayList<Body> groundedFruit) {
		this.groundedFruit = groundedFruit;
	}

	public int getFruitCount() {
		return fruitCount;
	}


	public void setFruitCount(int fruitCount) {
		this.fruitCount = fruitCount;
	}
	
	
int i =0;




	private void createFruit(int fruitNo)
	{
		String binPath=null;
		String imgPath=null;
		String fixturePath=null;
		
			if(fruitNo ==1)
			{
				binPath	= AssetConstants.PHY_FRUIT1;
				imgPath = AssetConstants.PHY_IMG_FRUIT1;
				fixturePath ="fruit1";
			}
			else if(fruitNo ==2)
			{
				binPath	= AssetConstants.PHY_FRUIT2;
				imgPath = AssetConstants.PHY_IMG_FRUIT2;
				fixturePath ="fruit2";
			}
			else if(fruitNo ==3)
			{
				binPath	= AssetConstants.PHY_FRUIT3;
				imgPath = AssetConstants.PHY_IMG_FRUIT3;
				fixturePath ="fruit3";
			}
			else if(fruitNo ==4)
			{
				binPath	= AssetConstants.PHY_FRUIT4;
				imgPath = AssetConstants.PHY_IMG_FRUIT4;
				fixturePath ="fruit4";
			}
			else if(fruitNo ==5)
			{
				binPath	= AssetConstants.PHY_FRUIT5;
				imgPath = AssetConstants.PHY_IMG_FRUIT5;
				fixturePath ="fruit5";
			}

			PhysicsBody2 pb = new PhysicsBody2((this.x),
					(this.y),
					(width),
					01f,
					binPath,//AssetConstants.PHY_FRUIT1 ,
					imgPath,//AssetConstants.PHY_IMG_FRUIT1,
					fixturePath,//"fruit1", 
					world,
					density,
					friction,
					restitution,
					((short) -2),
					((short) 20),
					((short) (22|11|15|1|20)),    /// here 1 == carbody; 11== ground 15==rope // 22 = fruitconsumer
					fixturePath,zIndex,
					BodyType.DynamicBody);
//			GlobalVars.ge.getCurrentStage().addElement(pb);
			GlobalVars.ge.getCurrentStage().addElementZSorted(pb);
			
			fruit = pb.getBody();
			fruit.setUserData("fruit");
			fruit.setActive(true);
			fruits.add(fruit);
	
	}
	
	
	public void destroyFruits()
	{
	 	if(isInGround&&fruit!=null)
		{
//			world.destroyBody(fruits.get(0));
	 		fruits.remove(fruits.size()-1);
	 		
//	 		((BikeLevel) GlobalVars.ge.getCurrentStage()).getWorld().destroyBody(fruits.get(fruits.size()-1));
//	 		((BikeLevel) GlobalVars.ge.getCurrentStage()).getWorld().destroyBody(fruit);
	 		
	 		//	            fruit.setUserData(null);
//	            fruit = null;
//			fruits.remove(0);

	 		groundedFruit.add(fruit);
	 		Helper.println("no of grounded fruit is==="+ (groundedFruit.size()));
	 		this.setIsInGround(false);
		}
	}
    
    public void update(float dt){
    }

    private int k = 0;
	public void consumFruit() {		
			k = fruits.size()-1;
	}

	public void continueConsumeFruit() {
		k = Math.min(k, fruits.size()-1);
		Helper.println("k: " + k);
		for(int j = 0; j <= k; j++){
//			fruits.get(j).setUserData("Nothing");
			if(fruits.get(j).getUserData() == "truckedfruit")
				fruits.get(j).setTransform(fruits.get(j).getPosition().x, fruits.get(j).getPosition().y + 0.5f, 0);
		}
	}
	
	public boolean isConsumptionFinished()
	{
		boolean finished = true; 
		for(int i = 0; i < GlobalVars.ge.getCurrentStage().getElements().size; i++)
			
//			zSortedElements.get(i).size=;
			if(GlobalVars.ge.getCurrentStage().getElements().get(i) instanceof PhysicsBody2)
			{
//				Helper.printTest("Fruit userdata : " + (((PhysicsBody2)GlobalVars.ge.getCurrentStage().getElements().get(i)).getBody().getUserData()));
				if(((String)(((PhysicsBody2)GlobalVars.ge.getCurrentStage().getElements().get(i)).getBody().getUserData())).compareTo("truckedfruit") == 0)
				{
					finished = false;
				}
			}
		return finished;
//		if(k == fruits.size()-1)
//			return true;
//		else 
//			return false;		
	}


	public void consumedFruits() {
		// TODO Auto-generated method stub
		if(isInTruck&&fruit !=null&&!isInGround)
		{
//			fruits.remove(fruits.size()-1);
//	 		((BikeLevel) GlobalVars.ge.getCurrentStage()).getWorld().destroyBody(fruits.get(fruits.size()-1));
//	 		((BikeLevel) GlobalVars.ge.getCurrentStage()).getWorld().destroyBody(fruit);
			
//			truckedFruit.add(fruit);
//	 		this.currentTruckedFruitReduction();
	 		totalTruckedFruit++;
			
			Helper.println("no of consumed fruit is==="+ (totalTruckedFruit));
//			truckedFruit.get(truckedFruit.size()-1).setUserData("truckedfruit");
//			fruit.setType(BodyType.StaticBody);
			this.setIsInTruck(false);
		}
	
		
		
 		
	}


	public long getCurrentTruckedFruit() {
		return currentTruckedFruit;
	}


	public void setCurrentTruckedFruit(long currentTruckedFruit) {
		this.currentTruckedFruit = currentTruckedFruit;
	}


	public long getTotalTruckedFruit() {
		return totalTruckedFruit;
	}


	public void setTotalTruckedFruit(long totalTruckedFruit) {
		this.totalTruckedFruit = totalTruckedFruit;
	}


	public void currentTruckedFruit() {
		// TODO Auto-generated method stub
		currentTruckedFruit++;
//		fruits.get(fruits.size()-1).setUserData(truckedfruit");
	}
	
	public void currentTruckedFruitReduction() {
		// TODO Auto-generated method stub
		currentTruckedFruit--;
//		fruits.get(fruits.size()-1).setUserData(truckedfruit");
	}

}
