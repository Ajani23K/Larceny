package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Punch extends Entity{

public OBJ_Punch(GamePanel gp) {
		
		super(gp);
		name = "Punch";
		type = type_punch;
		attackValue = 1;
		attackArea.height = 32;
		attackArea.width = 32;
	}
}
