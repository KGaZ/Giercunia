package me.kgaz.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.kgaz.Main;

public class DesktopLauncher {

	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Jump King 2";

		config.width = 1024;
		config.height = 960;

		//config.resizable = false;

		new LwjglApplication(new Main(), config);

	}

}
