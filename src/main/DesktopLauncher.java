package main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Game.title;
		config.width = Game.V_WIDTH* Game.scale;
		config.height = Game.V_HEIGHT * Game.scale;
		new LwjglApplication(new Game(), config);
	}
}
