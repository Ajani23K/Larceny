package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Soda extends Entity{

	GamePanel gp;
	public OBJ_Soda(GamePanel gp) {
		
		super(gp);
		this.gp = gp;
		name = "Soda";
		down1 = setup("/objects/SodaObject");
		description = "[" + name + "]\nA sweet beverage, provides healing.";
		pickable = true;
		type = type_consumable;
		
	}
	public void use(Entity entity) {
		
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "As you drink the "+ name + ", " + 
		"you feel your health recover by " + healvalue + ".";
		entity.life += healvalue;
		if(gp.player.life > gp.player.maxLife) {
			entity.life = entity.maxLife;
		}
	}
}
