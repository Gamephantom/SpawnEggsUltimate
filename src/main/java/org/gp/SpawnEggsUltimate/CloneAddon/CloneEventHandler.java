package org.gp.SpawnEggsUltimate.CloneAddon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.gp.SpawnEggsUltimate.API.AccessGetterNBT;
import org.gp.SpawnEggsUltimate.API.OnHitEntityEvent;
import org.gp.core.SpawnEggsUltimate.SpawnEggsTransformer;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CloneEventHandler {
	
	@SubscribeEvent
	public void onHitEntity(OnHitEntityEvent ev)
	{
		try
		{
			NBTTagCompound spawnData = new NBTTagCompound();
			Entity e = ev.entity;
			ItemStack spawnEgg = ev.stack;
			spawnEgg.setItemDamage(EntityList.getEntityID(e));
			if(spawnEgg.stackTagCompound == null)
				spawnEgg.stackTagCompound = new NBTTagCompound();
			else if(spawnEgg.stackTagCompound.hasKey("SpawnData"))
				spawnEgg.stackTagCompound.removeTag("SpawnData");
			if(SpawnEggsTransformer.getObfuscated())
			{
				spawnData = new AccessGetterNBT<Entity>().<NBTTagCompound>invokeMethodWithReturn(e, new NBTTagCompound(), "b", new Object[]{new NBTTagCompound()});
			}else
			{
				spawnData = new AccessGetterNBT<Entity>().<NBTTagCompound>invokeMethodWithReturn(e, new NBTTagCompound(), "writeEntityToNBT", new Object[]{new NBTTagCompound()});		
			}
			spawnEgg.stackTagCompound.setTag("SpawnData", spawnData);
			ev.setResult(Result.ALLOW);
		}catch(Exception e)
		{
			ev.setResult(Result.DENY);
			e.printStackTrace();
		}
	}
}
