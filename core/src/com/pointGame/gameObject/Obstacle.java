package com.pointGame.gameObject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pointGame.config.Constante;

public class Obstacle {

    private Rectangle rectangle;
    private Color ObstacleColor = Constante.OBSTACLE_COLOR;

    public Obstacle(float x,float y,float width, float height){
        this.rectangle = new Rectangle(x,y,width,height);
    }

    public Obstacle(Rectangle rectangle){
        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void update(ShapeRenderer shapeRenderer){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.ObstacleColor);
        shapeRenderer.rect(this.rectangle.getX(), this.rectangle.getY(), this.rectangle.getWidth(),this.rectangle.getHeight());
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(this.rectangle.getX(), this.rectangle.getY(), this.rectangle.getWidth(),this.rectangle.getHeight());
        shapeRenderer.end();
    }

    public void setObstacleColor(Color obstacleColor) {
        ObstacleColor = obstacleColor;
    }
}
