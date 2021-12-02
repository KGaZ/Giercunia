package me.kgaz.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.kgaz.assets.Assets;
import me.kgaz.physics.Position;
import me.kgaz.player.Player;
import me.kgaz.utils.LevelUtil;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public abstract class Level {

    protected TextureRegion foreGround;
    protected TextureRegion backGround;
    protected int[][] tileSet;

    protected Class<? extends Level> leftLevel, rightLevel, upLevel, downLevel;

    private Tiles TILESET;

    private Assets assets;

    public Level(Assets assets, TextureRegion backGround, TextureRegion foreGround) {

        this.assets = assets;

        TILESET = assets.TILESET;

        this.backGround = backGround;
        this.foreGround = foreGround;

        tileSet = buildLevel();

        leftLevel = null;
        rightLevel = null;
        upLevel = null;
        downLevel = null;

    }

    public abstract int[][] buildLevel();

    // can be overriden

    public final boolean isSolid(Player player, Position pos) {

        return isSolid(player, pos.x, pos.y);

    }

    public final boolean isSolid(Player player, float x, float y) {

        int tileX = (int) Math.floor(x/Tile.TILE_SIZE);
        int tileY = (int) Math.floor(y/Tile.TILE_SIZE);

        if(tileX < 0 || tileX >= 240) {

            if(tileX < 0) {

                if(leftLevel == null) return true;

                Level level;

                try {

                    level = leftLevel.getConstructor(Assets.class).newInstance(assets);

                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {

                    e.printStackTrace();

                    return true;
                }

                player.changeLevel(level, LevelUtil.LevelPosition.LEFT);

                return false;

            }

            if(rightLevel == null) return true;

            Level level;

            try {

                level = rightLevel.getConstructor(Assets.class).newInstance(assets);

            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {

                e.printStackTrace();

                return true;
            }

            player.changeLevel(level, LevelUtil.LevelPosition.RIGHT);

            return false;

        }
        if(tileY < 0 || tileY >= 135) {

            if(tileY < 0) {

                if(downLevel == null) return true;

                Level level;

                try {

                    level = downLevel.getConstructor(Assets.class).newInstance(assets);

                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {

                    e.printStackTrace();

                    return true;
                }

                player.changeLevel(level, LevelUtil.LevelPosition.DOWN);

                return false;

            }

            if(upLevel == null) return true;

            Level level;

            try {

                level = upLevel.getConstructor(Assets.class).newInstance(assets);

            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {

                e.printStackTrace();

                return true;
            }

            player.changeLevel(level, LevelUtil.LevelPosition.UP);

            return false;

        }

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
