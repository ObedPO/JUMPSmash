package com.game;



import com.graphics.Renderer;

import com.org.world.World;
import org.input.Input;
import org.object.Platform;
import org.object.Player;
import org.object.PlayerTwo;
import org.object.Sprite;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;

import org.Music.WavPlayer;


        public class Game{
    public static void main (String[] args){
        Renderer.init();

        World.currentWorld = new World();
        World.currentWorld.addSprite(new Player(-30,100));
        //World.currentWorld.addSprite(new Player(150,100,"/com/resources/images/naruto.png",2));
        World.currentWorld.addSprite(new Platform(50,200,300,20));
        World.currentWorld.addSprite(new PlayerTwo(150,100));
        WavPlayer song = new WavPlayer("com/resources/images/NarutoMusic.wav");




    }

    public static void quit(){
        System.exit(0);
    }


        }