package application;

public class King extends Piece {

	//Constructor
	public King(int x, int y){
		super(x,y);
	}
	
	//When selected, get all possible moves
	public void getMoves(){
		moves.clear();
		
		//Get all tiles one away
		moves.add(new Pair(xPos-1, yPos-1));
		moves.add(new Pair(xPos  , yPos-1));
		moves.add(new Pair(xPos+1, yPos-1));
		moves.add(new Pair(xPos+1, yPos  ));
		moves.add(new Pair(xPos+1, yPos+1));
		moves.add(new Pair(xPos  , yPos+1));
		moves.add(new Pair(xPos-1, yPos+1));
		moves.add(new Pair(xPos-1, yPos  ));
	}	
	
}
