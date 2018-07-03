package org.trompgames.utils;

import java.awt.image.BufferedImage;

public class Tileset {
	
	private BufferedImage image;
	
	public Tileset(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage(int x, int y, int width, int height) {
		
		return image.getSubimage(x, y, width, height);
		
	}
	
}
