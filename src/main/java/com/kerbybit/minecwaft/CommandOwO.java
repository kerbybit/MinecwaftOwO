package com.kerbybit.minecwaft;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandOwO extends CommandBase {
    @Override
    public String getCommandName() {
        return "minecwaftowo";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/minecwaftowo - toggles owo mode";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        MinecwaftOwO.toggled = !MinecwaftOwO.toggled;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("\u00a77Toggled OwO mode " + (MinecwaftOwO.toggled ? "\u00a7aON" : "\u00a7cOFF")));
    }
}
