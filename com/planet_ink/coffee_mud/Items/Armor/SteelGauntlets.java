package com.planet_ink.coffee_mud.Items.Armor;
import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.*;

public class SteelGauntlets extends StdArmor
{
	public SteelGauntlets()
	{
		super();
		myID=this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.')+1);
		name="some steel gauntlets";
		displayText="a pair of steel gauntlets sit here.";
		description="They look like they're made of steel.";
		properWornBitmap=Item.ON_HANDS | Item.ON_LEFT_WRIST | Item.ON_RIGHT_WRIST;
		wornLogicalAnd=true;
		baseEnvStats().setArmor(3); // = $$$$ =
		baseEnvStats().setAbility(0);
		baseEnvStats().setWeight(5);
		baseGoldValue=20;
		recoverEnvStats();
		material=Armor.METAL;
	}
	public Environmental newInstance()
	{
		return new SteelGauntlets();
	}
}
