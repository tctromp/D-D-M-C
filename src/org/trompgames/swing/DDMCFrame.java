package org.trompgames.swing;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.trompgames.ddmc.DDMCHandler;

public class DDMCFrame extends JFrame{

	private DDMCHandler handler;
	private DDMCPanel panel;
	
	public DDMCFrame(DDMCHandler handler, int width, int height) {
		this.handler = handler;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		
		this.setSize(width, height);
		
		this.panel = new DDMCPanel(handler);
		this.add(panel);
		
		
		
		this.setVisible(true);
	}
	
	public DDMCPanel getPanel() {
		return panel;
	}
	
}
