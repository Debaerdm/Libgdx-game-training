package com.pointGame.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Debaerdm on 11/10/2016.
 */
public class World {

    public List<Block> blocks = new ArrayList<Block>();

    public Player player;

    public List<Block> getBlocks() {
        return blocks;
    }

    public Player getPlayer() {
        return player;
    }

    public World(){
        this.createWorld();
    }

    public void createWorld(){
        this.player = new Player(new Vector2(7, 2));

        for (int col = 0; col < 10; col++){
            blocks.add(new Block(new Vector2(col, 0)));
            blocks.add(new Block(new Vector2(col, 6)));
            if (col > 2) {
                blocks.add(new Block(new Vector2(col, 1)));
            }
        }

        blocks.add(new Block(new Vector2(9, 2)));
        blocks.add(new Block(new Vector2(9, 3)));
        blocks.add(new Block(new Vector2(9, 4)));
        blocks.add(new Block(new Vector2(9, 5)));

        blocks.add(new Block(new Vector2(6, 3)));
        blocks.add(new Block(new Vector2(6, 4)));
        blocks.add(new Block(new Vector2(6, 5)));
    }
}
