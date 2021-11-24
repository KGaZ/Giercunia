package me.kgaz.physics;

public class Vector {

    public float x,y;

    public Vector(int x, int y) {

        this.x = x;
        this.y =y;

    }

    public Vector add(Vector vector) {

        this.x+=vector.x;
        this.y+=vector.y;

        return this;

    }

    public Vector subtract(Vector vector) {

        this.x-=vector.x;
        this.y-=vector.y;

        return this;

    }

    public double distance(){

        return Math.sqrt((x*x) + (y*y));

    }

    public double distanceSquared() {

        return (x*x) + (y*y);

    }

    public Vector reverse(){
        this.x = -x;
        this.y = -y;

        return this;
    }

    public Vector normalize() {

        double dist = distance();

        if(dist == 0) return this;

        this.x /=dist;
        this.y /=dist;

        return this;

    }

    public Vector multiply(Vector vector) {

        this.x*=vector.x;
        this.y*=vector.y;

        return this;

    }

    public Vector multiply(double z){

        this.x*=z;
        this.y*=z;

        return this;
    }

    public Vector multiply(double x, double y){

        this.x*=x;
        this.y*=y;

        return this;

    }



}
