package org.trompgames.mapeditor;

import org.trompgames.ddmc.DDMCHandler;

public class MapEditor {

	private DDMCHandler handler;
	private MapEditorFrame frame;
	
	
	public MapEditor(DDMCHandler handler) {
		this.handler = handler;
		this.frame = new MapEditorFrame(handler);
	
	}
	
}
