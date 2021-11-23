package me.kgaz.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import me.kgaz.Main;
import me.kgaz.userInterface.UIManager;
import me.kgaz.userInterface.elements.Button;

public class MenuScreen implements Screen {

    private final Main handler;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Texture background;

    private UIManager uiManager;

    public MenuScreen(Main game){

        this.handler = game;

        this.uiManager = new UIManager();

        uiManager.addUiElement(new Button(835, 500, 250, 100, "beta/textures/buttonStart.png"));

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

        { // UI Manager

            Vector3 mousePos = new Vector3();

            mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(mousePos);

            uiManager.update(mousePos.x, mousePos.y);

            uiManager.render(batch);

        }

        handler.batch.draw(handler.getAssets().TILESET_PLAINS.findRegion("tile025"), 0, 0);

        handler.manager.ARIAL.draw(batch, "FPS: "+Gdx.graphics.getFramesPerSecond()+". Ver. InDev 1\nFrame Time: "+Gdx.graphics.getDeltaTime()+"ms", 0, 1080);

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
