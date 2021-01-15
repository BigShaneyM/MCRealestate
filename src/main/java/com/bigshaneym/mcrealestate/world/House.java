package com.bigshaneym.mcrealestate.world;

import org.bukkit.Location;

import java.util.List;

public class House {

    private List<WorldAABB> houseAABBs;
    private String houseName;
    private double listed_price;
    private List<String> owner_uuid;



    public boolean isLocationInBounds(Location toCheck) {
        for (WorldAABB aabb : houseAABBs) {
            if (!toCheck.getWorld().getName().equalsIgnoreCase(aabb.getWorldName()))
                return false;
            if (aabb.getMinX() > toCheck.getX() || aabb.getMaxX() < toCheck.getX())
                return false;
            if (aabb.getMinY() > toCheck.getY() || aabb.getMaxY() < toCheck.getY())
                return false;
            if (aabb.getMinZ() > toCheck.getZ() || aabb.getMaxZ() < toCheck.getZ())
                return false;
        }
        return true;
    }

}
