package me.kgaz.world.levels;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.kgaz.assets.Assets;
import me.kgaz.utils.LevelBuilder;
import me.kgaz.world.Level;

public class Level01 extends Level {

    public Level01(Assets assets) {

        super(assets, assets.LEVEL_BACKGROUNDS.findRegion("plainsBackground"), null);

        super.leftLevel = MenuLevel.class;

    }

    @Override
    public int[][] buildLevel() {

        return new LevelBuilder().setGroundLevel(100).
                empty(20, 20, 200, 100).
                fillBlock(110, 20, 10, 100).
                fillBlock(50, 80, 20, 5).
                fillBlock(90, 80, 30, 5).
                fillBlock(75, 65, 10, 5).
                fillBlock(100, 10, 15, 25).
                fillBlock(50, 30, 15, 5).
                fillBlock(45, 0, 5, 134).
                empty(25, 20, 10, 100).
                empty(25, 20, 40, 20).
                build();

    }

}
