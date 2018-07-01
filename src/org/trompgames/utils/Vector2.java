package org.trompgames.utils;

public class Vector2 {

	private double x;
	private double y;
	
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public Vector2 add(double x, double y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Vector2 add(Vector2 v) {
		return add(v.x, v.y);
	}
	
	public Vector2 mult(double a) {
		this.x *= a;
		this.y *= a;
		return this;
	}
	
}
