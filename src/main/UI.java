package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Dollar;

public class UI {
	
	GamePanel gp;
	Font arial_40;
	Graphics2D g2;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public String currentDialogue;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		
	}
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		//playstate
		if(gp.gameState == gp.playState) {
			//Do playState stuff later
		}
		//pauseState
		if(gp.gameState == gp.pauseState) {
			 drawPausedScreen();
		}
		//dialogueState
		if(gp.gameState == gp.dialogueState) {
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
