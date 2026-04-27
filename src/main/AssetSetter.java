package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import entity.NPC_Bob;
import map.Map_BobsBodega;
import map.Map_OBJBobsBodega;
import map.Map_OBJWorld;
import map.Map_World;
import map.SuperMap;
import monster.MON_Police;
import object.OBJ_Dollar;
import object.OBJ_Door;
import object.OBJ_Soda;
import object.OBJ_Trashcan;

public class AssetSetter {
	
	GamePanel gp;
	public int OBJTileNum[][];
	int objectCount = 0;
	int currentOBJMAP;
	boolean clearobj = false;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void setObject(int i) {
		if(clearobj) {
			for(int t = 0; t < gp.obj.length; t++) {
				gp.obj[t] = null;
			}
			clearobj = false;
		}
		OBJTileNum = new int [gp.map[i].worldCol][gp.map[i].worldRow];
		currentOBJMAP = i;
		loadObjectMap(gp.map[i]);
		readObjectMap();
		//reset object count after reading object map
		
		objectCount = 0;
		clearobj = true;
		
		/*gp.obj[0] = new OBJ_Dollar(gp);
		gp.obj[0].worldX = 24 * gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Dollar(gp);
		gp.obj[1].worldX = 26 * gp.tileSize;
		gp.obj[1].worldY = 9 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Door(gp);
		gp.obj[2].worldX = 3 * gp.tileSize;
		gp.obj[2].worldY = 6 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Door(gp);
		gp.obj[3].worldX = 4 * gp.tileSize;
		gp.obj[3].worldY = 6 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Soda(gp);
		gp.obj[4].worldX = 6 * gp.tileSize;
		gp.obj[4].worldY = 9 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Trashcan(gp);
		gp.obj[5].worldX = 7 * gp.tileSize;
		gp.obj[5].worldY = 9 * gp.tileSize; */
	}
	public void setMap() {
		gp.map[0] = new Map_World();
		gp.map[1] = new Map_BobsBodega();
		gp.map[2] = new Map_OBJWorld();
		gp.map[3] = new Map_OBJBobsBodega();
	}
	public void setNPC() {
		
		gp.npc[0] = new NPC_Bob(gp);
		gp.npc[0].worldX = gp.tileSize*2;
		gp.npc[0].worldY = gp.tileSize*7;
	}
	public void loadObjectMap(SuperMap map) {
		
		
			try {
				System.out.println("reading object map, "+map.FilePath);
				InputStream is = getClass().getResourceAsStream(map.FilePath);
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				
				int col = 0;
				int row = 0;
				System.out.println(map.worldCol+" "+map.worldRow);
				while(col <  map.worldCol && row < map.worldRow) {
					
					String line = br.readLine();
					
					while(col < map.worldCol) {
						
						String numbers[]= line.split(" ");
						int num = Integer.parseInt(numbers[col]);
						
						OBJTileNum[col][row] = num;
						col++;
						
					}
					if(col == map.worldCol) {
						col = 0;
						row++;
					}
				}
				br.close();
				
			}catch(Exception e) {
				System.out.println("tilemap read failed");
			}
		
	}
	public void readObjectMap() {
		//when reading an object map, 0 = door , 1 = trashcan,  2 = soda can, 3 = dollar
		int worldCol = 0;
		int worldRow = 0;
		int x;
		int y;
		
		
		while(worldRow < gp.map[currentOBJMAP].worldRow) {
			int OBJNum = OBJTileNum[0][worldRow];
			x = OBJTileNum[1][worldRow];
			y = OBJTileNum[2][worldRow];
			
				setObjectLocation(OBJNum, x, y);
				
			
				worldRow++;
		
			
		}
	}
	public void setMonster() {
		gp.monster[0] = new MON_Police(gp);
		gp.monster[0].worldX = 24 * gp.tileSize;
		gp.monster[0].worldY = 8 * gp.tileSize;
		
		gp.monster[1] = new MON_Police(gp);
		gp.monster[1].worldX = 27 * gp.tileSize;
		gp.monster[1].worldY = 9 * gp.tileSize;
	}
	public void setObjectLocation(int objNum, int col, int row) {
		switch(objNum) {
		case 0:
			gp.obj[objectCount] = new OBJ_Door(gp);
			gp.obj[objectCount].worldX = col * gp.tileSize;
			gp.obj[objectCount].worldY = row * gp.tileSize;
			break;
		case 1:
			gp.obj[objectCount] = new OBJ_Trashcan(gp);
			gp.obj[objectCount].worldX = col * gp.tileSize;
			gp.obj[objectCount].worldY = row * gp.tileSize;
			break;
		case 2:
			gp.obj[objectCount] = new OBJ_Soda(gp);
			gp.obj[objectCount].worldX = col * gp.tileSize;
			gp.obj[objectCount].worldY = row * gp.tileSize;
			break;
		case 3:
			gp.obj[objectCount] = new OBJ_Dollar(gp);
			gp.obj[objectCount].worldX = col * gp.tileSize;
			gp.obj[objectCount].worldY = row * gp.tileSize;
			break;
		}
		
		objectCount++;
	}
}
