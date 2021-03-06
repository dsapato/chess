package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A Piece object, moves forward one or two space, attacks to the diagonal
 */
public class Pawn extends Piece {

	/**
	 * Only constructor, simply sets internals to parameters and gathers some data from the Map
	 * @param x Integer specifying the x position
	 * @param y Integer specifying the y position
	 * @param owner OWNER enumeration telling which player owns the Piece
	 */	
	public Pawn(int x, int y, Game.OWNER owner){
		super(x,y, owner);
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		try {
			if(owner == Game.OWNER.PLAYER_ONE)
				img = ImageIO.read(new File("resources/pawnWhite.png"));
			else{
				img = ImageIO.read(new File("resources/pawnBlack.png"));
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
		
		//If first turn, move two squares
		if(firstMove){
			if(owner == Game.OWNER.PLAYER_ONE){
				if(!Game.map.checkTileEnemy(xPos, yPos + 2, owner))//Can't move forward if enemy there
					moves.add(new Pair     (xPos, yPos + 2));
			}
			else if(owner == Game.OWNER.PLAYER_TWO){
				if(!Game.map.checkTileEnemy(xPos, yPos - 2, owner))//Can't move forward if enemy there
					moves.add(new Pair     (xPos, yPos - 2));
			}
		}
		
		//Normal move forward
		if(owner == Game.OWNER.PLAYER_ONE){
			if(!Game.map.checkTileEnemy(xPos    , yPos + 1, owner))//Can't move forward if enemy there
				moves.add(new Pair     (xPos    , yPos + 1));
			if(Game.map.checkTileEnemy (xPos - 1, yPos + 1, owner))//Check diagonals for enemies
				moves.add(new Pair     (xPos - 1, yPos + 1));
			if(Game.map.checkTileEnemy (xPos + 1, yPos + 1, owner))//Check diagonals for enemies
				moves.add(new Pair     (xPos + 1, yPos + 1));
		}
		else if(owner == Game.OWNER.PLAYER_TWO){
			if(!Game.map.checkTileEnemy(xPos, yPos, owner))//Can't move forward if enemy there
				if(!Game.map.checkTileEnemy(xPos    , yPos - 1, owner))//Can't move forward if enemy there
					moves.add(new Pair     (xPos    , yPos - 1));
				if(Game.map.checkTileEnemy (xPos - 1, yPos - 1, owner))//Check diagonals for enemies
					moves.add(new Pair     (xPos - 1, yPos - 1));
				if(Game.map.checkTileEnemy (xPos + 1, yPos - 1, owner))//Check diagonals for enemies
					moves.add(new Pair     (xPos + 1, yPos - 1));
		}
		
		//Check diagonals for enemies
		
		
		checkMoves();
	}	
	
}
