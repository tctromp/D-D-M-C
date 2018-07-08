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
		
		mana.addParticle();
	}
	
	
	// Simple way to externally effect the number of shown particles (e)
	public void resetParticles(int totalParticles, double particleSpread, double radius, double speed) {
		for(GameObject obj : particles) {
			handler.removeGameObject(obj);
		}
		particles = new ArrayList<>(); // reset particle storage (e)
		GameObject obj = this;
		for(int i = 0; i < totalParticles; i++) {
			final int c = i;
			
			Particle p = (new Particle(handler, gridLoc, DungeonTile.SMALLBLUEPARTICLE) {
				
				double theta = c * particleSpread;
	
				@Override
				public void update() {
					
					theta += handler.deltaTime() * speed;

					double midX = obj.getLoc().getX() + 8 * 4 - 4; // Subtracting 4 (scale = 4) to center the particle
					double midY = obj.getLoc().getY() + 8 * 4 - 4;
					
					double targetX = midX + radius * Math.cos(theta);
					double targetY = midY + radius * Math.sin(theta); //theta2
					
					Vector2 target = new Vector2(targetX, targetY);
					
					double dist = this.getLoc().distance(target);
					
					//double alpha = 200 * handler.deltaTime() / dist;
					double alpha = handler.deltaTime() * Math.sqrt(dist) + 0.01;
	
					
					//System.out.println(alpha);
					if(alpha > 1) alpha = 1;
					
					Vector2 newLoc = this.getLoc().lerp(target, alpha);
					
					
					this.setLocation(newLoc);		
					
				}
				
			});
			particles.add(p);
			handler.addGameObject(p);
		}
	}
}
