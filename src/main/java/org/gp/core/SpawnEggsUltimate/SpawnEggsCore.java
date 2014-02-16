package org.gp.core.SpawnEggsUltimate;

import java.io.File;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;

@MCVersion(value = "1.7.2")
public class SpawnEggsCore implements IFMLLoadingPlugin {

	public static File location;
    public static Logger ModLog = LogManager.getLogger();
	
	@Override
	public String[] getASMTransformerClass() {
		// TODO Auto-generated method stub
		return new String[]{SpawnEggsTransformer.class.getName()};
	}

	@Override
	public String getModContainerClass() {
		// TODO Auto-generated method stub
		return SpawnEggsContainer.class.getName();
	}

	@Override
	public String getSetupClass() {
		// 
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		location = (File) data.get("coremodLocation");
	}

	@Override
	public String getAccessTransformerClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
