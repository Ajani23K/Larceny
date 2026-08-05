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
		
		containerItems.add(new OBJ_Soda(gp));
		containerItems.add(new OBJ_Soda(gp));
		containerItems.add(new OBJ_Soda(gp));
		containerItems.add(new OBJ_Soda(gp));
		
	}
}
