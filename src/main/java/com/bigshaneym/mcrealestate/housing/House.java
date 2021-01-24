package com.bigshaneym.mcrealestate.housing;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class House {

    private static Map<String, House> houseMap;

    private WorldAABB houseAABB;
    private String houseName;
    private int listed_price;
    private Set<String> ownerUUIDs;
    private Map<String, Set<String>> guestUUIDs;
    private int chest_storage_amount;
    private HousingTypes type = HousingTypes.NULL_TYPE;
    private Location teleport;

    public House(WorldAABB houseAABB, String houseName, int listed_price, Set<String> ownerUUIDs, Map<String, Set<String>> guestUUIDs, int chest_storage_amount, HousingTypes type, Location teleport) {
        this.houseAABB = houseAABB;
        this.houseName = houseName;
        this.listed_price = listed_price;
        this.ownerUUIDs = ownerUUIDs;
        this.guestUUIDs = guestUUIDs;
        this.chest_storage_amount = chest_storage_amount;
        this.type = type;
        this.teleport = teleport;

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

    public Set<String> getOwnerUUIDs() {
        return this.ownerUUIDs;
    }

    public Map<String, Set<String>> getGuestUUIDs() {
        return guestUUIDs;
    }

    public int getChestStorageAmount() {
        return chest_storage_amount;
    }

    public HousingTypes getType() {
        return type;
    }

    public Location getTeleport() {
        return teleport;
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

    public static void setHouseMap(Map<String, House> houseMap) {
        House.houseMap = houseMap;
    }

    public static void clearHouseMap() {
        houseMap.clear();
        houseMap = null;
    }

}
