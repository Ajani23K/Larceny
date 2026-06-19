package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.AssetSetter;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import map.SuperMap;
import object.OBJ_Bullet;
import object.OBJ_Dollar;
import object.OBJ_Punch;
import object.OBJ_Soda;
import tile.TileManager;

public class Player extends Entity{
	
	
	KeyHandler keyH;
	TileManager tileM;
	AssetSetter aSetter;
	SuperMap map[];
	public String playerStoreLocation = "";
	public final int screenX;
	public final int screenY;
	public Entity Punch;
	public boolean inStore = false;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	public Entity lasttalked;
	public double weaponCooldown;
	
	public double cooldownTime = 1000000000; //1 second cooldown
	public double startTime = 0;
	
	public Player(GamePanel gp, KeyHandler keyH, TileManager tileM, SuperMap map[], AssetSetter aSetter) {
		super(gp);
		
		this.aSetter = aSetter;
		invincible = false;
		this.keyH = keyH;
		this.tileM = tileM;
		this.map = map;
		screenX = gp.ScreenWidth/2 - (gp.tileSize/2);
		screenY = gp.ScreenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(8,16,25,25); //collision area x,y,width, height
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y; //record default values because they will change later
	
	
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		getPlayerShootImage();
		setItems();
	}
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 2;
		worldY = gp.tileSize * 8;
		speed = 4;
		direction = "down";
		
		//player status
		//1 life = half heart, 2 life = full heart
		weight = 150;
		height = 4;
		bodyType = "Slim";
		playerPassive = "None";
		maxAmmo = 4;
		ammo = maxAmmo;
		maxLife = 6;
		life = maxLife;
		money = 4;
		Punch = new OBJ_Punch(gp);
		currentWeapon = Punch;
		projectile = new OBJ_Bullet(gp);
		setAttack();
		
		
	}
	public void setAttack() {
		playerAttack = currentWeapon.attackValue;
	}
	public void setItems() {
		
		inventory.add(new OBJ_Soda(gp));
		
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
	public void setProjectile() {
		if(currentWeapon.type == type_gun) {
			projectile = new OBJ_Bullet(gp);
		}
	}
	public void getPlayerAttackImage() {
		if(currentWeapon.type == type_punch) {
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
		else if(currentWeapon.type == type_gun) {
			
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
		
	}
	public void getPlayerShootImage() {
		
		if(currentWeapon.type == type_gun) {
			
			//down
			shootdown1 = setup("/player/LarceyShootDown1", gp.tileSize , gp.tileSize * 2);
			shootdown2 = setup("/player/LarceyShootDown2", gp.tileSize , gp.tileSize * 2);
			shootdown3 = setup("/player/LarceyShootDown3", gp.tileSize , gp.tileSize * 2);
			shootdown4 = setup("/player/LarceyShootDown4", gp.tileSize , gp.tileSize * 2);
			shootdown5 = setup("/player/LarceyShootDown5", gp.tileSize , gp.tileSize * 2);
			//left
			shootleft1 = setup("/player/LarceyShootLeft1", gp.tileSize * 2, gp.tileSize);
			shootleft2 = setup("/player/LarceyShootLeft2", gp.tileSize * 2, gp.tileSize);
			shootleft3 = setup("/player/LarceyShootLeft3", gp.tileSize * 2, gp.tileSize);
			shootleft4 = setup("/player/LarceyShootLeft4", gp.tileSize * 2, gp.tileSize);
			shootleft5 = setup("/player/LarceyShootLeft5", gp.tileSize * 2, gp.tileSize);
			//right
			shootright1 = setup("/player/LarceyShootRight1", gp.tileSize * 2, gp.tileSize);
			shootright2 = setup("/player/LarceyShootRight2", gp.tileSize * 2, gp.tileSize);
			shootright3 = setup("/player/LarceyShootRight3", gp.tileSize * 2, gp.tileSize);
			shootright4 = setup("/player/LarceyShootRight4", gp.tileSize * 2, gp.tileSize);
			shootright5 = setup("/player/LarceyShootRight5", gp.tileSize * 2, gp.tileSize);
			//up
			shootup1 = setup("/player/LarceyShootUp1", gp.tileSize , gp.tileSize * 2);
			shootup2 = setup("/player/LarceyShootUp2", gp.tileSize , gp.tileSize * 2);
			shootup3 = setup("/player/LarceyShootUp3", gp.tileSize , gp.tileSize * 2);
			shootup4 = setup("/player/LarceyShootUp4", gp.tileSize , gp.tileSize * 2);
			shootup5 = setup("/player/LarceyShootUp5", gp.tileSize , gp.tileSize * 2);
		}
	}
	
	public void update() {
		if(attacking == true || shooting == true) {
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
			shooting = false;
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
		case "up": worldY -= currentWeapon.attackArea.height; break;
		case "down": worldY += currentWeapon.attackArea.height; break;
		case "left": worldX -= currentWeapon.attackArea.width; break;
		case "right": worldX += currentWeapon.attackArea.width; break;
		}
		//attackArea becomes solidArea
		solidArea.width = currentWeapon.attackArea.width;
		solidArea.height = currentWeapon.attackArea.height;
		//check Monster collision with updated worldX and Y
		
		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
		damageMonster(monsterIndex, playerAttack);
		
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
			if(shooting == true) {
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum ==1) {
					image = shootup1;
				}
				if(spriteNum == 2) {
					image = shootup2;
				}
				if(spriteNum == 3) {
					image = shootup3;
				}
				if(spriteNum == 4) {
					image = shootup4;
				}
				if(spriteNum == 5) {
					image = shootup5;
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
			if(shooting == true) {
				if(spriteNum ==1) {
					image = shootdown1;
				}
				if(spriteNum == 2) {
					image = shootdown2;
				}
				if(spriteNum == 3) {
					image = shootdown3;
				}
				if(spriteNum == 4) {
					image = shootdown4;
				}
				if(spriteNum == 5) {
					image = shootdown5;
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
			if(shooting == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum ==1) {
					image = shootleft1;
				}
				if(spriteNum == 2) {
					image = shootleft2;
				}
				if(spriteNum == 3) {
					image = shootleft3;
				}
				if(spriteNum == 4) {
					image = shootleft4;
				}
				if(spriteNum == 5) {
					image = shootleft5;
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
			if(shooting == true) {
				if(spriteNum ==1) {
					image = shootright1;
				}
				if(spriteNum == 2) {
					image = shootright2;
				}
				if(spriteNum == 3) {
					image = shootright3;
				}
				if(spriteNum == 4) {
					image = shootright4;
				}
				if(spriteNum == 5) {
					image = shootright5;
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
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true || keyH.ePressed == true || keyH.m1Pressed) {
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
		interactDoor(objIndex);
		pickUpObject(objIndex);
		interactObject(objIndex);
		
		//check NPC collision
		int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
		interactNPC(npcIndex);
		
		//check Monster collision
		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
		contactMonster(monsterIndex);
		
		//check event
		gp.eventHandler.checkEvent();
		
		
		
		//if collision is false, player can move 
		if(collisionOn == false && keyH.ePressed == false && keyH.m1Pressed == false) {
			
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
		
		 HandleShooting();
		 HandleReload();
		 
		if(invincible) {
			invincibleCounter++;
			if(invincibleCounter> 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		
	}
	public void HandleShooting() {
		
		if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30 && currentWeapon.type == type_gun && ammo > 0) {
			//if(projectile.alive == false) {
			// Set default coords, direction, user, alive 
			shooting = true;
			projectile.set(worldX, worldY, direction, true, this);
			
			gp.projectileList.add(projectile);
			shotAvailableCounter = 0;
			ammo--;
			
			//}
		}
		if(shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}
	}
	public void HandleReload() {
		if(gp.keyH.rPressed) {
			ammo = maxAmmo;
		}
	}
	
	public void pickUpObject(int i) {
		
		if(i != 999) {
			if(gp.obj[i].type == type_pickupOnly) {
				gp.obj[i].use(this);
				removeObject(i);
			}else {
				//Inventory Items
				String text = "";
				String objectName = gp.obj[i].name;
				if(inventory.size() != maxInventorySize) {
					if(gp.obj[i].pickable) {
						if(inStore) {
							gp.crimeHandler.addObject(gp.obj[i]);
						}
					inventory.add(gp.obj[i]);
					removeObject(i);
					text = "You picked up a " + objectName + ".";
					}
				}else {
					text = "Your bag is full!";
				}
				if(text.length() > 0) {
				gp.ui.showMessage(text);
				}
			}
		}
	}
	public void interactNPC(int i) {
		
		if(keyH.m1Pressed) {
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
			
			if(invincible == false && gp.monster[i].dying == false) {
			life -= 1;
			invincible = true;
			}
		}
		
	}
	public void damageMonster(int i, int attack) {
		if(i != 999) {
			
			if(gp.monster[i].invincible == false) {
				gp.monster[i].life -= attack;
				if(gp.monster[i].life  < 0) {
					gp.monster[i].life = 0;
				}
				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();
				
				if(gp.monster[i].life <= 0) {
					gp.monster[i].dying = true;
				}
			}
		}
	}
	public boolean checkObjectLocation(int worldX, int worldY, int objX, int objY) {
		System.out.println("OBJECT X: "+ objX+", "+ "OBJECT Y: "+ objY);
		System.out.println("WORLDX CHECK: "+ worldX*gp.tileSize+", "+ "WORLDY CHECK: "+ gp.tileSize*worldY);
		if(worldX*gp.tileSize == objX && gp.tileSize*worldY == objY) {
			
			return true;
		}
		return false;
	}
	public void setPlayerExitLocation() {
		switch(playerStoreLocation) {
		case "BobsBodega":
			worldX = (int)(3.5*gp.tileSize);
			worldY = 7*gp.tileSize;
		}
		playerStoreLocation = "";
	}
	public void interactDoor(int i) {
		
		if(i != 999) {
			String objectname = gp.obj[i].name;
			if(objectname.equals("Door")) {
			
				
				if(inStore == false) {
					if(System.nanoTime() - startTime >= cooldownTime) {
						
						//world x, worldy, obj worldx, obj world y
						if(checkObjectLocation(3, 6, gp.obj[i].worldX, gp.obj[i].worldY) || checkObjectLocation(4, 6, gp.obj[i].worldX, gp.obj[i].worldY)) {
							//doors assigned tile map, doors object map, where to move player x, where to move player y
						
							
							worldX = (int)(3.5*gp.tileSize);
							worldY = 73*gp.tileSize;
							inStore = true;
							aSetter.moveNPC(0);
							playerStoreLocation = "BobsBodega";
							gp.crimeHandler.saveInventory(inventory);
							
						}
						
						
						startTime = System.nanoTime();
					}
				}
				else {	
					if(System.nanoTime() - startTime >= cooldownTime) {
					gp.crimeHandler.checkStealing(inventory);
					gp.crimeHandler.clearObject();
					setPlayerExitLocation();
					inStore = false;
					aSetter.moveNPC(0);
					startTime = System.nanoTime();
					
					}
				}
			}
		}
	}
	public void removeObject(int i) {
		gp.obj[i] = null;
	}
	public void interactObject(int i) {
		if(i != 999) {
			if(gp.obj[i] != null) {
				if(gp.obj[i].talkative) {
					if(keyH.ePressed == true) {
						gp.gameState = gp.dialogueState;
						gp.obj[i].speak();
						lasttalked = gp.obj[i];
					}
				}
			}
		}
	}
	public void selectItem() {
		
		int itemIndex = gp.ui.getItemIndexOnSlot();
		
		if(itemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(itemIndex);
			
			if(selectedItem.type == type_gun) {
				
				if(selectedItem.type != currentWeapon.type) {
					
					currentWeapon = selectedItem;
					getPlayerAttackImage();
					getPlayerShootImage();
					setAttack();
				}else {
					currentWeapon = Punch;
					getPlayerAttackImage();
					getPlayerShootImage();
					setAttack();
				}
				
			}
			if(selectedItem.type == type_consumable) {
				
				selectedItem.use(this);
				inventory.remove(itemIndex);
			}
		}
	}
	public void selectDialogue() {
		if(gp.ui.dslotCol == 0) {
			
			System.out.println(lasttalked.name);
			//response yes
			if(lasttalked != null) {
				lasttalked.response(true);
			}
		}else {
			//response no
			if(lasttalked != null) {
				lasttalked.response(false);
			}
		}
	}
	public int checkMoney() {
		/*
		int money = 0;
		for(int i = 0; i < inventory.size(); i++) {
			Entity item = inventory.get(i);
			if(item.name.equals("Dollar")) {
				money++;
			}
		}
		*/
		return money;
	}
	public void consumeMoney(int amount) {
		/*
		for(int t = 0; t < inventory.size() && amount > 0; t++) {
			Entity item = inventory.get(t);
		
			if(item != null && "Dollar".equals(item.name)) {
				inventory.remove(t);
				t--;
				amount--;
			}
			
		}
		*/
		money-=amount;
	}
	
}