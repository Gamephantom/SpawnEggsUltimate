package org.gp.core.SpawnEggsUltimate.ASM;

import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ASMManipulator implements Opcodes {
	
	public static InsnList patchOnItemUse()
	{
		//OnSpawnEggUseEvent localOnSpawnEggUseEvent = new OnSpawnEggUseEvent(...);
		InsnList list = new InsnList();
		list.add(new TypeInsnNode(NEW, "org/gp/SpawnEggsUltimate/API/OnSpawnEggUseEvent"));
		list.add(new InsnNode(DUP));
		list.add(new VarInsnNode(ALOAD, 2));
		list.add(new VarInsnNode(ALOAD, 3));
		list.add(new VarInsnNode(ILOAD, 4));
		list.add(new VarInsnNode(ILOAD, 5));
		list.add(new VarInsnNode(ILOAD, 6));
		list.add(new MethodInsnNode(INVOKESPECIAL, "org/gp/SpawnEggsUltimate/API/OnSpawnEggUseEvent", "<init>", "(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;III)V"));
		list.add(new VarInsnNode(ASTORE, 14));
		//if(MinecraftForge.EVENT_BUS.post(evt))
		list.add(new FieldInsnNode(GETSTATIC, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lcpw/mods/fml/common/eventhandler/EventBus;"));
		list.add(new VarInsnNode(ALOAD, 14));
		list.add(new MethodInsnNode(INVOKEVIRTUAL, "cpw/mods/fml/common/eventhandler/EventBus", "post", "(Lcpw/mods/fml/common/eventhandler/Event;)Z"));
		list.add(new InsnNode(IFEQ));
		//{
		return list;
	}
	
}
