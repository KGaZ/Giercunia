package me.kgaz.physics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.kgaz.world.Level;

public class CollisionCheck {

    private int relativeX, relativeY;

    private Texture region;

    public CollisionCheck(int relativeX, int relativeY, Texture textureRegion) {

        this.relativeX = relativeX;
        this.relativeY = relativeY;

        region = textureRegion;
    }

    public Vector calculateMovement(Vector vector, Position position, Level level) {

        int moveX = 0;
        int moveY = 0;

        if(vector.y > 0) moveY = calculatePossibleMoveUp((int) Math.ceil(vector.y), position, level);
        else if(vector.y < 0) moveY = -calculatePossibleMoveDown((int) Math.floor(-vector.y), position, level);

        if(vector.x > 0) moveX = calculatePossibleMoveRight((int) Math.ceil(vector.x), position, level);
        else if(vector.x < 0) moveX = -calculatePossibleMoveLeft((int) Math.floor(-vector.x), position, level);

        return new Vector(moveX, moveY);

    }

    public int calculatePossibleMoveRight(int capacity, Position position, Level level) {

        int possibleMove = 0;

        for(int i = 1; i < capacity; i++) {

            if(level.isSolid(position.x+relativeX+i, position.y+relativeY)) break;

            possibleMove++;
        }

        return possibleMove;

    }

    public int calculatePossibleMoveLeft(int capacity, Position position, Level level) {

        int possibleMove = 0;

        for(int i = 1; i < capacity; i++) {

            if(level.isSolid(position.x+relativeX-i, position.y+relativeY)) break;

            possibleMove++;
        }

        return possibleMove;

    }

    public int calculatePossibleMoveUp(int capacity, Position position, Level level) {

        int possibleMove = 0;

        for(int i = 1; i < capacity; i++) {

            if(level.isSolid(position.x+relativeX, position.y+relativeY+i)) break;

            possibleMove++;
        }

        return possibleMove;

    }

    public int calculatePossibleMoveDown(int capacity, Position position, Level level) {

        int possibleMove = 0;

        for(int i = 1; i < capacity; i++) {

            if(level.isSolid(position.x+relativeX, position.y+relativeY-i)) break;

            possibleMove++;
        }

        return possibleMove;

    }

    public boolean isInBlock(Position position, Level level) {

        return level.isSolid(new Position(position.x+relativeX, position.y+relativeY));

    }

    public void render(SpriteBatch batch, Position playerPosition, Level level){

        batch.draw(region, playerPosition.x+relativeX-1, playerPosition.y+relativeY-1, 3 ,3);

    }

}
