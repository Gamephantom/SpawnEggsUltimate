package org.gp.core.SpawnEggsUltimate;

import java.awt.Color;
import java.util.Arrays;

import com.google.common.eventbus.Subscribe;
//import net.minecraft.entity.EntityList;
//import net.minecraft.entity.EntityList.EntityEggInfo;
import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class SpawnEggsContainer extends DummyModContainer {
	
    public SpawnEggsContainer() {

        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "SpawnEggsCore";
        meta.name = "SpawnEggs Ultimate";
        meta.version = "1.0";
        meta.authorList = Arrays.asList("Gamephantom");
        meta.description = "This coremod overwrites the Core of the Spawn Eggs!";
        meta.updateUrl = "";
        meta.screenshots = new String[0];
        meta.logoFile = "";
        }
    
    @Override
    public boolean registerBus(EventBus bus, LoadController controller)
    {
    	bus.register(this);
        return true;
    }
	
    @Subscribe
    public void modConstruction(FMLConstructionEvent evt){

    }

    @Subscribe
    public void preInit(FMLPreInitializationEvent evt) {

    }

    @Subscribe
    public void init(FMLInitializationEvent evt) {
    	LanguageRegistry.instance().addStringLocalization("itemGroup.spawnEgg", "Spawner Eggs");
    	LanguageRegistry.instance().addStringLocalization("entity.ThrownExpBottle.name", "EXP-Bottle");
    	LanguageRegistry.instance().addStringLocalization("entity.EnderCrystal.name", "Ender Crystal");
    	//initEntityList();
    }


    @Subscribe
    public void postInit(FMLPostInitializationEvent evt) {
    	//addEntitytoRarityMap(63, EnumRarity.epic);
    	//addEntitytoRarityMap(64, EnumRarity.epic);
    }
    
    
    /*@SuppressWarnings("unchecked")
	public void initEntityList()
    {
    	EntityList.entityEggs.put(17, new EntityEggInfo(17, Color.GREEN.getRGB(), Color.CYAN.getRGB()));
    	EntityList.entityEggs.put(97, new EntityEggInfo(97, Color.WHITE.getRGB(), Color.WHITE.getRGB()));
    	EntityList.entityEggs.put(12, new EntityEggInfo(12, Color.RED.getRGB(), Color.GRAY.getRGB()));
    	EntityList.entityEggs.put(22, new EntityEggInfo(22, Color.RED.getRGB(), Color.PINK.getRGB()));
    	EntityList.entityEggs.put(20, new EntityEggInfo(20, Color.RED.getRGB(), Color.BLACK.getRGB()));
    	EntityList.entityEggs.put(63, new EntityEggInfo(63, Color.BLACK.getRGB(), Color.DARK_GRAY.getRGB()));
    	EntityList.entityEggs.put(64, new EntityEggInfo(64, Color.DARK_GRAY.getRGB(), Color.BLUE.getRGB()));
    	EntityList.entityEggs.put(99, new EntityEggInfo(99, Color.LIGHT_GRAY.getRGB(), Color.MAGENTA.getRGB()));
    	EntityList.entityEggs.put(200, new EntityEggInfo(200, Color.BLACK.getRGB(), Color.PINK.getRGB()));
    }*/
    
    /*public static void addEntitytoRarityMap(int i, EnumRarity rarity)
    {
    	((ItemMonsterPlacer)Item.monsterPlacer).addRarityLevel(i, rarity);
    }*/

}
