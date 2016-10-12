package com.pointGame.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pointGame.config.Enums;

public class Player {

    public static final float SPEED = 4f;
    public static final float JUMP_VELOCITY = 1f;
    public static final float SIZE = 0.5f;

    public Vector2 position;
    public Vector2 acceleration;
    public Vector2 velocity;
    public Rectangle bounds;
    public Enums.State state;
    public boolean facingLeft;
    public float stateTime = 0;

    public Player(){
        this.position = new Vector2();
        this.acceleration = new Vector2();
        this.velocity = new Vector2();
        this.bounds = new Rectangle();
        this.state = Enums.State.IDLE;
        this.facingLeft = true;
    }

    public Player(Vector2 position){
        this();
        this.position = position;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
        this.bounds.x = this.position.x;
        this.bounds.y = this.position.y;
    }

    public float getStateTime() {
        return stateTime;
    }

    public static float getSpeed() {
        return SPEED;
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

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Enums.State getState() {
        return state;
    }

    public void setState(Enums.State state) {
        this.state = state;
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    public void update(float delta){
        this.stateTime += delta;
        this.position.mulAdd(velocity.cpy(), delta);
    }
}
