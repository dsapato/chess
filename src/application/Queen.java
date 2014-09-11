package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Queen extends Piece {

	//Constructor
	public Queen(int x, int y, Game.OWNER playerNum){
		super(x,y);
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		try {
			if(playerNum == Game.OWNER.PLAYER_ONE)
				img = ImageIO.read(new File("resources/queenWhite.png"));
			else{
				img = ImageIO.read(new File("resources/queenBlack.png"));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//When selected, get all possible moves
	public void getMoves(){
		moves.clear();
		
		//Get all of the squares in this row
		moves.addAll(Game.map.getRowPositionsAt(yPos));
		
		//Get all of the squares in this column
		moves.addAll(Game.map.getColumnPositionsAt(xPos));
		
		//Get all of the squares in the diagonal bottom left to top right
		moves.addAll(Game.map.getDiagonalBLtoTR(xPos, yPos));
		
		//Get all of the squares in the other diagonal
		moves.addAll(Game.map.getDiagonalTLtoBR(xPos, yPos));
	}
		
	
}
