package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_CounterEdgeRight extends Entity{

	public OBJ_CounterEdgeRight(GamePanel gp) {
		
		super(gp);
		name = "CounterEdgeRight";
		down1 = setup("/objects/CounterEdgeRightObject");
		collision = true;
	}
}
