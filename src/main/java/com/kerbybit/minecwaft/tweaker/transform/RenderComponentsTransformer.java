package com.kerbybit.minecwaft.tweaker.transform;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.ListIterator;

public class RenderComponentsTransformer implements ITransformer {

    @Override
    public String[] getClassName() {
        return new String[]{"net.minecraft.client.gui.GuiUtilRenderComponents"};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode methodNode : classNode.methods) {
            String methodName = mapMethodName(classNode, methodNode);

            if (methodName.equalsIgnoreCase("splitText")
                    || methodName.equalsIgnoreCase("func_178908_a")) {
                ListIterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();
                while (iterator.hasNext()) {
                    AbstractInsnNode next = iterator.next();
                    if (next instanceof MethodInsnNode) {
                        MethodInsnNode methodInsnNode = (MethodInsnNode) next;
                        if (methodInsnNode.owner.equals("net/minecraft/util/IChatComponent")
                                && methodInsnNode.name.equals("getUnformattedTextForChat")
                                && methodInsnNode.desc.equals("()Ljava/lang/String;")) {
                            iterator.add(changeTextHook());
                        }
                    }
                }
            }
        }
    }

    private AbstractInsnNode changeTextHook() {
        return new MethodInsnNode(
                Opcodes.INVOKESTATIC,
                "com/kerbybit/minecwaft/MinecwaftOwO",
                "makeOwO",
                "(Ljava/lang/String;)Ljava/lang/String;",
                false
        );
    }
}
