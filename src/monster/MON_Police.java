package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_Police extends Entity{

	public MON_Police(GamePanel gp) {
		super(gp);
		
		type = 2;
		name = "Police";
		monster = true;
		speed = 2;
		maxLife = 3;
		life = maxLife;
		
		solidArea.x = 8;
		solidArea.y = 2;
		solidArea.width = 10*3;
		solidArea.height = 13*3;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	public void getImage() {
		//load and scale sprites
		
		up1 = setup("/monster/PoliceUp1");
		down1 = setup("/monster/PoliceDown1");
		left1 = setup("/monster/PoliceLeft1");
		right1 = setup("/monster/PoliceRight1");
	}
	public void setAction() {
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

}
