package org.trompgames.monstercharacter;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.objects.Character;
import org.trompgames.utils.Layer;
import org.trompgames.utils.Vector2;

public abstract class MonsterCharacter extends Character{

	
	
	public MonsterCharacter(DDMCHandler handler, Vector2 gridLoc, int health) {
		super(handler, gridLoc, health);
		
		this.setLayer(Layer.MONSTER.getLayerId());
		// TODO Auto-generated constructor stub
	}


}
