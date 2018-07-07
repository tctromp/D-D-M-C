package org.trompgames.utils;

public enum Layer {

	
	PLAYER(3),
	ENTITY(2),
	MONSTER(1),
	TILE(0),
	
	;

	
	private int layerId;
	
	private Layer(int layerId) {
		this.layerId = layerId;
	}
	
	public int getLayerId() {
		return layerId;
	}
	
}
