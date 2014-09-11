package application;

import java.util.Vector;

public class Player {

	private Game.OWNER playerNumber;
	
	public Vector<Piece> pieces;
	
	public Player(Game.OWNER playerNum){
		this.playerNumber = playerNum;
		
		//Add pieces
		pieces = new Vector<Piece>();
		setUpPieces();
	}
	
	private void setUpPieces(){
		int yPos = 0;//What row to put the pieces in
		
		//Player One, bottom of board, pawns in row 1, rest row 0.
		if(playerNumber == Game.OWNER.PLAYER_ONE){
			for(int i = 0; i < Game.map.getWidth(); i++){
				pieces.add(new Pawn(i, 1));
			}
			yPos = 0;
		}
		
		//Player Two, top of board, pawns in row 6, rest row 7.
		else if(playerNumber == Game.OWNER.PLAYER_TWO){
			for(int i = 0; i < Game.map.getWidth(); i++){
				pieces.add(new Pawn(i, 6));
			}
			yPos = 7;
		}
		
		pieces.add(new Rook  (0,yPos));
		pieces.add(new Rook  (7,yPos));
		pieces.add(new Knight(1,yPos));
		pieces.add(new Knight(6,yPos));
		pieces.add(new Bishop(2,yPos));
		pieces.add(new Bishop(5,yPos));
		pieces.add(new Queen (3,yPos));
		pieces.add(new King  (4,yPos));		
	}
	
}
