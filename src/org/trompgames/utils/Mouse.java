package org.trompgames.utils;

import java.util.ArrayList;

import org.trompgames.ddmc.DDMCHandler;

public class Mouse {
	
	private ArrayList<MouseListener> listeners = new ArrayList<MouseListener>();
	
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
	
	public Vector2 getGridLoc() {
		return DDMCHandler.screenToGridCords(loc.getX(), loc.getY());
	}
	
	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
		
		for(MouseListener listener : listeners) {
			if(isPressed)
				listener.onPress(this);
			else
				listener.onRelease(this);
		}
		
	}
	
	public void addMouseListener(MouseListener listener) {
		listeners.add(listener);
	}
	
	public void setLocation(Vector2 loc) {
		this.loc = loc;
	}
	
	
	
	
}
