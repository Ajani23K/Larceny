package tile;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UI;
import main.UtilityTool;
import map.SuperMap;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	SuperMap map;
	Font arial40 = new Font("Arial", Font.BOLD, 15);
	public TileManager(GamePanel gp, SuperMap map) {
		
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int [gp.MaxWorldCol][gp.MaxWorldRow];
		getTileImage();
		this.map = map;
		loadMap(map.FilePath);
		
		
		
	}
	
	public void getTileImage() {
		

			System.out.println("reading tile images");
			
			setup(0, "BuildingWIP", true);
			
			setup(1, "SideWalkWIP", false);
	
			setup(2, "StreetTileWIP", false);
			
			setup(3, "GrassTileWIP", false);

			setup(4, "SignTileWIP", true);

			setup(5, "CrossWalkWIP", false);
			
			setup(6, "WallWIP", true);
			
			setup(7, "FloorWIP", false);
			
			setup(8, "BarrierTile", true);

	}
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool utool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
			tile[index].image = utool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("tile images read failed");
		}
	}
	
	public void loadMap(String FilePath) {
		try {
			System.out.println("reading tile map, "+FilePath);
			InputStream is = getClass().getResourceAsStream(FilePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			System.out.println(gp.MaxWorldCol+" "+gp.MaxWorldRow);
			while(col < gp.MaxWorldCol && row < gp.MaxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.MaxWorldCol) {
					
					String numbers[]= line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
					
				}
				if(col == gp.MaxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			System.out.println("tilemap read failed");
		}
	}
	public void draw(Graphics2D g2) {
		
		
		int worldCol = 0;
		int worldRow = 0;
		
		
		while(worldCol < gp.MaxWorldCol && worldRow < gp.MaxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			//check the tile of world X and Y
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			//figure out where the player is relative to the world map position
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			//check if the tile being drawn is in players vision if it isnt in the players vision then it wont be drawn
			if(worldX + gp.tileSize*2 > gp.player.worldX - gp.player.screenX && 
			   worldX - gp.tileSize*2 < gp.player.worldX + gp.player.screenX && 
			   worldY + gp.tileSize*2 > gp.player.worldY - gp.player.screenY && 
			   worldY - gp.tileSize*2 < gp.player.worldY + gp.player.screenY) {
				//paint tile in that position 
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
				if(gp.debugState == true) {
					g2.setColor(Color.white);
					
					g2.setFont(arial40);
					g2.drawRect(screenX,screenY,gp.tileSize,gp.tileSize);
					String sworldCol =  Integer.toString(worldRow);
					String sworldRow =  Integer.toString(worldCol);
					String stileNum = "("+sworldCol+", "+sworldRow+")";
					g2.setColor(Color.green);
					g2.drawString(stileNum, screenX+gp.tileSize/4, screenY+gp.tileSize/2);
				}
			}
			
			//increment world col
			worldCol++;
			//if worldcol reaches max world col then it goes down a row
			if(worldCol == gp.MaxWorldCol) {
				
				worldCol = 0;
				worldRow++;
			}
		}
	}
	public void changeMap(SuperMap map) {
		
		gp.mapnum = map.mapNumber;
		gp.MaxWorldCol = map.worldCol;
		gp.MaxWorldRow = map.worldRow;
		loadMap(map.FilePath);
		
	}

}

