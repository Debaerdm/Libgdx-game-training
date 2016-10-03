package com.pointGame.character;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.pointGame.config.Constante;
import com.pointGame.gameObject.Obstacle;

public class Player extends Character {

    public final Color playerColor = Constante.PLAYER_COLOR;
    public Circle circle;

    public Player(int vie, int force) {
        super(vie, force);
        this.circle = new Circle(0,0, Constante.PLAYER_RADIUS);
    }

    public Player(int vie, int force, int x, int y) {
        super(vie, force, x, y);
        this.circle = new Circle(x,y, Constante.PLAYER_RADIUS);
    }

    public Player(int vie, int force, Vector2 vector2) {
        super(vie, force, vector2);
        this.circle = new Circle(vector2.x,vector2.y, Constante.PLAYER_RADIUS);
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
        notifyObservers();
    }

    public void add(float x, float y){
        this.getVector2().add(x,y);
        this.setCircle(new Circle(vector2.x,vector2.y, Constante.PLAYER_RADIUS));
    }

    public void update(ShapeRenderer shapeRenderer){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.getPlayerColor());
        shapeRenderer.circle(this.getCircle().x, this.getCircle().y, this.getCircle().radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(this.getCircle().x, this.getCircle().y, this.getCircle().radius);
        shapeRenderer.end();
    }

    public boolean isCollide(Obstacle obstacle){

        double x = Math.abs((double)(this.circle.x - obstacle.getRectangle().getX()));
        double y = Math.abs((double)(this.circle.y - obstacle.getRectangle().getY()));

        if (x > (obstacle.getRectangle().getWidth()/2 + circle.radius)) {return false;}
        if (y > (obstacle.getRectangle().getHeight()/2 + circle.radius)) {return false;}
        if (x <= (obstacle.getRectangle().getWidth()/2)) {return true;}
        if (y <= (obstacle.getRectangle().getHeight()/2)) {return true;}

        double cornerDistance = (Math.pow((x - obstacle.getRectangle().getWidth()/2),2) + Math.pow((y - obstacle.getRectangle().getHeight()/2),2));

        return (cornerDistance <= (Math.pow(circle.radius,2)));
    }

    /*bool CollisionCercleAABB(Cercle C1,AABB box1)
{
   AABB boxCercle = GetBoxAutourCercle(C1);  // retourner la bounding box de l'image porteuse, ou calculer la bounding box.
   if (CollisionAABBvsAABB(box1,boxCercle)==0)
      return false;   // premier test
   if (CollisionPointCercle(box1.x,box1.y,C1)==1
    || CollisionPointCercle(box1.x,box1.y+box1.h,C1)==1
    || CollisionPointCercle(box1.x+box1.w,box1.y,C1)==1
    || CollisionPointCercle(box1.x+box1.w,box1.y+box1.h,C1)==1)
      return true;   // deuxieme test
   if (CollisionPointAABB(C1.x,C1.y,box1)==1)
      return true;   // troisieme test
   int projvertical = ProjectionSurSegment(C1.x,C1.y,box1.x,box1.y,box1.x,box1.y+box1.h);
   int projhorizontal = ProjectionSurSegment(C1.x,C1.y,box1.x,box1.y,box1.x+box1.w,box1.y);
   if (projvertical==1 || projhorizontal==1)
      return true;   // cas E
   return false;  // cas B
}

int ProjectionSurSegment(int Cx,int Cy,int Ax,int Ay,int Bx,int By)
{
   int ACx = Cx-Ax;
   int ACy = Cy-Ay;
   int ABx = Bx-Ax;
   int ABy = By-Ay;
   int BCx = Cx-Bx;
   int BCy = Cy-By;
   int s1 = (ACx*ABx) + (ACy*ABy);
   int s2 = (BCx*ABx) + (BCy*ABy);
   if (s1*s2>0)
     return 0;
   return 1;
}
*/

    @Override
    public String toString() {
        return "Le joueur à [ "+super.toString()+"].";
    }
}
