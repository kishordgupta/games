package com.rhymes.game.stage.levels;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.data.App;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.menu.ButtonSkipChance;
import com.rhymes.game.entity.elements.nonphysical.Player1;
import com.rhymes.game.entity.elements.nonphysical.Player2;
import com.rhymes.game.entity.elements.nonphysical.Player3;
import com.rhymes.game.entity.elements.nonphysical.Player4;
import com.rhymes.game.entity.elements.nonphysical.Player5;
import com.rhymes.game.entity.elements.nonphysical.Player6;
import com.rhymes.game.entity.elements.nonphysical.ScoreBounce;
import com.rhymes.game.entity.elements.nonphysical.Arrow;
import com.rhymes.game.entity.elements.nonphysical.Player;
import com.rhymes.game.entity.elements.nonphysical.ResultBounce;
import com.rhymes.game.entity.elements.nonphysical.ScoreShot;
import com.rhymes.game.entity.elements.physical.Ball;
import com.rhymes.game.entity.elements.physical.Ball1;
import com.rhymes.game.entity.elements.physical.Ball2;
import com.rhymes.game.entity.elements.physical.Ball3;
import com.rhymes.game.entity.elements.physical.CollisionListener;
import com.rhymes.game.entity.elements.physical.PhysicsBody;
import com.rhymes.game.entity.elements.ui.ButtonPause;
import com.rhymes.game.entity.elements.ui.ButtonQuit;
import com.rhymes.game.entity.elements.ui.ButtonRestart;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.entity.elements.ui.Text2;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.entity.elements.unsorted.NumericText;
import com.rhymes.game.interactions.ISingleCollision;
import com.rhymes.game.interactions.inputs.InteractionBounce;
import com.rhymes.game.interactions.inputs.InteractionFlick;
import com.rhymes.game.interactions.inputs.InteractionLeftRight;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.game.interactions.inputs.InteractionTouchCompas;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.game.stage.menus.MenuHome;
import com.rhymes.game.stage.result.Result;
import com.rhymes.game.stage.result.ResultBTRClassic;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.helpers.Helper;

public class BounceTest extends StageBase implements InteractionTouchCallbacks {
	@Override
	public void resume() {
		super.resume();
		this.fontLoaded = false;
		loadFonts();
		Helper.println("Resuming Bounce Test");
	}

	App physApp;
	protected World world;
	private CollisionListener collisionListener;
	private int pointer = 1;
	public Arrow arrow;
	public Result result = null;
	private int levelNumber = 1;
	private float playerPositionX = 30f * GameMenuInfo.ratio_w;
	private float playerPositionY = 30f * GameMenuInfo.ratio_h;
	private float playerWidth = 48f * GameMenuInfo.ratio_w;
	private float playerheight = playerWidth * 1.47f;
	private float ballPositionX = 66f * GameMenuInfo.ratio_w;
	private float ballPositionY = 70f * GameMenuInfo.ratio_h;
	public ArrayList<PhysicsBody> consumers = new ArrayList<PhysicsBody>();
	InteractionBounce ifl;

	/**
	 * @return the ballPositionX
	 */
	public float getBallPositionX() {
		return ballPositionX;
	}

	/**
	 * @param ballPositionX
	 *            the ballPositionX to set
	 */
	public void setBallPositionX(float ballPositionX) {
		this.ballPositionX = ballPositionX;
	}

	/**
	 * @return the ballPositionY
	 */
	public float getBallPositionY() {
		return ballPositionY;
	}

	/**
	 * @param ballPositionY
	 *            the ballPositionY to set
	 */
	public void setBallPositionY(float ballPositionY) {
		this.ballPositionY = ballPositionY;
	}

	/**
	 * @return the playerPositionX
	 */
	public float getPlayerPositionX() {
		return playerPositionX;
	}

	/**
	 * @param playerPositionX
	 *            the playerPositionX to set
	 */
	public void setPlayerPositionX(float playerPositionX) {
		this.playerPositionX = playerPositionX;
	}

	/**
	 * @return the playerPositionY
	 */
	public float getPlayerPositionY() {
		return playerPositionY;
	}

	/**
	 * @param playerPositionY
	 *            the playerPositionY to set
	 */
	public void setPlayerPositionY(float playerPositionY) {
		this.playerPositionY = playerPositionY;
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	// private TextTime time;
	private NumericText bouncePoint;
	// private NumericText basketInPoint;
	private NumericText scoreTotalPoint;
	private Text bouncePointText;
	private Text totalPointText;

	private boolean isInBasket = false;

	private ScoreBounce scoreBounce;
	private ScoreShot shotLeft;

	private int scoreTotal;

	Background background;
	private Ball ball;
	private Player player;
	private PhysicsBody collidedBPhysicsBody;
	protected PhysicsBody ballStick;
	protected PhysicsBody ground;
	private int playerNo;

	public int getPlayerNo() {
		return playerNo;
	}

	public void setPlayerNo(int playerNo) {
		this.playerNo = playerNo;
	}

	public void setPlayerandBall() {
		if (Constants.playerSelected == 1) {
			Player player = new Player1(playerPositionX, playerPositionY,
					playerWidth, playerheight, 90f, 1);
			addElement(player);
			setPlayer(player);
			Helper.println("addedplayerpawl");
		} else if (Constants.playerSelected == 2) {
			Player player = new Player2(playerPositionX, playerPositionY,
					playerWidth, playerheight, 90f, 1);
			addElement(player);
			setPlayer(player);
		} else if (Constants.playerSelected == 3) {
			Player player = new Player3(playerPositionX, playerPositionY,
					playerWidth, playerheight, 90f, 1);
			addElement(player);
			setPlayer(player);
		} else if (Constants.playerSelected == 4) {
			Player player = new Player4(playerPositionX, playerPositionY,
					playerWidth, playerheight, 90f, 1);
			addElement(player);
			setPlayer(player);
		} else if (Constants.playerSelected == 5) {
			Player player = new Player5(playerPositionX, playerPositionY,
					playerWidth, playerheight, 90f, 1);
			addElement(player);
			setPlayer(player);
		} else if (Constants.playerSelected == 6) {
			Player player = new Player6(playerPositionX, playerPositionY,
					playerWidth, playerheight, 90f, 1);
			addElement(player);
			setPlayer(player);
		}
		
		// Gdx.input.setInputProcessor((InteractionBounce)ifl);
		// InteractionFlick ifl;
		if (Constants.ballSelected == 1) {			
			Ball b = new Ball1(ballPositionX, ballPositionY,
					7f * GameMenuInfo.ratio_w, world, true);
			addElement(b);
			b.setWidth(b.radius * 2f * GameMenuInfo.ratio_w);
			b.setHeight(b.radius * 2f * GameMenuInfo.ratio_h);
			setBall(b);
			// ifl = new InteractionFlick(b);
			ifl = new InteractionBounce(b);
			// this.interactions.add(ifl);
			Gdx.input.setInputProcessor(ifl);
		} else if (Constants.ballSelected == 2) {
			Ball b = new Ball2(ballPositionX, ballPositionY,
					7f * GameMenuInfo.ratio_w, world, true);
			addElement(b);
			b.setWidth(b.radius * 2f * GameMenuInfo.ratio_w);
			b.setHeight(b.radius * 2f * GameMenuInfo.ratio_h);
			setBall(b);
			ifl = new InteractionBounce(b);
			// ifl = new InteractionFlick(b);
			// this.interactions.add(ifl);
			Gdx.input.setInputProcessor(ifl);
		} else if (Constants.ballSelected == 3) {
			Ball b = new Ball3(ballPositionX, ballPositionY,
					7f * GameMenuInfo.ratio_w, world, true);
			addElement(b);
			b.setWidth(b.radius * 2f * GameMenuInfo.ratio_w);
			b.setHeight(b.radius * 2f * GameMenuInfo.ratio_h);
			setBall(b);
			// ifl = new InteractionFlick(b);
			ifl = new InteractionBounce(b);
			// this.interactions.add(ifl);
			Gdx.input.setInputProcessor(ifl);
		}
	}

	public void loadGameElements() {
		// loading background
		// background = new Background(0, 0, 480, 320, 1,
		// AssetConstants.IMG_PLAYGROUND_BCKG);
		// addElement(background);
		// ground = new PhysicsBody(0, 0, 480, 22, "/ground/ground.bin",
		// AssetConstants.IMG_GROUND, "gfx\\ground.png", world,
		// ((short) 3));
		// addElement(ground);
		// // /adding ballStick
		// ballStick = new PhysicsBody(365, 25, 70, 169,
		// "/ballstick/ballstickFinal.bin", AssetConstants.IMG_BALLSTICK,
		// "gfx\\ballstickTrns.png", world, (short) 2);
		// addElement(ballStick);

	}

	// public BitmapFont fontImagica, fontSuperstar, fontPlok, fontNeon;
	private BitmapFont fontComboScore;
	private BitmapFont fontTopTotalScore;
	private BitmapFont fontTopCurrentScore;
	private BitmapFont fontTextScore;
	private BitmapFont fontGreeting;
	public BitmapFont fontLevelOver;
	public BitmapFont fontHitScore;
	private Text scoreText;

	public BitmapFont createFont(String fontPath) {
		return TrueTypeFontFactory.createBitmapFont(Gdx.files
				.internal(fontPath), Text2.getFrontChars(), 12.5f, 7.5f, 1.0f,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	
	public boolean fontLoaded = false;
	public void loadFonts() {
		if(fontLoaded)
			return;
		Helper.println("Creating font");
		fontComboScore = createFont(AssetConstants.FONT_IMAGICA);
		fontHitScore = createFont(AssetConstants.FONT_IMAGICA);
		fontTopTotalScore = createFont(AssetConstants.FONT_IMAGICA);
		fontTopCurrentScore = createFont(AssetConstants.FONT_IMAGICA);
		fontTextScore = createFont(AssetConstants.FONT_IMAGICA);
		fontLevelOver = createFont(AssetConstants.FONT_IMAGICA);
		// fontSuperstar = createFont(AssetConstants.FONT_SUPER_STAR);
		// fontPlok = createFont(AssetConstants.FONT_PLOK);
		// fontNeon = createFont(AssetConstants.FONT_NEON);
		fontGreeting = createFont(AssetConstants.FONT_PLOK);
		
		fontLoaded = true;
	}

	boolean firstTime = true;
	public void loadElements() {
/*		this.elements.clear();
		this.topElements.clear();
		this.interactions.clear();
		this.gameControlInteractions.clear();
		
		firstTime = true;
		initCumStepTime = 0;
		startNow = false;
		GlobalVars.ge.getCurrentStage().subscribeToInteraction(this,
				InteractionTouch.class);

		Helper.println("\n\n\n\n\n\n************************* BallposX: " + ballPositionX);
		if(StartupInfo.sound_handler.is_sound_active())
			StartupInfo.sound_handler
				.playSound(SoundHandler.soundType.SOUND_ENTER_EFFECT);
		loadFonts();
//		 result.reset();
		
		world = new World(new Vector2(0, -10f), true);
		collisionListener = new CollisionListener();
		world.setContactListener(collisionListener);
		collisionListener.setCollided(true);
		*//******* Load the Interactions *******//*
		InteractionTouch iTouch = new InteractionTouch();
		this.interactions.add(iTouch);
		InteractionTouchCompas aTouch = new InteractionTouchCompas();
		this.interactions.add(aTouch);
		ISingleCollision isc = new ISingleCollision();
		this.interactions.add(isc);
		loadGameElements();
		setPlayerandBall();
		arrow = new Arrow(0 * GameMenuInfo.ratio_w,
				-160 * GameMenuInfo.ratio_h, 19 * GameMenuInfo.ratio_w,
				20 * GameMenuInfo.ratio_h, 1);
		addElement(arrow);
		aTouch.subscribe(arrow);
//		reload();
		if (this.result instanceof ResultBounce) {
			 this.addElement(new Background(Gdx.graphics.getWidth() - 150
			 * GameMenuInfo.ratio_w, Gdx.graphics.getHeight() - 25
			 * GameMenuInfo.ratio_h, 60 * GameMenuInfo.ratio_w,
			 17 * GameMenuInfo.ratio_h, 1, AssetConstants.IMG_SCORE));
//			scoreText = new Text(Gdx.graphics.getWidth() - 190
//					* GameMenuInfo.ratio_w, Gdx.graphics.getHeight() - 25
//					* GameMenuInfo.ratio_h, .4f * GameMenuInfo.ratio_w,
//					.4f * GameMenuInfo.ratio_h, fontTextScore, "NOTHING", false);
//			addElement(scoreText);
//			scoreText.setText("SCORE");
//			scoreText.getFont().setColor(scoreColorR, scoreColorG, scoreColorB,
//					1f);

			// bouncePoint = new NumericText(Gdx.graphics.getWidth() -
			// 90*GameMenuInfo.ratio_w,
			// Gdx.graphics.getHeight() - 25*GameMenuInfo.ratio_h,
			// 15*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 1);
			// this.elements.add(bouncePoint);
			bouncePointText = new Text(Gdx.graphics.getWidth() - 300
					* GameMenuInfo.ratio_w, Gdx.graphics.getHeight() - 25
					* GameMenuInfo.ratio_h, .4f * GameMenuInfo.ratio_w,
					.4f * GameMenuInfo.ratio_h, fontTopCurrentScore, "NOTHING",
					false);
			addElement(bouncePointText);
			bouncePointText.getFont().setColor(scoreColorR, scoreColorG,
					scoreColorB, scoreColorT);

			
			 * scoreBounce = new ScoreBounce(Gdx.graphics.getWidth() - 220,
			 * Gdx.graphics.getHeight() - 150, 15, 30, 1);
			 * this.elements.add(scoreBounce);
			 
			shotLeft = new ScoreShot(Gdx.graphics.getWidth() - 80
					* GameMenuInfo.ratio_w, Gdx.graphics.getHeight() - 50
					* GameMenuInfo.ratio_h, 20 * GameMenuInfo.ratio_w,
					20 * GameMenuInfo.ratio_h, 1, Constants.ballSelected);
			this.elements.add(shotLeft);
			// score changed after the ball is in the basket
			// scoreTotalPoint = new NumericText(Gdx.graphics.getWidth() -
			// 90*GameMenuInfo.ratio_w,
			// Gdx.graphics.getHeight()-1*GameMenuInfo.ratio_h,
			// 15*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 1);
			// this.elements.add(scoreTotalPoint);
			totalPointText = new Text(Gdx.graphics.getWidth() - 80
					* GameMenuInfo.ratio_w, Gdx.graphics.getHeight() - 25
					* GameMenuInfo.ratio_h, .4f * GameMenuInfo.ratio_w,
					.4f * GameMenuInfo.ratio_h, fontTopTotalScore, "0", false);
			this.elements.add(totalPointText);
		}

		addControls();

		
		this.ball.getBody().setType(BodyType.StaticBody);
		
*/	}

	private void addControls() {
		InteractionTouch bTouch = new InteractionTouch();
		// this.interactions.add(bTouch);
		this.gameControlInteractions.add(bTouch);

		ButtonRestart bRestart = new ButtonRestart(0, Gdx.graphics.getHeight()
				- 35 * GameMenuInfo.ratio_h, 34 * GameMenuInfo.ratio_w,
				34 * GameMenuInfo.ratio_h, 1, AssetConstants.IMG_RELOAD,true);
		
		addElement(bRestart);
		
		
		ButtonSkipChance bSkip = new ButtonSkipChance(10f * GameMenuInfo.ratio_w
				, 10f * GameMenuInfo.ratio_h, 34 * GameMenuInfo.ratio_w,
				34 * GameMenuInfo.ratio_h, 1, AssetConstants.IMG_SKIP);
		
		
//		ButtonSkipChance bSkip = new ButtonSkipChance(50 * GameMenuInfo.ratio_w
//				, 10f * GameMenuInfo.ratio_h, 34 * GameMenuInfo.ratio_w,
//				34 * GameMenuInfo.ratio_h, 1, AssetConstants.IMG_SKIP);
		
		// ButtonRestart bRestart = new ButtonRestart(100, 100, 34, 34, 1,
		// AssetConstants.IMG_RELOAD);
		addElement(bSkip);
		subscribeToControllingInteraction(bSkip, InteractionTouch.class);
		// this.topElements.add(bRestart);
		bTouch.subscribe(bRestart);
		subscribeToControllingInteraction(bRestart, InteractionTouch.class);
		ButtonPause bPause = new ButtonPause(32 * GameMenuInfo.ratio_w,
				Gdx.graphics.getHeight() - 35 * GameMenuInfo.ratio_h,
				34 * GameMenuInfo.ratio_w, 34 * GameMenuInfo.ratio_h, 1,
				AssetConstants.IMG_PAUSE);
		addElement(bPause);
		// this.topElements.add(bPause);
		bTouch.subscribe(bPause);
		subscribeToControllingInteraction(bPause, InteractionTouch.class);

		ButtonQuit bQuit = new ButtonQuit(64 * GameMenuInfo.ratio_w,
				Gdx.graphics.getHeight() - 35 * GameMenuInfo.ratio_h,
				34 * GameMenuInfo.ratio_w, 34 * GameMenuInfo.ratio_h, 1,
				AssetConstants.IMG_QUIT);
		addElement(bQuit);
		// this.topElements.add(bQuit);
		bTouch.subscribe(bQuit);
		subscribeToControllingInteraction(bQuit, InteractionTouch.class);

	}

	public boolean shotOngoing = false;
	private Array<ElementBase> topElements;

	public void reset() {
		arrow.showArrow = true;
		shotOngoing = false;
		this.elements.clear();
		this.topElements.clear();
		this.interactions.clear();
		this.gameControlInteractions.clear();
		this.loadElements();
		this.loadTopElements();
		this.init();
		((ResultBounce) ((BounceTest) GlobalVars.ge.getCurrentStage()).result)
				.countShot();

	}
	
	@Override
	public void reload() {
		startNow = false;
		super.reload();
		Helper.println("Reloading");
		this.result.reset();
		arrow.showArrow = true;
		shotOngoing = false;
		((ResultBounce) result).setLevelScore(0);
	}

	public void loadTopElements() {
		// TODO Auto-generated method stub
		// scoreTotalPoint.setNumber((long) ((ResultBounce) this.result)
		// .getTotalBounceCollected());
		// this.topElements.add(scoreTotalPoint);
		totalPointText.setText("+"
				+ ((ResultBounce) this.result).getTotalBounceCollectedPoint());
		this.topElements.add(totalPointText);

	}

	public void gameOver() {
//		Helper.println("\n\n\nGame is over!");
//		Helper.println("Result: " + this.result.toString());
		// this.reset();
		// this.pause();
		// this.showGameOverScreen();
		this.showLevelFinished();
		this.result.reset();

		// /mehtod gameoverscreen

	}

	private void showLevelFinished() {
		// TODO Auto-generated method stub
		InteractionTouch bLevelFinish = new InteractionTouch();
/*
		if ((((ResultBounce) result).getTargetScore()) > (((ResultBounce) result)
				.getLevelScore())) {
			// Helper.println("showimage tryagain");
			GlobalVars.ge.loadStage(new GameFailedScreen(this));
			// ((ResultBounce) result).setLevelScore(0);
		} else if ((((ResultBounce) result).getTargetScore()) < (((ResultBounce) result)
				.getLevelScore())) {
			// Helper.println("showimage greatShot");
			GlobalVars.ge.loadStage(new GameOverScreen(this));
			// ((ResultBounce) result).setLevelScore(0);
		}*/

	}

	private void getAnotherChance() {
		// this.showTotalScore();
		int i = 0;
//		for (i = 0; i < 10000; i++) {
//			// Helper.println("just pasuse here for some moment");
//		}
		this.reset();
		this.result.setState(GameState.PLAYING);

		((ResultBounce) result).bounceCollected = 0;
		 Helper.println("Result:Another chacne " + this.result.toString());
	}

	// private void showTotalScore() {
	// // TODO Auto-generated method stub
	// // scoreTotalPoint.setNumber((long)
	// // ((ResultBounce)this.result).getTotalBounceCollected());
	//
	// scoreTotalPoint = new NumericText(Gdx.graphics.getWidth() -
	// 60*GameMenuInfo.ratio_w,
	// Gdx.graphics.getHeight() - 30*GameMenuInfo.ratio_h,
	// 15*GameMenuInfo.ratio_w, 20*GameMenuInfo.ratio_h, 1);
	// scoreTotalPoint.setNumber((long) ((ResultBounce) this.result)
	// .getTotalBounceCollected());
	// this.topElements.add(scoreTotalPoint);
	// }

	public void basketJustIn() {
		((CollisionListener) collisionListener).setCollided(false);
		if(StartupInfo.sound_handler.is_sound_active())
//		StartupInfo.sound_handler
//				.playSound(SoundHandler.soundType.SOUND_GOAL_EFFECT);
		// Text scoreText = new Text(ball.getX(), ball.getY(),
		// 0.5f * GameMenuInfo.ratio_w, 0.5f * GameMenuInfo.ratio_h,
		// fontGreeting, "", true);
		// addElement(scoreText);
		// scoreText.setText("+9999");
		// scoreText.getFont().setColor(scoreColorR, scoreColorG, scoreColorB,
		// scoreColorT);

/*		Text scoreGrettings = new Text(ball.getX() - 160 * GameMenuInfo.ratio_w,
				ball.getY(), 0.6f * GameMenuInfo.ratio_w,
				0.6f * GameMenuInfo.ratio_h, fontGreeting, "Awesome Shot", true);
		addElement(scoreGrettings);
		scoreGrettings.setText("+Awesome Shot");

		scoreGrettings.getFont().setColor(.02f, 0f,
			0f, 1f);
*/
		// scoreBounce.setImage(Helper
		// .getImageFromAssets(AssetConstants.IMG_SCORESTAR));

		((ResultBounce) result).scoreCollectionBasket();

		// Helper.println("i'm here");
		// getAnotherChance();
	}

	Point basketPoint = new Point(371, 134);
	public float scoreColorR = 1f;
	public float scoreColorG = 1f;
	public float scoreColorB = 1f;
	public float scoreColorT = 1f;

	public float initCumStepTime = 0;
	float cumTimeStep = 0;
	float physStepTime = 25;
	float initWaitTime = 2000;
	
	public boolean startNow = false;
	@Override
	public void tick(long stepTime) {
		cumTimeStep += stepTime;
		
		if(firstTime){
			firstTime = false;
			startNow  = false;
			initCumStepTime = 0;
			this.ifl.startNow = false;
		}
		
		if(!startNow){
			initCumStepTime += stepTime;
			Helper.println("initCumStepTime: " + initCumStepTime);
		}
		if(initCumStepTime >= initWaitTime){
			this.ifl.startNow = true;
			startNow  = true;
			initCumStepTime = 0;
//			this.ball.getBody().setType(BodyType.DynamicBody);
		}
		
//		Helper.println("\nStepTime: " + stepTime);
//		Helper.println("CumStepTime: " + cumTimeStep);
		if(cumTimeStep >= physStepTime)
		{
			cumTimeStep = 0;
			if (this.gameState == GameState.PLAYING){
				world.step(1/15f, 10, 10);
//				Helper.println("Stepping physics world");
			}
		}
		
		basketPoint.setLocation(ballStick.getX()
				+ (0.135f * ballStick.getWidth()), ballStick.getY()
				+ (0.72f * ballStick.getHeight()));
		try {
			if ((Point.distancePoint2Point(ball.getLocation(), basketPoint) <= ball.radius)
					&& !isInBasket) {

				basketJustIn();

				// this.isInBasket = true;
				this.setInBasket(true);
//			Helper
//					.println("Basket IN !.........................................................................");
			}
		} catch (Exception e) {
		}

		if (this.gameState == GameState.PLAYING) {
			// Helper.println("Result: " + result.toString()) ;
			if (this.result instanceof ResultBounce) {
				// bouncePoint.setNumber((long) ((ResultBounce) this.result)
				// .getBounceCollected());
				bouncePointText.setText(""
						+ ((ResultBounce) this.result).getBounceCollected());
				// bouncePointText.setSize(15, 10);

				// Helper.println("scoreTotalPoint::"+scoreTotalPoint);
				if (this.result.getState() == GameState.OVER) {
					getAnotherChance();
//					Helper.println("gac 1");
				} else if (ball.getX() >= Gdx.graphics.getWidth()
				// || ball.getY() >= Gdx.graphics.getHeight() + 40
						|| ball.getX() < 0 || ball.getY() < 0) {
//					Helper.println("gac 2");

					getAnotherChance();
				}
				//				
				//				
			}

		}

	}

	public boolean isInBasket() {
		return isInBasket;
	}

	public void setInBasket(boolean isInBasket) {
		this.isInBasket = isInBasket;
	}

	public PhysicsBody getCollidedBPhysicsBody() {
		return collidedBPhysicsBody;
	}

	public void setCollidedBPhysicsBody(PhysicsBody collidedBPhysicsBody) {
		this.collidedBPhysicsBody = collidedBPhysicsBody;
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		// assetPack.addTexture(AssetConstants.IMG_SHOT_BALL);
		// assetPack.addTexture(AssetConstants.IMG_AFTER_SHOT_BALL);
		assetPack.addTexture(AssetConstants.IMG_SCORE);
		assetPack.addTexture(AssetConstants.IMG_SCORESTAR);
		assetPack.addTexture(AssetConstants.IMG_JOINT);
		assetPack.addTexture(AssetConstants.IMG_TRYAGAIN);
		assetPack.addTexture(AssetConstants.FONT_IMAGICA);
		assetPack.addTexture(AssetConstants.FONT_PLOK);
		assetPack.addTexture(AssetConstants.FONT_NEON);
		assetPack.addTexture(AssetConstants.FONT_SUPER_STAR);
		assetPack.addTexture(AssetConstants.IMG_SKIP);
		
		return assetPack;
	}

	@Override
	public void onEvent(Point hitPoint) {
		// TODO Auto-generated method stub
		// this.reset();
//		StartupInfo.sound_handler.playSound(SoundHandler.soundType.SOUND_CLICK);
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public Ball getBall() {
		return ball;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public BounceTest() {
		super();
		this.levelNumber = 3;
		this.result = new ResultBounce();
		result.reset();
	}

	public BounceTest(int levelNumber, Result result) {
		super();
		this.levelNumber = levelNumber;
		this.result = result;

		if (result instanceof ResultBounce) {
			// ((ResultBounce)result).setBounceCollected(0);
			// ((ResultBounce)result).setTotalBounceCollected(10);
			result.reset();
		}
	}

	int comboCount = 0;
	PhysicsBody latest = null;
	private Text lastScoreText;


	PhysicsBody p ;
	int MAX_BOUNCE_ALLOWD = 17;
	int bounceCount = 0;
	
	public void showBounceScore(PhysicsBody collidedBody, Ball ball) {
		if(p == collidedBody)
		{
			bounceCount++;
			if(bounceCount > MAX_BOUNCE_ALLOWD)
				gameOver();
		}
		else{
			bounceCount = 1;
			p = collidedBody;
		}
		
		Helper.printKeyVal("Boucne count", bounceCount);
		// TODO Auto-generated method stub
		// collidedBody.getBodyType();
		// Helper.println("colllidedbody:: "+this.collidedBody.getId());
		// Helper.println("collided body::"+ this.collidedBody.getTop());

		if (!consumers.isEmpty()) {
			PhysicsBody latestConsumer = consumers.get(consumers.size() - 1);
//			Helper.println("latestConsumer" + latestConsumer.categoryBits);
//			Helper.println("collidedBody" + collidedBody.getBody().toString());
			
			
			if ((collidedBody != ground)) {
				((ResultBounce) result).scoreCollectionGround();
				if (latest == latestConsumer) {
					comboCount++;
					lastScoreText.setFont(fontComboScore);
					// lastScoreText.getFont().setScale(0.4f, 0.5f);
					lastScoreText.setText("combo" + " " + comboCount + "x100");
//					lastScoreText.getFont().setScale(0.5f,0.5f);
					lastScoreText.getFont().setColor(scoreColorR, scoreColorG,
							scoreColorB, scoreColorT);
				} else {
					Helper.println("comboCount:::::::::::::" + comboCount);
					latest = latestConsumer;
					comboCount = 1;
						// Text scoreText = new Text(ball.getX(), ball.getY(),
						// 0.5f * GameMenuInfo.ratio_w,
						// 0.5f * GameMenuInfo.ratio_h, fontPlok,
						// "NOTHING", true);
						// addElement(scoreText);
						float bx = ball.getX();
						float by = ball.getY();
						if (bx > (Gdx.graphics.getWidth() - (240 * GameMenuInfo.ratio_w))) {
							bx = bx - 240* GameMenuInfo.ratio_w;
						}
						/*Text comboScore = new Text(bx, ball.getY(),
								0.3f * GameMenuInfo.ratio_w,
								0.3f * GameMenuInfo.ratio_h, fontComboScore,
								"", true);
						addElement(comboScore);

						comboScore.setText("+100");
						// comboScore.getFont().setColor(0f, 0.7f, 0.8f, 1.0f);
//						comboScore.setFont(fontHitScore);
//						comboScore.getFont().setScale(0.5f, 0.5f);
						comboScore.getFont().setColor(scoreColorR, scoreColorG,
								scoreColorB, scoreColorT);

						lastScoreText = comboScore;
						*/
				}
			}
		}
	}
}

// if(!consumers.isEmpty())
// {
// for(int i=3;i<consumers.size();i++)
// {
//			
// if ((collidedBody != ground)) {
// if((consumers.get(i).categoryBits == consumers.get(i -
// 1).categoryBits)&&(consumers.get(i - 1).categoryBits==consumers.get(i -
// 2).categoryBits))
// {
// count++;
// if(count>10)
// {
// Text scoreText = new Text(ball.getX(), ball.getY(),
// 0.5f * GameMenuInfo.ratio_w,
// 0.5f * GameMenuInfo.ratio_h, fontImagica,
// "NOTHING", true);
// addElement(scoreText);
// scoreText.setText("10X100");
// scoreText.getFont().setColor(1.0f, 1.0f, 1.0f, 1.0f);
//				 
// }
// }
// {
// if ((consumers.get(i).categoryBits != consumers.get(i - 2).categoryBits)
// || (consumers.get(i - 2).categoryBits != consumers
// .get(i - 3).categoryBits)) {
// Helper.println("consumerbody:: "
// + consumers.get(i).categoryBits);
//
// // if ((collidedBody != ground)&&(collidedBody != ballStick)) {
// // ScoreBounce scoreBounce = new ScoreBounce(ball.getX(), ball.getY(), 50);
// // addElement(scoreBounce);
// // Text scoreText = new Text(ball.getX(), ball.getY(),
// // 0.5f * GameMenuInfo.ratio_w,
// // 0.5f * GameMenuInfo.ratio_h, fontImagica,
// // "NOTHING", true);
// // addElement(scoreText);
// // scoreText.setText("+100");
// // scoreText.getFont().setColor(1.0f, 1.0f, 1.0f, 1.0f);
// // }
// // ((ResultBounce) result).scoreCollectionBallStick();
// }
// }
// }
//				 

// }

// }

