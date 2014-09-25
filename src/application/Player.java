package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import application.Game.OWNER;
import application.Game.STATE;

/**
 * This keeps tracks of all Pieces, along with drawing and selection
 */
public class Player {

	//Who owns the piece
	private Game.OWNER playerNumber;
	
	/**
	 * Vector which holds what Pieces the player owns
	 */
	public Vector<Piece> pieces;
	//What piece we've clicked on
	private int selectedPieceIndex;
	
	//Image To highlight tiles
	private BufferedImage highlightImage;
	
	//Width and height of a tile in pixels
	private int width;
	private int height;
	
	
	/**
	 * Only constructor, adds pieces to board
	 * @param playerNum Which player this is, to set ownership of pieces
	 */
	public Player(Game.OWNER playerNum){
		this.playerNumber = playerNum;
		
		//Add pieces
		pieces = new Vector<Piece>();
		setUpPieces();
		
		width = Game.map.getTileWidth();
		height = Game.map.getTileHeight();
		highlightImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		try {
			highlightImage = ImageIO.read(new File("resources/selected.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Draw possible moves of selected piece
	 */
	public void drawMoves(){
		if(Game.turn == playerNumber && Game.state == STATE.MOVING){
			Piece selected = pieces.get(selectedPieceIndex);
			int sizeMoves = selected.moves.size();
			for(int i = 0; i < sizeMoves; i++){
				Zen.drawImage(highlightImage, selected.moves.get(i).x * width, Zen.getZenHeight() - ((selected.moves.get(i).y+1) * height), width, height);
			}
			
		}
	}
	
	/**
	 * Draw pieces on board, calls draw function of each piece
	 */
	public void drawPieces(){
		int size = pieces.size();
		for(int i = 0; i < size; i++){
			pieces.elementAt(i).draw();
		}
	}
	
	/**
	 * Add all pieces to pieces vector, creating each Piece object
	 */
	private void setUpPieces(){
		
		//Player One, bottom of board, pawns in row 1, rest row 0.
		if(playerNumber == Game.OWNER.PLAYER_ONE){
			for(int i = 0; i < Game.map.getWidth(); i++){
				pieces.add(new Pawn(i, 1, playerNumber));
			}
			
			pieces.add(new Rook  (2,0, playerNumber));
			pieces.add(new Rook  (9,0, playerNumber));
			pieces.add(new Knight(3,0, playerNumber));
			pieces.add(new Knight(8,0, playerNumber));
			pieces.add(new Bishop(4,0, playerNumber));
			pieces.add(new Bishop(7,0, playerNumber));
			pieces.add(new Queen (5,0, playerNumber));
			pieces.add(new King  (6,0, playerNumber));
			pieces.add(new Archbishop(1,  0, playerNumber));
			pieces.add(new Archbishop(10, 0, playerNumber));
			pieces.add(new Chancellor(0,  0, playerNumber));
			pieces.add(new Chancellor(11, 0, playerNumber));
		}
		
		//Player Two, top of board, pawns in row 6, rest row 7.
		else if(playerNumber == Game.OWNER.PLAYER_TWO){
			
			for(int i = 0; i < Game.map.getWidth(); i++){
				pieces.add(new Pawn(i, Game.map.getHeight()-2, playerNumber));
			}
			
			int yPlacement = Game.map.getHeight()-1;
			pieces.add(new Rook  (2, yPlacement, playerNumber));
			pieces.add(new Rook  (9, yPlacement, playerNumber));
			pieces.add(new Knight(3, yPlacement, playerNumber));
			pieces.add(new Knight(8, yPlacement, playerNumber));
			pieces.add(new Bishop(4, yPlacement, playerNumber));
			pieces.add(new Bishop(7, yPlacement, playerNumber));
			pieces.add(new Queen (5, yPlacement, playerNumber));
			pieces.add(new King  (6, yPlacement, playerNumber));
			pieces.add(new Archbishop(1,  yPlacement, playerNumber));
			pieces.add(new Archbishop(10, yPlacement, playerNumber));
			pieces.add(new Chancellor(0,  yPlacement, playerNumber));
			pieces.add(new Chancellor(11, yPlacement, playerNumber));
		}
		
		//Make sure the pieces are on the board
		checkPieces();
	}
	
	/**
	 * Make sure that Pieces are on the board, if not, remove them
	 */
	private void checkPieces(){
		for(int i = 0; i < pieces.size(); i++){
			if(!Game.map.inBounds(pieces.get(i).getX(), pieces.get(i).getX())){//If out of bounds, remove
				pieces.remove(i);
				i--;//All further pieces will be shifted left by remove(), so check this i again
			}
		}
	}
	
	/**
	 * Handle action of clicking at (x,y), depending on Game state and turn
	 * @param x x-coordinate on Map
	 * @param y y-coordinate on Map
	 */
	public void select(int x, int y){
		//Select a piece if hovering one
		if(Game.state == STATE.SELECTING){
			for(selectedPieceIndex = 0; selectedPieceIndex < pieces.size(); selectedPieceIndex++){
				if(pieces.get(selectedPieceIndex).getX() == x && pieces.get(selectedPieceIndex).getY() == y){
					pieces.get(selectedPieceIndex).getMoves();
					Game.state = STATE.MOVING;
					return;
				}
			}
		}
		
		//Select a move if over a valid one
		else if(Game.state == STATE.MOVING){
			Piece currPiece = pieces.get(selectedPieceIndex);
			for(int i = 0; i < currPiece.moves.size(); i++){
				Pair move = currPiece.moves.get(i);
				if(move.x == x && move.y == y){
					//Record the move
					Game.moveStack.push(new MoveRecord(playerNumber, new Pair(currPiece.getX(), currPiece.getY()), selectedPieceIndex));
					
					//Move the piece
					currPiece.moveTo(x, y);
					
					//Next turn
					Game.switchTurn();
					return;
				}
			}
			Game.state = STATE.SELECTING;
		}
	}
	
	/**
	 * Removes Piece at (x,y)
	 * @param x x-coordinate to remove Piece from
	 * @param y y-coordinate to remove Piece from
	 */
	public void killAt(int x, int y){
		for(int i = 0; i < pieces.size(); i++){
			if(pieces.get(i).getX() == x && pieces.get(i).getY() == y){
				pieces.get(i).xPos = -1;//Just place off of map in case we undo and bring the piece back
				pieces.get(i).yPos = -1;
				return;
			}
		}
	}
}
