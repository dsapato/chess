package application;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class Piece {
	protected int xPos;
	protected int yPos;
	
	protected int width;
	protected int height;
	
	protected BufferedImage img;
	
	protected Vector<Pair> moves;
	
	protected Game.OWNER owner;
	protected boolean firstMove;
	
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

	public void draw(){
		Zen.drawImage(img, xPos * width, Zen.getZenHeight() - ((yPos+1) * height), width, height);
	}
	
	//Overridden function
	public void getMoves(){
		
	}
	
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
	
	public int getX(){
		return xPos;
	}
	public int getY(){
		return yPos;
	}
}
