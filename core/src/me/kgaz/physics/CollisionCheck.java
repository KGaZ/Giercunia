package me.kgaz.physics;

import me.kgaz.world.Level;

public class CollisionCheck {

    private float relativeX, relativeY;

    public CollisionCheck(float x, float y) {

        this.relativeX = x;
        this.relativeY = y;

    }

    // work
    private float possibleMove = 0, i = 0;
    private Position workPosition = new Position(0, 0);

    public float checkDistanceLeft(float capacity, Level level, Position position) {

        possibleMove = 0;

        for(i = 0; i < capacity; i+=0.1f) {

            workPosition = new Position(relativeX - i - i + position.x, position.y + relativeY);

            if(level.isSolid(workPosition)) return possibleMove;

            possibleMove += 0.1f;

        }

        return -possibleMove;

    }

    public float checkDistanceRight(float capacity, Level level, Position position) {

        possibleMove = 0;

        for(i = 0; i < capacity; i+=0.1f) {

            workPosition = new Position(relativeX + (1f) + i + position.x, position.y + relativeY);

            if(level.isSolid(workPosition)) return possibleMove;

            possibleMove += 0.1f;

        }

        return possibleMove;

    }

    public float checkDistanceUp(float capacity, Level level, Position position) {

        possibleMove = 0;

        for(i = 0; i < capacity; i+=0.1f) {

            workPosition = new Position(relativeX + position.x, position.y + relativeY + i + 1f);

            if(level.isSolid(workPosition)) return possibleMove;

            possibleMove += 0.1f;

        }

        return possibleMove;

    }

    public float checkDistanceDown(float capacity, Level level, Position position) {

        possibleMove = 0;

        for(i = 0; i < capacity; i+=0.1f) {

            workPosition = new Position(relativeX + position.x, position.y + relativeY - i - 1f);

            if(level.isSolid(workPosition)) return possibleMove;

            possibleMove += 0.1f;

        }

        return -possibleMove;

    }

}
