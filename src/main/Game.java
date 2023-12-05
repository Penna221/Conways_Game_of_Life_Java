package main;

import window.Window;

public class Game implements Runnable{

    private Thread thread;
    private boolean running;
    public static Window w;
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

    
    private void init(){
        w = new Window("Conway's Game of Life", 600, 400);
    }
    @Override
    public void run() {
        init();
    }
    
}
