package me.kgaz.physics;

import me.kgaz.player.Player;
import me.kgaz.world.Level;

public class CollisionCheck {

    private final float relativeX;
    private final float relativeY;

    public CollisionCheck(float x, float y) {

        this.relativeX = x;
        this.relativeY = y;

    }

    public CollisionData.CollisionType canMove(Player player, Vector vector, Position position, Level level) {

        if(level.isSolid(player, position.x + relativeX + vector.x, position.y + relativeY + vector.y)) {

            if(vector.x >= vector.y) {

                if(level.isSolid(player, position.x + relativeX + vector.x, position.y + relativeY)) {

                    return vector.x > 0 ? CollisionData.CollisionType.HIT_WALL_RIGHT : CollisionData.CollisionType.HIT_WALL_LEFT;

                }

                return vector.y > 0 ? CollisionData.CollisionType.HIT_WALL_UP : CollisionData.CollisionType.HIT_WALL_DOWN;

            } else {

                if(level.isSolid(player, position.x + relativeX, position.y + relativeY + vector.y)) {

                    return vector.y > 0 ? CollisionData.CollisionType.HIT_WALL_UP : CollisionData.CollisionType.HIT_WALL_DOWN;

                }

                return vector.x > 0 ? CollisionData.CollisionType.HIT_WALL_RIGHT : CollisionData.CollisionType.HIT_WALL_LEFT;

            }

        }

        return CollisionData.CollisionType.NO_COLLISION;

    }

}
