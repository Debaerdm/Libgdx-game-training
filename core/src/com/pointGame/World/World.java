package com.pointGame.World;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pointGame.character.Player;
import com.pointGame.config.Constante;
import com.pointGame.gameObject.Obstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by mathieu on 08/10/16.
 */
public class World {
    private Player player;
    private Floor floor;
    private List<Obstacle> obstacleList;

    public World(Player player, Floor floor){
        this.player = player;
        this.floor = floor;
        this.obstacleList = new ArrayList<Obstacle>();
    }

    public Floor getFloor() {
        return floor;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public Player getPlayer() {
        return player;
    }

    public void addToPlayer(float x, float y){
        this.player.getVector2().add(x,y);
        this.outScreen();
        this.player.setRectangle(new Rectangle(player.getVector2().x,player.getVector2().y, Constante.PLAYER_RADIUS,Constante.PLAYER_RADIUS));
        this.player.collisionObstacle(x,y);
        this.floor.collisionFloor(this.getPlayer(), x , y);
    }

    public void generateObstacle(){
        Random random = new Random();
        for(int i = 0; i < (random.nextInt(10)+5); i++){
            Obstacle obstacle = new Obstacle(((float)(random.nextInt(Constante.SCREEN_WIDTH - (int)Constante.OBSTACLE_WIDTH)) + Constante.OBSTACLE_WIDTH),
                    ((float)(random.nextInt(Constante.SCREEN_HEIGHT - (int)Constante.OBSTACLE_HEIGHT)) + Constante.OBSTACLE_HEIGHT),
                    Constante.OBSTACLE_WIDTH, Constante.OBSTACLE_HEIGHT);
            this.obstacleList.add(obstacle);
        }

        this.player.setObstacleList(obstacleList);
    }

    public void updateObstacle(ShapeRenderer shapeRenderer){
        for (Obstacle obstacle: obstacleList) {
            obstacle.update(shapeRenderer);
        }
    }

    public void outScreen(){
        if(this.player.getVector2().x < 0) this.player.getVector2().x = 0;
        if(this.player.getVector2().x > Constante.SCREEN_WIDTH - Constante.PLAYER_RADIUS) this.player.getVector2().x = Constante.SCREEN_WIDTH - Constante.PLAYER_RADIUS;
        if(this.player.getVector2().y < 0) this.player.getVector2().y = 0;
        if(this.player.getVector2().y > Constante.SCREEN_HEIGHT - Constante.PLAYER_RADIUS) this.player.getVector2().y = Constante.SCREEN_HEIGHT - Constante.PLAYER_RADIUS;
    }
}
