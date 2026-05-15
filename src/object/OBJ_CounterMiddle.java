package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_CounterMiddle extends Entity{
	
	public OBJ_CounterMiddle(GamePanel gp) {
		
		super(gp);
		name = "CounterMiddle";
		down1 = setup("/objects/CounterMiddleObject");
		right1 = setup("/objects/CounterMiddleObject");
		left1 = setup("/objects/CounterMiddleObject");
		up1 = setup("/objects/CounterMiddleObject");
		
		collision = true;
		talkative = true;
		setDialogue();
	}
	public void setDialogue() {
	dialogues[0] = "That will be $20";
	}
	public void speak() {
		super.speak();
		
	}
}
