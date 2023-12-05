package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import io.Input;
import states.State;
import window.Window;
public class Game implements Runnable{

    private Thread thread;
    private boolean running;
    public static Window w;
    public static int ups;
    private static double timeForUpdate;
    
    private BufferStrategy bs;
    private Graphics g;
    
    //Created from Launcher
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
        Input i = new Input();
        w.getCanvas().addMouseListener(i);
        w.getCanvas().addMouseMotionListener(i);
        w.getCanvas().addKeyListener(i);
        State.createStates();
        State.setState(State.menuState);
    }
    
    //Game loop
    @Override
    public void run() {
        init();
        //specify an (updates per seconds) rate that can be changed.
        ups = 9;
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
    //Not used?
    public static void updateUPS(int newUPS){
        ups = newUPS;
        if(ups == 0){
            ups = 1;
        }
        timeForUpdate = 1000/ups;
    }
    //Update everything
    public void update(){
        if(State.getState()!=null){
            State.getState().update();
        }
    }
    //Draw everything on canvas
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
        if(State.getState()!=null){
            State.getState().render(g);
        }

        g.dispose();
        bs.show();
    }
}
