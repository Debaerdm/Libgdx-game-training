package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.pointGame.config.Constante;
import com.pointGame.character.Player;
import com.pointGame.gameObject.Obstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {

	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private Player player;
	private List<Obstacle> obstacleList;
	
	@Override
	public void create () {
		obstacleList = new ArrayList<Obstacle>();
		camera = new OrthographicCamera(Constante.SCREEN_WITDH, Constante.SCREEN_HEIGHT);

		player = new Player(Constante.PLAYER_LIFE_DEFAULT, Constante.PLAYER_FORCE_DEFAULT, 50, 50);
		shapeRenderer = new ShapeRenderer();
		this.generateObstacle();
	}

	public void generateObstacle(){
		Random random = new Random();
		for(int i = 0; i < (random.nextInt(10)+5); i++){
			Obstacle obstacle = new Obstacle(((float)(random.nextInt(Constante.SCREEN_WITDH - (int)Constante.OBSTACLE_WIDHT)) + Constante.OBSTACLE_WIDHT),
					((float)(random.nextInt(Constante.SCREEN_HEIGHT - (int)Constante.OBSTACLE_HEIGHT)) + Constante.OBSTACLE_HEIGHT),
					Constante.OBSTACLE_WIDHT, Constante.OBSTACLE_HEIGHT);
			this.obstacleList.add(obstacle);
		}
	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);

		this.player.update(shapeRenderer);

		this.intPutMove();
		this.outScreen();

		for (Obstacle obstacle: obstacleList) {
			obstacle.update(shapeRenderer);
			if (this.player.isCollide(obstacle)){

			}
		}
	}

	public void intPutMove(){
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.add(1,0);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			player.add(-1,0);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
			player.add(0,1);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			player.add(0,-1);
		}
	}

	public void outScreen(){
		if(player.getVector2().x < 0) player.getVector2().x = 0;
		if(player.getVector2().x > Constante.SCREEN_WITDH - Constante.PLAYER_RADIUS) player.getVector2().x = Constante.SCREEN_WITDH - Constante.PLAYER_RADIUS;
		if(player.getVector2().y < 0) player.getVector2().y = 0;
		if(player.getVector2().y > Constante.SCREEN_HEIGHT - Constante.PLAYER_RADIUS) player.getVector2().y = Constante.SCREEN_HEIGHT - Constante.PLAYER_RADIUS;
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
