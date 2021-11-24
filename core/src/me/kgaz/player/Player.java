package me.kgaz.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.kgaz.Main;
import me.kgaz.physics.Position;
import me.kgaz.physics.Vector;
import me.kgaz.world.Level;

public class Player {

    public Position loc;

    public static final int PLAYER_HEIGHT = 64, PLAYER_WIDTH = 64;

    public static final float PLAYER_SPEED = 12;

    private Main game;

    private boolean facing;

    private TextureAtlas playerAtlas;

    private TextureRegion STANDING;

    private Vector velocity;

    public Player(Main owner, Position position) {

        this.velocity = new Vector(0, 0);

        this.playerAtlas = owner.getAssets().PLAYER_TEXTURES;

        this.STANDING = playerAtlas.findRegion("playerStand");

        this.game = owner;

        this.loc = position;

        this.facing = true;
    }

    private Vector getInputVector(){

        Vector vector = new Vector(0, 0);

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {

            vector.x+=1;

        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {

            vector.x-=1;

        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {

            vector.y+=1;

        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {

            vector.y-=1;

        }

        return vector.normalize().multiply(PLAYER_SPEED);

    }


    private void calcVectors(){

        Vector calc = new Vector(0, 0);

        calc.add(getInputVector());

        this.velocity = calc;

        if(velocity.x == 0) return;

        facing = velocity.x > 0;

    }

    public void update(Level level){

        calcVectors();

        Position copy = new Position(loc.x+velocity.x, loc.y+ velocity.y);

        if(level.isSolid(copy)) return;

        loc.add(velocity);

    }


    public void render(SpriteBatch batch, Level level) {

        update(level);

        if(facing) batch.draw(STANDING, loc.x, loc.y, PLAYER_WIDTH, PLAYER_HEIGHT);
        else batch.draw(STANDING, loc.x+PLAYER_WIDTH, loc.y, -PLAYER_WIDTH, PLAYER_HEIGHT);

    }

}
