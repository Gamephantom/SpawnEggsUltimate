package org.gp.SpawnEggsUltimate.API;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;

/***
 * 
 * @author Gamephantom
 * Entity Egg Base
 */
public class EntityEggBase {
	private HashMap<Integer,EntityEggInfo> register;// = new HashMap<Integer,EntityEggInfo>();
	private List<Integer> toRemove;
	
	public EntityEggBase()
	{
		register = new HashMap<Integer,EntityEggInfo>();
		register.putAll(EntityList.entityEggs);
	}
	
	public void registerEgg(int EntityID, EntityEggInfo egg)
	{
		if(EntityList.entityEggs.containsKey(EntityID) || EntityList.entityEggs.containsValue(egg))
		{
			System.out.println("You cannot register an Egg that is already registered!");
		}else if(register.containsKey(EntityID))
		{
			System.out.println("You cannot register an Egg that is already registered!");
		}else
		{
			register.put(EntityID, egg);
		}
	}
	
	public void removeEgg(int EntityID)
	{
		if(!EntityList.entityEggs.containsKey(EntityID))
			toRemove.add(EntityID);
		else System.out.println("ERROR : Egg is not in the Database!");
	}
	
	public void initialize()
	{
		Iterator<Integer> it = toRemove.iterator();
		while(it.hasNext())
		{
			int i = it.next();
			EntityList.entityEggs.remove(i);
		}
	}
	
	public HashMap getRegister()
	{
		return register;
	}
}
