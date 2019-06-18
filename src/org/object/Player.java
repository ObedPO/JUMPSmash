package org.object;

import com.graphics.Animation;
import com.graphics.Renderer;
import com.org.world.World;
import org.input.Input;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Player extends Mob {

    private float velocityY =0;
    private float gravity = 300.0f;
    private float jumpHeight = 50;

    private boolean left= false;
    private boolean right = true;

    private int health = 100;

    private int direction = 1;

    Animation anim = new Animation();

    public Player(float posX, float posY) {
        super(posX, posY);

        width = 47;
        height = 49;
        runSpeed = 100;

        try {
            anim.images.add(Renderer.loadImage("/com/resources/images/goku.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        animations = new Animation[]{
                anim
        };

    }

    public void update(float deltaTime){



        //This is the default Sprite

//        try {
//            anim.images.add(Renderer.loadImage("/com/resources/images/goku.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        animations = new Animation[]{
//                anim
//        };

    float moveX = 0;

        if (Input.getKey(KeyEvent.VK_A)) {
            moveX -= runSpeed;
            direction = -1;
            left = true;
            right = false;

            try {
                anim.images.set(anim.currentImage, Renderer.loadImage("/com/resources/images/gokuLeft.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Input.getKey(KeyEvent.VK_D)) {
            moveX += runSpeed;
            direction = 1;
            left = false;
            right = true;

            try {
                anim.images.set(anim.currentImage, Renderer.loadImage("/com/resources/images/goku.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(moveX > 0){
//
//            if(direction == 1) {
//                try {
//                    anim.images.add(Renderer.loadImage("/com/resources/images/goku.png"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
        }

//        if(moveX < 0){
//
//            if(direction == -1) {
//                try {
//                    anim.images.add(Renderer.loadImage("/com/resources/images/gokuLeft.png"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }

         velocityY += gravity * deltaTime;

        if(doesCollide(posX,posY + 1)) {
            if (Input.getKeyDown(KeyEvent.VK_W)) {
                velocityY = (float) -Math.sqrt(2 * jumpHeight * gravity);
            }
        }


        if(doesCollide(posX + moveX * deltaTime, posY)){
            moveX -= moveX;

        }

        if(doesCollide(posX, posY + velocityY * deltaTime)){
            velocityY -= velocityY;
        }

        if(Input.getKeyDown(KeyEvent.VK_SPACE)){

            try {
                if(left)
                    anim.images.set(anim.currentImage,Renderer.loadImage("/com/resources/images/gokuShootLeft.png"));
                else
                    anim.images.set(anim.currentImage,Renderer.loadImage("/com/resources/images/gokuShoot.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }


            Bullet bullet = new Bullet(posX,posY,direction);
            World.currentWorld.addSprite(bullet);

        }

    posX+= moveX * deltaTime;
    posY+=  velocityY * deltaTime;

    Renderer.camX = 100;
    Renderer.camY = 100;

    }



    public void takeDamage(float damage){
        health-= damage;
        if(health<= 0){
            World.currentWorld.removeSprite(this);
        }
    }





}
