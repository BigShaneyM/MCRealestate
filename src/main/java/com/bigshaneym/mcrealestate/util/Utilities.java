package com.bigshaneym.mcrealestate.util;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utilities {

    public static String toColor(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public static String getLocationString(Location l) {
        return "X:" + l.getBlockX() + " Y:" + l.getBlockY() + " Z:" + l.getBlockZ();
    }

    public static Set<String> getStringSetFromStringList(List<String> list) {
        Set<String> set = new HashSet<String>();
        for (String s : list) {
            set.add(s);
        }
        return set;
    }

    public static Location fromString(World world, String rawLoc) {
        String[] l = rawLoc.split(",");
        int x = Integer.parseInt(l[0]);
        int y = Integer.parseInt(l[1]);
        int z = Integer.parseInt(l[2]);
        return new Location(world, x, y, z);
    }
}
