package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import map.SuperMap;
import tile.TileManager;

public class Player extends Entity{
	
	
	KeyHandler keyH;
	TileManager tileM;
	SuperMap map[];
	public final int screenX;
	public final int screenY;
	public boolean inStore = false;
	
	public double cooldownTime = 1000000000; //1 second cooldown
	public double startTime = 0;
	
	public Player(GamePanel gp, KeyHandler keyH, TileManager tileM, SuperMap map[]) {
		super(gp);
		

		invincible = false;
		this.keyH = keyH;
		this.tileM = tileM;
		this.map = map;
		screenX = gp.ScreenWidth/2 - (gp.tileSize/2);
		screenY = gp.ScreenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(8,16,25,25); //collision area x,y,width, height
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y; //record default values because they will change later
	
	
		attackArea.width = 36;
		attackArea.height = 36;
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
	}
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 2;
		worldY = gp.tileSize * 8;
		speed = 4;
		direction = "down";
		
		//player status
		//1 life = half heart, 2 life = full heart
		maxLife = 6;
		life = maxLife;
		
	}
	public void getPlayerImage(){
		
	
			//down
			down1 = setup("/player/LarcyWalkDown1");
			down2 = setup("/player/LarcyWalkDown2");
			down3 = setup("/player/LarcyWalkDown3");
			down4 = setup("/player/LarcyWalkDown4");
			down5 = setup("/player/LarcyWalkDown5");
			//left
			left1 = setup("/player/LarcyWalkLeft1");
			left2 = setup("/player/LarcyWalkLeft2");
			left3 = setup("/player/LarcyWalkLeft3");
			left4 = setup("/player/LarcyWalkLeft4");
			left5 = setup("/player/LarcyWalkLeft5");
			//right
			right1 = setup("/player/LarcyWalkRight1");
			right2 = setup("/player/LarcyWalkRight2");
			right3 = setup("/player/LarcyWalkRight3");
			right4 = setup("/player/LarcyWalkRight4");
			right5 = setup("/player/LarcyWalkRight5");
			//up
			up1 = setup("/player/LarcyWalkUp1");
			up2 = setup("/player/LarcyWalkUp2");
			up3 = setup("/player/LarcyWalkUp3");
			up4 = setup("/player/LarcyWalkUp4");
			up5 = setup("/player/LarcyWalkUp5");
			

		
	}
	public void getPlayerAttackImage() {
		//down
		attackdown1 = setup("/player/LarceyPunchDown1", gp.tileSize , gp.tileSize * 2);
		attackdown2 = setup("/player/LarceyPunchDown2", gp.tileSize , gp.tileSize * 2);
		attackdown3 = setup("/player/LarceyPunchDown3", gp.tileSize , gp.tileSize * 2);
		attackdown4 = setup("/player/LarceyPunchDown4", gp.tileSize , gp.tileSize * 2);
		attackdown5 = setup("/player/LarceyPunchDown5", gp.tileSize , gp.tileSize * 2);
		//left
		attackleft1 = setup("/player/LarceyPunchLeft1", gp.tileSize * 2, gp.tileSize);
		attackleft2 = setup("/player/LarceyPunchLeft2", gp.tileSize * 2, gp.tileSize);
		attackleft3 = setup("/player/LarceyPunchLeft3", gp.tileSize * 2, gp.tileSize);
		attackleft4 = setup("/player/LarceyPunchLeft4", gp.tileSize * 2, gp.tileSize);
		attackleft5 = setup("/player/LarceyPunchLeft5", gp.tileSize * 2, gp.tileSize);
		//right
		attackright1 = setup("/player/LarceyPunchRight1", gp.tileSize * 2, gp.tileSize);
		attackright2 = setup("/player/LarceyPunchRight2", gp.tileSize * 2, gp.tileSize);
		attackright3 = setup("/player/LarceyPunchRight3", gp.tileSize * 2, gp.tileSize);
		attackright4 = setup("/player/LarceyPunchRight4", gp.tileSize * 2, gp.tileSize);
		attackright5 = setup("/player/LarceyPunchRight5", gp.tileSize * 2, gp.tileSize);
		//up
		attackup1 = setup("/player/LarceyPunchUp1", gp.tileSize , gp.tileSize * 2);
		attackup2 = setup("/player/LarceyPunchUp2", gp.tileSize , gp.tileSize * 2);
		attackup3 = setup("/player/LarceyPunchUp3", gp.tileSize , gp.tileSize * 2);
		attackup4 = setup("/player/LarceyPunchUp4", gp.tileSize , gp.tileSize * 2);
		attackup5 = setup("/player/LarceyPunchUp5", gp.tileSize , gp.tileSize * 2);
	}
	public void update() {
		if(attacking == true) {
			attacking();
		}else {
		//check if up is pressed to move player sprite
		playerMovement();
		}
		//collisionOn = false;
		//gp.colChecker.checkTile(this);
	}
	public void attacking() {
		
		spriteCounter++;
		
		if(spriteCounter <= 5) {
			spriteNum = 1;
		}
		if(spriteCounter > 5 && spriteCounter <= 10) {
			spriteNum = 2;
			checkAttack();
		}
		if(spriteCounter > 10 && spriteCounter <= 15) {
			spriteNum = 3;
			checkAttack();
		}
		if(spriteCounter > 15 && spriteCounter <= 20) {
			spriteNum = 4;
			checkAttack();
		}
		if(spriteCounter > 20 && spriteCounter <= 25) {
			spriteNum = 5;
			checkAttack();
		}
		if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	public void checkAttack() {
		//save current worldx, y, height, width
		int currentWorldX = worldX;
		int currentWorldY = worldY;
		int solidAreaWidth = solidArea.width;
		int solidAreaHeight = solidArea.height;
		//adjust players worldx/y for attack area
		switch(direction) {
		case "up": worldY -= attackArea.height; break;
		case "down": worldY += attackArea.height; break;
		case "left": worldX -= attackArea.height; break;
		case "right": worldX += attackArea.height; break;
		}
		//attackArea becomes solidArea
		solidArea.width = attackArea.width;
		solidArea.height = attackArea.height;
		//check Monster collision with updated worldX and Y
		
		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
		damageMonster(monsterIndex);
		
		worldX = currentWorldX;
		worldY = currentWorldY;
		solidArea.width = solidAreaWidth;
		solidArea.height = solidAreaHeight;
		
	}
	public void draw(Graphics2D g2) {
		
		//g2.setColor(Color.white);
		
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		//based on input direction we pick different images
		switch(direction) {
		case "up":
			if(attacking == false) {
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
			}
			if(attacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum ==1) {
					image = attackup1;
				}
				if(spriteNum == 2) {
					image = attackup2;
				}
				if(spriteNum == 3) {
					image = attackup3;
				}
				if(spriteNum == 4) {
					image = attackup4;
				}
				if(spriteNum == 5) {
					image = attackup5;
				}
			}
			break;
		case "down":
			if(attacking == false) {
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
			}
			if(attacking == true) {
				if(spriteNum ==1) {
					image = attackdown1;
				}
				if(spriteNum == 2) {
					image = attackdown2;
				}
				if(spriteNum == 3) {
					image = attackdown3;
				}
				if(spriteNum == 4) {
					image = attackdown4;
				}
				if(spriteNum == 5) {
					image = attackdown5;
				}
			}
			
			break;
		case "left":
			if(attacking == false) {
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
			}
			if(attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum ==1) {
					image = attackleft1;
				}
				if(spriteNum == 2) {
					image = attackleft2;
				}
				if(spriteNum == 3) {
					image = attackleft3;
				}
				if(spriteNum == 4) {
					image = attackleft4;
				}
				if(spriteNum == 5) {
					image = attackleft5;
				}
			}
			
			break;
		case "right":
			if(attacking == false) {
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
			}
			if(attacking == true) {
				if(spriteNum ==1) {
					image = attackright1;
				}
				if(spriteNum == 2) {
					image = attackright2;
				}
				if(spriteNum == 3) {
					image = attackright3;
				}
				if(spriteNum == 4) {
					image = attackright4;
				}
				if(spriteNum == 5) {
					image = attackright5;
				}
			}
			
			break;
		}
		
		if(invincible) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f)); //setting opacity of character to 70%
		}
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		
		//reset opacity
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
	}
	public void playerMovement() {
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true ||  keyH.leftPressed == true || keyH.ePressed == true || keyH.rPressed) {
			if(keyH.upPressed == true) {
				direction = "up";
			
				
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			
				
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			
				
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			
			}
		collisionOn = false;
		gp.cChecker.checkTile(this); //pass player class as an entity to check for collision
		
		//check Object collision
		int objIndex = gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		
		//check NPC collision
		int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
		interactNPC(npcIndex);
		
		//check Monster collision
		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
		contactMonster(monsterIndex);
		
		//check event
		gp.eventHandler.checkEvent();
		
		
		
		//if collision is false, player can move 
		if(collisionOn == false && keyH.ePressed == false) {
			
			switch(direction) {	
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}
		
		keyH.ePressed = false;
		
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
		
		if(invincible) {
			invincibleCounter++;
			if(invincibleCounter> 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		
	}
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Dollar":
			
				break;
			case "Door":
			
				if(inStore == false) {
					if(System.nanoTime() - startTime >= cooldownTime) {
						tileM.changeMap(map[1]);
						inStore = true;
						startTime = System.nanoTime();
					}
				}
				else {	
					if(System.nanoTime() - startTime >= cooldownTime) {
					tileM.changeMap(map[0]);
					inStore = false;
					startTime = System.nanoTime();
					}
				}
				break;
			case "Trashcan":
				break;
			case "Soda":
				speed+=2;
				gp.ui.showMessage("You Feel Hastened!");
				gp.obj[i] = null;
				break;
			}
		}	
	}
	public void interactNPC(int i) {
		
		if(keyH.rPressed) {
			attacking = true;
		}
		
		if(i != 999) {
			if(keyH.ePressed == true) {
			gp.gameState = gp.dialogueState;
			gp.npc[i].speak();
		}
		}
		
	}
	public void contactMonster(int i) {
		if(i != 999) {
			
			if(invincible == false) {
			life -= 1;
			invincible = true;
			}
		}
		
	}
	public void damageMonster(int i) {
		if(i != 999) {
			
			if(gp.monster[i].invincible == false) {
				gp.monster[i].life -= 1;
				gp.monster[i].invincible = true;
				
				if(gp.monster[i].life <= 0) {
					gp.monster[i] = null;
				}
			}
		}
	}
}