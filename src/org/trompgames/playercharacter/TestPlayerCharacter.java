package org.trompgames.playercharacter;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.objects.DungeonTile;
import org.trompgames.objects.GameObject;
import org.trompgames.objects.Particle;
import org.trompgames.objects.ParticleRing;
import org.trompgames.objects.TestObject;
import org.trompgames.utils.Keyboard;
import org.trompgames.utils.Layer;
import org.trompgames.utils.Mouse;
import org.trompgames.utils.MouseListener;
import org.trompgames.utils.Vector2;

public class TestPlayerCharacter extends PlayerCharacter{

	private ArrayList<Particle> particles = new ArrayList<>();
	
	private ParticleRing mana;
	
	public TestPlayerCharacter(DDMCHandler handler, Vector2 gridLoc, int health) {
		super(handler, gridLoc, health);

		this.setLayer(Layer.PLAYER.getLayerId());
		
		this.setImage(DungeonTile.BLUEMAGE.getImage());
		
		int totalParticles = 5;		
		double radius = 18 * 4; // 4 is the scale
		double speed = 1;
		
		//resetParticles(totalParticles, particleSpread, radius, speed);
		mana = new ParticleRing(this, totalParticles, radius, speed);
	}


	private long lastTime = System.currentTimeMillis();
	
	// Delay between movement inputs
	private long moveWaitMs = 175;
	
	private Vector2 startLoc;
	private Vector2 nextGridLoc;
	private Vector2 nextLoc;
	@Override
	public void update() {
				
		
		
		if(System.currentTimeMillis() < lastTime + moveWaitMs) {
			
			if(startLoc == null || nextGridLoc == null || nextLoc == null) return;
			
			this.setLocation(startLoc.lerp(nextLoc, 1.0 * (System.currentTimeMillis() - lastTime)/moveWaitMs));
			
			
			return;
		}
		
		if(nextGridLoc != null) {
			this.setGridLoc(nextGridLoc);
			nextGridLoc = null;
		}
		
		double deltaTime = this.getHandler().deltaTime();
		double speed = 100;
		
		
		Keyboard keyboard = this.getHandler().getKeyboard();
		
		
		
		double y = 0;
		if(keyboard.isPressedKey(KeyEvent.VK_S)) y++;
		if(keyboard.isPressedKey(KeyEvent.VK_W)) y--;
		
		double x = 0;
		if(keyboard.isPressedKey(KeyEvent.VK_D)) x++;
		if(keyboard.isPressedKey(KeyEvent.VK_A)) x--;
		
		if(y != 0 || x != 0) {	
			
			if(!this.getHandler().isWalkable(this.getGridLoc().clone().add(x, y))) return;
			
			startLoc = this.getLoc().clone();
			nextGridLoc = this.getGridLoc().clone().add(x, y);
			nextLoc = DDMCHandler.gridToScreenCords(nextGridLoc.getX(), nextGridLoc.getY());
			//this.setGridLoc(this.getGridLoc().add(x, y));
			lastTime = System.currentTimeMillis();
		}
				
	}
	
	@Override
	public void onClick() {
		System.out.println("Clicky Player");
	
		//int totalParticles = particles.size()-1;
		//resetParticles(totalParticles, 2 * Math.PI / totalParticles, 18*4, 1);
		
		//mana.addParticle();
		mana.removeParticle();
	}
}
