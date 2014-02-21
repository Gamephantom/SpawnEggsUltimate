package org.gp.SpawnEggsUltimate.API;

import java.awt.List;
import java.util.HashMap;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;

public class EntityEggBase {
	private HashMap<Integer,EntityEggInfo> register;// = new HashMap<Integer,EntityEggInfo>();
	private List toRemove = new List();
	
	public EntityEggBase()
	{
		register = new HashMap<Integer,EntityEggInfo>();
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
	
	
}
