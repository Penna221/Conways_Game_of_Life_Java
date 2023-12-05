package game;

import window.Window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
public class Game implements Runnable{

    private Thread thread;
    private boolean running;
    public static Window w;
    public static int ups;
    private static double timeForUpdate;
    
    private BufferStrategy bs;
    private Graphics g;
    
    private Grid grid;
    private int tileSize;
    public Game(){}
    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this,"Game");
        thread.start();
    }

    //Initialize everything
    private void init(){
        w = new Window("Conway's Game of Life", 600, 400);
        tileSize = 16;
        int gridWidth = w.getWidth()/tileSize-1;
        int gridHeight = w.getHeight()/tileSize-1;
        grid = new Grid(gridWidth,gridHeight,tileSize);
    }
    
    @Override
    public void run() {
        init();
        //specify an (updates per seconds) rate that can be changed.
        ups = 2;
        timeForUpdate = 1000/ups;
        double lastTime = System.currentTimeMillis();
        double now = System.currentTimeMillis();
        double elapsedTime = 0;

        while(running){
            now = System.currentTimeMillis();
            elapsedTime += now - lastTime;
            if(elapsedTime >=timeForUpdate){
                //UPDATE ONLY at the rate of ups
                update();
                elapsedTime-=timeForUpdate;
            }
            lastTime = now;
            //DRAW AS MUCH AS CAN
            render();
        }

    }
    public static void updateUPS(int newUPS){
        ups = newUPS;
        if(ups == 0){
            ups = 1;
        }
        timeForUpdate = 1000/ups;
    }
    public void update(){
        grid.update();
    }
    
    public void render(){
        bs = w.getCanvas().getBufferStrategy();
        if(bs == null){
            w.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, w.getWidth(), w.getHeight());
        g.setColor(Color.black);
        g.fillRect(0, 0, w.getWidth(), w.getHeight());

        //Draw everything
        grid.render(g);


        g.dispose();
        bs.show();
    }
}
