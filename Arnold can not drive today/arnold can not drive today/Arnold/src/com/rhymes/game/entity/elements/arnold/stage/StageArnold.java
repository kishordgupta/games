package com.rhymes.game.entity.elements.arnold.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.elements.arnold.ArnoldGameConstants;
import com.rhymes.game.entity.elements.arnold.element.Arnold;
import com.rhymes.game.entity.elements.arnold.element.BackGroundArnold;
import com.rhymes.game.entity.elements.arnold.element.states.fall.StateFall_Left;
import com.rhymes.game.entity.elements.arnold.element.states.fall.StateFall_Right;
import com.rhymes.game.entity.elements.arnold.stage.menu.gameovermenu.GameOverScreenArnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.ui.ArrowButtonLeftArnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.ui.ArrowButtonRightArnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.ui.BackToLevelMenuFrom_UI_Arnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.ui.score.HighScore_UI_Arnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.ui.score.Score_UI_Arnold;
import com.rhymes.game.entity.elements.ui.buttonlabel.Text;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;
import com.rhymes.helpers.Helper;

public class StageArnold extends StageBase{
	
	
	
	public static boolean is_sensor_active = false;

	public int level;
	public BackGroundArnold back_ground;
	public Arnold arnold;
	
	float x, y;
	
	public InteractionTouch interaction_touch;
	
	public BackToLevelMenuFrom_UI_Arnold back;
	public Score_UI_Arnold score_ui;
	public HighScore_UI_Arnold high_score_ui;
	
//	public SensorButtonArnold sensor_button;
	public ArrowButtonLeftArnold arrow_left;
	public ArrowButtonRightArnold arrow_right;
	
	public BitmapFont  statusFont;
	public Text score;
	public Text high_score;
	public Text inputStatus;
	
	
	
	public void set_zooming_time_of_level(int level){
		if(level == 0)
			back_ground.TargetTime = 50f * 1000f;
		else if(level == 1)
			back_ground.TargetTime = 100f * 1000f;
		else if(level == 2)
			back_ground.TargetTime = 150f * 1000f;
	}
	
	
	
	private void loadFonts() {
		font = TrueTypeFontFactory.createBitmapFont  
		(Gdx.files.internal(AssetConstants.font_arnold), Text.getFrontChars(),
				12.5f, 7.5f, 1.0f, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		
		font.setColor(1f, 1f, 1f, 1f);
		
		statusFont = TrueTypeFontFactory.createBitmapFont  
				(Gdx.files.internal(AssetConstants.font_arnold), Text.getFrontChars(),
						12.5f, 7.5f, 1.0f, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		statusFont.setScale(0.5f, 0.5f);
		statusFont.setColor(1f, 1f, 1f, 1f);
	}
	
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param level
	 */
	public StageArnold(int level){
		this.level = level;
		
	}
	

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.BACK_UI_ARNOLD_d);
		assetPack.addTexture(AssetConstants.BACK_UI_ARNOLD_n);

		assetPack.addTexture(AssetConstants.SCORE_UI_ARNOLD);
		assetPack.addTexture(AssetConstants.HIGHSCORE_UI_ARNOLD);

		assetPack.addTexture(AssetConstants.radio_button_arnold_off);
		
		assetPack.addTexture(AssetConstants.arrow_left_arnold);
		assetPack.addTexture(AssetConstants.arrow_right_arnold);
		
//		assetPack.addTexture(AssetConstants.sensor_button_arnold_on);
//		assetPack.addTexture(AssetConstants.sensor_button_arnold_off);

		
		return assetPack;
	}

	@Override
	public void loadElements() {

		StartupInfo.sound_handler.playMusic(musicType.MUSIC_ARNOLD);
		
		this.interaction_touch = new InteractionTouch();
		this.interactions.add(this.interaction_touch);
		
		loadFonts();
		
		setGame();
		set_ui_elements();
		set_score();
		
		StartupInfo.sound_handler.stopMusic(musicType.MUSIC_MENU);
		StartupInfo.sound_handler.playMusic(musicType.MUSIC_ARNOLD);
	}
	
	public void set_score() {
		x = Gdx.graphics.getWidth() - 90 * ArnoldGameConstants.ratio_w;
		y = Gdx.graphics.getHeight() - 100f * ArnoldGameConstants.ratio_h
		-  ArnoldGameConstants.button_height_ui/2f + 12f * ArnoldGameConstants.ratio_h;
		
		score = new Text(x, y,
				1f, 
				1f, 
				font, 
				""+(int)arnold.getTravelled_distance());
		this.topElements.add(score);

		
		y = y - 60f * ArnoldGameConstants.ratio_h;
		
		high_score = new Text(x, y,
				1f, 
				1f, 
				font, 
				""+(int)arnold.getTravelled_distance());
		this.topElements.add(high_score);
	}
	
	public void addInputStatus(float x, float y, String text)
	{
		if(inputStatus == null){
			inputStatus = new Text(x, y,
					1f, 
					1f, 
					statusFont, 
					text);
			this.topElements.add(inputStatus);
		}
		else
		{
			inputStatus.setText(text);
		}	
	}


	public void set_ui_elements(){
		x = 1/8f * Gdx.graphics.getWidth() - 
		(Helper.getImageFromAssets(AssetConstants.BACK_UI_ARNOLD_d).getRegionWidth()/2f *ArnoldGameConstants.ratio_w) /*ArnoldGameConstants.button_width_ui/2f*/;
		y = Gdx.graphics.getHeight() - 100f * ArnoldGameConstants.ratio_h
		- /*(Helper.getImageFromAssets(AssetConstants.BACK_UI_ARNOLD_d).getRegionHeight()/2f **/ ArnoldGameConstants.button_height_ui/2f;
		
		back = new BackToLevelMenuFrom_UI_Arnold(x, y, 
				Helper.getImageFromAssets(AssetConstants.BACK_UI_ARNOLD_d).getRegionWidth() *ArnoldGameConstants.ratio_w /*ArnoldGameConstants.button_width_ui*/, 
				Helper.getImageFromAssets(AssetConstants.BACK_UI_ARNOLD_d).getRegionHeight() *ArnoldGameConstants.ratio_h /*ArnoldGameConstants.button_height_ui*/, 
				1, 
				AssetConstants.BACK_UI_ARNOLD_d, 
				AssetConstants.BACK_UI_ARNOLD_n);
		this.topElements.add(back);
		this.interaction_touch.subscribe(back);
		
		x = 3/4f * Gdx.graphics.getWidth() - 
		Helper.getImageFromAssets(AssetConstants.HIGHSCORE_UI_ARNOLD).getRegionWidth()/2f *ArnoldGameConstants.ratio_w /*ArnoldGameConstants.score_width_ui/2f*/;
		
		score_ui = new Score_UI_Arnold(x, y, 
				Helper.getImageFromAssets(AssetConstants.SCORE_UI_ARNOLD ).getRegionWidth()*ArnoldGameConstants.ratio_w /*ArnoldGameConstants.score_width_ui*/,
				Helper.getImageFromAssets(AssetConstants.SCORE_UI_ARNOLD ).getRegionHeight()*ArnoldGameConstants.ratio_h/* ArnoldGameConstants.score_height_ui*/,
				1,
				AssetConstants.SCORE_UI_ARNOLD);
		this.topElements.add(score_ui);
		
		y = y - 60f * ArnoldGameConstants.ratio_h;
		high_score_ui = new HighScore_UI_Arnold(x, y, 
				Helper.getImageFromAssets(AssetConstants.HIGHSCORE_UI_ARNOLD ).getRegionWidth()*ArnoldGameConstants.ratio_w /*ArnoldGameConstants.score_width_ui*/,
				Helper.getImageFromAssets(AssetConstants.HIGHSCORE_UI_ARNOLD ).getRegionHeight()*ArnoldGameConstants.ratio_h /*ArnoldGameConstants.score_height_ui*/,
				1, AssetConstants.HIGHSCORE_UI_ARNOLD);
		this.topElements.add(high_score_ui);
		
		
		x = Gdx.graphics.getWidth()/2f - 330f*ArnoldGameConstants.ratio_w - ArnoldGameConstants.arrow_w/2f;
		y = 50*ArnoldGameConstants.ratio_h;
		
		arrow_left = new ArrowButtonLeftArnold(x, y, ArnoldGameConstants.arrow_w, ArnoldGameConstants.arrow_h, 1, AssetConstants.arrow_left_arnold);
		this.topElements.add(arrow_left);
		
		
		x = Gdx.graphics.getWidth()/2f + 330f*ArnoldGameConstants.ratio_w - ArnoldGameConstants.arrow_w/2f;
		
		arrow_right = new ArrowButtonRightArnold(x, y, ArnoldGameConstants.arrow_w, ArnoldGameConstants.arrow_h, 1, AssetConstants.arrow_right_arnold);
		this.topElements.add(arrow_right);
		
	}
	
	
	
	public void setGame(){
		setBackGround(level);
		set_zooming_time_of_level(level);
		setArnold();
		generate_game_state();
		
		time_begin = 0;
	}
	
	
	public void setArnold(){
		x = Gdx.graphics.getWidth()/2f + (Arnold.minimum_width/2f) - 8f * ArnoldGameConstants.ratio_h;
		y = Gdx.graphics.getHeight()/2f - Arnold.minimum_height - 100f *ArnoldGameConstants.ratio_h;
		
		arnold = new Arnold(x, y, 1);
		this.elements.add(arnold);
	}
	
	public void setBackGround(int level){
		
		x = 0f;
		y = 0f;
		
		if(level == 0){
			back_ground = new BackGroundArnold(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
					1,
					AssetConstants.ARNOLD_BG_1_1_1, 
					AssetConstants.ARNOLD_BG_1_2_1,
					AssetConstants.ARNOLD_BG_1_3_1,
					AssetConstants.ARNOLD_BG_1_4_1);
		}
		
		else if(level == 1){
			back_ground = new BackGroundArnold(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
					1,
					AssetConstants.ARNOLD_BG_2_1_1, 
					AssetConstants.ARNOLD_BG_2_2_1,
					AssetConstants.ARNOLD_BG_2_3_1,
					AssetConstants.ARNOLD_BG_2_4_1);
		}
		
		else if(level == 2){
			back_ground = new BackGroundArnold(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
					1,
					AssetConstants.ARNOLD_BG_3_1_1, 
					AssetConstants.ARNOLD_BG_3_2_1,
					AssetConstants.ARNOLD_BG_3_3_1,
					AssetConstants.ARNOLD_BG_3_4_1);
		}
		this.elements.add(back_ground);
		this.interaction_touch.subscribe(back_ground);
	}
	
	
	
	/**
	 * game play components & functions
	 */

	int random_negetive, random_positive, random_angle, random_x;
	int max, min;

	public void generate_game_state(){
//		generate_for_angle();
		generate_for_pos_x();
	}
	
	public void generate_for_pos_x(){
		max = 2;
		min = 1;
		random_positive = min + (int) Math.random() * ((max - min) + 1);

		max = -1;
		min = -2;
		random_negetive = min + (int) Math.random() * ((max - min) + 1);

		max = random_positive;
		min = random_negetive;
		random_x = min + (int) Math.random() * ((max - min) + 1);
		
		arnold_horizontal_x_value = (float) random_x;
		arnold.setX(arnold.getX() + arnold_horizontal_x_value);
		
		arnold.update_state(arnold.getX());
		
//		Helper.println("GENERATING x, y :::"+arnold.getX()+" , "+arnold.getY());
	}
	
	
	public void generate_for_angle(){
		max = 3;
		min = 1;
		random_positive = min + (int) Math.random() * ((max - min) + 1);
		
		max = -1;
		min = -3;
		random_negetive = min + (int) Math.random() * ((max - min) + 1);

		max = random_positive;
		min = random_negetive;
		random_angle = min + (int) Math.random() * ((max - min) + 1);
		
		angle_arnold = (float) random_angle;
		
		arnold.set_angle(angle_arnold);
	}
	
	
	
	public float get_tapped_angle(int region){
		if(region == BackGroundArnold.REGION_LEFT){
			user_tapped_angle = angle_per_tapping;
		}
		
		else if(region == BackGroundArnold.REGION_RIGHT){
			user_tapped_angle = - (angle_per_tapping);
		}
		
		return user_tapped_angle;
	}
	
	
	
	/**
	 * maintaining game angle components & functions
	 */
	
	public int user_input_region;
	public float angle_arnold;
	public float user_tapped_angle;
	public float generated_angle;
	public float angle_per_tapping = 15f;
	public float angle_per_increasing = 0.1f;
	
	public void update_game_angle(){
		angle_arnold = arnold.get_angle();
		
		if(angle_arnold <= 0){
			angle_arnold = angle_arnold + (-angle_per_increasing);
			
			arnold.set_angle(angle_arnold);
		}
		
		else if(angle_arnold > 0){
			angle_arnold = angle_arnold + angle_per_increasing;
			
			arnold.set_angle(angle_arnold);
		}
	}
	
	
	
	/**
	 * maintaining distance components & functions
	 */
	
	public long time_begin;
	public long time_end = 0;
	public long time_per_distance_iteration = 1000;
	
	public float distance_per_iteration = 1f;

	public void update_distance_per_time(long steptime){
		if(arnold== null)
			return;
		
		time_end = time_end + steptime;
		
		if((time_end - time_begin) >= time_per_distance_iteration){
			
			arnold.setTravelled_distance(arnold.getTravelled_distance() + distance_per_iteration);
//			update_arnold_ui();
			
//			Helper.println("score updated");
			
			time_begin = 0;
			time_end = 0;
		}
	}
	
	int i;
	public float user_tapped_horizontal_distance;
	public float horizontal_distance_per_tapping = 0.3f;
	public float horizontal_distance_increasing;
	
	public final float increasing_for_state_4 = 0.28f;
	public final float increasing_for_state_3 = 0.2f;
	public final float increasing_normal = 0.08f;
	
	public float arnold_horizontal_x_value;
	
	public void get_user_tapped_horizontal_distance(int region){
		
		if(!(arnold.getState() instanceof StateFall_Left) && !(arnold.getState() instanceof StateFall_Right)){
		
			if(region == BackGroundArnold.REGION_LEFT){
				for(i = 0; i < (int)horizontal_distance_per_tapping; i++){
					arnold.setX(arnold.getX() - (i+1));
					arnold.update_state(arnold.getX());
				}
			}
			
			else if(region == BackGroundArnold.REGION_RIGHT){
				for(i = 0; i < (int)horizontal_distance_per_tapping; i++){
					arnold.setX(arnold.getX() + (i+1));
					arnold.update_state(arnold.getX());
				}
			}
		}
	}
	
///////////
	public void update_arnold_for_tapping(){
		if(arnold== null || arnold.getState() == null)
			return;
		if(!(arnold.getState() instanceof StateFall_Left) && !(arnold.getState() instanceof StateFall_Right)){
			if(user_input_region == BackGroundArnold.REGION_LEFT){
				arnold.setX(arnold.getX() - horizontal_distance_per_tapping);
				arnold.update_state(arnold.getX());
			}
			
			else if(user_input_region == BackGroundArnold.REGION_RIGHT){
				arnold.setX(arnold.getX() + horizontal_distance_per_tapping);
				arnold.update_state(arnold.getX());
			}
		}
	}
	
///////////	
	public void update_horizontal_x(){
		if(arnold== null || arnold.getState() == null)
			return;
		if(arnold.getX() > arnold.default_x + 16f && arnold.getX() <= arnold.default_x + 20f)
			horizontal_distance_increasing = increasing_for_state_4;
		else if(arnold.getX() > arnold.default_x - 16f && arnold.getX() <= arnold.default_x - 20f)
			horizontal_distance_increasing = increasing_for_state_4;
		
		else if(arnold.getX() < arnold.default_x - 12f && arnold.getX() >= arnold.default_x - 16f)
			horizontal_distance_increasing = increasing_for_state_3;
		else if(arnold.getX() > arnold.default_x + 12f && arnold.getX() <= arnold.default_x + 16f)
			horizontal_distance_increasing = increasing_for_state_3;
		
		else
			horizontal_distance_increasing = increasing_normal;
			
		
		
		arnold_horizontal_x_value = arnold.getX();
		
		if(arnold_horizontal_x_value <= arnold.default_x){
			arnold.setX(arnold_horizontal_x_value - horizontal_distance_increasing);
			arnold.update_state(arnold.getX());
		}
		
		else if(arnold_horizontal_x_value > arnold.default_x){
			arnold.setX(arnold_horizontal_x_value + horizontal_distance_increasing);
			arnold.update_state(arnold.getX());

		}
	}
	
	
	
	public float added_width 	= 2f * ArnoldGameConstants.ratio_w;
	public float added_hight 	= 4f * ArnoldGameConstants.ratio_h;
	public float updated_y 		= 2f * ArnoldGameConstants.ratio_w;

	public void update_arnold_ui(){
		if(arnold.getY() >= 50f * ArnoldGameConstants.ratio_h){
			arnold.setY(arnold.getY() - updated_y);
			
			if(arnold.getWidth() <= Arnold.maximum_width && arnold.getHeight() <= Arnold.maximum_height){
				arnold.setWidth(arnold.getWidth() + added_width);
				arnold.setHeight(arnold.getHeight() + added_hight);
			}
		}
	}

	
	/**
	 * checking game over
	 */
	public void check_game_over(){
		
		///for game over
		if(arnold== null || arnold.getState() == null)
			return;	
		
		if(arnold.getState() instanceof StateFall_Left){
			if(((AniLoop)(arnold.getState()).getAnimation()).isFinished()){
				this.gameState = GameState.OVER;
		
			}
		}
			
		if(arnold.getState() instanceof StateFall_Right){
			if(((AniLoop)(arnold.getState()).getAnimation()).isFinished()){
				this.gameState = GameState.OVER;
			}
		}
		
		
		
		///for ending the level
		if(arnold.getTravelled_distance() >= arnold.get_full_distance_on_level(this.level)){
//			if(((AniLoop)(arnold.getState()).getAnimation()).isFinished()){
				this.gameState = GameState.FINISHED;
//			}
		}
	}
	

	public void setHightScore(float score){
		if(score > highScore)
			highScore = score;
	}
	

	public void go_to_game_over_screen(){
		if(this.gameState == GameState.FINISHED){
			GlobalVars.ge.loadStage(new GameOverScreenArnold(this.level, false, (int)this.arnold.getTravelled_distance()));
			StartupInfo.sound_handler.pauseMusic(musicType.MUSIC_ARNOLD);
			setHightScore(this.arnold.getTravelled_distance());
		}
		
		else if(this.gameState == GameState.OVER){
			GlobalVars.ge.loadStage(new GameOverScreenArnold(this.level, true, (int)this.arnold.getTravelled_distance()));
			StartupInfo.sound_handler.pauseMusic(musicType.MUSIC_ARNOLD);
			setHightScore(this.arnold.getTravelled_distance());
		}
	}
	
	public static float highScore = 0;
	
	@Override
	public void tick(long stepTime) {
//		update_game_angle();
		update_horizontal_x();
		update_arnold_for_tapping();

		check_game_over();
		go_to_game_over_screen();
		
		update_distance_per_time(stepTime);
		
		if(arnold != null){
			
//			Helper.println("arnold w and h : "+arnold.getWidth()+" , "+arnold.getHeight() );
			
			score.setText(""+(int)arnold.getTravelled_distance());
			high_score.setText(""+(int)highScore);
		}
		
	}

}
