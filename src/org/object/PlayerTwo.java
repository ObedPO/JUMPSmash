package org.object;

import com.graphics.Animation;
import com.graphics.Renderer;
import com.org.world.World;
import org.input.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.Key;

public class PlayerTwo extends Mob {

    private float velocityY =0;
    private float gravity = 300.0f;
    private float jumpHeight = 50;
    private int health = 100;

    private boolean right = false;
    private boolean left = true;

    private int direction = -1; //1 = right, -1 = left

    Animation anim = new Animation();

    public PlayerTwo(float posX, float posY) {
        super(posX, posY);

        width = 47;
        height = 49;
        runSpeed = 100;



        try {
            anim.images.add(Renderer.loadImage("/com/resources/images/naruto.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        animations = new Animation[]{
                anim
        };

    }


    public void update(float deltaTime){
        float moveX = 0;

        if (Input.getKey(KeyEvent.VK_LEFT)) {
            moveX -= runSpeed;
            direction = -1;
            left = true;
            right = false;

            try {
                anim.images.set(anim.currentImage, Renderer.loadImage("/com/resources/images/naruto.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Input.getKey(KeyEvent.VK_RIGHT)) {
            moveX += runSpeed;
            direction = 1;
            right = true;
            left = false;

            try {
                anim.images.set(anim.currentImage, Renderer.loadImage("/com/resources/images/narutoRight.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        Timer time = new Timer(5,Inpu.);
        if(Input.getKeyDown(KeyEvent.VK_SLASH)) {
//            //long start = System.currentTimeMillis();
//            //long timePassed = System.currentTimeMillis() - start;
//            //long seconds = timePassed/1000;
                /*try {
                    if (left)
                        anim.images.set(anim.currentImage, Renderer.loadImage("/com/resources/images/narutoCharge.png"));
                    else
                        anim.images.set(anim.currentImage, Renderer.loadImage("/com/resources/images/narutoChargeRight.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                try {
                    if(left)
                        anim.images.set(anim.currentImage, Renderer.loadImage("/com/resources/images/narutoRasen.png"));
                    else
                        anim.images.set(anim.currentImage, Renderer.loadImage("/com/resources/images/narutoRasenRight.png"));
                } catch (IOException e){
                    e.printStackTrace();
                }

            BulletTwo bulletTwo = new BulletTwo(posX,posY,direction);
            World.currentWorld.addSprite(bulletTwo);
       }
////        try {
////            if(left)
////                anim.images.set(anim.currentImage, Renderer.loadImage("/com/resources/images/narutoRasen.png"));
////            else
////                anim.images.set(anim.currentImage, Renderer.loadImage("/com/resources/images/narutoRasenRight.png"));
////        } catch (IOException e){
////            e.printStackTrace();
////        }
//                //long start2 = System.currentTimeMillis();
//                //long timePassed2 = System.currentTimeMillis() - start;
//                //long seconds2 = timePassed/1000;
//                /*if(seconds == 3){
//                    try {
//                        if(left)
//                            anim.images.set(anim.currentImage, Renderer.loadImage("/com/resources/images/naruto.png"));
//                        else
//                            anim.images.set(anim.currentImage, Renderer.loadImage("/com/resources/images/narutoRight.png"));
//                    } catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }*/

        if(moveX > 0){
            //direction = 1;
        }

        if(moveX < 0){
            //direction = -1;
        }

        velocityY += gravity * deltaTime;

        if(doesCollide(posX,posY + 1)) {
            if (Input.getKeyDown(KeyEvent.VK_UP)) {
                velocityY = (float) -Math.sqrt(2 * jumpHeight * gravity);
            }
        }


        if(doesCollide(posX + moveX * deltaTime, posY)){
            moveX -= moveX;

        }

        if(doesCollide(posX, posY + velocityY * deltaTime)){
            velocityY -= velocityY;
        }


        posX+= moveX * deltaTime;
        posY+=  velocityY * deltaTime;


        //Renderer.camX = posX;
        //Renderer.camY = 100;

    }

    public void takeDamage(float damage){
        health-= damage;
        if(health<= 0){
            World.currentWorld.removeSprite(this);
        }
    }


}
