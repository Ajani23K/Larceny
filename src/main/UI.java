package main;

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
		
		if(gp.gameState == gp.playState) {
			//Do playState stuff later
		}
		if(gp.gameState == gp.pauseState) {
			 drawPausedScreen();
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
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.ScreenWidth/2 - length/2;
		return x;
	}
}
