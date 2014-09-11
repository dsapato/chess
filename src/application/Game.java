package application;

public class Game {
	public static enum OWNER {
	    NONE, PLAYER_ONE, PLAYER_TWO
	}

	public static Map map;
	public static Player playerOne;
	public static Player playerTwo;
	
	
	//Constructor
	Game(){
		map = new Map();
		playerOne = new Player(OWNER.PLAYER_ONE);
		playerTwo = new Player(OWNER.PLAYER_TWO);
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
	}
	
	//Draw, calls appropriate draw function of all objects
	private void draw(){
		map.draw();
		
		Zen.flipBuffer();
	}
	
	private void checkKeys(){
		//Check for escape
		if(Zen.isKeyPressed((char)27)){
			Zen.closeWindow();
			System.exit(0);
		}
	}

}
