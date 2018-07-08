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
		return new Vector2(this.x + x, this.y + y);
	}
	
	public Vector2 add(Vector2 v) {
		return add(v.x, v.y);
	}
	
	public Vector2 sub(Vector2 v) {
		return this.add(v.mult(-1));
	}
	
	public Vector2 sub(double a) {
		return new Vector2(this.x - a, this.y - a);
	}
	
	
	public Vector2 add(double a) {
		return new Vector2(this.x + a, this.y + a);
	}
	
	
	public Vector2 normalize() {
		double dist = new Vector2(0, 0).distance(this);
		return new Vector2(this.x / dist, this.y / dist);
	}
	
	public Vector2 mult(double a) {
		return new Vector2(this.x * a, this.y * a);
	}
	
	public Vector2 pow(double a) {
		return new Vector2(Math.pow(x, a), Math.pow(y, a));
	}
	
	public Vector2 lerp(Vector2 v2, double alpha) {
		return this.mult(1-alpha).add(v2.clone().mult(alpha));
	}
	
	
	//NOTE: NOT TESTED	
	public Vector2 ease(Vector2 v2, double alpha) {	
		Vector2 normal = v2.clone().sub(this).normalize();
		double normalX = normal.getX();
		
		double xPowA = Math.pow(normalX, alpha);
		
		double normalY = xPowA / (xPowA + Math.pow(1 - x, alpha));
		
		return this.lerp(v2, normalY);		
	}
	
	
	
	public double distance(Vector2 v2) {
		return Math.sqrt(Math.pow(v2.x - x, 2) + Math.pow(v2.y - y, 2));
	}
	
	public Vector2 clone() {
		return new Vector2(x, y);
	}
	

	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Vector2)) return false;
		Vector2 v = (Vector2) obj;
		return v.getX() == x && v.getY() == y;
	}
	
	@Override
	public String toString() {
		return "X: " + x + " Y: " + y;
	}
	
}
