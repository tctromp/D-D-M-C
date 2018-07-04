package org.trompgames.objects;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.ddmc.DDMCMain;
import org.trompgames.utils.Vector2;

public class Bat extends GameObject{

	public Bat(DDMCHandler handler, Vector2 loc) {
		super(handler, loc);
		this.setImage(DungeonTile.BAT.getImage());
	}

	double angle = 0;
	double angle2 = 0;
	
	@Override
	public void update() {
		
		double hoverSpeed = 2;
		double amplitude = 14;
		
		angle += this.getHandler().deltaTime() * hoverSpeed;
		angle2 += this.getHandler().deltaTime() * (hoverSpeed + 1);

		Vector2 gridLoc = this.getGridLoc();
		double x = gridLoc.getX();
		double y = gridLoc.getY();
		
		this.setLocation(DDMCMain.gridToScreenCords(x, y, 16, 64, 4).add((amplitude + 2) * Math.cos(angle2), amplitude * Math.sin(angle)));
		
		
	}

}
