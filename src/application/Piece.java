package application;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class Piece {
	protected int xPos;
	protected int yPos;
	
	protected int width;
	protected int height;
	
	protected BufferedImage img;
	
	protected Vector<Pair> moves;
	
	protected boolean alive;
	
	public Piece(int x, int y){
		this.xPos = x;
		this.yPos = y;
		this.width = Game.map.getTileWidth();
		this.height = Game.map.getTileHeight();
		this.alive = true;
		this.moves = new Vector<Pair>();
	}
	
	//Update, usually overridden
	public void update(){
		
	}
	
	//Draw, usually overridden
	public void draw(){
		Zen.drawImage(img, xPos * width, Zen.getZenHeight() - ((yPos+1) * height), width, height);
	}
	
	public void getMoves(){
		
	}
	
	public void moveTo(int x, int y){
		this.xPos = x;
		this.yPos = y;
	}
	
	public int getX(){
		return xPos;
	}
	public int getY(){
		return yPos;
	}
}
