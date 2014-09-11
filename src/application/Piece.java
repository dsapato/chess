package application;

import java.util.Vector;

public class Piece {
	protected int xPos;
	protected int yPos;
	
	protected Vector<Pair> moves;
	
	protected boolean alive;
	
	public Piece(int x, int y){
		this.xPos = x;
		this.yPos = y;
		this.alive = true;
		this.moves = new Vector<Pair>();
	}
	
	//Update, usually overridden
	public void update(){
		
	}
	
	//Draw, usually overridden
	public void draw(){
		
	}
	
	public void getMoves(){
		
	}
	
	public void moveTo(int x, int y){
		this.xPos = x;
		this.yPos = y;
	}	
}
