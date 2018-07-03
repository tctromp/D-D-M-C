package org.trompgames.ddmc;

import org.trompgames.objects.DungeonTile;
import org.trompgames.objects.TestObject;
import org.trompgames.objects.Tile;
import org.trompgames.objects.Torch;
import org.trompgames.utils.Vector2;

public class DDMCMain {

	public static void main(String[] args) {
		
		DDMCHandler handler = new DDMCHandler();
		
		double scale = 3;
		
		Tile tile1 = new Tile(handler, DungeonTile.TOPLEFTWALL, new Vector2(100, 100));
		handler.addGameObject(tile1);
		
		int i = 0;
		
		for(i = 0; i < 6; i++) {
			Tile tile2 = new Tile(handler, DungeonTile.PLAINTILE, new Vector2(100 + i * 48, 244));
			handler.addGameObject(tile2);
		}		
		
		for(i = 1; i < 5; i++) {
			tile1 = new Tile(handler, DungeonTile.TOPMIDWALL, new Vector2(100 + i * 48, 100));
			handler.addGameObject(tile1);
		}
		
		tile1 = new Tile(handler, DungeonTile.TOPRIGHTWALL, new Vector2(100 + i * 48, 100));
		handler.addGameObject(tile1);
		
		
		for(i = 0; i < 6; i++) {
			tile1 = new Tile(handler, DungeonTile.BOT1, new Vector2(100 + i * 48, 291));
			handler.addGameObject(tile1);
		}
		
		
		Torch torch = new Torch(handler, new Vector2(100 + (i-2) * 48, 200));
		handler.addGameObject(torch);
		
		
		
		
		TestObject obj = new TestObject(handler, new Vector2(50, 50));		
		handler.addGameObject(obj);
		
	}
	
}
