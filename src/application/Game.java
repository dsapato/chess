package application;

import java.awt.event.MouseEvent;

public class Game {
	public static enum OWNER {
	    NONE, PLAYER_ONE, PLAYER_TWO
	}
	
	public static enum STATE {
		SELECTING, MOVING
	}

	public static Map map;
	public static Player playerOne;
	public static Player playerTwo;
	public static STATE state;
	public static OWNER turn;
	
	private boolean mouseReleased;
	
	//Constructor
	Game(){
		map = new Map();
		playerOne = new Player(OWNER.PLAYER_ONE);
		playerTwo = new Player(OWNER.PLAYER_TWO);
		state = STATE.SELECTING;
		turn = OWNER.PLAYER_ONE;
		mouseReleased = false;
	
	}
	
	//Game loop, updates and draws everything until closing
	public void run(){
		while(Zen.isRunning()){
			update();
			draw();
		}
	}
	
	//Update, calls appropriate update function of all objects
	private void update(){
		checkKeys();
		checkMouse();
	}
	
	//Draw, calls appropriate draw function of all objects
	private void draw(){
		map.draw();
		playerOne.drawMoves();
		playerTwo.drawMoves();
		playerOne.drawPieces();
		playerTwo.drawPieces();
		
		Zen.flipBuffer();
	}
	
	private void checkKeys(){
		//Check for escape
		if(Zen.isKeyPressed((char)27)){
			Zen.closeWindow();
			System.exit(0);
		}
	}
	
	private void checkMouse(){
		//Use release trick to avoid holding down counting as multiple clicks
		if(Zen.getMouseState() == MouseEvent.MOUSE_RELEASED || Zen.getMouseState() == MouseEvent.MOUSE_CLICKED){
			mouseReleased = true;
		}
		if(mouseReleased && Zen.getMouseState() == MouseEvent.MOUSE_PRESSED){
			mouseReleased = false;
			int mouseX = Zen.getMouseX();
			int mouseY = 640 - Zen.getMouseY();
			
			handleClick(mouseX, mouseY);
		}
	}
	
	private void handleClick(int x, int y){
		int boardX = x/map.getTileWidth();
		int boardY = y/map.getTileHeight();
		if(turn == OWNER.PLAYER_ONE){
			playerOne.select(boardX, boardY);
		}
		else if(turn == OWNER.PLAYER_TWO){
			playerTwo.select(boardX, boardY);
		}
	}
}
