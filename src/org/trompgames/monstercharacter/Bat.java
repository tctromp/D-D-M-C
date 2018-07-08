package org.trompgames.monstercharacter;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.ddmc.DDMCMain;
import org.trompgames.objects.DungeonTile;
import org.trompgames.objects.GameObject;
import org.trompgames.utils.Vector2;

public class Bat extends MonsterCharacter{

	public Bat(DDMCHandler handler, Vector2 loc, int health) {
		super(handler, loc, health);
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
		
		this.setLocation(DDMCHandler.gridToScreenCords(x, y).add((amplitude + 2) * Math.cos(angle2), amplitude * Math.sin(angle)));
		
		
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		System.out.println("Clicky bat");
	}

	@Override
	public void useAbility() {
		// TODO Auto-generated method stub
		
	}

}
