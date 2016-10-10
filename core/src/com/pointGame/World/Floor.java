package com.pointGame.World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pointGame.Exception.NoIndexObstacleException;
import com.pointGame.character.Player;
import com.pointGame.config.Constante;
import com.pointGame.gameObject.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private List<Obstacle> obstacles;
    private int nb_Obstacles;

    public Floor(){
        this(20);
    }

    public Floor(int nb){
        this.nb_Obstacles = nb;
        this.obstacles = new ArrayList<Obstacle>();
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public void setAllObstacleColor(Color color){
        for (Obstacle obstacle: obstacles) {
            obstacle.setObstacleColor(color);
        }
    }

    public void setObstacleColor(int index, Color color) throws NoIndexObstacleException {
        if (obstacles.contains(this.obstacles.get(index))) {
            this.obstacles.get(index).setObstacleColor(color);
        } else {
            throw  new NoIndexObstacleException("Index not exist!");
        }
    }

    public void generateFloor(){
        float widthOfRectangle = Constante.SCREEN_WIDTH / nb_Obstacles;
        Obstacle obstacle = new Obstacle(0,0, widthOfRectangle, Constante.OBSTACLE_HEIGHT);
        this.obstacles.add(obstacle);
        for (float i = 1; i < nb_Obstacles; i++) {
            Obstacle prev = this.obstacles.get((int)i - 1);
            float x = prev.getRectangle().x + widthOfRectangle;
            Obstacle next = new Obstacle(x, 0, widthOfRectangle, Constante.OBSTACLE_HEIGHT);
            this.obstacles.add(next);
        }
    }

    public void update(ShapeRenderer shapeRenderer){
        for (Obstacle obstacle: obstacles) {
            obstacle.update(shapeRenderer);
        }
    }

}
