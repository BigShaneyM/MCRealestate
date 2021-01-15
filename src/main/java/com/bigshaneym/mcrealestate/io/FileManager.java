package com.bigshaneym.mcrealestate.io;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public abstract class FileManager {

    private FileConfiguration fileConfig;
    private File file;
    private String fileName;
    private Plugin plugin;

    public FileManager(Plugin pl, String fileName) {
        this(pl, fileName, null);
    }

    public FileManager(Plugin pl, String fileName, String dir) {
        this.plugin = pl;
        this.fileName = fileName;
        setUp(dir);
    }


    private void setUp(String dir) {
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();

        if (dir == null)
            file = new File(plugin.getDataFolder(), fileName);
        else
            file = new File(plugin.getDataFolder() + File.separator + dir, fileName);

        if (!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e)
            {
                System.out.println(ChatColor.RED + "Could not create file " + fileName + "!");
                e.printStackTrace();
            }
        }
        loadConfig();
        setDefaultConfigData();
        this.fileConfig.options().copyDefaults(true);
        saveConfig();
    }

    public void loadConfig() {
        fileConfig = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getFileConfig() {
        return fileConfig;
    }

    public File getFile() {
        return file;
    }

    public void saveConfig() {
        try {
            fileConfig.save(file);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().warning(ChatColor.RED + "Could not save file " + fileName + "!");
            e.printStackTrace();
        }
    }

    public abstract void setDefaultConfigData();

}
