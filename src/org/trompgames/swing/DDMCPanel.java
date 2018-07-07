package org.trompgames.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.ddmc.DDMCMain;
import org.trompgames.objects.GameObject;
import org.trompgames.utils.Mouse;
import org.trompgames.utils.Vector2;

public class DDMCPanel extends JPanel{

	private DDMCHandler handler;
	private Mouse mouse;
	private boolean isUpdating = false;
	
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
		
		if(!handler.gameLoopEnabled()) return;

		isUpdating = true;
		
		Graphics2D g2d = (Graphics2D) g;
		
		//g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		g2d.setColor(new Color(28, 17, 23));
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		if(handler.debugMode()) {
			g2d.setColor(Color.gray);
			
			int scale = 4;
			int offset = 64;
			for(int i = 0; i < handler.getGridWidth(); i++) {
				for(int j = 0; j < handler.getGridHeight(); j++) {
					g2d.drawRect(offset + i * 16 * scale, offset + j * 16 * scale, 16 * scale, 16 * scale);
				}
			}
		}
		
		for(GameObject obj : handler.getGameObjects()) {
			obj.draw(g2d);
		}
		
		
		if(handler.debugMode()) {
			g2d.setColor(Color.white);
			
			
			int x = (int) mouse.getLoc().getX();
			int y = (int) mouse.getLoc().getY();

			Vector2 grid = DDMCHandler.screenToGridCords(x, y);
			
			int gridX = (int) grid.getX();
			int gridY = (int) grid.getY();
			
			g2d.drawString("Mouse Pos: X = " + x + " Y= " + y, 5, 15);
			g2d.drawString("Mouse Grid: X = " + gridX + " Y= " + gridY, 5, 25);

			g2d.drawString("Mouse Clicked: " + mouse.isPressed(), 5, 35);
			
			g2d.drawString("Delta Time: " + handler.deltaTime(), 5, 45);
			
			
			
			g2d.drawString("FPS Time: " + String.format("%.2f", 1.0/handler.deltaTime()), 5, 55);

			String s = "[";
			
			for(int i : handler.getKeyboard().getPressedKeys()) {
				s += (i + " ");
			}
			s += ("]");
			
			g2d.drawString("Keys Pressed: " + s, 5, 65);

			
			
		}
		
		isUpdating = false;
		
	}
	
	public boolean isUpdating() {
		return isUpdating;
	}
	
}
