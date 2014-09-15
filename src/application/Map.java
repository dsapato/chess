package application;

import java.util.Vector;


public class Map {
	private final int SIZE = 12;
	
	private int TILESIZE = Zen.getZenWidth()/SIZE; 
	
	//Draw offset fixes a gap being drawn if the screen size isn't a multiple of SIZE
	private int drawOffset = Zen.getZenWidth() -  (Zen.getZenWidth()/SIZE*SIZE);
	
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
					Zen.fillRect(x*TILESIZE, y*TILESIZE + drawOffset, TILESIZE, TILESIZE);
				}
				else{
					Zen.setColor(255, 255, 255);//White
					Zen.fillRect(x*TILESIZE, y*TILESIZE + drawOffset, TILESIZE, TILESIZE);
				}
			}
		}
	}
	
	//Return Vector of the positions of tiles in that row
	public Vector<Pair> getRowPositionsAt(int x, int y, Game.OWNER owner){
		Vector<Pair> ret = new Vector<Pair>();
		//Square from 0 to SIZE-1
		for(int i = x-1; i >= 0; i--){
			if(checkTileOccupied(i,y)){//Occupied, stop
				if(mapTiles[i][y].owner != owner){//Enemy piece, include and stop
					ret.add(new Pair(i,y));
				}
				break;
			}
			ret.add(new Pair(i,y));
		}
		for(int i = x+1; i < SIZE; i++){
			if(checkTileOccupied(i,y)){//Occupied, stop
				if(mapTiles[i][y].owner != owner){//Enemy piece, include and stop
					ret.add(new Pair(i,y));
				}
				break;
			}
			ret.add(new Pair(i,y));
		}
		return ret;
	}
	
	//Return Vector of the positions of tiles in that column
	public Vector<Pair> getColumnPositionsAt(int x, int y, Game.OWNER owner){
		Vector<Pair> ret = new Vector<Pair>();
		//Square from 0 to SIZE-1
		for(int i = y-1; i >= 0; i--){
			if(checkTileOccupied(x,i)){//Occupied, stop
				if(mapTiles[x][i].owner != owner){//Enemy piece, include and stop
					ret.add(new Pair(x,i));
				}
				break;
			}
			ret.add(new Pair(x,i));
		}
		for(int i = y+1; i < SIZE; i++){
			if(checkTileOccupied(x,i)){//Occupied, stop
				if(mapTiles[x][i].owner != owner){//Enemy piece, include and stop
					ret.add(new Pair(x,i));
				}
				break;
			}
			ret.add(new Pair(x,i));
		}
		return ret;
	}
	
	//Return Vector of the positions of tiles in that diagonal, bottom left to top right
	public Vector<Pair> getDiagonalBLtoTR(int x, int y, Game.OWNER owner){
		Vector<Pair> ret = new Vector<Pair>();
		
		//Get all upwards and to the right
		int currX = x;
		int currY = y;
		while(currX < SIZE-1 && currY < SIZE-1){
			currX++;
			currY++;
			if(checkTileOccupied(currX,currY)){//Occupied, stop
				if(mapTiles[currX][currY].owner != owner){//Enemy piece, include and stop
					ret.add(new Pair(currX,currY));
				}
				break;
			}
			ret.add(new Pair(currX, currY));
		}
		
		//Get all below and to the left
		currX = x;
		currY = y;
		while(currX > 0 && currY > 0){
			currX--;
			currY--;
			if(checkTileOccupied(currX,currY)){//Occupied, stop
				if(mapTiles[currX][currY].owner != owner){//Enemy piece, include and stop
					ret.add(new Pair(currX,currY));
				}
				break;
			}
			ret.add(new Pair(currX, currY));
		}
		
		return ret;
	}
	
	//Return Vector of the positions of tiles in that diagonal, top left to bottom right
	public Vector<Pair> getDiagonalTLtoBR(int x, int y, Game.OWNER owner){
		Vector<Pair> ret = new Vector<Pair>();
		
		//Get all upwards and to the left
		int currX = x;
		int currY = y;
		while(currX > 0 && currY < SIZE-1){
			currX--;
			currY++;
			if(checkTileOccupied(currX,currY)){//Occupied, stop
				if(mapTiles[currX][currY].owner != owner){//Enemy piece, include and stop
					ret.add(new Pair(currX,currY));
				}
				break;
			}
			ret.add(new Pair(currX, currY));
		}
		
		//Get all below and to the right
		currX = x;
		currY = y;
		while(currX < SIZE-1 && currY > 0){
			currX++;
			currY--;
			if(checkTileOccupied(currX,currY)){//Occupied, stop
				if(mapTiles[currX][currY].owner != owner){//Enemy piece, include and stop
					ret.add(new Pair(currX,currY));
				}
				break;
			}
			ret.add(new Pair(currX, currY));
		}
		
		return ret;
	}
	
	public boolean inBounds(int x, int y){
		return x >= 0 && y >= 0 && x < SIZE && y < SIZE;
	}
	
	public boolean checkTileOccupied(int x, int y){
		if(mapTiles[x][y].owner == Game.OWNER.NONE){//Open tile, not occupied
			return false;
		}
		return true;
	}
	
	public boolean checkTileEnemy(int x, int y, Game.OWNER owner){
		return mapTiles[x][y].owner != Game.OWNER.NONE && mapTiles[x][y].owner != owner;
	}
	
	
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
		if(x >= 0 && y >= 0 && x < SIZE && y < SIZE){
			//Check for killing other piece
			if(mapTiles[x][y].owner == Game.OWNER.PLAYER_ONE){
				Game.playerOne.killAt(x,y);
			}
			else if(mapTiles[x][y].owner == Game.OWNER.PLAYER_TWO){
				Game.playerTwo.killAt(x,y);
			}
			
			//Adjust ownership of tile
			mapTiles[x][y].owner = owner;
		}
	}
}
