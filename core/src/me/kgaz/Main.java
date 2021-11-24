package me.kgaz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.kgaz.assets.Assets;
import me.kgaz.assets.FontManager;
import me.kgaz.garbage.DisposeManager;
import me.kgaz.screens.MenuScreen;
import me.kgaz.world.Level;
import me.kgaz.world.levels.MenuLevel;

public class Main extends Game {

	public SpriteBatch batch;
	public BitmapFont font;

	private Assets assets;
	private DisposeManager disposeManager;

	public FontManager manager;

	@Override
	public void create () {

		disposeManager = new DisposeManager();

		manager = new FontManager(this);
		assets = new Assets(this);

		batch = new SpriteBatch();
		font = new BitmapFont();

		//this.setScreen(new TestLevelScreen(this, new MenuLevel(this.assets)));
		this.setScreen(new MenuScreen(this));

	}

	@Override
	public void render () {

		super.render();

	}
	
	@Override
	public void dispose () {

		disposeManager.dispose();

		batch.dispose();
		font.dispose();

	}

	public DisposeManager getDisposeManager(){

		return disposeManager;

	}

	public Assets getAssets(){
		return assets;
	}
}
