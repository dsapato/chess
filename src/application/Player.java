package application;

import java.util.Vector;

public class Player {

	private Game.OWNER playerNumber;
	
	public Vector<Piece> pieces;
	public int selectedPieceIndex;
	
	//Constructors
	public Player(Game.OWNER playerNum){
		this.playerNumber = playerNum;
		
		//Add pieces
		pieces = new Vector<Piece>();
		setUpPieces();
	}
	
	//Draw pieces
	public void draw(){
		int size = pieces.size();
		for(int i = 0; i < size; i++){
			pieces.elementAt(i).draw();
		}
	}
	
	private void setUpPieces(){
		
		//Player One, bottom of board, pawns in row 1, rest row 0.
		if(playerNumber == Game.OWNER.PLAYER_ONE){
			for(int i = 0; i < Game.map.getWidth(); i++){
				pieces.add(new Pawn(i, 1, playerNumber));
			}
			
			pieces.add(new Rook  (0,0, playerNumber));
			pieces.add(new Rook  (7,0, playerNumber));
			pieces.add(new Knight(1,0, playerNumber));
			pieces.add(new Knight(6,0, playerNumber));
			pieces.add(new Bishop(2,0, playerNumber));
			pieces.add(new Bishop(5,0, playerNumber));
			pieces.add(new Queen (3,0, playerNumber));
			pieces.add(new King  (4,0, playerNumber));	
		}
		
		//Player Two, top of board, pawns in row 6, rest row 7.
		else if(playerNumber == Game.OWNER.PLAYER_TWO){
			for(int i = 0; i < Game.map.getWidth(); i++){
				pieces.add(new Pawn(i, 6, playerNumber));
			}
			
			pieces.add(new Rook  (0,7, playerNumber));
			pieces.add(new Rook  (7,7, playerNumber));
			pieces.add(new Knight(1,7, playerNumber));
			pieces.add(new Knight(6,7, playerNumber));
			pieces.add(new Bishop(2,7, playerNumber));
			pieces.add(new Bishop(5,7, playerNumber));
			pieces.add(new Queen (3,7, playerNumber));
			pieces.add(new King  (4,7, playerNumber));	
		}	
	}
	
	//Get piece at x,y
	public void select(int x, int y){
		for(selectedPieceIndex = 0; selectedPieceIndex < pieces.size(); selectedPieceIndex++){
			if(pieces.get(selectedPieceIndex).getX() == x && pieces.get(selectedPieceIndex).getY() == y){
				break;
			}
		}
	}
	
}
