package org.trompgames.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.objects.GameObject;
import org.trompgames.utils.Mouse;
import org.trompgames.utils.Vector2;

public class DDMCPanel extends JPanel{

	private DDMCHandler handler;
	private Mouse mouse;

	public DDMCPanel(DDMCHandler handler) {
		this.handler = handler;
		
		mouse = new Mouse();
		handler.setMouse(mouse);
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent event) {
				
			}

			@Override
			public void mouseEntered(MouseEvent event) {
				
			}

			@Override
			public void mouseExited(MouseEvent event) {
				
			}

			@Override
			public void mousePressed(MouseEvent event) {
				mouse.setPressed(true);

			}

			@Override
			public void mouseReleased(MouseEvent event) {
				mouse.setPressed(false);
			}			
			
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				
			}

			@Override
			public void mouseMoved(MouseEvent event) {
				mouse.setLocation(new Vector2(event.getX(), event.getY()));
			}			
		});
		
		
	}
	
	public Mouse getMouse() {
		return mouse;
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		
		// Update screen here
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		
		for(GameObject obj : handler.getGameObjects()) {
			obj.draw(g2d);
		}
		
		
		if(handler.debugMode()) {
			g2d.setColor(Color.BLACK);
			
			
			int x = (int) mouse.getLoc().getX();
			int y = (int) mouse.getLoc().getY();

			
			g2d.drawString("Mouse Pos: X = " + x + " Y= " + y, 5, 15);
			g2d.drawString("Mouse Clicked: " + mouse.isPressed(), 5, 25);
			
			String s = "[";
			
			for(int i : handler.getKeyboard().getPressedKeys()) {
				s += (i + " ");
			}
			s += ("]");
			
			g2d.drawString("Keys Pressed: " + s, 5, 35);

		}
		
		
		
	}
	
}
