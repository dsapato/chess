package application;

import java.awt.event.MouseEvent;

public class MapTile {
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean released;
	
	public MapTile(){
		
	}
	
	public boolean isHovered(){
		if(this.x + this.width > Zen.getMouseX() && this.x < Zen.getMouseX()){
			if(this.y + this.height > Zen.getMouseY() && this.y < Zen.getMouseY()){
				return true;
			}
		}
		return false;
	}	
	
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
}
