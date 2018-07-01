package org.trompgames.objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.utils.Vector2;

public class TestObject extends GameObject{

	public TestObject(DDMCHandler handler, Vector2 loc) {
		super(handler);
		this.setLocation(loc);
		this.setScale(3);
		try {
			BufferedImage image = ImageIO.read(new File("Character.png"));
			this.setImage(image);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	@Override
	public void update() {
		
		double deltaTime = this.getHandler().deltaTime() / 10;
		
		this.setLocation(this.getLoc().add(deltaTime, deltaTime));
		
	}

}
