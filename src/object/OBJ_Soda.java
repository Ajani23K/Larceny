package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Soda extends SuperObject{

	GamePanel gp;
	public OBJ_Soda(GamePanel gp) {
		
		name = "Soda";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/SodaObjectWIP.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
