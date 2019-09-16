package com.kerbybit.minecwaft.tweaker.transform;

import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public interface ITransformer extends Opcodes {

    String[] getClassName();
    void transform(ClassNode classNode, String name);

    default String mapMethodName(ClassNode classNode, MethodNode methodNode) {
        return FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(classNode.name, methodNode.name, methodNode.desc);
    }
}
