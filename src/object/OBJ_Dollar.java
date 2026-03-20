package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Dollar extends SuperObject{

	public OBJ_Dollar() {
		
		name = "Dollar";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/DollarObjectWIP.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
