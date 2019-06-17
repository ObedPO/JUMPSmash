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

    private int direction = 1;

    public Player(float posX, float posY) {
        super(posX, posY);

        width = 47;
        height = 49;
        runSpeed = 100;

        Animation anim = new Animation();
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
    float moveX = 0;

    if(Input.getKey(KeyEvent.VK_LEFT)){
        moveX -= runSpeed;
    }
        if(Input.getKey(KeyEvent.VK_RIGHT)){
            moveX += runSpeed;
        }

        if(moveX > 0){
            direction = 1;
        }

        if(moveX < 0){
            direction = -1;
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

        if(Input.getKeyDown(KeyEvent.VK_SPACE)){
            Bullet bullet = new Bullet(posX,posY,direction);
            World.currentWorld.addSprite(bullet);

        }

    posX+= moveX * deltaTime;
    posY+=  velocityY * deltaTime;

    Renderer.camX = posX;
    Renderer.camY = 100;

    }

    private boolean doesCollide(float x, float y){
        float myLeft = x - width / 2;
        float myRight = x + width / 2;
        float myUp = y - height / 2;
        float myDown = y + height / 2;

        for(Sprite sprite : World.currentWorld.sprites){
            if(sprite == this || !sprite.isSolid){
                continue;
            }


            float otherLeft = sprite.posX - sprite.width / 2;
            float otherRight = sprite.posX + sprite.width / 2;
            float otherUp = sprite.posY - sprite.height / 2;
            float otherDown = sprite.posY + sprite.height / 2;

            if(myLeft< otherRight && myRight> otherLeft && myDown > otherUp && myUp < otherDown ){
                return true;
            }

        }
        return false;
    }



}
