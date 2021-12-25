package xyz.prorickey.familytree.commands;

import org.bukkit.command.*;
import org.bukkit.plugin.java.*;
import xyz.prorickey.api.chat.*;

public class CmdFamilyTree implements CommandExecutor {

    public static JavaPlugin plugin;

    public CmdFamilyTree(JavaPlugin p) {
        plugin = p;
        p.getCommand("familytree").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        sender.sendMessage(Chat.format("&aFamilyTree v" + plugin.getDescription().getVersion() + " - A plugin to create families with your friends."));
        sender.sendMessage(Chat.format("&6" + "come back to this prorickey"));
        return true;
    }
}
