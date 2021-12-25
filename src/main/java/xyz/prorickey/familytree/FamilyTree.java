package xyz.prorickey.familytree;

import org.bukkit.*;
import org.bukkit.plugin.java.*;
import xyz.prorickey.familytree.commands.*;

public class FamilyTree extends JavaPlugin {

    public static FamilyTree plugin;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Enabling FamilyTree v" + getDescription().getVersion());
        plugin = this;
        new Config(this);
        new CmdFamilyTree(this);

    }

    public static JavaPlugin getPlugin() { return plugin; }
}
