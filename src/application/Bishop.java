package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bishop extends Piece {

	//Constructor
	public Bishop(int x, int y, Game.OWNER owner){
		super(x,y, owner);
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		try {
			if(owner == Game.OWNER.PLAYER_ONE)
				img = ImageIO.read(new File("resources/bishopWhite.png"));
			else{
				img = ImageIO.read(new File("resources/bishopBlack.png"));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//When selected, get all possible moves
	public void getMoves(){
		moves.clear();
		
		//Get all of the squares in the diagonal bottom left to top right
		moves.addAll(Game.map.getDiagonalBLtoTR(xPos, yPos, owner));
		
		//Get all of the squares in the other diagonal
		moves.addAll(Game.map.getDiagonalTLtoBR(xPos, yPos, owner));
	}
	
	
}
