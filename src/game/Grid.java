package game;
import java.awt.Color;
import java.awt.Graphics;
public class Grid {
    private int width, height;
    private int tileSize = 16;
    private boolean[][] grid;
    public Grid(int width, int height,int tileSize){
        this.width = width;
        this.height = height;
        this.tileSize = tileSize;
        generateEmptyGrid();
        
    }
    private void generateEmptyGrid(){
        grid = new boolean[width][height];
        for(int y = 0; y < grid[0].length; y++){
            for(int x = 0; x < grid.length; x++){
                grid[x][y] = false;
            }
        }
    }
    public void update(){

    }    
    public void render(Graphics g){
        g.setColor(Color.white);
        for(int y = 0; y < grid[0].length; y++){
            for(int x = 0; x < grid.length; x++){
                g.drawRect(x*tileSize, y*tileSize,tileSize,tileSize);
                if(grid[x][y]){
                    g.fillRect(x*tileSize, y*tileSize,tileSize,tileSize);
                }
            }
        }
    }
}
