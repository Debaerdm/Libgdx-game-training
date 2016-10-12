package com.pointGame.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pointGame.config.Enums;
import com.pointGame.model.Assets;
import com.pointGame.model.Block;
import com.pointGame.model.Player;
import com.pointGame.model.World;

public class WorldRenderer {

    private static final float CAMERA_WIDTH = 10f;
    private static final float CAMERA_HEIGHT = 7f;

    private Assets assets;
    private World world;
    private OrthographicCamera camera;

    public ShapeRenderer debugRender = new ShapeRenderer();

    private SpriteBatch spriteBatch;
    private boolean debug = false;
    private int width;
    private int height;
    private float ppuX;
    private float ppuY;

    public void setSize(int w, int h){
        this.width = w;
        this.height = h;
        this.ppuX = (float) this.width/CAMERA_WIDTH;
        this.ppuY = (float) this.height/CAMERA_HEIGHT;
    }

    public WorldRenderer(World world, boolean debug){
        this.world = world;
        this.camera = new OrthographicCamera(CAMERA_WIDTH,CAMERA_HEIGHT);
        this.camera.position.set(CAMERA_WIDTH/2f,CAMERA_HEIGHT/2f,0);
        this.camera.update();
        this.debug = debug;
        this.spriteBatch = new SpriteBatch();
        this.assets = new Assets();
    }

    public void render(){
        this.spriteBatch.begin();
        this.drawBlocks();
        this.drawPlayer();
        this.spriteBatch.end();
        if (debug)
            drawDebug();
    }

    private void drawBlocks() {
        for (Block block: this.world.getBlocks()) {
            spriteBatch.draw(
                    assets.getBlockTexture(),
                    block.getPosition().x * ppuX,
                    block.getPosition().y * ppuY,
                    Block.getSIZE() * ppuX,
                    Block.getSIZE() * ppuY);
        }
    }

    private void drawPlayer() {
        Player player = this.world.getPlayer();
        this.assets.setPlayerFrame(player.isFacingLeft() ? this.assets.getPlayerIdleLeft() : this.assets.getPlayerIdleRight());
        if (player.getState().equals(Enums.State.WALKING)) {
            this.assets.setPlayerFrame(player.isFacingLeft() ?
                    this.assets.getWalkLeftAnimation().getKeyFrame(player.getStateTime(), true) :
                    this.assets.getWalkRightAnimation().getKeyFrame(player.getStateTime(), true));
        } else if (player.getState().equals(Enums.State.JUMPING)) {
            if (player.getVelocity().y > 0){
                this.assets.setPlayerFrame((player.isFacingLeft())? this.assets.getPlayerJumpLeft() : this.assets.getPlayerJumpRight());
            } else {
                this.assets.setPlayerFrame((player.isFacingLeft())? this.assets.getPlayerFallLeft() : this.assets.getPlayerFallRight());
            }
        }

        spriteBatch.draw(
                this.assets.getPlayerFrame(),
                player.getPosition().x * ppuX,
                player.getPosition().y * ppuY,
                Player.getSIZE() * ppuX,
                Player.getSIZE() * ppuY);
    }

    public void drawDebug() {
        this.debugRender.setProjectionMatrix(camera.combined);
        this.debugRender.begin(ShapeRenderer.ShapeType.Line);

        for (Block block: this.world.getBlocks()) {
            Rectangle rect = block.getBounds();
            float x1 = block.getPosition().x;
            float y1 = block.getPosition().y;
            this.debugRender.setColor(Color.RED);
            this.debugRender.rect(x1,y1, rect.width, rect.height);
        }

        Player player = this.world.getPlayer();
        Rectangle rect = player.getBounds();
        float x1 = player.getPosition().x;
        float y1 = player.getPosition().y;
        this.debugRender.setColor(Color.GREEN);
        this.debugRender.rect(x1,y1, rect.width, rect.height);
        this.debugRender.end();
    }
}
