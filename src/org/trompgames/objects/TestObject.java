package org.trompgames.objects;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.ddmc.DDMCMain;
import org.trompgames.utils.Keyboard;
import org.trompgames.utils.Vector2;

public class TestObject extends GameObject{

	private ArrayList<Particle> particles = new ArrayList<>();
	
	public TestObject(DDMCHandler handler, Vector2 gridLoc) {
		super(handler, gridLoc);

		//this.setImage(handler.getTileset().getImage(82, 176, 13, 16));
		this.setImage(DungeonTile.BLUEMAGE.getImage());
		
		int totalParticles = 10;		
		double particleSpread = 2 * Math.PI / totalParticles;
		double radius = 18 * 4; // 4 is the scale
		double speed = 1;
		
		GameObject obj = this;	

		
		for(int i = 0; i < totalParticles; i++) {
			
			final int c = i;
			
			Particle p = (new Particle(handler, gridLoc, DungeonTile.SMALLBLUEPARTICLE) {
				
				double theta = c * particleSpread;
				double theta2 = c * (particleSpread + 2);

				@Override
				public void update() {
					
					theta += handler.deltaTime() * speed;
					theta2 += handler.deltaTime() * (speed + 3);

					double midX = obj.getLoc().getX() + 8 * 4 - 4;
					double midY = obj.getLoc().getY() + 8 * 4 - 4;
					
					double targetX = midX + radius * Math.cos(theta);
					double targetY = midY + radius * Math.sin(theta); //theta2
					
					Vector2 target = new Vector2(targetX, targetY);
					
					double dist = this.getLoc().distance(target);
					
					double alpha = (3) / dist;
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
			startLoc = this.getLoc().clone();
			nextGridLoc = this.getGridLoc().clone().add(x, y);
			nextLoc = DDMCMain.gridToScreenCords(nextGridLoc.getX(), nextGridLoc.getY(), 16, 64, 4);
			//this.setGridLoc(this.getGridLoc().add(x, y));
			lastTime = System.currentTimeMillis();
		}
		
		
		//this.setLocation(this.getLoc().add(x * deltaTime * speed, y * deltaTime * speed));
		
	}

}
