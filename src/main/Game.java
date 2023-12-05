package main;

import window.Window;

public class Game implements Runnable{

    private Thread thread;
    private boolean running;
    public static Window w;
    public static int ups;
    private static double timeForUpdate;
    
    public Game(){

    }
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
        System.out.println("Update");
    }
    
    public void render(){
        System.out.println("DRAWING.....");
    }
}
