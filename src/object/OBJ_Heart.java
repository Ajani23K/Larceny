package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity{
	

	public OBJ_Heart(GamePanel gp) {
		
		super(gp);
		name = "Heart";
		
		image = setup("/objects/HeartObject1");
		image2 = setup("/objects/HeartObject2");
		image3 = setup("/objects/HeartObject3");
	
	}
}
