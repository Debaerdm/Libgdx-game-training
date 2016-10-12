package com.pointGame.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Debaerdm on 11/10/2016.
 */
public class Assets {

    private static final float RUNNING_FRAME_DURATION = 0.06f;

    private TextureRegion playerIdleLeft;
    private TextureRegion playerIdleRight;
    private TextureRegion playerJumpLeft;
    private TextureRegion playerFallLeft;
    private TextureRegion playerJumpRight;
    private TextureRegion playerFallRight;
    private TextureRegion blockTexture;
    private TextureRegion playerFrame;

    private Animation walkLeftAnimation;
    private Animation walkRightAnimation;

    public Assets(){
        this.loadTextures();
    }

    private void loadTextures() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/textures/textures.atlas"));
        this.playerIdleLeft = atlas.findRegion("bob", 1);
        this.playerIdleRight = new TextureRegion(playerIdleLeft);
        this.playerIdleRight.flip(true, false);
        this.blockTexture = atlas.findRegion("block");
        int walkNbFrames = 5;
        TextureRegion[] walkLeftFrames = new TextureRegion[walkNbFrames];
        for (int i = 0; i < walkNbFrames; i++){
            walkLeftFrames[i] = atlas.findRegion("bob", i + 2);
        }
        walkLeftAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFrames);

        TextureRegion[] walkRightFrames = new TextureRegion[walkNbFrames];
        for (int i = 0; i < walkNbFrames; i++){
            walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
            walkRightFrames[i].flip(true, false);
        }
        walkRightAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFrames);

        this.playerJumpLeft = atlas.findRegion("bob_up");
        this.playerJumpRight = new TextureRegion(playerJumpLeft);
        this.playerJumpRight.flip(true, false);

        this.playerFallLeft = atlas.findRegion("bob_down");
        this.playerFallRight = new TextureRegion(playerFallLeft);
        this.playerFallRight.flip(true, false);

    }

    public TextureRegion getBlockTexture() {
        return blockTexture;
    }

    public TextureRegion getPlayerIdleLeft() {
        return playerIdleLeft;
    }

    public TextureRegion getPlayerIdleRight() {
        return playerIdleRight;
    }

    public TextureRegion getPlayerFrame() {
        return playerFrame;
    }

    public void setPlayerFrame(TextureRegion playerFrame) {
        this.playerFrame = playerFrame;
    }

    public Animation getWalkLeftAnimation() {
        return walkLeftAnimation;
    }

    public Animation getWalkRightAnimation() {
        return walkRightAnimation;
    }

    public TextureRegion getPlayerFallLeft() {
        return playerFallLeft;
    }

    public TextureRegion getPlayerFallRight() {
        return playerFallRight;
    }

    public TextureRegion getPlayerJumpLeft() {
        return playerJumpLeft;
    }

    public TextureRegion getPlayerJumpRight() {
        return playerJumpRight;
    }
}
