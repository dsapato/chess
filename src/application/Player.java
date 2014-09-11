package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import application.Game.OWNER;
import application.Game.STATE;

public class Player {

	private Game.OWNER playerNumber;
	
	public Vector<Piece> pieces;
	public int selectedPieceIndex;
	
	//To highlight tiles
	private BufferedImage highlightImage;
	private int width;
	private int height;
	
	
	//Constructor
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
	
	//Draw pieces
	public void draw(){
		if(Game.turn == playerNumber && Game.state == STATE.MOVING){
			Piece selected = pieces.get(selectedPieceIndex);
			int sizeMoves = selected.moves.size();
			for(int i = 0; i < sizeMoves; i++){
				Zen.drawImage(highlightImage, selected.moves.get(i).x * width, Zen.getZenHeight() - ((selected.moves.get(i).y+1) * height), width, height);
			}
			
		}
		
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
					currPiece.moveTo(x, y);
					//Change turn
					if(Game.turn == OWNER.PLAYER_ONE){
						Game.turn = OWNER.PLAYER_TWO;
					}
					else if(Game.turn == OWNER.PLAYER_TWO){
						Game.turn = OWNER.PLAYER_ONE;
					}
					Game.state = STATE.SELECTING;
					return;
				}
			}
			Game.state = STATE.SELECTING;
		}
	}	
}
