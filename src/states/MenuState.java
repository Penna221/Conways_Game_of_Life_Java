package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import io.Input;

public class MenuState extends State{

    private static boolean canClick = false;
    private Rectangle bounds = new Rectangle();
    public MenuState(){
        bounds = new Rectangle(150,10,100,40);
    }
    
    @Override
    public void update() {}
    public static void click(){
        if(canClick){
            State.setState(State.gameState);
        }
    }
    @Override
    public void render(Graphics g) {
        
        g.setColor(new Color(230,190,0));
        g.setFont(new Font("Serif",Font.BOLD,32));
        g.drawString("Controls", 10, 40);
        
        g.setColor(new Color(0,255,255));
        g.setFont(new Font("Serif",Font.BOLD,22));
        g.drawString("F5 - RUN", 10, 100);
        g.drawString("F6 - STOP", 10, 130);
        g.drawString("F7 - STEP", 10, 160);
        g.drawString("F10 - CLEAR GRID", 10, 190);
        g.drawString("ESC - Return to Menu",10,220);
        g.setColor(new Color(100,0,255));
        g.drawString("Left click - make cell alive", 10, 250);
        g.drawString("Right click - make cell dead", 10, 280);
        
        //update mouse stuff
        int x = Input.mouseX;
        int y = Input.mouseY;
        Point p = new Point(x,y);
        if(bounds.contains(p)){
            canClick = true;
        }else{
            canClick = false;
        }

        //Draw button
        g.setColor(Color.blue);
        g.drawRect(bounds.x,bounds.y,bounds.width,bounds.height);
        if(canClick){
            g.setColor(Color.white);
            g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);
        }
        g.setColor(Color.blue);
        g.drawString("Let's Go!", bounds.x + 4, bounds.y + bounds.height/3*2);
        
    }
    
    
}
