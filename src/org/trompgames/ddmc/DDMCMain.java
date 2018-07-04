package org.trompgames.ddmc;

import org.trompgames.objects.Bat;
import org.trompgames.objects.DungeonTile;
import org.trompgames.objects.TestObject;
import org.trompgames.objects.Tile;
import org.trompgames.objects.Torch;
import org.trompgames.utils.Vector2;

public class DDMCMain {

	public static void main(String[] args) {
		
		int width = 1920;
		int height = 1080;
		double fps = 60;
		
		double scale = 4;

		
		int gridWidth = (int) (width / (16 * scale));
		int gridHeight = (int) (height / (16 * scale));

		
		gridWidth = 28;
		gridHeight = 14; 
		
		DDMCHandler handler = new DDMCHandler(width, height, fps, gridWidth, gridHeight);
		
		//handler.setDebug(false);
		
		int offset = 64;
		int tileWidth = 16;
		
		
		System.out.println(gridWidth);
		System.out.println(gridHeight);
		
		//Torch torch = new Torch(handler, gridToScreenCords(0, 0));
		//handler.addGameObject(torch);
		
		
		
		

		
		
		Tile tile = new Tile(handler, DungeonTile.TOPLEFTWALL, new Vector2(4, 4));
		handler.addGameObject(tile);
		
		int i = 0;
		int j = 0;

		for(i = 0; i < 9; i++) {		
			tile = new Tile(handler, DungeonTile.TOPMIDWALL, new Vector2(5 + i, 4));
			handler.addGameObject(tile);
		}
		
		tile = new Tile(handler, DungeonTile.TOPRIGHTWALL, new Vector2(5 + i, 4));
		handler.addGameObject(tile);
		
		for(i = 0; i < 11; i++) {
			tile = new Tile(handler, DungeonTile.MIDWALL, new Vector2(4 + i, 5));
			handler.addGameObject(tile);
		}
		
		for(i = 0; i < 11; i++) {
			tile = new Tile(handler, DungeonTile.WALLFLOOR1, new Vector2(4 + i, 6 + j));
			handler.addGameObject(tile);
		}
		
		for(j = 1; j < 3; j++) {
			for(i = 0; i < 11; i++) {
				tile = new Tile(handler, DungeonTile.PLAINTILE, new Vector2(4 + i, 6 + j));
				handler.addGameObject(tile);
			}
		}
		
		
		
		for(i = 0; i < 11; i++) {
			tile = new Tile(handler, DungeonTile.BOT1, new Vector2(4 + i, 6 + j));
			handler.addGameObject(tile);
		}
		

		
		Bat bat = new Bat(handler, new Vector2(12, 7));
		handler.addGameObject(bat);
		
		Torch torch = new Torch(handler, new Vector2(14, 6));
		handler.addGameObject(torch);
		
		torch = new Torch(handler, new Vector2(4, 6));
		handler.addGameObject(torch);
		
		TestObject obj = new TestObject(handler, new Vector2(5, 7));		
		handler.addGameObject(obj);

	}
	
	public static Vector2 gridToScreenCords(double x, double y, int tileWidth, int offset, double scale) {
		return new Vector2(offset + x * tileWidth * scale, offset + y * tileWidth * scale);
	}
	
	public static Vector2 screenToGridCords(double x, double y, int tileWidth, int offset, double scale) {
		return new Vector2((int) (x - offset) / (tileWidth * scale), (int) (y - offset) / (tileWidth * scale));
	}
	
	
}
