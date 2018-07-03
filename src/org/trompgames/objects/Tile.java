package org.trompgames.objects;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.utils.Vector2;

public class Tile extends GameObject{

	private DungeonTile tile;
	
	//TODO: add collision object
	
	public Tile(DDMCHandler handler, DungeonTile tile, Vector2 loc) {
		super(handler);
		this.tile = tile;
		this.setImage(tile.getImage());
		this.setLocation(loc);
	}

	public DungeonTile getTile() {
		return tile;
	}

	@Override
	public void update() {
		
	}


	
	
	
}
