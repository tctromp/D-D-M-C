package org.trompgames.objects;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.utils.Vector2;

public class Particle extends GameObject{

	public Particle(DDMCHandler handler, Vector2 gridLoc, DungeonTile tile) {
		super(handler, gridLoc, 2);
		this.setImage(tile.getImage());
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
