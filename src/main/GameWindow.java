package main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import main.Game;

public class GameWindow {

	public static void main(String[] args) {

		LwjglApplicationConfiguration configure = new LwjglApplicationConfiguration();
		
		configure.title = Game.title;
		configure.width = Game.V_WIDTH* Game.scale;
		configure.height = Game.V_HEIGHT * Game.scale;
		
		new LwjglApplication(new Game(), configure);

	}

}
