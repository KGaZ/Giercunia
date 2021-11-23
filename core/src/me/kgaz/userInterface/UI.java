package me.kgaz.userInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class UI {

    protected int x,y;
    protected int width, height;

    public UI(int x, int y, int width, int height){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.hovered = false;

    }

    public void update(int mouseX, int mouseY){

        if(isHovered()) {

            if(Gdx.input.isTouched()) {

                onClick();

            }

        }

        if(isHovered() && !isWithin(mouseX, mouseY)) {

            hovered = false;
            onUnHover();

        } else if (!isHovered() && isWithin(mouseX, mouseY)) {

            hovered = true;
            onHover();

        }

    }

    public boolean isWithin(int mouseX, int mouseY){

        return (mouseX >= getX() && mouseX <=getX()+getWidth() && mouseY >= getY() && mouseY <= getY()+getHeight());

    }

    public final boolean isHovered() {

        return hovered;

    }

    public final void setHovered(boolean hovered) {

        this.hovered = hovered;

    }

    /*
    These can be overriden if neccessarry
     */

    protected int getX(){
        return x;
    }

    protected int getY() {
        return y;
    }

    protected int getWidth() {
        return width;
    }

    protected int getHeight() {
        return height;
    }

    protected boolean hovered;

    public abstract void onClick();
    public abstract void onHover();
    public abstract void onUnHover();

    public abstract void render(SpriteBatch batch);

    public abstract void dispose();

}
