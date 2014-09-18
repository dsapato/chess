package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A Piece object, moves in an "L" and across rows or columns
 */
public class Chancellor extends Piece{

	/**
	 * Only constructor, simply sets internals to parameters and gathers some data from the Map
	 * @param x Integer specifying the x position
	 * @param y Integer specifying the y position
	 * @param owner OWNER enumeration telling which player owns the Piece
	 */	
	public Chancellor(int x, int y, Game.OWNER owner){
		super(x,y, owner);
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		try {
			if(owner == Game.OWNER.PLAYER_ONE)
				img = ImageIO.read(new File("resources/chancellorWhite.png"));
			else{
				img = ImageIO.read(new File("resources/chancellorBlack.png"));
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
		
		//Get all of the squares in this row
		moves.addAll(Game.map.getRowPositionsAt(xPos, yPos, owner));
		
		//Get all of the squares in this column
		moves.addAll(Game.map.getColumnPositionsAt(xPos, yPos, owner));
		
		//Get all tiles an L away
		moves.add(new Pair(xPos+2, yPos+1));
		moves.add(new Pair(xPos+1, yPos+2));
		
		moves.add(new Pair(xPos-2, yPos+1));
		moves.add(new Pair(xPos-1, yPos+2));
		
		moves.add(new Pair(xPos+2, yPos-1));
		moves.add(new Pair(xPos+1, yPos-2));
		
		moves.add(new Pair(xPos-2, yPos-1));
		moves.add(new Pair(xPos-1, yPos-2));
		
		checkMoves();
	}
}
