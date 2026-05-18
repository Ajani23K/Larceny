package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Gun  extends Entity{

public OBJ_Gun(GamePanel gp) {
		
		super(gp);
		name = "Gun";
		down1 = setup("/objects/GunObject");
		description = "[" + name + "]\nYour trusty weapon.";
		pickable = true;
		type = type_gun;
		attackValue = 3;
		attackArea.height = 48;
		attackArea.width = 48;
	}
}
