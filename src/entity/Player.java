package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
	
		screenX = gp.ScreenWidth/2 - (gp.tileSize/2);
		screenY = gp.ScreenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(8,16,32,32); //collision area
	
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 2;
		worldY = gp.tileSize * 8;
		speed = 4;
		direction = "down";
	}
	public void getPlayerImage(){
		
		try {
			//down
			down1 = ImageIO.read(getClass().getResource("/player/LarcyWalkDown1.png"));
			down2 = ImageIO.read(getClass().getResource("/player/LarcyWalkDown2.png"));
			down3 = ImageIO.read(getClass().getResource("/player/LarcyWalkDown3.png"));
			down4 = ImageIO.read(getClass().getResource("/player/LarcyWalkDown4.png"));
			down5 = ImageIO.read(getClass().getResource("/player/LarcyWalkDown5.png"));
			//left
			left1 = ImageIO.read(getClass().getResource("/player/LarcyWalkLeft1.png"));
			left2 = ImageIO.read(getClass().getResource("/player/LarcyWalkLeft2.png"));
			left3 = ImageIO.read(getClass().getResource("/player/LarcyWalkLeft3.png"));
			left4 = ImageIO.read(getClass().getResource("/player/LarcyWalkLeft4.png"));
			left5 = ImageIO.read(getClass().getResource("/player/LarcyWalkLeft5.png"));
			//right
			right1 = ImageIO.read(getClass().getResource("/player/LarcyWalkRight1.png"));
			right2 = ImageIO.read(getClass().getResource("/player/LarcyWalkRight2.png"));
			right3 = ImageIO.read(getClass().getResource("/player/LarcyWalkRight3.png"));
			right4 = ImageIO.read(getClass().getResource("/player/LarcyWalkRight4.png"));
			right5 = ImageIO.read(getClass().getResource("/player/LarcyWalkRight5.png"));
			//up
			up1 = ImageIO.read(getClass().getResource("/player/LarcyWalkUp1.png"));
			up2 = ImageIO.read(getClass().getResource("/player/LarcyWalkUp2.png"));
			up3 = ImageIO.read(getClass().getResource("/player/LarcyWalkUp3.png"));
			up4 = ImageIO.read(getClass().getResource("/player/LarcyWalkUp4.png"));
			up5 = ImageIO.read(getClass().getResource("/player/LarcyWalkUp5.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		//check if up is pressed to move player sprite
	if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true ||  keyH.leftPressed == true) {
		if(keyH.upPressed == true) {
			direction = "up";
			worldY -= speed;
			
		}
		else if(keyH.downPressed == true) {
			direction = "down";
			worldY += speed;
			
		}
		else if(keyH.leftPressed == true) {
			direction = "left";
			worldX -= speed;
			
		}
		else if(keyH.rightPressed == true) {
			direction = "right";
			worldX += speed;
		}
	
	spriteCounter++;
	//player image changes every 3 frames
	if(spriteCounter > 3) {
		if(spriteNum == 1) {
			spriteNum = 2;
		}else if(spriteNum == 2) {
			spriteNum = 3;
		}else if(spriteNum == 3) {
			spriteNum = 4;
		}else if(spriteNum == 4) {
			spriteNum = 5;
		}else if(spriteNum == 5) {
			spriteNum = 1;
		}
		spriteCounter = 0;
	}
	}
	
	
		//collisionOn = false;
		//gp.colChecker.checkTile(this);
	}
	public void draw(Graphics2D g2) {
		
		//g2.setColor(Color.white);
		
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		//based on input direction we pick different images
		switch(direction) {
		case "up":
			if(spriteNum ==1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			if(spriteNum == 3) {
				image = up3;
			}
			if(spriteNum == 4) {
				image = up4;
			}
			if(spriteNum == 5) {
				image = up5;
			}
			break;
		case "down":
			if(spriteNum ==1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			if(spriteNum == 3) {
				image = down3;
			}
			if(spriteNum == 4) {
				image = down4;
			}
			if(spriteNum == 5) {
				image = down5;
			}
			break;
		case "left":
			if(spriteNum ==1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			if(spriteNum == 3) {
				image = left3;
			}
			if(spriteNum == 4) {
				image = left4;
			}
			if(spriteNum == 5) {
				image = left5;
			}
			break;
		case "right":
			if(spriteNum ==1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			if(spriteNum == 3) {
				image = right3;
			}
			if(spriteNum == 4) {
				image = right4;
			}
			if(spriteNum == 5) {
				image = right5;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize,gp.tileSize, null);
		
	}
}