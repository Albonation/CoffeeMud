package com.planet_ink.coffee_mud.Items.Weapons;
import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;

public class DragonClaw extends Natural
{
	public DragonClaw()
	{
		super();
		myID=this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.')+1);
		name="a vicious dragons claw.";
		displayText="a Dragons Claw";
		miscText="";
		description="the claw of a dragon";
		baseEnvStats().setAbility(0);
		baseEnvStats().setLevel(0);
		baseEnvStats.setWeight(7);
		baseEnvStats().setAttackAdjustment(2);
		baseEnvStats().setDamage(8);
		recoverEnvStats();
		weaponType=TYPE_SLASHING;
		weaponClassification=Weapon.CLASS_NATURAL;
	}

	public Environmental newInstance()
	{
		return new DragonClaw();
	}
}
