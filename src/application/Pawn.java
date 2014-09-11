package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pawn extends Piece {

	//Constructor
	public Pawn(int x, int y, Game.OWNER playerNum){
		super(x,y);
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		try {
			if(playerNum == Game.OWNER.PLAYER_ONE)
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
		
		//Get tile in front and diagonal in front
		
	}	
	
}
