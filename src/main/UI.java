package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.OBJ_Heart;
import object.OBJ_Dollar;
import entity.Entity;


public class UI {
	
	GamePanel gp;
	public Font arial_40;
	Graphics2D g2;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public String currentDialogue;
	public int commandNum = 0;
	BufferedImage heart_full, heart_half, heart_blank;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		
		//Create HUD object
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		
	}
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		//titlestate
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		//playstate
		if(gp.gameState == gp.playState) {
			//Do playState stuff later
			 drawPlayerLife();
		}
		//pauseState
		if(gp.gameState == gp.pauseState) {
			 drawPlayerLife();
			 drawPausedScreen();
		}
		//dialogueState
		if(gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		//Message
		if(messageOn == true) {
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.drawString(message, 400, 60);
			
			messageCounter++;
			
			if(messageCounter > 120) {
				messageCounter = 0;
				messageOn = false;
			}
		}
	}
	public void drawPlayerLife() {
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		//Draw Max Life
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x+= gp.tileSize + 4;
		}
		
		//Reset
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x+= gp.tileSize + 4;
			if(x >= gp.ScreenWidth) {
				y += gp.tileSize;
				x = gp.tileSize/2;
			}
		}
		
		
	}
	public void drawTitleScreen() {
		//background color
		g2.setColor(new Color(124, 41, 42));
		g2.fillRect(0, 0, gp.ScreenWidth, gp.ScreenHeight);
		//Title Name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		String text = "Larceny";
		int x = getXforCenteredText(text);
		int y = gp.tileSize*3;
		
		//Shadow
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		//Main Color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		//Displaying Character Image
		
		x = gp.ScreenWidth/2 - (gp.tileSize)/2;
		y += gp.tileSize*4;
		g2.drawImage(gp.player.right1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		//Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum ==0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "LOAD GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x-gp.tileSize, y);
		}
	}
	public void drawPausedScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		
		int y = gp.ScreenHeight/2;
		
		g2.drawString(text, x, y);
	}
	public void drawDialogueScreen() {
		
		//Window 
		int x = gp.tileSize*2; 
		int y = gp.tileSize/2;
		int width = gp.ScreenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		
		drawSubWindow(x,y,width,height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
			//split text at \n then display it under the first line
		g2.drawString(line, x, y);
		y+= 40;
		}
	}
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color (0,0,0, 210); //final number is transparancy
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.ScreenWidth/2 - length/2;
		return x;
	}
	
}
