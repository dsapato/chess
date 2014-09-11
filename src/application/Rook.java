package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rook extends Piece {
	
	
	//Constructor
	public Rook(int x, int y, Game.OWNER owner){
		super(x,y, owner);
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		try {
			if(owner == Game.OWNER.PLAYER_ONE)
				img = ImageIO.read(new File("resources/rookWhite.png"));
			else{
				img = ImageIO.read(new File("resources/rookBlack.png"));
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
	}
	
}
