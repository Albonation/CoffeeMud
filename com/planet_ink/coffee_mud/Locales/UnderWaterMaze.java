package com.planet_ink.coffee_mud.Locales;

import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.Sense;
import java.util.*;

public class UnderWaterMaze extends Maze
{
	public UnderWaterMaze()
	{
		super();
		myID=this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.')+1);
		baseEnvStats().setSensesMask(baseEnvStats().sensesMask()|Sense.CAN_BREATHE);
		baseEnvStats().setDisposition(baseEnvStats().disposition()|Sense.IS_SWIMMING);
		recoverEnvStats();
		domainType=Room.DOMAIN_OUTDOORS_UNDERWATER;
		domainCondition=Room.CONDITION_WET;
	}

	public Environmental newInstance()
	{
		return new UnderWaterMaze();
	}

	public void affectEnvStats(Environmental affected, EnvStats affectableStats)
	{
		super.affectEnvStats(affected,affectableStats);
		affectableStats.setDisposition(affectableStats.disposition()|Sense.IS_SWIMMING);
	}

	public boolean okAffect(Affect affect)
	{
		if(!super.okAffect(affect))
			return false;
		if((affect.targetMinor()==affect.TYP_FIRE)
		||(affect.targetMinor()==affect.TYP_GAS))
		{
			affect.source().tell("That won't work underwater.");
			return false;
		}
		return true;
	}
}
