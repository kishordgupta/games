/**
 * 
 */

package com.rhymes;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rhymes.ge.pw.GameApplication;

public class xFactoryDesktop {
//	private static final int SCREEN_WIDTH = 640;
//	private static final int SCREEN_HEIGHT = 1024;
	
//	private static final int SCREEN_WIDTH = 320;
//	private static final int SCREEN_HEIGHT = 480;

//	private static final int SCREEN_WIDTH = 480;
//	private static final int SCREEN_HEIGHT = 850;
	
	
	
// rotated
	private static final int SCREEN_HEIGHT = 640;
	private static final int SCREEN_WIDTH = 1024;
	
//	private static final int SCREEN_WIDTH = 480;
//	private static final int SCREEN_HEIGHT = 320;

//	private static final int SCREEN_WIDTH = 850;
//	private static final int SCREEN_HEIGHT = 480;	

	public static void main (String[] args) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useGL20 = true;
		config.vSyncEnabled = true;
		config.width = SCREEN_WIDTH;
		config.height = SCREEN_HEIGHT;

		new LwjglApplication(new GameApplication(), config);
//		new JoglApplication(new GameApplication(), "Animator", 480, 320, false);
	}
}
