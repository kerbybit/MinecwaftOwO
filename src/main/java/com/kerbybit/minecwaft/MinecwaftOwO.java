package com.kerbybit.minecwaft;

import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mod(modid = MinecwaftOwO.MODID, version = MinecwaftOwO.VERSION)
public class MinecwaftOwO {
    static final String MODID = "MinecwaftOwO";
    static final String VERSION = "1.0";

    //private static final String[] faces = new String[]{"(*^ω^)", "(◕‿◕✿)", "(◕ᴥ◕)", "ʕ•ᴥ•ʔ", "ʕ￫ᴥ￩ʔ", "(*^.^*)", "owo", "(｡♥‿♥｡)", "uwu", "(*￣з￣)", ">w<", "^w^", "(つ✧ω✧)つ", "(/ =ω=)/"};
    private static final String[] faces = new String[]{"(*^w^)", "(*^.^*)", "owo", "uwu", ">w<", "^w^", "(/ =w=)/"};
    private static final Matcher[] faceText = new Matcher[]{
            Pattern.compile("!( |$)").matcher(""),
            Pattern.compile("\\.( |$)").matcher(""),
            Pattern.compile(",( |$)").matcher("")
    };
    private static final HashMap<Matcher, String> matchers = new HashMap<Matcher, String>(){{
        put(Pattern.compile("(?:l|r)").matcher(""), "w");
        put(Pattern.compile("(?:L|R)").matcher(""), "W");
        put(Pattern.compile("n([aeiou])").matcher(""), "ny$1");
        put(Pattern.compile("N([aeiou])").matcher(""), "Ny$1");
        put(Pattern.compile("N([AEIOU])").matcher(""), "NY$1");
    }};

    public static String makeOwO(String string) {
        string = StringUtils.replace(string, "th","d");
        string = StringUtils.replace(string, "Th","D");
        string = StringUtils.replace(string, " is"," ish");
        string = StringUtils.replace(string, " Is"," Ish");
        string = StringUtils.replace(string, "ove", "uv");

        for (Map.Entry<Matcher, String> match : matchers.entrySet()) {
            string = match.getKey().reset(string).replaceAll(match.getValue());
        }

        for (Matcher match : faceText) {
            string = match.reset(string).replaceAll(getFace(string));
        }

        return string;
    }

    private static String getFace(String string) {
        return faces[string.length() % faces.length];
    }
}
