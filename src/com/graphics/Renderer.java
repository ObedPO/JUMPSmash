package com.graphics;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;

import com.game.Game;
import com.org.world.World;
import org.input.Input;

import javax.imageio.ImageIO;

public class Renderer {

    private static Frame frame;
    private static Canvas canvas;

    private static int canvasWidth = 0;
    private static int canvasHeight = 0;

    private static final int GAME_WIDTH = 400;
    private static final int GAME_HEIGHT = 300;

    public static int gameWidth = 0;
    public static int gameHeight = 0;

    private static int targetFPS = 60;
    private static int targetTime = 1000000000 / targetFPS;

    public static float camX = 0;
    public static float camY = 0;

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

        gameWidth = canvasWidth/factor + xDiff/factor;
        gameHeight = canvasHeight/factor + yDiff/factor;
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

        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                Game.quit();
            }
        });

        frame.setVisible(true);

        canvas.addKeyListener(new Input());

        startRendering();
    }

    private static void startRendering(){
        Thread thread = new Thread(){
            public void run(){

                GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
                VolatileImage vImage = gc.createCompatibleVolatileImage(gameWidth,gameHeight);

                while(true){
                    long startTime = System.nanoTime();

                    if(vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE){
                        vImage = gc.createCompatibleVolatileImage(gameWidth,gameHeight);
                    }
                    Graphics g = vImage.getGraphics();

                    g.setColor(Color.black);
                    g.fillRect(0,0,gameWidth,gameHeight);

                    //UPDATE STUFF
                    World.update();
                    Input.finishedInput();

                    //RENDER STUFF
                    World.render(g);


                    g.dispose();

                    g = canvas.getGraphics();
                    g.drawImage(vImage,0,0,canvasWidth,canvasHeight,null);

                    g.dispose();

                    long totalTime = System.nanoTime() - startTime;

                    if(totalTime < targetTime ){
                        try {
                            Thread.sleep((targetTime - totalTime) / 1000000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        };
        thread.setName("Rendering Thread");
        thread.start();
    }

    public static BufferedImage loadImage (String path)throws IOException {
        BufferedImage rawImage = ImageIO.read(Renderer.class.getResource(path));
        BufferedImage finalImage = canvas.getGraphicsConfiguration().createCompatibleImage(rawImage.getWidth(), rawImage.getHeight(),rawImage.getTransparency());

        finalImage.getGraphics().drawImage(rawImage, 0, 0, rawImage.getWidth(), rawImage.getHeight(),null);

        return finalImage;
    }

}