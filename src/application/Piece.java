package application;

import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * A superclass for all game pieces. Contains things like position, size, image, and moves.
 */
public class Piece {
	/**
	 * X Coordinate of where Piece is on the board
	 */	
	protected int xPos;
	/**
	 * Y Coordinate of where Piece is on the board
	 */	
	protected int yPos;
	
	/**
	 * Width in pixels, used for drawing
	 */	
	protected int width;
	/**
	 * Height in pixels, used for drawing
	 */	
	protected int height;
	
	/**
	 * Image, loaded by subclass, specific to type of Piece
	 */	
	protected BufferedImage img;
	
	/**
	 * Vector expanded as needed to contain current possible moves to take
	 */	
	protected Vector<Pair> moves;
	
	/**
	 * Which player controls the Piece
	 */	
	protected Game.OWNER owner;
	
	/**
	 * True if the Piece has not moved yet at all
	 */	
	protected boolean firstMove;
	
	/**
	 * Only constructor, simply sets internals to parameters and gathers some data from the Map
	 * @param x Integer specifying the x position
	 * @param y Integer specifying the y position
	 * @param owner OWNER enumeration telling which player owns the Piece
	 */	
	public Piece(int x, int y, Game.OWNER owner){
		this.xPos = x;
		this.yPos = y;
		this.width = Game.map.getTileWidth();
		this.height = Game.map.getTileHeight();
		this.owner = owner;
		this.firstMove = true;
		this.moves = new Vector<Pair>();
	
		//Update map
		Game.map.setTileOwner(this.xPos, this.yPos, this.owner);
	}

	/**
	 * Draws img at (xPos,yPos) of size width by height
	 */	
	public void draw(){
		Zen.drawImage(img, xPos * width, Zen.getZenHeight() - ((yPos+1) * height), width, height);
	}
	
	/**
	 * Populates moves, unique to subclass
	 */	
	public void getMoves(){
		
	}
	
	/**
	 * Iterates through moves checking with Map to prove validity
	 */	
	protected void checkMoves(){
		for(int i = 0; i < moves.size(); i++){
			Pair move = moves.get(i);
			if(!Game.map.inBounds(move.x, move.y)){//Out of bounds, remove
				moves.remove(i);
				i--;//Remove() shifts all remaining elements to the left, so look at this spot again	
			}
			else if(Game.map.checkTileOccupied(move.x, move.y)){//Something in that spot
				if(!Game.map.checkTileEnemy(move.x, move.y, owner)){//Not an enemy
					moves.remove(i);
					i--;//Remove() shifts all remaining elements to the left, so look at this spot again
				}
			}
		}
	}
	
	/**
	 * Moves the piece, clearing old spot and taking control of new one
	 * @param x x-coordinate on map to move to
	 * @param y y-coordinate on map to move to
	 */	
	public void moveTo(int x, int y){
		firstMove = false;
		
		int oldX = this.xPos;
		int oldY = this.yPos;
		
		//Move
		this.xPos = x;
		this.yPos = y;
		
		//Release old spot, claim new
		Game.map.setTileOwner(x,y,owner);
		Game.map.setTileOwner(oldX, oldY, Game.OWNER.NONE);
	
	}
	
	/**
	 * Getter of x-coordinate
	 * @return x-coordinate on map of Piece
	 */	
	public int getX(){
		return xPos;
	}
	/**
	 * Getter of y-coordinate
	 * @return y-coordinate on map of Piece
	 */	
	public int getY(){
		return yPos;
	}
}
