package com.pointGame.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.pointGame.Controller.PlayerController;
import com.pointGame.model.World;
import com.pointGame.view.WorldRenderer;

/**
 * Created by Debaerdm on 11/10/2016.
 */
public class GameScreen implements Screen, InputProcessor {

    private World world;
    private WorldRenderer worldRenderer;
    private PlayerController playerController;

    private int width, height;

    @Override
    public void show() {
        this.world = new World();
        this.worldRenderer = new WorldRenderer(world, false);
        this.playerController = new PlayerController(world);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.playerController.update(delta);
        this.worldRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        this.worldRenderer.setSize(width,height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.Q:
                playerController.leftPressed();
                break;
            case Input.Keys.D:
                playerController.rightPressed();
                break;
            case Input.Keys.SPACE:
                playerController.jumpPressed();
                break;
            case Input.Keys.Z:
                playerController.firePressed();
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.Q:
                playerController.leftReleased();
                break;
            case Input.Keys.D:
                playerController.rightReleased();
                break;
            case Input.Keys.SPACE:
                playerController.jumpReleased();
                break;
            case Input.Keys.Z:
                playerController.fireReleased();
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(Application.ApplicationType.Android))
            return false;

        if (screenX < width / 2 && screenY > height / 2) {
            this.playerController.leftPressed();
        }
        if (screenX > width / 2 && screenY > height / 2) {
            this.playerController.rightPressed();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(Application.ApplicationType.Android))
            return false;

        if (screenX < width / 2 && screenY > height / 2) {
            this.playerController.leftReleased();
        }
        if (screenX > width / 2 && screenY > height / 2) {
            this.playerController.rightReleased();
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
