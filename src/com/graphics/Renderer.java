package com.graphics;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.game.Game;

public class Renderer {

    private static Frame frame;
    private static Canvas canvas;

    private static int canvasWidth = 0;
    private static int canvasHeight = 0;

    private static final int GAME_WIDTH = 300;
    private static final int GAME_HEIGHT = 250;

    private static int gameWidth = 0;
    private static int gameHeight = 0;
    public static void getBestSize(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        boolean done = false;

        while(!done){
            canvasWidth += GAME_WIDTH;
            canvasHeight += GAME_HEIGHT;

            if(canvasWidth > screenSize.width || canvasHeight > screenSize.height){
                canvasWidth-= GAME_WIDTH;
                canvasHeight-= GAME_HEIGHT;
                done = true;
            }
        }

        int xDiff = screenSize.width - canvasWidth;
        int yDiff = screenSize.height - canvasHeight;
        int factor = canvasWidth / GAME_WIDTH;

        gameWidth = canvasWidth / factor + xDiff / factor;
        gameHeight = canvasHeight / factor + yDiff / factor;

       canvasWidth = gameWidth + factor;
       canvasHeight = gameHeight + factor;

    }

    private static void makeFullscreen(){
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = env.getDefaultScreenDevice();

        if(gd.isFullScreenSupported()){
            //frame.setUndecorated(true);
            gd.setFullScreenWindow(frame);
        }
    }

    public static void init(){
        getBestSize();
        frame = new Frame();
        canvas = new Canvas();

        canvas.setPreferredSize(new Dimension(canvasHeight, canvasWidth));
        frame.add(canvas);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        makeFullscreen();

        frame.addWindowListener(new WindowAdapter(){
            public  void  windowClosing(WindowEvent e){
                Game.quit();
            }
        });

        frame.setVisible(true);
        startRendering();
    }

    private static void startRendering(){

    }
}