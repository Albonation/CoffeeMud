package com.planet_ink.coffee_mud.MOBS;

import java.util.*;
import com.planet_ink.coffee_mud.utils.*;
import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
public class Undead extends StdMOB
{

	public Undead()
	{
		super();
		Random randomizer = new Random(System.currentTimeMillis());

		Username="an undead being";
		setDescription("decayed and rotting, a dead body has been brought back to life...");
		setDisplayText("an undead thing slowly moves about.");
		setAlignment(0);
		setMoney(10);
		baseEnvStats.setWeight(30);
		setWimpHitPoint(0);

		baseCharStats().setMyRace(CMClass.getRace("Undead"));
		baseEnvStats().setDamage(8);

		baseEnvStats().setAbility(0);
		baseEnvStats().setLevel(1);
		baseEnvStats().setArmor(80);
		baseEnvStats().setSpeed(1.0);
		baseEnvStats().setDisposition(0); // disable infrared stuff
		baseEnvStats().setSensesMask(Sense.CAN_SEE_DARK);

		int hitPoints = 0;
		hitPoints += Math.abs(randomizer.nextInt()) % 18 + 1;
		hitPoints += Math.abs(randomizer.nextInt()) % 18 + 1;

		baseState.setHitPoints(hitPoints);

		addAbility(CMClass.getAbility("Skill_AllBreathing"));

		recoverMaxState();
		resetToMaxState();
		recoverEnvStats();
		recoverCharStats();
	}

	public Environmental newInstance()
	{
		return new Undead();
	}
}
