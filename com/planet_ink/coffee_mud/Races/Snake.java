package com.planet_ink.coffee_mud.Races;

import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.*;
import java.util.*;

public class Snake extends StdRace
{
	public Snake()
	{
		super();
		myID=this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.')+1);
	}
	public boolean playerSelectable(){return false;}
	public void affectCharStats(MOB affectedMOB, CharStats affectableStats)
	{
		super.affectCharStats(affectedMOB, affectableStats);
		affectableStats.setStrength(8);
		affectableStats.setDexterity(8);
		affectableStats.setIntelligence(3);
	}
	public String arriveStr()
	{
		return "slithers in";
	}
	public String leaveStr()
	{
		return "slithers";
	}
	public void setWeight(MOB mob)
	{
		Random randomizer = new Random(System.currentTimeMillis());
		char gender = mob.baseCharStats().getGender();

		int weightModifier = Math.abs(randomizer.nextInt() % 10);
		mob.baseEnvStats().setWeight(25+weightModifier);
	}
}
