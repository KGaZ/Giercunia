package me.kgaz.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import me.kgaz.Main;
import me.kgaz.userInterface.UIManager;
import me.kgaz.userInterface.elements.Button;
import me.kgaz.world.Level;
import me.kgaz.world.levels.MenuLevel;

public class MenuScreen implements Screen {

    private final Main handler;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private UIManager uiManager;

    private Level levelBackground;

    public MenuScreen(Main game){

       this.levelBackground = new MenuLevel(game.getAssets());

        this.handler = game;

        this.uiManager = new UIManager();

        Button start = new Button(835, 500, 250, 100, "beta/textures/buttonStart.png");

        start.setClickAction(new Runnable() {
            @Override
            public void run() {
                game.setScreen(new GameScreen(game, levelBackground));
            }
        });

        uiManager.addUiElement(start);

        game.getDisposeManager().registerDisposable(uiManager);

        this.batch = game.batch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

    }

    @Override
    public void render(float delta) {

        camera.update();

        this.batch.setProjectionMatrix(camera.combined);

        this.batch.begin();

        ScreenUtils.clear(0,0,0,0);

        levelBackground.render(batch);

        { // UI Manager

            Vector3 mousePos = new Vector3();

            mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(mousePos);

            uiManager.update(mousePos.x, mousePos.y);

            uiManager.render(batch);

        }

        handler.manager.ESTONIA_192.setColor(Color.WHITE);

        handler.manager.ESTONIA_192.draw(batch, "Jump King 2", 750, 840);

        this.batch.end();

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
