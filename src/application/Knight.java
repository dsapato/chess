package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Knight extends Piece {

	//Constructor
	public Knight(int x, int y, Game.OWNER owner){
		super(x,y, owner);
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		try {
			if(owner == Game.OWNER.PLAYER_ONE)
				img = ImageIO.read(new File("resources/knightWhite.png"));
			else{
				img = ImageIO.read(new File("resources/knightBlack.png"));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//When selected, get all possible moves
	public void getMoves(){
		moves.clear();
		
		//Get all tiles an L away
		moves.add(new Pair(xPos+2, yPos+1));
		moves.add(new Pair(xPos+1, yPos+2));
		
		moves.add(new Pair(xPos-2, yPos+1));
		moves.add(new Pair(xPos-1, yPos+2));
		
		moves.add(new Pair(xPos+2, yPos-1));
		moves.add(new Pair(xPos+1, yPos-2));
		
		moves.add(new Pair(xPos-2, yPos-1));
		moves.add(new Pair(xPos-1, yPos-2));
	}
	
}
