package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sign extends Entity{

	public OBJ_Sign(GamePanel gp) {
		
		super(gp);
		name = "Sign";
		down1 = setup("/objects/SignObject", 48, (int)(48*1.5));
		collision = true;
		entityWidth = gp.tileSize;
		entityHeight = (int)(48*1.5);
	}
}
