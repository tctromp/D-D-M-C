package org.trompgames.swing;

import java.awt.Graphics;

import javax.swing.JPanel;

import org.trompgames.ddmc.DDMCHandler;

public class DDMCPanel extends JPanel{

	private DDMCHandler handler;
	
	public DDMCPanel(DDMCHandler handler) {
		this.handler = handler;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		// Update screen here
		
	}
	
}
