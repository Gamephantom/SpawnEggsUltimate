package org.gp.core.SpawnEggsUltimate;

import java.util.Iterator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class PatchWholeEgg implements Opcodes {
	ClassReader cr;
	ClassNode cn;
	FieldVisitor fv;
	MethodVisitor mv;
	public byte[] patchEgg(byte[] bytes) throws Exception
	{
		cr = new ClassReader(bytes);
		cr.accept(cn, 0);
		fv = cn.visitField(ACC_PRIVATE, "rarityMap", "Ljava/util/HashMap;", "Ljava/util/HashMap<Ljava/lang/Integer;Lnet/minecraft/item/EnumRarity;>;", null);
		fv.visitEnd();
		for(MethodNode method : cn.methods)
		{
			if(method.name.equals("<init>") && method.desc.equals("()V"))
			{
				mv = cn.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
				mv.visitCode();
				Label l0 = new Label();
				mv.visitLabel(l0);
				mv.visitLineNumber(38, l0);
				mv.visitVarInsn(ALOAD, 0);
				mv.visitMethodInsn(INVOKESPECIAL, "net/minecraft/item/Item", "<init>", "()V");
				Label l1 = new Label();
				mv.visitLabel(l1);
				mv.visitLineNumber(40, l1);
				mv.visitVarInsn(ALOAD, 0);
				mv.visitInsn(ICONST_1);
				mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/item/ItemMonsterPlacer1", "setHasSubtypes", "(Z)Lnet/minecraft/item/Item;");
				mv.visitInsn(POP);
				Label l2 = new Label();
				mv.visitLabel(l2);
				mv.visitLineNumber(41, l2);
				mv.visitVarInsn(ALOAD, 0);
				mv.visitFieldInsn(GETSTATIC, "net/minecraft/creativetab/CreativeTabs", "tabMisc", "Lnet/minecraft/creativetab/CreativeTabs;");
				mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/item/ItemMonsterPlacer1", "setCreativeTab", "(Lnet/minecraft/creativetab/CreativeTabs;)Lnet/minecraft/item/Item;");
				mv.visitInsn(POP);
				Label l3 = new Label();
				mv.visitLabel(l3);
				mv.visitLineNumber(42, l3);
				mv.visitInsn(RETURN);
				Label l4 = new Label();
				mv.visitLabel(l4);
				mv.visitLocalVariable("this", "Lnet/minecraft/item/ItemMonsterPlacer1;", null, l0, l4, 0);
				mv.visitMaxs(2, 1);
				mv.visitEnd();
				}
				//{
				
				/*InsnList initMap = new InsnList();
				initMap.add(new LdcInsnNode(method.name));
				initMap.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "net/minecraft/item/ItemMonsterPlacer", "rarityMap", "Ljava/util/HashMap;"));
				method.instructions.add(initMap);
				System.out.println(method.name);
				Iterator<AbstractInsnNode> it = method.instructions.iterator();
				while(it.hasNext())
				{
					System.out.println(it.next().getOpcode());
				}*/
			//}
		}
		return cr.b;
	}
}
