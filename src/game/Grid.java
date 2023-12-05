package game;
import java.awt.Color;
import java.awt.Graphics;

import io.Input;
public class Grid {
    private int width, height;
    public static int tileSize = 16;
    private static boolean[][] grid;
    private static boolean running;
    public Grid(int width, int height){
        this.width = width;
        this.height = height;
        generateEmptyGrid();
        
    }

    public static void birth(int x, int y){
        int xx = x/tileSize;
        int yy = y/tileSize;
        if(xx < 0 || xx > grid.length || yy < 0 || yy > grid[0].length){
            return;
        }else{
            grid[xx][yy] = true;

        }
    }
    public static void death(int x, int y){
        int xx = x/tileSize;
        int yy = y/tileSize;
        if(xx < 0 || xx > grid.length || yy < 0 || yy > grid[0].length){
            return;
        }else{
            grid[xx][yy] = false;

        }
    }
    public void generateEmptyGrid(){
        grid = new boolean[width][height];
        for(int y = 0; y < grid[0].length; y++){
            for(int x = 0; x < grid.length; x++){
                grid[x][y] = false;  
            }
        }
    }
    public void update(){
        if(running){
            grid = updateGrid();
        }
    }    

    //CONTROL RUNNING
    public static void run(){
        running = true;
    }
    public static void stop(){
        running = false;
    }
    public static void step(){
        grid = updateGrid();
    }

    private static boolean tryRead(int x, int y) {
		boolean b = false;
		try {
			b = grid[x][y];
		}catch(Exception e) {
			return false;
		}
		
		return b;
	}
    private static boolean[][] updateGrid() {
	
		int w = grid.length;
		int h = grid[0].length;			
		boolean[][] temp = new boolean[w][h];
		
		for(int y = 0; y < grid[0].length; y++) {
			for(int x = 0; x < grid.length; x++) {
				
				boolean[] neighbours = new boolean[8];
				neighbours[0] = tryRead(x, y-1);
				neighbours[1] = tryRead(x+1,y-1);
				neighbours[2] = tryRead(x+1,y);
				neighbours[3] = tryRead(x+1,y+1);
				neighbours[4] = tryRead(x,y+1);
				neighbours[5] = tryRead(x-1,y+1);
				neighbours[6] = tryRead(x-1,y);
				neighbours[7] = tryRead(x-1,y-1);
				
				//Count neightbours
				int countAlive = 0;
				for(int i = 0; i < neighbours.length; i++) {
					if(neighbours[i]) {
						countAlive++;
					}
				}
				
				
				if(grid[x][y]) {
					//ALIVE
					if(countAlive < 2) {
						temp[x][y] = false;
					}else if(countAlive ==2 || countAlive == 3) {
						temp[x][y] = true;
					}else if(countAlive > 3) {
						temp[x][y] = false;						
					}
				}else {
					//DEAD
					if(countAlive == 3) {
						temp[x][y] = true;
						
					}
				}
			}
		}
		
		
		return temp;
	}
    public void render(Graphics g){

        //update mousepressing here because this updates as fast as it can.
        int mouseX = Input.mouseX;
        int mouseY = Input.mouseY;

        if(Input.leftPress){
            birth(mouseX, mouseY);
        }
        if(Input.rightPress){
            death(mouseX, mouseY);
        }


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
