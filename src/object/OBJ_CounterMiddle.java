package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_CounterMiddle extends Entity{

public OBJ_CounterMiddle(GamePanel gp) {
		
		super(gp);
		name = "CounterMiddle";
		down1 = setup("/objects/CounterMiddleObject");
	
		collision = true;
	}
}
