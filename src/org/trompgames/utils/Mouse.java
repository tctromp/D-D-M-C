package org.trompgames.utils;

public class Mouse {

	private boolean isPressed;
	private Vector2 loc;
	
	public Mouse() {
		isPressed = false;
		loc = new Vector2(0,0);
	}
	
	public boolean isPressed() {
		return isPressed;
	}
	
	public Vector2 getLoc() {
		return loc;
	}
	
	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}
	
	public void setLocation(Vector2 loc) {
		this.loc = loc;
	}
	
	
}
