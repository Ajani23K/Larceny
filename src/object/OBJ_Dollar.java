package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Dollar extends Entity{

	GamePanel gp;
	public OBJ_Dollar(GamePanel gp) {
		
		super(gp);
		this.gp = gp;
		name = "Dollar";
		down1 = setup("/objects/DollarObject");
		description = "[" + name + "]\nNever enough to buy \nhappiness.";
		pickable = true;
		value = 1;
		type = type_pickupOnly;
		
	}
	public void use(Entity entity) {
		gp.ui.showMessage("Money +" + value);
		gp.player.money+=value;
	}
}
