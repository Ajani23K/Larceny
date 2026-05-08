package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sign extends Entity{

	public OBJ_Sign(GamePanel gp) {
		
		super(gp);
		name = "Sign";
		down1 = setup("/objects/SignObject");
		
	}
}
