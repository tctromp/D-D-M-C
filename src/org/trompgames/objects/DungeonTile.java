package org.trompgames.objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.trompgames.utils.Tileset;

public enum DungeonTile {	
	
	TOPLEFTWALL(0, 0, 16, 48),
	TOPMIDWALL(16, 0, 16, 48),
	TOPRIGHTWALL(32, 0, 16, 48),
	
	BOT1(128, 0, 16, 16),
	BOT2(112, 16, 16, 16),
	BOT3(128, 16, 16, 16),

	TORCHOFF(116, 150, 8, 24),
	TORCHON1(132, 150, 8, 24),
	TORCHON2(148, 150, 8, 24),
	TORCHON3(164, 150, 8, 24),
	TORCHON4(180, 150, 8, 24),
	TORCHON5(196, 150, 8, 24),
	TORCHON6(212, 150, 8, 24),
	TORCHON7(228, 150, 8, 24),
	TORCHON8(244, 150, 8, 24),

	
	PLAINTILE(32, 48, 16, 16)
	
	
	
	
	
	;

	private static Tileset tileset;
	
	static {
		
		try {
			tileset = new Tileset(ImageIO.read((new File("0x72_16x16DungeonTileset.v4.png"))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("ERROR: Failed to load Dungeon Tileset");
		}
		
		
	}
	
	private int x;
	private int y;
	
	private int width;
	private int height;

	private BufferedImage image = null;
		
	DungeonTile(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;		
	}
	
	public BufferedImage getImage() {
		if(image == null) {
			image = tileset.getImage(x, y, width, height);
		}
		return image;
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}
	
	
	
}
