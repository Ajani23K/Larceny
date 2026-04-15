package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import map.SuperMap;
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
	
	
	//FPS = frames per second
	int FPS = 60;
	
	public int mapnum = 0;
	
	public SuperMap map[] = new SuperMap[10];
	TileManager tileM;
	public KeyHandler keyH; //adding keyhandler so we can move character
	Thread GameThread; //this will allow us to run mechanics indefinitely, such as creating 60 frames per second
	public CollisionChecker cChecker;
	public AssetSetter aSetter;
	public Player player;
	public Entity obj[] = new Entity[10];
	public UI ui;
	public Entity npc[] = new Entity[10];
	public EventHandler eventHandler;
	public Entity monster[] = new Entity[20];
	
	ArrayList<Entity> entityList = new ArrayList<>();
	//Game state
	
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	
	double time;
	
	//WORLD SETTINGS
	public int MaxWorldCol;
	public int MaxWorldRow;
	public int WorldWidth;
	public int WorldHeight;
	public int defaultOBJMAP = 2;
	

	//constructor to set panel size
	public GamePanel() {
		aSetter = new AssetSetter(this);
		aSetter.setMap();
		
		//WORLD SETTINGS
		MaxWorldCol = map[mapnum].worldCol;
		MaxWorldRow = map[mapnum].worldRow;
		System.out.println(MaxWorldCol + " " + MaxWorldRow);
		WorldWidth = tileSize * MaxWorldCol;
		WorldHeight = tileSize * MaxWorldRow;
				
		
		keyH = new KeyHandler(this); //adding keyhandler so we can move character
		tileM = new TileManager(this, map[mapnum], keyH);
		cChecker = new CollisionChecker(this);
		eventHandler = new EventHandler(this);
		player = new Player(this,keyH, tileM, map);
		ui = new UI(this);
		
		
		
		
		
		this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		
		aSetter.setObject(defaultOBJMAP);
		aSetter.setNPC();
		aSetter.setMonster();
		gameState = titleState;
		
		
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
		if(gameState == playState) {
		//changing position of character based on user input
		player.update();
		for(int i = 0; i < npc.length; i++) {
			if(npc[i] != null) {
				npc[i].update();
			}
		}
		for(int i = 0; i < monster.length; i++) {
			if(monster[i] != null) {
				monster[i].update();
			}
		}
	
		}
		if(gameState == pauseState) {
			//player info is not updated when paused
		}
		}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//DEBUG (how long does it take to draw these objects 
		long drawstart = 0;
		if(keyH.checkDrawTime == true) {
			drawstart = System.nanoTime();
		}
		//title Screen
		if(gameState == titleState) {
			ui.draw(g2);
		}
		//Others
		else {
			//draw background before player, similar to layers
			tileM.draw(g2);
			
			entityList.add(player);
			
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					entityList.add(npc[i]);
				}
				}
			
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					entityList.add(obj[i]);
				}
				}
			for(int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					entityList.add(monster[i]);
				}
			}
			
			//SORT
			Collections.sort(entityList, new Comparator<Entity>(){
				//sort entity list by worldY 
				@Override
				public int compare(Entity e1, Entity e2) {
					
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
				
			});
			
			//Draw Entities
			for(int i = 0; i <entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			//EMPTY ENTITY LIST
			entityList.clear();
			//draw ui
			ui.draw(g2);
			//DEBUG 
			if(keyH.checkDrawTime == true) {
				long drawEnd = System.nanoTime();
				long passed = drawEnd - drawstart;
				g2.setColor(Color.white);
				g2.drawString("Draw Time :"+ passed, 10, 400);
				System.out.println("Draw Time :"+ passed);
			}
		}
		
		
		g2.dispose(); //good practice to save memory
	}
	
	
	
}
