package application;

/**
 * Keeps a history of moves made, used for undo
 * @author Danny
 *
 */
public class MoveRecord {

	//Which player made the move
	private Game.OWNER player;
	
	//Where the piece was
	private Pair movedFrom;
	//Index of piece so we can idenify which one
	private int movedPieceIndex;
	
	
	//If we killed a piece
	private boolean killed;
	//Index of piece, to identify which piece
	private int killedPieceIndex;
	//Where piece was killed
	private Pair killedPiecePosition;
	
	/**
	 * Only constructor, records who made the move, from where, and what piece
	 */
	public MoveRecord(Game.OWNER whatPlayer, Pair fromWhere, int pieceIndex){
		player = whatPlayer;
		movedFrom = fromWhere;
		movedPieceIndex = pieceIndex;
		killed = false;
	}
	
	public void addKill(int pieceIndex, Pair position){
		killed = true;
		killedPieceIndex = pieceIndex;
		killedPiecePosition = position;
	}
	
	public void undo(){
		if(player == Game.OWNER.PLAYER_ONE){//Player one's move
			Game.playerOne.pieces.get(movedPieceIndex).moveTo(movedFrom.x, movedFrom.y);
		}
		else{
			Game.playerTwo.pieces.get(movedPieceIndex).moveTo(movedFrom.x, movedFrom.y);
		}
		
		if(killed){//Bring piece back onto board
			if(player == Game.OWNER.PLAYER_ONE){//Player one killed player two's piece
				Game.playerTwo.pieces.get(killedPieceIndex).moveTo(killedPiecePosition.x, killedPiecePosition.y);
			}
			else{
				Game.playerOne.pieces.get(killedPieceIndex).moveTo(killedPiecePosition.x, killedPiecePosition.y);
			}
		}
		
		Game.switchTurn();
	}
}
