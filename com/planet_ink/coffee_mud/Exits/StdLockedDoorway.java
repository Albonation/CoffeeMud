package com.planet_ink.coffee_mud.Exits;
import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.*;
import java.util.*;

public class StdLockedDoorway extends StdExit
{
	public StdLockedDoorway()
	{
		super();
		myID=this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.')+1);
		name="a door";
		description="An ordinary wooden door with swinging hinges and a latch.";
		displayText="an open door, unlocked.";
		closedText="a closed door with a lock.";
		miscText="";
		hasADoor=true;
		isOpen=false;
		hasALock=true;
		isLocked=true;
		doorDefaultsClosed=true;
		doorDefaultsLocked=true;
		openDelayTicks=15;
	}
	public Environmental newInstance()
	{
		StdLockedDoorway newOne=new StdLockedDoorway();
		return newOne;
	}
}
