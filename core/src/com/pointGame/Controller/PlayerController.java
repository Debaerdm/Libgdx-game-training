package com.pointGame.Controller;

import com.badlogic.gdx.math.Vector2;
import com.pointGame.config.Enums;
import com.pointGame.model.Player;
import com.pointGame.model.World;

import java.util.HashMap;
import java.util.Map;

public class PlayerController {

    private static final long LONG_JUMP_PRESS = 150l;
    private static final float ACCELERATION = 20f;
    private static final float GRAVITY = -20f;
    private static final float MAX_JUMP_SPEED = 7f;
    private static final float DAMP = 0.90f;
    private static final float MAX_VEL = 4f;

    private static final float WIDTH = 10f;

    private World world;
    private Player player;
    private long jumpPressedTime;
    private boolean jumpingPressed;

    public static Map<Enums.Keys, Boolean> keys = new HashMap<Enums.Keys, Boolean>();

    static {
        keys.put(Enums.Keys.LEFT, false);
        keys.put(Enums.Keys.RIGHT, false);
        keys.put(Enums.Keys.JUMP, false);
        keys.put(Enums.Keys.FIRE, false);
    }

    public PlayerController(World world){
        this.world = world;
        this.player = world.getPlayer();
    }

    public void leftPressed(){
        keys.get(keys.put(Enums.Keys.LEFT, true));
    }

    public void rightPressed(){
        keys.get(keys.put(Enums.Keys.RIGHT, true));
    }

    public void jumpPressed(){
        keys.get(keys.put(Enums.Keys.JUMP, true));
    }

    public void firePressed(){
        keys.get(keys.put(Enums.Keys.FIRE, true));
    }

    public void leftReleased(){
        keys.get(keys.put(Enums.Keys.LEFT, false));
    }

    public void rightReleased(){
        keys.get(keys.put(Enums.Keys.RIGHT, false));
    }

    public void jumpReleased(){
        keys.get(keys.put(Enums.Keys.JUMP, false));
        jumpingPressed = false;
    }

    public void fireReleased(){
        keys.get(keys.put(Enums.Keys.FIRE, false));
    }

    public void update(float delta) {
        this.processInput();

        this.player.getAcceleration().y = GRAVITY;

        this.player.setAcceleration(new Vector2(this.player.getAcceleration().x*delta, this.player.getAcceleration().y*delta));

        this.player.getVelocity().add(this.player.getAcceleration().x, this.player.getAcceleration().y);

        if(this.player.getAcceleration().x == 0) {
            this.player.getVelocity().x *= DAMP;
        }

        if(this.player.getVelocity().x > MAX_VEL) {
            this.player.getVelocity().x = MAX_VEL;
        }

        if(this.player.getVelocity().x < -MAX_VEL) {
            this.player.getVelocity().x = -MAX_VEL;
        }

        this.player.update(delta);

        if (this.player.getPosition().y < 0) {
            this.player.getPosition().y = 0f;
            this.player.setPosition(this.player.getPosition());
            if (this.player.getState().equals(Enums.State.JUMPING)) {
                this.player.setState(Enums.State.IDLE);
            }
        }

        if (this.player.getPosition().x < 0) {
            this.player.getPosition().x = 0f;
            this.player.setPosition(this.player.getPosition());
            if (!this.player.getState().equals(Enums.State.JUMPING)) {
                this.player.setState(Enums.State.IDLE);
            }
        }

        if (this.player.getPosition().x > WIDTH - this.player.getBounds().width) {
            this.player.getPosition().x = WIDTH - this.player.getBounds().width;
            this.player.setPosition(this.player.getPosition());
            if (!this.player.getState().equals(Enums.State.JUMPING)) {
                this.player.setState(Enums.State.IDLE);
            }
        }
    }

    private boolean processInput() {
        if (keys.get(Enums.Keys.JUMP)){
            if (!this.player.getState().equals(Enums.State.JUMPING)){
                this.jumpingPressed = true;
                this.jumpPressedTime = System.currentTimeMillis();
                this.player.setState(Enums.State.JUMPING);
                this.player.getVelocity().y = MAX_JUMP_SPEED;
            } else {
                if (jumpingPressed && ((System.currentTimeMillis() - jumpPressedTime) >= LONG_JUMP_PRESS)) {
                    jumpingPressed = false;
                } else {
                    if (jumpingPressed) {
                        this.player.getVelocity().y = MAX_JUMP_SPEED;
                    }
                }
            }
        }

        if (keys.get(Enums.Keys.LEFT)){
            this.player.setFacingLeft(true);
            if (!this.player.getState().equals(Enums.State.JUMPING)) {
                this.player.setState(Enums.State.WALKING);
            }

            this.player.getAcceleration().x = -ACCELERATION;
        } else if (keys.get(Enums.Keys.RIGHT)){
            this.player.setFacingLeft(false);
            if (!this.player.getState().equals(Enums.State.JUMPING)) {
                this.player.setState(Enums.State.WALKING);
            }

            this.player.getAcceleration().x = ACCELERATION;
        } else {
            if (!this.player.getState().equals(Enums.State.JUMPING)) {
                this.player.setState(Enums.State.IDLE);
            }
            this.player.getAcceleration().x = 0;
        }

        return false;
    }
}
