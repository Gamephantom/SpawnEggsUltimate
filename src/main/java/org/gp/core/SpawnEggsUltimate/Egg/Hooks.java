package org.gp.core.SpawnEggsUltimate.Egg;

import org.gp.SpawnEggsUltimate.API.AccessGetterNBT;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Hooks {
	
	public static void spawnCreatureNBT(ItemStack stack, Entity localEntity)
	{
		if (stack.stackTagCompound != null) {
			if (stack.stackTagCompound.hasKey("SpawnData")) {
				if (AccessGetterNBT.invokeMethodBoolean(localEntity, "a", 
						stack.getTagCompound().getCompoundTag("SpawnData")));
				else
					AccessGetterNBT.invokeMethodBoolean(localEntity,
							"readEntityFromNBT", stack.getTagCompound()
									.getCompoundTag("SpawnData"));
			}
		}
	}
	
	public static void spawnNonLivingCreature(Entity entity, World par0World, int par1, double par2, double par4, double par6, ItemStack stack)
	{
    	entity.setLocationAndAngles(par2, par4, par6, MathHelper.wrapAngleTo180_float(par0World.rand.nextFloat() * 360.0F), 0.0F);
    	spawnCreatureNBT(stack, entity);
    	par0World.spawnEntityInWorld(entity);
	}
	
	//Changed and reprogrammed!
    public static Entity spawnCreature(World par0World, int par1, double par2, double par4, double par6, ItemStack stack)
    {
        if (!EntityList.entityEggs.containsKey(Integer.valueOf(par1)))
        {
            return null;
        }
        else
        {
            Entity entity = null;

            for (int j = 0; j < 1; ++j)
            {
                entity = EntityList.createEntityByID(par1, par0World);

                if (entity != null && entity instanceof EntityLivingBase)
                {
                    EntityLiving entityliving = (EntityLiving)entity;
                    entity.setLocationAndAngles(par2, par4, par6, MathHelper.wrapAngleTo180_float(par0World.rand.nextFloat() * 360.0F), 0.0F);
                    entityliving.rotationYawHead = entityliving.rotationYaw;
                    entityliving.renderYawOffset = entityliving.rotationYaw;
                    entityliving.onSpawnWithEgg((IEntityLivingData)null);
                    spawnCreatureNBT(stack, entity);
                    par0World.spawnEntityInWorld(entity);
                    entityliving.playLivingSound();
                }else spawnNonLivingCreature(entity, par0World, par1, par2, par4, par6, stack);
            }

            return entity;
        }
    }
}
