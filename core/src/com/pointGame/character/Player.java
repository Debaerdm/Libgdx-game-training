package com.pointGame.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pointGame.World.Floor;
import com.pointGame.config.Constante;
import com.pointGame.gameObject.Obstacle;

import java.util.List;

public class Player extends Character {

    public final Color playerColor = Constante.PLAYER_COLOR;
    public Rectangle rectangle;
    public List<Obstacle> obstacleList;
    public float velocityY;
    public final float gravity = 9.8f;
    private Floor floor;

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

    public void collisionFloor(float x, float y){
        for (Obstacle obstacle: floor.getObstacles()) {
            if(this.getRectangle().overlaps(obstacle.getRectangle())) {
                this.getVector2().add(-x,-y);
                this.setRectangle(new Rectangle(this.getVector2().x,this.getVector2().y, Constante.PLAYER_RADIUS,Constante.PLAYER_RADIUS));
            }
        }
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Le joueur à [ "+super.toString()+"].";
    }

    public void fall(){

    }

    private boolean leftMove;
    private boolean rightMove;
    private boolean jumpMove;
    private boolean ground = false;

    public void addToPlayer(float x, float y){
        this.getVector2().add(x,y);
        this.setRectangle(new Rectangle(this.getVector2().x,this.getVector2().y, Constante.PLAYER_RADIUS,Constante.PLAYER_RADIUS));
        this.collisionObstacle(x,y);
        this.collisionFloor(x,y);
    }

    public void updateMotion(){
        if (leftMove) {
            this.addToPlayer(-200 * Gdx.graphics.getDeltaTime(), 0);
        }
        if (rightMove){
            this.addToPlayer(200 * Gdx.graphics.getDeltaTime(), 0);
        }
        if (jumpMove && ground) {
            velocityY += gravity * Gdx.graphics.getDeltaTime();

            if (this.getVector2().y < Constante.OBSTACLE_HEIGHT) {
                this.getVector2().y = Constante.OBSTACLE_HEIGHT;
                ground = false;
                velocityY = 0;
            }

            if (this.getVector2().y > Constante.SCREEN_HEIGHT) {
                this.getVector2().y = Constante.SCREEN_HEIGHT - Constante.PLAYER_RADIUS;
                ground = false;
                velocityY = 0;
            }

            if (this.getVector2().y >= (Constante.OBSTACLE_HEIGHT*3)+Constante.PLAYER_RADIUS) {
                ground = false;
            }

            this.addToPlayer(0, velocityY);
        } else {
            if (this.getVector2().y > Constante.OBSTACLE_HEIGHT) {
                this.addToPlayer(0, -velocityY);
                if (this.getVector2().y == Constante.OBSTACLE_HEIGHT){
                    ground = true;
                }
            }
        }
    }

    public void setLeftMove(boolean leftMove) {
        if (rightMove && leftMove) rightMove = false;
        this.leftMove = leftMove;
    }

    public void setRightMove(boolean rightMove) {
        if (leftMove && rightMove) leftMove = false;
        this.rightMove = rightMove;
    }

    public void setJumpMove(boolean jumpMove) {
        if (jumpMove) {
            velocityY = 6.0f;
            ground = true;
        } else {
            if(velocityY > 3.0f)
                velocityY = 3.0f;
        }
        this.jumpMove = jumpMove;
    }
}
