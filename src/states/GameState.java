package states;

import java.awt.Graphics;

import game.Game;
import game.Grid;

public class GameState extends State{

    public static Grid grid;
    public GameState(){
        int tileSize = 10;
        Grid.tileSize = tileSize;
        int gridWidth = (Game.w.getWidth()/tileSize)-1;
        int gridHeight = (Game.w.getHeight()/tileSize)-1;
        grid = new Grid(gridWidth,gridHeight);
    }
    @Override
    public void update() {
        grid.update();
    }

    @Override
    public void render(Graphics g) {
        grid.render(g);
    }
    
}
