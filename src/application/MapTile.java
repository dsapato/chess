package application;

/**
 * A simple object which makes up the Map, and stores who owns it
 */
public class MapTile {

	/**
	 * OWNER enumeration of who owns the tile
	 */	
	public Game.OWNER owner;
	
	/**
	 * Default constructor, sets owner to NONE
	 */	
	public MapTile(){
		owner = Game.OWNER.NONE;
	}

}
