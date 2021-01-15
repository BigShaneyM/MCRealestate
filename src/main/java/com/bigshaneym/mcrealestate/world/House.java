package com.bigshaneym.mcrealestate.world;

import org.bukkit.Location;

import java.util.List;

public class House {

    private WorldAABB houseAABB;
    private String houseName;
    private double listed_price;
    private String ownerUUID;
    private List<String> guestUUIDs;
    private int chest_storage_amount;



    public boolean isLocationInBounds(Location toCheck) {
        if (!toCheck.getWorld().getName().equalsIgnoreCase(this.houseAABB.getWorldName()))
            return false;
        if (this.houseAABB.getMinX() > toCheck.getX() || this.houseAABB.getMaxX() < toCheck.getX())
            return false;
        if (this.houseAABB.getMinY() > toCheck.getY() || this.houseAABB.getMaxY() < toCheck.getY())
            return false;
        if (this.houseAABB.getMinZ() > toCheck.getZ() || this.houseAABB.getMaxZ() < toCheck.getZ())
            return false;
        return true;
    }

    public static WorldAABB getAABBFromLocations(Location l0, Location l) {
        int minX = l0.getBlockX();
        int maxX = l.getBlockX();

        if (minX > maxX) {
            int temp = maxX;
            maxX = minX;
            minX = temp;
        }

        int minY = l0.getBlockY();
        int maxY = l.getBlockY();

        if (minY > maxY) {
            int temp = maxY;
            maxY = minY;
            minY = temp;
        }

        int minZ = l0.getBlockZ();
        int maxZ = l.getBlockZ();

        if (minZ > maxZ) {
            int temp = maxZ;
            maxZ = minZ;
            minZ = temp;
        }

        return new WorldAABB(minX, minY, minZ, maxX, maxY, maxZ, l0.getWorld().getName());
    }

}
