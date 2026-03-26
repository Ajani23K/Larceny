package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Trashcan extends SuperObject{
	
	GamePanel gp;
	public OBJ_Trashcan(GamePanel gp) {
		
		name = "Trashcan";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/TrashcanObjectWIP.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
