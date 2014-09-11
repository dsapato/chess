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
	
	protected Game.OWNER owner;
	protected boolean alive;
	
	public Piece(int x, int y, Game.OWNER owner){
		this.xPos = x;
		this.yPos = y;
		this.width = Game.map.getTileWidth();
		this.height = Game.map.getTileHeight();
		this.owner = owner;
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
		//Update map
		Game.map.setTileOwner(this.xPos, this.yPos, Game.OWNER.NONE);
		Game.map.setTileOwner(x,y,owner);
		
		//Move
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
