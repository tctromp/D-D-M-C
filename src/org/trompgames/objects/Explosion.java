package org.trompgames.objects;

import java.util.ArrayList;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.utils.Vector2;

public class Explosion extends Particle{
	
	public static final ArrayList<DungeonTile> tiles = new ArrayList<DungeonTile>();
	
	static {
		
		tiles.add(DungeonTile.SMALLEXPLOSION1);
		tiles.add(DungeonTile.SMALLEXPLOSION2);
		tiles.add(DungeonTile.SMALLEXPLOSION3);
		tiles.add(DungeonTile.SMALLEXPLOSION4);
		tiles.add(DungeonTile.SMALLEXPLOSION5);
		tiles.add(DungeonTile.SMALLEXPLOSION6);
		tiles.add(DungeonTile.SMALLEXPLOSION7);
		tiles.add(DungeonTile.SMALLEXPLOSION8);
		tiles.add(DungeonTile.SMALLEXPLOSION9);
		tiles.add(DungeonTile.SMALLEXPLOSION10);

		
	}

	public Explosion(DDMCHandler handler, Vector2 gridLoc, Vector2 loc) {
		super(handler, gridLoc, tiles.get(0));
		this.setLocation(loc);
		// TODO Auto-generated constructor stub
	}
	
	private int tile = 0;
	private long lastUpdate = System.currentTimeMillis();
	private double msPerUpdate = 50;
	
	@Override
	public void update() {
		if(System.currentTimeMillis() < lastUpdate + msPerUpdate) return;
		
		this.setImage(tiles.get(tile).getImage());
		tile++;
		if(tile >= tiles.size()) {
			//this.isVisible = false;
			handler.removeGameObject(this);
		}
		
		lastUpdate = System.currentTimeMillis();
	}

}
