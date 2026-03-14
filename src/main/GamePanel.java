package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	//Visual Settings
	final int OGtileSize = 16; //16*16 original tile size
	final int scale = 3; //how much original tile size will be scaled up
	
	final int tileSize = OGtileSize * scale; //48*48 is the final tile size 
	//declaring screen size
	final int MaxScreenCol = 20;
	final int MaxScreenRow = 16;
	
	final int ScreenWidth = tileSize * MaxScreenCol; //960 pixels
	final int ScreenHeight = tileSize * MaxScreenRow; //768 pixels
	
	Thread GameThread; //this will allow us to run mechanics indefinitely, such as creating 60 frames per second
	
	//constructor to set panel size
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
	
	public void StartGameThread() {
		GameThread = new Thread(this);
		GameThread.start();
	}
	@Override
	public void run() {
		// implementation of core game loop
		
	}
	
	
	
}
