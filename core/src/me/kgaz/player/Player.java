package me.kgaz.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.kgaz.Main;
import me.kgaz.physics.CollisionBox;
import me.kgaz.physics.CollisionCheck;
import me.kgaz.physics.Position;
import me.kgaz.physics.Vector;
import me.kgaz.screens.GameScreen;
import me.kgaz.world.Level;

public class Player {

    private CollisionBox collision;

    public Position loc;

    public static final Vector GRAVITY = new Vector(0, -5);

    public static final int PLAYER_HEIGHT = 64, PLAYER_WIDTH = 64;

    public static final float PLAYER_SPEED = 2;

    private Main game;

    private boolean facing;

    private TextureAtlas playerAtlas;

    private TextureRegion STANDING;

    private GameScreen screen;

    private Vector GRAVITY_VECTOR;
    private Vector INPUT_VECTOR;

    private Vector velocity;

    public Player(Main owner, Position position, GameScreen gameScreen) {

        this.screen = gameScreen;

        this.playerAtlas = owner.getAssets().PLAYER_TEXTURES;

        this.STANDING = playerAtlas.findRegion("playerStand");

        this.game = owner;

        this.loc = position;

        this.facing = true;

        this.collision = new CollisionBox();

        GRAVITY_VECTOR = new Vector(0, 0);
        INPUT_VECTOR = new Vector(0, 0);

        velocity = new Vector(0, 0);

    }

    private Vector getInputVector(){

        Vector vector = new Vector(0, 0);

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {

            vector.x+=1;

        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {

            vector.x-=1;

        }

//        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
//
//            vector.y+=1;
//
//        }
//
//        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
//
//            vector.y-=1;
//
//        }

        return vector.normalize().multiply(PLAYER_SPEED);

    }

    public boolean isOnGround(){

        return screen.getDisplayedLevel().isSolid(new Position(loc.x, loc.y-1)) || screen.getDisplayedLevel().isSolid(new Position(loc.x+PLAYER_WIDTH, loc.y-1));

    }

    private void updateVectors(){

        Vector input = getInputVector();

        if(isOnGround()) {

            GRAVITY_VECTOR.multiply(0);

            INPUT_VECTOR.add(input);

            INPUT_VECTOR.x *= 0.83;

            if(Math.abs(INPUT_VECTOR.x) < 0.5) INPUT_VECTOR.x = 0;

            if(Math.abs(INPUT_VECTOR.x) > 6) INPUT_VECTOR.x = INPUT_VECTOR.x > 0 ? 6 : -6;

        } else {

            if(GRAVITY_VECTOR.y == 0) {

                GRAVITY_VECTOR.y = -2.5f;

            }

            INPUT_VECTOR.add(input.multiply(0.55));

            INPUT_VECTOR.x *= 0.82;

            GRAVITY_VECTOR.multiply(1.09);

            if(GRAVITY_VECTOR.y < -35) GRAVITY_VECTOR.y = -35;

        }


    }


    private void calcVectors(){

        updateVectors();

        velocity = new Vector(0, 0).add(INPUT_VECTOR).add(GRAVITY_VECTOR);
    }

    public void click(float x, float y) {

        this.loc = new Position(x, y);

    }

    public void update(Level level){

        calcVectors();

        velocity = collision.checkMovement(velocity, loc, level);

        loc.add(velocity);

        if(velocity.x == 0) return;

        facing = velocity.x > 0;

    }


    public void render(SpriteBatch batch, Level level) {

        update(level);

        if(facing) batch.draw(STANDING, loc.x, loc.y, PLAYER_WIDTH, PLAYER_HEIGHT);
        else batch.draw(STANDING, loc.x+PLAYER_WIDTH, loc.y, -PLAYER_WIDTH, PLAYER_HEIGHT);


    }

}
