package com.kerbybit.minecwaft;

import io.netty.util.internal.StringUtil;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mod(modid = MinecwaftOwO.MODID, version = MinecwaftOwO.VERSION)
public class MinecwaftOwO {
    static final String MODID = "MinecwaftOwO";
    static final String VERSION = "1.0";

    //private static final String[] faces = new String[]{"(*^ω^)", "(◕‿◕✿)", "(◕ᴥ◕)", "ʕ•ᴥ•ʔ", "ʕ￫ᴥ￩ʔ", "(*^.^*)", "owo", "(｡♥‿♥｡)", "uwu", "(*￣з￣)", ">w<", "^w^", "(つ✧ω✧)つ", "(/ =ω=)/"};
    private static final String[] faces = new String[]{"(*^w^)", "(*^.^*)", "owo", "uwu", ">w<", "^w^", "(/ =w=)/"};
    private static final Matcher pattern1 = Pattern.compile("(?:l|r)").matcher("");
    private static final Matcher pattern2 = Pattern.compile("(?:L|R)").matcher("");
    private static final Matcher pattern3 = Pattern.compile("n([aeiou])").matcher("");
    private static final Matcher pattern4 = Pattern.compile("N([aeiou])").matcher("");
    private static final Matcher pattern5 = Pattern.compile("N([AEIOU])").matcher("");

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    public static String makeOwO(String string) {
        

        string = StringUtils.replace(string, "th","d");
        string = StringUtils.replace(string, "Th","D");
        string = StringUtils.replace(string, " is"," ish");
        string = StringUtils.replace(string, " Is"," Ish");
        string = StringUtils.replace(string, "ove", "uv");


        string = pattern1.reset(string).replaceAll("w");
        string = pattern2.reset(string).replaceAll("W");
        string = pattern3.reset(string).replaceAll("ny$1");
        string = pattern4.reset(string).replaceAll("Ny$1");
        string = pattern5.reset(string).replaceAll("Ny$1");



        string = string.replaceAll("!( |$)", getFace(string));
        string = string.replaceAll("\\.( |$)", getFace(string));
        string = string.replaceAll(",( |$)", getFace(string));

        return string;
    }

    private static String getFace(String string) {
        return faces[string.length() % faces.length];
    }
}
