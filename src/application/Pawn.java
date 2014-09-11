package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pawn extends Piece {
	
	private boolean firstMove;
	private Game.OWNER player;

	//Constructor
	public Pawn(int x, int y, Game.OWNER owner){
		super(x,y, owner);
		firstMove = true;
		
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
	
	//When selected, get all possible moves
	public void getMoves(){
		moves.clear();
		
		//If first turn, move two squares
		if(firstMove){
			if(owner == Game.OWNER.PLAYER_ONE){
				moves.add(new Pair(xPos, yPos + 2));
			}
			else if(owner == Game.OWNER.PLAYER_TWO){
				moves.add(new Pair(xPos, yPos - 2));
			}
		}
		
		//Normal move forward
		if(owner == Game.OWNER.PLAYER_ONE){
			moves.add(new Pair(xPos, yPos + 1));
		}
		else if(owner == Game.OWNER.PLAYER_TWO){
			moves.add(new Pair(xPos, yPos - 1));
		}		
	}	
	
}
