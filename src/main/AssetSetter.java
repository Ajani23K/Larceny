package main;

import object.OBJ_Dollar;
import object.OBJ_Door;
import object.OBJ_Soda;
import object.OBJ_Trashcan;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Dollar();
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 8 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Dollar();
		gp.obj[1].worldX = 25 * gp.tileSize;
		gp.obj[1].worldY = 8 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Door();
		gp.obj[2].worldX = 2 * gp.tileSize;
		gp.obj[2].worldY = 5 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Door();
		gp.obj[3].worldX = 3 * gp.tileSize;
		gp.obj[3].worldY = 5 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Soda();
		gp.obj[4].worldX = 5 * gp.tileSize;
		gp.obj[4].worldY = 8 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Trashcan();
		gp.obj[5].worldX = 6 * gp.tileSize;
		gp.obj[5].worldY = 8 * gp.tileSize;
	}
}
