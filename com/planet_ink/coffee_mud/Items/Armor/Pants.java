package com.planet_ink.coffee_mud.Items.Armor;
import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.*;

public class Pants extends StdArmor
{
	public Pants()
	{
		super();
		myID=this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.')+1);
		name="a pair of pants";
		displayText="a pair of pants lies here";
		description="a well tailored pair of travellors pants.";
		properWornBitmap=Item.ON_LEGS;
		wornLogicalAnd=false;
		baseEnvStats().setArmor(1);
		baseEnvStats().setWeight(1);
		baseEnvStats().setAbility(0);
		baseGoldValue=1;
		recoverEnvStats();
		material=Armor.CLOTH;
	}
	public Environmental newInstance()
	{
		return new Pants();
	}
}
