package object;


import entity.Entity;
import main.GamePanel;

public class OBJ_Trashcan extends Entity{
	
	GamePanel gp;
	public OBJ_Trashcan(GamePanel gp) {
		
		super(gp);
		this.gp = gp;
		name = "Trashcan";
		down1 = setup("/objects/TrashcanObject");
		
		collision = true;
		//solidArea.x = 20;
		//solidArea.y = 20;
		
		//solidArea.height = 28;
		//solidArea.width = 28;
		
		//solidAreaDefaultX = solidArea.x;
		//solidAreaDefaultY = solidArea.y;
		
		container = true;
		
		
		setItems();
	}
	public void setItems() {
		//loot table for trash can 30% chance for nothing, 20% chance for abundance, 50% chance for normal
		
		int random = (int) (Math.random()* 100);
		String lootType;
		
		if(random < 30) {
			lootType = "none";
		}else if(random >= 30 && random < 50) {
			lootType = "abundance";
		}else {
			lootType = "normal";
		}
		
		if(lootType.equals("none")) {
			//no loot
			random = 0;
		}
		else if(lootType.equals("abundance")) {
			random = (int) (Math.random()* (10 - 3 + 1)) + 3;
		}else {
			random = (int) (Math.random()* (5 - 1 + 1)) + 1;
		}
		
		for(int i = 0; i < random; i++) {
			int loot = (int) (Math.random()* 2);
			
			if(loot == 0) {
				containerItems.add(new OBJ_Soda(gp));
			}else if(loot == 1) {
				containerItems.add(new OBJ_Dollar(gp));
			}
		}
	}
}
