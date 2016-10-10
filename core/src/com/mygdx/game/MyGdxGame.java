package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.pointGame.World.Floor;
import com.pointGame.World.World;
import com.pointGame.config.Constante;
import com.pointGame.character.Player;

public class MyGdxGame extends ApplicationAdapter {

	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private World world;
	private Player player;

	@Override
	public void create () {
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Constante.SCREEN_WIDTH, Constante.SCREEN_HEIGHT);

		this.player = new Player(Constante.PLAYER_LIFE_DEFAULT, Constante.PLAYER_FORCE_DEFAULT, 50, 50);

		Floor floor = new Floor(50);
		floor.generateFloor();
		floor.setAllObstacleColor(Color.GREEN);

		this.shapeRenderer = new ShapeRenderer();

		this.world = new World(player, floor);
		this.world.generateObstacle();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);

		this.player.update(shapeRenderer);

		this.intPutMove();
		this.world.getFloor().update(shapeRenderer);
		this.world.updateObstacle(shapeRenderer);

		this.world.getPlayer().fall();
	}

	public void intPutMove(){
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			this.world.addToPlayer(1,0);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			this.world.addToPlayer(-1,0);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
			this.world.addToPlayer(0,1);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			this.world.addToPlayer(0,-1);
		}
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
