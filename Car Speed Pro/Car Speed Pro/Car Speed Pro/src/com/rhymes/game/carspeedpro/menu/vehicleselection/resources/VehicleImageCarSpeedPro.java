package com.rhymes.game.carspeedpro.menu.vehicleselection.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.carspeedpro.AssetConstantsCarSpeedPro;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.helpers.Helper;

public class VehicleImageCarSpeedPro{

	TextureRegion image_01, image_02, image_03, image_alpha, 
	imageWheelForward, imageWheelForward_alpha, 
	imageWheelBackward, imageWheelBackward_alpha;
	
	float x, y, width, height;
	
	vehicleType vehicle_type;
	colorTpe color_type;
	
	public static enum vehicleType{
		benga,
		huwwer,
		krac,
		military,
		police,
		school
	}
	
	public static enum colorTpe{
		color_0,//red
		color_1,//yellow
		color_2,//blue
		color_3,//green
		color_4//white
	}
	
	public VehicleImageCarSpeedPro(float x, float y, float width, float height,
			vehicleType vehicle_type,
			colorTpe color_type) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		
		this.vehicle_type = vehicle_type;
		this.color_type = color_type;
		
		choose_image_set();
	}
	
	
	
	public void choose_image_set(){
		
		if(vehicle_type == vehicleType.benga){
			image_01 		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_01);
			image_02 		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_02);
			image_03		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_03);
			image_alpha		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_alpha);

			imageWheelForward 			= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_wheel);
			imageWheelForward_alpha 	= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_wheel_alpha);
			imageWheelBackward 			= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_wheel);
			imageWheelBackward_alpha 	= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.benga_wheel_alpha);

		}
		
		else if(vehicle_type == vehicleType.huwwer){
			image_01 		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.huwwer_01);
			image_02 		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.huwwer_02);
			image_03		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.huwwer_03);
			image_alpha		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.huwwer_alpha);

			imageWheelForward 			= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.huwwer_wheel);
			imageWheelForward_alpha 	= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.huwwer_wheel_alpha);
			imageWheelBackward 			= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.huwwer_wheel);
			imageWheelBackward_alpha 	= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.huwwer_wheel_alpha);
		}

		else if(vehicle_type == vehicleType.krac){
			image_01 		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.krac_01);
			image_02 		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.krac_02);
			image_03		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.krac_03);
			image_alpha		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.krac_alpha);

			imageWheelForward 			= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.krac_wheel);
			imageWheelForward_alpha 	= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.krac_wheel_alpha);
			imageWheelBackward 			= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.krac_wheel);
			imageWheelBackward_alpha 	= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.krac_wheel_alpha);
		}

		else if(vehicle_type == vehicleType.military){
			image_01 		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.military_01);
			image_02 		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.military_02);
			image_03		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.military_03);
			image_alpha		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.military_alpha);

			imageWheelForward 			= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.military_wheel);
			imageWheelForward_alpha 	= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.military_wheel_alpha);
			imageWheelBackward 			= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.military_wheel);
			imageWheelBackward_alpha 	= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.military_wheel_alpha);
		}

		else if(vehicle_type == vehicleType.police){
			image_01 		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.police_01);
			image_02 		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.police_02);
			image_03			= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.police_03);
			image_alpha		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.police_alpha);

			imageWheelForward 			= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.police_wheel);
			imageWheelForward_alpha 	= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.police_wheel_alpha);
			imageWheelBackward 			= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.police_wheel);
			imageWheelBackward_alpha 	= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.police_wheel_alpha);
		}

		else if(vehicle_type == vehicleType.school){
			image_01 		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.schoolbus_01);
			image_02 		= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.schoolbus_02);

			imageWheelForward 			= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.schoolbus_wheel);
			imageWheelForward_alpha 	= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.schoolbus_wheel_alpha);
			imageWheelBackward 			= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.schoolbus_wheel);
			imageWheelBackward_alpha 	= Helper.getImageFromAssets(AssetConstantsCarSpeedPro.schoolbus_wheel_alpha);
		}
		
	}
	
	
	public void render_vehicle(){
		if(vehicle_type == vehicleType.benga){
		
			render_color();
			
			GlobalVars.ge.getRenderer().render(image_01, x, y, width, height);
			GlobalVars.ge.getRenderer().render(image_02, x, y, width, height);
			GlobalVars.ge.getRenderer().render(image_03, x, y, width, height);
			GlobalVars.ge.getRenderer().render(image_alpha, x, y, width, height);

			GlobalVars.ge.getRenderer().render(imageWheelForward_alpha, x+width/2f, y, width, height);
			GlobalVars.ge.getRenderer().render(imageWheelForward, x+width/2f, y, width, height);

			GlobalVars.ge.getRenderer().render(imageWheelBackward_alpha, x+width/2f, y, width, height);
			GlobalVars.ge.getRenderer().render(imageWheelBackward, x+width/2f, y, width, height);

			GlobalVars.ge.getScreen().getBatch().setColor(1, 1, 1, 1f);
		}
		
		else if(vehicle_type == vehicleType.huwwer){
			
		}
		
		else if(vehicle_type == vehicleType.krac){
			
		}
		
		else if(vehicle_type == vehicleType.military){
			
		}
		
		else if(vehicle_type == vehicleType.police){
			
		}
		
		else if(vehicle_type == vehicleType.school){
		
		}
	}
	
	
	public void render_color(){
		if(color_type == colorTpe.color_0){
			
		}
		
		else if(color_type == colorTpe.color_1){
			
		}
		
		else if(color_type == colorTpe.color_2){
			
		}
		
		else if(color_type == colorTpe.color_3){
			
		}
		
		else if(color_type == colorTpe.color_4){
			
		}
	}

}
