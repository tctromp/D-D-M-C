package org.trompgames.ddmc;

import java.util.ArrayList;

import org.trompgames.objects.GameObject;
import org.trompgames.swing.DDMCFrame;
import org.trompgames.utils.Keyboard;
import org.trompgames.utils.Mouse;

public class DDMCHandler {

	private DDMCFrame frame;
	private Keyboard keyboard;
	private Mouse mouse;
	private DDMCThread thread;
	private boolean debug = true;
	
	private ArrayList<GameObject> objects = new ArrayList<>();
	
	private ArrayList<GameObject> toRemove = new ArrayList<>();
	private ArrayList<GameObject> toAdd = new ArrayList<>();
	
	
	public DDMCHandler() {
		
		int width = 1920/2;
		int height = 1080/2;
		double fps = 60;
		
		
		frame = new DDMCFrame(this, width, height);
		
		thread = new DDMCThread(this, fps);
		thread.start();
		
	}
	
	public void update() {
		objects.removeAll(toRemove);
		objects.addAll(toAdd);
		
		
		for(GameObject obj : objects) {
			obj.update();
		}
	}
	
	public void addGameObject(GameObject obj) {
		toAdd.add(obj);
	}
	
	public void removeGameObject(GameObject obj) {
		toRemove.add(obj);
	}
	
	public DDMCFrame getFrame() {
		return frame;
	}
	
	public Mouse getMouse() {
		return mouse;
	}
	
	public DDMCThread getThread() {
		return thread;
	}
	
	public boolean debugMode() {
		return debug;
	}
	
	public Keyboard getKeyboard() {
		return keyboard;
	}
	
	public void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}
	
	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}
	
	public ArrayList<GameObject> getGameObjects(){
		return objects;
	}
	
	public double deltaTime() {
		return 1.0 * (System.currentTimeMillis() - thread.getLastTime()) / 1000.0;
	}
}
