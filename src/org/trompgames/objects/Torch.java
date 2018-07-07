package org.trompgames.objects;

import java.util.ArrayList;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.utils.Vector2;

public class Torch extends GameObject{

	public static final ArrayList<DungeonTile> tiles = new ArrayList<DungeonTile>();
	
	static {
		
		tiles.add(DungeonTile.TORCHON1);
		tiles.add(DungeonTile.TORCHON2);
		tiles.add(DungeonTile.TORCHON3);
		tiles.add(DungeonTile.TORCHON4);
		tiles.add(DungeonTile.TORCHON5);
		tiles.add(DungeonTile.TORCHON6);
		tiles.add(DungeonTile.TORCHON7);
		tiles.add(DungeonTile.TORCHON8);

		
	}
	
	public Torch(DDMCHandler handler, Vector2 loc) {
		super(handler, loc, 1);
		this.setImageOffset(tiles.get(0).getOffset().clone().mult(4));
	}

	private int tile = 0;
	private long lastUpdate = System.currentTimeMillis();
	private double msPerUpdate = 150;
	
	@Override
	public void update() {
		
		if(System.currentTimeMillis() < lastUpdate + msPerUpdate) return;
		
		this.setImage(tiles.get(tile).getImage());
		tile++;
		if(tile >= tiles.size()) tile = 0;
		
		lastUpdate = System.currentTimeMillis();
	}

}
