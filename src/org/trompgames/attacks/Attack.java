package org.trompgames.attacks;

import java.util.Random;

public class Attack {
	
	protected String name;
	
	protected AttackShape shape;
	protected AttackType type;
	
	protected int range;
	
	protected int damageMin;
	protected int damageMax;
	
	protected double accuracy;
	protected double crit;
	
	public Attack(String name, AttackShape shape, AttackType type, int range, int damageMin, int damageMax, double accuracy,
			double crit) {
		super();
		this.name = name;
		this.shape = shape;
		this.type = type;
		this.range = range;
		this.damageMin = damageMin;
		this.damageMax = damageMax;
		this.accuracy = accuracy;
		this.crit = crit;
	}
	
	public String getName() {
		return name;
	}

	public AttackShape getShape() {
		return shape;
	}

	public AttackType getType() {
		return type;
	}

	public int getRange() {
		return range;
	}

	public int getDamageMin() {
		return damageMin;
	}

	public int getDamageMax() {
		return damageMax;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public double getCrit() {
		return crit;
	}
	
	public int rollDamage() {
		Random random = new Random();
	
		int rand = random.nextInt(damageMax - damageMin + 1) + damageMin;
		
		return rand;		
	}
	
	public boolean rollCrit() {
		return Math.random() <= accuracy;
	}
	
}
