package com.org.test;

import com.game.Game;
import com.graphics.Renderer;
import org.input.Input;
import org.object.Sprite;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class TestSprite extends Sprite {

    public TestSprite(float posX, float posY){
        super(posX, posY, "");

        try {
            image = Renderer.loadImage("/com/resources/images/goku.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update(float deltaTime){
        if(Input.getKey(KeyEvent.VK_W)){
            posY -= 100*deltaTime;
        }

        if(Input.getKey(KeyEvent.VK_S)){
            posY += 100*deltaTime;
        }


    }


}
