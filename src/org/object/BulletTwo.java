package org.object;


import com.graphics.Animation;
import com.graphics.Renderer;
import com.org.world.World;

import java.awt.*;
import java.io.IOException;

public class BulletTwo extends Sprite {


    public int direction = 1; //-1 = left, 1 = right
    public float speed = 300.0f;
    public float damage = 10.0f;

    public BulletTwo(float posX, float posY, int direction) {
        super(posX, posY);
        this.direction = direction;
        width = 10;
        height = 10;
        isSolid = false;

        Animation anim = new Animation();
        try {
            anim.images.add(Renderer.loadImage("/com/resources/images/rasenShuriken.png"));
            anim.images.add(Renderer.loadImage("/com/resources/images/rasenShuriken.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }

        Animation anim2 = new Animation();
        try {
            anim2.images.add(Renderer.loadImage("/com/resources/images/rasenShuriken.png"));
            anim2.images.add(Renderer.loadImage("/com/resources/images/rasenShuriken.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        animations = new Animation[]{
                anim,anim2
        };
    }

    public void update(float deltaTime){
        float moveX = 0;

        moveX += speed*direction;

        posX += moveX * deltaTime;

        if(direction > 0){
            currentAnimation = 0;
        }
        if(direction < 0){
            currentAnimation = 1;
        }

        Sprite[] colliders = getColliders(posX,posY);

        if(colliders.length > 0){
            for(Sprite sprite : colliders){
                if(sprite instanceof Player){
                    Player player = (Player) sprite;
                    player.takeDamage(damage);
                    World.currentWorld.removeSprite(this);

                }
            }

        }




    }



}
