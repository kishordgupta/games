package com.rhymes.game.entity.elements.arnold.element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.entity.elements.arnold.ArnoldGameConstants;
import com.rhymes.game.entity.elements.arnold.element.states.fall.StateFall_Left;
import com.rhymes.game.entity.elements.arnold.element.states.fall.StateFall_Right;
import com.rhymes.game.entity.elements.arnold.element.states.left.StateLeft_1;
import com.rhymes.game.entity.elements.arnold.element.states.normal.StateNormal;
import com.rhymes.game.entity.elements.arnold.element.states.right.StateRight_1;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.entity.states.StateBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Arnold extends ElementBase{
	
	public float distance_per_level;
	
	public float get_full_distance_on_level(int level){
		if(level == 0)
			distance_per_level = 50f;
		else if(level == 1)
			distance_per_level = 100f;
		else if(level == 2)
			distance_per_level = 150f;
		
		return distance_per_level;
	}
	
	public final float default_distance = 0f;
	
	public final float max_horizontal_distance_factor = 20f;

	public float default_x, default_y;
	
	public final float default_angle = 0f;
	
	private float distance_travelled;

	
	public static float minimum_width = 25f * ArnoldGameConstants.ratio_w;
	public static float minimum_height = 75f * ArnoldGameConstants.ratio_h;
	
	public static float maximum_width = /*Helper.getImageFromAssets(AssetConstants.normal_1).getRegionWidth() * ArnoldGameConstants.ratio_w;*/110f * ArnoldGameConstants.ratio_w;
	public static float maximum_height = /*Helper.getImageFromAssets(AssetConstants.normal_1).getRegionHeight() * ArnoldGameConstants.ratio_h;*/180f * ArnoldGameConstants.ratio_h;
	
	
	public StateNormal state_normal;
	public StateLeft_1 state_left_1;
	public StateRight_1 state_right_1;
	
	public StateFall_Left state_fall_left;
	public StateFall_Right state_fall_right;

	
	public Arnold(float x, float y, int zIndex){				
		super(x, y, /*minimum_width*/maximum_width, /*minimum_height*/maximum_height, zIndex);
		

		this.rotateAngle = default_angle;
		this.distance_travelled = default_distance;
		
		this.default_x = x;
		this.default_y = y;
		
	}
	

	@Override
	public void getAssets(AssetPack assetPack) {
		
		//normal
		assetPack.addTexture(AssetConstants.normal_1);
		assetPack.addTexture(AssetConstants.normal_2);
		assetPack.addTexture(AssetConstants.normal_3);
		assetPack.addTexture(AssetConstants.normal_4);
		assetPack.addTexture(AssetConstants.normal_5);
		
	
		//left
		
		//1
		assetPack.addTexture(AssetConstants.left_1_1);
		assetPack.addTexture(AssetConstants.left_1_2);
		assetPack.addTexture(AssetConstants.left_1_3);
		assetPack.addTexture(AssetConstants.left_1_4);
		assetPack.addTexture(AssetConstants.left_1_5);
		//2
		assetPack.addTexture(AssetConstants.left_2_1);
		assetPack.addTexture(AssetConstants.left_2_2);
		assetPack.addTexture(AssetConstants.left_2_3);
		assetPack.addTexture(AssetConstants.left_2_4);
		assetPack.addTexture(AssetConstants.left_2_5);
		//3
		assetPack.addTexture(AssetConstants.left_3_1);
		assetPack.addTexture(AssetConstants.left_3_2);
		assetPack.addTexture(AssetConstants.left_3_3);
		assetPack.addTexture(AssetConstants.left_3_4);
		assetPack.addTexture(AssetConstants.left_3_5);
		//4
		assetPack.addTexture(AssetConstants.left_4_1);
		assetPack.addTexture(AssetConstants.left_4_2);
		assetPack.addTexture(AssetConstants.left_4_3);
		assetPack.addTexture(AssetConstants.left_4_4);
		assetPack.addTexture(AssetConstants.left_4_5);
		
	
		//right
		
		//1
		assetPack.addTexture(AssetConstants.right_1_1);
		assetPack.addTexture(AssetConstants.right_1_2);
		assetPack.addTexture(AssetConstants.right_1_3);
		assetPack.addTexture(AssetConstants.right_1_4);
		assetPack.addTexture(AssetConstants.right_1_5);
		//2
		assetPack.addTexture(AssetConstants.right_2_1);
		assetPack.addTexture(AssetConstants.right_2_2);
		assetPack.addTexture(AssetConstants.right_2_3);
		assetPack.addTexture(AssetConstants.right_2_4);
		assetPack.addTexture(AssetConstants.right_2_5);
		//3
		assetPack.addTexture(AssetConstants.right_3_1);
		assetPack.addTexture(AssetConstants.right_3_2);
		assetPack.addTexture(AssetConstants.right_3_3);
		assetPack.addTexture(AssetConstants.right_3_4);
		assetPack.addTexture(AssetConstants.right_3_5);
		//4
		assetPack.addTexture(AssetConstants.right_4_1);
		assetPack.addTexture(AssetConstants.right_4_2);
		assetPack.addTexture(AssetConstants.right_4_3);
		assetPack.addTexture(AssetConstants.right_4_4);
		assetPack.addTexture(AssetConstants.right_4_5);

	
		//fall
		
		//left fall
		assetPack.addTexture(AssetConstants.fall_1_1);
		assetPack.addTexture(AssetConstants.fall_1_2);
		assetPack.addTexture(AssetConstants.fall_1_3);
		//right fall
		assetPack.addTexture(AssetConstants.fall_2_1);
		assetPack.addTexture(AssetConstants.fall_2_2);
		assetPack.addTexture(AssetConstants.fall_2_3);

	}
	
	
	/**
	 * update game
	 * 
	 * state changes between (default_x - 20f) && (default_x + 20f)
	 * 
	 * @param arnold_pos_x
	 */
	public void update_state(float arnold_pos_x){

		StateBase currentState = this.state;
		
		if(arnold_pos_x < default_x - 20f){
			this.rotateAngle = 90f;// + 10f;
			set_state_fall_left();
		}
		
		else if(arnold_pos_x > default_x + 20f){
			this.rotateAngle = -90f;// - 10f;
			set_state_fall_right();
		}
		

		else if(arnold_pos_x <= default_x && arnold_pos_x >= default_x - 20f){
			
			if(arnold_pos_x < default_x && arnold_pos_x >= default_x - 8f){
				this.rotateAngle = 8f;
			}
		
			else if(arnold_pos_x < default_x - 8f && arnold_pos_x >= default_x - 12f){
				this.rotateAngle = 13f;
			}
		
			else if(arnold_pos_x < default_x - 12f && arnold_pos_x >= default_x - 16f){
				this.rotateAngle = 18f;
			}
		
			else if(arnold_pos_x < default_x - 16f && arnold_pos_x >= default_x - 20f){
				this.rotateAngle = 23;
			}
			
			set_state_left_1();
		}
		

		else if(arnold_pos_x > default_x && arnold_pos_x <= default_x + 20f){
			
			if(arnold_pos_x > default_x && arnold_pos_x <= default_x + 8f){
				this.rotateAngle = -8f;
			}
			
			else if(arnold_pos_x > default_x + 8f && arnold_pos_x <= default_x + 12f){
				this.rotateAngle = -13f;
			}
			
			else if(arnold_pos_x > default_x + 12f && arnold_pos_x <= default_x + 16f){
				this.rotateAngle = -18f;
			}
			
			else if(arnold_pos_x > default_x + 16f && arnold_pos_x <= default_x + 20f){
				this.rotateAngle = -23f;
			}
				
			set_state_right_1();
		}
		
		
		if(currentState!= null && currentState.getClass() == this.state.getClass())
			this.state = currentState;
	}
	
	
	
	@Override
	public void init() {
//		maximum_width = Helper.getImageFromAssets(AssetConstants.normal_1).getRegionWidth() * 0.8f;
//		maximum_height = Helper.getImageFromAssets(AssetConstants.normal_1).getRegionHeight() * 0.8f;
//		this.width = maximum_width;
//		this.height = maximum_height;

		set_state_normal();
		
	}
	

	TextureRegion lastImage = null;
	@Override
	public void render() {
		GlobalVars.ge.getScreen().getBatch().setColor(Color.WHITE);
		GlobalVars.ge.getRenderer().render(image, x, y-height/2f, width, height, 
				/*width/2f*/ 0, height/2f, this.get_angle()+90, 2f, 1f);
		
	}
	
	
	StateBase lastState = null;
	@Override
	public void step(long stepTime) {
		
		if(this.state != null){
			this.state.step(stepTime);
			
		}
	}

	
	/**
	 * states
	 */

	///normal
	
	final int num_of_normal_images = 5;
	TextureRegion[] normal_images;
	AniLoop aniLoop;
	public void set_state_normal(){
		
		normal_images = new TextureRegion[num_of_normal_images];
		
		normal_images[0] = Helper.getImageFromAssets(AssetConstants.normal_1);
		normal_images[1] = Helper.getImageFromAssets(AssetConstants.normal_2);
		normal_images[2] = Helper.getImageFromAssets(AssetConstants.normal_3);
		normal_images[3] = Helper.getImageFromAssets(AssetConstants.normal_4);
		normal_images[4] = Helper.getImageFromAssets(AssetConstants.normal_5);
		
		aniLoop = new AniLoop(this, normal_images, true);
		state_normal = new StateNormal(this, aniLoop);
		this.state = state_normal;
		this.state.init();
	}
	
	
	///left 1
	
	final int num_of_left_1_images = 25;
	TextureRegion[] left_1_images;
	public void set_state_left_1(){
		
		left_1_images = new TextureRegion[num_of_left_1_images];
		
		left_1_images[0] = Helper.getImageFromAssets(AssetConstants.normal_1);
		left_1_images[1] = Helper.getImageFromAssets(AssetConstants.normal_2);
		left_1_images[2] = Helper.getImageFromAssets(AssetConstants.normal_3);
		left_1_images[3] = Helper.getImageFromAssets(AssetConstants.normal_4);
		left_1_images[4] = Helper.getImageFromAssets(AssetConstants.normal_5);
		
		left_1_images[5] = Helper.getImageFromAssets(AssetConstants.left_1_1);
		left_1_images[6] = Helper.getImageFromAssets(AssetConstants.left_1_2);
		left_1_images[7] = Helper.getImageFromAssets(AssetConstants.left_1_3);
		left_1_images[8] = Helper.getImageFromAssets(AssetConstants.left_1_4);
		left_1_images[9] = Helper.getImageFromAssets(AssetConstants.left_1_4);

		left_1_images[10] = Helper.getImageFromAssets(AssetConstants.left_2_1);
		left_1_images[11] = Helper.getImageFromAssets(AssetConstants.left_2_2);
		left_1_images[12] = Helper.getImageFromAssets(AssetConstants.left_2_3);
		left_1_images[13] = Helper.getImageFromAssets(AssetConstants.left_2_4);
		left_1_images[14] = Helper.getImageFromAssets(AssetConstants.left_2_4);

		left_1_images[15] = Helper.getImageFromAssets(AssetConstants.left_3_1);
		left_1_images[16] = Helper.getImageFromAssets(AssetConstants.left_3_2);
		left_1_images[17] = Helper.getImageFromAssets(AssetConstants.left_3_3);
		left_1_images[18] = Helper.getImageFromAssets(AssetConstants.left_3_4);
		left_1_images[19] = Helper.getImageFromAssets(AssetConstants.left_3_4);
		
		left_1_images[20] = Helper.getImageFromAssets(AssetConstants.left_4_1);
		left_1_images[21] = Helper.getImageFromAssets(AssetConstants.left_4_2);
		left_1_images[22] = Helper.getImageFromAssets(AssetConstants.left_4_3);
		left_1_images[23] = Helper.getImageFromAssets(AssetConstants.left_4_4);
		left_1_images[24] = Helper.getImageFromAssets(AssetConstants.left_4_5);
		
		aniLoop = new AniLoop(this, left_1_images, true);
		state_left_1 = new StateLeft_1(this, aniLoop);
		this.state = state_left_1;
		this.state.init();
	
	}
	
	
	///right 1
	
	final int num_of_right_1_images = 25;
	TextureRegion[] right_1_images;
	public void set_state_right_1(){
		
		right_1_images = new TextureRegion[num_of_right_1_images];
		
		right_1_images[0] = Helper.getImageFromAssets(AssetConstants.normal_1);
		right_1_images[1] = Helper.getImageFromAssets(AssetConstants.normal_2);
		right_1_images[2] = Helper.getImageFromAssets(AssetConstants.normal_3);
		right_1_images[3] = Helper.getImageFromAssets(AssetConstants.normal_4);
		right_1_images[4] = Helper.getImageFromAssets(AssetConstants.normal_5);
		
		right_1_images[5] = Helper.getImageFromAssets(AssetConstants.right_1_1);
		right_1_images[6] = Helper.getImageFromAssets(AssetConstants.right_1_2);
		right_1_images[7] = Helper.getImageFromAssets(AssetConstants.right_1_3);
		right_1_images[8] = Helper.getImageFromAssets(AssetConstants.right_1_4);
		right_1_images[9] = Helper.getImageFromAssets(AssetConstants.right_1_4);

		right_1_images[10] = Helper.getImageFromAssets(AssetConstants.right_2_1);
		right_1_images[11] = Helper.getImageFromAssets(AssetConstants.right_2_2);
		right_1_images[12] = Helper.getImageFromAssets(AssetConstants.right_2_3);
		right_1_images[13] = Helper.getImageFromAssets(AssetConstants.right_2_4);
		right_1_images[14] = Helper.getImageFromAssets(AssetConstants.right_2_4);
		
		right_1_images[15] = Helper.getImageFromAssets(AssetConstants.right_3_1);
		right_1_images[16] = Helper.getImageFromAssets(AssetConstants.right_3_2);
		right_1_images[17] = Helper.getImageFromAssets(AssetConstants.right_3_3);
		right_1_images[18] = Helper.getImageFromAssets(AssetConstants.right_3_4);
		right_1_images[19] = Helper.getImageFromAssets(AssetConstants.right_3_4);
		
		right_1_images[20] = Helper.getImageFromAssets(AssetConstants.right_4_1);
		right_1_images[21] = Helper.getImageFromAssets(AssetConstants.right_4_2);
		right_1_images[22] = Helper.getImageFromAssets(AssetConstants.right_4_3);
		right_1_images[23] = Helper.getImageFromAssets(AssetConstants.right_4_4);
		right_1_images[24] = Helper.getImageFromAssets(AssetConstants.right_4_5);
		
		aniLoop = new AniLoop(this, right_1_images, true);
		state_right_1 = new StateRight_1(this, aniLoop);
		this.state = state_right_1;
		this.state.init();
	
	}
	
	
	
	///fall left
	
	final int num_of_fall_left_images = 3;
	TextureRegion[] fall_left_images;
	public void set_state_fall_left(){
		
		fall_left_images = new TextureRegion[num_of_fall_left_images];
		
		fall_left_images[0] = Helper.getImageFromAssets(AssetConstants.fall_1_1);
		fall_left_images[1] = Helper.getImageFromAssets(AssetConstants.fall_1_2);
		fall_left_images[2] = Helper.getImageFromAssets(AssetConstants.fall_1_3);
		
		
		aniLoop = new AniLoop(this, fall_left_images, false);
		state_fall_left = new StateFall_Left(this, aniLoop);
		this.state = state_fall_left;
		this.state.init();
	
	}
	
	
	///fall right
	
	final int num_of_fall_right_images = 3;
	TextureRegion[] fall_right_images;
	public void set_state_fall_right(){
		
		fall_right_images = new TextureRegion[num_of_fall_right_images];
		
		fall_right_images[0] = Helper.getImageFromAssets(AssetConstants.fall_2_1);
		fall_right_images[1] = Helper.getImageFromAssets(AssetConstants.fall_2_2);
		fall_right_images[2] = Helper.getImageFromAssets(AssetConstants.fall_2_3);
		
		
		aniLoop = new AniLoop(this, fall_right_images, false);
		state_fall_right = new StateFall_Right(this, aniLoop);
		this.state = state_fall_right;
		this.state.init();
	
	}


	public void setTravelled_distance(float travelled_distance) {
		this.distance_travelled = travelled_distance;
	}


	public float getTravelled_distance() {
		return distance_travelled;
	}
	
	
	
	public void set_angle(float angle){
		this.rotateAngle = angle;
	}
	
	public float get_angle(){
		return this.rotateAngle;
	}
	
}
