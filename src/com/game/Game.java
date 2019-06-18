
        package com.game;



import com.graphics.Renderer;

import com.org.world.World;
import org.input.Input;
import org.object.Platform;
import org.object.Player;
import org.object.PlayerTwo;
import org.object.Sprite;

import java.awt.event.KeyEvent;


        public class Game{
    public static void main (String[] args){
        Renderer.init();

        World.currentWorld = new World();
        World.currentWorld.addSprite(new Player(-30,100));
        World.currentWorld.addSprite(new Platform(50,200,300,20));
        World.currentWorld.addSprite(new PlayerTwo(150,100));


    }

    public static void quit(){
        System.exit(0);
    }
}