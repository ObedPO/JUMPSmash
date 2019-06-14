package org.object;

import com.game.Game;
import com.graphics.Renderer;
import org.input.Input;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {

    public float posX = 0;
    public float posY = 0;
    private double vi =0;
    private long lastTime= System.nanoTime();
    private double g =0;
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
        handleGravity();


    }


    public void handleKeys(){
        double speed = 0.25;


        if(Input.getKey(KeyEvent.VK_S)){
            posY += speed;
        }

        if(Input.getKey(KeyEvent.VK_A)){
            posX -= speed;
        }

        if(Input.getKey(KeyEvent.VK_D)){
            posX += speed;
        }

        if(Input.getKey((KeyEvent.VK_Q))){
            Game.quit();
        }

    }

    public void handleGravity(){
        //position = velocityI* time + 1/2* gravityA * time squared
        //long lastTime= 0;
        float t = (System.nanoTime() -  lastTime) / 1000000000.0f;

        if(Input.getKey(KeyEvent.VK_W)){
            vi = -200;
            g= 16.0;
            lastTime = System.nanoTime();
        } else if (Input.getKey(KeyEvent.VK_S)){
            vi = 0;
            g=0.0;
        }


        int var = 2;
        System.out.println("PosY: "+  posY + "\tt: " + t + "\tg: " + g + "\tvar: "+var);

        posY = (float) (300.0 + vi*t +  (g * (Math.pow(t*var,2))));
        if(posY>=300){
            posY = 300;
        }



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
