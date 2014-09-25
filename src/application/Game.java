package application;

import java.awt.event.MouseEvent;
import java.util.Stack;

/**
 * This is the manager of everything, including the Board, Players, and game loop
 */
public class Game {
	/**
	 * Owner enumeration to specify who controls something, whether its a turn or square
	 */
	public static enum OWNER {
	    NONE, PLAYER_ONE, PLAYER_TWO
	}
	
	/**
	 * State enumeration used to manage what stage the user is at
	 */
	public static enum STATE {
		SELECTING, MOVING, NEW_GAME_PROMPT
	}

	/**
	 * The Map we're playing on, this static reference allows all pieces to access the map easily
	 */
	public static Map map;
	
	/**
	 * Static reference to player one, the player on the bottom of the screen, which is white.
	 */	
	public static Player playerOne;

	/**
	 * Player two, the top player, who is black
	 */	
	public static Player playerTwo;

	/**
	 * State of the game, holds what the player is doing.
	 */	
	public static STATE state;

	/**
	 * Who's turn it is and can make a move
	 */	
	public static OWNER turn;
	
	private static boolean mouseReleased;
	
	/**
	 * Pair that represents matches won, (player one score, player two score)
	 */
	public static Pair score;
	
	/**
	 * Number of tied games
	 */
	public static int ties;
	
	/**
	 * Constant value of board width
	 */
	public final static int BOARD_WIDTH = 640;
	
	/**
	 * Constant value of board height
	 */
	public final static int BOARD_HEIGHT = 640;
	
	private static ControlPanel controlPanel;
	
	/**
	 * 
	 */
	public static Stack<MoveRecord> moveStack;
	
	/**
	 * The only constructor, creates Map, Players, and sets State
	 */
	Game(){
		newGame();
		score = new Pair(0,0);
		ties = 0;
		controlPanel = new ControlPanel();
	}
	
	public static void newGame(){
		map = new Map();
		playerOne = new Player(OWNER.PLAYER_ONE);
		playerTwo = new Player(OWNER.PLAYER_TWO);
		moveStack = new Stack<MoveRecord>();
		state = STATE.SELECTING;
		turn = OWNER.PLAYER_ONE;
		mouseReleased = false;		
	}

	/**
	 * Game loop, updates and draws everything until closing
	 */
	public void run(){
		while(Zen.isRunning()){
			update();
			draw();
		}
	}
	
	/**
	 * Update, calls appropriate update function of all objects
	 */
	private void update(){
		controlPanel.update();
		
		checkKeys();
		checkMouse();
	}
	
	/**
	 * Draw, calls appropriate draw function of all objects
	 */
	private void draw(){
		map.draw();
		playerOne.drawMoves();
		playerTwo.drawMoves();
		playerOne.drawPieces();
		playerTwo.drawPieces();
		controlPanel.draw();
		
		Zen.flipBuffer();
	}
	
	/**
	 * Check user input on keyboard
	 */	
	private void checkKeys(){
		//Check for escape
		if(Zen.isKeyPressed((char)27)){
			Zen.closeWindow();
			System.exit(0);
		}
	}
	
	/**
	 * Check user input of mouse movement and click
	 */	
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
	
	/**
	 * If click detected, handle according to game state
	 */	
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
	
	/**
	 * Undoes last move made
	 */
	public static void undoMove(){
		if(!moveStack.empty()){
			MoveRecord lastMove = moveStack.pop();
			lastMove.undo();	
		}
	}
	
	public static void switchTurn(){
		//Change turn
		if(turn == OWNER.PLAYER_ONE){
			turn = OWNER.PLAYER_TWO;
		}
		else if(Game.turn == OWNER.PLAYER_TWO){
			turn = OWNER.PLAYER_ONE;
		}
		state = STATE.SELECTING;
	}
}
