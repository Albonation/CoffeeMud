package com.planet_ink.coffee_mud.MOBS;

import java.util.*;
import com.planet_ink.coffee_mud.utils.*;
import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
public class WildEagle extends StdMOB
{

	public WildEagle()
	{
		super();
		Random randomizer = new Random(System.currentTimeMillis());

		Username="an eagle";
		setDescription("a majestic and very patriotic bird.");
		setDisplayText("A eagle gracefully glides upon the wind currents.");
		setAlignment(500);
		setMoney(0);
		setWimpHitPoint(0);

		baseEnvStats.setWeight(1 + Math.abs(randomizer.nextInt() % 15));


		baseCharStats().setIntelligence(4);
		baseCharStats().setStrength(11);
		baseCharStats().setDexterity(17);
		baseCharStats().setMyRace(CMClass.getRace("GreatBird"));

		baseEnvStats().setDamage(2);
		baseEnvStats().setSpeed(3.0);
		baseEnvStats().setAbility(0);
		baseEnvStats().setLevel(2);
		baseEnvStats().setArmor(60);
		baseEnvStats().setDisposition(baseEnvStats().disposition()|Sense.IS_FLYING);

		baseState.setHitPoints((Math.abs(randomizer.nextInt() % 4)*baseEnvStats().level()) + 4);

		recoverMaxState();
		resetToMaxState();
		recoverEnvStats();
		recoverCharStats();
	}
	public Environmental newInstance()
	{
		return new WildEagle();
	}
}
