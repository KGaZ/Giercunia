package me.kgaz.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.kgaz.assets.Assets;
import me.kgaz.physics.Position;
import me.kgaz.player.Player;

import java.io.*;
import java.util.Scanner;

public abstract class Level {

    protected TextureRegion foreGround;
    protected TextureRegion backGround;
    protected int[][] tileSet;

    private Tiles TILESET;

    public Level(Assets assets, TextureRegion backGround, TextureRegion foreGround) {

        TILESET = assets.TILESET;

        this.backGround = backGround;
        this.foreGround = foreGround;

        tileSet = buildLevel();

    }

    public abstract int[][] buildLevel();

    // can be overriden

    public boolean isSolid(Position pos) {

        return isSolid(pos.x, pos.y);

    }

    public boolean isSolid(float x, float y) {

        int tileX = (int) Math.floor(x/Tile.TILE_SIZE);
        int tileY = (int) Math.floor(y/Tile.TILE_SIZE);

        if(tileX < 0 || tileX >= 240) return true;
        if(tileY < 0 || tileY >= 135) return true;

        return TILESET.tileSet[tileSet[tileX][tileY]].isSolid((int) (x-tileX), (int) (y-tileY));

    }

    public Position getDefaultPosition(){

        return new Position(0, 1080- Player.PLAYER_HEIGHT);

    }

    public void render(SpriteBatch batch){

        batch.draw(backGround, 0, 0, 1920, 1080);

        for(int x = 0; x < 240; x++) {

            for(int y = 0; y < 135; y++) {

                TILESET.tileSet[tileSet[x][y]].render(batch, x*Tile.TILE_SIZE, y*Tile.TILE_SIZE);

            }

        }

    }

    public void renderForeGround(SpriteBatch batch) {

        if(foreGround != null) batch.draw(foreGround, 0, 0, 1920, 1080);

    }



}
