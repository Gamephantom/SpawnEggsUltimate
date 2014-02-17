package org.gp.core.SpawnEggsUltimate;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.gp.core.SpawnEggsUltimate.ASM.ASMManipulator;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class SpawnEggsTransformer implements
		net.minecraft.launchwrapper.IClassTransformer, Opcodes {

	ClassReader cr;
	ClassNode cn = new ClassNode();
	FieldVisitor fv;
	ClassWriter cw;
	MethodVisitor mv;
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
			bytes = patchEgg(bytes);
			return bytes;
			/*ZipFile f = new ZipFile(location);
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
			f.close();*/
		} catch (Exception e) {
			throw new RuntimeException("UGH @___@, this Cookie " + name
					+ " was too hard!", e);
		}
	}

	public static boolean getObfuscated() {
		return obfuscated;
	}
	
	public byte[] patchEgg(byte[] bytes) throws Exception
	{
		cr = new ClassReader(bytes);
		cr.accept(cn, 0);
		fv = cn.visitField(ACC_PRIVATE, "rarityMap", "Ljava/util/HashMap;", "Ljava/util/HashMap<Ljava/lang/Integer;Lnet/minecraft/item/EnumRarity;>;", null);
		fv.visitEnd();
		for(MethodNode method : cn.methods)
		{
            if(method.name.equals("onItemUse"))
			{
				InsnList initMap = new InsnList();
				initMap.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
				initMap.add(new LdcInsnNode("go"));
				initMap.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V"));
				method.instructions.insert(initMap);
				ListIterator<AbstractInsnNode> it = method.instructions.iterator();
				while(it.hasNext())
				{
					if(it.next().getOpcode() == Opcodes.DCONST_0)
					{
						method.instructions.insert(it.next(), ASMManipulator.patchOnItemUse());
						break;
					}
				}
			}
		}
		cw  = new ClassWriter(ClassWriter.COMPUTE_MAXS|ClassWriter.COMPUTE_FRAMES);
		cn.accept(cw);
		File outDir=new File("out/com/geekyarticles/asm");
		outDir.mkdirs();
	    DataOutputStream dout=new DataOutputStream(new FileOutputStream(new File(outDir,"LoggingTest.class")));
		dout.write(cw.toByteArray());
	    dout.flush();
		dout.close();
		return cw.toByteArray();
	}
}
