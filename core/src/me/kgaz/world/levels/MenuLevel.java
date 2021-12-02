package me.kgaz.world.levels;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.kgaz.assets.Assets;
import me.kgaz.physics.Position;
import me.kgaz.player.Player;
import me.kgaz.utils.LevelBuilder;
import me.kgaz.world.Level;

public class MenuLevel extends Level {

    public MenuLevel(Assets assets) {

        super(assets, assets.LEVEL_BACKGROUNDS.findRegion("plainsBackground"), null);

        super.rightLevel = Level01.class;

    }

    @Override
    public Position getDefaultPosition(){

        return new Position(230, 600- Player.PLAYER_HEIGHT);

    }

    @Override
    public int[][] buildLevel() {

        return new LevelBuilder().setGroundLevel(20).
                fillBlock(0, 0, 18, 135).
                fillBlock(18, 0, 2, 50).
                fillBlock(110, 0, 50, 100).
                fillBlock(0, 130, 240, 5).
                fillBlock(105, 80, 40, 4).
                fillBlock(85, 50, 40, 4).
                fillBlock(10, 95, 40, 4).
                fillBlock(150, 0, 90, 100).
                empty(110, 20, 80, 25).
                empty(150, 20, 40, 80).
                fillBlock(170, 110, 5, 25).
                fillBlock(170, 90, 40, 5).
                build();
    }

}
