package org.gp.SpawnEggsUltimate.CloneAddon;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "SpawnEggsUltimateClone", version = "1.0", name = "SpawnEggs Ultimate - Clone Addon",dependencies = "required-after:SpawnEggsCore")
public class CloneAddon {
    
	@EventHandler
    public void init(FMLInitializationEvent evt) {
		MinecraftForge.EVENT_BUS.register(new CloneEventHandler());
    }
}
