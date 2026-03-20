package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Soda extends SuperObject{

	public OBJ_Soda() {
		
		name = "Soda";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/SodaObjectWIP.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
