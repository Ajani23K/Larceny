package main;

import entity.NPC_Bob;
import map.Map_BobsBodega;
import map.Map_World;
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
		gp.obj[0] = new OBJ_Dollar(gp);
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 8 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Dollar(gp);
		gp.obj[1].worldX = 25 * gp.tileSize;
		gp.obj[1].worldY = 8 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Door(gp);
		gp.obj[2].worldX = 2 * gp.tileSize;
		gp.obj[2].worldY = 5 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Door(gp);
		gp.obj[3].worldX = 3 * gp.tileSize;
		gp.obj[3].worldY = 5 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Soda(gp);
		gp.obj[4].worldX = 5 * gp.tileSize;
		gp.obj[4].worldY = 8 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Trashcan(gp);
		gp.obj[5].worldX = 6 * gp.tileSize;
		gp.obj[5].worldY = 8 * gp.tileSize;
	}
	public void setMap() {
		gp.map[0] = new Map_World();
		gp.map[1] = new Map_BobsBodega();
	}
	public void setNPC() {
		
		gp.npc[0] = new NPC_Bob(gp);
		gp.npc[0].worldX = gp.tileSize*1;
		gp.npc[0].worldY = gp.tileSize*6;
	}
}
