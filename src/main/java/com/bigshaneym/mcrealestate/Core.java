package com.bigshaneym.mcrealestate;

import com.bigshaneym.mcrealestate.commands.HouseCommand;
import com.bigshaneym.mcrealestate.housing.HouseEditor;
import com.bigshaneym.mcrealestate.housing.listeners.HouseEditorChatListener;
import com.bigshaneym.mcrealestate.housing.listeners.HouseEditorLeaveServerListener;
import com.bigshaneym.mcrealestate.housing.listeners.HouseEditorPlayerInteract;
import com.bigshaneym.mcrealestate.housing.listeners.HouseEditorSignPlaceEvent;
import com.bigshaneym.mcrealestate.world.House;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    public void onEnable() {
        registerCommands();
        registerListeners();
        HouseEditor.createMap();
        House.createHouseMap();
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
}
