package com.pointGame.game;

import com.badlogic.gdx.Game;
import com.pointGame.screens.GameScreen;

public class PlayerGame extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
