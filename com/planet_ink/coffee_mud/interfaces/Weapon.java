package com.planet_ink.coffee_mud.interfaces;

public interface Weapon extends Item
{
	// weapon types
	public final static int TYPE_NATURAL=0;
	public final static int TYPE_SLASHING=1;
	public final static int TYPE_PIERCING=2;
	public final static int TYPE_BASHING=3;
	public final static int TYPE_BURNING=4;
	public final static int TYPE_BURSTING=5;

	// weapon classifications
	public final static int CLASS_AXE=0;
	public final static int CLASS_BLUNT=1;
	public final static int CLASS_EDGED=2;
	public final static int CLASS_FLAILED=3;
	public final static int CLASS_HAMMER=4;
	public final static int CLASS_NATURAL=5;
	public final static int CLASS_POLEARM=6;
	public final static int CLASS_RANGED=7;
	public final static int CLASS_SWORD=8;
	public final static int CLASS_DAGGER=9;
	public final static int CLASS_STAFF=10;
	
	public int weaponType();
	public int weaponClassification();
	public void setWeaponType(int newType);
	public void setWeaponClassification(int newClassification);
	public String typeDescription();
	public String classifictionDescription();
}
