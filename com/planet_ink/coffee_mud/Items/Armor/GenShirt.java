package com.planet_ink.coffee_mud.Items.Armor;
import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.*;

public class GenShirt extends GenArmor
{
	public GenShirt()
	{
		super();
		myID=this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.')+1);
		name="a nice tunic";
		displayText="a plain tunic is folded neatly here.";
		description="It is a plain buttoned tunic.";
		properWornBitmap=Item.ON_TORSO;
		wornLogicalAnd=true;
		baseEnvStats().setArmor(2);
		baseEnvStats().setWeight(1);
		baseEnvStats().setAbility(0);
		baseGoldValue=1;
		recoverEnvStats();
		material=Armor.CLOTH;
	}
	public Environmental newInstance()
	{
		return new GenShirt();
	}
}
