package com.planet_ink.coffee_mud.Items.Weapons;
import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;

public class Stiletto extends Dagger
{
	public Stiletto()
	{
		super();
		myID=this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.')+1);
		name="a cool stiletto";
		displayText="a stiletto is in the corner.";
		miscText="";
		description="A dagger, more or less, with a long slender blade and sharp point.";
		baseEnvStats().setAbility(0);
		baseEnvStats().setLevel(0);
		baseEnvStats.setWeight(1);
		baseEnvStats().setAttackAdjustment(0);
		baseEnvStats().setDamage(3);
		baseGoldValue=1;
		recoverEnvStats();
		weaponType=TYPE_PIERCING;
	}

	public Environmental newInstance()
	{
		return new Stiletto();
	}

}
