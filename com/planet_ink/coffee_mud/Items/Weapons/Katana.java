package com.planet_ink.coffee_mud.Items.Weapons;
import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;

public class Katana extends Sword
{
	public Katana()
	{
		super();
		myID=this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.')+1);
		name="a katana";
		displayText="a very ornate katana rests in the room.";
		miscText="";
		description="Just your typical, run-of-the-mill ninja sword--wrapped handle, steel blade, etc.";
		baseEnvStats().setAbility(0);
		baseEnvStats().setLevel(0);
		baseEnvStats.setWeight(4);
		baseEnvStats().setAttackAdjustment(0);
		baseEnvStats().setDamage(8);
		baseGoldValue=15;
		recoverEnvStats();
		weaponType=TYPE_SLASHING;
	}

	public Environmental newInstance()
	{
		return new Katana();
	}
}
