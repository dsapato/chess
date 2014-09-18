package application;

/**
 * Main class, creates a Game and runs it
 */
public class Chess {

	/**
	 * Main function, creates Game and calls run on it
	 * @param args No effect on program
	 */	
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}
}
