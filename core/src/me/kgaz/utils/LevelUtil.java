package me.kgaz.utils;

import me.kgaz.physics.Position;
import me.kgaz.player.Player;

public class LevelUtil {

    public enum LevelPosition {

        DOWN {

            @Override
            public Position changePosition(int x, int y) {

                return new Position(x, 1080 - Player.PLAYER_HEIGHT-1);

            }


        },

        LEFT {

            @Override
            public Position changePosition(int x, int y) {

                return new Position(1920-Player.PLAYER_WIDTH-1, y);

            }


        },

        RIGHT {

            @Override
            public Position changePosition(int x, int y) {

                return new Position(Player.PLAYER_WIDTH+1, y);

            }


        },

        UP {

            @Override
            public Position changePosition(int x, int y) {

                return new Position(x, Player.PLAYER_HEIGHT+1);

            }


        };

        public abstract Position changePosition(int x, int y);

    }

}
