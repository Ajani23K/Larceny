package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_CounterSideRight extends Entity{

	public OBJ_CounterSideRight(GamePanel gp) {
		
		super(gp);
		name = "CounterSideRight";
		down1 = setup("/objects/CounterSideRightObject");
		collision = true;
	}
}
