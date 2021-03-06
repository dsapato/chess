package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A Piece object, must keep alive, moves one space in any direction
 */
public class King extends Piece {

	/**
	 * Only constructor, simply sets internals to parameters and gathers some data from the Map
	 * @param x Integer specifying the x position
	 * @param y Integer specifying the y position
	 * @param owner OWNER enumeration telling which player owns the Piece
	 */	
	public King(int x, int y, Game.OWNER owner){
		super(x,y, owner);
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		try {
			if(owner == Game.OWNER.PLAYER_ONE)
				img = ImageIO.read(new File("resources/kingWhite.png"));
			else{
				img = ImageIO.read(new File("resources/kingBlack.png"));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets all available moves from the Map and stores them internally
	 */	
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
		
		checkMoves();
	}	
	
}
