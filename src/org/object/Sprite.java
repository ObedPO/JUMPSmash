package org.object;

import com.graphics.Renderer;
import org.input.Input;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {

    public float posX = 0;
    public float posY = 0;

    public BufferedImage image = null;

    public Sprite(float posX, float posY, String pic){
        this.posX = posX;
        this.posY = posY;

        try {
            image = Renderer.loadImage(pic);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update(float deltaTime){
        handleKeys();
        handleGravity(deltaTime);


    }


    public void handleKeys(){
        int speed = 10;
        if(Input.getKey(KeyEvent.VK_W)){
            posY -= speed;
        }

        if(Input.getKey(KeyEvent.VK_S)){
            posY += speed;
        }
    }

    public void handleGravity(float deltaTime){
        //position = velocityI* time + 1/2* gravityA * time squared

            posY += (0.5) * (9.81* (Math.pow(deltaTime,2)));

    }

    public void render(Graphics g){
        if(image == null){
            return;
        }

        int realX = (int) posX - image.getWidth()/2;
        int realY = (int) posY - image.getHeight()/2;


        g.drawImage(image, realX, realY, image.getWidth(), image.getHeight(), null);
    }
}
