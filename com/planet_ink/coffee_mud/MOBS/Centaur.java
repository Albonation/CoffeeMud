package com.planet_ink.coffee_mud.MOBS;

import java.util.*;
import com.planet_ink.coffee_mud.utils.*;
import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
public class Centaur extends StdMOB
{

	public Centaur()
	{
		super();
		Random randomizer = new Random(System.currentTimeMillis());

		Username="a centaur";
		setDescription("A creature whose upper body is that of a man, and lower body that of a horse.");
		setDisplayText("A centaur gallops around...");
		setAlignment(750);
		setMoney(200);
		baseEnvStats.setWeight(600 + Math.abs(randomizer.nextInt() % 101));


		baseCharStats().setIntelligence(5 + Math.abs(randomizer.nextInt() % 6));
		baseCharStats().setStrength(12 + Math.abs(randomizer.nextInt() % 6));
		baseCharStats().setDexterity(9 + Math.abs(randomizer.nextInt() % 6));

		baseEnvStats().setDamage(7);
		baseEnvStats().setSpeed(2.0);
		baseEnvStats().setAbility(0);
		baseEnvStats().setLevel(4);
		baseEnvStats().setArmor(0);

		baseState.setHitPoints((Math.abs(randomizer.nextInt() % 10)*baseEnvStats().level()) + 5);

		addBehavior(CMClass.getBehavior("Mobile"));

		recoverMaxState();
		resetToMaxState();
		recoverEnvStats();
		recoverCharStats();
	}
	public Environmental newInstance()
	{
		return new Centaur();
	}
}
