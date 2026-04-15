package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyHandler implements KeyListener, MouseListener{

	public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, rPressed, m1Pressed;
	GamePanel gp;
	boolean checkDrawTime = false;
	public boolean showGridLocation = false;
	public boolean showHitbox = false;
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
			if(code == KeyEvent.VK_R) {
				rPressed = true;
			}
		
		//DEBUG
			//check draw time
			if(code == KeyEvent.VK_T) {
				if(checkDrawTime == false) {
				checkDrawTime = true;
				}
				else if(checkDrawTime == true) {
				checkDrawTime = false;
				
				}
			}
			
			if(code == KeyEvent.VK_Y) {
				if(showGridLocation  == false) {
					
					showGridLocation = true;
					}
					else if(showGridLocation  == true) {
						
					showGridLocation = false;
					}
			}
			
			if(code == KeyEvent.VK_U) {
					if(showHitbox  == false) {
					
					showHitbox = true;
					}
					else if(showHitbox  == true) {
						
					showHitbox = false;
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
		if(code == KeyEvent.VK_R) {
			rPressed = false;
		}
		
	}
	public void mousePressed(MouseEvent e) {
		int code = e.getButton();
		
		if(code == MouseEvent.BUTTON1) {
			m1Pressed = true;
		}
	}
	public void mouseReleased(MouseEvent e) {
		int code = e.getButton();
		
		if(code == MouseEvent.BUTTON1) {
			m1Pressed = false;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
