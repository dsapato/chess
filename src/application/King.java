package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class King extends Piece {

	//Constructor
	public King(int x, int y, Game.OWNER playerNum){
		super(x,y);
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		try {
			if(playerNum == Game.OWNER.PLAYER_ONE)
				img = ImageIO.read(new File("resources/kingWhite.png"));
			else{
				img = ImageIO.read(new File("resources/kingBlack.png"));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//When selected, get all possible moves
	public void getMoves(){
		moves.clear();
		
		//Get all tiles one away
		moves.add(new Pair(xPos-1, yPos-1));
		moves.add(new Pair(xPos  , yPos-1));
		moves.add(new Pair(xPos+1, yPos-1));
		moves.add(new Pair(xPos+1, yPos  ));
		moves.add(new Pair(xPos+1, yPos+1));
		moves.add(new Pair(xPos  , yPos+1));
		moves.add(new Pair(xPos-1, yPos+1));
		moves.add(new Pair(xPos-1, yPos  ));
	}	
	
}
