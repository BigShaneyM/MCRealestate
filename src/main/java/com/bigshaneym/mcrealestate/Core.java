package com.bigshaneym.mcrealestate;

import com.bigshaneym.mcrealestate.commands.HouseCommand;
import com.bigshaneym.mcrealestate.housing.HouseEditor;
import com.bigshaneym.mcrealestate.housing.listeners.HouseEditorChatListener;
import com.bigshaneym.mcrealestate.housing.listeners.HouseEditorLeaveServerListener;
import com.bigshaneym.mcrealestate.housing.listeners.HouseEditorPlayerInteract;
import com.bigshaneym.mcrealestate.housing.listeners.HouseEditorSignPlaceEvent;
import com.bigshaneym.mcrealestate.housing.House;
import com.bigshaneym.mcrealestate.io.ConfigManager;
import com.bigshaneym.mcrealestate.io.HouseIOManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private static ConfigManager configManager;
    private static HouseIOManager houseIOManager;

    public void onEnable() {
        registerCommands();
        registerListeners();
        HouseEditor.createMap();
        House.createHouseMap();
        registerFiles();
        System.out.println("MCRealestate enabled");
    }

    public void onDisable() {
        HouseEditor.clearEditors();
        House.clearHouseMap();
        System.out.println("MCRealestate disabled");
    }

    public static Plugin getHousingPlugin() {
        return Bukkit.getPluginManager().getPlugin("MCRealEstate");
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new HouseEditorChatListener(), this);
        this.getServer().getPluginManager().registerEvents(new HouseEditorLeaveServerListener(), this);
        this.getServer().getPluginManager().registerEvents(new HouseEditorSignPlaceEvent(), this);
        this.getServer().getPluginManager().registerEvents(new HouseEditorPlayerInteract(), this);
    }

    private void registerCommands() {
        this.getCommand("house").setExecutor(new HouseCommand());
    }

    private void registerFiles() {
        configManager = new ConfigManager();
        houseIOManager = new HouseIOManager();
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    public static HouseIOManager getHouseIOManager() {
        return houseIOManager;
    }
}
