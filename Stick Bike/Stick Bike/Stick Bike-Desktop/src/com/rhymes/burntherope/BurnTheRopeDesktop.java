/**
 * 
 */

package com.rhymes.burntherope;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rhymes.game.entity.elements.physical.PhysicsTest;
import com.rhymes.ge.pw.GameApplication;
import com.rhymes.helpers.Helper;

public class BurnTheRopeDesktop {

//	private static final int SCREEN_WIDTH = (int) (360);
//	private static final int SCREEN_HEIGHT = (int) (240);

//	private static final int SCREEN_WIDTH = (int) (960);
//	private static final int SCREEN_HEIGHT = (int) (640);
	
//	private static final int SCREEN_WIDTH = (int) (1024);
//	private static final int SCREEN_HEIGHT = (int) (640);

	private static final int SCREEN_WIDTH = (int) (850);
	private static final int SCREEN_HEIGHT = (int) (480);
	
//	private static final int SCREEN_WIDTH = (int) (480);
//	private static final int SCREEN_HEIGHT = (int) (320);
	
//	private static final int SCREEN_WIDTH = (int) (1280);
//	private static final int SCREEN_HEIGHT = (int) (800);
	
	public static void main (String[] args) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useGL20 = true;
//		config.vSyncEnabled = true;
		config.width = SCREEN_WIDTH;
		config.height = SCREEN_HEIGHT;

		new LwjglApplication(new GameApplication(), config);
//		new LwjglApplication(new PhysicsTest(), config);
	}
}
