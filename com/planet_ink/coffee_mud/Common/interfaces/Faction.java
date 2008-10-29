package com.planet_ink.coffee_mud.Common.interfaces;

import com.planet_ink.coffee_mud.core.interfaces.*;
import com.planet_ink.coffee_mud.core.*;
import com.planet_ink.coffee_mud.Abilities.interfaces.*;
import com.planet_ink.coffee_mud.Areas.interfaces.*;
import com.planet_ink.coffee_mud.Behaviors.interfaces.*;
import com.planet_ink.coffee_mud.CharClasses.interfaces.*;
import com.planet_ink.coffee_mud.Commands.interfaces.*;
import com.planet_ink.coffee_mud.Common.DefaultFaction;
import com.planet_ink.coffee_mud.Common.interfaces.*;
import com.planet_ink.coffee_mud.Exits.interfaces.*;
import com.planet_ink.coffee_mud.Items.interfaces.*;
import com.planet_ink.coffee_mud.Locales.interfaces.*;
import com.planet_ink.coffee_mud.MOBS.interfaces.*;
import com.planet_ink.coffee_mud.Races.interfaces.*;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

/*
 * Copyright 2000-2008 Bo Zimmerman Licensed under the Apache License, Version
 * 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
/**
 * A Faction is an arbitrary numeric range, where different mobs/players can be
 * within that range, if they have the faction at all. Factions can be
 * programmatically set to change due to events that occur to/around the mob,
 * and adjust themselves relative to other factions. Subsets of the faction can
 * be given readable names for display to the user.
 * 
 * @see com.planet_ink.coffee_mud.MOBS.interfaces.MOB#fetchFaction(String)
 * @see com.planet_ink.coffee_mud.MOBS.interfaces.MOB#addFaction(String, int)
 */
public interface Faction extends CMCommon, MsgListener
{
    /**
     * Initializes a new faction with default values
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#initializeFaction(StringBuffer, String)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#factionID()
     * @param aname the factionID (and default name)
     */
    public void initializeFaction(String aname);

    /**
     * Initializes a new faction from a faction.ini properties formatted document, 
     * and a given new faction ID
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#initializeFaction(String)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#factionID()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#getINIDef(String, String)
     * @param file the ini properties style document
     * @param fID the new factionID
     */
    public void initializeFaction(StringBuffer file, String fID);

    /**
     * Returns the value of a given internal faction variable.  
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#TAG_NAMES
     * @param tag the tag to get the value of
     * @return the value of the given tag
     */
    public String getTagValue(String tag);

    /**
     * Retreives an entry for an ini properties definition document that describes this faction.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#getINIDef(String, String)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#initializeFaction(StringBuffer, String)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#TAG_NAMES
     * @param tag the tag to retreive a properties definition for
     * @param delimeter if the tag represents a list, this is the delimiter for entries.
     * @return the ini properties definition entry for the tag
     */
    public String getINIDef(String tag, String delimeter);

    /**
     * Returns a FactionData object for the given mob to store his faction
     * information in.  It will contain all the affects and behaviors, 
     * and other information necessary to maintain a relationship between
     * the given mob and this faction.
     * Any parameters should be set on the affects or behaviors before returning them.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionData
     * @param mob the mob to generate affects and behaviors for
     * @return a FactionData object with all the appropriate affects and behaviors
     */
    public FactionData makeFactionData(MOB mob);
    
    /**
     * Checks to see if the given mob has this faction.  Same as checking if
     * mob.fetchFaction(this.factionID())!=Integer.MAX_VALUE.
     * @param mob the mob to check
     * @return true if the mob has this faction, false otherwise
     */
    public boolean hasFaction(MOB mob);

    /**
     * Returns the given faction value, as a percent from minimum of the range
     * of this faction
     * @param faction the faction value to convert to a percent
     * @return the percentage value (0-100)
     */
    public int asPercent(int faction);

    /**
     * Returns the given value faction value, as a percent from average of the
     * range values of this faction.
     * @param faction the faction value to convert to a percent
     * @return the percentage value (0-100)
     */
    public int asPercentFromAvg(int faction);

    /**
     * Returns a random value within the valid range of this faction
     * @return a random valid value
     */
    public int randomFaction();

    /**
     * The official, unique faction id of this faction.  FactionIDs are usually
     * the CoffeeMud VFS path from the resources directory, of the properties ini
     * file that defines the faction.  The ID (and therefore the properties file location)
     * should not be changed once a faction is "deployed".
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#setFactionID(String)
     * @return the unique id of this faction
     */
    public String factionID();

    /**
     * Sets the official, unique faction id of this faction.  FactionIDs are usually
     * the CoffeeMud VFS path from the resources directory, of the properties ini
     * file that defines the faction.  The ID (and therefore the properties file location)
     * should not be changed once a faction is "deployed".
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#factionID()
     * @param newStr the new unique id of this faction
     */
    public void setFactionID(String newStr);

    /**
     * The friendly, displayable name of this faction.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#setName(String)
     * @return the name of this faction
     */
    public String name();

    /**
     * Sets the friendly, displayable name of this faction.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#name()
     * @param newStr the new name of this faction
     */
    public void setName(String newStr);

    /**
     * Gets the filename of a file, from the resources directory,
     * that is displayed to users when they are given the choice
     * of a starting value to this faction.  Requires more than
     * one choice range be available.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#findChoices(MOB)
     * @return the filename of the choice description file
     */
    public String choiceIntro();

    /**
     * Sets the filename of a file, from the resources directory,
     * that is displayed to users when they are given the choice
     * of a starting value to this faction.  Requires more than
     * one choice range be available.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#findChoices(MOB)
     * @param newStr the new filename of the choice description file
     */
    public void setChoiceIntro(String newStr);

    /**
     * Gets the lowest absolute range value 
     * @return the lowest absolute range value
     */
    public int minimum();

    /**
     * Gets the median absolute range value
     * @return the median absolute range value
     */
    public int middle();

    /**
     * Returns the difference between the highest and lowest range value
     * @return the difference between the highest and lowest range value
     */
    public int difference();

    /**
     * Returns the highest absolute range value
     * @return the highest absolute range value
     */
    public int maximum();

    /**
     * Returns the string code describing how a faction-holders experience
     * changes from killing another faction holder affect his own faction value.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#EXPAFFECT_NAMES
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#EXPAFFECT_DESCS
     * @return the string code for xp changes->faction changes
     */
    public String experienceFlag();

    /**
     * Sets the string code describing how a faction-holders experience
     * changes from killing another faction holder affect his own faction value.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#EXPAFFECT_NAMES
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#EXPAFFECT_DESCS
     * @param newStr the new string code for xp changes->faction changes
     */
    public void setExperienceFlag(String newStr);

    /**
     * Returns whether this faction is displayed in the player Score command.
     * @return true if displayed in Score, false otherwise
     */
    public boolean showInScore();

    /**
     * Sets whether this faction is displayed in the player Score command.
     * @param truefalse true if displayed in Score, false otherwise
     */
    public void setShowInScore(boolean truefalse);

    /**
     * Returns whether this factions value is shown in certain special admins commands.
     * @return true if displayed in special admin commands, false otherwise
     */
    public boolean showInSpecialReported();

    /**
     * Sets whether this factions value is shown in certain special admins commands.
     * @param truefalse true if displayed in special admin commands, false otherwise
     */
    public void setShowInSpecialReported(boolean truefalse);

    /**
     * Returns whether this factions value is shown as a line item in mob editors
     * @return true if displayed in mob editors, false otherwise
     */
    public boolean showInEditor();

    /**
     * Sets whether this factions value is shown as a line item in mob editors
     * @param truefalse true if displayed in mob editors, false otherwise
     */
    public void setShowInEditor(boolean truefalse);

    /**
     * Returns whether this factions value is shown in player Factions command
     * @return true if displayed in factions command, false otherwise
     */
    public boolean showInFactionsCommand();

    /**
     * Sets whether this factions value is shown in player Factions command
     * @param truefalse true if displayed in factions command, false otherwise
     */
    public void setShowInFactionsCommand(boolean truefalse);

    /**
     * Returns the default faction mask/value list, which is applied whenever
     * a Faction Change Event applies a Faction Add command.
     * A default faction mask/value is defined as a number, along with an
     * optional Zapper mask describing to whom the value is applied.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#changeEventKeys()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#setDefaults(Vector)
     * @see com.planet_ink.coffee_mud.Libraries.interfaces.MaskingLibrary
     * @return the default faction mask/value list
     */
    public Enumeration<String> defaults();

    /**
     * Returns the default faction value that applies to the given mob.
     * This method is called when a Faction Change event applies a 
     * Faction Add command. Returns Integer.MAX_VALUE if no default
     * value applies to this mob.
     * Each list item is a string.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#defaults()
     * @param mob the mob to find a default faction value for
     * @return the faction value that applies, or Integer.MAX_VALUE
     */
    public int findDefault(MOB mob);

    /**
     * Sets the default faction mask/value list, which is applied whenever
     * a Faction Change Event applies a Faction Add command.
     * A default faction mask/value is defined as a number, along with an
     * optional Zapper mask describing to whom the value is applied.
     * Each list item is a string.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#changeEventKeys()
     * @see com.planet_ink.coffee_mud.Libraries.interfaces.MaskingLibrary
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#defaults()
     * @param v the new default faction mask/value list
     */
    public void setDefaults(Vector<String> v);

    /**
     * Returns the automatic default faction mask/value list, which is 
     * possibly applied whenever a mob or player is brought to life for
     * the first time. An automatic default faction mask/value is defined 
     * as a number, along with an optional Zapper mask describing to whom 
     * the value is applied. Each list item is a string.
     * @see com.planet_ink.coffee_mud.Libraries.interfaces.MaskingLibrary
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#setAutoDefaults(Vector)
     * @return the automatic default faction mask/value list
     */
    public Enumeration<String> autoDefaults();

    /**
     * Returns the automatic default faction value that applies to the
     * given mob.  This method is called when a mob is brought into the
     * world.  Returns Integer.MAX_VALUE if no default value applies 
     * to this mob.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#defaults()
     * @param mob the mob to find a default value of this faction for.
     * @return the value to give to the given mob, or Integer.MAX_VALUE
     */
    public int findAutoDefault(MOB mob);

    /**
     * Sets the automatic default faction mask/value list, which is 
     * possibly applied whenever a mob or player is brought to life for
     * the first time. An automatic default faction mask/value is defined 
     * as a number, along with an optional Zapper mask describing to whom 
     * the value is applied. Each list item is a string.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#defaults()
     * @see com.planet_ink.coffee_mud.Libraries.interfaces.MaskingLibrary
     * @param v the new automatic default faction mask/value list
     */
    public void setAutoDefaults(Vector<String> v);

    /**
     * A modifier of the base amount of faction value change, namely 100.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#setRateModifier(double)
     * @return a modifier of the base amount of faction change
     */
    public double rateModifier();

    /**
     * Sets the modifier of the base amount of faction value change, namely 100.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#rateModifier()
     * @param d the new modifier of the base amount of faction value change
     */
    public void setRateModifier(double d);

    /**
     * Returns the player choosable faction mask/value list, which is 
     * possibly presented whenever a player creates a new character.
     * An faction mask/value is defined as a number, along with an 
     * optional Zapper mask describing to whom the value is applied.
     * Each list item is a string.
     * @see com.planet_ink.coffee_mud.Libraries.interfaces.MaskingLibrary
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#setChoices(Vector)
     * @return the choosable faction mask/value list
     */
    public Enumeration<String> choices();

    /**
     * Returns a vector of Integer objects representing the choosable
     * faction values available to the given mob when they create 
     * a new character.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#choices()
     * @param mob the player mob to evaluate
     * @return a vector of integer faction values that applies
     */
    public Vector<Integer> findChoices(MOB mob);

    /**
     * Sets the player choosable faction mask/value list, which is 
     * possibly presented whenever a player creates a new character.
     * An faction mask/value is defined as a number, along with an 
     * optional Zapper mask describing to whom the value is applied.
     * Each list item is a string.
     * @see com.planet_ink.coffee_mud.Libraries.interfaces.MaskingLibrary
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#choices()
     * @param v the list of choosable faction mask/values
     */
    public void setChoices(Vector<String> v);

    /**
     * Returns an enumeration of all available Faction.FactionRange objects,
     * representing the entire score of available values valid for this faction.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#addRange(String)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#delRange(com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionRange)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionRange
     * @return an enumeration of all available ranges
     */
    public Enumeration<Faction.FactionRange> ranges();

    /**
     * Returns the Faction.FactionRange object that applies to the given faction
     * value.  
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionRange
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ranges()
     * @param faction the value to find a matching range object for
     * @return the range object that matches the given faction value
     */
    public FactionRange fetchRange(int faction);

    /**
     * Returns the name of the Faction.FactionRange object that applies to 
     * the given faction value.  
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ranges()
     * @param faction the value to find a matching range object for
     * @return the name of the given faction object
     */
    public String fetchRangeName(int faction);

    /**
     * Adds a new Faction.FactionRange object to this faction using an encoded key.
     * The key is encoded as semicolon separated values of low, high, name, code name,
     * and alignment flag.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionRange
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ranges()
     * @param key the encoded values for the new faction range
     * @return the faction range object created and added.
     */
    public FactionRange addRange(String key);

    /**
     * Removes the given FactionRange object from the faction.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionRange
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ranges()
     * @param FR the faction range object to remove
     * @return whether a removal was necessary
     */
    public boolean delRange(FactionRange FR);
    
    /**
     * Returns the Faction.FactionRange object that applies to 
     * the given faction range code name.  
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ranges()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionRange#codeName()
     * @param codeName the code name to find a matching range object for
     * @return the correct faction range object, or null
     */
    public Faction.FactionRange fetchRange(String codeName);
    
    /**
     * Returns an enumeration of change event keys, which are the code names of
     * the triggers that cause faction values to change automatically.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#addChangeEvent(String)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#delChangeEvent(String)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALL_CHANGE_EVENT_TYPES()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#executeChange(MOB, MOB, com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent)
     * @return an enumeration of the event keys (triggers)
     */
    public Enumeration<String> changeEventKeys();

    /**
     * Returns a FactionChangeEvent that applies when the given Ability is used
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#changeEventKeys()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#executeChange(MOB, MOB, com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALL_CHANGE_EVENT_TYPES()
     * @param key the Ability to find a change event for.
     * @return the FactionChangeEvent that applies, or null.
     */
    public FactionChangeEvent findChangeEvent(Ability key);

    /**
     * Returns a FactionChangeEvent that applies when the given event name (a trigger
     * code) occurs in the game.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#changeEventKeys()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#MISC_TRIGGERS
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#executeChange(MOB, MOB, com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALL_CHANGE_EVENT_TYPES()
     * @param key the code name of the event that occurred
     * @return the FactionChangeEvent triggered by that event
     */
    public FactionChangeEvent getChangeEvent(String key);

    /**
     * Adds a new FactionChangeEvent object to this faction using the given event code
     * name, or fully encoded event string.  The key must be either a single event
     * trigger code (an ability name, event code name), or a fully encoded string
     * which is a semicolon delimited field consisting of event (trigger) id, direction
     * code, and amount 
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#changeEventKeys()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#MISC_TRIGGERS
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALL_CHANGE_EVENT_TYPES()
     * @param key the field used to create the new FactionChangeEvent
     * @return the FactionChangeEvent object created and added to this faction, or null
     */
    public FactionChangeEvent addChangeEvent(String key);

    /**
     * Removes a FactionChangeEvent of the given event (trigger) id.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#changeEventKeys()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#MISC_TRIGGERS
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALL_CHANGE_EVENT_TYPES()
     * @param eventKey the event id to remove from the list of change events
     * @return whether the event id was found to remove
     */
    public boolean delChangeEvent(String eventKey);


    /**
     * Executes a Faction change event for the given event source and target, and the 
     * applicable FactionChangeEvent event object for this faction 
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#changeEventKeys()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALL_CHANGE_EVENT_TYPES()
     * @param source the source of the event
     * @param target the target of the event
     * @param event the applicable event object for this faction
     */
    public void executeChange(MOB source, MOB target, FactionChangeEvent event);
    

    /**
     * Computed completed at runtime, this method returns all possible valid FactionChangeEvent
     * event ids that can be used to define triggers.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#changeEventKeys()
     * @return a list of all valid event trigger ids.
     */
    public String ALL_CHANGE_EVENT_TYPES();
    
    /**
     * Returns an enumeration of Object arrays referring to the a factor to multiply
     * times the base amount (100) of faction change (up or down) for particular
     * mobs who match a given Zapper mask.  Each Object array consists of a factor
     * to apply on faction gains, a factor to apply on factor drops, and the zapper
     * mask to decide which mobs it applies to (or mob states).
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#addFactor(double, double, String)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#delFactor(Object[])
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#findFactor(MOB, boolean)
     * @see com.planet_ink.coffee_mud.Libraries.interfaces.MaskingLibrary
     * @return the enumeration of change factor object arrays
     */
    public Enumeration<Object[]> factors();
    
    /**
     * Removes the given change factor from this faction.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#factors()
     * @param o the factor to remove
     * @return whether the given factor was found to remove
     */
    public boolean delFactor(Object[] o);

    /**
     * Returns the given enumerated change factor
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#factors()
     * @param x which factor (0-number) to return
     * @return the given factor, or null.
     */
    public Object[] getFactor(int x);
    
    /**
     * Adds a new change factor to this Faction.  A change factor is a state
     * dependent multiplier by a change in faction.  It consists of a Zapper
     * mask to determine whether the factor applies to the given mob/player
     * state, and a factor to apply on gains in faction or losses in faction.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#factors()
     * @see com.planet_ink.coffee_mud.Libraries.interfaces.MaskingLibrary
     * @param gain the factor to apply on gains in faction
     * @param loss the factor to apply on losses of faction
     * @param mask the zapper mask to use to determine if this factor applies to a mob
     * @return the newly created factor Object[] array
     */
    public Object[] addFactor(double gain, double loss, String mask);
    
    /**
     * Returns the applicable change factor for the given mob, and the
     * whether the faction change was a gain or loss (not a gain).
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#factors()
     * @see com.planet_ink.coffee_mud.Libraries.interfaces.MaskingLibrary
     * @param mob the mob to compare against the zapper masks of the various factors
     * @param gain return the gain factor if true, or the loss factor if false
     * @return the factor value that applies, or 1.0 (meaning no change).
     */
    public double findFactor(MOB mob, boolean gain);

    /**
     * Returns an enumeration of faction ids (of other factions) that are
     * automatically changed, up or down, when this faction changes. A relation
     * factor is a number multiplied by the change in this faction to determine
     * the amount that another faction on the same mob is changed by.  The factor
     * can be positive or negative to cause the other faction to rise or fall.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#factionID()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#addRelation(String, double)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#delRelation(String)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#getRelation(String)
     * @return an enumeration of faction ids
     */
    public Enumeration<String> relationFactions();

    /**
     * Removes the give faction relation from this faction.  Requires a faction id
     * of another faction.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#factionID()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#relationFactions()
     * @param factionID the faction id to remove
     * @return whether the faction id was found and removed
     */
    public boolean delRelation(String factionID);
    
    /**
     * Adds a new faction relation factor to this faction.  The faction id is the id
     * of another complementary or rival faction, and the relation is a number multiplied
     * by thge change in this faction to determine the amount the given faction id
     * faction is changed by. The relation factor can be positive or negative to cause
     * the faction id faction to rise or fall.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#factionID()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#relationFactions()
     * @param factionID the faction id of the other faction
     * @param relation the relation factor to use as a multiplier
     * @return whether the new faction id was successfully added
     */
    public boolean addRelation(String factionID, double relation);
    
    /**
     * Returns the relation factor of the given faction id.  See addRelation for
     * more information.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#factionID()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#relationFactions()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#addRelation(String, double)
     * @param factionID the other factions faction id
     * @return the factor to multiply a change in the other faction by
     */
    public double getRelation(String factionID); 
    
    /**
     * Returns an enumeration of Abilities or Behavior IDs that are
     * automatically but conditionally added to mobs (not players) with this faction. 
     * @see com.planet_ink.coffee_mud.Abilities.interfaces.Ability
     * @see com.planet_ink.coffee_mud.Behaviors.interfaces.Behavior
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#addAffectBehav(String, String, String)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#delAffectBehav(String)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#getAffectBehav(String)
     * @return an enumeration of Abilities or Behavior ID
     */
    public Enumeration<String> affectsBehavs();

    /**
     * Removes the given ability or behavior from this Faction.  It will require the
     * mob be reset or rejuved in order for this to take affect.
     * @see com.planet_ink.coffee_mud.Abilities.interfaces.Ability
     * @see com.planet_ink.coffee_mud.Behaviors.interfaces.Behavior
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#affectsBehavs()
     * @param ID the Abilities or Behavior ID to remove
     * @return whether the Abilities or Behavior ID was found and removed
     */
    public boolean delAffectBehav(String ID);
    
    /**
     * Adds a new Ability or Behavior to this Faction.  The ID must match a 
     * Behavior or, if one is not found, an Ability.  The parms are any
     * parameters required by the Behavior or Ability.  The gainMask is
     * a simple mask to further narrow what kind of mobs receive the 
     * given Ability or Behavior when first receiving this Faction.  It 
     * will require the mob be reset or rejuved in order for this to take affect.
     * @see com.planet_ink.coffee_mud.Abilities.interfaces.Ability
     * @see com.planet_ink.coffee_mud.Behaviors.interfaces.Behavior
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#affectsBehavs()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#delAffectBehav(String)
     * @param ID the Abilities or Behavior ID to add
     * @param parms the parameters for the new affect or behavior
     * @param gainMask the zapper mask to check to see who qualifies
     * @return whether the new Abilities or Behavior ID was successfully added
     */
    public boolean addAffectBehav(String ID, String parms, String gainMask);
    
    /**
     * Returns a string array containing the parms at index 0, and the gainMask at 1.  
     * See addAffectBehav for more information.
     * @see com.planet_ink.coffee_mud.Abilities.interfaces.Ability
     * @see com.planet_ink.coffee_mud.Behaviors.interfaces.Behavior
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#affectsBehavs()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#addAffectBehav(String, String, String)
     * @param ID the Abilities or Behavior ID
     * @return a string array containing the parms at index 0, and the gainMask at 1
     */
    public String[] getAffectBehav(String ID); 
    
    /**
     * Returns an enumeration of Faction.FactionAbilityUsage objects for this Faction.
     * A FactionAbilityUsage object defines restrictions on the use of a mob or players
     * abilities based on values in this faction and other variables.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#addAbilityUsage(String)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#delAbilityUsage(com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#usageFactorRangeDescription(Ability)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#hasUsage(Ability)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#canUse(MOB, Ability)
     * @return an enumeration of Faction.FactionAbilityUsage objects for this Faction
     */
    public Enumeration<Faction.FactionAbilityUsage> abilityUsages();
    
    /**
     * Returns the list of faction ranges that apply based on Faction.FactionAbilityUsage 
     * usage factor that apply to the given ability.  An empty string means it does not
     * apply.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#abilityUsages()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionRange
     * @param A the ability to find a usage factor for, and then use to find applicable ranges
     * @return the list of faction range names that apply to this ability from usage factors 
     */
    public String usageFactorRangeDescription(Ability A);

    /**
     * Returns whether any of the Faction.FactionAbilityUsage objects for this Faction
     * apply to the given ability.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#abilityUsages()
     * @param A the ability to find a usage criterium for
     * @return true if a criterium exists, false otherwise.
     */
    public boolean hasUsage(Ability A);

    /**
     * Returns whether the given player/mob is prevented from using the given Ability
     * based on any of the Faction.FactionAbilityUsage (faction ability usage) 
     * criterium defined for this Faction.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#abilityUsages()
     * @param mob the mob/player to evaluate
     * @param A the ability to evaluate
     * @return true if the player can use the ability, false otherwise
     */
    public boolean canUse(MOB mob, Ability A);

    /**
     * Adds a new Faction.FactionAbilityUsage object to this Faction based on the
     * given definitional key.  The key is NULL to create an empty usage, or
     * a definitional string that consists of one or more ability names, domains,
     * flags, etc followed by a semicolon and a minimum faction value, and another
     * semicolon and a maximum faction value. 
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#abilityUsages()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#delAbilityUsage(com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage)
     * @param key the definitional key, or null
     * @return the new Faction.FactionAbilityUsage added
     */
    public FactionAbilityUsage addAbilityUsage(String key);

    /**
     * Returns the enumerated Faction.FactionAbilityUsage object at the given index.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#abilityUsages()
     * @param x the index of the Faction.FactionAbilityUsage object to return
     * @return the Faction.FactionAbilityUsage object at that index
     */
    public FactionAbilityUsage getAbilityUsage(int x);
    
    /**
     * Removes the given Faction.FactionAbilityUsage object from this faction
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#abilityUsages()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#addAbilityUsage(String)
     * @param usage the Faction.FactionAbilityUsage object to remove
     * @return true if the object was found and removed
     */
    public boolean delAbilityUsage(FactionAbilityUsage usage);
    
    /**
     * A Faction Change Event is an event that triggers an automatic change in 
     * a mob or players faction value.  Triggers can be the use of abilities,
     * or certain specific coded events (such as killing another mob).
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALL_CHANGE_EVENT_TYPES()
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#executeChange(MOB, MOB, com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent)
     * 
     * @author Bo Zimmerman
     *
     */
    public static interface FactionChangeEvent
    {
        /**
         * Returns the event trigger id
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALL_CHANGE_EVENT_TYPES()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#setEventID(String)
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#IDclassFilter()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#IDdomainFilter()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#IDflagFilter()
         * @return the event trigger id
         */
        public String eventID();

        /**
         * Sets the event trigger id
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALL_CHANGE_EVENT_TYPES()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#eventID()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#IDclassFilter()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#IDdomainFilter()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#IDflagFilter()
         * @param newID the new event trigger id
         * @return true if the event id is valid
         */
        public boolean setEventID(String newID);
        
        
        /**
         * A derivative of the event id, this will return a value of 0 or above
         * if the event id was of a particular Ability ACODE_.  Returns -1 if 
         * this value does not apply, or an index into ACODE_DESCS.
         * @see com.planet_ink.coffee_mud.Abilities.interfaces.Ability#ACODE_DESCS
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#eventID()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#IDdomainFilter()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#IDflagFilter()
         * @return -1, or an index into an Ability ACODE
         */
        public int IDclassFilter();

        /**
         * A derivative of the event id, this will return a value of 0 or above
         * if the event id was of a particular Ability FLAG_.  Returns -1 if 
         * this value does not apply, or an index into FLAG_DESCS.
         * @see com.planet_ink.coffee_mud.Abilities.interfaces.Ability#FLAG_DESCS
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#eventID()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#IDclassFilter()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#IDdomainFilter()
         * @return -1, or an index into an Ability FLAG
         */
        public int IDflagFilter();

        /**
         * A derivative of the event id, this will return a value of 0 or above
         * if the event id was of a particular Ability DOMAIN_.  Returns -1 if 
         * this value does not apply, or an index into DOMAIN_DESCS.
         * @see com.planet_ink.coffee_mud.Abilities.interfaces.Ability#DOMAIN_DESCS
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#eventID()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#IDclassFilter()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#IDflagFilter()
         * @return -1, or an index into an Ability ACODE
         */
        public int IDdomainFilter();

        /**
         * Returns the list of flags that apply to this event.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#FLAG_DESCS
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#setFlags(String)
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#outsiderTargetOK()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#selfTargetOK()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#just100()
         * @return the list of applicable flags
         */
        public String flagCache();

        /**
         * Sets the list of flags that apply to this event.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#FLAG_DESCS
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#flagCache()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#outsiderTargetOK()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#selfTargetOK()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#just100()
         * @param newFlagCache the new list of applicable flags
         */
        public void setFlags(String newFlagCache);
        
        
        /**
         * A derivative of the flag cache, this method returns whether the flag was set that
         * allows this event to trigger when the target of the event does not have any value
         * with this faction
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#flagCache()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#selfTargetOK()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#just100()
         * @return true if the target does not have to have this faction, false otherwise
         */
        public boolean outsiderTargetOK();

        /**
         * A derivative of the flag cache, this method returns whether the flag was set that
         * allows this event to trigger when the target and source of the event are the same.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#flagCache()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#outsiderTargetOK()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#just100()
         * @return true if src and target are the same, false otherwise
         */
        public boolean selfTargetOK();

        /**
         * A derivative of the flag cache, this method returns whether the flag was set that
         * causes the determination of the amount of faction move to apply to NOT take the
         * difference between the source and targets levels into account.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#flagCache()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#outsiderTargetOK()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#selfTargetOK()
         * @return true to NOT take level into account when determining amount of faction change
         */
        public boolean just100();
        
        /**
         * Returns a code for a description of how an event, if applicable, will affect this
         * factions value.  The direction is an index into CHANGE_DIRECTION_DESCS, or one of the
         * CHANGE_DIRECTION_ constants.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#CHANGE_DIRECTION_DESCS
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#setDirection(int)
         * @return a FactionChangeEvent#CHANGE_DIRECTION_ constant
         */
        public int direction();

        /**
         * Sets a code for a description of how an event, if applicable, will affect this
         * factions value.  The direction is an index into CHANGE_DIRECTION_DESCS, or one of the
         * CHANGE_DIRECTION_ constants.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#CHANGE_DIRECTION_DESCS
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#direction()
         * @param newVal a new FactionChangeEvent#CHANGE_DIRECTION_ constant
         */
        public void setDirection(int newVal);
        
        /**
         * Returns the factor to multiply the base faction change amount (100) by, to determine
         * the amount of this faction changed by this event, in accordance with the given direction.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#direction()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#setFactor(double)
         * @return the factor to multiply the base amount of the faction by
         */
        public double factor();

        /**
         * Sets the factor to multiply the base faction change amount (100) by, to determine
         * the amount of this faction changed by this event, in accordance with the given direction.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#direction()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#factor()
         * @param newVal
         */
        public void setFactor(double newVal);
        
        /**
         * Returns the zapper mask that is used to see if the target of the event qualifies in
         * order to trigger a faction change by this defined event.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#setZapper(String)
         * @see com.planet_ink.coffee_mud.Libraries.interfaces.MaskingLibrary
         * @return the zapper mask string
         */
        public String zapper();

        /**
         * Sets the zapper mask that is used to see if the target of the event qualifies in
         * order to trigger a faction change by this defined event.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionChangeEvent#zapper()
         * @see com.planet_ink.coffee_mud.Libraries.interfaces.MaskingLibrary
         * @param newVal the new zapper mask string
         */
        public void setZapper(String newVal);

        /**
         * Returns a semicolon delimited list of all the settings in this change event
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#addChangeEvent(String)
         * @return a semicolon delimited list of all the settings in this change event
         */
        public String toString();

        /**
         * Returns whether the given mob is a valid target of this event.
         * @param mob the mob to evaluate
         * @return true if this event applies to the target mob, false otherwise
         */
        public boolean applies(MOB mob);
        
        /** a direction constant meaning this event changes the factions value upward */
        public static final int CHANGE_DIRECTION_UP=0;
        /** a direction constant meaning this event changes the factions value downward */
        public static final int CHANGE_DIRECTION_DOWN=1;
        /** a direction constant meaning this event changes the factions value opposite of targets faction leanings */
        public static final int CHANGE_DIRECTION_OPPOSITE=2;
        /** a direction constant meaning this event changes the factions value directly to lowest value */
        public static final int CHANGE_DIRECTION_MINIMUM=3;
        /** a direction constant meaning this event changes the factions value directly to highest value */
        public static final int CHANGE_DIRECTION_MAXIMUM=4;
        /** a direction constant meaning this event removes the faction altogether */
        public static final int CHANGE_DIRECTION_REMOVE=5;
        /** a direction constant meaning this event adds the faction with a default value */
        public static final int CHANGE_DIRECTION_ADD=6;
        /** a direction constant meaning this event changes the factions value away from targets value */
        public static final int CHANGE_DIRECTION_AWAY=7;
        /** a direction constant meaning this event changes the factions value towards the targets value */
        public static final int CHANGE_DIRECTION_TOWARD=8;
        /** the code words for the various direction flags that describe the direction and amount of faction change */
        public static final String[] CHANGE_DIRECTION_DESCS={"UP","DOWN","OPPOSITE","MINIMUM","MAXIMUM","REMOVE","ADD","AWAY","TOWARD"};
        /** the code words for the various evaluation flags to decide if this event applies and other things */
        public static final String[] FLAG_DESCS={"OUTSIDER","SELFOK","JUST100"};
        /** some non-ability-related event trigger ids */
        public static final String[] MISC_TRIGGERS={"MURDER","TIME","ADDOUTSIDER","MURDER2","MURDER3","MURDER4","MURDER5"};
    }
    

    /**
     * The foundation of any Faction, the Faction Range represents a range of values that constitutes
     * a single named group of numeric values for the faction.  A factions total range is determined
     * by the high value of the highest range and the low value of the lowest range.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#addRange(String)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ranges()
     * 
     * @author Bo Zimmerman
     *
     */    
    public static interface FactionRange
    {
        /**
         * Returns the unique code name that describes this range of faction values
         * @return the unique code name that describes this range of faction values
         */
        public String codeName();
        
        /**
         * Returns the numerically low value of this faction range
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionRange#setLow(int)
         * @return the numerically low value of this faction range
         */
        public int low();

        /**
         * Sets the numerically low value of this faction range
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionRange#low()
         * @param newVal the numerically low value of this faction range
         */
        public void setLow(int newVal);

        /**
         * Returns the numerically high value of this faction range
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionRange#setHigh(int)
         * @return the numerically high value of this faction range
         */
        public int high();

        /**
         * Sets the numerically high value of this faction range
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionRange#high()
         * @param newVal the numerically high value of this faction range
         */
        public void setHigh(int newVal);

        /**
         * Returns the nice friendly displayable name of this faction range,
         * which need not be unique.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionRange#setName(String)
         * @return the name of this range of values
         */
        public String name();

        /**
         * Sets the nice friendly displayable name of this faction range,
         * which need not be unique.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionRange#name()
         * @param newVal the name of this range of values
         */
        public void setName(String newVal);

        /**
         * Returns a constant reflecting whether this range of faction value is 
         * equivalent to one of the legacy alignment constant values.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALIGN_NAMES
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALIGN_INDIFF
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALIGN_EVIL
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALIGN_GOOD
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALIGN_NEUTRAL
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionRange#setAlignEquiv(int)
         * @return an alignment constant
         */
        public int alignEquiv();

        /**
         * Sets a constant reflecting whether this range of faction value is 
         * equivalent to one of the legacy alignment constant values.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALIGN_NAMES
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALIGN_INDIFF
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALIGN_EVIL
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALIGN_GOOD
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#ALIGN_NEUTRAL
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionRange#setAlignEquiv(int)
         * @param newVal a new alignment constant
         */
        public void setAlignEquiv(int newVal);

        /**
         * Returns a semicolon-delimited representation of this faction range, which
         * can be used to create a new one later.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#addRange(String)
         * @return a semicolon-delimited range
         */
        public String toString();

        /**
         * Returns a random numeric value within this faction range
         * @return a random numeric value within this faction range
         */
        public int random();
    }
    
    /**
     * A FactionData object is stored inside other objects that keep track
     * of their own faction.  The object stores the faction value, any
     * event listeners or tickers, and a method to determine when it is
     * time to refresh the object.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#makeFactionData(MOB)
     * @author bzimmerman
     */
    public static interface FactionData
    {
        /**
         * Returns true if this object requires updating by the parent
         * faction for some reason.
         * @return true if an update is necessary, false otherwise.
         */
        public boolean requiresUpdating();
        
        /**
         * Returns the actual value that the holding object has in this faction.
         * @return the faction value
         */
        public int value();
        
        /**
         * Sets the actual value that the holding object has in this faction.
         * @param newValue the faction value
         */
        public void setValue(int newValue);
        
        /**
         * Clears and re-adds all the necessary message listeners and tickers
         * for this object.
         * @param listeners a vector of msglisteners
         * @param tickers a vector of msglisteners
         */
        public void addListenersNTickers(Vector listeners, Vector tickers);
        
        /**
         * Returns an enumeration of specific message listeners for this holder
         * of the faction.
         * @return an enumeration of specific message listeners
         */
        public Enumeration listeners();
        
        /**
         * Returns an enumeration of specific ticking objects for this holder 
         * of the faction.
         * @return an enumeration of specific ticking objects
         */
        public Enumeration tickers();
    }
    
    /**
     * A Faction Ability Usage object represents a set of criterium that can be used
     * to determine whether this faction allows a mob or player to use a particular
     * ability, or class of abilities.
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#addAbilityUsage(String)
     * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#abilityUsages()
     * @author Bo Zimmerman
     *
     */
    public static interface FactionAbilityUsage
    {
        /**
         * The unconverted ability mask, denoting ability ids, domains, flags, etc.
         * Is parsed for benefit of other methods below
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#setAbilityFlag(String)
         * @return the unconverted ability mask
         */
        public String abilityFlags();
        
        /**
         * Sets the ability usage masks and methods from an ability id, domain, flags, etc.
         * Parses the string sent to set many of the methods below. 
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#abilityFlags()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#notflag()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#possibleAbilityID()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#type()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#domain()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#flag()
         * @param str the ability usage mask
         * @return A vector of words inside the given string that are not valid or were not understood.
         */
        public Vector<String> setAbilityFlag(String str);

        /**
         * A bitmask of ability flags that must NOT be set for this usage to apply to an ability
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#abilityFlags()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#flag()
         * @see com.planet_ink.coffee_mud.Abilities.interfaces.Ability#FLAG_DESCS
         * @return a bitmask of Ability flags that must not be set by the ability
         */
        public int notflag();

        /**
         * A bitmask of ability flags that MUST be set for this usage to apply to an ability
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#abilityFlags()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#notflag()
         * @see com.planet_ink.coffee_mud.Abilities.interfaces.Ability#FLAG_DESCS
         * @return a bitmask of Ability flags that must be set by the ability
         */        
        public int flag();
        
        /**
         * Whether the abilityFlags() method is possibly a specific Ability ID
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#abilityFlags()
         * @see com.planet_ink.coffee_mud.Abilities.interfaces.Ability#ID()
         * @return true if the abilityFlags() string is an Ability ID()
         */
        public boolean possibleAbilityID();

        /**
         * An ability code that an ability must be in order for this usage to apply, or -1
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#abilityFlags()
         * @see com.planet_ink.coffee_mud.Abilities.interfaces.Ability#ACODE_DESCS
         * @return an ability code that an ability must be in order for this usage to apply, or -1
         */
        public int type();

        /**
         * An ability domain that an ability must be in order for this usage to apply, or -1
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#abilityFlags()
         * @see com.planet_ink.coffee_mud.Abilities.interfaces.Ability#DOMAIN_DESCS
         * @return an ability domain that an ability must be in order for this usage to apply, or -1
         */
        public int domain();

        /**
         * The minimum value that a player must have in the faction to be able to use the selected
         * ability referred to by the ability flags of this usage criterium.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#abilityFlags()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#setLow(int)
         * @return a minimum faction value
         */
        public int low();

        /**
         * Sets the minimum value that a player must have in the faction to be able to use the selected
         * ability referred to by the ability flags of this usage criterium.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#abilityFlags()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#low()
         * @param newVal a new minimum faction value
         */
        public void setLow(int newVal);

        /**
         * Returns the maximum value that a player must have in the faction to be able to use the selected
         * ability referred to by the ability flags of this usage criterium.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#abilityFlags()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#setHigh(int)
         * @return a maximum faction value
         */
        public int high();

        /**
         * Sets the maximum value that a player must have in the faction to be able to use the selected
         * ability referred to by the ability flags of this usage criterium.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#abilityFlags()
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction.FactionAbilityUsage#high()
         * @param newVal a new maximum faction value
         */
        public void setHigh(int newVal);
        
        /**
         * Returns a semicolon-delimited string of the values of this ability usage, suitable for
         * using to create a new one later.
         * @see com.planet_ink.coffee_mud.Common.interfaces.Faction#addAbilityUsage(String)
         * @return a semicolon-delimited string of the values of this ability usage
         */
        public String toString();
    }
    /** legacy constant for {@link FactionRange#alignEquiv()} denoting that the range does not reflect alignment */
    public final static int ALIGN_INDIFF=0;
    /** legacy constant for {@link FactionRange#alignEquiv()} denoting that the range reflects evil alignment */
    public final static int ALIGN_EVIL=1;
    /** legacy constant for {@link FactionRange#alignEquiv()} denoting that the range reflects neutral alignment */
    public final static int ALIGN_NEUTRAL=2;
    /** legacy constant for {@link FactionRange#alignEquiv()} denoting that the range reflects good alignment */
    public final static int ALIGN_GOOD=3;
    
    /** String list for the {@link Faction#ALIGN_EVIL} constants */
    public final static String[] ALIGN_NAMES={"","EVIL","NEUTRAL","GOOD"};
    
    /** String list for the valid {@link Faction#experienceFlag()} constants */
    public final static String[] EXPAFFECT_NAMES={"NONE","EXTREME","HIGHER","LOWER","FOLLOWHIGHER","FOLLOWLOWER"};
    /** String descriptions for the valid {@link Faction#experienceFlag()} constants */
    public final static String[] EXPAFFECT_DESCS={"None","Proportional (Extreme)","Higher (mine)","Lower (mine)","Higher (other)","Lower (other)"};
    
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the NAME tag */
    public final static int TAG_NAME=0;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the MINIMUM tag */
    public final static int TAG_MINIMUM=1;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the MAXIMUM tag */
    public final static int TAG_MAXIMUM=2;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the SCOREDISPLAY tag */
    public final static int TAG_SCOREDISPLAY=3;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the SPECIALREPORTED tag */
    public final static int TAG_SPECIALREPORTED=4;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the EDITALONE tag */
    public final static int TAG_EDITALONE=5;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the DEFAULT tag */
    public final static int TAG_DEFAULT=6;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the AUTODEFAULTS tag */
    public final static int TAG_AUTODEFAULTS=7;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the AUTOCHOICES tag */
    public final static int TAG_AUTOCHOICES=8;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the CHOICEINTRO tag */
    public final static int TAG_CHOICEINTRO=9;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the RATEMODIFIER tag */
    public final static int TAG_RATEMODIFIER=10;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the EXPERIENCE tag */
    public final static int TAG_EXPERIENCE=11;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the RANGE tag */
    public final static int TAG_RANGE_=12;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the CHANGE tag */
    public final static int TAG_CHANGE_=13;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the ABILITY tag */
    public final static int TAG_ABILITY_=14;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the FACTOR tag */
    public final static int TAG_FACTOR_=15;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the RELATION tag */
    public final static int TAG_RELATION_=16;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the SHOWINFACTIONSCMD tag */
    public final static int TAG_SHOWINFACTIONSCMD=17;
    /** index constant for tag names in {@link Faction#TAG_NAMES} denoting the AFFBEHAV tag */
    public final static int TAG_AFFBEHAV_=18;
    /** list of valid tag names for internal faction data, retrieved by {@link Faction#getTagValue(String)} */
    public final static String[] TAG_NAMES={"NAME","MINIMUM","MAXIMUM","SCOREDISPLAY",
                                            "SPECIALREPORTED","EDITALONE","DEFAULT","AUTODEFAULTS",
                                            "AUTOCHOICES","CHOICEINTRO","RATEMODIFIER","EXPERIENCE",
                                            "RANGE*","CHANGE*","ABILITY*","FACTOR*","RELATION*",
                                            "SHOWINFACTIONSCMD","AFFBEHAV*"};
}
