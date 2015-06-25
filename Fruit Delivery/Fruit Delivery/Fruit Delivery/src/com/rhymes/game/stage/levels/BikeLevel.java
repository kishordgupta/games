package com.rhymes.game.stage.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.CSPConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.csp.GearButton;
import com.rhymes.game.entity.elements.csp.RearForward;
import com.rhymes.game.entity.elements.csp.StartCounter;
import com.rhymes.game.entity.elements.menu.ButtonSkipChance;
import com.rhymes.game.entity.elements.nonphysical.Player;
import com.rhymes.game.entity.elements.path.traversal.PathNode;
import com.rhymes.game.entity.elements.path.traversal.Rope;
import com.rhymes.game.entity.elements.physical.Ball;
import com.rhymes.game.entity.elements.physical.Ball1;
import com.rhymes.game.entity.elements.physical.Ball3;
import com.rhymes.game.entity.elements.physical.BikeCollisionListener;
import com.rhymes.game.entity.elements.physical.Car;
import com.rhymes.game.entity.elements.physical.CarSpeedPro;
import com.rhymes.game.entity.elements.physical.CollisionListener;
import com.rhymes.game.entity.elements.physical.Elevator;
import com.rhymes.game.entity.elements.physical.FruitConsumer;
import com.rhymes.game.entity.elements.physical.FruitSender;
import com.rhymes.game.entity.elements.physical.Fruits;
import com.rhymes.game.entity.elements.physical.Ground;
import com.rhymes.game.entity.elements.physical.Lift;
import com.rhymes.game.entity.elements.physical.PhysicsHelper;
import com.rhymes.game.entity.elements.physical.Vehicle;
import com.rhymes.game.entity.elements.ui.ButtonQuit;
import com.rhymes.game.entity.elements.ui.ButtonRestart;
import com.rhymes.game.entity.elements.ui.PhysicsBody2;
import com.rhymes.game.entity.elements.ui.ProgressBar;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.fruitdelivery.menu.gameover.GameOverMenuFruit;
import com.rhymes.game.fruitdelivery.menu.selectlevel.res.LevelInfoFruit;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.game.stage.menus.stick.ButtonPause;
import com.rhymes.game.stage.menus.stick.GameOver;
import com.rhymes.game.stage.menus.stick.InterfaceiPause;
import com.rhymes.game.stage.menus.stick.LevelInfo;
import com.rhymes.game.stage.menus.stick.ScoreManage;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;
import com.rhymes.ge.pw.sound.SoundHandler.soundType;
import com.rhymes.helpers.Helper;
import com.rhymes.helpers.XMLReader;
import com.rhymes.helpers.XMLReaderChain;

public class BikeLevel extends StageBase implements InteractionTouchCallbacks,
		InterfaceiPause {

	@Override
	public void finish() {
		super.finish();
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		super.pause();
		stopSounds();
	}

	public BikeLevel(int levelNum) {
		this.levelNum = levelNum;
	}

	private World world;
	private float ballPositionX = 0 * GameMenuInfo.ratio_w;
	private float ballPositionY = 500 * GameMenuInfo.ratio_h;
	private int count = 0;
	private boolean isInGround = true;
	private boolean fruitIsInGround = false;
	private boolean fruitIsInTruck = false;

	private Car car;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	private Vehicle fruitTruck;
	public Ground h0;
	private Ground virtualBody;
	private BikeCollisionListener collisionListener;
	private Ground virtualEndBody;
	private String xmlPath;
	private XMLReader xMLReader;
	private Background background;

	private int levelNum = 0;
	private StartCounter startCounter;

	Boolean first = true;

	public boolean isFruitIsInTruck() {
		return fruitIsInTruck;
	}

	public void setFruitIsInTruck(boolean fruitIsInTruck) {
		this.fruitIsInTruck = fruitIsInTruck;
	}

	public boolean isFruitIsInGround() {
		return fruitIsInGround;
	}

	public void setFruitIsInGround(boolean fruitIsInGround) {
		this.fruitIsInGround = fruitIsInGround;
	}

	public String getXmlPath() {
		return xmlPath;
	}

	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	public boolean isInGround() {
		return isInGround;
	}

	long lastHitTime = 0;

	public void bodyHitGround(boolean isInGround) {
		/*
		 * if(System.currentTimeMillis() - lastHitTime < 500) return;
		 * 
		 * lastHitTime = System.currentTimeMillis();
		 * 
		 * // this.isInGround = isInGround; this.car.damage += 10;
		 * if(this.car.damage >= car.MAX_DAMAGE){ this.isInGround = false;
		 * progressBar.setProgress(1); } else {
		 * progressBar.setProgress(car.damage / car.MAX_DAMAGE); }
		 */
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Body getH0() {
		// h0.getBody()
		return h0.getBody();
	}

	public void setH0(Ground h0) {
		this.h0 = h0;
	}

	public void loadFonts() {
		fontController.addFont(AssetConstants.FONT_1, AssetConstants.FONT_2,
				30, 20);
		fontController.addFont(AssetConstants.FONT_1 + "2",
				AssetConstants.FONT_2, 40, 30);
	}

	Background b;

	public float carStartX = 80 * GameMenuInfo.ratio_w,
			carStartY = 600 * GameMenuInfo.ratio_h;
	private ParticleEffect effect;

	public void adjustLevelParameters() {
		carStartX = XMLReader.ropes.get(0).getNodes().getFirst().getX() + 100
				* LevelInfo.ratioX;
		carStartY = XMLReader.ropes.get(0).getNodes().getFirst().getY() + 10
				* LevelInfo.ratioY;
		// carStartY = 6000;
		// carStartY = XMLReader.ropes.get(0).getNodes().getFirst().getY()+
		// 30000 * LevelInfo.ratioY;
	}

	private void addParticles(float x, float y, String particleImageFolderPath,
			String particleFileName) {
		effect = new ParticleEffect();
		effect.load(Gdx.files.internal(particleFileName),
				Gdx.files.internal(particleImageFolderPath));
		effect.setPosition(x * LevelInfo.ratioX, y * LevelInfo.ratioY);

		effect.getEmitters().get(0).setAdditive(true);
		effect.getEmitters().get(0).setContinuous(true);
		effect.getEmitters().get(0).getScale().setHigh(25 * LevelInfo.ratioX);

		effect.start();
	}

	String imagePathCarpet;
	String imagePathTexture;
	String imageBackGround;

	Background backCSP;

	public static int GW_SLOW = 0;
	public static int GW_FAST = 1;
	public static int GW_NORMAL = 2;

	public void setGameWorld(int worldType) {
		if (worldType == GW_FAST) {
			imageBackGround = CSPConstants.CITY_BACK;
			imagePathCarpet = CSPConstants.CITY_CARPET_FAST;
			imagePathTexture = CSPConstants.CITY_TEXTURE;
			af = 3;bf = 1.4f;
			Elevator.friction = 1f;
		} else if (worldType == GW_SLOW) {
			imageBackGround = CSPConstants.CITY_BACK;
			imagePathCarpet = CSPConstants.CITY_CARPET_SLOW;
			imagePathTexture = CSPConstants.CITY_TEXTURE;
			af = 3; bf = 5;
			Elevator.friction = 0.5f;
		} else if (worldType == GW_NORMAL) {
			imageBackGround = CSPConstants.CITY_BACK;
			imagePathCarpet = CSPConstants.CITY_CARPET;
			imagePathTexture = CSPConstants.CITY_TEXTURE;
			af = 3; bf = 3;
			Elevator.friction = 0.8f;
		}

		backCSP = new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, imageBackGround);
		addElementZSorted(backCSP);
	}

	// ProgressBar progressBar;
	float fallY = 0;
	private Rope rope;
	private Fruits fruit;

	public Fruits getFruit() {
		return fruit;
	}

	public void setFruit(Fruits fruit) {
		this.fruit = fruit;
	}

	private FruitConsumer fruitconsumer;
	private FruitSender fruitSender;

	public static int gameMode = GW_SLOW;
	
	
	
	float sX, sY;
	Point p ;
	int countMax = 0;
	private void setStartPoint()
	{
		int count = 0;
		if(LevelInfoFruit.currentLevelNumber >10 )
			countMax = 1;
		else if( LevelInfoFruit.currentLevelNumber == 6)
			countMax = -1;
		for (int i = 0; i < XMLReader.ropes.size; i++) {			
			for (int j = 0; j < XMLReader.ropes.get(i).getNodes().size(); j++) {
				count++;
				if(count>countMax)
				{
					p = XMLReader.ropes.get(i).getNodes().get(j).getLocation();
					sX = p.x * LevelInfo.ratioX;
					sY = p.y * LevelInfo.ratioX;
//					Helper.printTest2("Start point setting: " + sX + "  " + sY);
					 if( LevelInfoFruit.currentLevelNumber == 5)
							carY = 200 * bf/5;
					 else if( LevelInfoFruit.currentLevelNumber == 6 ||LevelInfoFruit.currentLevelNumber == 8
							 ||LevelInfoFruit.currentLevelNumber == 9){
							carY = 70;
							sY -=260* bf/5;
					 }
					return;
				}
			}
		}
	}

	float af, bf, carY = 130;
	@Override
	public void loadElements() {

		levelNum = LevelInfoFruit.currentLevelNumber;
//		Helper.printTest("LevelNum: " + levelNum);
		loadFonts();
		ScoreManage.reset();
		stopSounds();

		setGameWorld(gameMode);

		isInGround = true;
		xMLReader.main(AssetConstants.XMLPATH + levelNum + ".xml",af, bf);
		// XMLReaderChain.main(AssetConstants.XMLPATHCHAIN);

		setStartPoint();
		adjustLevelParameters();

		world = new World(new Vector2(0, -08f), true);

		addParticles(Gdx.graphics.getWidth() / 2 * LevelInfo.ratioX,
				LevelInfo.ratioY * Gdx.graphics.getHeight() / 2,
				AssetConstants.PARTICLE_FOLDER, AssetConstants.PART_FIRE);

		collisionListener = new BikeCollisionListener();
		world.setContactListener(collisionListener);
		collisionListener.setCollided(true);
		InteractionTouch screenTouch = new InteractionTouch();
		this.gameControlInteractions.add(screenTouch);

		car = new Car(/*XMLReader.ropes.get(0).getNodes().getFirst().getX() + 280
				* GameMenuInfo.ratio_w, XMLReader.ropes.get(0).getNodes()
				.getFirst().getY()
				+ 130 * LevelInfo.ratioY*/sX + 280 * LevelInfo.ratioX, sY + carY* LevelInfo.ratioY,
				45 * GameMenuInfo.ratio_w,
				10 * GameMenuInfo.ratio_h, 0f, Constants.carTypeFruitTruck,
				world, false, false, 3);
		addElementZSorted(car);

		fruit = new Fruits(world, /*xMLReader.ropes.get(0).getNodes().get(0).getX()*/sX+90 * GameMenuInfo.ratio_w,
				/*xMLReader.ropes.get(0).getNodes().get(0).getY()*/sY + 400 * GameMenuInfo.ratio_h, 
				40 * GameMenuInfo.ratio_w,
				40 * GameMenuInfo.ratio_h, 4);
		addElement(fruit);
		// addElementZSorted(fruit);

		fruitSender = new FruitSender(world,sX, sY/* XMLReader.ropes.get(0).getNodes()
				.getFirst().getX()
				* GameMenuInfo.ratio_w, XMLReader.ropes.get(0).getNodes()
				.getFirst().getY()*/
				/*- 100 * GameMenuInfo.ratio_h*/, 233 * GameMenuInfo.ratio_w,
				10 * GameMenuInfo.ratio_h, 4, 3);
		addElement(fruitSender);

		Elevator elevator0 = new Elevator(world, 30, new Vector2(
				xMLReader.ropes.get(0).getNodes().get(0).getX(),
				xMLReader.ropes.get(0).getNodes().get(0).getY()), null,
				LevelInfo.ratioX * 20, LevelInfo.ratioX * 20, 2,
				imagePathCarpet, imagePathTexture);
		// addElement(elevator0);
		addElementZSorted(elevator0);

		fruitconsumer = new FruitConsumer(world, lastPathNode.getPrevNode()
				.getX() - 360 * LevelInfo.ratioX, lastPathNode.getPrevNode()
				.getY()/*-492 * LevelInfo.ratioY*/, 360 * LevelInfo.ratioX,
				10 * LevelInfo.ratioY, 2);
		addElement(fruitconsumer);

		// addElementZSorted(fruitconsumer);

		float x = 0;
		float y = 0;

		x = Gdx.graphics.getWidth() - 75f * LevelInfo.ratioX;
		y = Gdx.graphics.getHeight() - 65f * LevelInfo.ratioY;
		pause = new ButtonPause(x, y, 48f * LevelInfo.ratioX,
				48f * LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_PAUSE, this);
		this.elements.add(pause);
		subscribeToControllingInteraction(pause, InteractionTouch.class);

		addText();

		// progressBar = new ProgressBar(10, 10, Gdx.graphics.getWidth()/2f, 40
		// * LevelInfo.ratioY,
		// AssetConstants.IMG_LOADING_BACK, AssetConstants.IMG_LOADING_BACK,
		// "Damage: ", AssetConstants.FONT_1+ "2");
		// this.addElement(progressBar);

		this.gameState = GameState.PLAYING;

		startCounter = new StartCounter(Gdx.graphics.getWidth() - 42.5f,
				Gdx.graphics.getHeight() - 64f, 85 * LevelInfo.ratioX,
				128 * LevelInfo.ratioY, 1);
		startCounter.start();
		int i = addElementZAtTop(startCounter);

		gLeft = new GearButton(0,0, 128 * LevelInfo.ratioX, 256f * LevelInfo.ratioY, i);
		addElement(gLeft);
		
		gRight = new GearButton(0,0, 128 * LevelInfo.ratioX, 256f * LevelInfo.ratioY, i);
		addElement(gRight);
		
		rf = new RearForward(0,0, 128 * LevelInfo.ratioX, 128f * LevelInfo.ratioY, i);
		addElement(rf);
		
		subscribeToControllingInteraction(rf, InteractionTouch.class);

	}
	
	public GearButton gLeft, gRight;
	public RearForward rf;

	public void setCarDirection(boolean carDirection)
	{
		car.carDirectionForward = carDirection;
	}


	private void setInput() {
		Gdx.input.setInputProcessor(new InputProcessor() {

			@Override
			public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
				gLeft.reset();
				gRight.reset();
				return false;
			}

			@Override
			public boolean touchMoved(int arg0, int arg1) {
				return false;
			}

			@Override
			public boolean touchDragged(int arg0, int arg1, int arg2) {
				return false;
			}

			@Override
			public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
				if(gameState != GameState.PLAYING)
					return false;
				
				
				//right
				if (arg0 > Gdx.graphics.getWidth() * 2 / 3f) {
					if(car.getMotorSpeed() > 0.5f)
						StartupInfo.sound_handler.playSound(SoundHandler.soundType.SOUND_BIKE_GEARUP);
					
					gRight.reset();
					gRight.start();
				}//left
				else if (arg0 < Gdx.graphics.getWidth() / 3f) {
					gLeft.reset();
					gLeft.start();
				} 
				return false;
			}

			@Override
			public boolean scrolled(int arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyUp(int arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyTyped(char arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyDown(int arg0) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		// assetPack.addTexture(CSPConstants.TROPIC_BACK);
		// assetPack.addTexture(CSPConstants.TROPIC_CARPET);
		// assetPack.addTexture(CSPConstants.TROPIC_TEXTURE);

//		assetPack.addTexture(AssetConstants.IMG_PAGE_BLANK);
		// assetPack.addTexture(AssetConstants.IMG_CL_BKG);
		// assetPack.addTexture(AssetConstants.IMG_GROUND_2);
		// assetPack.addTexture(AssetConstants.IMG_BALL_RUBBER);
//		assetPack.addTexture(AssetConstants.PHY_IMG_CARBODY);
//		assetPack.addTexture(AssetConstants.PHY_IMG_BIKEMAN);
//		assetPack.addTexture(AssetConstants.PHY_IMG_LEFTAXLECONTAINER);
//		assetPack.addTexture(AssetConstants.PHY_IMG_WHEEL);
//
//		assetPack.addTexture(AssetConstants.IMG_BKG);
//		assetPack.addTexture(AssetConstants.IMG_BTN_PAUSE);
		assetPack.addTexture(AssetConstants.IMG_BTN_RESTART);
		assetPack.addTexture(AssetConstants.IMG_BTN_RESUME);
		assetPack.addTexture(AssetConstants.IMG_BTN_QUITLEVEL);
		assetPack.addTexture(AssetConstants.IMG_SELECT);
//		assetPack.addTexture(AssetConstants.IMG_BTN_GAMEOVER);
//		assetPack.addTexture(AssetConstants.IMG_BTN_LEVELCOMPLETE);

		assetPack.addTexture(AssetConstants.IMG_PATH);
		// /for fruit
		assetPack.addTexture(AssetConstants.PHY_IMG_FRUIT1);
		assetPack.addTexture(AssetConstants.PHY_IMG_FRUIT2);
		assetPack.addTexture(AssetConstants.PHY_IMG_FRUIT3);
		assetPack.addTexture(AssetConstants.PHY_IMG_FRUIT4);
		assetPack.addTexture(AssetConstants.PHY_IMG_FRUIT5);

		// return null;
		return assetPack;
	}

	// private int gameState = GameState.PLAYING;

	Body fumeBody;

	public void setFumeBody(Body fumeBody) {
		this.fumeBody = fumeBody;
	}

	public void setFumePosition(float x, float y) {
		effect.setPosition(x, y);
	}

	long demoSteptime = 0;
	long anotherdemoSteptime = 0;

	boolean f = false;
	@Override
	public void tick(long stepTime) {

		demoSteptime = demoSteptime + stepTime;
		anotherdemoSteptime = anotherdemoSteptime + stepTime;
		// Helper.println("demosteptime value :::"+ demoSteptime);

		// Helper.println("Fruits: " + fruit.fruits.size());

		if (startCounter.isStarted()) {
			if (startCounter.isFinished()) {
				car.started = true;
				if ((car.getX() > fruitconsumer.getLeft() - 50f * LevelInfo.ratioX)
						/*&& (car.getX() < fruitconsumer.getRight() + 20)*/) {
					Helper.printTest("///////////////////////////////////Car Near to Counsmer");
					if(!f){
						int count = 0;
						for(int i = 0; i < GlobalVars.ge.getCurrentStage().getElements().size; i++)
							if(GlobalVars.ge.getCurrentStage().getElements().get(i) instanceof PhysicsBody2)
							{
								if(((String)(((PhysicsBody2)GlobalVars.ge.getCurrentStage().getElements().get(i)).getBody().getUserData())).compareTo("truckedfruit") == 0)
								{
									count++;
								}
							}
						ScoreManage.totalTruckedFruit = count;
						f = true;
					}
					fruit.continueConsumeFruit();
					if (fruit.isConsumptionFinished()) {
						// missionAchived();
						if(ScoreManage.totalTruckedFruit > 0)
							GlobalVars.ge.loadStage(new GameOverMenuFruit(false));
						// gameOver();

						// based on the results call either one of these
						// functions when level finished
					}

				}

				if (anotherdemoSteptime > 15000) {
					if (currentTruckedFruit == 0) {
						GlobalVars.ge.loadStage(new GameOverMenuFruit(true));
					}
				}
				if (demoSteptime > 200) {
					// fruit.
					// Helper.println("demosteptime value when 1000ms passed:::"+
					// demoSteptime);
					fruit.startGenaration();

					if ((car.getX() > fruitconsumer.getLeft()  - 50f * LevelInfo.ratioX)
							/*&& (car.getX() < fruitconsumer.getRight() + 20)*/) {
						// Helper.println("Ok baby I'm here suck it"+car.getX()+"and fruit consumer position is ::"+fruitconsumer.getLeft());

						fruit.consumFruit();
					}
					demoSteptime = demoSteptime - 1000;
				}

			}
		}

		updateScores(stepTime);
		checkGameState();
		setInput();
	}

	private void checkGameState() {

		// Helper.println("\n\n Car x: " +
		// PhysicsHelper.ConvertToWorld(car.getBody().getWorldCenter().x));
		// Helper.println("P x: " + p.getX());

		// Helper.println("Game State:  " + this.gameState);
		// if (PhysicsHelper.ConvertToWorld(car.getBody().getWorldCenter().x) >
		// lastPathNode
		// .getX() - 200)
		// missionAchived();

		if (PhysicsHelper.ConvertToWorld(car.getBody().getWorldCenter().y) < fallY)
			gameOver();

		if (!isInGround) {
			car.destroyJoints();
			this.bodyHitGround(true);
			gameOver();
			// reload();
		}

		if (fruitIsInGround) {
			fruit.setIsInGround(true);
			fruit.destroyFruits();
			this.setFruitIsInGround(false);
		}

		if (fruitIsInTruck) {
			fruit.setIsInTruck(true);
			fruit.consumedFruits();
			this.setFruitIsInTruck(false);
		}

	}

	public void gameOver() {
		stopSounds();
		this.gameState = GameState.OVER;
		// GameOver go = new GameOver(LevelInfo.GAME_FAILED, this);
		// go.main();
		GlobalVars.ge.loadStage(new GameOverMenuFruit(true));
	}

	public PathNode lastPathNode;

	public void setElevatorInfo(PathNode lastPathNode, float lastY) {
		this.lastPathNode = lastPathNode;
		this.fallY = lastY;
	}

	public void missionAchived() {
		stopSounds();
		this.gameState = GameState.OVER;
		currentDistance = (long) totalDistance;
		score.setDistance((long) totalDistance);
		score.setPercent(100);
		// updateScores(GlobalVars.ge.stepTime);

		textDistance.setText("Distance: " + score.getDistance() + " m");
		textTime.setText("Time: " + score.getTime() + " s");
		textPercent.setText("Percent: " + 100 + "%");

		GameOver go = new GameOver(LevelInfo.GAME_WIN, this);
		go.main();
		Helper.println("Game Mission Achived");
	}

	@Override
	public void onEvent(Point hitPoint) {
	}

	public void addText() {
		float x = 20f * LevelInfo.ratioX;
		float y = Gdx.graphics.getHeight() - 15f * LevelInfo.ratioY;

		float r, g, b, a;
		r = 0.39f;
		g = 0.09f;
		b = 0.79f;
		a = 0.7f;

		textDistance = new Text(x, y, fontController, AssetConstants.FONT_1,
				"Distance: " + score.getDistance());
		// textDistance.setColor(0.1f, 0.3f, 0.7f,1.0f);
		textDistance.setColor(r, g, b, a);
		// textDistance.setColor(0.1f, 0.1f, 0.1f,0.8f);
		addElement(textDistance);

		x = x + 350f * LevelInfo.ratioX;
		textTime = new Text(x, y, fontController, AssetConstants.FONT_1,
				"Time: " + score.getTime());
		// textTime.setColor(0.1f, 0.3f, 0.7f,1.0f);
		// textTime.setColor(0.59f, 0.59f, 0.59f,0.59f);
		// textTime.setColor(0.69f, 0.79f, 0.89f,1f);
		// textTime.setColor(0.1f, 0.1f, 0.1f,0.8f);
		textTime.setColor(r, g, b, a);
		addElement(textTime);

//		Helper.printTest("LevelNum: " + levelNum);
		x = x + 350f * LevelInfo.ratioX;
		textLevelNumber = new Text(x, y, fontController, AssetConstants.FONT_1,
				"Level: " + levelNum);
		// textLevelNumber.setColor(0.1f, 0.3f, 0.7f,1.0f);
		// textLevelNumber.setColor(0.59f, 0.59f, 0.59f,0.59f);
		textLevelNumber.setColor(r, g, b, a);
		addElement(textLevelNumber);

		x = x - 100f * LevelInfo.ratioX;
		textTruckedFruit = new Text(x, y, fontController,
				AssetConstants.FONT_1, "Trucked: " + score.getTruckedFruit());
		// textDistance.setColor(0.1f, 0.3f, 0.7f,1.0f);
		// textDistance.setColor(0.89f, 0.59f, 0.59f,1f);
		textTruckedFruit.setColor(r, g, b, a);
		addElement(textTruckedFruit);

		x = 20f * LevelInfo.ratioX;
		y = 20f * LevelInfo.ratioY;
		textPercent = new Text(x, y, fontController, AssetConstants.FONT_1,
				"Percent: " + getPercentage());
		// textPercent.setColor(0.1f, 0.3f, 0.7f,1.0f);
		textPercent.setColor(0.9f, 0.8f, b, 0.7f);
		addElement(textPercent);

	}

	boolean start = true;
	float curCarX, cx;

	public void updateScores(long stepTime) {

		// missionAchived();

		if (score.getDistance() > 40f)
			if (currentTruckedFruit == 0)
				gameOver();

		if (start) {
			carStartX = car.getBody().getPosition().x;
			this.totalDistance = PhysicsHelper.ConvertToBox(lastPathNode
					.getLocation().x) - carStartX;
			start = false;
		}
		cx = car.getBody().getPosition().x;
		currentDistance = (long) (cx - carStartX);
		currentTruckedFruit = fruit.getCurrentTruckedFruit();
		totalDeliveredFruit = fruit.getTotalTruckedFruit();
		totalIssuedFruit = fruit.getFruitCount();

		// if(cx > curCarX){
		// currentDistance += (cx - curCarX);
		// curCarX =
		// PhysicsHelper.ConvertToWorld(car.getBody().getPosition().x);
		// }
		// else{
		// currentDistance += (cx - curCarX);
		// curCarX =
		// PhysicsHelper.ConvertToWorld(car.getBody().getPosition().x);
		// }
		// currentDistance += 5;
		if(car.started)
			score.setTime(stepTime);
		score.setDistance(currentDistance);
		score.setTotalIssuedFruit(totalIssuedFruit);
		score.setTruckedFruit(currentTruckedFruit);
		// score.setTotalTruckedFruit(totalDeliveredFruit);
		score.setPercent(getPercentage());

		textDistance.setText("Distance: " + score.getDistance() + " m");
		textTruckedFruit.setText("Trucked: " + score.getTruckedFruit() + " pc");
		textTime.setText("Time: " + score.getTime() + " s");
		// textLevelNumber.setText("Level: " + LevelInfo.currentLevelNumber);
		textPercent.setText("Percent: " + getPercentage() + "%");

		float x = GlobalVars.ge.getScreen().cam.position.x
				- Gdx.graphics.getWidth() / 2f + 20f * LevelInfo.ratioX;
		float y = GlobalVars.ge.getScreen().cam.position.y
				+ Gdx.graphics.getHeight() / 2f - 35f * LevelInfo.ratioY;

		textDistance.setX(x);
		textDistance.setY(y);

		x = x + 350f * LevelInfo.ratioX;
		textTime.setX(x);
		textTime.setY(y);
		x = x + 480f * LevelInfo.ratioX;
		textLevelNumber.setX(x);
		textLevelNumber.setY(y);

		textTruckedFruit.setX(x - 250f * LevelInfo.ratioX);
		textTruckedFruit.setY(y);

		x = GlobalVars.ge.getScreen().cam.position.x - Gdx.graphics.getWidth()
				/ 2f + 20f * LevelInfo.ratioX;
		y = GlobalVars.ge.getScreen().cam.position.y - Gdx.graphics.getHeight()
				/ 2f + 15f * LevelInfo.ratioY;
		textPercent.setX(x);
		textPercent.setY(y);

		x = GlobalVars.ge.getScreen().cam.position.x + Gdx.graphics.getWidth()
				/ 2f - 85f * LevelInfo.ratioX;
		y = GlobalVars.ge.getScreen().cam.position.y - Gdx.graphics.getHeight()
				/ 2f + 20f * LevelInfo.ratioY;
		pause.setX(x);
		pause.setY(y);

		backCSP.setLocation(
				GlobalVars.ge.getScreen().cam.position.x
						- Gdx.graphics.getWidth() / 2f,
				GlobalVars.ge.getScreen().cam.position.y
						- Gdx.graphics.getHeight() / 2f);

		// progress bar

		x = GlobalVars.ge.getScreen().cam.position.x - Gdx.graphics.getWidth()
				/ 2f + 240f * LevelInfo.ratioX;
		y = GlobalVars.ge.getScreen().cam.position.y - Gdx.graphics.getHeight()
				/ 2f + 15f * LevelInfo.ratioY;
		// progressBar.setLocation(x, y);

		if (!startCounter.isFinished())
			startCounter.setLocation(GlobalVars.ge.getScreen().cam.position.x
					- 42.5f * LevelInfo.ratioX,
					GlobalVars.ge.getScreen().cam.position.y - 64f
							* LevelInfo.ratioX);


		gLeft.setLocation(
				GlobalVars.ge.getScreen().cam.position.x
						- Gdx.graphics.getWidth()/2f ,
				GlobalVars.ge.getScreen().cam.position.y
				- Gdx.graphics.getHeight()/2f );
		
		gRight.setLocation(
				GlobalVars.ge.getScreen().cam.position.x
				+ Gdx.graphics.getWidth()/2f - gRight.getWidth(),
		GlobalVars.ge.getScreen().cam.position.y
		- Gdx.graphics.getHeight()/2f );
		
		rf.setLocation(
				GlobalVars.ge.getScreen().cam.position.x
				+ Gdx.graphics.getWidth()/2f - gLeft.getWidth() * 3f,
		GlobalVars.ge.getScreen().cam.position.y
		- Gdx.graphics.getHeight()/2f + 50 * LevelInfo.ratioY) ;

	}

	public double totalDistance = 10000;
	public long currentDistance = 0;
	public long currentTruckedFruit = 0;
	public long totalDeliveredFruit = 0;
	private int totalIssuedFruit;

	ScoreManage score = new ScoreManage();
	public Text textDistance;
	public Text textTime;
	public Text textLevelNumber;
	public Text textPercent;
	public Text textTruckedFruit;
	public ButtonPause pause;

	public long getPercentage() {
		double percent = 0;
		percent = currentDistance * 100 / totalDistance;
		return (long) percent;
	}

	@Override
	public StageBase getStage() {
		return this;
	}

	public void stopSounds() {
		StartupInfo.sound_handler.stopSound(soundType.SOUND_BIKE_GEARUP);
		StartupInfo.sound_handler.stopMusic(musicType.SOUND_BIKE_RUNNING);
		StartupInfo.sound_handler.stopSound(soundType.SOUND_BIKE_START);
	}

	// public void removeGroundedFruits() {
	// for(int i = 0; i < elements.size; i++)
	// {
	// zSortedElements.get(i).size=;
	// if(elements.get(i) instanceof PhysicsBody2)
	// {
	// if(((String)(((PhysicsBody2)elements.get(i)).getBody().getUserData())).compareTo("Grounded")
	// == 0)
	// {
	// postDestroyed(elements.get(i));
	// }
	// }
	// }
	// }

	public void removeGroundedFruits() {
		// Helper.printTest("Removing grounded fruits");
		 for(int j = 0; j < zSortedElements.size; j++)
		 {
			 for (int i = 0; i < zSortedElements.get(j).size; i++)
			if (zSortedElements.get(j).get(i) instanceof PhysicsBody2) {
//				Helper.printTest("Fruit userdata : "
//						+ (((PhysicsBody2) elements.get(i)).getBody()
//								.getUserData()));
				if (((String) (((PhysicsBody2) (zSortedElements.get(j).get(i))).getBody()
						.getUserData())).compareTo("Grounded") == 0
						|| ((String) (((PhysicsBody2) (zSortedElements.get(j).get(i)))
								.getBody().getUserData()))
								.compareTo("Consumed") == 0) {
					// Helper.printTest("Removing grounded fruits: " + i);
					postDestroyed((zSortedElements.get(j).get(i)));
				}
			}
		 }
	}

	public void removeConsumedFruits() {
		for (int i = 0; i < elements.size; i++)

			// zSortedElements.get(i).size=;
			if (elements.get(i) instanceof PhysicsBody2) {
//				Helper.printTest("Fruit userdata : "
//						+ (((PhysicsBody2) elements.get(i)).getBody()
//								.getUserData()));
				if (((String) (((PhysicsBody2) elements.get(i)).getBody()
						.getUserData())).compareTo("Consumed") == 0) {
					// Helper.printTest("Removing grounded fruits: " + i);
					postDestroyed(elements.get(i));
				}
			}
	}

}
