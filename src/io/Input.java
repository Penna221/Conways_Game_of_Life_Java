package io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.Grid;
import states.GameState;
import states.MenuState;
import states.State;


public class Input implements MouseMotionListener, MouseListener, KeyListener{

    public static int mouseX, mouseY;
    public static boolean leftPress, rightPress;
    @Override
    public void mouseClicked(MouseEvent e) {
        if(State.getState() == State.menuState){
            MenuState.click();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        
        mouseX = e.getX();
        mouseY = e.getY();
        if(e.getButton() == MouseEvent.BUTTON1){
            leftPress = true;
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            rightPress = true;
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
         mouseX = e.getX();
        mouseY = e.getY();
        if(e.getButton() == MouseEvent.BUTTON1){
            leftPress = false;
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            rightPress = false;
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
    //KEY INPUT
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_F5){
            System.out.println("START");
            Grid.run();
        }else if(key == KeyEvent.VK_F6){
            System.out.println("STOP");
            Grid.stop();
        }else if(key == KeyEvent.VK_F7){
            System.out.println("STEP");
            Grid.step();
        }else if(key == KeyEvent.VK_ESCAPE){
            if(State.getState()==State.gameState){
                State.setState(State.menuState);
            }
        }else if(key == KeyEvent.VK_F10){
            if(State.getState()==State.gameState){
                GameState.grid.generateEmptyGrid();
            }
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {}
    
}
