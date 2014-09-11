package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Map {
	private final int SIZE = 8;
	
	private int TILESIZE = Zen.getZenWidth()/SIZE; 
	
	private MapTile[][] mapTiles;

	
	//Constructor
	public Map(){
		mapTiles = new MapTile[SIZE][SIZE];
		for(int x = 0; x < SIZE; x++){
			for(int y = 0; y < SIZE; y++){
				mapTiles[x][y] = new MapTile();
			}
		}
	}
	
	public void update(){

	}
	
	public void draw(){
		for(int x = 0; x < SIZE; x++){
			for(int y = 0; y < SIZE; y++){
				if((x+y) % 2 == 1){//Switch on odd/even
					Zen.setColor(150, 150, 150);//Gray
					Zen.fillRect(x*TILESIZE, y*TILESIZE, TILESIZE, TILESIZE);
				}
				else{
					Zen.setColor(255, 255, 255);//White
					Zen.fillRect(x*TILESIZE, y*TILESIZE, TILESIZE, TILESIZE);
				}
			}
		}
	}
	
	//Return Vector of the positions of tiles in that row
	public Vector<Pair> getRowPositionsAt(int y){
		Vector<Pair> ret = new Vector<Pair>();
		//Square from 0 to SIZE-1
		for(int i = 0; i < SIZE; i++){
			ret.add(new Pair(i,y));
		}
		return ret;
	}
	
	//Return Vector of the positions of tiles in that column
	public Vector<Pair> getColumnPositionsAt(int x){
		Vector<Pair> ret = new Vector<Pair>();
		//Square from 0 to SIZE-1
		for(int i = 0; i < SIZE; i++){
			ret.add(new Pair(x,i));
		}
		return ret;
	}
	
	//Return Vector of the positions of tiles in that diagonal, bottom left to top right
	public Vector<Pair> getDiagonalBLtoTR(int x, int y){
		Vector<Pair> ret = new Vector<Pair>();
		
		//Get all upwards and to the right
		int currX = x;
		int currY = y;
		while(currX < SIZE && currY < SIZE){
			currX++;
			currY++;
			ret.add(new Pair(currX, currY));
		}
		
		//Get all below and to the left
		currX = x;
		currY = y;
		while(currX > 0 && currY > 0){
			currX--;
			currY--;
			ret.add(new Pair(currX, currY));
		}
		
		return ret;
	}
	
	//Return Vector of the positions of tiles in that diagonal, top left to bottom right
	public Vector<Pair> getDiagonalTLtoBR(int x, int y){
		Vector<Pair> ret = new Vector<Pair>();
		
		//Get all upwards and to the left
		int currX = x;
		int currY = y;
		while(currX > 0 && currY < SIZE){
			currX--;
			currY++;
			ret.add(new Pair(currX, currY));
		}
		
		//Get all below and to the right
		currX = x;
		currY = y;
		while(currX < SIZE && currY > 0){
			currX++;
			currY--;
			ret.add(new Pair(currX, currY));
		}
		
		return ret;
	}
	
	/****Getters and Setters****/
	public int getWidth(){
		return SIZE;
	}
	
	public int getHeight(){
		return SIZE;
	}
	
	public int getTileWidth(){
		return TILESIZE;
	}
	
	public int getTileHeight(){
		return TILESIZE;
	}
	
	public void setTileOwner(int x, int y, Game.OWNER owner){
		if(x > 0 && y > 0 && x < SIZE && y < SIZE){
			mapTiles[x][y].owner = owner;
		}
	}
}
