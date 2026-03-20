package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Trashcan extends SuperObject{

	public OBJ_Trashcan() {
		
		name = "Trashcan";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/TrashcanObjectWIP.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
