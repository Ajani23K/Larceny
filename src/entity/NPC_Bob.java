package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Bob extends Entity{

	public NPC_Bob(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	public void getImage(){
		
		
		//down
		down1 = setup("/npc/BobNPCDown1");
		
		//left
		left1 = setup("/npc/BobNPCLeft1");
		
		//right
		right1 = setup("/npc/BobNPCRight1");
	
		//up
		up1 = setup("/npc/BobNPCUp1");
	
		

	
	}
	public void setAction() {
		//acts as npcs AI
		actionLockCounter++; 
		Random random = new Random();
		if(actionLockCounter == 120) {
		int i = random.nextInt(100) + 1; //picks number from 1 to 100
		
		if(i <= 25) {
			direction = "up";
		}
		if(i > 25 && i <= 50) {
			direction = "down";
		}
		if(i > 50 && i <= 75) {
			direction = "left";
		}
		if(i > 75 && i <= 100) {
			direction = "right";
		}
		actionLockCounter = 0;
		}
		
	}
	public void setDialogue() {
		dialogues[0] = "Welcome to Bob's Bodega, please come inside!";
	}
	public void speak() {
		super.speak();
	}
}
