package com.pointGame.character;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pointGame.config.Constante;
import com.pointGame.gameObject.Obstacle;

import java.util.List;

public class Player extends Character {

    public final Color playerColor = Constante.PLAYER_COLOR;
    public Rectangle rectangle;
    public List<Obstacle> obstacleList;
    public float vertical_speed = 0;

    public Player(int vie, int force) {
        super(vie, force);
        this.rectangle = new Rectangle(0,0, Constante.PLAYER_RADIUS,Constante.PLAYER_RADIUS);
    }

    public Player(int vie, int force, int x, int y) {
        super(vie, force, x, y);
        this.rectangle = new Rectangle(x,y, Constante.PLAYER_RADIUS, Constante.PLAYER_RADIUS);
    }

    public Player(int vie, int force, Vector2 vector2) {
        super(vie, force, vector2);
        this.rectangle = new Rectangle(vector2.x,vector2.y, Constante.PLAYER_RADIUS, Constante.PLAYER_RADIUS);
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setObstacleList(List<Obstacle> obstacleList) {
        this.obstacleList = obstacleList;
    }

    public void update(ShapeRenderer shapeRenderer){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.getPlayerColor());
        shapeRenderer.rect(this.getRectangle().x, this.getRectangle().y, this.getRectangle().width, this.getRectangle().height);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(this.getRectangle().x, this.getRectangle().y, this.getRectangle().width, this.getRectangle().height);
        shapeRenderer.end();
    }

    public void collisionObstacle(float x, float y){
        for (Obstacle obstacle: obstacleList) {
            if(this.getRectangle().overlaps(obstacle.getRectangle())) {
                this.getVector2().add(-x,-y);
                this.setRectangle(new Rectangle(getVector2().x,getVector2().y, Constante.PLAYER_RADIUS,Constante.PLAYER_RADIUS));
            }
        }
    }

    @Override
    public String toString() {
        return "Le joueur à [ "+super.toString()+"].";
    }

    public void fall(){

    }
}
