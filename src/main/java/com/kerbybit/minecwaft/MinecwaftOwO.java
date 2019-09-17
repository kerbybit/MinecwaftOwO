package com.kerbybit.minecwaft;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mod(modid = MinecwaftOwO.MODID, version = MinecwaftOwO.VERSION)
public class MinecwaftOwO {
    static final String MODID = "MinecwaftOwO";
    static final String VERSION = "1.2";
    public static boolean toggled = true;

    private static final String[] faces = new String[]{"(*^w^)", "(*^.^*)", "(OuO)", "(OwO)", "(UwU)", "(>w<)", "(^w^)", "(^u^)", "(/ =w=)/"};
    private static final Matcher[] faceText = new Matcher[]{
            Pattern.compile("!( |$)").matcher(""),
            Pattern.compile("\\.( |$)").matcher(""),
            Pattern.compile(",( |$)").matcher("")
    };
    private static final HashMap<Matcher, String> matchers = new HashMap<Matcher, String>(){{
        put(Pattern.compile("(?<!\u00A7)(?:l|r)").matcher(""), "w");
        put(Pattern.compile("(?:L|R)").matcher(""), "W");
        put(Pattern.compile("(?<!\u00A7|\\\\)n([aeiou])").matcher(""), "ny$1");
        put(Pattern.compile("N([aeiou])").matcher(""), "Ny$1");
        put(Pattern.compile("N([AEIOU])").matcher(""), "NY$1");
    }};

    @Mod.EventHandler
    private void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new CommandOwO());
    }

    public static String makeOwO(String string) {
        if (!toggled) return string;

        string = StringUtils.replace(string, "th","d");
        string = StringUtils.replace(string, "Th","D");
        string = StringUtils.replace(string, " is"," ish");
        string = StringUtils.replace(string, " Is"," Ish");
        string = StringUtils.replace(string, "ove", "uv");

        for (Map.Entry<Matcher, String> match : matchers.entrySet()) {
            string = match.getKey().reset(string).replaceAll(match.getValue());
        }

        for (Matcher match : faceText) {
            string = match.reset(string).replaceAll(faces[string.length() % faces.length]);
        }

        return string;
    }
}
