package org.trompgames.objects;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.utils.Mouse;
import org.trompgames.utils.MouseListener;
import org.trompgames.utils.Vector2;

public abstract class Character extends GameObject{

	protected int health;
	protected boolean isDead;
	
	//TODO: add ability classes
	
	public Character(DDMCHandler handler, Vector2 gridLoc, int health) {
		super(handler, gridLoc);
		// TODO Auto-generated constructor stub
		
		addMouseListener();
		
	}
	
	private void addMouseListener() {
		Character character = this;

		MouseListener listener = new MouseListener() {

			@Override
			public void onPress(Mouse mouse) {
				if(mouse.getGridLoc().equals(character.getGridLoc()))
					onClick();
			}
			
			@Override
			public void onRelease(Mouse mouse) {
				// TODO Auto-generated method stub
				
			}
			
		};		
		
		handler.getMouse().addMouseListener(listener);
	}
	
	public abstract void onClick();
	
	public abstract void useAbility();

	public int getHealth() {
		return health;
	}
	
	public boolean isDead() {
		return isDead;
	}
	
}
