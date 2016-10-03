package com.pointGame.character;

import com.badlogic.gdx.math.Vector2;

import java.util.Observable;

public abstract class Character extends Observable {

    protected int vie;
    protected int force;
    protected Vector2 vector2;

    public Character(int vie, int force){
        this.vie = vie;
        this.force = force;
        this.vector2 = new Vector2(0,0);
    }

    public Character(int vie, int force, float x, float y){
        this.vie = vie;
        this.force = force;
        this.vector2 = new Vector2(x,y);
    }

    public Character(int vie, int force, Vector2 vector2){
        this.vie = vie;
        this.force = force;
        this.vector2 = vector2;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
        notifyObservers();
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
        notifyObservers();
    }

    public Vector2 getVector2() {
        return vector2;
    }

    public void setVector2(Vector2 vector2) {
        this.vector2 = vector2;
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Vie : "+this.vie+", Force : "+this.force+" et Vecteur : ("+this.vector2.x+","+this.vector2.y+")";
    }
}
