package org.trompgames.objects;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.utils.Keyboard;
import org.trompgames.utils.Vector2;

public class TestObject extends GameObject{

	public TestObject(DDMCHandler handler, Vector2 loc) {
		super(handler);
		this.setLocation(loc);

		this.setImage(handler.getTileset().getImage(82, 176, 13, 16));
		
		
		
		
	}
	
	@Override
	public void update() {
		
		double deltaTime = this.getHandler().deltaTime();
		double speed = 100;
		
		
		Keyboard keyboard = this.getHandler().getKeyboard();
		
		
		
		double y = 0;
		if(keyboard.isPressedKey(KeyEvent.VK_S)) y++;
		if(keyboard.isPressedKey(KeyEvent.VK_W)) y--;
		
		double x = 0;
		if(keyboard.isPressedKey(KeyEvent.VK_D)) x++;
		if(keyboard.isPressedKey(KeyEvent.VK_A)) x--;
		
		
		this.setLocation(this.getLoc().add(x * deltaTime * speed, y * deltaTime * speed));
		
	}

}
