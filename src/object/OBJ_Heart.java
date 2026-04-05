package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Heart extends SuperObject{
	
	GamePanel gp;
	public OBJ_Heart(GamePanel gp) {
		
		name = "Dollar";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/HeartObject1.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/HeartObject2.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/objects/HeartObject3.png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
			image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
