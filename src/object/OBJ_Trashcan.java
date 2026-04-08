package object;


import entity.Entity;
import main.GamePanel;

public class OBJ_Trashcan extends Entity{
	

	public OBJ_Trashcan(GamePanel gp) {
		
		super(gp);
		name = "Trashcan";
		down1 = setup("/objects/TrashcanObjectWIP");
		
		collision = true;
		solidArea.x = 20;
		solidArea.y = 20;
		
		solidArea.height = 28;
		solidArea.width = 28;
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		
	}
}
