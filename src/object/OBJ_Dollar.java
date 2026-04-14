package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Dollar extends Entity{

	
	public OBJ_Dollar(GamePanel gp) {
		
		super(gp);
		name = "Dollar";
		down1 = setup("/objects/DollarObjectWIP");
		
	}
}
