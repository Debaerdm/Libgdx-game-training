package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.pointGame.World.Floor;
import com.pointGame.World.World;
import com.pointGame.config.Constante;
import com.pointGame.character.Player;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {

	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private World world;
	private Player player;

	@Override
	public void create () {
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Constante.SCREEN_WIDTH, Constante.SCREEN_HEIGHT);

		this.player = new Player(Constante.PLAYER_LIFE_DEFAULT, Constante.PLAYER_FORCE_DEFAULT, (int)Constante.PLAYER_RADIUS, (int)(Constante.OBSTACLE_HEIGHT));

		Floor floor = new Floor(50);
		floor.generateFloor();
		floor.setAllObstacleColor(Color.GREEN);

		this.shapeRenderer = new ShapeRenderer();

		this.world = new World(player, floor);
		this.world.generateObstacle();

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);

		this.world.updateMotionPlayer();
		this.world.updateGraphicsPlayer(shapeRenderer);
		this.world.getFloor().update(shapeRenderer);
		this.world.updateObstacle(shapeRenderer);
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
			case Input.Keys.Q:
				this.world.getPlayer().setLeftMove(true);
				break;
			case Input.Keys.D:
				this.world.getPlayer().setRightMove(true);
				break;
			case Input.Keys.SPACE:
				this.world.getPlayer().setJumpMove(true);
				break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case Input.Keys.Q:
				this.world.getPlayer().setLeftMove(false);
				break;
			case Input.Keys.D:
				this.world.getPlayer().setRightMove(false);
				break;
			case Input.Keys.SPACE:
				this.world.getPlayer().setJumpMove(false);
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
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
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
