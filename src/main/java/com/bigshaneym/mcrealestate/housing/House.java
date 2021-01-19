package com.bigshaneym.mcrealestate.housing;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class House {

    private static Map<String, House> houseMap;

    private WorldAABB houseAABB;
    private String houseName;
    private int listed_price;
    private String ownerUUID;
    private List<String> guestUUIDs;
    private int chest_storage_amount;
    private HousingTypes type = HousingTypes.NULL_TYPE;

    public House(WorldAABB houseAABB, String houseName, int listed_price, String ownerUUID, List<String> guestUUIDs, int chest_storage_amount, HousingTypes type) {
        this.houseAABB = houseAABB;
        this.houseName = houseName;
        this.listed_price = listed_price;
        this.ownerUUID = ownerUUID;
        this.guestUUIDs = guestUUIDs;
        this.chest_storage_amount = chest_storage_amount;
        this.type = type;

        houseMap.put(houseName, this);
    }

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

    public void removeHouse() {
        houseMap.remove(houseName);
    }


    public WorldAABB getHouseAABB() {
        return houseAABB;
    }

    public String getHouseName() {
        return houseName;
    }

    public int getListedPrice() {
        return listed_price;
    }

    public String getOwnerUUID() {
        return ownerUUID;
    }

    public List<String> getGuestUUIDs() {
        return guestUUIDs;
    }

    public int getChestStorageAmount() {
        return chest_storage_amount;
    }

    public HousingTypes getType() {
        return type;
    }

    /**STATIC METHODS HERE*/

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

    public static Map<String, House> getHouseMap() {
        return houseMap;
    }

    public static void createHouseMap() {
        houseMap = new HashMap<String, House>();
    }

    public static void clearHouseMap() {
        houseMap.clear();
        houseMap = null;
    }

}
