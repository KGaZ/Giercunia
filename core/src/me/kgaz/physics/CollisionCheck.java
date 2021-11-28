package me.kgaz.physics;

import me.kgaz.world.Level;

public class CollisionCheck {

    public int relativeX;
    public int relativeY;

    public CollisionCheck(int x, int y){

        this.relativeX = x;
        this.relativeY = y;

    }

//    public Vector checkAvailableMovement(Vector movement, Position position, Level level) {
//
//        return new Vector(checkDistanceX((int) movement.x, position, level), checkDistanceY((int) movement.y, position, level));
//
//    }

    public int checkDistanceUp(int capacity, Position position, Level level, Vector velocity) {

        Position cPos = new Position(relativeX+position.x, relativeY+position.y);

        int diffX = velocity.x < 0 ? 1 : -1;

        if(velocity.x == 0) diffX = 0;

        for(int i = 0; i < capacity; i++) {

            if(level.isSolid(cPos.x+diffX, cPos.y+i)) {

                return i;

            }

        }

        return capacity;

    }

//    public int checkDistanceY(int capacity, Position position, Level level) {
//
//        if(capacity > 0) return checkDistanceUp(capacity, position, level);
//        else if (capacity == 0) return 0;
//        else return checkDistanceDown(-capacity, position, level);
//
//    }

    public int checkDistanceDown(int capacity, Position position, Level level, Vector velocity) {

        Position cPos = new Position(relativeX+position.x, relativeY+position.y);

        int diffX = velocity.x < 0 ? 1 : -1;

        if(velocity.x == 0) diffX = 0;

        for(int i = 0; i < capacity; i++) {

            if(level.isSolid(cPos.x+diffX, cPos.y+i)) {

                return -i;

            }

        }

        return -capacity;

    }

    public int checkDistanceRight(int capacity, Position position, Level level) {

        Position cPos = new Position(relativeX+position.x, relativeY+position.y+1);

        for(int i = 0; i < capacity; i++) {

            if(level.isSolid(cPos.x+i, cPos.y)) {

                return i;

            }

        }

        return capacity;

    }

    public int checkDistanceX(int capacity, Position position, Level level) {

        if(capacity > 0) return checkDistanceRight(capacity, position, level);
        else if (capacity == 0) return 0;
        else return checkDistanceLeft(-capacity, position, level);

    }

    public int checkDistanceLeft(int capacity, Position position, Level level) {

        Position cPos = new Position(relativeX+position.x, relativeY+position.y+1);

        for(int i = 0; i < capacity; i++) {

            if(level.isSolid(cPos.x-i, cPos.y)) {

                return -i;

            }

        }

        return -capacity;

    }

}
