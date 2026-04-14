package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Soda extends Entity{

	
	public OBJ_Soda(GamePanel gp) {
		
		super(gp);
		name = "Soda";
		down1 = setup("/objects/SodaObjectWIP");
		
	}
}
