package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed;
	GamePanel gp;
	boolean checkDrawTime = false;
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	@Override
	public void keyTyped(KeyEvent e) { //not being used
	}

	@Override
	public void keyPressed(KeyEvent e) {


		int code = e.getKeyCode(); //returns the number of the key that was pressed
		//titleState
		if(gp.gameState == gp.titleState) {
			
			if(code == KeyEvent.VK_W) {
				if(gp.ui.commandNum== 0) {
					gp.ui.commandNum = 2;
				}else {
				gp.ui.commandNum--;
				}
			}
			if(code == KeyEvent.VK_S) {
				if(gp.ui.commandNum==2) {
					gp.ui.commandNum = 0;
				}else {
				gp.ui.commandNum++;
				}	
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
				}
				if(gp.ui.commandNum == 1) {
					//add later
				}
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		}
		
		
		//playState
		if(gp.gameState == gp.playState) {
			
			if(code == KeyEvent.VK_W) {
			upPressed = true;
			}
			if(code == KeyEvent.VK_A) {
			leftPressed = true;
			}
			if(code == KeyEvent.VK_S) {
			downPressed = true;
			}
			if(code == KeyEvent.VK_D) {
			rightPressed = true;
			}if(code == KeyEvent.VK_E) {
			ePressed = true;
			}if(code == KeyEvent.VK_ESCAPE) {
			if(gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
			}
		}
		
		//DEBUG
			if(code == KeyEvent.VK_T) {
				if(checkDrawTime == false) {
				checkDrawTime = true;
				gp.debugState = true;
				}
				else if(checkDrawTime == true) {
				checkDrawTime = false;
				gp.debugState = false;
				}
			}
		
		}
		//pauseState
		else if(gp.gameState == gp.pauseState) {
			if(gp.gameState == gp.pauseState) {
				gp.gameState = gp.playState;
			}
		}
		
		//dialogueState
		else if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_SPACE) {
				gp.gameState = gp.playState;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode(); //returns the number of the key that was pressed
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}if(code == KeyEvent.VK_E) {
			ePressed = false;
		}
		
	}

}
