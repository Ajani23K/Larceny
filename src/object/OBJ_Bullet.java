package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Bullet extends Projectile{
	
	GamePanel gp;
	
	public OBJ_Bullet(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Bullet";
		speed = 5;
		maxLife = 80;
		life = maxLife;
		attackValue = 2;
		getImage();
	}
	
	public void getImage() {
		 up1 = setup("/projectile/BulletUp",gp.tileSize, gp.tileSize); 
		 down1 = setup("/projectile/BulletDown",gp.tileSize, gp.tileSize); 
		 left1 = setup("/projectile/BulletLeft",gp.tileSize, gp.tileSize); 
		 right1 = setup("/projectile/BulletRight",gp.tileSize, gp.tileSize); 
	}
}
