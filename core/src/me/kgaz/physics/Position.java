package me.kgaz.physics;

public class Position {

    public float x, y;

    public Position(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public Position(float x, float y) {

        this.x = x;
        this.y = y;

    }

    public void add(Vector vector) {

        this.x+=vector.x;
        this.y+=vector.y;

    }

}
