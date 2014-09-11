package application;

public class Queen extends Piece {

	//Constructor
	public Queen(int x, int y){
		super(x,y);
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
