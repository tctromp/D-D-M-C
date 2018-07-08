package org.trompgames.playercharacter;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.objects.DungeonTile;
import org.trompgames.objects.Explosion;
import org.trompgames.objects.GameObject;
import org.trompgames.objects.Particle;
import org.trompgames.objects.ParticleRing;
import org.trompgames.objects.ParticleRing.RingParticle;
import org.trompgames.objects.TestObject;
import org.trompgames.utils.Keyboard;
import org.trompgames.utils.Layer;
import org.trompgames.utils.Mouse;
import org.trompgames.utils.MouseListener;
import org.trompgames.utils.Vector2;

public class TestPlayerCharacter extends PlayerCharacter{

	private ArrayList<Particle> particles = new ArrayList<>();
	private ParticleRing mana;
	public ParticleRing getMana() {
		return mana;
	}
	
	public TestPlayerCharacter(DDMCHandler handler, Vector2 gridLoc, int health) {
		super(handler, gridLoc, health);

		this.setLayer(Layer.PLAYER.getLayerId());
		
		this.setImage(DungeonTile.BLUEMAGE.getImage());
		
		int totalParticles = 5;		
		double radius = 18 * 4; // 4 is the scale
		double speed = 1;
		
		//resetParticles(totalParticles, particleSpread, radius, speed);
		mana = new ParticleRing(this, totalParticles, radius, speed);
		
		// Listens for clicking elsewhere for attacks and stuff
		PlayerCharacter pc = this;
		MouseListener listener = new MouseListener() {
			@Override
			public void onPress(Mouse mouse) {
				if((!mouse.getGridLoc().equals(pc.getGridLoc()) && handler.getSelected() != null && handler.getSelected().equals(pc)))
					useAbility();
			}

			@Override
			public void onRelease(Mouse mouse) {
				// TODO Auto-generated method stub
				
			}
		};		
		handler.getMouse().addMouseListener(listener);
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
		
		mana.addParticle(1);
		//mana.removeParticle();
		
		handler.select(this);
	}
	
	@Override
	public void useAbility() {
		System.out.println("Using ability");
		
		if(mana.getRingParticles().size() <= 0) {
			System.out.println("Not enough mana!");
			return;
		}
		
		// just messing around with particles
		RingParticle chosenOne = mana.getClosestParticle(handler.getMouse().getGridLoc());
		// TODO why is chosenOne null
		if(chosenOne == null) {
			System.out.println("Chosen one is null");
			chosenOne = mana.getRingParticles().get(0);
		}
		Vector2 location = DDMCHandler.screenToGridCords(chosenOne.calculateParticlePosition().getX(), chosenOne.calculateParticlePosition().getY());
		mana.removeParticle(chosenOne);
		
		Particle particle = (new Particle(handler, location, DungeonTile.SMALLORANGEPARTICLE) {
			Vector2 target = handler.getMouse().getLoc();
			
			@Override
			public void update() {
				double dist = this.getLoc().distance(target);
				
				//double alpha = 200 * handler.deltaTime() / dist;
				double alpha = handler.deltaTime() * Math.sqrt(dist);

				//System.out.println(alpha);
				if(alpha > 1) alpha = 1;
				alpha = 0.1;
				Vector2 newLoc = this.getLoc().lerp(target, alpha);
				
				this.setLocation(newLoc);
				
				
				if(dist < 10) {
					System.out.println("< 10 ");
					Explosion exp = new Explosion(handler, DDMCHandler.screenToGridCords(this.getLoc()));
					handler.addGameObject(exp);
					handler.removeGameObject(this);
				}
				
			}
			
		});
		handler.addGameObject(particle);
	}
}
