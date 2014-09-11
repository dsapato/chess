package application;

public class Rook extends Piece {
	
	//Constructor
	public Rook(int x, int y){
		super(x,y);
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
