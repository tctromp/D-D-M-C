package org.trompgames.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.ddmc.DDMCMain;
import org.trompgames.utils.Vector2;

public abstract class GameObject {

	private Vector2 loc = new Vector2(0, 0);
	private Vector2 gridLoc;
	private boolean isVisible;
	
	private BufferedImage image;
	private double scale = 4;
		
	private DDMCHandler handler;
	
	private Vector2 imageOffset = new Vector2(0, 0);
	
	public GameObject(DDMCHandler handler, Vector2 gridLoc) {
		this.handler = handler;
		this.gridLoc = gridLoc;
		this.loc = DDMCMain.gridToScreenCords(gridLoc.getX(), gridLoc.getY(), 16, 64, 4);
	}
	
	public abstract void update();
	
	
	public void draw(Graphics2D g2d) {
		if(image == null) return;
		
		int x = (int) loc.getX();
		int y = (int) loc.getY();
		
		int xOffset = (int) (imageOffset.getX() * scale);
		int yOffset = (int) (imageOffset.getY() * scale);

		int width = image.getWidth();
		int height = image.getHeight();
		
		g2d.drawImage(image, x + xOffset, y + yOffset, (int) (width * scale), (int) (height * scale), null);
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
	
	public Vector2 getGridLoc() {
		return gridLoc;
	}
	
	public Vector2 getImageOffset() {
		return imageOffset;
	}
	
	public void setImageOffset(Vector2 imageOffset) {
		this.imageOffset = imageOffset;
	}
	
	public void setGridLoc(Vector2 gridLoc) {
		this.gridLoc = gridLoc;
		this.loc = DDMCMain.gridToScreenCords(gridLoc.getX(), gridLoc.getY(), 16, 64, 4);
	}
	
}
