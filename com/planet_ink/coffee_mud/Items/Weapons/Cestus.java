package com.planet_ink.coffee_mud.Items.Weapons;
import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;

public class Cestus extends StdWeapon
{
	public Cestus()
	{
		super();
		myID=this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.')+1);
		name="a mean looking cestus";
		displayText="a cestus is on the gound.";
		miscText="";
		description="It\\`s a glove covered in long spikes and blades.";
		baseEnvStats().setAbility(0);
		baseEnvStats().setLevel(0);
		baseEnvStats.setWeight(2);
		baseEnvStats().setAttackAdjustment(0);
		baseEnvStats().setDamage(4);
		baseGoldValue=5;
		recoverEnvStats();
		weaponType=Weapon.TYPE_PIERCING;
		weaponClassification=Weapon.CLASS_EDGED;

	}

	public Environmental newInstance()
	{
		return new Cestus();
	}

}
