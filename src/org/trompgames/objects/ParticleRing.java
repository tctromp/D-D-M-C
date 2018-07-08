package org.trompgames.objects;

import java.util.ArrayList;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.utils.Vector2;

public class ParticleRing {
	
	private GameObject anchor;
	private ArrayList<RingParticle> ringParticles = new ArrayList<>();
	private double speed;
	private double radius;
	
	
	private double particleSpread;
	
	public ParticleRing(GameObject anchor, int particles, double radius, double speed) {
		// create new particles around the anchor
		this.anchor = anchor;
		this.particleSpread = 2 * Math.PI / particles;
		this.radius = radius;
		this.speed = speed;
		
		for(int i = particles; i > 0; i--) {
			RingParticle rp = new RingParticle(this, anchor.handler, anchor.gridLoc, DungeonTile.SMALLBLUEPARTICLE);
			anchor.handler.addGameObject(rp);
		}
	}
	
	
	public GameObject getAnchor() {
		return anchor;
	}
	public double getParticleSpread() {
		return particleSpread;
	}
	public double getRadius() {
		return radius;
	}
	public double getSpeed() {
		return speed;
	}
	
	public void addParticle() {
		addParticle(1);
	}
	public void addParticle(int amount) {
		for(int i = amount; i > 0; i--) {
			RingParticle rp = new RingParticle(this, anchor.handler, anchor.gridLoc, DungeonTile.SMALLBLUEPARTICLE);
			anchor.handler.addGameObject(rp);
			
			updateParticleSpread();
			updateParticles();
		}
	}
	
	public void removeParticle() {
		removeParticle(1);
	}
	public void removeParticle(int amount) {
		for(int i = amount; i > 0; i--) {
			RingParticle rp = ringParticles.get(0);
			anchor.handler.removeGameObject(rp);
			ringParticles.remove(rp);
	
			updateParticleSpread();
			updateParticles();
		}
	}
	
	
	public void setParticleSpread(double newSpread) {
		particleSpread = newSpread;
	}
	public void updateParticleSpread() {
		particleSpread = 2 * Math.PI / ringParticles.size();;
	}
	
	public void updateParticles() {
		for(RingParticle rp : ringParticles) {
			rp.updateTheta(ringParticles.get(0).getTheta());
			rp.setLocation(rp.calculateParticlePosition());
		}
	}
	
	public static class RingParticle extends Particle {
		private double theta;
		private ParticleRing parent;
		
		public RingParticle(ParticleRing parent, DDMCHandler handler, Vector2 gridLoc, DungeonTile tile) {
			super(handler, gridLoc, tile);
			parent.ringParticles.add(this);
			this.parent = parent;
			theta = parent.ringParticles.indexOf(this) * parent.getParticleSpread();
			//System.out.println("THETA: "+theta);
			this.setLocation(this.calculateParticlePosition());
		}
		
		public void updateTheta(double startingTheta) {
			theta = startingTheta + parent.ringParticles.indexOf(this) * parent.getParticleSpread();
		}
		
		public double getTheta() {
			return theta;
		}
		
		@Override
		public void update() {
			if(!parent.ringParticles.contains(this)) System.out.println("WHY NOT DEAD");
			theta += handler.deltaTime() * parent.getSpeed();
			this.setLocation(calculateParticlePosition());
		}
		
		public Vector2 calculateParticlePosition() {
			double midX = parent.getAnchor().getLoc().getX() + 8 * 4 - 4; // Subtracting 4 (scale = 4) to center the particle
			double midY = parent.getAnchor().getLoc().getY() + 8 * 4 - 4;
			
			double targetX = midX + parent.getRadius() * Math.cos(theta);
			double targetY = midY + parent.getRadius() * Math.sin(theta); //theta2
			
			Vector2 target = new Vector2(targetX, targetY);
			
			double dist = this.getLoc().distance(target);
			
			//double alpha = 200 * handler.deltaTime() / dist;
			double alpha = handler.deltaTime() * Math.sqrt(dist);

			//System.out.println(alpha);
			if(alpha > 1) alpha = 1;

			Vector2 newLoc = this.getLoc().lerp(target, alpha);
			
			return newLoc;
		}
	}
}

