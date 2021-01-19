package com.bigshaneym.mcrealestate.util;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class Utilities {

    public static String toColor(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public static String getLocationString(Location l) {
        return "X:" + l.getBlockX() + " Y:" + l.getBlockY() + " Z:" + l.getBlockZ();
    }
}
