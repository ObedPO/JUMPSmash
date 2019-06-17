
        package com.game;



import com.graphics.Renderer;

import com.org.world.World;
import org.object.Platform;
import org.object.Player;
import org.object.Sprite;


public class Game{
    public static void main (String[] args){
        Renderer.init();

        World.currentWorld = new World();
        World.currentWorld.addSprite(new Player(50,100));
        World.currentWorld.addSprite(new Platform(100,200,300,20));

    }

    public static void quit(){
        System.exit(0);
    }
}