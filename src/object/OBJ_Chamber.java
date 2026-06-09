package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chamber extends Entity{
	

	public OBJ_Chamber(GamePanel gp) {
		
		super(gp);
		name = "Chamber";
		
		image = setup("/ui/Chamber1", gp.tileSize*4, gp.tileSize*4);
		image2 = setup("/ui/Chamber2", gp.tileSize*4, gp.tileSize*4);
		image3 = setup("/ui/Chamber3", gp.tileSize*4, gp.tileSize*4);
		image4 = setup("/ui/Chamber4", gp.tileSize*4, gp.tileSize*4);
		image5 = setup("/ui/Chamber5", gp.tileSize*4, gp.tileSize*4);
	
	}
}
