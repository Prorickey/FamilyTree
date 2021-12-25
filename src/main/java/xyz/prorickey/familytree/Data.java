package xyz.prorickey.familytree;

import org.bukkit.*;
import org.bukkit.configuration.file.*;

import java.io.*;

public class Data {

    public static FileConfiguration getUser(String uuid) {
        File f = new File(FamilyTree.getPlugin().getDataFolder() + "/userdata/" + uuid + ".yml");
        if(!f.exists()) {
            f.getParentFile().mkdir();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileConfiguration config = YamlConfiguration.loadConfiguration(f);
            config.set("name", Bukkit.getOfflinePlayer(uuid).getName());
            config.set("marriage.married", false);
            config.set("marriage.divorced", false);
            config.set("marriage.spouse", null);
            config.set("marriage.marriedSince", null);
            config.set("marriage.divorcedSince", null);
            try {
                config.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return YamlConfiguration.loadConfiguration(f);
    }

}
