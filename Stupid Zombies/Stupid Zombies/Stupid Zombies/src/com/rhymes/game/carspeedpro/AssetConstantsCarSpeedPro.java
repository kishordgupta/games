package com.rhymes.game.carspeedpro;

import com.badlogic.gdx.Gdx;
import com.rhymes.helpers.Helper;

public class AssetConstantsCarSpeedPro {
	
	public static float ratio_w						= (float)Gdx.graphics.getWidth() / 960f;
	public static float ratio_h 					= (float)Gdx.graphics.getHeight() / 640f;
	

	private static String PREFIX				= "games/CarSpeedProRes/";
	
	static{
		if(Gdx.graphics.getWidth() > 480)
			PREFIX = PREFIX + "1x/";
		else if(Gdx.graphics.getWidth() > 350)
			PREFIX = PREFIX + "1x/";
		else 
			PREFIX = PREFIX + "1x/";
		
		Helper.println("\n\nSetting AssetConstants prefix: " + PREFIX);
	}
	
	
	
	
	//splash
	
	public static final String splash_bg 			= "games/CarSpeedProRes/default/splash.png";
	
	

	/**
	 * Image Resources
	 */
	
	
	
	//main menu
	
	public static final String main_bg 				= PREFIX+"images/main menu/main_bg.png";
	
	public static final String play_h 				= PREFIX+"images/main menu/play_high.png";
	public static final String play_n 				= PREFIX+"images/main menu/play_nor.png";
	public static final String settings_h 			= PREFIX+"images/main menu/settings_high.png";
	public static final String settings_n 			= PREFIX+"images/main menu/settings_nor.png";
	
	
	//settings
	
	public static final String game_mode 			= PREFIX+"images/settings menu/gamemode.png";
	public static final String free 				= PREFIX+"images/settings menu/free.png";
	public static final String time 				= PREFIX+"images/settings menu/time.png";
	public static final String sound 				= PREFIX+"images/settings menu/sound.png";
	public static final String on 					= PREFIX+"images/settings menu/on.png";
	public static final String off 					= PREFIX+"images/settings menu/off.png";
	

	//select level menu
	
	public static final String d_1 					= PREFIX+"images/select levels menu/1_down.png";
	public static final String n_1 					= PREFIX+"images/select levels menu/1_normal.png";
	public static final String d_2 					= PREFIX+"images/select levels menu/2_down.png";
	public static final String n_2 					= PREFIX+"images/select levels menu/2_normal.png";
	public static final String d_3 					= PREFIX+"images/select levels menu/3_down.png";
	public static final String n_3 					= PREFIX+"images/select levels menu/3_normal.png";
	public static final String d_4 					= PREFIX+"images/select levels menu/4_down.png";
	public static final String n_4 					= PREFIX+"images/select levels menu/4_normal.png";
	public static final String d_5 					= PREFIX+"images/select levels menu/5_down.png";
	public static final String n_5 					= PREFIX+"images/select levels menu/5_normal.png";
	public static final String city_d 				= PREFIX+"images/select levels menu/city_down.png";
	public static final String city_n 				= PREFIX+"images/select levels menu/city_normal.png";
	public static final String desert_d 			= PREFIX+"images/select levels menu/desert_down.png";
	public static final String desert_n 			= PREFIX+"images/select levels menu/desert_normal.png";
	public static final String tropic_d 			= PREFIX+"images/select levels menu/tropic_down.png";
	public static final String tropic_n 			= PREFIX+"images/select levels menu/tropic_normal.png";
	public static final String ok_h 				= PREFIX+"images/select levels menu/ok_high.png";
	public static final String ok_n 				= PREFIX+"images/select levels menu/ok_nor.png";
	
	public static final String back_h 				= PREFIX+"images/select levels menu/back_high.png";
	public static final String back_n		 		= PREFIX+"images/select levels menu/back_nor.png";

	public static final String select_bg 			= PREFIX+"images/select levels menu/select_bg.png";

	
	//select vehicle menu
	
	public static final String benga_d 					= PREFIX+"images/select vehicle menu/benga_down.png";
	public static final String benga_n 					= PREFIX+"images/select vehicle menu/benga_normal.png";
	public static final String huwwer_d 				= PREFIX+"images/select vehicle menu/huwwer_down.png";
	public static final String huwwer_n 				= PREFIX+"images/select vehicle menu/huwwer_normal.png";
	public static final String krac_d 					= PREFIX+"images/select vehicle menu/krac_down.png";
	public static final String krac_n 					= PREFIX+"images/select vehicle menu/krac_normal.png";
	public static final String military_d 				= PREFIX+"images/select vehicle menu/military_down.png";
	public static final String military_n 				= PREFIX+"images/select vehicle menu/military_normal.png";
	public static final String police_d 				= PREFIX+"images/select vehicle menu/police_down.png";
	public static final String police_n 				= PREFIX+"images/select vehicle menu/police_normal.png";
	public static final String school_d 				= PREFIX+"images/select vehicle menu/school_down.png";
	public static final String school_n 				= PREFIX+"images/select vehicle menu/school_normal.png";
	public static final String clr_0 					= PREFIX+"images/select vehicle menu/clr_0.png";
	public static final String clr_s0 					= PREFIX+"images/select vehicle menu/clr_sel_0.png";
	public static final String clr_1 					= PREFIX+"images/select vehicle menu/clr_1.png";
	public static final String clr_s1 					= PREFIX+"images/select vehicle menu/clr_sel_1.png";
	public static final String clr_2 					= PREFIX+"images/select vehicle menu/clr_2.png";
	public static final String clr_s2 					= PREFIX+"images/select vehicle menu/clr_sel_2.png";
	public static final String clr_3 					= PREFIX+"images/select vehicle menu/clr_3.png";
	public static final String clr_s3 					= PREFIX+"images/select vehicle menu/clr_sel_3.png";
	public static final String clr_4 					= PREFIX+"images/select vehicle menu/clr_4.png";
	public static final String clr_s4 					= PREFIX+"images/select vehicle menu/clr_sel_4.png";
	
	
	
	//vehicle images
	
		//benga
	public static final String benga_01 					= PREFIX+"images/game/vehicle/benga/benga_01.png";
	public static final String benga_02 					= PREFIX+"images/game/vehicle/benga/benga_02.png";
	public static final String benga_03 					= PREFIX+"images/game/vehicle/benga/benga_03.png";
	public static final String benga_alpha 					= PREFIX+"images/game/vehicle/benga/benga_alpha.png";

	public static final String benga_wheel 					= PREFIX+"images/game/vehicle/benga/benga_wheel.png";
	public static final String benga_wheel_alpha			= PREFIX+"images/game/vehicle/benga/benga_wheel_alpha.png";

		//huwwer
	public static final String huwwer_01 					= PREFIX+"images/game/vehicle/huwwer/huwwer_01.png";
	public static final String huwwer_02 					= PREFIX+"images/game/vehicle/huwwer/huwwer_02.png";
	public static final String huwwer_03 					= PREFIX+"images/game/vehicle/huwwer/huwwer_03.png";
	public static final String huwwer_alpha 				= PREFIX+"images/game/vehicle/huwwer/huwwer_alpha.png";

	public static final String huwwer_wheel 				= PREFIX+"images/game/vehicle/huwwer/huwwer_wheel.png";
	public static final String huwwer_wheel_alpha			= PREFIX+"images/game/vehicle/huwwer/huwwer_wheel_alpha.png";
	
		//krac
	public static final String krac_01 						= PREFIX+"images/game/vehicle/krac/krac_01.png";
	public static final String krac_02 						= PREFIX+"images/game/vehicle/krac/krac_02.png";
	public static final String krac_03 						= PREFIX+"images/game/vehicle/krac/krac_03.png";
	public static final String krac_alpha 					= PREFIX+"images/game/vehicle/krac/krac_alpha.png";

	public static final String krac_wheel 					= PREFIX+"images/game/vehicle/krac/krac_wheel.png";
	public static final String krac_wheel_alpha				= PREFIX+"images/game/vehicle/krac/krac_wheel_alpha.png";	
	
		//military
	public static final String military_01 					= PREFIX+"images/game/vehicle/military/military_01.png";
	public static final String military_02 					= PREFIX+"images/game/vehicle/military/military_02.png";
	public static final String military_03 					= PREFIX+"images/game/vehicle/military/military_03.png";
	public static final String military_alpha 				= PREFIX+"images/game/vehicle/military/military_alpha.png";

	public static final String military_wheel 				= PREFIX+"images/game/vehicle/military/military_wheel.png";
	public static final String military_wheel_alpha			= PREFIX+"images/game/vehicle/military/military_wheel_alpha.png";
	
		//police
	public static final String police_01 					= PREFIX+"images/game/vehicle/police/police_01.png";
	public static final String police_02 					= PREFIX+"images/game/vehicle/police/police_02.png";
	public static final String police_03 					= PREFIX+"images/game/vehicle/police/police_03.png";
	public static final String police_alpha 				= PREFIX+"images/game/vehicle/police/police_alpha.png";

	public static final String police_wheel 				= PREFIX+"images/game/vehicle/police/police_wheel.png";
	public static final String police_wheel_alpha			= PREFIX+"images/game/vehicle/police/police_wheel_alpha.png";
	
		//school
	public static final String schoolbus_01 				= PREFIX+"images/game/vehicle/school bus/schoolbus_01.png";
	public static final String schoolbus_02 				= PREFIX+"images/game/vehicle/school bus/schoolbus_02.png";

	public static final String schoolbus_wheel 				= PREFIX+"images/game/vehicle/school bus/schoolbus_wheel.png";
	public static final String schoolbus_wheel_alpha		= PREFIX+"images/game/vehicle/school bus/schoolbus_wheel_alpha.png";
	
	
	
	
	//game
	
		//loading
	public static final String loading_bg 			= PREFIX+"images/game/loading_bg.png";
		
		//ui
	public static final String health_a 			= PREFIX+"images/game/ui/health_act.png";
	public static final String health_d 			= PREFIX+"images/game/ui/health_dis.png";
	public static final String km_h 				= PREFIX+"images/game/ui/km_h.png";
	public static final String particle 			= PREFIX+"images/game/ui/particle.png";
	public static final String particle1 			= PREFIX+"images/game/ui/particle1.png";
	public static final String pause_a 				= PREFIX+"images/game/ui/pause_act.png";
	public static final String pause_n 				= PREFIX+"images/game/ui/pause_nor.png";
	public static final String ready_1 				= PREFIX+"images/game/ui/ready_1";
	public static final String ready_2 				= PREFIX+"images/game/ui/ready_2";
	public static final String ready3 				= PREFIX+"images/game/ui/ready_3";

	
		//control
			//foot
	public static final String foot_1 				= PREFIX+"images/game/control/foot_1.png";
	public static final String foot_2 				= PREFIX+"images/game/control/foot_2.png";
	public static final String foot_3 				= PREFIX+"images/game/control/foot_3.png";
	public static final String foot_4 				= PREFIX+"images/game/control/foot_4.png";
	public static final String foot_5 				= PREFIX+"images/game/control/foot_5.png";
	public static final String foot_6 				= PREFIX+"images/game/control/foot_6.png";
	public static final String foot_7 				= PREFIX+"images/game/control/foot_7.png";
	public static final String foot_8 				= PREFIX+"images/game/control/foot_8.png";
	
			//gear
	public static final String gear_1 				= PREFIX+"images/game/control/gear_1.png";
	public static final String gear_2 				= PREFIX+"images/game/control/gear_2.png";

			//gps
	public static final String gps	 				= PREFIX+"images/game/control/gps.png";
	
	
		//environment
			//building
	public static final String house_01 			= PREFIX+"images/game/environmental elements/building/benzinkut.png";
	public static final String house_02				= PREFIX+"images/game/environmental elements/building/house_01.png";
	public static final String house_03				= PREFIX+"images/game/environmental elements/building/house_02.png";
	public static final String house_04				= PREFIX+"images/game/environmental elements/building/house_03.png";
	public static final String house_05				= PREFIX+"images/game/environmental elements/building/house_04.png";

			//tree
	public static final String tree_1				= PREFIX+"images/game/environmental elements/tree/tree1.png";
	public static final String tree2				= PREFIX+"images/game/environmental elements/tree/tree2.png";
	public static final String tree3				= PREFIX+"images/game/environmental elements/tree/tree3.png";
	public static final String tree4				= PREFIX+"images/game/environmental elements/tree/tree4.png";
	
			//level modes
				//city
	public static final String city_image_city					= PREFIX+"images/game/environmental elements/level modes/city/city.png";
	public static final String city_image_alap_2				= PREFIX+"images/game/environmental elements/level modes/city/city_alap_2.png";
	public static final String city_image_alap_3				= PREFIX+"images/game/environmental elements/level modes/city/city_alap_3.png";
	public static final String city_image_alap_4				= PREFIX+"images/game/environmental elements/level modes/city/city_alap_4.png";
	public static final String city_image_bg					= PREFIX+"images/game/environmental elements/level modes/city/city_bg.png";
	public static final String city_image_bg_dawn				= PREFIX+"images/game/environmental elements/level modes/city/city_dawn_bg.png";
	public static final String city_image_ground				= PREFIX+"images/game/environmental elements/level modes/city/city_ground.png";

				//desert
	public static final String desert_image_desert				= PREFIX+"images/game/environmental elements/level modes/desert/desert.png";
	public static final String desert_image_alap_2				= PREFIX+"images/game/environmental elements/level modes/desert/desert_alap_2.png";
	public static final String desert_image_alap_3				= PREFIX+"images/game/environmental elements/level modes/desert/desert_alap_3.png";
	public static final String desert_image_alap_4				= PREFIX+"images/game/environmental elements/level modes/desert/desert_alap_4.png";
	public static final String desert_image_bg					= PREFIX+"images/game/environmental elements/level modes/desert/desert_bg.png";
	public static final String desert_image_bg_dawn				= PREFIX+"images/game/environmental elements/level modes/desert/desert_dawn_bg.png";
	public static final String desert_image_ground				= PREFIX+"images/game/environmental elements/level modes/desert/desert_ground.png";

				//tropic
	public static final String tropic_image_tropic				= PREFIX+"images/game/environmental elements/level modes/mountain/tropic.png";
	public static final String tropic_image_alap_1				= PREFIX+"images/game/environmental elements/level modes/mountain/tropic_alap_1.png";
	public static final String tropic_image_alap_2				= PREFIX+"images/game/environmental elements/level modes/mountain/tropic_alap_2.png";
	public static final String tropic_image_alap_3				= PREFIX+"images/game/environmental elements/level modes/mountain/tropic_alap_3.png";
	public static final String tropic_image_alap_4				= PREFIX+"images/game/environmental elements/level modes/mountain/tropic_alap_4.png";
	public static final String tropic_image_alap_5				= PREFIX+"images/game/environmental elements/level modes/mountain/tropic_alap_5.png";
	public static final String tropic_image_bg					= PREFIX+"images/game/environmental elements/level modes/mountain/tropic_bg.png";
	public static final String tropic_image_bg_dawn				= PREFIX+"images/game/environmental elements/level modes/mountain/tropic_dawn_bg.png";
	public static final String tropic_image_ground				= PREFIX+"images/game/environmental elements/level modes/mountain/tropic_ground.png";
	
				//dirt
	public static final String dirt_01							= PREFIX+"images/game/environmental elements/dirt/dirt_01.png";
	public static final String dirt_wheel						= PREFIX+"images/game/environmental elements/dirt/dirt_wheel.png";

	
	//game over
	public static final String yes_h							= PREFIX+"images/game over/yes_high.png";
	public static final String yes_n							= PREFIX+"images/game over/yes_nor.png";
	public static final String no_h								= PREFIX+"images/game over/no_high.png";
	public static final String no_n								= PREFIX+"images/game over/no_nor.png";
	public static final String again							= PREFIX+"images/game over/again.png";

	
	
	/**
	 * Sound Resources
	 */

	public static final String sound_ready 					= "games/CarSpeedProRes/sounds/ready.mp3";
	public static final String sound_lets_go 				= "games/CarSpeedProRes/sounds/lets_go.mp3";
	public static final String music_game_bg 				= "games/CarSpeedProRes/sounds/bg.mp3";
	public static final String music_first 					= "games/CarSpeedProRes/sounds/first.mp3";
	public static final String music_over_bg 				= "games/CarSpeedProRes/sounds/bgmusic.mp3";

}
