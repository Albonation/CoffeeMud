package com.planet_ink.coffee_mud.Items.Armor;
import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.*;

public class DrowChainMailArmor extends StdArmor
{
	public DrowChainMailArmor()
	{
		super();
		myID=this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.')+1);
		name="a suit of dark chain mail armor";
		displayText="a suit of chain mail armor made of dark material sits here.";
		description="This suit includes a fairly solid looking hauberk with leggings and a coif, all constructed from a strong, dark metal.";
        secretIdentity="A suit of Drow Chain Mail Armor";
		properWornBitmap=Item.ON_TORSO | Item.ON_ARMS | Item.ON_LEGS;
		wornLogicalAnd=true;
		baseEnvStats().setArmor(65);
		baseEnvStats().setWeight(60);
		baseEnvStats().setAbility(0);
		baseGoldValue=1500;
		recoverEnvStats();
		material=Armor.METAL;
	}
	public Environmental newInstance()
	{
		return new DrowChainMailArmor();
	}
}
