package org.trompgames.swing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.utils.Keyboard;

public class DDMCFrame extends JFrame{

	private DDMCHandler handler;
	private DDMCPanel panel;
	private Keyboard keyboard;
	
	public DDMCFrame(DDMCHandler handler, int width, int height) {
		this.handler = handler;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		
		this.setSize(width, height);
		
		this.panel = new DDMCPanel(handler);
		this.add(panel);
		
		keyboard = new Keyboard(handler);
		handler.setKeyboard(keyboard);
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent event) {
				keyboard.addPressedKey(event.getKeyCode());
			}

			@Override
			public void keyReleased(KeyEvent event) {
				keyboard.removePressedKey(event.getKeyCode());
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
			
		});
		
		
		this.setVisible(true);
	}
	
	public DDMCPanel getPanel() {
		return panel;
	}
	
	
}
