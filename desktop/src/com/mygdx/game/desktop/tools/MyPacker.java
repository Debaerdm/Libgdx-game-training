package com.mygdx.game.desktop.tools;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Created by Debaerdm on 11/10/2016.
 */
public class MyPacker {
    public static void main (String[] args) throws Exception {
        TexturePacker.process("core/assets/images","core/assets/images/textures", "textures");
    }
}
