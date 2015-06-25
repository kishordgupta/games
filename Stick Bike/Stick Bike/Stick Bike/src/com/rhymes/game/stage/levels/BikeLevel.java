package com.rhymes.game.stage.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.menu.ButtonSkipChance;
import com.rhymes.game.entity.elements.nonphysical.Player;
import com.rhymes.game.entity.elements.path.traversal.PathNode;
import com.rhymes.game.entity.elements.path.traversal.Rope;
import com.rhymes.game.entity.elements.physical.Ball;
import com.rhymes.game.entity.elements.physical.Ball1;
import com.rhymes.game.entity.elements.physical.Ball3;
import com.rhymes.game.entity.elements.physical.BikeCollisionListener;
import com.rhymes.game.entity.elements.physical.Car;
import com.rhymes.game.entity.elements.physical.CollisionListener;
import com.rhymes.game.entity.elements.physical.Elevator;
import com.rhymes.game.entity.elements.physical.Ground;
import com.rhymes.game.entity.elements.physical.Lift;
import com.rhymes.game.entity.elements.physical.PhysicsHelper;
import com.rhymes.game.entity.elements.ui.ButtonQuit;
import com.rhymes.game.entity.elements.ui.ButtonRestart;
import com.rhymes.game.entity.elements.ui.PhysicsBody2;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.entity.elements.unsorted.Background;
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
	private Car car;
	public Ground h0;
	private Ground virtualBody;
	private BikeCollisionListener collisionListener;
	private Ground virtualEndBody;
	private String xmlPath;
	private XMLReader xMLReader;
	private Background background;

	private int levelNum = 0;
	
	
	 
	public String getXmlPath() {
		return xmlPath;
	}

	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	public boolean isInGround() {
		return isInGround;
	}

	public void setInGround(boolean isInGround) {
		this.isInGround = isInGround;
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
	}

	Background b;

	
	
	public float carStartX = 80 * GameMenuInfo.ratio_w,
			carStartY = 600 * GameMenuInfo.ratio_h;
	private ParticleEffect effect;

	public void adjustLevelParameters()
	{
		carStartX = XMLReader.ropes.get(0).getNodes().getFirst().getX()+ 100 * LevelInfo.ratioX;
		carStartY = XMLReader.ropes.get(0).getNodes().getFirst().getY()+ 30 * LevelInfo.ratioY ;
//		carStartY = 6000;
//		carStartY = XMLReader.ropes.get(0).getNodes().getFirst().getY()+ 30000 * LevelInfo.ratioY;
	}

	
	@Override
	public void tick(long stepTime) {
//		gameOver();
//		missionAchived();
//		GlobalVars.ge.getScreen().cam.zoom = 2;
		
		// if(this.gameState == GameState.OVER)
		// return;
		
//		Helper.println("Fumebody position: " + PhysicsHelper.ConvertToWorld(fumeBody.getPosition().x));
//		Helper.println("playerbody position: " + PhysicsHelper.ConvertToWorld(car.getBody().getPosition().x));
//		effect.setPosition(Helper.p2w(car.getFumeBody().getPosition().x), Helper.p2w(car.getFumeBody().getPosition().y));
		/*effect.setPosition(PhysicsHelper.ConvertToWorld(car.getBody().getPosition().x)  * LevelInfo.ratioX
				-(float)( 30 * LevelInfo.ratioY * Math.cos((car.getBody().getAngle() + 90 * MathUtils.degreesToRadians ))), 
				(float) (PhysicsHelper.ConvertToWorld(car.getBody().getPosition().y) * LevelInfo.ratioX
				+ 30 * LevelInfo.ratioY * Math.sin((car.getBody().getAngle()+ 90 * MathUtils.degreesToRadians) )));*/
		
//		Helper.println(car.getBody().getAngle() * MathUtils.radiansToDegrees + "");
		effect.getEmitters().get(0).getAngle().setLow(car.getBody().getAngle() * MathUtils.radiansToDegrees - 180 -15 );
		effect.getEmitters().get(0).getAngle().setHigh(car.getBody().getAngle() * MathUtils.radiansToDegrees - 180 -15) ;
		
	    
//		effect.getEmitters().get(0).getEmission().setLow(50);
//		effect.getEmitters().get(0).getEmission().setHigh(100);
//		effect.getEmitters().get(0).getLife().setLow(0.01f);
//		effect.getEmitters().get(0).getLife().setHigh(0.01f);
//		effect.getEmitters().get(0).getDuration().setLow(0.01f);
//		effect.getEmitters().get(0).getWind().setActive(false);
		effect.getEmitters().get(0).getVelocity().setLow(100);
		effect.getEmitters().get(0).getVelocity().setHigh(100);
		
		effect.update(stepTime/1000f);
		
//		GlobalVars.ge.getScreen().getBatch().setColor(0.1f, 0.1f, 0.1f, 1);
		effect.draw(GlobalVars.ge.getScreen().getBatch());
//		GlobalVars.ge.getScreen().getBatch().setColor(1f, 1f, 1f, 1);
		updateScores(stepTime);
		checkGameState();
		setInput();
	}

	float[] colors = {0.1f,0.1f,0.1f,0.1f,0.1f,0.1f,0.1f,0.1f,0.1f};
private void addParticles(float x, float y, String particleImageFolderPath, String particleFileName) {
	effect = new ParticleEffect();
    effect.load(Gdx.files.internal(particleFileName), Gdx.files.internal(particleImageFolderPath));
    effect.setPosition(x * LevelInfo.ratioX, y * LevelInfo.ratioY);
    
//    effect.getEmitters().get(0).setAdditive(true);
//    effect.getEmitters().get(0).setContinuous(true);
    effect.getEmitters().get(0).getScale().setHigh(15 * LevelInfo.ratioX);
    
    effect.getEmitters().get(0).getTint().setColors(colors);
    effect.getEmitters().get(0).getTint().setActive(true);
    effect.getEmitters().get(0).getTransparency().setActive(true);
    effect.getEmitters().get(0).getTransparency().setLow(0.3f);
    effect.getEmitters().get(0).getTransparency().setHigh(0.5f);
    effect.start();
}
	
	
	float fallY = 0; 
	@Override
	public void loadElements() {
		loadFonts();
		ScoreManage.reset();
		stopSounds();
		isInGround = true;
		xMLReader.main(AssetConstants.XMLPATH + levelNum + ".xml");

		adjustLevelParameters();
		
		world = new World(new Vector2(0, -7.5f), true);
		
		
		addParticles(Gdx.graphics.getWidth() / 2 * LevelInfo.ratioX, LevelInfo.ratioY * Gdx.graphics.getHeight() / 2, AssetConstants.PARTICLE_FOLDER,
				AssetConstants.PART_FIRE);

		b = new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_PAGE_BLANK);
		addElementZSorted(b);

		collisionListener = new BikeCollisionListener();
		world.setContactListener(collisionListener);
		collisionListener.setCollided(true);
		InteractionTouch screenTouch = new InteractionTouch();
		this.gameControlInteractions.add(screenTouch);
		// TODO Auto-generated method stub
//		virtualBody = new Ground(10 * GameMenuInfo.ratio_w,
//				1 * GameMenuInfo.ratio_h, 05 * GameMenuInfo.ratio_w,
//				50 * GameMenuInfo.ratio_h, 0f, AssetConstants.IMG_GROUND_2,
//				world, false);
//		addElement(virtualBody);

		//
		// background = new Background(0, 0, 480*GameMenuInfo.ratio_w,
		// 320*GameMenuInfo.ratio_h, 1,
		// AssetConstants.IMG_BKG_LEVEL7);
		// addElement(background);
		// screenTouch.subscribe(background);
		car = new Car(carStartX, carStartY,
//				45 * GameMenuInfo.ratio_w, 10 * GameMenuInfo.ratio_h, 0f,
				45 , 10, 0f,
				AssetConstants.IMG_CL_BKG, world, false);
		addElement(car);
		
		// screenTouch.subscribe(car);
		// Rope rope = new Rope(new
		// Vector2(virtualBody.getX(),virtualBody.getY()),
		// virtualBody.getBody(), 20, world,false,20,05);
		// addElement(rope);
		// float nextStartX =
		// PhysicsHelper.ConvertToWorld(rope.getLastLink().getPosition().x)+rope.getWidth()/2*19;/*virtualBody.getX()+virtualBody.getWidth()+rope.getWidth()*20;*/
		// float nextStartY = rope.getY();
		// nextStartX = 100;
		// Elevator elevator = new Elevator(world,100,new
		// Vector2(nextStartX,nextStartY), null, rope.getLastLink(), null, 20,
		// 05, 1);
		// addElement(elevator);

		// virtualEndBody = new
		// Ground(PhysicsHelper.ConvertToWorld(lift.elevator().getPosition().x)+lift.getWidth(),10*GameMenuInfo.ratio_h,
		// 50*GameMenuInfo.ratio_w,5*GameMenuInfo.ratio_h,90f,AssetConstants.IMG_GROUND_2,
		// world,false);
		// addElement(virtualEndBody);
		// Rope rope2 = new Rope(new
		// Vector2(virtualEndBody.getOriginX(),virtualEndBody.getY()),
		// virtualEndBody.getBody(), 10, world,false,20,05);
		// addElement(rope2);
		//
		// float nextX =
		// PhysicsHelper.ConvertToWorld(rope2.getLastLink().getPosition().x)+rope2.getWidth()*19;/*virtualBody.getX()+virtualBody.getWidth()+rope.getWidth()*20;*/
		// float nextY = rope.getY();

		// Helper.println("xml reader given rope no::::;"+xMLReader.ropes.get(0)+"xml reader given rope nodes ate::::;"+xMLReader.ropes.get(0).getNodes().get(0));
		//
		Elevator elevator0 = new Elevator(world, 30, new Vector2(
				xMLReader.ropes.get(0).getNodes().get(0).getX(),
				xMLReader.ropes.get(0).getNodes().get(0).getY()), null,
				LevelInfo.ratioX * 20, LevelInfo.ratioX * 5, 1);
		addElement(elevator0);
		// Elevator elevator1 = new Elevator(world,20,new
		// Vector2(xMLReader.ropes.get(1).getNodes().get(0).getX(),xMLReader.ropes.get(0).getNodes().get(0).getY()),
		// null, GameMenuInfo.ratio_w*20, GameMenuInfo.ratio_h*05, 1);
		// addElement(elevator1);

		// Lift lift = new Lift(new
		// Vector2(PhysicsHelper.ConvertToWorld(elevator0.getLastVertex().x)+300f,PhysicsHelper.ConvertToWorld(elevator0.getLastVertex().y-04f)),
		// null,null , null, 1, world, true,600,25);
		// addElement(lift);

		// virtualEndBody = new
		// Ground(lift.getX()+lift.getWidth(),10*GameMenuInfo.ratio_h,
		// 500*GameMenuInfo.ratio_w,15*GameMenuInfo.ratio_h,0f,AssetConstants.IMG_AXLE,
		// world,false);
		// addElement(virtualEndBody);

		// Elevator elevator2 = new Elevator(world,30,new
		// Vector2(virtualEndBody.getBody().getPosition()), null,
		// GameMenuInfo.ratio_w*20, GameMenuInfo.ratio_h*05, 1);
		// addElement(elevator2);
		// Elevator elevator3 = new Elevator(world,10,new
		// Vector2(elevator2.getLastVertex().x,elevator2.getLastVertex().y-2f),
		// null, GameMenuInfo.ratio_w*20, GameMenuInfo.ratio_h*05, 1);
		// addElement(elevator3);
		//
		// Elevator elevator4 = new Elevator(world,50,new
		// Vector2(elevator2.getLastVertex().x-2.5f,elevator2.getLastVertex().y-4.5f),
		// null, GameMenuInfo.ratio_w*20, GameMenuInfo.ratio_h*05, 1);
		// addElement(elevator4);

		// Ground virtualEndBody1 = new
		// Ground(PhysicsHelper.ConvertToWorld(elevator4.getLastVertex().x)+elevator4.getWidth()/2*25f,PhysicsHelper.ConvertToWorld(elevator4.getLastVertex().y)*GameMenuInfo.ratio_h,
		// 500*GameMenuInfo.ratio_w,10*GameMenuInfo.ratio_h,20f,AssetConstants.IMG_AXLE,
		// world,false);
		// addElement(virtualEndBody1);
		// Rope rope2 = new Rope(new
		// Vector2(virtualEndBody.getX(),virtualEndBody.getY()),
		// virtualEndBody.getBody(), 20, world,false,20,05);
		// addElement(rope2);

		// Helper.println("rope end Position in pixel::="+nextStartX+"lift start Position::="+lift.getX());
		// // float nextStartX =
		// PhysicsHelper.ConvertToWorld(rope.getLastLink().getPosition().x);
		// float nextStartY =
		// PhysicsHelper.ConvertToWorld(rope.getLastLink().getPosition().y);
		//

		// this.addControls();

		// setInput();

		float x = 0;
		float y = 0;

		// x =
		// GlobalVars.ge.getScreen().cam.position.x+Gdx.graphics.getWidth()/2f-37f*LevelInfo.ratioX;
		// y =
		// GlobalVars.ge.getScreen().cam.position.y+Gdx.graphics.getHeight()/2f-25f*LevelInfo.ratioY;
		x = Gdx.graphics.getWidth() - 75f * LevelInfo.ratioX;
		y = Gdx.graphics.getHeight() - 65f * LevelInfo.ratioY;
		pause = new ButtonPause(x, y, 48f * LevelInfo.ratioX,
				48f * LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_PAUSE, this);
		this.elements.add(pause);
		subscribeToControllingInteraction(pause, InteractionTouch.class);

		addText();

		this.gameState = GameState.PLAYING;
	}

	
	
	private void setInput() {
		Gdx.input.setInputProcessor(new InputProcessor() {

			@Override
			public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
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
				if (arg0 > Gdx.graphics.getWidth() * 2 / 3f) {
					if(car.getMotorSpeed() > 0.5f)
						StartupInfo.sound_handler.playSound(SoundHandler.soundType.SOUND_BIKE_GEARUP);
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
		assetPack.addTexture(AssetConstants.IMG_PAGE_BLANK);
//		assetPack.addTexture(AssetConstants.IMG_CL_BKG);
//		assetPack.addTexture(AssetConstants.IMG_GROUND_2);
//		assetPack.addTexture(AssetConstants.IMG_BALL_RUBBER);
		assetPack.addTexture(AssetConstants.PHY_IMG_CARBODY);
		assetPack.addTexture(AssetConstants.PHY_IMG_BIKEMAN);
		assetPack.addTexture(AssetConstants.PHY_IMG_LEFTAXLECONTAINER);
		assetPack.addTexture(AssetConstants.PHY_IMG_WHEEL);

		assetPack.addTexture(AssetConstants.IMG_BKG);
		assetPack.addTexture(AssetConstants.IMG_BTN_PAUSE);
		assetPack.addTexture(AssetConstants.IMG_BTN_RESTART);
		assetPack.addTexture(AssetConstants.IMG_BTN_RESUME);
		assetPack.addTexture(AssetConstants.IMG_BTN_QUITLEVEL);
		assetPack.addTexture(AssetConstants.IMG_SELECT);
		assetPack.addTexture(AssetConstants.IMG_BTN_GAMEOVER);
		assetPack.addTexture(AssetConstants.IMG_BTN_LEVELCOMPLETE);

		assetPack.addTexture(AssetConstants.IMG_PATH);
		// return null;
		return assetPack;
	}

	// private int gameState = GameState.PLAYING;

	
	Body fumeBody;
	public void setFumeBody(Body fumeBody)
	{
		this.fumeBody = fumeBody;
	}
	
	public void setFumePosition(float x, float y)
	{
		effect.setPosition(x, y);
	}

	private void checkGameState() {
		// Helper.println("\n\n Car x: " +
		// PhysicsHelper.ConvertToWorld(car.getBody().getWorldCenter().x));
		// Helper.println("P x: " + p.getX());

		// Helper.println("Game State:  " + this.gameState);
		if (PhysicsHelper.ConvertToWorld(car.getBody().getWorldCenter().x) > lastPathNode
				.getX() - 200)
			missionAchived();
		
		
		if (PhysicsHelper.ConvertToWorld(car.getBody().getWorldCenter().y) < fallY)
			gameOver();


		if (!isInGround) {
			car.destroyJoints();
			this.setInGround(true);
			gameOver();
			// reload();
		}
	}
	
	public void gameOver()
	{
		stopSounds();
		this.gameState = GameState.OVER;
		GameOver go = new GameOver(LevelInfo.GAME_FAILED, this);
		go.main();
	}

	public PathNode lastPathNode;
	
	public void setElevatorInfo(PathNode lastPathNode, float lastY)
	{
		this.lastPathNode = lastPathNode;
		this.fallY = lastY;
	}

	public void missionAchived() {
		stopSounds();
		this.gameState = GameState.OVER;
		currentDistance = (long) totalDistance;
		score.setDistance((long) totalDistance);
		score.setPercent(100);
//		updateScores(GlobalVars.ge.stepTime);

		textDistance.setText("Distance: " + score.getDistance()+" m");
		textTime.setText("Time: " + score.getTime()+" s");
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
		textDistance = new Text(x, y, fontController, AssetConstants.FONT_1,
				"Distance: " + score.getDistance());
//		textDistance.setColor(0.1f, 0.3f, 0.7f,1.0f);
//		textDistance.setColor(0.89f, 0.59f, 0.59f,1f);
		textDistance.setColor(0.1f, 0.1f, 0.1f,0.8f);
		addElement(textDistance);

		x = x + 350f * LevelInfo.ratioX;
		textTime = new Text(x, y, fontController, AssetConstants.FONT_1,
				"Time: " + score.getTime());
//		textTime.setColor(0.1f, 0.3f, 0.7f,1.0f);
//		textTime.setColor(0.59f, 0.59f, 0.59f,0.59f);
//		textTime.setColor(0.69f, 0.79f, 0.89f,1f);
		textTime.setColor(0.1f, 0.1f, 0.1f,0.8f);
		addElement(textTime);

		x = x + 350f * LevelInfo.ratioX;
		textLevelNumber = new Text(x, y, fontController, AssetConstants.FONT_1,
				"Level: " + LevelInfo.currentLevelNumber);
//		textLevelNumber.setColor(0.1f, 0.3f, 0.7f,1.0f);
//		textLevelNumber.setColor(0.59f, 0.59f, 0.59f,0.59f);
		textLevelNumber.setColor(0.1f, 0.1f, 0.1f,0.80f);
		addElement(textLevelNumber);

		x = 20f * LevelInfo.ratioX;
		y = 20f * LevelInfo.ratioY;
		textPercent = new Text(x, y, fontController, AssetConstants.FONT_1,
				"Percent: " + getPercentage());
//		textPercent.setColor(0.1f, 0.3f, 0.7f,1.0f);
		textPercent.setColor(0.1f, 0.1f, 0.1f,1.0f);
		addElement(textPercent);
		
		
	}

	
	boolean start = true;
	float curCarX, cx;
	public void updateScores(long stepTime) {
		
//		gameOver();
//		missionAchived();
		
		if(start)
		{
			carStartX = car.getBody().getPosition().x;
			this.totalDistance = PhysicsHelper.ConvertToBox(lastPathNode.getLocation().x) - carStartX;
			start = false;
		}
		cx = car.getBody().getPosition().x;
		currentDistance = (long) (cx-carStartX);
//		if(cx > curCarX){
//			currentDistance += (cx - curCarX);
//			curCarX = PhysicsHelper.ConvertToWorld(car.getBody().getPosition().x);
//		}
//		else{
//			currentDistance += (cx - curCarX);
//			curCarX = PhysicsHelper.ConvertToWorld(car.getBody().getPosition().x);
//		}
//		currentDistance += 5;
		score.setTime(stepTime);
		score.setDistance(currentDistance);
		score.setPercent(getPercentage());

		textDistance.setText("Distance: " + score.getDistance()+" m");
		textTime.setText("Time: " + score.getTime()+" s");
		textLevelNumber.setText("Level: " + LevelInfo.currentLevelNumber);
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

		b.setLocation(
				GlobalVars.ge.getScreen().cam.position.x
						- Gdx.graphics.getWidth() / 2f,
				GlobalVars.ge.getScreen().cam.position.y
						- Gdx.graphics.getHeight() / 2f);

	}

	public double totalDistance = 10000;
	public long currentDistance = 0;
	ScoreManage score = new ScoreManage();
	public Text textDistance;
	public Text textTime;
	public Text textLevelNumber;
	public Text textPercent;
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
	
	public void stopSounds()
	{
		StartupInfo.sound_handler.stopSound(soundType.SOUND_BIKE_GEARUP);
		StartupInfo.sound_handler.stopMusic(musicType.SOUND_BIKE_RUNNING);
		StartupInfo.sound_handler.stopSound(soundType.SOUND_BIKE_START);
	}
}


