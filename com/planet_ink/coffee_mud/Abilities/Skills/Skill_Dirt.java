package com.planet_ink.coffee_mud.Abilities.Skills;
import com.planet_ink.coffee_mud.Abilities.StdAbility;
import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.*;
import java.util.*;

public class Skill_Dirt extends StdAbility
{
	boolean doneTicking=false;
	public String ID() { return "Skill_Dirt"; }
	public String name(){ return "Dirt";}
	public String displayText(){ return "(Dirt in your eyes)";}
	protected int canAffectCode(){return CAN_MOBS;}
	protected int canTargetCode(){return CAN_MOBS;}
	public int quality(){return Ability.MALICIOUS;}
	private static final String[] triggerStrings = {"DIRT"};
	public String[] triggerStrings(){return triggerStrings;}
	public int classificationCode(){return Ability.SKILL;}
	public int maxRange(){return 1;}
	public Environmental newInstance(){	return new Skill_Dirt();}
	public int usageType(){return USAGE_MOVEMENT;}

	public void affectEnvStats(Environmental affected, EnvStats affectableStats)
	{
		super.affectEnvStats(affected,affectableStats);
		if(!doneTicking)
			affectableStats.setSensesMask(affectableStats.sensesMask()|EnvStats.CAN_NOT_SEE);
	}

	public boolean okMessage(Environmental myHost, CMMsg msg)
	{
		if((affected==null)||(!(affected instanceof MOB)))
			return true;

		MOB mob=(MOB)affected;

		if((doneTicking)&&(msg.amISource(mob)))
			unInvoke();
		return true;
	}

	public void unInvoke()
	{
		if((affected==null)||(!(affected instanceof MOB)))
			return;
		MOB mob=(MOB)affected;

		super.unInvoke();

		if(canBeUninvoked())
			mob.tell("You can see again!");
	}

	public boolean invoke(MOB mob, Vector commands, Environmental givenTarget, boolean auto)
	{
		MOB target=this.getTarget(mob,commands,givenTarget);
		if(target==null) return false;


		if((mob.location().domainConditions()==Room.CONDITION_WET)
		 ||(mob.location().domainType()==Room.DOMAIN_OUTDOORS_AIR)
		 ||(mob.location().domainType()==Room.DOMAIN_OUTDOORS_CITY)
		 ||(mob.location().domainType()==Room.DOMAIN_OUTDOORS_SPACEPORT)
		 ||(mob.location().domainType()==Room.DOMAIN_OUTDOORS_UNDERWATER)
		 ||(mob.location().domainType()==Room.DOMAIN_OUTDOORS_WATERSURFACE)
		 ||(mob.location().domainType()==Room.DOMAIN_INDOORS_AIR)
		 ||(mob.location().domainType()==Room.DOMAIN_INDOORS_UNDERWATER)
		 ||(mob.location().domainType()==Room.DOMAIN_INDOORS_WATERSURFACE)
		 ||(mob.location().domainType()==Room.DOMAIN_INDOORS_MAGIC)
		 ||(mob.location().domainType()==Room.DOMAIN_INDOORS_STONE)
		 ||(mob.location().domainType()==Room.DOMAIN_INDOORS_METAL)
		 ||(mob.location().domainType()==Room.DOMAIN_INDOORS_CAVE)
		 ||(mob.location().domainType()==Room.DOMAIN_INDOORS_WOOD))
		{
			mob.tell("There's no dirt here to kick!");
			return false;
		}

		if((!auto)&&(mob.charStats().getBodyPart(Race.BODY_FOOT)<=0))
		{
			mob.tell("You need feet to kick.");
			return false;
		}

		if((!auto)&&(target.charStats().getBodyPart(Race.BODY_EYE)==0))
		{
			mob.tell(target.name()+" has no eyes, and would not be affected.");
			return false;
		}

		if(!super.invoke(mob,commands,givenTarget,auto))
			return false;

		boolean success=profficiencyCheck(mob,-(target.charStats().getStat(CharStats.DEXTERITY)*3),auto);

		if(success)
		{
			FullMsg msg=new FullMsg(mob,target,this,CMMsg.MSK_MALICIOUS_MOVE|CMMsg.TYP_JUSTICE|(auto?CMMsg.MASK_GENERAL:0),auto?"Dirt flies at <T-NAME>!":"^F<S-NAME> kick(s) dirt at <T-NAMESELF>.^?");
			if(mob.location().okMessage(mob,msg))
			{
				mob.location().send(mob,msg);
				mob.location().show(target,null,CMMsg.MSG_OK_VISUAL,"<S-NAME> <S-IS-ARE> blinded!");
				maliciousAffect(mob,target,3,-1);
			}
		}
		else
			return maliciousFizzle(mob,target,"<S-NAME> attempt(s) to kick dirt at <T-NAMESELF>, but miss(es).");
		return success;
	}
}
