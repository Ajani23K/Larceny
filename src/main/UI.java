package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Dollar;

public class UI {
	
	GamePanel gp;
	Font arial_40;
	BufferedImage moneyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		OBJ_Dollar dollar = new OBJ_Dollar(gp);
		moneyImage = dollar.image;
	}
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		//avoid instantiating objects in draw method as it will slow down the game (because its being called 60 times per second)
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		g2.drawImage(moneyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
		g2.drawString("x "+gp.player.hasMoney, 80,60);
		
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
}
