package org.trompgames.mapeditor;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

import org.trompgames.ddmc.DDMCHandler;
import org.trompgames.ddmc.DDMCMain;
import org.trompgames.objects.DungeonTile;
import org.trompgames.objects.Tile;
import org.trompgames.utils.Mouse;
import org.trompgames.utils.MouseListener;
import org.trompgames.utils.Vector2;

public class MapEditorFrame extends JFrame{

	private DDMCHandler handler;
	private JList<DungeonTile> list;
	private MouseListener mouseListener;
	private boolean clickEnabled = false;
	
	public MapEditorFrame(DDMCHandler handler) {
		this.handler = handler;		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		DefaultListModel<DungeonTile> listModel = new DefaultListModel<>();
		for(DungeonTile tile : DungeonTile.values()) {
			listModel.addElement(tile);

		}
		
		list = new JList<>(listModel);
		
		
		MapEditorFrame frame = this;
		
		mouseListener = new MouseListener() {

			@Override
			public void onPress(Mouse mouse) {
				if(!frame.clickEnabled()) return;
				
				Vector2 screenLoc = mouse.getLoc();
				
				Vector2 gridLoc = DDMCHandler.screenToGridCords(screenLoc.getX(), screenLoc.getY());
								
				Tile tile = new Tile(handler, list.getSelectedValue(), gridLoc, true);
				
				tile.setImageOffset(list.getSelectedValue().getOffset().clone().mult(4));
				
				handler.addGameObject(tile);
				
			}

			@Override
			public void onRelease(Mouse mouse) {
				
			}
			
		};
		
		handler.getMouse().addMouseListener(mouseListener);
		
		this.add(list);
		
		this.pack();
		this.setVisible(true);		
	}
	
	
	public boolean clickEnabled() {
		return clickEnabled;
	}
	
	public void clickEnabled(boolean clickEnabled) {
		this.clickEnabled = clickEnabled;
	}
	
	
	
	
	
}
