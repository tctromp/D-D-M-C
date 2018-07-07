package org.trompgames.ddmc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.trompgames.mapeditor.MapEditor;
import org.trompgames.objects.GameObject;
import org.trompgames.objects.Tile;
import org.trompgames.swing.DDMCFrame;
import org.trompgames.utils.Keyboard;
import org.trompgames.utils.Mouse;
import org.trompgames.utils.Tileset;
import org.trompgames.utils.Vector2;

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
	
	private boolean gameLoopEnabled = false;
	
	private MapEditor mapEditor;
	
	public static int TILEWIDTH = 16;
	public static int OFFSET = 64;
	public static double SCALE = 4;
	
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
		
		
		this.mapEditor = new MapEditor(this);
		
		
		thread = new DDMCThread(this, fps);
		thread.start();
		//this.startGameLoop();
	}
	
	public void startGameLoop() {
		gameLoopEnabled = true;
	}
	
	public boolean gameLoopEnabled() {
		return gameLoopEnabled;
	}
	
	public void update() {
		objects.removeAll(toRemove);
		
		for(GameObject obj : toAdd) {
			int i;
			for(i = 0; i < objects.size(); i++) {
				GameObject c = objects.get(i);
				//System.out.println(obj.getLayer());
				if(obj.getLayer() < c.getLayer()) break;
							
			}
			
			objects.add(i, obj);			
		}
		
		
		
		toAdd.clear();
		toRemove.clear();
		
		for(GameObject obj : objects) {
			obj.update();

		}
	}
	
	public boolean isWalkable(Vector2 gridLoc) {	
		for(GameObject obj : objects) {
			if(!(obj instanceof Tile)) continue;
			Tile tile = (Tile) obj;
			if(tile.isWalkable() && tile.getGridLoc().equals(gridLoc)) return true;
		}
		return false;
	}
	
	public static Vector2 gridToScreenCords(double x, double y) {
		return new Vector2(OFFSET + x * TILEWIDTH * SCALE, OFFSET + y * TILEWIDTH * SCALE);
	}
	
	public static Vector2 screenToGridCords(double x, double y) {
		return new Vector2((int) ((x - OFFSET) / (TILEWIDTH * SCALE)), (int) ((y - OFFSET) / (TILEWIDTH * SCALE)));
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
