package org.gp.core.SpawnEggsUltimate;

import org.gp.SpawnEggsUltimate.API.OnSpawnEggUseEvent;
import org.gp.core.SpawnEggsUltimate.Egg.Hooks;

import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class SpawnEggPatch extends ItemMonsterPlacer {

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
        OnSpawnEggUseEvent evt = new OnSpawnEggUseEvent(par2EntityPlayer, par3World, par4, par5, par6);
        if(!MinecraftForge.EVENT_BUS.post(evt))
        {
        	return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
        }else if(evt.getResult() == Result.ALLOW)
        {
        	return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
        }else
        {
        	return true;
        }
	}
	
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
		OnSpawnEggUseEvent evt = new OnSpawnEggUseEvent(par3EntityPlayer, par2World, mop.blockX, mop.blockY, mop.blockZ);
		if(!MinecraftForge.EVENT_BUS.post(evt))
		{
			return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
		}else if(evt.getResult() == Result.ALLOW)
		{
			return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
		}
		else return par1ItemStack;
    }
    
    public static Entity spawnCreature(World par0World, int par1, double par2, double par4, double par6)
    {
    	return spawnCreature(par0World, par1, par2, par4, par6, new ItemStack(Items.spawn_egg));
    }
    
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
                    Hooks.spawnCreatureNBT(stack, entity);
                    par0World.spawnEntityInWorld(entity);
                    entityliving.playLivingSound();
                }else Hooks.spawnNonLivingCreature(entity, par0World, par1, par2, par4, par6, stack);
            }

            return entity;
        }
    }
}
