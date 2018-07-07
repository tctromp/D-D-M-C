package org.trompgames.objects;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.utils.Vector2;

public class Tile extends GameObject{

	private DungeonTile tile;
	
	//TODO: add collision object
	
	private boolean isWalkable;
	
	public Tile(DDMCHandler handler, DungeonTile tile, Vector2 gridLoc, boolean isWalkable) {
		super(handler, gridLoc);
		this.tile = tile;
		this.isWalkable = isWalkable;
		this.setImage(tile.getImage());
	}

	public DungeonTile getTile() {
		return tile;
	}

	public boolean isWalkable() {
		return isWalkable;
	}
	
	@Override
	public void update() {
		
	}


	
	
	
}
