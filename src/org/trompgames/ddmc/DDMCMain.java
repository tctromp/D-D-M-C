package org.trompgames.ddmc;

import org.trompgames.objects.TestObject;
import org.trompgames.utils.Vector2;

public class DDMCMain {

	public static void main(String[] args) {
		
		DDMCHandler handler = new DDMCHandler();
		
		TestObject obj = new TestObject(handler, new Vector2(50, 50));
		
		handler.addGameObject(obj);
		
	}
	
}
