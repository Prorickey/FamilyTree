package xyz.prorickey.familytree.commands;

import net.md_5.bungee.api.chat.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.configuration.file.*;
import org.bukkit.entity.*;
import xyz.prorickey.api.chat.*;
import xyz.prorickey.familytree.*;

import java.text.*;
import java.util.*;

public class CmdMarry implements CommandExecutor, TabCompleter {

    public static Map<String, >

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            sender.sendMessage(Config.getLang("CannotExecuteFromConsole"));
            return true;
        }
        Player p = (Player) sender;
        FileConfiguration data = Data.getUser(p.getUniqueId().toString());
        if(args.length == 0) {
            TextComponent comp = new TextComponent(Chat.format("&6Marriage Status\n"));
            comp.addExtra(Chat.format("&aMarried &7- " + (data.getBoolean("marriage.married") ? "&aYes" : "&cNo") + "\n"));
            if(data.getBoolean("marriage.married")) {
                OfflinePlayer spouse = Bukkit.getOfflinePlayer(data.getString("marriage.spouse"));
                comp.addExtra(Chat.format("&aSpouse &7- &e" + spouse.getName()));
                SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
                String dateText = df2.format(data.getLong("marriage.marriedSince") * 1000);
                comp.addExtra(Chat.format("&aMarried Since &7- &e" + dateText));
            } else if(data.getBoolean("marriage.divorced")) {
                SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
                String dateText = df2.format(data.getLong("marriage.divorcedSince") * 1000);
                comp.addExtra(Chat.format("&aDivorced Since &7- &e" + dateText));
            }
            return true;
        }
        if(!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
            p.sendMessage(Config.getLang("PlayerNotOnline"));
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        FileConfiguration otherdata = Data.getUser(target.getUniqueId().toString());
        if(data.getBoolean("marriage.married")) {
            p.sendMessage(Config.getLang("AlreadyMarried"));
            return true;
        }
        if(otherdata.getBoolean("marriage.married")) {
            p.sendMessage(Config.getLang("TargetAlreadyMarried"));
            return true;
        }



        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 1) {
            List<String> players = new ArrayList<>();
            Bukkit.getOnlinePlayers().forEach(p -> { players.add(p.getName()); });
            return TabComplete.tabCompletionsSearch(args[0], players);
        }
        return TabComplete.emptyList();
    }
}
