package org.trompgames.utils;

import java.util.ArrayList;

import org.trompgames.ddmc.DDMCHandler;

public class Keyboard {

	private DDMCHandler handler;
	private ArrayList<Integer> pressedKeys = new ArrayList<>();
	
	//Key codes example: KeyEvent.VK_Q <- same as 'q' being pressed
	
	public Keyboard(DDMCHandler handler) {
		this.handler = handler;
	}
	
	public void addPressedKey(int key) {
		if(!isPressedKey(key))
			pressedKeys.add(key);
	}
	
	public void removePressedKey(int key) {
		pressedKeys.remove((Integer) key);
	}
	
	public boolean isPressedKey(int key) {
		return pressedKeys.contains(key);
	}
	
	
	
	public ArrayList<Integer> getPressedKeys(){
		return pressedKeys;
	}
	
	
}
