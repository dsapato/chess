package application;

import java.awt.Color;

/**
 * Side bar that gives information about the game state and allows for new games and undo moves
 * @author Danny
 *
 */
public class ControlPanel {

	//Buttons to start new game or undo
	private Button newGameButton;
	private Button undoButton;
	
	//Buttons seen on new game prompt
	private Button yesRestartButton;
	private Button noRestartButton;
	private Game.STATE oldState;
	
	/**
	 * Only constructor, creates side bar and needed buttons
	 */
	ControlPanel(){
		newGameButton = new Button(Game.BOARD_WIDTH + 55, Game.BOARD_HEIGHT - 80, 90, 25, "New Game");
		undoButton = new Button(Game.BOARD_WIDTH + 75, Game.BOARD_HEIGHT - 50, 45, 25, "Undo");
		yesRestartButton = new Button(Zen.getZenWidth() /2 - 75, Zen.getZenHeight()/2, 35, 25, "Yes");
		noRestartButton =  new Button(Zen.getZenWidth() /2 + 50, Zen.getZenHeight()/2, 25, 25, "No");
	}
	
	/**
	 * Need update called each game loop, allows buttons to be hovered and clicked
	 */
	public void update(){
		if(newGameButton.isClicked()){
			oldState = Game.state;
			Game.state = Game.STATE.NEW_GAME_PROMPT;
		}
		if(undoButton.isClicked()){
			Game.undoMove();
		}
		
		//Prompt screen of new game
		if(Game.state == Game.STATE.NEW_GAME_PROMPT){
			if(yesRestartButton.isClicked()){//New Game
				Game.newGame();
				Game.ties++;
			}
			if(noRestartButton.isClicked()){//Go back to game
				Game.state = oldState;
			}
		}
	}
	
	/**
	 * Draw the side control panel and all of its child elements
	 */
	public void draw(){
		//Title
		Zen.drawText("-----------------", Game.BOARD_WIDTH + 50, 50);
		Zen.drawText("Chess", Game.BOARD_WIDTH + 75, 80);
		Zen.drawText("-----------------", Game.BOARD_WIDTH + 50, 110);
		
		//Turn
		Zen.drawText("Turn:", Game.BOARD_WIDTH + 75, 220);
		if(Game.turn == Game.OWNER.PLAYER_ONE){
			Zen.drawText("Player One (Bottom)", Game.BOARD_WIDTH + 20, 250);
		}
		else{
			Zen.drawText("Player Two (Top)", Game.BOARD_WIDTH + 30, 250);
		}
		
		//Score
		Zen.drawText("Score:", Game.BOARD_WIDTH + 72, 320);
		Zen.drawText("(P1, P2, Draws)", Game.BOARD_WIDTH + 32, 350);
		Zen.drawText("(" + Game.score.x + ", " + Game.score.y + ", " + Game.ties + ")", Game.BOARD_WIDTH + 65, 380);
		
		//Buttons\
		newGameButton.draw();
		undoButton.draw();
		
		//Draw prompt over everything
		if(Game.state == Game.STATE.NEW_GAME_PROMPT){
			int xStart = Zen.getZenWidth() /2 - 400;
			int yStart = Zen.getZenHeight()/2 - 200;
			Zen.setColor(Color.LIGHT_GRAY);
			Zen.fillRect(xStart, yStart, 800, 400);
			Zen.setColor(Color.WHITE);
			Zen.drawText("Are you sure you want to restart?", xStart+275, yStart+170);
			yesRestartButton.draw();
			noRestartButton.draw();
		}
	}
}
