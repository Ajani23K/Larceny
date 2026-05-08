package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_CounterEdgeLeft extends Entity{

	public OBJ_CounterEdgeLeft(GamePanel gp) {
		
		super(gp);
		name = "CounterEdgeLeft";
		down1 = setup("/objects/CounterEdgeLeftObject");
		collision = true;
	}
}
