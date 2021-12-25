package xyz.prorickey.familytree;

import org.bukkit.configuration.file.*;
import org.bukkit.plugin.java.*;
import xyz.prorickey.api.chat.*;

import java.io.*;

public class Config {
    private static JavaPlugin plugin;
    private static File file;
    private static FileConfiguration config;
    private static File lfile;
    private static FileConfiguration lang;
    public Config(JavaPlugin p) {
        plugin = p;
        file = new File(p.getDataFolder() + "/config.yml");
        lfile = new File(p.getDataFolder() + "/lang.yml");
        if(!file.exists()) { p.saveResource("config.yml", false); }
        if(!lfile.exists()) { p.saveResource("lang.yml", false); }
        config = YamlConfiguration.loadConfiguration(file);
        lang = YamlConfiguration.loadConfiguration(lfile);
    }
    public static FileConfiguration getConfig() { return config; }
    public static String getLang(String node) { return Chat.format(lang.getString(node)); }
    public static void saveConfig() {
        try {
            getConfig().save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void reloadConfig() { config = YamlConfiguration.loadConfiguration(file); }
    public static void reloadLang() { lang = YamlConfiguration.loadConfiguration(lfile); }

}
