package me.kgaz.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.kgaz.Main;

import me.kgaz.physics.CollisionBox;
import me.kgaz.physics.CollisionData;
import me.kgaz.physics.Position;
import me.kgaz.physics.Vector;
import me.kgaz.screens.GameScreen;
import me.kgaz.utils.LevelUtil;
import me.kgaz.world.Level;

public class Player {

    private CollisionBox collision;

    public Position loc;

    public static final int PLAYER_HEIGHT = 64, PLAYER_WIDTH = 64;
    public static final float GRAVITY_SPEED = -0.7f;
    public static final float PLAYER_SPEED = 0.7f;

    private Main game;

    private boolean facing;

    private TextureAtlas playerAtlas;

    private TextureRegion STANDING;
    private TextureRegion JUMP_FALLING;
    private TextureRegion[] WALK_ANIM;
    private TextureRegion PREPARE_JUMP;
    private TextureRegion PLAYER_JUMP_UP;
    private TextureRegion PLAYER_BOUNCED_WALL;
    private TextureRegion PLAYER_LAYING;

    private GameScreen screen;

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

        this.PLAYER_BOUNCED_WALL = playerAtlas.findRegion("playerBounceWall");

        this.PLAYER_JUMP_UP = playerAtlas.findRegion("playerJumpUp");

        this.PLAYER_LAYING = playerAtlas.findRegion("playerLay");

        this.PREPARE_JUMP = playerAtlas.findRegion("playerSquat");

        this.game = owner;

        this.loc = position;

        this.spaceTime = 0;

        this.facing = true;

        this.collision = new CollisionBox(game.getAssets());

        velocity = new Vector(0, 0);

        animKlatka = 0;
        lastAnim = System.currentTimeMillis();

    }

    private Vector getInputVector(){

        Vector vector = new Vector(0, 0);

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

            laying = false;
            return vector;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {

            laying = false;
            vector.x+=1;

        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {

            laying = false;
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

        return vector.multiply(PLAYER_SPEED);

    }

    public void changeLevel(Level level, LevelUtil.LevelPosition position) {

        loc = position.changePosition((int) loc.x, (int) loc.y);

        screen.setDisplayedLevel(level);

    }

    public void click(float a, float b) {

      // loc = new Position(a, b);

    }

    public boolean isOnGround(){

        return collision.isOnGround(this, loc, screen.getDisplayedLevel());
    }

    public void jump(float strength) {

        if(strength < 0.2) strength = 0.2f;

        Vector vector = new Vector(0, 0);

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {

            vector.x+=10;

        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {

            vector.x-=10;

        }

        vector.y = 25;

        vector.y *= strength;

        velocity.add(vector);
    }

    private boolean bounced = false;
    private boolean laying = false;

    public void bounced() {

        bounced = true;

    }

    public void fellDown() {

        laying = true;

    }


    private void calcVectors(){

        if(Math.abs(velocity.x) <= 0.1) velocity.x = 0;
        if(Math.abs(velocity.y) <= 0.1) velocity.y = 0;

        boolean onGround = isOnGround();

        if(!onGround) {

            velocity.add(new Vector(0, GRAVITY_SPEED));

        } else {

            bounced = false;

            if(velocity.y < 0) velocity.y = 0;

            Vector input = getInputVector();

            velocity.multiply(new Vector(0.91f, 1f));

            if(input.x == 0) velocity.multiply(new Vector(0.74f, 1f));

            if(Math.abs(velocity.x) > 8) return;

            velocity.add(input);


        }

    }

    public String getVectorStringDebug(){
        return "Vector X: "+velocity.x+" Y: "+velocity.y;
    }

    public void update(Level level){

        calcVectors();

        if(isOnGround() && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

            velocity.x = 0;

            spaceTime += Gdx.graphics.getDeltaTime();

            if(spaceTime > 0.8) {

                jump(1);

                spaceTime = 0;

            }

        } else if(spaceTime > 0) {

            if(spaceTime < 0.15) spaceTime = 0;

            else {

                float strength = spaceTime/0.8f;

                jump(strength);

                spaceTime = 0;

            }

        }

        String before = getVectorStringDebug();

        velocity = collision.calculateMovement(this, velocity, loc, level);

        System.out.println(before+" | "+getVectorStringDebug());

        if(!Gdx.input.isTouched()) loc.add(velocity);

        if(velocity.x == 0) return;

        facing = velocity.x > 0;

    }

    private int animKlatka;
    private long lastAnim;

    public String getLastCollision(){

        return "Last Collision: "+lastType.toString();

    }

    private CollisionData.CollisionType lastType = CollisionData.CollisionType.NO_COLLISION;

    public void debugCollision(CollisionData.CollisionType type) {

        lastType = type;

    }

    public void render(SpriteBatch batch, Level level) {

        update(level);

        TextureRegion renderRegion = STANDING;

        boolean onGround = isOnGround();

        if(!onGround)  {

            renderRegion = velocity.y > 0 ? PLAYER_JUMP_UP : JUMP_FALLING;
            animKlatka = 0;
            lastAnim = System.currentTimeMillis();

            if(bounced) {

                renderRegion = PLAYER_BOUNCED_WALL;

            }
        }

        if(laying) {

            animKlatka = 0;
            lastAnim = System.currentTimeMillis();
            renderRegion = PLAYER_LAYING;

        }

        if(onGround && (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) && !laying) {

            if(System.currentTimeMillis() - lastAnim > 100L) {

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
