package object;

import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;

public class OBJ_CounterMiddle extends Entity{
	GamePanel gp;
	public OBJ_CounterMiddle(GamePanel gp) {
		
		super(gp);
		this.gp = gp;
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
		checkPrice();
		super.speak();
		
	}
	public void checkPrice() {
		int price = 0;
		ArrayList<Entity> items = new ArrayList<Entity>(gp.crimeHandler.sendObjects());
		for(int i = 0; i < items.size(); i++) {
			Entity Item = items.get(i);
			
			if(Item.name.equals("Soda")) {
				price += 2;
			}
		}
		dialogues[0] = "That will be $"+price;
	}
}
