package com.game;



import com.graphics.Renderer;
import com.org.test.TestSprite;
import com.org.world.World;
import org.object.Sprite;


public class Game{
    public static void main (String[] args){
        Renderer.init();

        World.currentWorld = new World();
        World.currentWorld.sprites.add(new Sprite(300,300, "/com/resources/images/goku.png"));
        
    }

    public static void quit(){
        System.exit(0);
    }
}