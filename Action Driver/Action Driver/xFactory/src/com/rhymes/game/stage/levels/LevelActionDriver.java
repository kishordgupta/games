package com.rhymes.game.stage.levels;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.data.Score;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.animations.actiondriver.AniDestroyFlyAway;
import com.rhymes.game.entity.animations.actiondriver.AniFallingRock;
import com.rhymes.game.entity.animations.actiondriver.AniShrink;
import com.rhymes.game.entity.elements.actiondriver.Car;
import com.rhymes.game.entity.elements.actiondriver.CarEnemey;
import com.rhymes.game.entity.elements.actiondriver.CarHero;
import com.rhymes.game.entity.elements.actiondriver.GearSwitch;
import com.rhymes.game.entity.elements.actiondriver.SideScroller;
import com.rhymes.game.entity.elements.actiondriver.collidable.ADCollidable;
import com.rhymes.game.entity.elements.actiondriver.collidable.CollisionHandler;
import com.rhymes.game.entity.elements.ui.Background;
import com.rhymes.game.entity.elements.ui.ButtonPause;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.generation.GenerationController;
import com.rhymes.game.generation.GenerationLine;
import com.rhymes.game.generation.actiondriver.ADGenerationController;
import com.rhymes.game.interactions.ISingleCollision;
import com.rhymes.game.interactions.InteractionFlick;
import com.rhymes.game.interactions.actiondriver.ICCarController;
import com.rhymes.game.interactions.actiondriver.InteractionCarController;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.menus.GameOverScreen;
import com.rhymes.game.stage.menus.MenuActionDriver;
import com.rhymes.game.stage.result.ResultActionDriver;
import com.rhymes.ge.core.controller.FontController;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class LevelActionDriver extends StageBase {
	public void gameOver()
	{
		reload();
//		dead = true;
////		Score.reset();
//		Score score = new Score();
//		score.carCrashed = this.result.getCarCrashed();
//		score.cashCollected = this.result.getCashCollected();
//		score.score = this.result.getScore();
//		score.totalScore = this.result.getTotalScore();
//		
//		Helper.println("1 Total Score: " + score.totalScore);
//		GlobalVars.ge.loadStage(new GameOverScreen(score));		
//		Helper.println("2 Total Score: " + score.totalScore);
	}
	
	@Override
	public void resume() {
		super.resume();
		fontController.reloadFonts();
		fontController.getFont(Constants.fontName1).setScale(Constants.fontScoreScaleX, Constants.fontScoreScaleY);
	}

	InteractionCarController iCarController;
	CarHero heroCar;
	ResultActionDriver result;
	ISingleCollision iSingleCollision;
	FontController fontController;
	GenerationController generationController;
	
	//score ui
	Text textScore, textCarCrashed, textCash;
	
	private void printStartingInfo()
	{
		Helper.println("Rx: " + Constants.rx);
		Helper.println("Ry: " + Constants.ry);
		Helper.println("Car SpeedX: " + Constants.carStartingSpeedX);
	}

	
	private void loadFonts() {
		fontController.addFont(Constants.fontName1, AssetConstants.FONT_1);
		fontController.addFont(Constants.fontName2, AssetConstants.FONT_2);
	}

	ButtonPause buttonPause;
	@Override
	public void loadElements() {
		
		printStartingInfo();
		
		fontController = new FontController();
		loadFonts();
		
		this.gameControlInteractions.add(new InteractionTouch());
		
		iSingleCollision = new ISingleCollision();
		this.interactions.add(iSingleCollision);
		
		
		initGeneration();
		result = new ResultActionDriver();
		addRoad();
		addHeroCar();
		addScores();
		
		
		buttonPause = new ButtonPause(50 * Constants.ry, 30*Constants.ry, 100*Constants.rx,
				100*Constants.ry, 3, AssetConstants.IMG_PAUSE, AssetConstants.IMG_PLAY);
		addElementZSorted(buttonPause);

		
//		addEnemey(10, 150 * Constants.ry, Constants.carStartingSpeedX * 1.5f, 100);
//		addEnemey(80, 0 * Constants.ry, Constants.carStartingSpeedX / 1.5f, 100);
//		addEnemey(1000, 150 * Constants.ry, Constants.carStartingSpeedX * 1.5f, 100);
//		addEnemey(500, 80 * Constants.ry, Constants.carStartingSpeedX * 2.5f, 100);
//		addEnemey(700, 80 * Constants.ry, Constants.carStartingSpeedX * 1.5f, 100);
//		addEnemey(1700, 80 * Constants.ry, Constants.carStartingSpeedX * 2f, 100);
//		addEnemey(1500, 80 * Constants.ry, Constants.carStartingSpeedX * 1.5f, 100);
		
		
//		addCone(879, 110, Constants.coneWidth, Constants.coneHeight);
//		addCone(839, 90, Constants.coneWidth, Constants.coneHeight);
//		addCone(899, 80, Constants.coneWidth, Constants.coneHeight);
		
//		addDrum(699, 30, Constants.bigCrackWidth, 100);
//		addBox(799, 40, Constants.boxWidth, Constants.boxHeight);
//		addPeeble(899, 40, Constants.bigCrackWidth/5f, 20);
		
//		addSteepJumper(699, 70, Constants.bigCrackWidth/5f, 60);
//		addFallingRock(400, 10, 100, 100, 10);
		
//		addCrack(699, 0, Constants.bigCrackWidth, Helper.getScreenHeight());
//		addCash(900, Constants.roadLowerBoundary + 20, Constants.COLLIDABLE_CASH_1);
//		addCash(1000, Constants.roadLowerBoundary + 40, Constants.COLLIDABLE_CASH_1);
//		addCash(1100, Constants.roadLowerBoundary + 70, Constants.COLLIDABLE_CASH_1);
		
		
		GlobalVars.ge.getScreen().setCamController(true);
	}
	
	
	private void initGeneration() {
		this.generationController = new ADGenerationController(); 
	}

	public void addGearSwitchText(String text)
	{
		addElementZSorted(new GearSwitch(Helper.getScreenWidth()/2f, Helper.getScreenHeight()/2f, 2, 0.05f, fontController.getFont(Constants.fontName2), text));
	}

	private void addScores() {
		textScore = new Text(Constants.scoreX, Constants.scoresY, Constants.fontScoreScaleX, Constants.fontScoreScaleY, fontController, Constants.fontName1, "Score: 100");
		textScore.setzIndex(2);
		addElementZSorted(textScore);
		
		textCash = new Text(Constants.cashX, Constants.scoresY, Constants.fontScoreScaleX, Constants.fontScoreScaleY, fontController, Constants.fontName1, "Cash: $100");
		textCash.setzIndex(2);
		addElementZSorted(textCash);
		
		textCarCrashed = new Text(Constants.carCrashedX, Constants.scoresY, Constants.fontScoreScaleX, Constants.fontScoreScaleY,
				fontController, Constants.fontName1, "Car Crashed: 3");
		textCarCrashed.setzIndex(2);
		addElementZSorted(textCarCrashed);
	}


//	public void addSteepJumper(float x, float y, float width, float height)
//	{
//		ADCollidable adCollidable = new ADCollidable(x, y, width, height, 1,  Constants.COLLIDABLE_STEEP_JUMPER, 
//				Helper.getImageFromAssets(AssetConstants.IMG_DUMMY_1));
////		adCollidable.setNormalAnimation(new AniFallingRock(adCollidable, dy, lastY));
////		adCollidable.setAnimation(adCollidable.getNormalAnimation());
////		adCollidable.setFinishAnimation(new AniShrink(adCollidable, 7,7));
//		addElementZSorted(adCollidable);
//		subscribeToInteraction(adCollidable, ISingleCollision.class);
//	}
	
	
	public AnimationBase getFinishAnimation(ElementBase element)
	{
		return new AniDestroyFlyAway(element, 15 * Constants.rx, 15 * Constants.ry);
	}
	
	public void addFallingRock(float x, float lastY, float width, float height, float dy)
	{
		ADCollidable adCollidable = new ADCollidable(x, 0, width, height, 3,  Constants.COLLIDABLE_FALLING_ROCK, 
				Helper.getImageFromAssets(AssetConstants.IMG_ROCK));
		adCollidable.setNormalAnimation(new AniFallingRock(adCollidable, dy, lastY));
		adCollidable.setAnimation(adCollidable.getNormalAnimation());
		adCollidable.setFinishAnimation(getFinishAnimation(adCollidable));
		addElementZSorted(adCollidable);
		subscribeToInteraction(adCollidable, ISingleCollision.class);
	}
	
	public void addPeeble(float x, float y, float width, float height)
	{
		ADCollidable adCollidable = new ADCollidable(x, y, width/2f, height, 1,  Constants.COLLIDABLE_PEEBLE, 
				Helper.getImageFromAssets(AssetConstants.IMG_RED_GAS_CAN));
		adCollidable.setFinishAnimation(getFinishAnimation(adCollidable));
		addElementZSorted(adCollidable);
		subscribeToInteraction(adCollidable, ISingleCollision.class);
	}
	
	public void addDrum(float x, float y, float width, float height)
	{
		ADCollidable adCollidable = new ADCollidable(x, y, width, height, 1,  Constants.COLLIDABLE_DRUM, 
				Helper.getImageFromAssets(AssetConstants.IMG_GREEN_BIN));
		adCollidable.setFinishAnimation(getFinishAnimation(adCollidable));
		addElementZSorted(adCollidable);
		subscribeToInteraction(adCollidable, ISingleCollision.class);
	}
	
	public void addBox(float x, float y, float width, float height)
	{
		ADCollidable adCollidable = new ADCollidable(x, y, width, height, 1,  Constants.COLLIDABLE_BOX, 
				Helper.getImageFromAssets(AssetConstants.IMG_GIFT_1));
		adCollidable.setFinishAnimation(getFinishAnimation(adCollidable));
		addElementZSorted(adCollidable);
		subscribeToInteraction(adCollidable, ISingleCollision.class);
	}
	
	
	public void addCone(float x, float y)
	{
		addCone(x, y, Constants.coneWidth, Constants.coneHeight);
	}
	public void addCone(float x, float y, float width, float height)
	{
		ADCollidable adCollidable = new ADCollidable(x, y, width, height, 1,  Constants.COLLIDABLE_CONE, 
				Helper.getImageFromAssets(AssetConstants.IMG_CONE));
		adCollidable.setFinishAnimation(getFinishAnimation(adCollidable));
		addElementZSorted(adCollidable);
		subscribeToInteraction(adCollidable, ISingleCollision.class);
	}
	
	public void addCrack(float x, float y, float width, float height)
	{
		ADCollidable adCollidable = new ADCollidable(x, y, width, height, 1,  Constants.COLLIDABLE_CRACK, 
				Helper.getImageFromAssets(AssetConstants.IMG_ROCK));
		addElementZSorted(adCollidable);
		subscribeToInteraction(adCollidable, ISingleCollision.class);
	}
	
	public void addCash(float x, float y, int type)
	{	
		ADCollidable adCollidable = null;
		adCollidable = new ADCollidable(x, y, Constants.cashWidth, Constants.cashHeight, 1, type, 
				Helper.getImageFromAssets(AssetConstants.IMG_CASH));
		
		adCollidable.setFinishAnimation(getFinishAnimation(adCollidable));
		addElementZSorted(adCollidable);
		subscribeToInteraction(adCollidable, ISingleCollision.class);
	}
	
	
	public void addEnemey(float x, float y, float speedX, float health)
	{
		speedX = getHeroSpeed();
		Helper.println("Enemey Car added at: " + x);
		Car enemeyCar = new CarEnemey( x, y,
				Constants.carWidth, Constants.carHeight, 
				2, heroCar.getSpeedX() * 0.8f, health);
//		Background enemeyCar = new Background(x, y, 150, 150, 2,AssetConstants.IMG_CAR_RED);
//		enemeyCar.init();
		addElementZSorted(enemeyCar);
		iSingleCollision.subscribe(enemeyCar);
	}
	private void addHeroCar()
	{
		heroCar = new CarHero( (Helper.getScreenWidth()-Constants.carWidth)/2f
				, 50 * Constants.ry,
				Constants.carWidth, Constants.carHeight, 2, Constants.carStartingSpeedX);
		
		addElementZSorted(heroCar);
		iCarController = new InteractionCarController((ICCarController) heroCar);
		Gdx.input.setInputProcessor(iCarController);
		
		iSingleCollision.addHero(heroCar);
	}

	private void addRoad() {
		Array<ElementBase> roadSegments = new Array<ElementBase>();
		
		// road segments
//		roadSegments.add(new Background(0, 0, Helper.getImageFromAssets(AssetConstants.IMG_ROAD_1).getRegionWidth()*2f, 
//				Helper.getScreenHeight(), 0, Helper.getImageFromAssets(AssetConstants.IMG_ROAD_1) ));

		roadSegments.add(new Background(0, 0, Helper.getImageFromAssets(AssetConstants.IMG_ROAD_1).getRegionWidth(), 
				Helper.getScreenHeight()+50*Constants.ry, 0, Helper.getImageFromAssets(AssetConstants.IMG_ROAD_1) ));
		
//
//		roadSegments.add(new Background(0, 0, Helper.getImageFromAssets(AssetConstants.IMG_ROAD_1).getRegionWidth() * 3f, 
//				Helper.getScreenHeight(), 0, Helper.getImageFromAssets(AssetConstants.IMG_ROAD_1) ));
//		
//		roadSegments.add(new Background(0, 0, Helper.getImageFromAssets(AssetConstants.IMG_ROAD_1).getRegionWidth()*5f, 
//				Helper.getScreenHeight(), 0, Helper.getImageFromAssets(AssetConstants.IMG_ROAD_1) ));
		
//		addElementZSorted(new SideScroller(roadSegments, 0, Helper.getScreenHeight()/2.5f, true));
		addElementZSorted(new SideScroller(roadSegments, 0, 0, true));
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_ROCK);
		assetPack.addTexture(AssetConstants.IMG_CAR);
		assetPack.addTexture(AssetConstants.IMG_CAR_RED);
		assetPack.addTexture(AssetConstants.IMG_ROAD_1);
		assetPack.addTexture(AssetConstants.IMG_BOX);
		assetPack.addTexture(AssetConstants.IMG_CASH);
		assetPack.addTexture(AssetConstants.IMG_CONE);
		assetPack.addTexture(AssetConstants.IMG_CARGO);
		
		assetPack.addTexture(AssetConstants.IMG_GREEN_BIN);
		assetPack.addTexture(AssetConstants.IMG_RED_GAS_CAN);
		assetPack.addTexture(AssetConstants.IMG_GIFT_1);
		
		assetPack.addTexture(AssetConstants.IMG_PLAY);		
		assetPack.addTexture(AssetConstants.IMG_PAUSE);
		return assetPack;
	}

	float lastCamX = 0;
	public boolean dead = false;
	@Override
	public void tick(long stepTime) {                  
		
		if(Gdx.app.getType() == ApplicationType.Android){
			if(!dead)
				this.stepElementsZ(stepTime);
		}
		this.generationController.tick(stepTime);
		this.result.increaseRoundDuration(stepTime);
		this.result.scoreX = (heroCar.getSpeedX() - Constants.carStartingSpeedX + 1);
		if((Helper.getCameraX() - lastCamX) > 500){
			this.result.increaseScore((int) (10 * (heroCar.getSpeedX() - Constants.carStartingSpeedX + 1)));
			lastCamX = Helper.getCameraX();
		}
		updateScores();
		
		buttonPause.setX(Helper.getCameraX() + 50 * Constants.rx);
	}
	
	private void updateScores() {
		textScore.setText("Score: " + this.result.getScore());
		textScore.setX(Helper.getCameraX() + Constants.scoreX);
		
		textCash.setX(Helper.getCameraX() + Constants.cashX);
		textCash.setText("Cash: " + this.result.getCashCollected());
		
		textCarCrashed.setText("Car crashed: " + this.result.getCarCrashed());
		textCarCrashed.setX(Helper.getCameraX() + Constants.carCrashedX);
		
		
//		Helper.println("TextScoreX: " + textScore.getX());
//		Helper.println("CameraX: " + Helper.getCameraX());
	}


	public void addCashCollected(int cash)
	{
		this.result.increaseCash(cash);
	}
	
	public void increaseScore(int score)
	{
		this.result.increaseScore(score);
	}
	
	public void addCarCrashed(int num)
	{
		this.result.setCarCrashed(this.result.getCarCrashed() + num);
	}
	

	public float getTotalScore()
	{
		return this.result.getTotalScore();
	}
	
	public float getHeroSpeed()
	{
		return this.heroCar.getSpeedX();
	}
}
