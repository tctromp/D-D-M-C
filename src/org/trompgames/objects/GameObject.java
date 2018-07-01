package org.trompgames.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.utils.Vector2;

public abstract class GameObject {

	private Vector2 loc;
	private boolean isVisible;
	
	private BufferedImage image;
	private double scale = 1;
		
	private DDMCHandler handler;
	
	public GameObject(DDMCHandler handler) {
		this.handler = handler;
	}
	
	public abstract void update();
	
	
	public void draw(Graphics2D g2d) {
		if(image == null) return;
		
		int x = (int) loc.getX();
		int y = (int) loc.getY();
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		g2d.drawImage(image, x, y, (int) (width * scale), (int) (height * scale), null);
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public double getScale() {
		return scale;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public Vector2 getLoc() {
		return loc;
	}
	
	public void setLocation(Vector2 loc) {
		this.loc = loc;
	}
	
	public void setScale(double scale) {
		this.scale = scale;
	}
	
	public DDMCHandler getHandler() {
		return handler;
	}
	
}
