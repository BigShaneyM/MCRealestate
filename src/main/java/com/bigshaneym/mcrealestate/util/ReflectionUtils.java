package com.bigshaneym.mcrealestate.util;

import org.bukkit.Bukkit;

public class ReflectionUtils {

    private static ReflectionUtils instance = null;

    private String BUILD_VERSION = null;

    private ReflectionUtils() {
        BUILD_VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

    public static ReflectionUtils getInstance() {
        if (instance == null) {
            instance = new ReflectionUtils();
        }
        return instance;
    }

    public String getBuildVersion() {
        return BUILD_VERSION;
    }

    public Class<?> getNMSClass(String className) {
        return getFromClassName(("net.minecraft.server." + BUILD_VERSION), className);
    }

    public Class<?> getCraftBukkitClass(String className) {
        return getFromClassName(("org.bukkit.craftbukkit." + BUILD_VERSION), className);
    }


    public Class<?> getFromClassName(String packageName, String className) {
        Class<?> clazz = null;

        try {
            clazz = Class.forName(packageName + "." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Could not find class '" + packageName + "." + className + "'");
        }

        return clazz;
    }

}
