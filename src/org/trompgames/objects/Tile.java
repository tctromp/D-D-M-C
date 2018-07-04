package org.trompgames.objects;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.utils.Vector2;

public class Tile extends GameObject{

	private DungeonTile tile;
	
	//TODO: add collision object
	
	public Tile(DDMCHandler handler, DungeonTile tile, Vector2 gridLoc) {
		super(handler, gridLoc);
		this.tile = tile;
		this.setImage(tile.getImage());
	}

	public DungeonTile getTile() {
		return tile;
	}

	@Override
	public void update() {
		
	}


	
	
	
}
