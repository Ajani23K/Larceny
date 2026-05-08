package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_CounterSideLeft extends Entity{

	public OBJ_CounterSideLeft(GamePanel gp) {
		
		super(gp);
		name = "CounterSideLeft";
		down1 = setup("/objects/CounterSideLeftObject");
		collision = true;
	}
}
