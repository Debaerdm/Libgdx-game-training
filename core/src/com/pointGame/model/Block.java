package com.pointGame.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Debaerdm on 11/10/2016.
 */
public class Block {

    public static final float SIZE = 1f;

    public Vector2 position;
    public Rectangle bounds;

    public Block(){
        this.position = new Vector2();
        this.bounds = new Rectangle();
    }

    public Block(Vector2 position){
        this();
        this.position = position;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
        this.bounds.x = this.position.x;
        this.bounds.y = this.position.y;
    }

    public static float getSIZE() {
        return SIZE;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
