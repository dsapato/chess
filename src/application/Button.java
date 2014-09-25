package application;

import java.awt.Color;
import java.awt.event.MouseEvent;

/**
 * A box with text that checks if hovered or clicked
 * @author Danny
 *
 */

public class Button{
	private int x;
	private int y;
	private int width;
	private int height;
	private String title;
	private Color color;
	private boolean released = true;

	/**
	 * Only constructor, needs position, size, and text
	 * @param xx x-position to place button
	 * @param yy y-position to place button
	 * @param w width of the button
	 * @param h height of the button
	 * @param title text content of the button
	 */
	public Button(int xx, int yy, int w, int h, String title){
		this.x = xx;
		this.y = yy;
		this.width = w;
		this.height = h;
		this.title = title;
		color = Color.WHITE;
	}

	/**
	 * Checks if mouse if over the button
	 * @return true if mouse over button, false otherwise
	 */
	public boolean isHovered(){
		if(this.x + this.width > Zen.getMouseX() && this.x < Zen.getMouseX()){
			if(this.y + this.height > Zen.getMouseY() && this.y < Zen.getMouseY()){
				color = Color.GRAY;
				return true;
			}
		}
		color = Color.WHITE;
		return false;
	}	
	
	/**
	 * Checks if user clicked the button element
	 * @return true if clicked, false otherwise
	 */
	public boolean isClicked(){
		if(isHovered()){
			if(Zen.getMouseState() == MouseEvent.MOUSE_RELEASED || Zen.getMouseState() == MouseEvent.MOUSE_CLICKED){
				released = true;
			}
			if(released && Zen.getMouseState() == MouseEvent.MOUSE_PRESSED){
				released = false;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Draws the button at its position and of its size
	 */
	public void draw(){
		Zen.setColor(color);
		Zen.fillRect(x, y, width, height);
		Zen.setColor(Color.BLACK);
		Zen.drawText(title, x, y + height*3/4);
	}
}
