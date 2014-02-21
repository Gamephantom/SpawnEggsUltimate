package org.gp.core.SpawnEggsUltimate;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameData;

@Mod(modid="SpawnEggsCore", name="SpawnEggs Ultimate")
public class SpawnEggsUltimate {
	private static final Item spawnEgg = new SpawnEggPatch().setCreativeTab(new CreativeTabSpawnEgg("SpawnEggsU")).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		GameData.itemRegistry.putObject("spawnEggU", spawnEgg );
	}
	
	public void init(FMLInitializationEvent e)
	{
		registerDispenser();
	}
	
	private void registerDispenser()
	{
		BlockDispenser.dispenseBehaviorRegistry.putObject(spawnEgg, new BehaviorDefaultDispenseItem()
	    {
	      public ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack)
	      {
	        EnumFacing enumfacing = BlockDispenser.func_149937_b(par1IBlockSource.getBlockMetadata());
	        double d0 = par1IBlockSource.getX() + enumfacing.getFrontOffsetX();
	        double d1 = par1IBlockSource.getYInt() + 0.2F;
	        double d2 = par1IBlockSource.getZ() + enumfacing.getFrontOffsetZ();
	        Entity entity = SpawnEggPatch.spawnCreature(par1IBlockSource.getWorld(), par2ItemStack.getItemDamage(), d0, d1, d2);
	        if (((entity instanceof EntityLivingBase)) && (par2ItemStack.hasDisplayName())) {
	          ((EntityLiving)entity).setCustomNameTag(par2ItemStack.getDisplayName());
	        }
	        par2ItemStack.splitStack(1);
	        return par2ItemStack;
	      }
	    });
	}
}
