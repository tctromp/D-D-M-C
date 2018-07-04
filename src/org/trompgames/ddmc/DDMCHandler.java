package org.trompgames.ddmc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.trompgames.objects.GameObject;
import org.trompgames.swing.DDMCFrame;
import org.trompgames.utils.Keyboard;
import org.trompgames.utils.Mouse;
import org.trompgames.utils.Tileset;

public class DDMCHandler {

	private DDMCFrame frame;
	private Keyboard keyboard;
	private Mouse mouse;
	private DDMCThread thread;
	private boolean debug = true;
	private Tileset tileset;
	
	
	private ArrayList<GameObject> objects = new ArrayList<>();
	
	private ArrayList<GameObject> toRemove = new ArrayList<>();
	private ArrayList<GameObject> toAdd = new ArrayList<>();
	
	private int gridWidth;
	private int gridHeight;
	
	public DDMCHandler(int width, int height, double fps, int gridWidth, int gridHeight) {
		
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;

		
		try {
			tileset = new Tileset(ImageIO.read((new File("0x72_16x16DungeonTileset.v4.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		frame = new DDMCFrame(this, width, height);
		
		thread = new DDMCThread(this, fps);
		thread.start();
		
	}
	
	public void update() {
		objects.removeAll(toRemove);
		objects.addAll(toAdd);
		
		toAdd.clear();
		toRemove.clear();
		
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
	
	public void setDebug(boolean debug) {
		this.debug = debug;
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
	
	public Tileset getTileset() {
		return tileset;
	}
	
	public int getGridWidth() {
		return gridWidth;
	}
	
	public int getGridHeight() {
		return gridHeight;
	}
}
