package application;

/**
 * Custom pair object, simply holds and x and y integer
 */
public class Pair {
	/**
	 * x component, an integer
	 */	
	public int x;

	/**
	 * y component, an integer
	 */		
	public int y;
	
	/**
	 * Only constructor, simply sets internals to parameters
	 * @param xx Integer specifying the x of the Pair
	 * @param yy Integer specifying the y of the Pair
	 */	
	Pair(int xx, int yy){
		this.x = xx;
		this.y = yy;
	}
}
