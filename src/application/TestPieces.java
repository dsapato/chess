package application;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit Tests to check various functionality
 * @author Danny Sapato
 *
 */
public class TestPieces {

	/**
	 * Tests constructor of a new game piece, also checks movement of pieces
	 */
	@Test
	public void testNewPieceAndMovement() {
		Game g = new Game();
		
		Piece piece = new Piece(2, 4, Game.OWNER.PLAYER_ONE);
		assertEquals(2, piece.getX());
		assertEquals(4, piece.getY());
		assertEquals(Game.OWNER.PLAYER_ONE, piece.owner);
		assertTrue(piece.firstMove);
		
		piece.moveTo(4,3);
		assertEquals(4, piece.getX());
		assertEquals(3, piece.getY());
	}
	
	/**
	 * Makes sure that a new pawn can move one or two blocks ahead
	 */
	@Test
	public void testPawn() {
		Game g = new Game();
		
		Piece piece = new Pawn(2, 3, Game.OWNER.PLAYER_ONE);
		piece.getMoves();
		assertEquals(2, piece.moves.size());
		assertEquals(5, piece.moves.get(0).y);
		assertEquals(4, piece.moves.get(1).y);
	}
	
	/**
	 * Tests creation of maps, including row getting and bound checking
	 */
	@Test
	public void testMap() {
		Game g = new Game();
		Game.newGame();
		
		Map map = new Map();
		assertEquals(11, map.getRowPositionsAt(4, 4, Game.OWNER.PLAYER_ONE).size());//12 spots per row, minus the tile its on
		assertFalse(map.checkTileOccupied(5, 4));
		
		assertFalse(map.inBounds(100, -3 ));
		assertFalse(map.inBounds(100, 100));
		assertFalse(map.inBounds(0  , -3 ));
		assertTrue (map.inBounds(3  , 3  ));
	}
	
	/**
	 * Creates a game, moves the leftmost pawn, and then undoes the move
	 */
	@Test
	public void testUndo(){
		Game g = new Game();
		Game.newGame();
	
		//Simulate moving PlayerOnes leftmost pawn
		Game.playerOne.select(0, 1);//Click pawn
		assertEquals(2, Game.playerOne.pieces.get(0).moves.size());
		
		Game.playerOne.select(0, 3);//Two moves forward
		
		assertTrue(g.map.checkTileEnemy(0, 3, Game.OWNER.PLAYER_TWO));//Check if player two has an enemy where we moved the pawn
		
		Game.undoMove();
		
		assertFalse(g.map.checkTileOccupied(0, 3));//Should be gone now
	}
	
	/**
	 * Creates a game, moves the left pawn, ending the turn. Then creates a new game, making sure the piece is back and turn reset.s
	 */
	@Test
	public void testNewGame(){
		Game g = new Game();
		Game.newGame();
	
		//Simulate moving PlayerOnes leftmost pawn
		Game.playerOne.select(0, 1);//Click pawn
		assertEquals(2, Game.playerOne.pieces.get(0).moves.size());
		
		Game.playerOne.select(0, 3);//Two moves forward
		assertTrue(g.map.checkTileEnemy(0, 3, Game.OWNER.PLAYER_TWO));//Check if player two has an enemy where we moved the pawn
		
		Game.newGame();
		
		assertFalse(g.map.checkTileEnemy(0, 3, Game.OWNER.PLAYER_TWO));//Check if player two has an enemy where we moved the pawn
		assertTrue(g.turn == Game.OWNER.PLAYER_ONE);//Player ones turn again since new game
		
	}

}
