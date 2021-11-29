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
    private TextureRegion JUMP_FALLING;
    private TextureRegion[] WALK_ANIM;
    private TextureRegion PREPARE_JUMP;

    private GameScreen screen;

    private Vector GRAVITY_VECTOR;
    private Vector INPUT_VECTOR;

    private Vector velocity;

    public float spaceTime;

    public Player(Main owner, Position position, GameScreen gameScreen) {

        this.screen = gameScreen;

        this.playerAtlas = owner.getAssets().PLAYER_TEXTURES;

        this.STANDING = playerAtlas.findRegion("playerStand");
        this.JUMP_FALLING = playerAtlas.findRegion("playerJumpFalling");

        WALK_ANIM = new TextureRegion[] {
                playerAtlas.findRegion("playerMoveLeftFoot"),
                playerAtlas.findRegion("playerRunningMiddle"),
                playerAtlas.findRegion("playerMoveRightFoot"),
                playerAtlas.findRegion("playerRunningMiddle")
        };

        this.PREPARE_JUMP = playerAtlas.findRegion("playerSquat");

        this.game = owner;

        this.loc = position;

        this.spaceTime = 0;

        this.facing = true;

        this.collision = new CollisionBox(game.getAssets());

        GRAVITY_VECTOR = new Vector(0, 0);
        INPUT_VECTOR = new Vector(0, 0);

        velocity = new Vector(0, 0);

        animKlatka = 0;
        lastAnim = System.currentTimeMillis();

    }

    private Vector getInputVector(){

        Vector vector = new Vector(0, 0);

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) return vector;

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

    private static final Vector groundVector = new Vector(0, -1);

    public boolean isOnGround(){

        return collision.isOnGround(loc, screen.getDisplayedLevel());
    }

    private void updateVectors(){

        Vector input = getInputVector();

        if(isOnGround()) {

            if(GRAVITY_VECTOR.y <= 0) GRAVITY_VECTOR.multiply(0);

            INPUT_VECTOR.add(input);

            INPUT_VECTOR.x *= 0.83;

            if(Math.abs(INPUT_VECTOR.x) < 0.5) INPUT_VECTOR.x = 0;

            if(Math.abs(INPUT_VECTOR.x) > 6) INPUT_VECTOR.x = INPUT_VECTOR.x > 0 ? 6 : -6;

        } else {

            if(GRAVITY_VECTOR.y == 0) GRAVITY_VECTOR.y = -2.5f;

            if(GRAVITY_VECTOR.x < 0.5 && GRAVITY_VECTOR.x > -0.5) GRAVITY_VECTOR.x = 0;

            if(GRAVITY_VECTOR.y > -2.5 && GRAVITY_VECTOR.y < 6) {

                GRAVITY_VECTOR.y -= 1;

            }

            if(GRAVITY_VECTOR.y > 0) {

                GRAVITY_VECTOR.y*=0.94;

                if(GRAVITY_VECTOR.y < 0.3 && GRAVITY_VECTOR.y > 0) GRAVITY_VECTOR.y = -GRAVITY_VECTOR.y;

            }

            INPUT_VECTOR.x *= 0.9;

            if(GRAVITY_VECTOR.y < 0) {

                GRAVITY_VECTOR.x *= 0.9f;

                GRAVITY_VECTOR.multiply(1.09);
            }

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

        velocity = collision.calculateMovement(velocity, loc, level);

        loc.add(velocity);

        if(velocity.x == 0) return;

        facing = velocity.x > 0;

    }

    private int animKlatka;
    private long lastAnim;

    public void render(SpriteBatch batch, Level level) {

        update(level);

        TextureRegion renderRegion = STANDING;

        boolean onGround = isOnGround();

        if(!onGround)  {
            renderRegion = JUMP_FALLING;
            animKlatka = 0;
            lastAnim = System.currentTimeMillis();
        }

        if(onGround && (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))) {

            if(System.currentTimeMillis() - lastAnim > 80l) {

                lastAnim = System.currentTimeMillis();
                animKlatka++;

                if(animKlatka == 4) animKlatka = 0;

            }

            renderRegion = WALK_ANIM[animKlatka];


        } else {

            animKlatka = 0;
            lastAnim = System.currentTimeMillis();

        }

        if(spaceTime > 0) renderRegion = PREPARE_JUMP;

        if(facing) batch.draw(renderRegion, loc.x, loc.y, PLAYER_WIDTH, PLAYER_HEIGHT);
        else batch.draw(renderRegion, loc.x+PLAYER_WIDTH, loc.y, -PLAYER_WIDTH, PLAYER_HEIGHT);

        collision.render(batch, loc, level);

    }

}
