package org.gp.core.SpawnEggsUltimate;

import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class SpawnEggsTransformer implements
		net.minecraft.launchwrapper.IClassTransformer {

	private static boolean obfuscated = true;

	@Override
	public byte[] transform(String arg0, String arg1, byte[] arg2) {
		// TODO Auto-generated method stub
		if (arg0.equals("ze")) {
			obfuscated = true;
			SpawnEggsCore.ModLog
					.info("Found the Item MonsterPlacer! Yummy NOMNOMNOM!");
			SpawnEggsCore.ModLog
					.info("Eating it and backing a new Item MonsterPlacer");
			arg2 = eatTheMonsterPlacer(arg0, arg2, SpawnEggsCore.location);
		} else if (arg0.equals("net.minecraft.item.ItemMonsterPlacer")) {
			obfuscated = false;
			SpawnEggsCore.ModLog
					.info("Found the Item MonsterPlacer! Yummy NOMNOMNOM!");
			SpawnEggsCore.ModLog
					.info("Eating it and backing a new Item MonsterPlacer");
			arg2 = eatTheMonsterPlacer(arg0, arg2, SpawnEggsCore.location);
		}
		return arg2;
	}

	public byte[] eatTheMonsterPlacer(String name, byte[] bytes, File location) {
		try {
			ZipFile f = new ZipFile(location);
			ZipEntry entry = f.getEntry(name.replace('.', '/') + ".class");
			if (entry == null) {
				SpawnEggsCore.ModLog.warn("Ohhhh! No Cookie named" + name
						+ "found in the Jar File :(!");
			} else {
				InputStream streamCookie = f.getInputStream(entry);
				bytes = new byte[(int) entry.getSize()];
				streamCookie.read(bytes);
				streamCookie.close();
			}
			f.close();
		} catch (Exception e) {
			throw new RuntimeException("UGH @___@, this Cookie " + name
					+ " was too hard!", e);
		}
		return bytes;
	}

	public static boolean getObfuscated() {
		return obfuscated;
	}

}
