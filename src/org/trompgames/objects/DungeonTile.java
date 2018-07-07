package org.trompgames.objects;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.trompgames.utils.Tileset;
import org.trompgames.utils.Vector2;

public enum DungeonTile {	
	
	//144, 224.
	
	BLUEMAGE(144, 224, 16, 16),
	BAT(64, 144, 16, 16),
	
	TOPLEFTWALL(0, 0, 16, 16),
	TOPMIDWALL(16, 0, 16, 16),
	TOPRIGHTWALL(32, 0, 16, 16),
	LEFTWALL(0, 16, 16, 16),
	MIDWALL(16, 16, 16, 16),
	RIGHTWALL(32, 16, 16, 16),
	
	WALLFLOOR1(0, 32, 16, 16),
	WALLFLOOR2(16, 32, 16, 16),
	WALLFLOOR3(32, 32, 16, 16),

	BOT1(128, 0, 16, 16),
	BOT2(112, 16, 16, 16),
	BOT3(128, 16, 16, 16),

	TORCHOFF(116, 150, 8, 24, new Vector2(1, -3)),
	TORCHON1(132, 150, 8, 24, new Vector2(1, -3)),
	TORCHON2(148, 150, 8, 24, new Vector2(1, -3)),
	TORCHON3(164, 150, 8, 24, new Vector2(1, -3)),
	TORCHON4(180, 150, 8, 24, new Vector2(1, -3)),
	TORCHON5(196, 150, 8, 24, new Vector2(1, -3)),
	TORCHON6(212, 150, 8, 24, new Vector2(1, -3)),
	TORCHON7(228, 150, 8, 24, new Vector2(1, -3)),
	TORCHON8(244, 150, 8, 24, new Vector2(1, -3)),

	DARKLEFT(160, 112, 32, 32),
	DARKRIGHT(192, 112, 32, 32),

	PLAINTILE(32, 48, 16, 16),
	
	
	SMALLWHITEPARTICLE(81, 134, 3, 3),
	
	SMALLDARKGREENPARTICLE(85, 134, 3, 3),
	SMALLREDPARTICLE(89, 134, 3, 3),
	SMALLBROWNPARTICLE(93, 134, 3, 3),
	SMALLBLUEPARTICLE(97, 134, 3, 3),
	SMALLORANGEPARTICLE(101, 134, 3, 3),
	SMALLLIGHTBLUEPARTICLE(105, 134, 3, 3),
	SMALLLIGHTGREENPARTICLE(109, 134, 3, 3),
	SMALLYELLOWPARTICLE(113, 134, 3, 3),

	
	
	;

	private static Tileset tileset;
	
	static {
		
		try {
			tileset = new Tileset(ImageIO.read((new File("0x72_16x16DungeonTileset.v4.png"))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println("ERROR: Failed to load Dungeon Tileset");
		}
		
		
	}
	
	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private Vector2 offset = new Vector2(0, 0);

	private BufferedImage image = null;
		
	DungeonTile(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;		
	}
	
	DungeonTile(int x, int y, int width, int height, Vector2 offset){
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;	
		
		this.offset = offset;
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
	
	public Vector2 getOffset() {
		return offset;
	}
	
	
}
