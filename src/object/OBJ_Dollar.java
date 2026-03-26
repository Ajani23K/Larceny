package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Dollar extends SuperObject{

	GamePanel gp;
	public OBJ_Dollar(GamePanel gp) {
		
		name = "Dollar";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/DollarObjectWIP.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
