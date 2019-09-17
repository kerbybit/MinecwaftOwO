package com.kerbybit.minecwaft.tweaker;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.kerbybit.minecwaft.tweaker.transform.FontRendererTransformer;
import com.kerbybit.minecwaft.tweaker.transform.ITransformer;
import com.kerbybit.minecwaft.tweaker.transform.RenderComponentsTransformer;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.util.Collection;

public class ClassTransformer implements IClassTransformer {

    private static final Logger LOGGER = LogManager.getLogger("AutoCorrect");
    private final Multimap<String, ITransformer> transformerMap = ArrayListMultimap.create();

    public ClassTransformer() {
        registerTransformer(new FontRendererTransformer());
        registerTransformer(new RenderComponentsTransformer());
    }

    private void registerTransformer(ITransformer transformer) {
        for (String cls : transformer.getClassName()) {
            transformerMap.put(cls, transformer);
        }
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (bytes == null) return null;

        Collection<ITransformer> transformers = transformerMap.get(transformedName);
        if (transformers.isEmpty()) return bytes;

        LOGGER.info("Found {} transformers for {}", transformers.size(), transformedName);

        ClassReader reader = new ClassReader(bytes);
        ClassNode node = new ClassNode();
        reader.accept(node, ClassReader.EXPAND_FRAMES);

        transformers.forEach(transformer -> {
            LOGGER.info("Applying transformer {} on {}...", transformer.getClass().getName(), transformedName);
            transformer.transform(node, transformedName);
        });

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

        try {
            node.accept(writer);
        } catch (Throwable t) {
            LOGGER.error("Exception when transforming " + transformedName + " : " + t.getClass().getSimpleName());
            t.printStackTrace();
        }

        return writer.toByteArray();
    }
}
