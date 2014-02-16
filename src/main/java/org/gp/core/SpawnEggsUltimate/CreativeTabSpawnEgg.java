package org.gp.core.SpawnEggsUltimate;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabSpawnEgg extends CreativeTabs {

	public CreativeTabSpawnEgg(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return Items.spawn_egg;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "Spawner Egg";
	}
}
