package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	//Visual Settings
	final int OGtileSize = 16; //16*16 original tile size
	final int scale = 3; //how much original tile size will be scaled up
	
	public final int tileSize = OGtileSize * scale; //48*48 is the final tile size 
	//declaring screen size
	public final int MaxScreenCol = 20;
	public final int MaxScreenRow = 16;
	
	public final int ScreenWidth = tileSize * MaxScreenCol; //960 pixels
	public final int ScreenHeight = tileSize * MaxScreenRow; //768 pixels
	
	//WORLD SETTINGS
	public final int MaxWorldCol = 50;
	public final int MaxWorldRow = 50;
	public final int WorldWidth = tileSize * MaxWorldCol;
	public final int WorldHeight = tileSize * MaxWorldRow;
	//FPS = frames per second
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(); //adding keyhandler so we can move character
	Thread GameThread; //this will allow us to run mechanics indefinitely, such as creating 60 frames per second
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this,keyH);
	

	//constructor to set panel size
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void StartGameThread() {
		GameThread = new Thread(this);
		GameThread.start();
	}
	@Override
	public void run() {
		// implementation of core game loop
		//as long as game thread exists this loop will run
		
		double drawInterval = 1000000000/FPS; //we draw the screen 0.01666 or 60 times a second
		double nextDrawTime = System.nanoTime() + drawInterval; //declare the next draw time to be the next 60th of a second
		
		while(GameThread != null) {
			
			long currentTime = System.nanoTime();
			
			//purpose of loop
			//update player information
			update();
			
			//draw on screen with updated information
			repaint(); //call paintComponent by calling repaint 
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				
				//convert time into milliseconds
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				//pause the game until time passes
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval; 
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		// when player hits a key the input recorded and the player icon is updated/moved
		
	}
	public void update() {
		//changing position of character based on user input
		player.update();
		}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		//draw background before player, similar to layers
		tileM.draw(g2);
		
		player.draw(g2);
		g2.dispose(); //good practice to save memory
	}
	
	
}
