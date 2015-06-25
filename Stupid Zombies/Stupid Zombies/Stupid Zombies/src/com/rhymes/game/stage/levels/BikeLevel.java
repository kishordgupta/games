package com.rhymes.game.stage.levels;

import java.util.LinkedList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.carspeedpro.AssetConstantsCarSpeedPro;
import com.rhymes.game.carspeedpro.menu.levelselection.SelectLevelMenuCarSpeedPro;
import com.rhymes.game.carspeedpro.menu.levelselection.resources.SelectionButtonCarSpeedPro.ButtonSpecific_tag;
import com.rhymes.game.carspeedpro.menu.vehicleselection.SelectVehicleMenuCarSpeedPro;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.CSPConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.csp.GearButton;
import com.rhymes.game.entity.elements.csp.RearForward;
import com.rhymes.game.entity.elements.csp.StartCounter;
import com.rhymes.game.entity.elements.menu.ButtonSkipChance;
import com.rhymes.game.entity.elements.nonphysical.Player;
import com.rhymes.game.entity.elements.path.traversal.Path;
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
import com.rhymes.game.entity.elements.physical.Ground;
import com.rhymes.game.entity.elements.physical.Lift;
import com.rhymes.game.entity.elements.physical.PhysicsHelper;
import com.rhymes.game.entity.elements.ui.ButtonQuit;
import com.rhymes.game.entity.elements.ui.ButtonRestart;
import com.rhymes.game.entity.elements.ui.PhysicsBody2;
import com.rhymes.game.entity.elements.ui.ProgressBar;
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
	
	private String carTypeString = Constants.carTypeSuper;
	private int worldTypeID = GW_TROPIC;
	ButtonSpecific_tag carType; 
	ButtonSpecific_tag carColor; 
	ButtonSpecific_tag worldType;
	ButtonSpecific_tag levelNumber;
		
	public BikeLevel() {
		
	}
	
	
	private void setLeveInfos()
	{
		levelNumber = SelectLevelMenuCarSpeedPro.selected_level_number_button.specific_tag;
		worldType = SelectLevelMenuCarSpeedPro.selected_level_mode_button.specific_tag;
		carType = SelectVehicleMenuCarSpeedPro.selected_vehicle_button.specific_tag;
		carColor = SelectVehicleMenuCarSpeedPro.selected_color_button.specific_tag;
	
//		Helper.printTest("car type: " + carType);
//		Helper.printTest("car color: " + carColor);
		
		setLevelNumber(levelNumber);
		setCarType(carType);
		setWorldType(worldType);
	}
	private void setCarColor(ButtonSpecific_tag color, Car car)
	{
		if(color == ButtonSpecific_tag.clr_0){
			car.setOverlayColor(1, 0, 0, 1);

		}
		
		else if(color == ButtonSpecific_tag.clr_1){
			car.setOverlayColor(1, 1, 0, 1);

		}
		
		else if(color == ButtonSpecific_tag.clr_2){
			car.setOverlayColor(0, 0, 1, 1);

		}
		
		else if(color == ButtonSpecific_tag.clr_3){
			car.setOverlayColor(0, 1, 0, 1);

		}
		
		else if(color == ButtonSpecific_tag.clr_4){
			car.setOverlayColor(1, 1, 1, 1);

		}
	}
	private void setWorldType(ButtonSpecific_tag worldType2) {
		if(worldType2 == ButtonSpecific_tag.city)
			this.worldTypeID = GW_CITY;
		else if(worldType2 == ButtonSpecific_tag.desert)
			this.worldTypeID = GW_DESERT;
		else if(worldType2 == ButtonSpecific_tag.tropic)
			this.worldTypeID = GW_TROPIC;
	}

	public void setCarType(ButtonSpecific_tag carType)
	{
		if(carType == ButtonSpecific_tag.benga)
			carTypeString = Constants.carTypeBenga;
		else if(carType == ButtonSpecific_tag.huwwer)
			carTypeString = Constants.carTypeHuwwer;
		else if(carType == ButtonSpecific_tag.krac)
			carTypeString = Constants.carTypeKrac;
		else if(carType == ButtonSpecific_tag.police)
			carTypeString = Constants.carTypePolice;
		else if(carType == ButtonSpecific_tag.school)
			carTypeString = Constants.carTypeSchoolbus;
		else if(carType == ButtonSpecific_tag.military)
			carTypeString = Constants.carTypeSuper;
	}
	
	public void setLevelNumber(ButtonSpecific_tag levelNumber)
	{
		if(levelNumber == ButtonSpecific_tag.level_0)
			levelNum = 1;
		else if(levelNumber == ButtonSpecific_tag.level_1)
			levelNum = 2;
		else if(levelNumber == ButtonSpecific_tag.level_2)
			levelNum = 3;
		else if(levelNumber == ButtonSpecific_tag.level_3)
			levelNum = 4;
		else if(levelNumber == ButtonSpecific_tag.level_4)
			levelNum = 5;
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
	private StartCounter startCounter;
	
	
	 
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
		if(System.currentTimeMillis() - lastHitTime < 500)
			return;
		
		lastHitTime = System.currentTimeMillis();
		
//		this.isInGround = isInGround;
		this.car.damage += 10;
		if(this.car.damage >= car.MAX_DAMAGE){
			this.isInGround = false;
			progressBar.setProgress(1);
		}
		else {
			progressBar.setProgress(car.damage / car.MAX_DAMAGE);
		}
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
		fontController.addFont(AssetConstants.FONT_1 + "2", AssetConstants.FONT_2,
				40, 30);
		fontController.addFont("time", AssetConstants.FONT_2,
				30, 20);
	}

	Background b;

	
	
	public float carStartX = 80 * GameMenuInfo.ratio_w,
			carStartY = 600 * GameMenuInfo.ratio_h;
	private ParticleEffect effect;

	public void adjustLevelParameters()
	{
		carStartX = XMLReader.ropes.get(0).getNodes().getFirst().getX()+ 100 * LevelInfo.ratioX;
		carStartY = XMLReader.ropes.get(0).getNodes().getFirst().getY()+ 10 * LevelInfo.ratioY ;
//		carStartY = 6000;
//		carStartY = XMLReader.ropes.get(0).getNodes().getFirst().getY()+ 30000 * LevelInfo.ratioY;
	}

	

private void addParticles(float x, float y, String particleImageFolderPath, String particleFileName) {
	effect = new ParticleEffect();
    effect.load(Gdx.files.internal(particleFileName), Gdx.files.internal(particleImageFolderPath));
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
	
	public static int GW_DESERT = 0;
	public static int GW_TROPIC = 1;
	public static int GW_CITY = 2;
	
	public void setGameWorld(int worldType)
	{
		if(worldType == GW_TROPIC)
		{
			imageBackGround = CSPConstants.TROPIC_BACK;
			imagePathCarpet = CSPConstants.TROPIC_CARPET;
			imagePathTexture = CSPConstants.TROPIC_TEXTURE;
		}
		else if(worldType == GW_DESERT)
		{
			imageBackGround = CSPConstants.DESERT_BACK;
			imagePathCarpet = CSPConstants.DESERT_CARPET;
			imagePathTexture = CSPConstants.DESERT_TEXTURE;
		}
		else if(worldType == GW_CITY)
		{
			imageBackGround = CSPConstants.CITY_BACK;
			imagePathCarpet = CSPConstants.CITY_CARPET;
			imagePathTexture = CSPConstants.CITY_TEXTURE;
		}
		
		backCSP = new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, imageBackGround);
		addElementZSorted(backCSP);
	}
	
	ProgressBar progressBar;
	float fallY = 0; 
	@Override
	public void loadElements() {
		setLeveInfos();
		loadFonts();
		ScoreManage.reset();
		stopSounds();

		setGameWorld(worldTypeID);
		
		isInGround = true;
		
		if(worldTypeID == GW_CITY)
			xMLReader.main(CSPConstants.XMLPATH_CITY + levelNum + ".xml");
		else if(worldTypeID == GW_DESERT)
			xMLReader.main(CSPConstants.XMLPATH_TROPIC + levelNum + ".xml");
		else if(worldTypeID == GW_TROPIC)
			xMLReader.main(CSPConstants.XMLPATH_TROPIC + levelNum + ".xml");
		createBuildings();
		adjustLevelParameters();
		

		world = new World(new Vector2(0, -10f), true);
		
		addParticles(Gdx.graphics.getWidth() / 2 * LevelInfo.ratioX, LevelInfo.ratioY * Gdx.graphics.getHeight() / 2, AssetConstants.PARTICLE_FOLDER, AssetConstants.PART_FIRE);

		collisionListener = new BikeCollisionListener();
		world.setContactListener(collisionListener);
		collisionListener.setCollided(true);
		InteractionTouch screenTouch = new InteractionTouch();
		this.gameControlInteractions.add(screenTouch);

		
		car= new Car(XMLReader.ropes.get(0).getNodes().getFirst().getX()+ 20 * GameMenuInfo.ratio_w, 
				XMLReader.ropes.get(0).getNodes().getFirst().getY()+ 20 * GameMenuInfo.ratio_h,
				45*GameMenuInfo.ratio_w,10*GameMenuInfo.ratio_h,0f, carTypeString, world,false, true);
		addElement(car);
		setCarColor(this.carColor, car);
		
		
		
		Elevator elevator0 = new Elevator(world, 30, new Vector2(
				xMLReader.ropes.get(0).getNodes().get(0).getX(),
				xMLReader.ropes.get(0).getNodes().get(0).getY()), null,
				LevelInfo.ratioX * 20, LevelInfo.ratioX * 20, 2, imagePathCarpet
		, imagePathTexture);
		addElementZSorted(elevator0);
		
		
		
		float x = 0;
		float y = 0;

		x = Gdx.graphics.getWidth() - 75f * LevelInfo.ratioX;
		y = Gdx.graphics.getHeight() - 65f * LevelInfo.ratioY;
		pause = new ButtonPause(x, y, 48f * LevelInfo.ratioX,
				48f * LevelInfo.ratioY, 1, CSPConstants.IMG_BTN_PAUSE, this);
		this.elements.add(pause);
		subscribeToControllingInteraction(pause, InteractionTouch.class);

		addText();
		
		progressBar = new ProgressBar(10, 10, Gdx.graphics.getWidth()/3f, 40 * LevelInfo.ratioY,
				CSPConstants.HEALTH_ACT, CSPConstants.HEALTH_DIS, true);
		this.addElement(progressBar);


		this.gameState = GameState.PLAYING;
		

		startCounter = new StartCounter(Gdx.graphics.getWidth()-42.5f,
				Gdx.graphics.getHeight()-64f,
				85 * LevelInfo.ratioX,
				128 * LevelInfo.ratioY, 1);
		startCounter.start();
		int i = addElementZAtTop(startCounter);
		
		gLeft = new GearButton(0,0, 128 * LevelInfo.ratioX, 256f * LevelInfo.ratioY, i);
		addElementZAtTop(gLeft);
		
		gRight = new GearButton(0,0, 128 * LevelInfo.ratioX, 256f * LevelInfo.ratioY, i);
		addElementZAtTop(gRight);
		
		rf = new RearForward(0,0, 128 * LevelInfo.ratioX, 128f * LevelInfo.ratioY, i);
		addElement(rf);
		
		subscribeToControllingInteraction(rf, InteractionTouch.class);
		
		
				
	}

	
//	public static Array<Path> ropes = new Array<Path>();
	
//	private void generateEqualSizedSegments(Point p, Point q, Path path)
//	{
//		Helper.println("\n\n\n\nBuildings added: " + p);
//		
//		float distance = p.distancePoint2Point(q);
//		float d = 0;
//		while(d < distance)
//		{
//			Point t = new Point();
//			p.getPointAtDistance(p, Helper.getAngle(p, q)+90, d, t);
//			d+=20;
//			path.addLast(t);
//			
//		}
//	}

	Random rand = new Random();
	float w, h;
	private void addTreeRandom(float x, float y){
		int i = rand.nextInt(4);
		x =x * LevelInfo.ratioX;
		y = y * LevelInfo.ratioX - 50 * LevelInfo.ratioY;
		
		w = 128;
		h = 256;
		if(i == 0)
			addElementZSorted(new Background(x, y, w * LevelInfo.ratioX, h * LevelInfo.ratioY, 1, 
					AssetConstantsCarSpeedPro.tree_1));
		else if(i == 1)
			addElementZSorted(new Background(x, y, w * LevelInfo.ratioX, h * LevelInfo.ratioY, 1, 
					AssetConstantsCarSpeedPro.tree2));
		else if(i == 2)
			addElementZSorted(new Background(x, y, w * LevelInfo.ratioX, h * LevelInfo.ratioY, 1, 
					AssetConstantsCarSpeedPro.tree3));
		else if(i == 3)
			addElementZSorted(new Background(x, y, w * LevelInfo.ratioX, h * LevelInfo.ratioY, 1, 
					AssetConstantsCarSpeedPro.tree4));
	}
	
	private void addBuildingRandom(float x, float y, LinkedList<PathNode> nodes, int j){
		int i = rand.nextInt(5);
		x =x * LevelInfo.ratioX;
		y = y * LevelInfo.ratioX - 30 * LevelInfo.ratioY;
		
		float d = 384 * LevelInfo.ratioX;
		for(int k = j+1; k < nodes.size(); k++)
		{
			d -= nodes.get(k-1).getLocation().distancePoint2Point(nodes.get(k).getLocation());
			if(nodes.get(k).getLocation().y < y)
				y = nodes.get(k).getLocation().y;
			if(d <= 0)
				break;
		}

		w = 384;
		h = 256;
		if(i == 0){
			w = 256;
			h = 128;
			addElementZSorted(new Background(x, y, w * LevelInfo.ratioX, h * LevelInfo.ratioY, 1, 
					AssetConstantsCarSpeedPro.house_01));
		}
		else if(i == 1)
		{
			addElementZSorted(new Background(x, y, w * LevelInfo.ratioX, h * LevelInfo.ratioY, 1, 
					AssetConstantsCarSpeedPro.house_02));
		}
		else if(i == 2){
			addElementZSorted(new Background(x, y, w * LevelInfo.ratioX, h * LevelInfo.ratioY, 1, 
					AssetConstantsCarSpeedPro.house_03));
		}
		else if(i == 3){
			addElementZSorted(new Background(x, y, w * LevelInfo.ratioX, h * LevelInfo.ratioY, 1, 
					AssetConstantsCarSpeedPro.house_04));
		}
		else if(i == 4){
			addElementZSorted(new Background(x, y, w * LevelInfo.ratioX, h * LevelInfo.ratioY, 1, 
					AssetConstantsCarSpeedPro.house_05));
		}
	}
	
	
	Point p;
	float r;
	
	float t = 1000;
	Point prev = new Point();
	private void createBuildings() {
		float distance;
		for (int i = 0; i < XMLReader.ropes.size; i++) {			
			int j = 0;
			if(i == 0)
				j = 1;
			for (; j < XMLReader.ropes.get(i).getNodes().size()-2; j++) {
				p = XMLReader.ropes.get(i).getNodes().get(j).getLocation();
				
				if(prev.distancePoint2Point(p) < t)
					continue;
				
				r = rand.nextInt(4);
				if(r == 0){
					addBuildingRandom(p.x, p.y, XMLReader.ropes.get(i).getNodes(), j);
				}
				else if(r >= 1)
					addTreeRandom(p.x, p.y);
				
				prev = p;
			}
		}
//		XMLReader.ropes = ropes;
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
//		assetPack.addTexture(CSPConstants.TROPIC_BACK);
//		assetPack.addTexture(CSPConstants.TROPIC_CARPET);
//		assetPack.addTexture(CSPConstants.TROPIC_TEXTURE);
		
		
//		assetPack.addTexture(AssetConstants.IMG_PAGE_BLANK);
//		assetPack.addTexture(AssetConstants.IMG_CL_BKG);
//		assetPack.addTexture(AssetConstants.IMG_GROUND_2);
//		assetPack.addTexture(AssetConstants.IMG_BALL_RUBBER);
//		assetPack.addTexture(AssetConstants.PHY_IMG_CARBODY);
//		assetPack.addTexture(AssetConstants.PHY_IMG_BIKEMAN);
//		assetPack.addTexture(AssetConstants.PHY_IMG_LEFTAXLECONTAINER);
//		assetPack.addTexture(AssetConstants.PHY_IMG_WHEEL);

//		assetPack.addTexture(AssetConstants.IMG_BKG);
//		assetPack.addTexture(AssetConstants.IMG_BTN_PAUSE);
		assetPack.addTexture(AssetConstants.IMG_BTN_RESTART);
		assetPack.addTexture(AssetConstants.IMG_BTN_RESUME);
		assetPack.addTexture(AssetConstants.IMG_BTN_QUITLEVEL);
		assetPack.addTexture(AssetConstants.IMG_SELECT);
		assetPack.addTexture(AssetConstants.IMG_BTN_GAMEOVER);
		assetPack.addTexture(AssetConstants.IMG_BTN_LEVELCOMPLETE);

//		assetPack.addTexture(AssetConstants.IMG_PATH);
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
	@Override
	public void tick(long stepTime) {
		
		if(startCounter.isStarted())
		{
			if(startCounter.isFinished())
				car.started = true;
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
//		if (PhysicsHelper.ConvertToWorld(car.getBody().getWorldCenter().x) > lastPathNode
//				.getX() - 200)
//			missionAchived();
		
		
		if (PhysicsHelper.ConvertToWorld(car.getBody().getWorldCenter().y) < fallY)
			gameOver();


		if (!isInGround) {
			car.destroyJoints();
			this.bodyHitGround(true);
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

//		textDistance.setText("Distance: " + score.getDistance()+" m");
//		textTime.setText("Time: " + score.getTime()+" s");
//		textPercent.setText("Percent: " + 100 + "%");
		
		GameOver go = new GameOver(LevelInfo.GAME_WIN, this);
		go.main();
		Helper.println("Game Mission Achived");
	}

	@Override
	public void onEvent(Point hitPoint) {
	}

	public void addText() {
		float x = 20f * LevelInfo.ratioX;
		float y = Gdx.graphics.getHeight() - 10f * LevelInfo.ratioY;
		textDistance = new Text(x, y, fontController, AssetConstants.FONT_1,
				"Distance: " + score.getDistance());
//		textDistance.setColor(0.1f, 0.3f, 0.7f,1.0f);
//		textDistance.setColor(0.89f, 0.59f, 0.59f,1f);
		textDistance.setColor(0.5f, 0.1f, 0.9f,0.8f);
		addElement(textDistance);

		x = x + 350f * LevelInfo.ratioX;
		textTime = new Text(x, y, fontController, "time",
				"Time: " + score.getTime());
//		textTime.setColor(0.1f, 0.3f, 0.7f,1.0f);
//		textTime.setColor(0.59f, 0.59f, 0.59f,0.59f);
//		textTime.setColor(0.69f, 0.79f, 0.89f,1f);
		textTime.setColor(0.5f, 0.1f, 0.9f,0.8f);
		addElement(textTime);

		x = x + 350f * LevelInfo.ratioX;
		textLevelNumber = new Text(x, y, fontController, AssetConstants.FONT_1,
				"Level: " + LevelInfo.currentLevelNumber);
//		textLevelNumber.setColor(0.1f, 0.3f, 0.7f,1.0f);
//		textLevelNumber.setColor(0.59f, 0.59f, 0.59f,0.59f);
		textLevelNumber.setColor(0.3f, 0.8f, 0.1f,0.80f);
//		addElement(textLevelNumber);

		x = 20f * LevelInfo.ratioX;
		y = 20f * LevelInfo.ratioY;
		textPercent = new Text(x, y, fontController, AssetConstants.FONT_1,
				"Percent: " + getPercentage());
//		textPercent.setColor(0.1f, 0.3f, 0.7f,1.0f);
		textPercent.setColor(0.1f, 0.1f, 0.1f,1.0f);
//		addElement(textPercent);
		
		
	}

	
	boolean start = true;
	float curCarX, cx;
	float speed = 0 ; 
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

		score.setTime(stepTime);
		score.setDistance(currentDistance);
		score.setPercent(getPercentage());

		
		
//		if(score.getTime() > 0)
//			speed = score.getDistance()/score.getTime();
//		else speed = 0;
		
		
		
		
		textTime.setText( score.getTime()/60 +"m " + score.getTime()%60 + "s") ;
		textLevelNumber.setText("Level: " + LevelInfo.currentLevelNumber);
		textPercent.setText("Percent: " + getPercentage() + "%");

		float x = GlobalVars.ge.getScreen().cam.position.x
				- Gdx.graphics.getWidth() / 2f + 100f * LevelInfo.ratioX;
		float y = GlobalVars.ge.getScreen().cam.position.y
				+ Gdx.graphics.getHeight() / 2f - 55f * LevelInfo.ratioY;


		
//		x = x + 350f * LevelInfo.ratioX;
		textTime.setX(x);
		textTime.setY(y+ 10 * LevelInfo.ratioY);
		
//		x = x + 480f * LevelInfo.ratioX;
//		textLevelNumber.setX(x);
//		textLevelNumber.setY(y);

		
//		x = GlobalVars.ge.getScreen().cam.position.x - Gdx.graphics.getWidth()
//				/ 2f + 20f * LevelInfo.ratioX;
//		y = GlobalVars.ge.getScreen().cam.position.y - Gdx.graphics.getHeight()
//				/ 2f + 15f * LevelInfo.ratioY;
//		textPercent.setX(x);
//		textPercent.setY(y);
		
		
//		x = GlobalVars.ge.getScreen().cam.position.x - Gdx.graphics.getWidth()
//				/ 2f + 20f * LevelInfo.ratioX;
//		y = GlobalVars.ge.getScreen().cam.position.y
//				+ Gdx.graphics.getHeight() / 2f - 35f * LevelInfo.ratioY;
		
		x = x - 80 * LevelInfo.ratioX;
		pause.setX(x);
		pause.setY(y);
		
		// progress bar
		
		x = x + 320 * LevelInfo.ratioX;
		progressBar.setLocation(x, y);
				
		
		// km/h
		textDistance.setText((int)(car.getSpeed()) + " KM/h ");
		textDistance.setX(GlobalVars.ge.getScreen().cam.position.x + Gdx.graphics.getWidth()/2f - 220 * LevelInfo.ratioX);
		textDistance.setY(y+ 10 * LevelInfo.ratioY);
		
		

		backCSP.setLocation(
				GlobalVars.ge.getScreen().cam.position.x
						- Gdx.graphics.getWidth() / 2f,
				GlobalVars.ge.getScreen().cam.position.y
						- Gdx.graphics.getHeight() / 2f);
		
		
		
		

		startCounter.setLocation(
				GlobalVars.ge.getScreen().cam.position.x
						- 42.5f * LevelInfo.ratioX,
				GlobalVars.ge.getScreen().cam.position.y
						+ 64f * LevelInfo.ratioY);


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



